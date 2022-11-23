package calculatorhttp;

public class CalcOpData {
    private OperationType operation;
    private float v1;
    private float v2;

    public  CalcOpData(OperationType operationType, float value1, float value2) {
        operation = operationType;
        v1 = value1;
        v2 = value2;
    }

    public OperationType getOperationType() { return operation; }
    public float getValue1() { return v1; }
    public float getValue2() { return v2; }
}
