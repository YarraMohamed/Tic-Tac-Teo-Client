/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


package Controllers;

import java.util.Random;
import javafx.scene.control.Button;

/**
 *
 * @author Maryam Muhammad
 */
public abstract class Mode {
    protected Button[][] board;
    protected char computerSymbol;
    protected char playerSymbol;

    public Mode(Button[][] board, char computerSymbol, char playerSymbol) {
        this.board = board;
        this.computerSymbol = computerSymbol;
        this.playerSymbol = playerSymbol;
    }

    public abstract int[] getMove(); 
    
    // Consolidated checkWin function
    protected boolean checkWin(char symbol) {
<<<<<<< HEAD
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
=======
        for (int i = 0; i < 3; i++) {
            //Button in row i, column 0.
>>>>>>> 7e3e0859beb0f21f55c8b33a611df16a0c528ca9
            if ((board[i][0].getText().charAt(0) == symbol && board[i][1].getText().charAt(0)  == symbol && board[i][2].getText().charAt(0)  == symbol) ||
                (board[0][i].getText().charAt(0)  == symbol && board[1][i].getText().charAt(0)  == symbol && board[2][i].getText().charAt(0)  == symbol)) {
                return true;
            }
        }
        // Check diagonals
        return (board[0][0].getText().charAt(0)  == symbol && board[1][1].getText().charAt(0)  == symbol && board[2][2].getText().charAt(0)  == symbol) ||
               (board[0][2].getText().charAt(0)  == symbol && board[1][1].getText().charAt(0)  == symbol && board[2][0].getText().charAt(0)  == symbol);
    }

    protected boolean isCellEmpty(int row, int col)
    {
        return board[row][col].getText().isEmpty(); 
    }
<<<<<<< HEAD
=======
    
    protected boolean isBoardFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (isCellEmpty(i, j)) 
                    return false;
            }
        }
        return true;
    }
>>>>>>> 7e3e0859beb0f21f55c8b33a611df16a0c528ca9
};

class Easy extends Mode {
    private Random random = new Random();

    public Easy(Button[][] board, char computerSymbol, char playerSymbol) {
        super(board, computerSymbol, playerSymbol);
    }

    @Override
    public int[] getMove() {
        int row, col;
<<<<<<< HEAD
=======
//    //Pick a random cell → Check if it's empty → Repeat if necessary.
>>>>>>> 7e3e0859beb0f21f55c8b33a611df16a0c528ca9
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!isCellEmpty(row,col));
        return new int[]{row, col};
    }
<<<<<<< HEAD
}

=======
};

/*
Choose a winning move for itself (if it can win),
Block the player from winning (if the player is about to win),
Or select a random empty cell if no immediate win or block is needed.
*/
>>>>>>> 7e3e0859beb0f21f55c8b33a611df16a0c528ca9
class Medium extends Mode {
    
    public Medium(Button[][] board, char computerSymbol, char playerSymbol) {
        super(board, computerSymbol, playerSymbol);
    }

    @Override
    public int[] getMove() {
        // Try to win or block the opponent’s winning move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isCellEmpty(i,j)){
                    board[i][j].setText(String.valueOf(computerSymbol));
                    if (checkWin(computerSymbol)) {
<<<<<<< HEAD
                        board[i][j].setText("");   
                        return new int[]{i, j};
                    }
                    board[i][j].setText(""); 

                    board[i][j].setText(String.valueOf(playerSymbol));
                    if (checkWin(playerSymbol)) {
                        board[i][j].setText("");  
                        return new int[]{i, j};
=======
                        board[i][j].setText("");   //This is likely to (undo the move) for evaluation purposes before returning the position
                        return new int[]{i, j}; //found a winning move and return it to record the position of the move made by the computer, and the game can use it in the record game feature
                    }
                    board[i][j].setText(""); //It ensures that the board cell does not retain any unwanted values or results.

                    board[i][j].setText(String.valueOf(playerSymbol)); //The AI temporarily places the player’s symbol (playerSymbol) in the empty cell.
                    if (checkWin(playerSymbol)) { //Calls checkWin(playerSymbol) to see if this move allows the player to win.
                        board[i][j].setText("");   
                        return new int[]{i, j}; // Block the player's winning move
>>>>>>> 7e3e0859beb0f21f55c8b33a611df16a0c528ca9
                    }
                    board[i][j].setText("");  
                }
            }
        }

<<<<<<< HEAD
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isCellEmpty(j, j)) {
                    return new int[]{i, j};
=======
        //If no winning or blocking move is found, the AI picks the first available cell.
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isCellEmpty(i, j)) {
                    return new int[]{i, j}; //return the available positions to play in 
>>>>>>> 7e3e0859beb0f21f55c8b33a611df16a0c528ca9
                }
            }
        }
        return new int[]{0, 0};  // Default move (should never reach here)
    }
}

