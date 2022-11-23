package calculatorhttp;

public class CalculatorHttp {
    private CalcOpParser parser;

    private float executeCalc(CalcOpData calcData) {
        switch (calcData.getOperationType()) {
            case SUM:
                return calcData.getValue1() + calcData.getValue2();
            case SUB:
                return calcData.getValue1() - calcData.getValue2();
            case DIV:
                return calcData.getValue1() / calcData.getValue2();
            case MUL:
                return calcData.getValue1() * calcData.getValue2();
        }
        return 0.0f;
    }

    public CalculatorHttp() {
        parser = new CalcOpParser();
    }

    public float executeOperation(String operation) {
        CalcOpData calcOp = parser.parseOperationStr(operation);
        return executeCalc(calcOp);
    }
}
