import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalTime;


public class Authentication {

    public static String AUTH=null;
    static int time=5;
    static int  c=5;
    public final static HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();
    public static void authenticate() throws IOException, InterruptedException, JSONException {
        String clientId="1000.MBG1WQ3UP2M2NR9S7CSWS913RM6P1D";
        String clientSecret="2be01f6c01d88673f8d914a879fa7f1959317f9469";
        String refreshToken="1000.c7b5319f0040ba6d90e96623cc69e6b8.3fb6fa80d19dae0261f20c5108f271eb";

        int seconds = LocalTime.now().toSecondOfDay();
        if(time<seconds)
        {
            time=c+seconds;
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .uri(URI.create("https://accounts.zoho.com/oauth/v2/token?refresh_token="+refreshToken+"&client_id="+clientId+"&client_secret="+clientSecret+"&grant_type=refresh_token"))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject obj=new JSONObject(response.body());
            AUTH= obj.getString("access_token");
        }

        System.out.println(AUTH);
    }
}
