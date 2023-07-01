import java.io.*;
import java.net.*;
import com.google.gson.*; 

public class ExampleClient {
    public static void main (String[] args){
        try {
            Socket s = new Socket ("localhost",8000);     // establish connection
            BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            DataInputStream din = new DataInputStream(s.getInputStream());
            StringBuilder sb = new StringBuilder();
            //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //JSON parser object to parse read file
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try {
               APIRequest req = gson.fromJson(new FileReader("RequestFormat.json"), APIRequest.class); 
               System.out.println(req.method);
               String str = new Gson().toJson(req,APIRequest.class),str2="";
                dout.writeUTF(str);
            } catch (IOException io) {System.out.println("IO exception:" + io);};

            dout.flush();


            Thread.sleep(2000);
            dout.close();

            String line;
            //Thread.sleep(2000);
            while ((line = br.readLine()) != null) {
                sb.append(line).append(System.lineSeparator());
                System.out.println("some");
            }

            //Thread.sleep(2000);
            String content = sb.toString();
            String modified = content.substring(2, content.length());
            System.out.println(modified.charAt(modified.length() - 2));
            System.out.println("ok");

            JsonElement jsonElement = JsonParser.parseString(modified);
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            System.out.println( jsonObject.get("statuscode"));
            /*while (!str2.equals("stop")) {
                str2=br.readLine();
                if (str2.equals("stop"))
                    break;
                dout.writeUTF(str2);
                dout.flush();
            }
            */
            dout.close();
            s.close();
        } catch (Exception a) {System.out.println(a);}
    }
}