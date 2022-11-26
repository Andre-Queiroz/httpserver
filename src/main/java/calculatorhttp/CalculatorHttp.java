package calculatorhttp;

import calcparser.CalcOpData;

public class CalculatorHttp {
    public float executeOperation(CalcOpData opData) {
        switch (opData.getOpType()) {
            case SUM:
                return opData.v1 + opData.v2;
            case SUB:
                return opData.v1 - opData.v2;
            case DIV:
                return opData.v1 / opData.v2;
            case MUL:
                return opData.v1 * opData.v2;
        }
        return 0.0f;
    }
}
