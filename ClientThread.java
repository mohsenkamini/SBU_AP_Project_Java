import java.io.*;
import java.net.*;
import java.nio.*;
import java.util.*;
import com.google.gson.*;


public class ClientThread extends Thread{
    Socket socket;
    DataOutputStream out = null;
    DataOutputStream in = null;

    public ClientThread(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run () {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
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
            APIRequest req = new APIRequest();
            //APIResponse response = handleRequest(req);
            socket.close();
          
        } catch (IOException e) {
            System.out.println("Error occurred: " + e.getMessage());
        }
    }

}
