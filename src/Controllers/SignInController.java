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

public class SignInController  {

    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML TextField nameTextField;
    @FXML TextField passTextField;

    public void goToSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/FXML/SignUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void goToModePage(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulate("signin",nameTextField.getText(), passTextField.getText());
        root = FXMLLoader.load(getClass().getResource("/FXML/ModePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
