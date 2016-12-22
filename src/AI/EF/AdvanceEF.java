package AI.EF;

import AI.AIUtils;
import ticTacToe.AbsTicTacToe;

/**
 * This class implements an advance function of evaluation
 *
 * @author Davide Rigoni
 */
public class AdvanceEF  implements IEvalFunction{

    @Override
    public double eval(AbsTicTacToe state) {
        double valAI = AIUtils.SimpleAIAlgorithm(state.getField(),1);
        double valPlayer = AIUtils.SimpleAIAlgorithm(state.getField(),0);
        return valAI - valPlayer;
    }
}
