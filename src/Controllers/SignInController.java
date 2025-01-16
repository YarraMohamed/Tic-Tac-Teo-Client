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
        String message = Encapsulator.encapsulate("signin",nameTextField.getText(), passTextField.getText());
        System.out.println(message);
        boolean result = connection.checkServerAvailibily(SharedData.getInstance().getServerIp());
        System.out.println(result);
        if(result){
            /*will check for the response to decide what will happen next*/
            connection.openConnection();
            String response = connection.sendRequest(message);
            System.out.println(response);
            nav.goToPage("ModePage", event);
        } else {
            nav.ShowAlerts("ErrorAlert", event);
        }
    }
}
