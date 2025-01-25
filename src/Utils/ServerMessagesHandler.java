package Utils;


import Controllers.GameBoardController;
import Controllers.GameRequestNotificationController;
import Utils.Navigation;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;

public class ServerMessagesHandler {
    
    private Navigation nav = new Navigation();
    
    
    public void respondToGameRequest(JSONObject jsonReceived) {
        GameRequestNotificationController n = new GameRequestNotificationController();
        
        try {
            int requestingPlayerID = jsonReceived.getInt("requestingPlayer_ID");
            int requestedPlayerID = jsonReceived.getInt("requestedPlayerID"); 
             String requestingPlayerUsername = jsonReceived.getString("requestingPlayerUsername");
        Platform.runLater( () -> {
                System.out.println("Showing game request notification for: " + requestingPlayerUsername);
                nav.showGameRequestNotification(requestingPlayerUsername+"" ,requestingPlayerID,requestedPlayerID );
        });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error handling game request.");
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
    
      public void Errormessage(){
         Platform.runLater(() -> {
            try {
                nav.ShowAlerts("ErrorAlert");
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Error navigating to ModePage.");
            }
        });
     }
    public void waitResponse(){
         Platform.runLater(() -> {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Please wait");
        alert.setHeaderText(null);
        alert.setContentText("wait for the player response");
        alert.show();
        });
     }
    
    public void rejection(){
         Platform.runLater(() -> {
           Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Rejection");
        alert.setHeaderText(null);
        alert.setContentText("Please choose antoher player");
        alert.show();
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

    public void inGameMove(JSONObject jsonReceived){
        String move=jsonReceived.getString("btn");
        GameBoardController.updateBoard(move);
        
    }
       
    public void printResponse(String response) {
        System.out.println("Response to client request: " + response);
    }
    
}
