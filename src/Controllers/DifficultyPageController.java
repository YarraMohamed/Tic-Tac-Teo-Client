/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Maryam Muhammad
 */
public class DifficultyPageController implements Initializable {

    @FXML
    private BorderPane borderPane;
    @FXML
    private Label modeLabel1;
    @FXML
    private Label modeLabel2;
    @FXML
    private Button easyButton;
    @FXML
    private Button mediumButton;
    @FXML
    private Button hardButton;
    
    private char currentPlayer='X';
    
    private Mode computerPlayer;
    
    private char[] board = new char[9];
    
    private boolean gameOver=false;
    
    private GridPane grid=new GridPane();

    private Game game;
    
     private Parent root;
    private Scene scene;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }   
    
      
    private void handleMove(int index, Button button)
    {
        if(gameOver ||board[index]!=' ')
        {
            return;
        }
        
        board[index]=currentPlayer;
        button.setText(String.valueOf(currentPlayer));
        if(game.checkWinner(currentPlayer))
        {
           gameOver=true;
           return;
        }
        
        currentPlayer=(currentPlayer=='X')?'O':'X';

        
        if(currentPlayer=='O'&&computerPlayer!=null)
        {
            int move=computerPlayer.getMove(new Game(board));
            
            handleMove(move,(Button)borderPane.getChildren().get(move));
        }
    }
    @FXML 
    private void handleEasy()
    {
       
            computerPlayer=new Easy('O');
            currentPlayer='X';
            

    }
   
     @FXML 
    private void handleMedium()
    {
        
    }
    
     @FXML 
    private void handleHard()
    {
        computerPlayer= new Hard('O');
        currentPlayer='X';
    }
}
    

