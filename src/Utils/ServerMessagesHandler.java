package Utils;

import Controllers.GameRequestNotificationController;
import Utils.Navigation;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

public class ServerMessagesHandler {
    
    private Navigation nav = new Navigation();
    
    public void respondToGameRequest(JSONObject jsonReceived) {
    String requestType = jsonReceived.getString("requestType");
    int rejectedPlayerId = jsonReceived.getInt("requestingPlayer_ID");
    GameRequestNotificationController.rejectedPlayerId = rejectedPlayerId;
    if ("GAME_REQUEST".equals(requestType)) {
        String requestingPlayerUsername = jsonReceived.getString("requestingPlayerUsername");
        Platform.runLater(() -> {
            nav.showGameRequestNotification(requestingPlayerUsername);
        });
    } else {
        System.out.println("Unhandled requestType: " + requestType);
    }
}
    
    /*public void respondToGameRequest(JSONObject jsonReceived) {
        
        try {
            int rejectedPlayerId = jsonReceived.getInt("requestingPlayer_ID");
            /*GameRequestNotificationController grc = new GameRequestNotificationController();
            grc.setRejectedPlayerId(rejectedPlayerId);*/
            /*String requestingPlayerUsername = jsonReceived.getString("requestingPlayerUsername");
            GameRequestNotificationController.rejectedPlayerId = rejectedPlayerId;
            System.out.println("Parsed requestingPlayerUsername: " + requestingPlayerUsername); // log message
            Platform.runLater( () -> {
                nav.showGameRequestNotification(requestingPlayerUsername);
        });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error handling game request.");
        }    
          
    }*/
    
    
    /*public void respondToGameReject(JSONObject jsonReceived) {
        
        try {
            String rejectingPlayerUsername = jsonReceived.getString("rejectingPlayerUsername");
            System.out.println("Parsed rejectingPlayerUsername: " + rejectingPlayerUsername); // log message
            Platform.runLater( () -> {
                nav.showGameRejectNotification(rejectingPlayerUsername);
        });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error handling game rejection.");
        }    
          
    }*/
    
    
    public void respondToGameReject(JSONObject jsonReceived) {
        
        try {
            //String rejectingPlayerUsername = jsonReceived.getString("rejectingPlayerUsername");
            //System.out.println("Parsed rejectingPlayerUsername: " + rejectingPlayerUsername); // log message
            Platform.runLater( () -> {
                nav.showGameRejectNotification();
        });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error handling game rejection.");
        }    
          
    }
    
    
    public void Auth(int player_id) throws IOException{
        SharedData.getInstance().setPlayerID(player_id);
        Platform.runLater(() -> {
            try {
                nav.goToPage("ModePage");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error navigating to ModePage.");
            }
        });
    }
    

    public void failedMessage(){
         Platform.runLater(() -> {
            try {
                nav.ShowAlerts("InvalidMessage");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error navigating to ModePage.");
            }
        });
     }
    
    
    public void viewProfile( String userName,int score){
         SharedData.getInstance().setUserName(userName);
         SharedData.getInstance().setScore(score);
         Platform.runLater(() -> {
            try {
                Navigation.goToPage("ProfilePageFXML");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error navigating to ModePage.");
            }
        });
    }
    
    
    public void avaliablePlayers(JSONObject jsonReceived ){
         
         Map<String, Integer> playersMap = new HashMap<>();
         if (jsonReceived == null || jsonReceived.isEmpty()) {
            System.out.println("Error: Server returned an empty response.");
            SharedData.getInstance().setAvailablePlayers(playersMap);
        }
        
        JSONArray playersArray = jsonReceived.getJSONArray("players");
        for (int i = 0; i < playersArray.length(); i++) {
                JSONObject playerObject = playersArray.getJSONObject(i);
                String playerName = playerObject.getString("NAME");
                Integer playerId = playerObject.getInt("ID");

                playersMap.put(playerName, playerId);
                
            }
        SharedData.getInstance().setAvailablePlayers(playersMap);
        Platform.runLater(() -> {
            try {
                Navigation.goToPage("AvailablePlayers");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error navigating to ModePage.");
            }
        });
     }
         
         
    public void printResponse(String response) {
        System.out.println("Response to client request: " + response);
    }
    
    

}
