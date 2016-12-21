import ticTacToe.TicTacToe;

final public class MinimaxImpl{
	int depth;
	EvalFunc f;


 	public MinimaxImpl(int depth, f){
		this.depth = depth;
		this.f = f;
	}

	private double maxValue(TicTacToe state, int depthP){
		if (state.checkWinner() || depthP == 0){  
			// f may accept player character (TODO) 
			return f(state); 
		}

		double v = Double.MIN_VALUE;
		
		ArrayList<Move> actions = computeActions(state.getField());		
		for (int i = 0; i < actions.length; i++){
			Move currAction = actions[i];
			TicTacToe newState = state.move(currActions.getX(), currActions.getY()); 

                        double min = minValue(newState, depthP - 1);

			if (min > v){
				v = min
			}	
		}

		return v;
	}

	private double minValue(TicTacToe state, int depthP){
              if (state.checkWinner() || depthP == 0){
                        return f(state);
              }

              double v = Double.MAX_VALUE;

              ArrayList<Move> actions = computeActions(state.getField());
              for (int i = 0; i < actions.length; i++){
                        Move currAction = actions[i];
                        TicTacToe newState = state.move(currActions.getX(), currActions.getY());

			double max = maxValue(newState, depthP - 1);

                        if (max < v){
                                v = max
                        }
                }

                return v;
	}

	Move computeMove(TicTacToe state){
		ArrayList<Move> actions = computeActions(state.getField());  
	        int max = Integer.MIN_VALUE;	
		for (int i = 0; i < actions.size(); i++){
			double minValue = minValue(state.play(actions[i]), depth);
			if (minValue > max){
				max = minValue;
			}
		}

		return max;
	}
}
