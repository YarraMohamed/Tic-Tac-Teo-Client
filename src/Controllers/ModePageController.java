package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModePageController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goToProfile(ActionEvent event) throws IOException {
        
        
        String message = Encapsulator.encapsulateSignOut("USER_NAME",SharedData.getInstance().getPlayerID());
        boolean result = connection.checkServerAvailibily(SharedData.getInstance().getServerIp());
        
        if(result){
            
            String responseJSON = connection.sendRequest(message);
            JSONObject jsonReceived = new JSONObject(responseJSON);
            String response = jsonReceived.getString("response");
            
            if(response.equals("Success")){
                
                String userName = jsonReceived.getString("Name");
                int score = jsonReceived.getInt("Score"); 
                SharedData.getInstance().setUserName(userName);
                SharedData.getInstance().setScore(score);
                System.out.println(SharedData.getInstance().getScore());
                System.out.println(SharedData.getInstance().getUserName());
                nav.goToPage("ProfilePageFXML", event);
            }            
        }else{
            nav.ShowAlerts("ErrorAlert", event);
        }
        
    }
    
    public void signout(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulate("signout");
        root = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void goToLocalMode(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulate("signout");
        root = FXMLLoader.load(getClass().getResource("/FXML/GameBoard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     public void goToDifficultyMode(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulate("signout");
        root = FXMLLoader.load(getClass().getResource("/FXML/DifficultyPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void getPlayersList(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulate("signout");
        root = FXMLLoader.load(getClass().getResource("/FXML/AvailablePlayers.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
