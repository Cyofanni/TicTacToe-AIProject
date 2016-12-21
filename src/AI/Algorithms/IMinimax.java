package AI.Algorithms;

import ticTacToe.AbsMove;
import ticTacToe.AbsTicTacToe;

//IMinimax basic algorithm
public interface IMinimax {
	public AbsMove computeMove(AbsTicTacToe state);
}

