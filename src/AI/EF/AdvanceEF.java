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
        char[][] field = state.getField();
        int length = field.length;
        int plus = 0;

        if(state.checkEnd()){
            if(state.checkWinner()){
                if(state.getActivePlayer() == 1) {
                    return length * 2;
                }else{
                    return -(length * 2);
                }
            } else{
                return 0;
            }
        } else{

            if(length%2 == 1){
                int index = (length/2);
                if(field[index][index] == AbsTicTacToe.PLAYERSVALUE[1]){
                    plus = length;
                } else{
                    if(field[index][index] == AbsTicTacToe.PLAYERSVALUE[0]){
                        plus = -length;
                    } else{
                        plus = 0;
                    }
                }
            }

            double valAI = AIUtils.SimpleAIAlgorithm(field,1);
            double valPlayer = AIUtils.SimpleAIAlgorithm(field,0);
            return valAI - valPlayer + plus;
        }


    }
}
