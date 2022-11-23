package calculatorhttp;

public class CalculatorHttp {
    private CalcOpParser parser;

    public CalculatorHttp() {
        parser = new CalcOpParser();
    }

    public float executeOperation(String operation) {
        CalcOpData calcOp = parser.parseOperation(operation);

        return 0.0f;
    }
}
