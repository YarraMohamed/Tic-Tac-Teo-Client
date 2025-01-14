/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Maryam Muhammad
 */
public class Game {
    
    private char[] board;
    char currentWinner;
    
    public Game(char[] board)
    {
       // board=new char[9];
        //Arrays.fill(board, ' ');
        currentWinner='\0'; 
        this.board= Arrays.copyOf(board, board.length);
    }
    
    public List<Integer> getEmptyCells() //available Moves
    {
        List<Integer> emptyCells= new ArrayList<>();
        for(int i=0;i<board.length;i++)
        {
            if(board[i]==' ')
            {
                emptyCells.add(i);
            }
        }
        return emptyCells;
    }
    
    
    public boolean checkWinner(char player)
    {
        int[][] winningPosibilities ={
                                        {0,1,2},{3,4,5},{6,7,8},
                                        {0,3,6},{1,4,7},{2,5,8},
                                        {0,4,8},{2,4,6} };
        
        for(int[] posibilities:winningPosibilities)
        {
            if(board[posibilities[0]]==player && board[posibilities[1]]==player && board[posibilities[2]]==player)
            {
                return true;
            }
        }
        
        return false;                 
                
    } 

    /*
        public void makeMove(int position, char player) {
        if (board[position] == ' ')
        {
            board[position] = player;
        
            if (checkWinner(player))
            
            {
                currentWinner = player;
            }
        }
    }
      
*/
    public boolean makeMove(int position, char player) {
    if (isValidMove(position)) {
        board[position] = player;
        if (checkWinner(player)) {
            currentWinner = player;
            return true; // return true if the move results in a win
        }
    }
    return false;
}
    
    
    public void undoMove(int index)
    {
        board[index]=' ';
            currentWinner=' ';
    }
    
    public boolean isValidMove(int index)
    {
        return index>=0 && index<board.length && board[index]==' ';
    }
    
    public boolean isBoardFull()
    {
        for(char cell:board)
        {
            if(cell==' ')
                return false;
        }
        return true;
    }
}
