package AI.Algorithms;

import AI.AIUtils;
import AI.EF.IEvalFunction;
import ticTacToe.AbsTicTacToe;
import ticTacToe.TicTacToe;
import ticTacToe.AbsMove;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This class implements the {@link IMinimax IMinimax} algorithm
 * with alpha beta pruning
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
final public class MinimaxABPRot implements IMinimax{
	int depth;
	IEvalFunction f;


 	public MinimaxABPRot(int depth, IEvalFunction f){
		this.depth = depth;
		this.f = f;
	}

	private double maxValue(TicTacToe state, double alpha, double beta, int depthP) {
		if (state.checkEnd() || depthP == 0)
			return f.eval(state);

		double v = Double.MIN_VALUE;
		ArrayList<AbsMove> actions = AIUtils.
				computeActions(state.getField());
		for (int i = 0; i < actions.size(); i++){
			TicTacToe newState = state.deepClone();
			newState.move(actions.get(i));

            		double min = minValue(newState,alpha, beta, depthP - 1);
			if (min > v)
				v = min;

			if (v >= beta)
				return v;
			if (v > alpha)
				alpha = v;
		}

		return v;
	}

	private double minValue(TicTacToe state, double alpha, double beta, int depthP) {
		if (state.checkEnd() || depthP == 0)
			return f.eval(state);

		double v = Double.MAX_VALUE;
		ArrayList< AbsMove> actions = AIUtils.
				computeActions(state.getField());
		for (int i = 0; i < actions.size(); i++){
			TicTacToe newState = state.deepClone();
			newState.move(actions.get(i));

			double max = maxValue(newState, alpha, beta, depthP - 1);
			if (max < v){
				v = max;
			}

			if (v <= alpha)
				return v;
			if (v < beta)
				beta = v;
		}

		return v;
	}

	@Override
	public AbsMove computeMove(AbsTicTacToe state){
		ArrayList<AbsMove> actions = AIUtils.
				computeActions(state.getField());
		double v = Integer.MIN_VALUE;
		AbsMove bestMove = null;
		
		for (int i = 0; i < actions.size(); i++){
			TicTacToe newState = state.deepClone();
			newState.move(actions.get(i));
			double min = minValue(newState, Double.MIN_VALUE, Double.MAX_VALUE, depth - 1);
			if (min > v){
				v = min;
				bestMove = actions.get(i);
			}
		}

		return bestMove;
	}
}
