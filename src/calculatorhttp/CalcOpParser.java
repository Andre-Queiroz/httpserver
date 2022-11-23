package calculatorhttp;

public class CalcOpParser {
    private enum ParserState {

    }
    public CalcOpData parseOperation(String operation) {
        char[] chars = new char[operation.length()];
        char currentChar;
        String buffer = new String();

        OperationType opType = null;
        float v1 = 0;
        float v2 = 0;

        operation.getChars(0, operation.length() - 1, chars, 0);

        for (int i = 0; i < operation.length(); i++) {
            currentChar = chars[i];


            buffer += currentChar;


        }

        return new CalcOpData(opType, v1, v2);
    }
}
