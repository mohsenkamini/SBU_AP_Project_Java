import java.io.*;
import java.net.*;
import java.nio.*;
import java.util.*;
import com.google.gson.*;


public class ClientThread extends Thread{
    Socket socket;
    DataOutputStream out = null;
    DataOutputStream in = null;

    Gson gson = new GsonBuilder().setLenient().create();

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run () {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            DataOutputStream dout = new DataOutputStream(socket.getOutputStream());
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            String content = sb.toString();
            String modified = content.substring(2, content.length());
            System.out.println(modified.charAt(modified.length() - 2));

            JsonElement jsonElement = JsonParser.parseString(modified);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            System.out.println( jsonObject.get("method"));
            //getting the request and handling it

            APIRequest req = gson.fromJson(jsonObject, APIRequest.class); 
            System.out.println(req.username);
            APIResponse response = BackendServer.handleRequest(req);
            String str = new Gson().toJson(response,APIResponse.class);
            System.out.println(str);

            dout.writeUTF(str);
            dout.flush();
            dout.close();
            System.out.println("ehem");
            socket.close();
            System.out.println("xxx");
          
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        } //catch (InterruptedException i) {System.out.println("interrupt exception");}
    }
}
