import Controllers.DifficultyPageController;
import Controllers.GameBoardController;
import Utils.Encapsulator;
import Utils.Navigation;
import Utils.ServerConnection;
import Utils.SharedData;
import java.io.IOException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;
import org.json.JSONObject;

public class Main extends Application {
    
    
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Navigation nav = new Navigation();
        
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.setTitle("Tic Tac Toe game");
        
        stage.setOnCloseRequest(event -> {
            try {
                if(SharedData.getInstance().getPlayerID() != 0){
                    String message = ServerConnection.getInstance().sendRequest(
                        Encapsulator.encapsulateID("SIGN_OUT", SharedData.getInstance().getPlayerID())
                );
                
                   JSONObject jsonReceived = new JSONObject(message);
                   if(jsonReceived.getString("response").equals("Success")){
                    ServerConnection.getInstance().closeConnection();
                  }
                } 
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
               
        });
        
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
