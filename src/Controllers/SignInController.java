package Controllers;

import Utils.Encapsulator;
import Utils.Navigation;
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
    
    @FXML private TextField nameTextField;
    @FXML private TextField passTextField;

    public void goToSignUp(ActionEvent event) throws IOException {
       nav.goToPage("SignUp", event);
    }
    
    public void goToModePage(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulate("signin",nameTextField.getText(), passTextField.getText());
        nav.goToPage("ModePage", event);
      
    }
}
