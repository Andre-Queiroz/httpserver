package httpserver.worker;

import calcparser.CalcOpData;
import calculatorhttp.CalculatorHttp;
import htmlbuilder.HtmlBuilder;
import httpserver.web.HttpResponse;

import java.io.PrintStream;

public class MultiThreadWorker implements ThreadWorker, Runnable {

    private final CalculatorHttp calculator = new CalculatorHttp();
    private final HtmlBuilder htmlBuilder = new HtmlBuilder();
    private final HttpResponse httpResponse = new HttpResponse();
    private final PrintStream printStream;
    private CalcOpData opData;

    public MultiThreadWorker(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void run() {
        System.out.println("(MultiThread) Thread id: " + Thread.currentThread().getId());
        String html = htmlBuilder.generateSuccessHtml(calculator.executeOperation(opData));

        try {
            printStream.print(httpResponse.success(html));
            printStream.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void execute(CalcOpData opData) {
        this.opData = opData;
        new Thread(this).start();
    }

}
