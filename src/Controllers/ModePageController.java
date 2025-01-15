package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ModePageController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void goToProfile(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/FXML/ProfilePageFXML.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void signout(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulate("signout");
        root = FXMLLoader.load(getClass().getResource("/FXML/SignIn.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
    public void goToLocalMode(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulate("signout");
        root = FXMLLoader.load(getClass().getResource("/FXML/GameBoard.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
     public void goToDifficultyMode(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulate("signout");
        root = FXMLLoader.load(getClass().getResource("/FXML/DifficultyPage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    public void getPlayersList(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulate("signout");
        root = FXMLLoader.load(getClass().getResource("/FXML/AvailablePlayers.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
    
}
