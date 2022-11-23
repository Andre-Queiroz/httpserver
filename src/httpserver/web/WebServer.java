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

public class WebServer {

    private int port = 8080;
    private boolean isRequestMultiThread = false;

    public WebServer(int port) {
        this.port = port;
    }

    public void execute() {
        ServerSocket socket = null;
        HttpRequestProcessor processor;
        HttpResponse httpResponse;
        ThreadWorker worker;

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

                    String request = input.readLine();

                    if (request == null) {
                        continue;
                    }

                    // ignoring
                    while (true) {
                        String ignore = input.readLine();
                        if (ignore == null || ignore.length() == 0) {
                            break;
                        }
                    }

                    if (!processor.isRequestValid(request)) {
                        httpResponse.badRequest();
                    } else {
                        String path = processor.getUrlPath(request);

                        if (processor.isPathValid(path)) {
                            isRequestMultiThread = processor.isMultiThread(path);
                            String queryParams = processor.getQueryParams(path);

                            if (isRequestMultiThread) {
                                worker = new MultiThreadWorker(socket, connection);
                            } else {
                                worker = new SingleThreadWorker(socket, connection);
                            }

                            worker.execute(queryParams);
                        } else {
                            httpResponse.notFound();
                        }
                    }

                    printStream.close();
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
