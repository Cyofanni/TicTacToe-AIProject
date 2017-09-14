package AI.algorithms;

import AI.AIUtils;
import AI.EF.IEvalFunction;
import ticTacToe.*;
import java.util.ArrayList;


/**
 * This class implements the {@link IMinimax IMinimax} algorithm
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
public class Minimax extends AbsMinimax{

 	public Minimax(int depth, IEvalFunction f){
		super(depth, f);
	}

	@Override
	protected double maxValue(AbsTicTacToeAI state, int depthP, int currentAI) {
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
			double min = minValue(newState, depthP - 1, currentAI);
			if (min > v){
				v = min;
			}
		}

		return v;
	}

	@Override
	protected double minValue(AbsTicTacToeAI state, int depthP, int currentAI) {
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
		ArrayList< AbsMove> actions = AIUtils.computeActions(state.getField());

		for (int i = 0; i < actions.size(); i++){
			AbsTicTacToeAI newState = (AbsTicTacToeAI)state.deepClone();
			newState.move(actions.get(i));
			double max = maxValue(newState, depthP - 1, currentAI);
			if (max < v){
				v = max;
			}
		}

		return v;
	}

	@Override
	protected double callComputeMove(AbsTicTacToeAI state, int depthP, int currentAI){
		return minValue(state, depthP - 1, currentAI);
	}
}
