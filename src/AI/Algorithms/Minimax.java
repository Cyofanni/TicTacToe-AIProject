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
final public class Minimax extends AbsMinimax{

 	public Minimax(int depth, IEvalFunction f){
		super(depth, f);
	}

	@Override
	protected double maxValue(TicTacToe state, int depthP) {
		//Count the new node
		this.res.addNode();

		if (state.checkEnd() || depthP == 0){
			return this.getF().eval(state);
		}
		double v = Double.NEGATIVE_INFINITY;
		ArrayList<AbsMove> actions = AIUtils.computeActions(state.getField());

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

	@Override
	protected double minValue(TicTacToe state, int depthP) {
		//Count the new node
		this.res.addNode();

		if (state.checkEnd() || depthP == 0){
			return this.getF().eval(state);
		}
		double v = Double.POSITIVE_INFINITY;
		ArrayList< AbsMove> actions = AIUtils.computeActions(state.getField());

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
}
