package AI.Algorithms;

import ticTacToe.AbsMove;
import ticTacToe.AbsTicTacToe;

/**
 * This interface represents the Minimax algorithm
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
public interface IMinimax {
	public AbsMove computeMove(AbsTicTacToe state);
}

