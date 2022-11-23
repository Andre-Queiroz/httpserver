package httpserver.worker;

import calculatorhttp.CalculatorHttp;
import htmlbuilder.HtmlBuilder;

public class SingleThreadWorker implements ThreadWorker {

    private final CalculatorHttp calculator = new CalculatorHttp();
    private final HtmlBuilder htmlBuilder = new HtmlBuilder();
    private String html;

    @Override
    public void execute(String queryParams) {
        html = htmlBuilder.generateHtml(calculator.executeOperation(queryParams));
    }

    @Override
    public String getResult() {
        return html;
    }

}
