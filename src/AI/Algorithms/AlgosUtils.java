package AI.Algorithms;

import ticTacToe.AbsTicTacToe;
import ticTacToe.AbsMove;
import ticTacToe.Move;
import java.util.ArrayList;

public class AlgosUtils{
	public static ArrayList<AbsMove> computeActions(char[][] board){
		int n = board.length;
		ArrayList<AbsMove> resActions = new ArrayList<AbsMove>();
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if (board[i][j] == AbsTicTacToe.BLANKVALUE){
					resActions.add(new Move(i, j));
				}
			}
		}
		return resActions;
	}
}
