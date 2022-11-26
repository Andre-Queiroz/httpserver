package calcparser;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;

public class CalcOpData {
    private static final String keySoma = "soma";
    private static final String keySub = "subtracao";
    private static final String keyMul = "multiplicacao";
    private static final String keyDiv = "divisao";

    private static final HashMap<String, OperationType> stringToOp = new HashMap<>() {{
        put(keySoma, OperationType.SUM);
        put(keySub, OperationType.SUB);
        put(keyMul, OperationType.MUL);
        put(keyDiv, OperationType.DIV);
    }};

    @JsonProperty("op")
    public String op;
    @JsonProperty("v1")
    public float v1;
    @JsonProperty("v2")
    public float v2;

    public CalcOpData() {

    }

    public CalcOpData(String op, float v1, float v2) {
        this.op = op;
        this.v1 = v1;
        this.v2 = v2;
    }

    public OperationType getOpType() {
        return opTypeFromString(op);
    }

    public static OperationType opTypeFromString(String opType) {
        if (stringToOp.containsKey(opType)) return stringToOp.get(opType);
        else throw new RuntimeException("Operacao inexistente");
    }
}
