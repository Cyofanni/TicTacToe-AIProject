package AI.EF;

import AI.AIUtils;
import ticTacToe.AbsTicTacToeAI;

/**
 * This class implements a simple function of evaluation
 *
 * @author Davide Rigoni
 */
public class SimpleEF implements IEvalFunction{

    @Override
    public double eval(AbsTicTacToeAI state, int currentAI) {
        char[][] field = state.getField();
        if(state.checkEnd()){
            if(state.checkWinner()){
                if(state.getActivePlayer() == currentAI) {
                    return field.length;
                }else{
                    return -field.length;
                }
            } else{
                return 0;
            }
        } else{
            double valAI = AIUtils.SimpleAIAlgorithm(field,currentAI);
            return valAI;
        }
    }
}
