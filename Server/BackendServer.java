import java.io.*;
import java.net.*;

public class BackendServer {
    public static void main (String[] args){
        try {
            ServerSocket ss = new ServerSocket(6666);
            Socket s = ss.accept();     // establish connection
            DataInputStream din = new DataInputStream(s.getInputStream());
            DataOutputStream dout = new DataOutputStream(s.getOutputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            String str = "",str2="";
            while (!str.equals("stop")) {
                str = (String)din.readUTF();
                System.out.println("client says: " + str);
                str2=br.readLine();
                dout.writeUTF(str2);
                dout.flush();           // will not be transported to socket/file
            }
            din.close();
            ss.close();
        } catch (Exception a) {System.out.println(a);}
    }
}