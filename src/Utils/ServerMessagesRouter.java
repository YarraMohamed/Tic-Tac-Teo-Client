package Utils;

import java.io.IOException;
import javafx.application.Platform;
import org.json.JSONObject;


public class ServerMessagesRouter {
    
     public static void routeServerMessage(String message) {
        Navigation nav = new Navigation();

        try {
            
            System.out.println("Raw message received: " + message); // Log the raw message
        if (message == null || message.trim().isEmpty()) {
            System.err.println("Error: Received null or empty message from the server.");
            return;
        }
            System.out.println("message in routeServer " + message);
            JSONObject serverMessageJson = new JSONObject(message);
            ServerMessagesHandler serverMessagesHandler = new ServerMessagesHandler();
            
            if (serverMessageJson.has("requestType")) {
            String requestType = serverMessageJson.getString("requestType");
            
            switch (requestType) {
                case "GAME_REQUEST":
                    serverMessagesHandler.respondToGameRequest(serverMessageJson);
                    break;
                case "MOVE":
                    System.out.println("galk mooove");
                    serverMessagesHandler.inGameMove(serverMessageJson);
                    break;
                   case "rejectedNotificationAccepted":
    int currentPlayerID = SharedData.getInstance().getPlayerID();
    // Notify the sender even if recipientID is missing (fallback behavior)
    serverMessagesHandler.rejection();
    break;
    
                    
                default:
                    System.out.println("Unhandled requestType: " + requestType);
                    break;
            }
        } else if (serverMessageJson.has("response")) {
            String response = serverMessageJson.getString("response");
            int playerid = serverMessageJson.optInt("Player_ID");
            String userName = serverMessageJson.optString("Name");
            int score = serverMessageJson.optInt("Score");
            
            switch(response){
                case "MOVED":
                    System.out.println("Bravooo 3leek");
                    break;
                case "LOGGED_IN" :
                    serverMessagesHandler.Auth(playerid);
                    break;
                case "Profile":
                    serverMessagesHandler.viewProfile(userName, score);
                    break;
                case "Failed":
                    serverMessagesHandler.failedMessage();
                    break;    
                case "List_Of_Players" :
                    serverMessagesHandler.avaliablePlayers(serverMessageJson);
                    break;
                case "GAME_REQUEST_SUCCESS" :
                    serverMessagesHandler.waitResponse();
                    break;
                case "GAME_REQUEST_FAILED" :
                    serverMessagesHandler.Errormessage();
                    break;
                case "GAME_REQUEST_ACCEPTED":
                    int player1ID = serverMessageJson.getInt("player1ID"); // Sender
                    int player2ID = serverMessageJson.getInt("player2ID"); // Receiver
                    int currentPlayerID = SharedData.getInstance().getPlayerID();

                    // Check if the current player is part of the game
                    if (currentPlayerID == player1ID || currentPlayerID == player2ID) {
                        Platform.runLater(() -> {
            try {
                nav.goToBoardOnlineMode(player1ID, player2ID, null);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error navigating to game board.");
            }
        });
    }
    break;
            }
        } else {
            System.out.println("Invalid message format: " + message);
        }
    
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error processing server message: " + message);
        }
        
    
    }
    
}
