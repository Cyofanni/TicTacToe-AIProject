package AI.Algorithms;

import AI.EF.IEvalFunction;
import ticTacToe.AbsResult;
import ticTacToe.Result;

/**
 * Created by davide on 05/09/17.
 */
public abstract class AbsMinimaxStructure implements IMinimax {
    private int depth;
    private IEvalFunction f;
    protected Result res;


    /**
     * Constructor
     * @param depth Max limit in the recursion
     * @param f Eval function
     */
    public AbsMinimaxStructure(int depth, IEvalFunction f){
        this.depth = depth;
        this.f = f;
    }

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
