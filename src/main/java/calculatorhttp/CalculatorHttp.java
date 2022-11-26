package calculatorhttp;

import calcparser.CalcOpData;

public class CalculatorHttp {
    public float executeOperation(CalcOpData opData) {
        return switch (opData.getOpType()) {
            case SUM -> opData.v1 + opData.v2;
            case SUB -> opData.v1 - opData.v2;
            case DIV -> opData.v1 / opData.v2;
            case MUL -> opData.v1 * opData.v2;
        };
    }
}
