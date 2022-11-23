package httpserver.worker;

import calculatorhttp.CalculatorHttp;
import htmlbuilder.HtmlBuilder;

public class MultiThreadWorker implements ThreadWorker, Runnable {

    private final CalculatorHttp calculator = new CalculatorHttp();
    private final HtmlBuilder htmlBuilder = new HtmlBuilder();
    private String queryParams;
    private volatile String html;

    @Override
    public void run() {
        System.out.println("Thread id: " + Thread.currentThread().getId());
        html = htmlBuilder.generateHtml(calculator.executeOperation(queryParams));
    }

    @Override
    public void execute(String queryParams) {
        this.queryParams = queryParams;
        new Thread(this).start();
    }

    @Override
    public String getResult() {
        return html;
    }

}
