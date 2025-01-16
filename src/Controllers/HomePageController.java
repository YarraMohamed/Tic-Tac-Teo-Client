/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Utils.SharedData;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author Maryam Muhammad
 */
public class HomePageController implements Initializable {
    
 
    @FXML
    private Button playButton;
    
    private Button signInButton;
    
    private Parent root;
    private Scene scene;
    private Stage stage;
    

    
    @FXML
    public void clickPlayButton(ActionEvent event) throws IOException {
        try {
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/FXML/DifficultyPage.fxml"));
            Parent root= loader.load();
            DifficultyPageController difficultyPageController  =loader.getController();
            Scene scene = new Scene(root);
            stage= (Stage)((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.show();
        } catch (IOException ex) {
            Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    
    @FXML
      private void clickSignInButton(ActionEvent event) throws IOException {
        try {
        FXMLLoader dialogLoader = new FXMLLoader(getClass().getResource("/FXML/ServerIP.fxml"));
        Parent alertRoot = dialogLoader.load();

        ServerIPController dialogController = dialogLoader.getController();

        Stage alertStage = new Stage();
        alertStage.initModality(Modality.APPLICATION_MODAL);
        alertStage.initOwner(((Node) event.getSource()).getScene().getWindow());
        alertStage.setScene(new Scene(alertRoot));
        alertStage.setTitle("Enter Server IP");
        alertStage.showAndWait();
        
        String serverIP = dialogController.getIpAddress();
        
        if (serverIP != null && !serverIP.isEmpty()) {
            SharedData.getInstance().setServerIp(serverIP);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/FXML/SignIn.fxml"));
            Parent root = loader.load();
            SignInController signInController = loader.getController();
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        }
    } catch (IOException ex) {
        Logger.getLogger(HomePageController.class.getName()).log(Level.SEVERE, null, ex);
    }  
    }
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }     
}
