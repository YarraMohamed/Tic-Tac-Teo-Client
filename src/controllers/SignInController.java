package controllers;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SignInController  {

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void goToSignUp(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/SignUp.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void goToModePage(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/fxml/ModePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}