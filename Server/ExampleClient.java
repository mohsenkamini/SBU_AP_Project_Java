import java.io.*;
import java.net.*;
import org.json.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.google.gson.*; 
public class ExampleClient {
    public static void main (String[] args){
        try {
            Socket s = new Socket ("localhost",8000);     // establish connection
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            DataInputStream din = new DataInputStream(s.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            //JSON parser object to parse read file
            JSONParser jsonParser = new JSONParser();
            FileReader reader = new FileReader("RequestFormat.json");
            
                //Read JSON file
            JSONObject obj = jsonParser.parse(reader);
            
            String str = obj.toString(),str2="";
            dout.writeUTF(str);
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