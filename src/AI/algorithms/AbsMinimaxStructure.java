package AI.algorithms;

import AI.AIUtils;
import AI.EF.IEvalFunction;
import AI.MatrixOperations;
import ticTacToe.AbsMove;
import ticTacToe.AbsResult;
import ticTacToe.AbsTicTacToeAI;
import ticTacToe.Result;

import java.util.ArrayList;

/**
 * Created by davide on 05/09/17.
 */
public abstract class AbsMinimaxStructure implements IMinimax {
    private int depth;
    private IEvalFunction f;
    protected Result res;
    protected MatrixOperations mop;


    /**
     * Constructor
     * @param depth Max limit in the recursion
     * @param f Eval function
     */
    public AbsMinimaxStructure(int depth, IEvalFunction f){
        this.depth = depth;
        this.f = f;
     }

    @Override
    public AbsMove computeMove(AbsTicTacToeAI state, int currentAI){
        //Start the time and sum 1 to node because of root
        this.mop = new MatrixOperations();
        mop.add(0,state.getField());
        this.res = new Result();
        this.res.startTime();
        this.res.addNode();

        ArrayList<AbsMove> actions = AIUtils.computeActions(state.getField());
        double v = Double.NEGATIVE_INFINITY;
        AbsMove bestMove = null;

        for (int i = 0; i < actions.size(); i++){
            AbsTicTacToeAI newState = (AbsTicTacToeAI)state.deepClone();
            newState.move(actions.get(i));
            double min = callComputeMove(newState, this.getDepth(), currentAI);
            if (min > v){
                v = min;
                bestMove = actions.get(i);
            }
        }

        //End time and return the best move
        this.res.endTime();
        return bestMove;
    }

    /**
     * This method start the different minValue functions of the child
     * @param state
     * @param depthP
     * @param currentAI
     * @return The result of the MinMax
     */
    protected abstract double callComputeMove(AbsTicTacToeAI state, int depthP, int currentAI);

    //-----------------------------------------------------------------
    //------------------------ GETTERS --------------------------------
    //-----------------------------------------------------------------
    public int getDepth(){
        return depth;
    }

    public IEvalFunction getF(){
        return f;
    }

    public AbsResult getResult(){
        return res;
    }
}
