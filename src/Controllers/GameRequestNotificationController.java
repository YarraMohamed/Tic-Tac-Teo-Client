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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
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

    private ServerConnection connection = ServerConnection.getInstance();
    private Navigation nav=new Navigation();

    public void setRequestingPlayerUsername(String requestingPlayerUsername) {
        this.requestingPlayerUsername = requestingPlayerUsername;
        System.out.println("Setting requestingPlayerUsername: " + requestingPlayerUsername); // log message
        gameRequestMessage.setText(requestingPlayerUsername + " is asking you to a game.\nYou can either accept or reject the request.");
    }
    
    
    @FXML
    private void onAcceptButtonClicked(ActionEvent acceptEvent) {
        String message = Encapsulator.encapsulateAcceptiance(requestedPlayerID);
        try {
            ServerConnection.getInstance().sendRequest(message);
            nav.goToBoardOnlineMode(requestedPlayerID, requestedPlayerID, acceptEvent);
        } catch (IOException ex) {
            Logger.getLogger(GameRequestNotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    private void onRejectButtonClicked(ActionEvent rejectEvent) {
        String message = Encapsulator.encapsulateRejection(requestedPlayerID);
        System.out.println(requestedPlayerID);
        try {
            ServerConnection.getInstance().sendRequest(message);
        } catch (IOException ex) {
            Logger.getLogger(GameRequestNotificationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Reject button clicked.");
    }
        
        
}
   
    

