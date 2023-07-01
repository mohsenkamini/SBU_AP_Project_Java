          
import com.google.gson.*; 

public class APIResponse {

    int statusCode;
    String message;
    JsonElement payload;
    APIResponse(){
        statusCode=500;
        message="500";
    }
}
