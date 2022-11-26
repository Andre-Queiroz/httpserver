package httpserver.web;

import java.util.Date;

public class HttpResponse {

    private static final String newLine = "\r\n";

    public String ok(String response) {
        Date date = new Date();

        return
            "HTTP/1.0 200 OK" + newLine +
                "Content-Type: text/html" + newLine +
                "Date: " + date + newLine +
                "Content-length: " + response.length() + newLine + newLine +
                response;
    }

    public String badRequest() {
        return "HTTP/1.0 400 Bad Request" + newLine + newLine;
    }

    public String notFound() {
        return "HTTP/1.0 404 Not Found" + newLine + newLine;
    }

}
