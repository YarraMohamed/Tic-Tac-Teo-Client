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
    
    public static String encapsulateSignOut(String requestType,int PlayerID) {
        JSONObject json = new JSONObject();
        json.put("requestType", requestType);
        json.put("Player_ID", PlayerID);
        return json.toString();
    }
    public static String encapsulateGameMove(int PlayerID, int Player2ID, String btnId) {
        JSONObject json = new JSONObject();
        json.put("requestType", "MOVE");
        json.put("Player_ID", PlayerID);
        json.put("Player2_ID", Player2ID);
        json.put("btn",btnId);
        return json.toString();
    }
}

