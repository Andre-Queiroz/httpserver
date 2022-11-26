import httpserver.web.WebServer;

public class Main {

    public static void main(String[] args) {
        new WebServer(8080).execute();
    }
}
