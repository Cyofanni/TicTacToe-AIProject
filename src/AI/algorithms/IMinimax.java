package AI.algorithms;

import ticTacToe.AbsMove;
import ticTacToe.AbsTicTacToe;

/**
 * This interface represents the Minimax algorithm
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
public interface IMinimax {
	AbsMove computeMove(AbsTicTacToe state);
}

