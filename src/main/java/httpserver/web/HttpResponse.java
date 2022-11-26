package httpserver.web;

import java.util.Date;

public class HttpResponse {

    private static final String newLine = "\r\n";
    private static final String HTTP_SUCCESS = "200 OK";
    private static final String HTTP_BAD_REQUEST = "400 Bad Request";
    private static final String HTTP_NOT_FOUND = "404 Not Found";

    private final String baseResponse =
        "HTTP/1.0 " + "%s" + newLine +
            "Content-Type: text/html" + newLine +
            "Date: " + new Date() + newLine +
            "Content-length: " + "%d" + newLine + newLine +
            "%s";

    public String success(String response) {
        return String.format(baseResponse, HTTP_SUCCESS, response.length(), response);
    }

    public String badRequest(String response) {
        return String.format(baseResponse, HTTP_BAD_REQUEST, response.length(), response);
    }

    public String notFound(String response) {
        return String.format(baseResponse, HTTP_NOT_FOUND, response.length(), response);
    }

}
