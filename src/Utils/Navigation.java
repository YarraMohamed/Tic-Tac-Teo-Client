
package Utils;

import Controllers.InvalidMessageController;
import Controllers.ServerIPController;

import java.io.IOException;
import javafx.event.ActionEvent;
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
    
    public void ShowAlerts(String path ,ActionEvent event) throws IOException{
        FXMLLoader alertLoader = new FXMLLoader(getClass().getResource("/FXML/"+path+".fxml"));
        Parent alertRoot = alertLoader.load();

        InvalidMessageController errorAlertController = alertLoader.getController();

        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        alertStage.setScene(new Scene(alertRoot));
        alertStage.setTitle("Error Message");
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
    
     public void goToPage(String Path,ActionEvent event) throws IOException{
        root = FXMLLoader.load(getClass().getResource("/FXML/"+Path+".fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();   
    }  
}