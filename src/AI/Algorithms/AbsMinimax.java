package AI.Algorithms;


import AI.AIUtils;
import AI.EF.IEvalFunction;
import ticTacToe.AbsMove;
import ticTacToe.AbsTicTacToe;
import ticTacToe.TicTacToe;

import java.util.ArrayList;

/**
 * Created by davide on 05/09/17.
 */
public abstract class AbsMinimax extends AbsMinimaxStructure {
    /**
     * Constructor
     * @param depth Max limit in the recursion
     * @param f Eval function
     */
    public AbsMinimax(int depth, IEvalFunction f){
        super(depth,f);
    }

    protected abstract double maxValue(TicTacToe state, int depthP);

    protected abstract double minValue(TicTacToe state, int depthP);

    @Override
    public AbsMove computeMove(AbsTicTacToe state){
        //Start the time and sum 1 to node because of root
        this.res.startTime();
        this.res.addNode();

        ArrayList<AbsMove> actions = AIUtils.computeActions(state.getField());
        double max = Double.NEGATIVE_INFINITY;
        AbsMove bestMove = null;

        for (int i = 0; i < actions.size(); i++){
            TicTacToe newState = state.deepClone();
            newState.move(actions.get(i));
            double minValue = minValue(newState, this.getDepth() - 1);
            if (minValue > max){
                max = minValue;
                bestMove = actions.get(i);
            }
        }

        //End time and return the best move
        this.res.startTime();
        return bestMove;

    }
}
