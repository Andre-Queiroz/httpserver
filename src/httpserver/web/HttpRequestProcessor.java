package httpserver.web;

import java.util.List;

public class HttpRequestProcessor {

    public boolean isRequestValid(List<String> request) {
        return request.get(0).startsWith("GET ") && (request.get(0).endsWith(" HTTP/1.0") || request.get(0).endsWith(" HTTP/1.1"));
    }

    public String getUrlPath(List<String> request) {
        return request.get(0)
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

    public String getQueryParams(String path) {
        return path.substring(path.lastIndexOf("?") + 1);
    }

}
