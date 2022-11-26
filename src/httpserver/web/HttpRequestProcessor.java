package httpserver.web;

import calcparser.CalcOpData;
import calcparser.CalcOpParser;
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
        String queryParams;

        if(lines[0].startsWith("GET ")) {
            queryParams = lines[0].replaceAll("GET ", "")
                    .replaceAll("POST ", "")
                    .replaceAll(" HTTP/1.0", "")
                    .replaceAll(" HTTP/1.1", "")
                    .replaceAll("/favicon.ico", "");

            return parser.parseOperationStr(queryParams.substring(queryParams.lastIndexOf("?") + 1));
        } else if (lines[0].startsWith("POST ")){

        } else {
            throw new RuntimeException("Bad request!");
        }
        return new CalcOpData();
    }

}
