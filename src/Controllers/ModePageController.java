package Controllers;

import Utils.Encapsulator;
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

import Utils.Navigation;
import Utils.SharedData;

public class ModePageController {
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Navigation nav = new Navigation();

    public void goToProfile(ActionEvent event) throws IOException {
        nav.goToPage("ProfilePageFXML", event);
        
    }
    
    public void signout(ActionEvent event) throws IOException {
        String message = Encapsulator.encapsulateSignOut(SharedData.getInstance().getPlayerID());
        nav.goToPage("HomePage", event);
    }
    

    public void goToLocalMode(ActionEvent event) throws IOException {
       nav.goToPage("GameBoard", event);
       
    }
    
     public void goToDifficultyMode(ActionEvent event) throws IOException {
        nav.goToPage("DifficultyPage", event);
    }
    public void getPlayersList(ActionEvent event) throws IOException {
        nav.goToPage("AvailablePlayers", event);
        
    }
    

}
