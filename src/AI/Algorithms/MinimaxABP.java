package AI.Algorithms;

import AI.AIUtils;
import AI.EF.IEvalFunction;
import ticTacToe.TicTacToe;
import ticTacToe.AbsMove;
import java.util.ArrayList;


/**
 * This class implements the {@link IMinimax IMinimax} algorithm
 * with alpha beta pruning
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
final public class MinimaxABP extends AbsMinimaxABP{

 	public MinimaxABP(int depth, IEvalFunction f){
		super(depth,f);
	}

	@Override
	protected double maxValue(TicTacToe state, double alpha, double beta, int depthP) {
		if (state.checkEnd() || depthP == 0)
			return this.getF().eval(state);

		double v = Double.NEGATIVE_INFINITY;
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

	@Override
	protected double minValue(TicTacToe state, double alpha, double beta, int depthP) {
		if (state.checkEnd() || depthP == 0)
			return this.getF().eval(state);

		double v = Double.POSITIVE_INFINITY;
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
}
