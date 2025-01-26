package Utils;


import Controllers.GameBoardController;
import Controllers.GameRequestNotificationController;
import Utils.Navigation;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONArray;
import org.json.JSONObject;
import sun.applet.Main;

public class ServerMessagesHandler {
    
    private Navigation nav = new Navigation();
//    private Stage stage;
    
    
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
    
    public void onServerColsed() {        
            try {
                nav.goToPage("HomePage");
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error navigating to Home Page.");
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
    public void onAccept(JSONObject json) throws IOException{
        int p2=json.getInt("p2ID");
//        nav.goToBoardOnlineMode2(1,p2);
    }

    public void inGameMove(JSONObject jsonReceived){
        String move=jsonReceived.getString("btn");
        System.out.println(move);
        GameBoardController.updateBoard(move);
        
    }
       
    public void printResponse(String response) {
        System.out.println("Response to client request: " + response);
    }
    
}
