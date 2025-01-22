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
import Utils.SharedData;
import java.io.File;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.DirectoryChooser;

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
    public void goBack(ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("/FXML/ModePage.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } 
}
