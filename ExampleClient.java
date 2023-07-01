import java.io.*;
import java.net.*;
import com.google.gson.*; 

public class ExampleClient {
    public static void main (String[] args){
        try {
            Socket s = new Socket ("localhost",8000);     // establish connection
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            DataInputStream din = new DataInputStream(s.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //JSON parser object to parse read file
            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            try {
               APIRequest req = gson.fromJson(new FileReader("RequestFormat.json"), APIRequest.class); 
               System.out.println(req.method);
               String str = new Gson().toJson(req,APIRequest.class),str2="";
                dout.writeUTF(str);
            } catch (IOException io) {System.out.println("IO exception:" + io);};

            dout.flush();
            dout.close();
            /*while (!str2.equals("stop")) {
                str2=br.readLine();
                if (str2.equals("stop"))
                    break;
                dout.writeUTF(str2);
                dout.flush();
            }
            dout.close();
            */
            s.close();
        } catch (Exception a) {System.out.println(a);}
    }
}