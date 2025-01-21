package Utils;

import org.json.JSONObject;

public class Encapsulator {
    public static String encapsulateSignIn(String username, String password) {
        JSONObject json = new JSONObject();
        json.put("requestType", "SIGN_IN");
        json.put("username", username);
        json.put("password", password);
        return json.toString();
    }

    public static String encapsulateSignUp(String username, String email, String password) {
        JSONObject json = new JSONObject();
        json.put("requestType", "SIGN_UP");
        json.put("username", username);
        json.put("password", password);
        json.put("email", email);
        return json.toString();
    }
    
    public static String encapsulateID(String requestType,int PlayerID) {
        JSONObject json = new JSONObject();
        json.put("requestType", requestType); 
        json.put("Player_ID", PlayerID);
        return json.toString();
    }
}

