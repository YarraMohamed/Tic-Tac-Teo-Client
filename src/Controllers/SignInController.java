package Controllers;

import Utils.Encapsulator;
import Utils.Navigation;
import Utils.ServerConnection;
import Utils.SharedData;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.JSONObject;

public class SignInController  {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private Navigation nav = new Navigation();
    private ServerConnection connection = ServerConnection.getInstance();
    
    @FXML private TextField nameTextField;
    @FXML private TextField passTextField;

    public void goToSignUp(ActionEvent event) throws IOException {
       nav.goToPage("SignUp", event);
    }
    
    public void goToModePage(ActionEvent event) throws IOException {
        
        String message = Encapsulator.encapsulateSignIn(nameTextField.getText(), passTextField.getText());
        boolean result = connection.checkServerAvailibily(SharedData.getInstance().getServerIp());
        
        if(result){
            
            connection.openConnection();
            String responseJSON = connection.sendRequest(message);
            JSONObject jsonReceived = new JSONObject(responseJSON);
            String response = jsonReceived.getString("response");
            
            if(response.equals("Success")){
                int playerID = jsonReceived.optInt("Player_ID");
                SharedData.getInstance().setPlayerID(playerID);
                nav.goToPage("ModePage", event);
            } else {
                nav.ShowAlerts("InvalidMessage", event);
            }
            
        }else{
            nav.ShowAlerts("ErrorAlert", event);
        }
       
    }
}
