package AI.Algorithms;

import AI.AIUtils;
import AI.EF.IEvalFunction;
import ticTacToe.AbsTicTacToe;
import ticTacToe.TicTacToe;
import ticTacToe.AbsMove;
import java.util.ArrayList;


/**
 * This class implements the {@link IMinimax IMinimax} algorithm
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */

final public class MinimaxRot implements IMinimax{
	int depth;
	IEvalFunction f;              //reference to evaluation function interface


 	public MinimaxRot(int depth, IEvalFunction f){
		this.depth = depth;
		this.f = f;
	}

	private double maxValue(TicTacToe state, int depthP) {
		if (state.checkEnd() || depthP == 0){
			return f.eval(state);
		}
		double v = Double.MIN_VALUE;
		ArrayList<AbsMove> actions = AIUtils.computeActions(state.getField());    //create an array with the legal action from the state
											  //of the current recursive call
		//TODO: add checks for redundant field configurations (rotations)
		for (int i = 0; i < actions.size(); i++){
			TicTacToe newState = state.deepClone();
			newState.move(actions.get(i));
			/* 1) Compute 90-degree right rotation 
		           2) Compute 90-degree left rotation
			   3) Compute 128-degree rotation
			   Finally look for a match of this rotations against 
			   stored states
			*/


                        double min = minValue(newState, depthP - 1);
			if (min > v){
				v = min;
			}
		}

		return v;
	}

	private double minValue(TicTacToe state, int depthP) {
		if (state.checkEnd() || depthP == 0){
			return f.eval(state);
		}
		double v = Double.MAX_VALUE;
		ArrayList< AbsMove> actions = AIUtils.computeActions(state.getField());

		for (int i = 0; i < actions.size(); i++){
			TicTacToe newState = state.deepClone();
			newState.move(actions.get(i));

			/* 1) Compute 90-degree right rotation 
		           2) Compute 90-degree left rotation
			   3) Compute 128-degree rotation
			   Finally look for a match of this rotations against 
			   stored states
			*/

			double max = maxValue(newState, depthP - 1);
			if (max < v){
				v = max;
			}
		}

		return v;
	}

	@Override
	public AbsMove computeMove(AbsTicTacToe state){
		ArrayList<AbsMove> actions = AIUtils.computeActions(state.getField());
		double max = Integer.MIN_VALUE;
		AbsMove bestMove = null;
		
		for (int i = 0; i < actions.size(); i++){
			TicTacToe newState = state.deepClone();
			newState.move(actions.get(i));
			double minValue = minValue(newState, depth);
			if (minValue > max){
				max = minValue;
				bestMove = actions.get(i);
			}
		}

		return bestMove;
	}
}
