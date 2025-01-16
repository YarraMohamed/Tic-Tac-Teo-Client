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

public class SignUpController  {

    private Stage stage;
    private Scene scene;
    private Parent root;
    
    @FXML TextField nameTextField;
    @FXML TextField passTextField;
    @FXML TextField emailTextField;
    private Navigation nav = new Navigation();
    
    
    public void goToModePage(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulate("signup",nameTextField.getText(), passTextField.getText(),emailTextField.getText());
        nav.goToPage("ModePage", event);
    }
}
