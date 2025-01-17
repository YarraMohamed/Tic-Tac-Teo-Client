package Controllers;

import Utils.Encapsulator;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import Utils.Navigation;
import Utils.ServerConnection;
import Utils.SharedData;

public class SignUpController  {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML TextField nameTextField;
    @FXML TextField passTextField;
    @FXML TextField emailTextField;
    private Navigation nav = new Navigation();
    private ServerConnection connection = ServerConnection.getInstance();
    
    
    public void goToModePage(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulate("signup",nameTextField.getText(),emailTextField.getText(),passTextField.getText());
        boolean result = connection.checkServerAvailibily(SharedData.getInstance().getServerIp());
        if(result){
            connection.openConnection();
            String response = connection.sendRequest(message);
            if(response.equals("Success")){
            nav.goToPage("ModePage", event);
            } else {
                nav.ShowAlerts("InvalidMessage", event);
            }
        }else{
            nav.ShowAlerts("ErrorAlert", event);
        }
    }
}
