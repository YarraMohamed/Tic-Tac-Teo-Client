/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Arrays.fill(board,' ');
        createBoard();
    }   
    
    public void createBoard()
    {
        //use gridpane instead of borderpane
        borderPane.getChildren().clear();
        for(int i=0;i<9;i++)
        {
            Button button= new Button(" ");
            //button.setMinSize(50,50);
            
            int index=i;
            button.setOnAction(e -> handleMove(index, button)); 
           grid.add(button,i%3,i/3);
        }
        
        borderPane.setCenter(grid);
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
        createBoard();
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
        createBoard();
    }
}
    