<<<<<<< HEAD
=======
/*
The minimax algorithm is a game-tree search algorithm that looks ahead at all possible future moves and 
evaluates them. The idea is to "minimize the maximum possible loss" by choosing the best move considering 
both the AI's and the opponent's future moves.

Minimax works by recursively simulating all possible game states and assigning scores to 
terminal states (win, lose, or draw). The algorithm then selects the move that maximizes the AI's score 
while minimizing the opponent's potential score.

*/
>>>>>>> 7e3e0859beb0f21f55c8b33a611df16a0c528ca9
class Hard extends Mode {
    public Hard(Button[][] board, char computerSymbol, char playerSymbol) {
        super(board, computerSymbol, playerSymbol);
    }

<<<<<<< HEAD
    @Override
    public int[] getMove() {
        int bestScore = Integer.MIN_VALUE;
=======
     private int minimax(boolean isMaximizing) { //The isMaximizing flag indicates whether it is the maximizing player's (the computer's) turn or the minimizing player's (the player’s) turn.
        if (checkWin(computerSymbol))
            return 1;
        if (checkWin(playerSymbol)) 
            return -1;
        if (isBoardFull()) 
            return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE; // it is the maximizing player's (computer’s) turn or the minimizing player’s (player’s) turn. 

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isCellEmpty(i, j)) {
                    board[i][j].setText(isMaximizing ? String.valueOf(computerSymbol) : String.valueOf(playerSymbol));
                    int score = minimax(!isMaximizing); //so it alternates between the computer’s and the player’s turn)
                    board[i][j].setText("");
                    bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
                }
            }
        }
        return bestScore;
    }
     
     
    @Override
    public int[] getMove() {
        //This value is used when you want to initialize a variable that will later store the maximum value in a comparison process.
        //When you set a variable to Integer.MIN_VALUE,
        //you are saying, "I'm starting with the smallest value, and later, I will update this value to something larger when I find a better option.
        int bestScore = Integer.MIN_VALUE; //constant that represents the smallest possible value an int can have. Its value is -2,147,483,648
        
>>>>>>> 7e3e0859beb0f21f55c8b33a611df16a0c528ca9
        int[] bestMove = new int[]{-1, -1};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isCellEmpty(i, j)) {
                    board[i][j].setText(String.valueOf(computerSymbol));
                    int score = minimax(false);
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

<<<<<<< HEAD
    private int minimax(boolean isMaximizing) {
        if (checkWin(computerSymbol))
            return 1;
        if (checkWin(playerSymbol)) 
            return -1;
        if (isBoardFull()) 
            return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (isCellEmpty(i, j)) {
                    board[i][j].setText(isMaximizing ? String.valueOf(computerSymbol) : String.valueOf(playerSymbol));
                    int score = minimax(!isMaximizing);
                    board[i][j].setText("");
                    bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
                }
            }
        }
        return bestScore;
    }

    private boolean isBoardFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (isCellEmpty(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }
=======
   

    
>>>>>>> 7e3e0859beb0f21f55c8b33a611df16a0c528ca9
}




