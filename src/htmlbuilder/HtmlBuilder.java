package htmlbuilder;

public class HtmlBuilder {

    private final String baseHtml = """
        <!DOCTYPE html>
        <html lang="en">
        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Calculator</title>
        </head>
        <body>
            <div>
                <h1>
                    %s
                </h1>
            </div>
        </body>
        </html>
        """;

    public String generateSuccessHtml(float calcResult) {
        String append = "" + calcResult + "";
        return String.format(baseHtml, append);
    }

    public String generateBadRequestHtml() {
        return String.format(baseHtml, "400 Bad Request");
    }

    public String generateNotFoundHtml() {
        return String.format(baseHtml, "404 Not Found");
    }
}
