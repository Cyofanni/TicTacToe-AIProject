package AI.EF;

import AI.AIUtils;
import ticTacToe.AbsTicTacToeAI;

/**
 * This class implements an advance function of evaluation
 *
 * @author Davide Rigoni
 */
public class AdvanceEF  extends AbsEvalFunction{

    /*@Override
    public double eval(AbsTicTacToeAI state, int currentAI) {
        char[][] field = state.getField();
        int otherAI = (currentAI + 1) % 2;
        double result = 0;


        if(state.checkEnd()){
            result += AbsEvalFunction.WinScore(state,currentAI);
        } else{
            result += AbsEvalFunction.ExtraScore(field,currentAI);
            //Evaluate the fields from both the AI and player
            double valAI = AbsEvalFunction.SimpleAIAlgorithm(field,currentAI);
            double valPlayer = AbsEvalFunction.SimpleAIAlgorithm(field,otherAI);

            //Return the result
            //return result + valAI - (valPlayer / (length - valPlayer));
            result += valAI - valPlayer ;
        }

        return result;
    }*/

    @Override
    public double eval(AbsTicTacToeAI state, int currentAI) {
        char[][] field = state.getField();
        int otherAI = (currentAI + 1) % 2;
        double result = 0;


        if(state.checkEnd()){
            result += AbsEvalFunction.WinScore(state,currentAI);
        } else{
            //Evaluate the fields from both the AI and player
            double valAI = AbsEvalFunction.FreeSpace(field,currentAI);
            double valPlayer = AbsEvalFunction.FreeSpace(field,otherAI);
            result += valAI - valPlayer ;
        }

        return result;
    }
}
