package httpserver.web;

import calcparser.CalcOpData;
import calcparser.CalcOpParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Scanner;

public class HttpRequestProcessor {
    private CalcOpParser parser;

    public HttpRequestProcessor() {
        parser = new CalcOpParser();
    }

    public boolean isRequestValid(String request) {
        Scanner scanner = new Scanner(request);

        if(!scanner.hasNext()) return false;

        String firstLine = scanner.nextLine();

        return (firstLine.startsWith("GET ") || firstLine.startsWith("POST ")) &&
                (firstLine.endsWith(" HTTP/1.0") || firstLine.endsWith(" HTTP/1.1"));
    }

    public String getUrlPath(String request) {
        Scanner scanner = new Scanner(request);

        if(!scanner.hasNext()) return "";

        String firstLine = scanner.nextLine();

        return firstLine
            .replaceAll("GET ", "")
            .replaceAll("POST ", "")
            .replaceAll(" HTTP/1.0", "")
            .replaceAll(" HTTP/1.1", "")
            .replaceAll("/favicon.ico", "");
    }

    public boolean isPathValid(String path) {
        return path.startsWith("/api/calculator/single-thread") ||
            path.startsWith("/api/calculator/multi-thread");
    }

    public boolean isMultiThread(String path) {
        return path.contains("multi-thread");
    }

    public CalcOpData getCalcParams(String request)
    {
        String[] lines = request.split(System.getProperty("line.separator"));
        String buffer;
        CalcOpData result = new CalcOpData();

        if(lines[0].startsWith("GET ")) {
            buffer = lines[0].replaceAll("GET ", "")
                    .replaceAll("POST ", "")
                    .replaceAll(" HTTP/1.0", "")
                    .replaceAll(" HTTP/1.1", "")
                    .replaceAll("/favicon.ico", "");

            result = parser.parseOperationStr(buffer.substring(buffer.lastIndexOf("?") + 1));
        } else if (lines[0].startsWith("POST ")){
            buffer = "";
            int index = 1;
            for (; index < lines.length - 1; index++) {
                if(lines[index - 1].isBlank() && lines[index].contains("{")) break;
            }
            for (; index < lines.length; index++) {
                buffer += lines[index];
            }

            ObjectMapper mapper = new ObjectMapper();

            try{
                result = mapper.readValue(buffer, CalcOpData.class);
            } catch (JsonProcessingException pe) {
                throw new RuntimeException("Bad json!" + pe.toString());
            }
        } else {
            throw new RuntimeException("Bad request!");
        }
        return result;
    }

}
