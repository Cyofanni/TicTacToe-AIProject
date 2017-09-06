package AI.algorithms;


import AI.AIUtils;
import AI.EF.IEvalFunction;
import ticTacToe.AbsMove;
import ticTacToe.AbsTicTacToe;
import ticTacToe.Result;
import ticTacToe.TicTacToe;

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

    protected  abstract double maxValue(TicTacToe state, double alpha, double beta, int depthP);

    protected abstract double minValue(TicTacToe state, double alpha, double beta, int depthP);

    @Override
    public AbsMove computeMove(AbsTicTacToe state){
        //Start the time and sum 1 to node because of root
        this.res.startTime();
        this.res.addNode();

        ArrayList<AbsMove> actions = AIUtils.computeActions(state.getField());
        double v = Double.NEGATIVE_INFINITY;
        AbsMove bestMove = null;

        for (int i = 0; i < actions.size(); i++){
            TicTacToe newState = state.deepClone();
            newState.move(actions.get(i));
            double min = minValue(newState, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, this.getDepth() - 1);
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
