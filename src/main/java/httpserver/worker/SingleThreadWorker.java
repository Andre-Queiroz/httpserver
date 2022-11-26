package httpserver.worker;

import calcparser.CalcOpData;
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
    public void execute(CalcOpData opData) {
        System.out.println("(SingleThread) Thread id: " + Thread.currentThread().getId());
        String html = htmlBuilder.generateHtml(calculator.executeOperation(opData));

        try {
            printStream.print(httpResponse.ok(html));
            printStream.close();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

}
