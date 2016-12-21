public class AlgosUtils{
	public ArrayList<Move> computeActions(char [][] board){
		 ArrayList<Move> resActions = new ArrayList<Move>();
		 for (int i = 0; i < board.length; i++){
			for (int j = 0; i < board[].length; j++){
				if (board[i][j] == AbsTicTacToe.BLANK_VALUE){
					resActions.add(new Move(i, j));		
				}
			}
		 }

		 return resActions;
	}
}
