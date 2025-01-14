/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.util.List;
import java.util.Random;

/**
 *
 * @author Maryam Muhammad
 */
abstract class Mode {
    protected char letter;
    
    public Mode(char letter)
    {
        this.letter=letter;
    }
    
    public abstract int getMove(Game game);
};

class Easy extends Mode
{
    public Easy(char ch)
    {
        super(ch);
    }
    
    public int getMove(Game game)
    {
        List<Integer> availableMoves= game.getEmptyCells();
        
        return availableMoves.get(new Random().nextInt(availableMoves.size()));
    }
}

/*
class Medium extends Mode
{
    public Medium(char letter)
    {
        super(letter);
    }
    
    public int getMove(Game game)
    {
                List<Integer> availableMoves= game.getEmptyCells();
                
               

    }
}

*/

/*
You have a few ways you can adjust strength, although the level of granularity is not great for a game as simple as tic-tac-toe.

Limit your search depth. For example: if the AI only looks 1-2 turns ahead, it's possible to use a strategy to trap it into an unavoidable losing state, while a deeper tree could predict well enough to counter every strategy and always force a draw.
Weaken your evaluation function. This is a bit difficult to do meaningfully in tic-tac-toe, but you might be able to come up with something. If the AI undervalues or overvalues something, it will play worse.
Add noise. Give your program a random chance to select a suboptimal move.
Bias suboptimal decisions. Make the AI less likely to lead the first move with a corner spot, for example.
*/

class Hard extends Mode
{
    Hard(char letter)
    {
        super(letter);
    }
    
    
    
    public int getMove(Game game) {
        int bestScore = Integer.MIN_VALUE;
        int bestMove = -1;

        // Try each available move
        for (int move : game.getEmptyCells()) {
            game.makeMove(move, this.letter);  // Make the move
            int score = minimax(game, false);  // Call minimax for opponent's turn
            game.undoMove(move);  // Undo the move

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

}

