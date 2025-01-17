import Controllers.DifficultyPageController;
import Controllers.GameBoardController;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        
    GameBoardController gameBoardController = new GameBoardController();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("DifficultyPage.fxml"));
        Parent root = loader.load();

        DifficultyPageController difficultyPageController = loader.getController();

        difficultyPageController.setGameBoardController(gameBoardController);
       Parent root = FXMLLoader.load(getClass().getResource("/FXML/HomePage.fxml"));

        Scene scene = new Scene(root);
        //primaryStage.setScene(scene);
        //primaryStage.show();
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
