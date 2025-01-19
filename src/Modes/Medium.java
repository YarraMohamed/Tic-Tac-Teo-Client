/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modes;

import javafx.scene.control.Button;

/**
 *
 * @author Maryam Muhammad
 */

public class Medium extends Mode {
    private static final int MAX_DEPTH = 2;  

    public Medium(Button[][] board, char computerSymbol, char playerSymbol) {
        super(board, computerSymbol, playerSymbol);
    }

    private int minimax(int depth, boolean isMaximizing) {
        if (checkWin(computerSymbol)) 
            return 1;
        if (checkWin(playerSymbol)) 
            return -1;
        if (isBoardFull()) 
            return 0;
        if (depth == MAX_DEPTH)  
            return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isCellEmpty(i, j)) {
                    board[i][j].setText(isMaximizing ? String.valueOf(computerSymbol) : String.valueOf(playerSymbol));
                    int score = minimax(depth + 1, !isMaximizing); 
                    board[i][j].setText("");
                    bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
                }
            }
        }
        return bestScore;
    }

    @Override
    public int[] getMove() {
        if (checkWin(computerSymbol) || checkWin(playerSymbol)) {
            return new int[]{-1, -1}; 
        }

        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[]{-1, -1};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isCellEmpty(i, j)) {
                    board[i][j].setText(String.valueOf(computerSymbol));

                    if (checkWin(computerSymbol)) {
                        return new int[]{i, j}; 
                    }

                    int score = minimax(0, false); 
                    board[i][j].setText(""); 

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }

        return bestMove;
    }
}
/*
public class Medium extends Mode {
    private static final int MAX_DEPTH = 3; 

    public Medium(Button[][] board, char computerSymbol, char playerSymbol) {
        super(board, computerSymbol, playerSymbol);
    }

    private int minimax(int depth, boolean isMaximizing) {
        if (checkWin(computerSymbol)) 
            return 1;
        if (checkWin(playerSymbol)) 
            return -1;
        if (isBoardFull()) 
            return 0;
        if (depth == MAX_DEPTH)  
            return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isCellEmpty(i, j)) {
                    board[i][j].setText(isMaximizing ? String.valueOf(computerSymbol) : String.valueOf(playerSymbol));
                    int score = minimax(depth + 1, !isMaximizing); // Increase depth with each recursive call
                    board[i][j].setText("");
                    bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
                }
            }
        }
        return bestScore;
    }

    @Override
public int[] getMove() {
    if (checkWin(computerSymbol) || checkWin(playerSymbol)) {
        return new int[]{-1, -1}; 
    }

    int bestScore = Integer.MIN_VALUE;
    int[] bestMove = new int[]{-1, -1};

    for (int i = 0; i < board.length; i++) {
        for (int j = 0; j < board[i].length; j++) {
            if (isCellEmpty(i, j)) {
                board[i][j].setText(String.valueOf(computerSymbol));
                
                if (checkWin(computerSymbol)) {
                    return new int[]{i, j}; 
                }

                int score = minimax(0, false); 
                board[i][j].setText(""); 

                if (score > bestScore) {
                    bestScore = score;
                    bestMove = new int[]{i, j};
                }
            }
        }
    }

    return bestMove;
}
}
*/