import netscape.javascript.JSObject;
import org.json.JSONException;
import org.json.JSONObject;

import javax.xml.transform.Result;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.*;
import java.time.LocalTime;


public class Authentication {

    public static String AUTH=null;
    public final static HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    static String clientId="1000.MBG1WQ3UP2M2NR9S7CSWS913RM6P1D";
    static String clientSecret="2be01f6c01d88673f8d914a879fa7f1959317f9469";
    static String refreshToken="1000.c7b5319f0040ba6d90e96623cc69e6b8.3fb6fa80d19dae0261f20c5108f271eb";

    public static void authenticate(String email) throws IOException, InterruptedException, JSONException, ClassNotFoundException, SQLException {

        int time = 0, limit = 30;
        String mailId = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/sys", "test", "test123#T");
        Statement stmt = con.createStatement();

        ResultSet rs = stmt.executeQuery("select * from token");
        while (rs.next()) {
            if (email.equals(rs.getString(2))) {
                mailId = email;
                time = rs.getInt(4);
                System.out.println("While");
                break;
            }
        }
        if (mailId.equals("")) {
            String sql = "INSERT INTO token(mailId,authToken,time) VALUES ('" + email + "','" + generateAUTH() + "'," + LocalTime.now().toSecondOfDay() + ")";
            stmt.executeUpdate(sql);
            System.out.println("Insert");

        }
        else {
            if (Math.abs(LocalTime.now().toSecondOfDay() - time) >= limit) {
                String sql="UPDATE token set authToken='"+generateAUTH()+"',time="+LocalTime.now().toSecondOfDay()+" where mailId='"+mailId+"'";
                stmt.executeUpdate(sql);
                System.out.println("Update");
            }
        }
    }
        public static String generateAUTH () throws JSONException, IOException, InterruptedException {
            HttpRequest request = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(""))
                    .uri(URI.create("https://accounts.zoho.com/oauth/v2/token?refresh_token=" + refreshToken + "&client_id=" + clientId + "&client_secret=" + clientSecret + "&grant_type=refresh_token"))
                    .build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            JSONObject obj = new JSONObject(response.body());
            AUTH = obj.getString("access_token");

            return AUTH;
        }

}
