package Controllers;

import Utils.Encapsulator;
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

import Utils.Navigation;
import Utils.ServerConnection;
import Utils.SharedData;
import org.json.JSONObject;

public class ModePageController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Navigation nav = new Navigation();
    private ServerConnection connection = ServerConnection.getInstance();

    public void goToProfile(ActionEvent event) throws IOException {
        nav.goToPage("ProfilePageFXML", event);
        
    }
    
    public void signout(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulateSignOut("SIGN_OUT",SharedData.getInstance().getPlayerID());
        boolean result = connection.checkServerAvailibily(SharedData.getInstance().getServerIp());
        
        if(result){
            
            String responseJSON = connection.sendRequest(message);
            JSONObject jsonReceived = new JSONObject(responseJSON);
            String response = jsonReceived.getString("response");
            
            if(response.equals("Success")){
                SharedData.getInstance().setPlayerID(0);
                System.out.println(SharedData.getInstance().getPlayerID());
                nav.goToPage("HomePage", event);
                connection.closeConnection();
            }
            
        }else{
            nav.ShowAlerts("ErrorAlert", event);
        }
    }
    

    public void goToLocalMode(ActionEvent event) throws IOException {
       nav.goToPage("GameBoard", event);
       
    }
    
     public void goToDifficultyMode(ActionEvent event) throws IOException {
        nav.goToPage("DifficultyPage", event);
    }
    public void getPlayersList(ActionEvent event) throws IOException {
        nav.goToPage("AvailablePlayers", event);
        
    }
    

}
