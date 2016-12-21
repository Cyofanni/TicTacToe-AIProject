import ticTacToe.TicTacToe;
import ticTacToe.AbsMove;

final public class MinimaxImpl{
	int depth;
	EvalFunc f;


 	public MinimaxImpl(int depth, f){
		this.depth = depth;
		this.f = f;
	}

	private double maxValue(TicTacToe state, int depthP){
		if (state.checkWinner() || depthP == 0){   
			return f(state); 
		}

		double v = Double.MIN_VALUE;
		
		ArrayList<AbsMove> actions = computeActions(state.getField());		
		for (int i = 0; i < actions.length; i++){
			TicTacToe newState = state.move(actions[i]); 

                        double min = minValue(newState, depthP - 1);

			if (min > v){
				v = min;
			}
		}

		return v;
	}

	private double minValue(TicTacToe state, int depthP){
              if (state.checkWinner() || depthP == 0){
                        return f(state);
              }

              double v = Double.MAX_VALUE;

              ArrayList< AbsMove> actions = computeActions(state.getField());
              for (int i = 0; i < actions.length; i++){
                        TicTacToe newState = state.move(actions[i]);

			double max = maxValue(newState, depthP - 1);

                        if (max < v){
                                v = max;
                        }
                }

                return v;
	}

	public AbsMove computeMove(TicTacToe state){
		ArrayList<AbsMove> actions = computeActions(state.getField());
	        double max = Integer.MIN_VALUE;
		AbsMove bestMove = null;
		
		for (int i = 0; i < actions.size(); i++){
			double minValue = minValue(state.play(actions[i]), depth);
			if (minValue > max){
				max = minValue;
				bestMove = actions[i];
			}
		}

		return bestMove;
	}
}
