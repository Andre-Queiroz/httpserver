package httpserver.web;

public class HttpRequestProcessor {

    public boolean isRequestValid(String request) {
        return request.startsWith("GET ") && (request.endsWith(" HTTP/1.0") || request.endsWith(" HTTP/1.1"));
    }

    public String getUrlPath(String request) {
        return request
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
