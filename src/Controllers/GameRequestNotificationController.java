package Controllers;

import Utils.Navigation;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.Encapsulator;
import Utils.ServerConnection;
import Utils.ServerMessagesRouter;
import Utils.SharedData;
import java.io.IOException;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.json.JSONException;


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

    private ServerConnection connection = ServerConnection.getInstance();
    private Navigation nav=new Navigation();

    public void setRequestingPlayerUsername(String requestingPlayerUsername) {
        this.requestingPlayerUsername = requestingPlayerUsername;
        System.out.println("Setting requestingPlayerUsername: " + requestingPlayerUsername); // log message
        gameRequestMessage.setText(requestingPlayerUsername + " is asking you to a game.\nYou can either accept or reject the request.");
    }
    private int requestedPlayerID, requestingPlayerID;

    public int getRequestedPlayerID() {
        return requestedPlayerID;
    }

    public void setRequestedPlayerID(int requestedPlayerID) {
        this.requestedPlayerID = requestedPlayerID;
    }

    public int getRequestingPlayerID() {
        return requestingPlayerID;
    }

    public void setRequestingPlayerID(int requestingPlayerID) {
        this.requestingPlayerID = requestingPlayerID;
    }
    private Stage stage;

    @FXML
    private void onAcceptButtonClicked(ActionEvent acceptEvent) {
        try {
            System.out.println("Game request accepted");
            connection.sendRequest(Encapsulator.encapsulateAcceptiance(requestingPlayerID));
            nav.goToBoardOnlineMode(2, requestingPlayerID);
            acceptButton.getScene().getWindow().hide();

//            stage =(Stage) ((Node) acceptEvent.getSource()).getScene().getWindow();
//            stage.getScene().;
        } catch (IOException ex) {
            Logger.getLogger(GameRequestNotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void onRejectButtonClicked(ActionEvent rejectEvent) {
        acceptButton.getScene().getWindow().hide();
    }
        
        
}
   
    

