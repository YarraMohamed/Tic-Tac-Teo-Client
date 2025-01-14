/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author Ziad
 */
public class AvailablePlayersController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private VBox  userContainer;
    
    
    @FXML
    public void onNavBack(Event event) {
    try {
        Parent root = FXMLLoader.load(getClass().getResource("/FXML/ModePage.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    } catch (IOException ex) {
        Logger.getLogger(AvailablePlayersController.class.getName()).log(Level.SEVERE, "Failed to load SignIn.fxml", ex);
    }
}
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        VBox.setVgrow(userContainer, Priority.ALWAYS);
        label.setText("Let’s Play");
        addCompoent("ziad");
        addCompoent("fawzy");
        addCompoent("yara");
        addCompoent("mariem");
        addCompoent("amira");


    

     
    }
    private void addCompoent(String user){
         // Create a box
        HBox box = new HBox();
        
        // Set max width and preferred height
        box.setMaxWidth(Double.MAX_VALUE); // Fills the available width
        box.setPrefHeight(40);

        box.setPadding(new Insets(10));
        box.setSpacing(10);

        BackgroundFill backgroundFill = new BackgroundFill(
            Color.web("#A6A6A6", 0.7), 
            new CornerRadii(15), 
            Insets.EMPTY
        );
        box.setBackground(new Background(backgroundFill));
        Label label = new Label(user);
        label.setStyle("-fx-text-fill: black;");
        box.getChildren().add(label);
        userContainer.getChildren().add(box);
        
    
    }
    
    
}
