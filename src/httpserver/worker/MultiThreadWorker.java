package httpserver.worker;

import calculatorhttp.CalculatorHttp;
import htmlbuilder.HtmlBuilder;
import httpserver.web.HttpResponse;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiThreadWorker implements ThreadWorker, Runnable {

    private final CalculatorHttp calculator = new CalculatorHttp();
    private final HtmlBuilder htmlBuilder = new HtmlBuilder();
    private final HttpResponse httpResponse = new HttpResponse();
    private String queryParams;
    private PrintStream responseStream;

    public MultiThreadWorker(PrintStream responseStream) {
        this.responseStream = responseStream;
    }

    @Override
    public void run() {
        System.out.println("Thread id: " + Thread.currentThread().getId());

        System.out.println("\nThreadId: " + Thread.currentThread().getId());
        String html = htmlBuilder.generateHtml(calculator.executeOperation(queryParams));
        System.out.println("\nQuery: " + queryParams);
        System.out.println("\nHtml: " + html);

        try {
            responseStream.print(httpResponse.ok(html));
            responseStream.close();
            return;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(String queryParams) {
        this.queryParams = queryParams;
        new Thread(this).start();
    }

}
