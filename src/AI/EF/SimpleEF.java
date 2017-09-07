package AI.EF;

import AI.AIUtils;
import ticTacToe.AbsTicTacToeAI;

/**
 * This class implements a simple function of evaluation
 *
 * @author Davide Rigoni
 */
public class SimpleEF extends AbsEvalFunction{

    @Override
    public double eval(AbsTicTacToeAI state, int currentAI) {
        char[][] field = state.getField();
        double result = 0;


        if(state.checkEnd()){
            result += AbsEvalFunction.WinScore(state,currentAI);
        } else{
            result += AbsEvalFunction.ExtraScoreSimple(field, currentAI);
            double valAI = AbsEvalFunction.SimpleAIAlgorithm(field,currentAI);
            result+= valAI;
        }

        return result;
    }
}
