import java.io.*;
import java.net.*;
import com.google.gson.*; 

public class ExampleClient {
    public static void main (String[] args){
        try {
            Socket s = new Socket ("localhost",8000);     // establish connection
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            StringBuilder sb = new StringBuilder();
            DataInputStream din = new DataInputStream(s.getInputStream());
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //JSON parser object to parse read file
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try {
	       // APIRequest req = gson.fromJson(new FileReader("RequestFormat.json"), APIRequest.class); 
	       APIRequest req = gson.fromJson(new FileReader("signup.json"), APIRequest.class); 
	       //APIRequest req = gson.fromJson(new FileReader("login.json"), APIRequest.class); 
               System.out.println(req.method);
               String str = new Gson().toJson(req,APIRequest.class),str2="";
		System.out.println("Sending to server:\n" + str);
               dout.writeUTF(str+"\n");
            } catch (IOException io) {System.out.println("IO exception:" + io);};



            Thread.sleep(500);
            dout.flush();
            
            String line;
            
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
                break;
            }

            //Thread.sleep(2000);
            String content = sb.toString();
            System.out.println(content);
            String modified = content.substring(2, content.length());
            //System.out.println(modified.charAt(modified.length() - 2));
            //System.out.println("ok");

            JsonElement jsonElement = JsonParser.parseString(modified);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            //System.out.println( jsonObject.get("statusCode"));
            dout.close();
            s.close();
        } catch (Exception a) {System.out.println(a);}
        
    }
}
