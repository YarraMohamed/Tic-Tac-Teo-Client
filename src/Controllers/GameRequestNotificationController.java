package Controllers;

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
    
    //private int rejectingPlayerId;
    //private int rejectedPlayerId;
    
    //private int rejectingPlayerId = SharedData.getInstance().getPlayerID();
    //public static int rejectingPlayerId;
    public static int rejectedPlayerId;

    private ServerConnection connection = ServerConnection.getInstance();
    
    
        /*public void setRejectingPlayerId(int rejectingPlayerId) {
        this.rejectingPlayerId = rejectingPlayerId;
    }*/
    
    /*public int getRejectingPlayerId() {
        return rejectingPlayerId;
    }*/

    
    /*public void setRejectedPlayerId(int rejectedPlayerId) {
        this.rejectedPlayerId = rejectedPlayerId;
    }
    
    
    public int getRejectedPlayerId() {
        return rejectedPlayerId;
    }*/
    

    public void setRequestingPlayerUsername(String requestingPlayerUsername) {
        this.requestingPlayerUsername = requestingPlayerUsername;
        System.out.println("Setting requestingPlayerUsername: " + requestingPlayerUsername); // log message
        gameRequestMessage.setText(requestingPlayerUsername + " is asking you to a game.\nYou can either accept or reject the request.");
    }
    
    
    @FXML
    private void onAcceptButtonClicked(ActionEvent acceptEvent) {
        System.out.println("Game request accepted");
    }
    
    
    @FXML
    private void onRejectButtonClicked(ActionEvent rejectEvent) {
        System.out.println("Reject button clicked.");
        //int rejectingPlayerId = SharedData.getInstance().getPlayerID();
        try {
            boolean result = connection.checkServerAvailibily(SharedData.getInstance().getServerIp());
            if(result){
                //int rejectingPlayerId = SharedData.getInstance().getPlayerID(); // The current player's ID
                //System.out.println("Rejecting Player ID: " + rejectingPlayerId); // Should match the current player's ID
                System.out.println("Rejected Player ID: " + rejectedPlayerId); // Should match the ID of the original requesting player
                
                //String gameRejectedRequest = Encapsulator.encapsulateGameRejectRequest(getRejectingPlayerId(), rejectedPlayerId);
                //String gameRejectedRequest = Encapsulator.encapsulateGameRejectRequest(2, 1);
                String gameRejectedRequest = Encapsulator.encapsulateGameRejectRequest(rejectedPlayerId);
                ServerConnection.getInstance().sendRequest(gameRejectedRequest);
                
                System.out.println("Reject button clicked.");
                System.out.println("Game request rejected");
            } else {
                   System.out.println("error");
               }
            
        } catch(IOException e) {
                e.printStackTrace();
                System.out.println("Failed to send game reject.");
            } 
    }

}
        
        

   
    

