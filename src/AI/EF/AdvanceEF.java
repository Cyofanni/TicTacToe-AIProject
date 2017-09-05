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
        double result = 0;

        //Give an extra score to the center cell
        if(length%2 == 1){
            int index = (length/2);
            if(field[index][index] == AbsTicTacToe.PLAYERSVALUE[1]){
                result = result + length;
            } else{
                if(field[index][index] == AbsTicTacToe.PLAYERSVALUE[0]){
                    result = result - length;
                }
            }
        }

        if(field[0][0] == AbsTicTacToe.PLAYERSVALUE[1]){
            result = result + 0.25;
        }else{
            if(field[0][0] == AbsTicTacToe.PLAYERSVALUE[0])
                result = result - 0.25;
        }
        if(field[length-1][0] == AbsTicTacToe.PLAYERSVALUE[1]){
            result = result + 0.25;
        }else{
            if(field[length-1][0] == AbsTicTacToe.PLAYERSVALUE[0])
                result = result - 0.25;
        }
        if(field[0][length-1] == AbsTicTacToe.PLAYERSVALUE[1]){
            result = result + 0.25;
        }else{
            if(field[0][length-1] == AbsTicTacToe.PLAYERSVALUE[0])
                result = result - 0.25;
        }
        if(field[length-1][length-1] == AbsTicTacToe.PLAYERSVALUE[1]){
            result = result + 0.25;
        }else{
            if (field[length-1][length-1] == AbsTicTacToe.PLAYERSVALUE[0])
                result = result - 0.25;
        }

        if(state.checkEnd()){
            if(state.checkWinner()){
                if(state.getActivePlayer() == 1) {
                    return length * 10 + result;
                }else{
                    return -(length * 10) + result;
                }
            } else{
                return 0;
            }
        } else{

            //Evaluate the fields from both the AI and player
            double valAI = AIUtils.SimpleAIAlgorithm(field,1);
            double valPlayer = AIUtils.SimpleAIAlgorithm(field,0);

            //Return the result
            return result + valAI - valPlayer ;
        }


    }
}
