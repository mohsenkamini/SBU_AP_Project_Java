import java.io.*;
import java.net.*;

public class ExampleClient {
    public static void main (String[] args){
        try {
            Socket s = new Socket ("localhost",8000);     // establish connection
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            DataInputStream din = new DataInputStream(s.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String str = "",str2="";
            while (!str2.equals("stop")) {
                str2=br.readLine();
                if (str2.equals("stop"))
                    break;
                dout.writeUTF(str2);
                dout.flush();
            }
            dout.close();
            s.close();
        } catch (Exception a) {System.out.println(a);}
    }
}