/*
 class Easy extends Mode {
    private Random random = new Random();

    public Easy(char[][] board, char computerSymbol, char playerSymbol) {
        super(board, computerSymbol, playerSymbol);
    }

    @Override
    public int[] getMove() {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != ' ');
        return new int[]{row, col};
    }
}

class Medium extends Mode {
    
    public Medium(char[][] board, char computerSymbol, char playerSymbol) 
    {
        super(board, computerSymbol, playerSymbol);
    }

    @Override
    public int[] getMove() {
        // Try to win or block the opponent’s winning move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = computerSymbol;
                    if (checkWin(computerSymbol)) {
                        board[i][j] = ' ';  
                        return new int[]{i, j};
                    }
                    board[i][j] = ' ';  

                    board[i][j] = playerSymbol;
                    if (checkWin(playerSymbol)) {
                        board[i][j] = ' ';  
                        return new int[]{i, j};
                    }
                    board[i][j] = ' ';  
                }
            }
        }

        // If no winning/blocking move, pick the first empty cell
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};  // Default move (should never reach here)
    }

    private boolean checkWin(char symbol) {
        // Check rows, columns, and diagonals
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) return true;
            if (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol) return true;
        }
        if (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) return true;
        if (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol) return true;
        return false;
    }
}

class Hard extends Mode {
    public Hard(char[][] board, char computerSymbol, char playerSymbol) {
        super(board, computerSymbol, playerSymbol);
    }

    @Override
    public int[] getMove() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[]{-1, -1};

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = computerSymbol;
                    int score = minimax(false);
                    board[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(boolean isMaximizing) {
        if (checkWin(computerSymbol)) return 1;
        if (checkWin(playerSymbol)) return -1;
        if (isBoardFull()) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = isMaximizing ? computerSymbol : playerSymbol;
                    int score = minimax(!isMaximizing);
                    board[i][j] = ' ';
                    bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
                }
            }
        }
        return bestScore;
    }

    private boolean checkWin(char symbol) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < board.length; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }
        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
               (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}



class Medium extends Mode {

    public Medium(char[][] board, char computerSymbol, char playerSymbol) {
        super(board, computerSymbol, playerSymbol);
    }

    @Override
    public int[] getMove() {
        // Try to win or block the opponent’s winning move first
        int[] winningMove = findWinningMove(computerSymbol);
        if (winningMove != null) {
            return winningMove;
        }

        int[] blockingMove = findWinningMove(playerSymbol);
        if (blockingMove != null) {
            return blockingMove;
        }

        // If no winning/blocking move, choose the best move using minimax
        int[] minimaxMove = getBestMoveUsingMinimax();
        if (minimaxMove != null) {
            return minimaxMove;
        }

        // If no critical move, pick a random available cell
        return getRandomMove();
    }

    private int[] findWinningMove(char symbol) {
        // Check for a winning move for the given symbol (computer or player)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = symbol;
                    if (checkWin(symbol)) {
                        board[i][j] = ' ';
                        return new int[]{i, j};
                    }
                    board[i][j] = ' ';  // Undo the move if it didn't win
                }
            }
        }
        return null;  // No winning move found
    }

    private int[] getBestMoveUsingMinimax() {
        int bestScore = Integer.MIN_VALUE;
        int[] bestMove = new int[]{-1, -1};

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = computerSymbol;
                    int score = minimax(false);
                    board[i][j] = ' ';
                    if (score > bestScore) {
                        bestScore = score;
                        bestMove = new int[]{i, j};
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(boolean isMaximizing) {
        if (checkWin(computerSymbol)) return 1;
        if (checkWin(playerSymbol)) return -1;
        if (isBoardFull()) return 0;

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    board[i][j] = isMaximizing ? computerSymbol : playerSymbol;
                    int score = minimax(!isMaximizing);
                    board[i][j] = ' ';
                    bestScore = isMaximizing ? Math.max(bestScore, score) : Math.min(bestScore, score);
                }
            }
        }
        return bestScore;
    }

    private boolean checkWin(char symbol) {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }
        return (board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
               (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol);
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    private int[] getRandomMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != ' ');  // Ensure the spot is empty
        return new int[]{row, col};
    }
}


You have a few ways you can adjust strength, although the level of granularity is not great for a game as simple as tic-tac-toe.

Limit your search depth. For example: if the AI only looks 1-2 turns ahead, it's possible to use a strategy to trap it into an unavoidable losing state, while a deeper tree could predict well enough to counter every strategy and always force a draw.
Weaken your evaluation function. This is a bit difficult to do meaningfully in tic-tac-toe, but you might be able to come up with something. If the AI undervalues or overvalues something, it will play worse.
Add noise. Give your program a random chance to select a suboptimal move.
Bias suboptimal decisions. Make the AI less likely to lead the first move with a corner spot, for example.
*/

/*
class Hard extends Mode
{
 public Hard(char[][] board, char computerSymbol, char playerSymbol)
 {
    super(board, computerSymbol, playerSymbol);
 }
    
 public int[] getMove() {
    int bestScore = Integer.MIN_VALUE;
    int[] bestMove = new int[]{-1, -1};

    for (int[] move : game.getEmptyCells()) { // Assuming empty cells are returned as [row, col]
        game.makeMove(move, computerSymbol);
        int score = minimax(game, false);
        game.undoMove(move);

        if (score > bestScore) {
            bestScore = score;
            bestMove = move;
        }
    }
    return bestMove;
}

    private int minimax(Game game, boolean isMaximizing) {
        if (game.checkWinner(letter)) 
        {
            return +1;  
        }
        if (game.checkWinner(getOpponent()))
        {
            return -1;
        }  
        if (game.getEmptyCells().isEmpty()) 
        {
            return 0;
        }  

        int bestScore = isMaximizing ? Integer.MIN_VALUE : Integer.MAX_VALUE;

        for (int move : game.getEmptyCells()) {
            game.makeMove(move, isMaximizing ? this.letter : getOpponent());
            int score = minimax(game, !isMaximizing);
            game.undoMove(move);

            if (isMaximizing) {
                bestScore = Math.max(bestScore, score);  // Maximize score
            } else {
                bestScore = Math.min(bestScore, score);  // Minimize score
            }
        }
        return bestScore;
    }

    private char getOpponent() {
        return (this.letter == 'X') ? 'O' : 'X';
    }
/*
    
    public static class Move
{
    Integer position;
    int score;
    
    public Move(Integer pos, int score)
    {
        position=pos;
        this.score=score;
    }
}
    */


