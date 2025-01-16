package Controllers;

import Utils.SharedData;
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
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ServerIPController {

    @FXML
    private TextField ipTextField;
    @FXML
    private Button enterButton;
    private String ipAddress;

    @FXML
    private void ShowDialog(ActionEvent event) throws IOException{
        ipAddress = ipTextField.getText();
        boolean result = SharedData.isValidIP(ipAddress);
        if(result){
           enterButton.getScene().getWindow().hide();
        } else {
            FXMLLoader alertLoader = new FXMLLoader(getClass().getResource("/FXML/ErrorAlert.fxml"));
            Parent alertRoot = alertLoader.load();

            ErrorAlertController errorAlertController = alertLoader.getController();

           Stage alertStage = new Stage();
           alertStage.initModality(Modality.APPLICATION_MODAL);
           alertStage.initOwner(((Node) event.getSource()).getScene().getWindow());
           alertStage.setScene(new Scene(alertRoot));
           alertStage.setTitle("Error Message");
           alertStage.showAndWait();  
        }
    }

    public String getIpAddress() {
        return ipAddress;
    } 
    
}
