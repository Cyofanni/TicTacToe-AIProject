package AI.Algorithms;

import AI.EF.IEvalFunction;
import ticTacToe.AbsTicTacToe;
import ticTacToe.Move;
import ticTacToe.TicTacToe;
import ticTacToe.AbsMove;
import java.io.IOException;
import java.util.ArrayList;

final public class Minimax implements IMinimax{
	int depth;
	IEvalFunction f;


 	public Minimax(int depth, IEvalFunction f){
		this.depth = depth;
		this.f = f;
	}

	private double maxValue(TicTacToe state, int depthP) throws IOException {
		if (state.checkWinner() || depthP == 0){   
			return f.eval(state);
		}
		double v = Double.MIN_VALUE;
		ArrayList<AbsMove> actions = AlgosUtils.
				computeActions(state.getField());
		for (int i = 0; i < actions.size(); i++){
			TicTacToe newState = state.deepClone();
			newState.move(actions.get(i));
            double min = minValue(newState, depthP - 1);
			if (min > v){
				v = min;
			}
		}

		return v;
	}

	private double minValue(TicTacToe state, int depthP) throws IOException {
		if (state.checkWinner() || depthP == 0){
			return f.eval(state);
		}
		double v = Double.MAX_VALUE;
		ArrayList< AbsMove> actions = AlgosUtils.
		computeActions(state.getField());
		for (int i = 0; i < actions.size(); i++){
			TicTacToe newState = state.deepClone();
			newState.move(actions.get(i));
			double max = maxValue(newState, depthP - 1);
			if (max < v){
				v = max;
			}
		}

		return v;
	}

	@Override
	public AbsMove computeMove(AbsTicTacToe state){
		ArrayList<AbsMove> actions = AlgosUtils.
				computeActions(state.getField());
		double max = Integer.MIN_VALUE;
		AbsMove bestMove = null;
		
		for (int i = 0; i < actions.size(); i++){
			TicTacToe newState = state.deepClone();
			try {
				newState.move(actions.get(i));
				double minValue = minValue(newState, depth);
				if (minValue > max){
					max = minValue;
					bestMove = actions.get(i);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		return bestMove;
	}
}
