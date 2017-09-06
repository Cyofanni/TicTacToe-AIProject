package AI.algorithms;

import AI.AIUtils;
import AI.EF.IEvalFunction;
import AI.MatrixOperations;
import ticTacToe.AbsTicTacToe;
import ticTacToe.TicTacToe;
import ticTacToe.AbsMove;
import java.util.ArrayList;


/**
 * This class implements the {@link IMinimax IMinimax} algorithm
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */

final public class MinimaxRot extends AbsMinimax{

	//Create the object used to save the fields
	MatrixOperations mop = new MatrixOperations();

 	public MinimaxRot(int depth, IEvalFunction f){
		super(depth,f);
	}

	@Override
	protected double maxValue(AbsTicTacToe state, int depthP) {
		//Statistics: Count the new node
		this.res.addNode();

		//Check the end of the game or the max depth
		if (state.checkEnd() || depthP == 0){
			double fvalue = this.getF().eval(state);

			//Statistics: Set best score and nearest level
			res.setBestScore(fvalue);
			res.setNearestLevel(this.getDepth() - depthP -1);
			return fvalue;
		}

		mop.add(state.getField());
		double v = Double.NEGATIVE_INFINITY;
		ArrayList<AbsMove> actions = AIUtils.computeActions(state.getField());    //create an array with the legal action from the state

		for (int i = 0; i < actions.size(); i++){
			AbsTicTacToe newState = state.deepClone();
			newState.move(actions.get(i));
			char[][] currFieldConf = newState.getField();
			boolean matchFound = mop.checkExistence(currFieldConf);

			//recursive call only if the field is rotation-independently new
			if (matchFound == false){
                        	double min = minValue(newState, depthP - 1);
				if (min > v){
				   v = min;
				}
			}
		}

		return v;
	}

	@Override
	protected double minValue(AbsTicTacToe state, int depthP) {
		//Statistics: Count the new node
		this.res.addNode();

		//Check the end of the game or the max depth
		if (state.checkEnd() || depthP == 0){
			double fvalue = this.getF().eval(state);

			//Statistics: Set best score and nearest level
			res.setBestScore(fvalue);
			res.setNearestLevel(this.getDepth() - depthP -1);
			return fvalue;
		}

		mop.add(state.getField());
		double v = Double.POSITIVE_INFINITY;
		ArrayList< AbsMove> actions = AIUtils.computeActions(state.getField());

		for (int i = 0; i < actions.size(); i++){
			AbsTicTacToe newState = state.deepClone();
			newState.move(actions.get(i));
			char[][] currFieldConf = newState.getField();   //get the field from the new state
			boolean matchFound = mop.checkExistence(currFieldConf);

			//recursive call only if the field is rotation-independently new
			if (matchFound == false){
				double max = maxValue(newState, depthP - 1);
			
				if (max < v){
				   v = max;
				}
			}
		}

		return v;
	}
}
