package AI.algorithms;


import AI.EF.IEvalFunction;
import ticTacToe.*;

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

    protected abstract double maxValue(AbsTicTacToeAI state, int depthP, int currentAI);

    protected abstract double minValue(AbsTicTacToeAI state, int depthP, int currentAI);

}
