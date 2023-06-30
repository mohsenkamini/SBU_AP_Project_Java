import java.io.*;
import java.net.*;
import org.json.*;  

public class BackendServer {
    public static void main (String[] args){
        try {
            ServerSocket ss = new ServerSocket(8000);
            Socket s = ss.accept();     // establish connection
            StringBuilder sb = new StringBuilder();
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream())); 
            String line=null;
            while ( (line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
            }
            String content = sb.toString();
            String modified = content.substring(2, content.length());
            System.out.println(modified.charAt(modified.length()-2));
            
            JSONObject json = new JSONObject(modified);  
            String type = json.getString("type");
            System.out.println(type);
            ss.close();
        } catch (Exception a) {System.out.println(a);}
    }
}