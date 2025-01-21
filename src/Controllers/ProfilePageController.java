package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import Utils.Navigation;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.stage.Modality;

public class ProfilePageController {
    
    private Stage stage;
    private Scene scene;
    private Parent root;  
    @FXML
    private Text titleProfileText;
    private Navigation nav = new Navigation();

    public void goBack(ActionEvent event) throws IOException {
        nav.goToPage("ModePage", event);
    } 
    
    public void viewGame(Event event) {
      try {        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/ReplayBoard.fxml"));
        Parent root = loader.load();
        ReplayBoardController controller = loader.getController();
        String name = titleProfileText.getText();
        controller.setFileName(name);
        Stage newStage = new Stage();
        newStage.setTitle("Replay Game");
        newStage.setScene(new Scene(root));
        newStage.show();
 
      } catch (IOException ex) {
        Logger.getLogger(AvailablePlayersController.class.getName()).log(Level.SEVERE, "Failed to load SignIn.fxml", ex);
      }
    }
    
    
}
