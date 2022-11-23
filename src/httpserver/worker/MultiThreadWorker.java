package httpserver.worker;

import calculatorhttp.CalculatorHttp;
import htmlbuilder.HtmlBuilder;
import httpserver.web.HttpResponse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadWorker implements ThreadWorker, Runnable {

    private final CalculatorHttp calculator = new CalculatorHttp();
    private final HtmlBuilder htmlBuilder = new HtmlBuilder();
    private final HttpResponse httpResponse = new HttpResponse();
    private String queryParams;
    private ServerSocket tcpServerSocket;
    private Socket clientRequest;

    public MultiThreadWorker(ServerSocket tcpServerSocket, Socket clientRequest) {
        this.tcpServerSocket = tcpServerSocket;
        this.clientRequest = clientRequest;
    }

    @Override
    public void run() {
        System.out.println("Thread id: " + Thread.currentThread().getId());

        DataOutputStream dos =  null;

        try {
            dos =  new DataOutputStream(clientRequest.getOutputStream());
        } catch (IOException ex) {
            ex.printStackTrace();
            return;
        }

        System.out.println("\nThreadId: " + Thread.currentThread().getId());
        String format  = "\nServidorST: Request recebido de: %s:%s";
        System.out.printf(format, clientRequest.getInetAddress().getHostName(), clientRequest.getPort());
        String html = htmlBuilder.generateHtml(calculator.executeOperation(queryParams));

        try {
            dos.writeUTF(httpResponse.ok(html));
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(String queryParams) {
        this.queryParams = queryParams;
        new Thread(this).start();
    }

}
