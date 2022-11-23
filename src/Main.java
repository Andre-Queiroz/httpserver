import httpserver.web.WebServer;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import calculatorhttp.CalculatorHttp;

public class Main {
    public static void main(String[] args) {
        /*
        CalculatorHttp calc = new CalculatorHttp();
        String input = "1";
        while (input != "0") {
            Scanner sc = new Scanner(System.in);
            input = sc.next();
            System.out.println(calc.executeOperation(input));
        }
        */
        new WebServer(8080).execute();
    }
}
