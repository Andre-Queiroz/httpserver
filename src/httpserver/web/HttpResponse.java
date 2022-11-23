package httpserver.web;

import java.io.PrintStream;
import java.util.Date;

public class HttpResponse {

    private static final String newLine = "\r\n";

    private final PrintStream printStream;

    public HttpResponse(PrintStream printStream) {
        this.printStream = printStream;
    }

    public void ok(String response) {
        Date date = new Date();

        printStream.print(
            "HTTP/1.0 200 OK" + newLine +
                "Content-Type: text/html" + newLine +
                "Date: " + date + newLine +
                "Content-length: " + response.length() + newLine + newLine +
                response
        );
    }

    public void badRequest() {
        printStream.print("HTTP/1.0 400 Bad Request" + newLine + newLine);
    }

    public void notFound() {
        printStream.print("HTTP/1.0 404 Not Found" + newLine + newLine);
    }

}
