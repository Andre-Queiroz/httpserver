package httpserver.worker;

import calculatorhttp.CalculatorHttp;
import htmlbuilder.HtmlBuilder;
import httpserver.web.HttpResponse;
import java.io.PrintStream;

public class SingleThreadWorker implements ThreadWorker {

    private final CalculatorHttp calculator = new CalculatorHttp();
    private final HtmlBuilder htmlBuilder = new HtmlBuilder();
    private final HttpResponse httpResponse = new HttpResponse();
    private final PrintStream printStream;

    public SingleThreadWorker(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void execute(String queryParams) {
        System.out.println("(SingleThread) Thread id: " + Thread.currentThread().getId());
        String html = htmlBuilder.generateSuccessHtml(calculator.executeOperation(queryParams));
        try {
            printStream.print(httpResponse.success(html));
            printStream.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

}
