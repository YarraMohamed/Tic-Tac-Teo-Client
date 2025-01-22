import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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