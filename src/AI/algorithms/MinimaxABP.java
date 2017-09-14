package AI.algorithms;

import AI.AIUtils;
import AI.EF.IEvalFunction;
import ticTacToe.AbsTicTacToeAI;
import ticTacToe.AbsMove;
import java.util.ArrayList;


/**
 * This class implements the {@link IMinimax IMinimax} algorithm
 * with alpha beta pruning
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
public class MinimaxABP extends AbsMinimaxABP{

 	public MinimaxABP(int depth, IEvalFunction f){
		super(depth,f);
	}

	@Override
	protected double maxValue(AbsTicTacToeAI state, double alpha, double beta, int depthP, int currentAI) {
		//Statistics: Count the new node
		this.res.addNode();

		//Check the end of the game or the max depth
		if (state.checkEnd() || depthP == 0){
			double fvalue = this.getF().eval(state, currentAI);

			//Statistics: Set best score and nearest level
			res.setBestScore(fvalue);
			res.setNearestLevel(this.getDepth() - depthP);
			return fvalue;
		}

		double v = Double.NEGATIVE_INFINITY;
		ArrayList<AbsMove> actions = AIUtils.computeActions(state.getField());
		for (int i = 0; i < actions.size(); i++){
			AbsTicTacToeAI newState = (AbsTicTacToeAI)state.deepClone();
			newState.move(actions.get(i));

			double min = minValue(newState,alpha, beta, depthP - 1, currentAI);
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
	protected double minValue(AbsTicTacToeAI state, double alpha, double beta, int depthP, int currentAI) {
		//Statistics: Count the new node
		this.res.addNode();

		//Check the end of the game or the max depth
		if (state.checkEnd() || depthP == 0){
			double fvalue = this.getF().eval(state, currentAI);

			//Statistics: Set best score and nearest level
			res.setBestScore(fvalue);
			res.setNearestLevel(this.getDepth() - depthP);
			return fvalue;
		}

		double v = Double.POSITIVE_INFINITY;
		ArrayList< AbsMove> actions = AIUtils.
				computeActions(state.getField());
		for (int i = 0; i < actions.size(); i++){
			AbsTicTacToeAI newState = (AbsTicTacToeAI)state.deepClone();
			newState.move(actions.get(i));

			double max = maxValue(newState, alpha, beta, depthP - 1, currentAI);
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
	protected double callComputeMove(AbsTicTacToeAI state, int depthP, int currentAI){
		return minValue(state, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, depthP - 1, currentAI);
	}
}
