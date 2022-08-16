import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ResponseHandler {
    public void handleResponse(HttpRequest request) throws IOException, InterruptedException,JSONException {
        HttpResponse<String> response = Authentication.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        // print status code
        System.out.println(response.statusCode());

        JSONObject obj = new JSONObject(response.body());
        System.out.println(obj.toString(2));
    }
}
