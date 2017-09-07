package AI.algorithms;


import AI.AIUtils;
import AI.EF.IEvalFunction;
import AI.MatrixOperations;
import ticTacToe.*;

import java.util.ArrayList;

/**
 * Created by davide on 05/09/17.
 */
public abstract class AbsMinimaxABP extends AbsMinimaxStructure {
    /**
     * Constructor
     * @param depth Max limit in the recursion
     * @param f Eval function
     */
    public AbsMinimaxABP(int depth, IEvalFunction f){
        super(depth,f);
    }

    protected  abstract double maxValue(AbsTicTacToeAI state, double alpha, double beta, int depthP, int currentAI);

    protected abstract double minValue(AbsTicTacToeAI state, double alpha, double beta, int depthP, int currentAI);

    @Override
    public AbsMove computeMove(AbsTicTacToeAI state, int currentAI){
        //Start the time and sum 1 to node because of root
        this.mop = new MatrixOperations();
        this.res = new Result();
        this.res.startTime();
        this.res.addNode();

        ArrayList<AbsMove> actions = AIUtils.computeActions(state.getField());
        double v = Double.NEGATIVE_INFINITY;
        AbsMove bestMove = null;

        for (int i = 0; i < actions.size(); i++){
            AbsTicTacToeAI newState = (AbsTicTacToeAI)state.deepClone();
            newState.move(actions.get(i));
            double min = minValue(newState, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, this.getDepth() - 1, currentAI);
            if (min > v){
                v = min;
                bestMove = actions.get(i);
            }
        }

        //End time and return the best move
        this.res.endTime();
        return bestMove;
    }
}
