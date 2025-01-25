/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/*package Controllers;

import java.util.Random;
import javafx.scene.control.Button;*/

/**
 *
 * @author Maryam Muhammad
 */
/*public abstract class Mode {
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
        // Check rows and columns
        for (int i = 0; i < 3; i++) {
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
};

class Easy extends Mode {
    private Random random = new Random();

    public Easy(Button[][] board, char computerSymbol, char playerSymbol) {
        super(board, computerSymbol, playerSymbol);
    }

    @Override
    public int[] getMove() {
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (!isCellEmpty(row,col));
        return new int[]{row, col};
    }
}

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
                        board[i][j].setText("");   
                        return new int[]{i, j};
                    }
                    board[i][j].setText(""); 

                    board[i][j].setText(String.valueOf(playerSymbol));
                    if (checkWin(playerSymbol)) {
                        board[i][j].setText("");  
                        return new int[]{i, j};
                    }
                    board[i][j].setText("");  
                }
            }
        }

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (isCellEmpty(j, j)) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{0, 0};  // Default move (should never reach here)
    }
}

class Hard extends Mode {
    public Hard(Button[][] board, char computerSymbol, char playerSymbol) {
        super(board, computerSymbol, playerSymbol);
    }

    @Override
    public int[] getMove() {
        int bestScore = Integer.MIN_VALUE;
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


