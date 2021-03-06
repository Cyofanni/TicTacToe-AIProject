package AI.algorithms;

import AI.AIUtils;
import AI.EF.IEvalFunction;
import AI.MatrixOperations;
import ticTacToe.AbsTicTacToe;
import ticTacToe.AbsTicTacToeAI;
import ticTacToe.TicTacToe;
import ticTacToe.AbsMove;
import java.util.ArrayList;


/**
 * This class implements the {@link IMinimax IMinimax} algorithm
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */

public class MinimaxRot extends AbsMinimax{

 	public MinimaxRot(int depth, IEvalFunction f){
		super(depth,f);
	}

	@Override
	protected double maxValue(AbsTicTacToeAI state, int depthP, int currentAI) {
		//Statistics: Count the new node
		this.res.addNode();
		int level = this.getDepth() - depthP;
		mop.add(level, state.getField());

		//Check the end of the game or the max depth
		if (state.checkEnd() || depthP == 0){
			double fvalue = this.getF().eval(state, currentAI);

			//Statistics: Set best score and nearest level
			res.setBestScore(fvalue);
			res.setNearestLevel(level);
			return fvalue;
		}

		double v = Double.NEGATIVE_INFINITY;
		ArrayList<AbsMove> actions = AIUtils.computeActions(state.getField());    //create an array with the legal action from the state

		for (int i = 0; i < actions.size(); i++){
			AbsTicTacToeAI newState = (AbsTicTacToeAI)state.deepClone();
			newState.move(actions.get(i));
			char[][] currFieldConf = newState.getField();
			boolean matchFound = mop.checkExistence(level + 1, currFieldConf);

			//recursive call only if the field is rotation-independently new
			if (matchFound == false){
				double min = minValue(newState, depthP - 1, currentAI);
				if (min > v){
				   v = min;
				}
			}
		}

		return v;
	}

	@Override
	protected double minValue(AbsTicTacToeAI state, int depthP, int currentAI) {
		//Statistics: Count the new node
		this.res.addNode();
		int level = this.getDepth() - depthP;
		mop.add(level, state.getField());

		//Check the end of the game or the max depth
		if (state.checkEnd() || depthP == 0){
			double fvalue = this.getF().eval(state, currentAI);

			//Statistics: Set best score and nearest level
			res.setBestScore(fvalue);
			res.setNearestLevel(level);
			return fvalue;
		}


		double v = Double.POSITIVE_INFINITY;
		ArrayList< AbsMove> actions = AIUtils.computeActions(state.getField());

		for (int i = 0; i < actions.size(); i++){
			AbsTicTacToeAI newState = (AbsTicTacToeAI)state.deepClone();
			newState.move(actions.get(i));
			char[][] currFieldConf = newState.getField();   //get the field from the new state
			boolean matchFound = mop.checkExistence(level + 1, currFieldConf);

			//recursive call only if the field is rotation-independently new
			if (matchFound == false){
				double max = maxValue(newState, depthP - 1, currentAI);
			
				if (max < v){
				   v = max;
				}
			}
			else{
				int a = 3;
			}
		}

		return v;
	}

	@Override
	protected double callComputeMove(AbsTicTacToeAI state, int depthP, int currentAI){
		char[][] currFieldConf = state.getField();   //get the field from the new state
		int level = this.getDepth() - depthP;
		boolean matchFound = mop.checkExistence(level + 1, currFieldConf);
		if (!matchFound) {
			return minValue(state, depthP - 1, currentAI);
		} else{
			return Double.NEGATIVE_INFINITY;
		}

	}
}
