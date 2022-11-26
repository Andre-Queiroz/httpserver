package httpserver.web;

import httpserver.worker.MultiThreadWorker;
import httpserver.worker.SingleThreadWorker;
import httpserver.worker.ThreadWorker;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class WebServer {

    private int port = 8080;

    public WebServer(int port) {
        this.port = port;
    }

    public void execute() {
        ServerSocket socket = null;
        HttpRequestProcessor processor;
        HttpResponse httpResponse;
        ThreadWorker worker;
        List<String> fullRequest = new ArrayList<>();

        try {
            socket = new ServerSocket(port);
            System.out.println("Web server listening on port " + port);

            while (true) {
                Socket connection = socket.accept();

                try {
                    BufferedReader input = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    OutputStream output = new BufferedOutputStream(connection.getOutputStream());
                    PrintStream printStream = new PrintStream(output);
                    httpResponse = new HttpResponse();
                    processor = new HttpRequestProcessor();
                    fullRequest.clear();

                    //String request = input.lines().toList();

                    fullRequest.add(input.readLine());
                    if (fullRequest.get(0) == null) {
                        continue;
                    }

                    // ignoring
                    while (input != null) {
                        String line = input.readLine();
                        if (line == null || line.length() == 0) {
                            break;
                        } else {
                            fullRequest.add(line);
                        }
                    }

                    if (!processor.isRequestValid(fullRequest)) {
                        printStream.print(httpResponse.badRequest());
                        printStream.close();
                    } else {
                        boolean isRequestMultiThread;
                        String path = processor.getUrlPath(fullRequest);

                        if (processor.isPathValid(path)) {
                            isRequestMultiThread = processor.isMultiThread(path);
                            String queryParams = processor.getQueryParams(path);

                            if (isRequestMultiThread) {
                                worker = new MultiThreadWorker(printStream);
                            } else {
                                worker = new SingleThreadWorker(printStream);
                            }
                            worker.execute(queryParams);
                        } else {
                            printStream.print(httpResponse.notFound());
                            printStream.close();
                        }
                    }
                } catch (Throwable throwable) {
                    System.err.println("Error receiving request: " + throwable);
                }
            }
        } catch (Throwable throwable) {
            System.err.println("Server failed to start: " + throwable);
        } finally {
            close(socket);
        }
    }

    private void close(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing socket");
        }
    }

}
