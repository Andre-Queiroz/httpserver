package httpserver.worker;

import calculatorhttp.CalculatorHttp;
import htmlbuilder.HtmlBuilder;
import httpserver.web.HttpResponse;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SingleThreadWorker implements ThreadWorker {

    private final CalculatorHttp calculator = new CalculatorHttp();
    private final HtmlBuilder htmlBuilder = new HtmlBuilder();
    private final HttpResponse httpResponse = new HttpResponse();
    private String html;

    private ServerSocket tcpServerSocket;
    private Socket clientRequest;
    private PrintStream responseStream;

    public SingleThreadWorker(PrintStream responseStream) {
        this.responseStream = responseStream;
    }
    @Override
    public void execute(String queryParams) {
        System.out.println("Thread id: " + Thread.currentThread().getId());

        System.out.println("\nThreadId: " + Thread.currentThread().getId());
        String html = htmlBuilder.generateHtml(calculator.executeOperation(queryParams));

        try {
            responseStream.print(httpResponse.ok(html));
            responseStream.close();
            return;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

}
