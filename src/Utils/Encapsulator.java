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
        
        //GameRequestNotificationController gameRequestNotification = new GameRequestNotificationController();
        
        gameRequest.put("requestType", "GAME_REQUEST");
        gameRequest.put("requestingPlayer_ID", requestingPlayerId);
        gameRequest.put("requestedPlayer_ID", requestedPlayerId);
        
        //GameRequestNotificationController.rejectingPlayerId = gameRequest.getInt("requestedPlayer_ID");
        /*int rejectingPlayeId = gameRequest.getInt("requestedPlayer_ID");
        int rejectedPlayeId = gameRequest.getInt("requestingPlayer_ID");
        gameRequestNotification.setRejectingPlayerId(rejectingPlayeId); 
        gameRequestNotification.setRejectedPlayerId(rejectedPlayeId);*/
        
        System.out.println("Sending GAME_REQUEST: " + gameRequest); // log message
        return gameRequest.toString();
    }
    
    
    /*public static String encapsulateGameRejectRequest(int rejectingPlayerId, int rejectedPlayerId) {
        System.out.println("Rejecting Player ID in JSON: " + rejectingPlayerId); // Ensure this matches the current player's ID
        System.out.println("Rejected Player ID in JSON: " + rejectedPlayerId); // Ensure this matches the requesting player's ID
        
        JSONObject gameRejectedRequest = new JSONObject(); 
        gameRejectedRequest.put("requestType", "GAME_REJECTED");
        gameRejectedRequest.put("rejectingPlayer_ID", rejectingPlayerId); // was the requestd before
        gameRejectedRequest.put("rejectedPlayer_ID", rejectedPlayerId); // was the requesting
        
        System.out.println("Sending GAME_REJECTED: " + gameRejectedRequest); // log message
        return gameRejectedRequest.toString();
    }*/
    
    
    public static String encapsulateGameRejectRequest(int rejectedPlayerId) {
        System.out.println("Rejected Player ID in JSON: " + rejectedPlayerId); // Ensure this matches the requesting player's ID
        
        JSONObject gameRejectedRequest = new JSONObject(); 
        gameRejectedRequest.put("requestType", "GAME_REJECTED");
        gameRejectedRequest.put("rejectedPlayer_ID", rejectedPlayerId); // was the requesting
        
        System.out.println("Sending GAME_REJECTED: " + gameRejectedRequest); // log message
        return gameRejectedRequest.toString();
    }
    
}

