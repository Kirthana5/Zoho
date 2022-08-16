import org.json.JSONException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws JSONException, IOException, InterruptedException {

        Scanner sc=new Scanner(System.in);

        Authentication.authenticate();
        RequestHandler requestHandler =new RequestHandler();
        requestHandler.handleRequest();

    }
}
