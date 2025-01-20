/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utils.Encapsulator;
import Controllers.SignInController;
import Utils.ServerConnection;
import Utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.json.JSONArray;
import org.json.JSONObject;


public class AvailablePlayersController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private VBox  userContainer;
    
    private ServerConnection connection = ServerConnection.getInstance();
    
    int currentPlayerID = SharedData.getInstance().getPlayerID();

    
    @FXML
    public void onNavBack(Event event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ModePage.fxml"));
        //Parent root = FXMLLoader.load(getClass().getResource("/FXML/AvailablePlayers.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AvailablePlayersController.class.getName()).log(Level.SEVERE, "Failed to load SignIn.fxml", ex);
    }
}
//    public List<String> getOnlinePlayers(int playerID) {
//    List<String> playersList = new ArrayList<>();
//
//    try {
//        // Send request to get only online players
//        String requestMessage = Encapsulator.encapsulateGetOnlinePlayers(playerID);
//        String responseJSON = connection.sendRequest(requestMessage);
//
//        System.out.println("Raw Server Response: [" + responseJSON + "]");
//
//        if (responseJSON == null || responseJSON.trim().isEmpty()) {
//            System.out.println("Error: Server returned an empty response.");
//            return playersList;
//        }
//
//        if (!responseJSON.trim().startsWith("{")) {
//            System.out.println("Error: Response is not a valid JSON object.");
//            return playersList;
//        }
//
//        JSONObject jsonReceived = new JSONObject(responseJSON);
//
//        if (jsonReceived.has("onlinePlayers")) {
//            JSONArray playersArray = jsonReceived.getJSONArray("onlinePlayers");
//
//            for (int i = 0; i < playersArray.length(); i++) {
//                String playerName = playersArray.getJSONObject(i).getString("NAME");
//                playersList.add(playerName);
//            }
//        } else {
//            System.out.println("Error: 'onlinePlayers' key not found in JSON.");
//        }
//
//    } catch (Exception e) {
//        Logger.getLogger(AvailablePlayersController.class.getName()).log(Level.SEVERE, "Error fetching available players", e);
//    }
//
//    return playersList;
//}
    
    
    public List<String> getAvailablePlayers(int playerID) {
    List<String> playersList = new ArrayList<>();

    try {
        // Create request message to get players list
        String requestMessage = Encapsulator.encapsulateID("GET_AVAILABLE_PLAYERS", playerID);
        String responseJSON = connection.sendRequest(requestMessage);

        // Debugging
        System.out.println("Raw Server Response: [" + responseJSON + "]");

        if (responseJSON == null || responseJSON.trim().isEmpty()) {
            System.out.println("Error: Server returned an empty response.");
            return playersList;
        }

        // Validate JSON format
        if (!responseJSON.trim().startsWith("{")) {
            System.out.println("Error: Response is not a valid JSON object.");
            return playersList;
        }

        // Parse JSON response
        JSONObject jsonReceived = new JSONObject(responseJSON);

        if (jsonReceived.has("players")) {
            JSONArray playersArray = jsonReceived.getJSONArray("players");

            for (int i = 0; i < playersArray.length(); i++) {
                JSONObject playerObject = playersArray.getJSONObject(i);
                String playerName = playerObject.getString("NAME");

                // Add player to the list
                playersList.add(playerName);
            }
        } else {
            System.out.println("Error: 'players' key not found in JSON.");
        }

    } catch (Exception e) {
        Logger.getLogger(AvailablePlayersController.class.getName()).log(Level.SEVERE, "Error fetching available players", e);
    }

    return playersList;
}



    @Override
    public void initialize(URL url, ResourceBundle rb) 
    {
        
            VBox.setVgrow(userContainer, Priority.ALWAYS);
            label.setText("Let’s Play");
//        addCompoent("ziad");
//        addCompoent("fawzy");
//        addCompoent("yara");
//        addCompoent("mariem");
//        addCompoent("amira");
        int playerID = 1; // Replace with the actual playerID, which you should have when the player signs in
        List<String> availablePlayers = getAvailablePlayers(currentPlayerID); // Fetch the list of online players
        // Iterate through the list of available players and add them to the UI
        for (String player : availablePlayers) {
            addCompoent(player);
}
    }

      
  
    public void addCompoent(String user){
        HBox box = new HBox();
        
        box.setMaxWidth(Double.MAX_VALUE); // Fills the available width
        box.setPrefHeight(40);
        box.setPadding(new Insets(10));
        box.setSpacing(10);

        BackgroundFill backgroundFill = new BackgroundFill(
            Color.web("#A6A6A6", 0.7), 
            new CornerRadii(15), 
            Insets.EMPTY
        );
        
        box.setBackground(new Background(backgroundFill));
        Label playerLabel = new Label(user);
        playerLabel.setStyle("-fx-text-fill: black; -fx-font-size: 14px;");
        box.getChildren().add(playerLabel);
        userContainer.getChildren().add(box);
    }    
}


//    public List<String> getAvailablePlayers(int playerID) throws IOException {
//    List<String> playersList = new ArrayList<>();
//    // Create request message, make sure to pass the playerID and correct request type
//    String requestMessage = Encapsulator.encapsulateID("getPlayers", playerID);  
//    String responseJSON = connection.sendRequest(requestMessage);  // Send the request and get response
//    
//    // Parse JSON and extract the list of player names
//    JSONObject jsonReceived = new JSONObject(responseJSON);
//    JSONArray playersArray = jsonReceived.getJSONArray("players");  // Extract the array of players
//
//    // Check if playersArray is not null and add player names to the list
//    if (playersArray != null) {
//        for (int i = 0; i < playersArray.length(); i++) {
//            String playerName = playersArray.getJSONObject(i).getString("NAME");  // Extract player name
//            playersList.add(playerName);
//        }
//    }
//    return playersList;
//}
    