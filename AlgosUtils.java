public class AlgosUtils{
	private boolean isFinalFromXY(char [][] board, int x, int y){
                char player = board[x][y];

		if (player != 'X' && player != 'O'){
			return false;	
		}
		
		try{
			// row direction
			if (board[x+1][y] == player && board[x+2][y] == player){
				return true;
			}
		}
		catch(IndexOutOfBoundsException){}

		try{
			// diagonal direction
			if (board[x+1][y+1] == player && board[x+2][y+2] == player){
				return true;
			}
		}
		catch(IndexOutOfBoundsException){}

		try{
			// column direction
			if (board[x][y+1] == player && board[x][y+2] == player){
		 		return true;						                        
			}
		}
		catch(IndexOutOfBoundsException){}

		try{
			// second diagonal direction
			if (board[x-1][y+1] == player && board[x-2][y+2] == player){
				return true;
			}	
		}
		catch(IndexOutOfBoundsException){}

	}

	boolean isFinalState(char [][] board){
  	      int numRows = board.length;
	      int numCols = board[0].length;

	      for (int i = 0; i < numRows; i++){
		for (int j = 0; j < numCols; j++){
			if (isFinalFromXY(board, j, i)){
				return true;
			}
		}
	      }
	      return false;
	}
}
