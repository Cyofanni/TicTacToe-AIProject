package AI.EF;

import AI.AIUtils;
import ticTacToe.AbsTicTacToe;

/**
 * This class implements a simple function of evaluation
 *
 * @author Davide Rigoni
 */
public class SimpleEF implements IEvalFunction{

    @Override
    public double eval(AbsTicTacToe state) {
        return AIUtils.SimpleAIAlgorithm(state.getField(),1);
    }
}
