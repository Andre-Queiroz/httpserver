package calcparser;

import com.fasterxml.jackson.annotation.JsonProperty;

public class CalcOpData {
    @JsonProperty("op")
    public OperationType op;
    @JsonProperty("v1")
    public float v1;
    @JsonProperty("v2")
    public float v2;

    public CalcOpData() {

    }

    public CalcOpData(OperationType op, float v1, float v2) {
        this.op = op;
        this.v1 = v1;
        this.v2 = v2;
    }
}
