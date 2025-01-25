package Controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;


public class GameRejectionNotificationController  {

    @FXML
    private AnchorPane gameRejectNotification;
    @FXML
    private Button cancelButton;
    @FXML
    private Text gameRejectTitle;
    @FXML
    private Label gameRejectMessage;

    //private String rejectingPlayerUsername; 
    
    
    /*public void setRejectingPlayerUsername(String rejectingPlayerUsername) {
        this.rejectingPlayerUsername = rejectingPlayerUsername;
        System.out.println("Setting rejectingPlayerUsername: " + rejectingPlayerUsername); // log message
        gameRejectMessage.setText(rejectingPlayerUsername + " has sadly rejected your request.\nChoose another player if you still wish to play.");
    }*/

    public void displayRejectionMessage() {
        gameRejectMessage.setText("Your request is rejected.\nChoose another player if you still wish to play.");
    }
    
   
    
    @FXML
    private void onCancelButtonClicked(ActionEvent cancelEvent) {
    }
    
}
