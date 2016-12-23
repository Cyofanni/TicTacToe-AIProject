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
        char[][] field = state.getField();
        if(state.checkEnd()){
            if(state.checkWinner()){
                if(state.getActivePlayer() == 1) {
                    return field.length;
                }else{
                    return -field.length;
                }
            } else{
                return 0;
            }
        } else{
            double valAI = AIUtils.SimpleAIAlgorithm(field,1);
            return valAI;
        }
    }
}
