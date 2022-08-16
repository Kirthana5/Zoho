import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpRequest;
import java.util.Scanner;

public class RequestHandler {
    Scanner sc=new Scanner(System.in);
    ResponseHandler responseHandler=new ResponseHandler();
    public void handleRequest() throws JSONException, IOException, InterruptedException {
        while (true)
        {
            System.out.println("Enter your choice\n1.GET\n2.POST\n3.UPDATE\n4.DELETE\n5.Exit");
            String choice=sc.next();

            switch(choice)
            {
                case "1":
                    GET();
                    break;
                case "2":
                    POST();
                    break;
                case "3":
                    PUT();
                    break;
                case "4":
                    DELETE();
                    break;
                case "5":
                    System.exit(0);
                    break;
                default:
                    System.out.println("Enter valid choice..!!");
                    break;
            }
        }
    }
    public void GET() throws IOException, InterruptedException, JSONException {
        System.out.println("GETTING CONTACTS");

        System.out.println("Do you want a Particular contact details. If so, enter Contact id otherwise press N/n:");
        String id=sc.next();
        String URi="";
        if(id.equals("n") || id.equals("N"))
        {
            URi="https://books.zoho.com/api/v3/contacts?organization_id=786665588";
        }
        else {
            URi="https://books.zoho.com/api/v3/contacts/"+id+"?organization_id=786665588";
        }
        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(URi))
                .setHeader("Authorization","Zoho-oauthtoken "+Authentication.AUTH)
                .build();

        responseHandler.handleResponse(request);
    }
    public void POST() throws IOException, InterruptedException, JSONException {
        System.out.println("CREATING CONTACT");

        System.out.println("Enter name:");
        String name=sc.next();
        System.out.println("Enter phone no.");
        String phone=sc.next();
        System.out.println("Enter email:");
        String email=sc.next();
        Contacts contact =new Contacts();
        contact.setContact_name(name);
        contact.setPhone(phone);
        contact.setEmail(email);
        JSONObject object=new JSONObject(contact);
        HttpRequest request = HttpRequest.newBuilder()
                .POST(HttpRequest.BodyPublishers.ofString(object.toString()))
                .uri(URI.create("https://books.zoho.com/api/v3/contacts?organization_id=786665588"))
                .setHeader("Authorization","Zoho-oauthtoken "+Authentication.AUTH)
                .build();

        responseHandler.handleResponse(request);
    }
    public void PUT() throws IOException, InterruptedException, JSONException {
        System.out.println("UPDATE CONTACT");
        System.out.println("Enter the contact id which you want to update:");
        String id=sc.next();
        System.out.println("Enter name:");
        String name=sc.next();
        System.out.println("Enter phone no.");
        String phone=sc.next();
        System.out.println("Enter email:");
        String email=sc.next();
        Contacts contact =new Contacts();
        contact.setContact_name(name);
        contact.setPhone(phone);
        contact.setEmail(email);
        JSONObject object=new JSONObject(contact);
        HttpRequest request = HttpRequest.newBuilder()
                .PUT(HttpRequest.BodyPublishers.ofString(object.toString()))
                .uri(URI.create("https://books.zoho.com/api/v3/contacts/"+id+"?organization_id=786665588"))
                .setHeader("Authorization","Zoho-oauthtoken "+Authentication.AUTH)
                .build();

        responseHandler.handleResponse(request);

    }
    public void DELETE() throws IOException, InterruptedException, JSONException {
        System.out.println("DELETE CONTACT");
        System.out.println("Enter the contact id which you want to delete:");
        String id=sc.next();
        HttpRequest request = HttpRequest.newBuilder()
                .DELETE()
                .uri(URI.create("https://books.zoho.com/api/v3/contacts/"+id+"?organization_id=786665588"))
                .setHeader("Authorization","Zoho-oauthtoken "+Authentication.AUTH)
                .build();

        responseHandler.handleResponse(request);

    }
}
