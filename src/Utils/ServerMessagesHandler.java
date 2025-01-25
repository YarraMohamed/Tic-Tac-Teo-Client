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

public class ServerMessagesHandler {
    
    private Navigation nav = new Navigation();
    private Stage stage;
    
    public void respondToGameRequest(JSONObject jsonReceived) {
        try {
            int requestingPlayerUsername = jsonReceived.getInt("requestingPlayer_ID");
            System.out.println("Parsed requestingPlayerUsername: " + requestingPlayerUsername); // log message
            Platform.runLater( () -> {
                System.out.println("Showing game request notification for: " + requestingPlayerUsername);
                nav.showGameRequestNotification(requestingPlayerUsername+"");
            });
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error handling game request.");
        }
          
    }
    
    public void onServerColsed() {
        
        stage = new Stage();
        stage.setTitle("Run it Back");
        stage.initStyle(StageStyle.UNDECORATED);


        Text againText = new Text("Server is Closed");
        againText.setFont(Font.font("Chewy", FontWeight.BOLD, 50));
        againText.setFill(Color.WHITE);

        Button yesButton = new Button("OK");
        
        yesButton.setOnAction(e -> {
            Platform.runLater(() -> {
            try {
                nav.goToPage("HomePage");
            } catch (IOException ex) {
                ex.printStackTrace();
                System.out.println("Error navigating to Home Page.");
            }
        });
        });
        
        yesButton.setFont(Font.font("Chewy", FontWeight.BOLD, 30));
        yesButton.setStyle("-fx-background-color: linear-gradient(to bottom,#1F60C1,#8D9CB3); -fx-background-radius: 30; -fx-text-fill:#ffffff;");

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(againText);

        HBox buttonBox = new HBox(30);
        buttonBox.getChildren().addAll(yesButton);
        buttonBox.setAlignment(Pos.CENTER); 

        borderPane.setBottom(buttonBox);
        
        borderPane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 0% 100%, #86AEE9, #09316D);");

        Scene scene = new Scene(borderPane, 400, 225);
        
        stage.setScene(scene);
        stage.show();
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

    public void inGameMove(JSONObject jsonReceived){
        String move=jsonReceived.getString("btn");
        System.out.println(move);
        GameBoardController.updateBoard(move);
        
    }
       
    public void printResponse(String response) {
        System.out.println("Response to client request: " + response);
    }
    
}
