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
    
   // private char currentPlayer='X';
    
    //private Mode computerPlayer;
    
    private Button[][] board = new Button[3][3];
    
    private boolean gameOver=false;
    
    private Mode mode;
    
     private Parent root;
    private Scene scene;
    private Stage stage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Button[] row : board) {
            Arrays.fill(row, ' ');
        }
    }   
    
      
 public void setMode(String difficulty, char computerSymbol, char playerSymbol) {
        switch (difficulty.toLowerCase()) {
            case "easy":
                mode = new Easy(board, computerSymbol, playerSymbol);
                break;
            case "medium":
                mode = new Medium(board, computerSymbol, playerSymbol);
                break;
           // case "hard":
             //   mode = new Hard(board, computerSymbol, playerSymbol);
               // break;
            default:
                throw new IllegalArgumentException("Invalid difficulty level!");
                
        }
    }
    public int[] getComputerMove() {
        return mode.getMove();
    }
    
    @FXML
private GameBoardController gameBoardController; 

public void setGameBoardController(GameBoardController gameBoardController)
{
    this.gameBoardController = gameBoardController;
}

    @FXML
private void handleEasy() {
    setMode("easy", 'O', 'X');
    modeLabel1.setText("Easy Mode Selected");
    gameBoardController.setMode(new Easy(board, 'O', 'X'));
}

@FXML
private void handleMedium() {
    setMode("medium", 'O', 'X');
    modeLabel1.setText("Medium Mode Selected");
    gameBoardController.setMode(new Medium(board, 'O', 'X'));
}

@FXML
private void handleHard()
{
    setMode("hard", 'O', 'X');
    modeLabel1.setText("Hard Mode Selected");
    //gameBoardController.setMode(new Hard(board, 'O', 'X'));
}

}