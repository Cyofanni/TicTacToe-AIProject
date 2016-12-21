import ticTacToe.AbsTicTacToe;
import ticTacToe.AbsMove;
import ticTacToe.Move;

public class AlgosUtils{
	public ArrayList<AbsMove> computeActions(char [][] board){
		 ArrayList<AbsMove> resActions = new ArrayList<AbsMove>();
		 for (int i = 0; i < board.length; i++){
			for (int j = 0; i < board[i].length; j++){
				if (board[i][j] == AbsTicTacToe.BLANK_VALUE){
					resActions.add(new Move(i, j));
				}
			}
		 }

		 return resActions;
	}
}
