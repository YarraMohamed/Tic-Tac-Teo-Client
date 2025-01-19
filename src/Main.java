import Controllers.DifficultyPageController;
import Controllers.GameBoardController;
import Utils.ServerConnection;
import java.io.IOException;
import java.io.File;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));
        Scene scene = new Scene(root);

        stage.setScene(scene);
        
        stage.setOnCloseRequest(event -> {
            ServerConnection.getInstance().closeConnection();
            System.out.println("Connection closed successfully.");
        });
        
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
