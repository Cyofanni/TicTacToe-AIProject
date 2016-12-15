final public class MinimaxImpl{
	int depth;
	EvalFunc f;
        private char currPlayer;


	private void switchPlayer(){
		if (currPlayer == 'X'){
			currPlayer = 'O';
		}
		else{
			currPlayer = 'X';
		}
	}

 	public MinimaxImpl(int depth, f){
		this.depth = depth;
		this.f = f;
	}

	private double maxValue(char [][] board){
		if (isFinalState(board)){
			switchPlayer();
			// f may accept player character (TODO) 
			return f(board);
		}

		double v = Double.MIN_VALUE;
		
		// computeActions will be defined
		Move [] actions = computeActions(board, currPlayer);		
		for (int i = 0; i < actions.length; i++){
			Move currAction = actions[i];
			char [][] newBoard;
			newBoard[currAction.getX()][currActions.getY()] = currPlayer;
		        switchPlayer();

                        double min = minValue(newBoard);

			if (min > v){
				v = min
			}	
		}

		return v;
	}

	private double minValue(){
              if (isFinalState(board)){
                        switchPlayer();
                        return f(board);
              }

              double v = Double.MAX_VALUE;

               // computeActions will be defined
              Move [] actions = computeActions(board, currPlayer);
              for (int i = 0; i < actions.length; i++){
                        Move currAction = actions[i];
                        char [][] newBoard;
                        newBoard[currAction.getX()][currActions.getY()] = currPlayer;
                        switchPlayer();

			double max = maxValue(newBoard);

                        if (max < v){
                                v = max
                        }
                }

                return v;
	}

	Move computemove(char [][] board){

	}
}
