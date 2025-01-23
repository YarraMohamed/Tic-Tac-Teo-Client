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
<<<<<<< HEAD
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.stage.Modality;
=======
import Utils.SharedData;
import java.io.File;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;
>>>>>>> a6910a5b740545793fe9c8e47735953a9f07ea6d

public class ProfilePageController implements Initializable {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Text usernameText;
    @FXML
    private Text scoreText;
    @FXML
    private ListView<String> fileListView;
    private String path;
    private Navigation nav = new Navigation();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        usernameText.setText(SharedData.getInstance().getUserName());
        scoreText.setText("Score : " + SharedData.getInstance().getScore());
        path = System.getProperty( "user.home" ) + "/TicTacToeRecordings/";
        savedGames();
        
        fileListView.setCellFactory(listView -> new ListCell<String>() {

                private Button watchButton = new Button(" Watch ");
                            
                @Override
                protected void updateItem(String item, boolean empty) {
                        
                        super.updateItem(item, empty);

                        if (empty || item == null) {
                            setText(null);    
                            setGraphic(null); 
                        } else {
                            setText(item);         
                            setGraphic(watchButton); 
                        }
                }
        
        });  
 }
        
    public void savedGames() {
       
        File records = new File(path);
            File[] files = records.listFiles();
            if (files != null) {
                for (int i = 0; i < files.length; i++) {
                    if (files[i].isFile()) {
                        fileListView.getItems().add(files[i].getName());
                    }
                }
            } 
    }
        
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
