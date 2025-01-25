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
    

    
    public static String encapsulateID(String requestType,int PlayerID) {
        JSONObject json = new JSONObject();
        json.put("requestType", requestType); 
        json.put("Player_ID", PlayerID);
        return json.toString();
    }
        
 
    public static String encapsulateGameRequest(int requestingPlayerId, int requestedPlayerId) {
        JSONObject gameRequest = new JSONObject();
        gameRequest.put("requestType", "GAME_REQUEST");
        gameRequest.put("requestingPlayer_ID", requestingPlayerId);
        gameRequest.put("requestedPlayer_ID", requestedPlayerId);
        System.out.println("Sending GAME_REQUEST: " + gameRequest); // log message
        return gameRequest.toString();
    }
    
}

