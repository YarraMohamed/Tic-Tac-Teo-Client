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
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

public class ModePageController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Navigation nav = new Navigation();
    private ServerConnection connection = ServerConnection.getInstance();

    public void goToProfile(ActionEvent event) throws IOException {
 
        String message = Encapsulator.encapsulateID("USER_NAME",SharedData.getInstance().getPlayerID());
        boolean result = connection.checkServerAvailibily(SharedData.getInstance().getServerIp());
        
        if(result){
            //connection.openConnection();
            connection.sendRequest(message);       
        }else{
            nav.ShowAlerts("ErrorAlert");
        }
        
    }
    
    public void signout(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulateID("SIGN_OUT",SharedData.getInstance().getPlayerID());
        boolean result = connection.checkServerAvailibily(SharedData.getInstance().getServerIp());
        
        if(result){
            SharedData.getInstance().setPlayerID(0);
            connection.sendRequest(message); 
            nav.goToPage("HomePage", event);
            connection.closeConnection();
        }else{
            nav.ShowAlerts("ErrorAlert");
        }
    }
    

    public void goToLocalMode(ActionEvent event) throws IOException {
//       nav.goToPage("GameBoard", event);
try {
             FXMLLoader x = new FXMLLoader(getClass().getResource("/FXML/GameBoard.fxml"));
             root = x.load();
             GameBoardController c = x.getController();
             c.setMode("pvp_local");
             stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
             scene = new Scene(root);
             stage.setScene(scene);
             stage.show();
         } catch (IOException ex) {
             Logger.getLogger(DifficultyPageController.class.getName()).log(Level.SEVERE, null, ex);
         }
       
    }
    
     public void goToDifficultyMode(ActionEvent event) throws IOException {
        nav.goToPage("DifficultyPage", event);
    }
    public void getPlayersList(ActionEvent event) throws IOException {
        
        String requestMessage = Encapsulator.encapsulateID("GET_AVAILABLE_PLAYERS",SharedData.getInstance().getPlayerID());
        boolean result = connection.checkServerAvailibily(SharedData.getInstance().getServerIp());
        if(result){
//            connection.openConnection();
            connection.sendRequest(requestMessage); 
        }else{
            nav.ShowAlerts("ErrorAlert");
        }
        
    }
    

}
