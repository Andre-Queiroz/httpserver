package calcparser;

public class CalcOpParser {
    private enum ParserState {
        WaitingOp,
        ReadingOp,
        WaitingV1,
        ReadingV1,
        WaitingV2,
        ReadingV2,
    }

    private float parseValue(String value) throws NumberFormatException {
        return Float.parseFloat(value);
    }

    public CalcOpData parseOperationStr(String operation) throws RuntimeException {
        ParserState parseState = ParserState.WaitingOp;
        char[] chars = new char[operation.length()];
        char currentChar;
        String buffer = new String();

        OperationType opType = null;
        float v1 = 0;
        float v2 = 0;

        operation.getChars(0, operation.length(), chars, 0);

        for (int i = 0; i < operation.length(); i++) {
            buffer += chars[i];

            switch (parseState) {
                case WaitingOp:
                    if(buffer.equals("op=")) {
                        parseState = ParserState.ReadingOp;
                        buffer = "";
                    }
                    break;
                case ReadingOp:
                    if(i < operation.length() - 1 && chars[i + 1] == '&') {
                        parseState = ParserState.WaitingV1;
                        opType = CalcOpData.opTypeFromString(buffer);
                        buffer = "";
                    }
                    break;
                case WaitingV1:
                    if(buffer.equals("&v1=")) {
                        parseState = ParserState.ReadingV1;
                        buffer = "";
                    }
                    break;
                case ReadingV1:
                    if(i < operation.length() - 1 && chars[i + 1] == '&') {
                        parseState = ParserState.WaitingV2;
                        v1 = parseValue(buffer);
                        buffer = "";
                    }
                    break;
                case WaitingV2:
                    if(buffer.equals("&v2=")) {
                        parseState = ParserState.ReadingV2;
                        buffer = "";
                    }
                    break;
            }
        }
        if(parseState == ParserState.ReadingV2) v2 = parseValue(buffer);
        else throw new RuntimeException("Erro no parser!");

        return new CalcOpData(opType, v1, v2);
    }
}
