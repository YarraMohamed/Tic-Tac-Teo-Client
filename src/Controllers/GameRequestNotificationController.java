package Controllers;

import Utils.Navigation;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class GameRequestNotificationController  {

    @FXML
    private AnchorPane gameRequestNotification;
    @FXML
    private Button acceptButton;
    @FXML
    private Button rejectButton;
    @FXML
    private Text gameRequestTitle;
    @FXML
    private Label gameRequestMessage;
    
    private String requestingPlayerUsername; 
    
    private Navigation nav=new Navigation();
    
    public void setRequestingPlayerUsername(String requestingPlayerUsername) {
        this.requestingPlayerUsername = requestingPlayerUsername;
        System.out.println("Setting requestingPlayerUsername: " + requestingPlayerUsername); // log message
        gameRequestMessage.setText(requestingPlayerUsername + " is asking you to a game.\nYou can either accept or reject the request.");
    }
    
    
    @FXML
    private void onAcceptButtonClicked(ActionEvent acceptEvent) {
        try {
            System.out.println("Game request accepted");
            nav.goToBoardOnlineMode(2, 11, acceptEvent);/////////////
        } catch (IOException ex) {
            Logger.getLogger(GameRequestNotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void onRejectButtonClicked(ActionEvent rejectEvent) {
        System.out.println("Game request rejected");
    }


    @FXML
    public void initialize() {
        
    }    
    
}
