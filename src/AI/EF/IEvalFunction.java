package AI.EF;

import ticTacToe.AbsTicTacToeAI;

/**
 * Interface of the functions of evaluations
 *
 * @author Davide Rigoni
 */
public interface IEvalFunction {

    /**
     * This method evaluate the state of the game
     * @return The score of the state
     */
    double eval(AbsTicTacToeAI state, int currentAI);
}
