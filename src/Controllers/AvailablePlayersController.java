/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import com.sun.javafx.geom.Rectangle;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;

/**
 *
 * @author Ziad
 */
public class AvailablePlayersController implements Initializable {
    
    @FXML
    private Label label;
    
    @FXML
    private VBox  userContainer;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
//        System.out.println();
//        root.getScene().getStylesheets().add(getClass().getResource("/resources/style/list_screen_style.css").toExternalForm());
    
    VBox.setVgrow(userContainer, Priority.ALWAYS);
    label.setText("Let’s Play");
    addCompoent("ziad");
    addCompoent("fawzy");
    addCompoent("yara");
    addCompoent("mariem");
    addCompoent("amira");


    

     
    }
    private void addCompoent(String user){
//        Rectangle rect=new Rectangle(1000, 60);
//        Box b=new Box();
//        b.set
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
        box.getChildren().add(label);
        userContainer.getChildren().add(box);
        
    }
    
}
