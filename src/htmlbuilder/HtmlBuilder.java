package htmlbuilder;

public class HtmlBuilder {
    private final String htmlHeader = "<!DOCTYPE html>\n" +
            "<html lang=\"en\">\n" +
            "<head>\n" +
            "    <meta charset=\"UTF-8\">\n" +
            "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n" +
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
            "    <title>Document</title>\n" +
            "</head>";
    public String generateHtml (float calcResult){

        String html = htmlHeader + "<body>" + Float.toString (calcResult) + "</body> </html>";
        return html;
    }

}
