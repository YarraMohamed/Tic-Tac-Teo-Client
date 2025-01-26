package Utils;

import Controllers.DifficultyPageController;
import Controllers.GameBoardController;
import Controllers.GameRequestNotificationController;
import Controllers.InvalidMessageController;
import Controllers.ServerIPController;

import java.io.IOException;
import java.util.function.Consumer;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Navigation {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    private static Stage primaryStage;
    
    public static void setPrimaryStage(Stage stage) {
        primaryStage = stage;
    }
    public static void switchScene(String fxmlPath, String title, Consumer<Object> controllerConsumer) throws IOException {
        FXMLLoader loader = new FXMLLoader(Navigation.class.getResource(fxmlPath));
        Parent root = loader.load();
        
        // Pass the controller to the consumer for initialization
        if (controllerConsumer != null) {
            controllerConsumer.accept(loader.getController());
        }

        primaryStage.setTitle(title);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
    
     public static void goToPage(String path) throws IOException {
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage is not set!");
        }

        Parent root = FXMLLoader.load(Navigation.class.getResource("/FXML/" + path + ".fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }
    
    public void ShowAlerts(String path) throws IOException{
        FXMLLoader alertLoader = new FXMLLoader(getClass().getResource("/FXML/"+path+".fxml"));
        Parent alertRoot = alertLoader.load();

        InvalidMessageController errorAlertController = alertLoader.getController();

        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.setScene(new Scene(alertRoot));
        alertStage.setTitle("Invalid inputs");
 
  
        alertStage.showAndWait(); 
        
    }
    
    public String ShowServerDialog(ActionEvent event) throws IOException{
        FXMLLoader dialogLoader = new FXMLLoader(getClass().getResource("/FXML/ServerIP.fxml"));
        Parent alertRoot = dialogLoader.load();

        ServerIPController dialogController = dialogLoader.getController();

        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        alertStage.setScene(new Scene(alertRoot));
        alertStage.setTitle("Enter Server IP");
        alertStage.showAndWait();
        
        return dialogController.getIpAddress();
        
    }
    
     public void goToPage(String Path,Event event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/FXML/"+Path+".fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("Tic Tac Toe game");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   

    }  
     
      public void goToPage(String Path,Node node) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/FXML/"+Path+".fxml"));
        stage = (Stage) node.getScene().getWindow();
        stage.setTitle("Tic Tac Toe game");
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   

    }  
    public void goToBoardOnlineMode2(Stage stage, int turn, int p2Id) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/GameBoard.fxml"));
        Parent root = loader.load();

        // Get the controller and set parameters
        GameBoardController controller = loader.getController();
        controller.setp2ID(p2Id);
        controller.setTurn(turn);
        controller.setMode("pvp_online");
        
        // Set the new scene
        Scene scene = new Scene(root);
        stage.setTitle("Tic Tac Toe game");
        stage.setScene(scene);
        stage.show();
    }
    public void goToBoardOnlineMode(int turn,int p2Id) throws IOException{
        
        FXMLLoader x = new FXMLLoader(getClass().getResource("/FXML/GameBoard.fxml"));
        root = x.load();
        GameBoardController c = x.getController();
        c.setp2ID(p2Id);
        c.setTurn(turn);
        c.setMode("pvp_online");
        if (primaryStage == null) {
            throw new IllegalStateException("Primary stage is not set!");
        }

//        Parent root = FXMLLoader.load(Navigation.class.getResource("/FXML/" + path + ".fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.setTitle("Tic Tac Toe");
        primaryStage.show();
    }
    public void showGameRequestNotification(String requestingPlayerUsername , int requestingPlayerID,int requestedPlayerID ) {
        try {
            FXMLLoader gameRequestLoader = new FXMLLoader(getClass().getResource("/FXML/GameRequestNotification.fxml"));
            Parent gameRequestRoot = gameRequestLoader.load();
            
            GameRequestNotificationController gameRequestController = gameRequestLoader.getController();
            gameRequestController.setRequestingPlayerUsername(requestingPlayerUsername);
            gameRequestController.setRequestedPlayerID(requestedPlayerID);
            gameRequestController.setRequestingPlayerID(requestingPlayerID);
           
            Stage gameRequestStage = new Stage();
            gameRequestStage.setScene(new Scene(gameRequestRoot));
            gameRequestStage.setTitle("Game Request");
            gameRequestStage.show();
            
        } catch (IOException e) {
           e.printStackTrace();
           System.out.println("Error while trying to show the game request notification.");
        } 
   
    }
    /*
    public void showGameRequestNotification(String requestingPlayerUsername) {
        try {
            FXMLLoader gameRequestLoader = new FXMLLoader(getClass().getResource("/FXML/GameRequestNotification.fxml"));
            Parent gameRequestRoot = gameRequestLoader.load();
            
            GameRequestNotificationController gameRequestController = gameRequestLoader.getController();
            gameRequestController.setRequestingPlayerUsername(requestingPlayerUsername);

            Stage gameRequestStage = new Stage();
            gameRequestStage.setScene(new Scene(gameRequestRoot));
            gameRequestStage.setTitle("Game Request");
            gameRequestStage.show();
            
        } catch (IOException e) {
           e.printStackTrace();
           System.out.println("Error while trying to show the game request notification.");
        } 
   
    }*/
 
}
