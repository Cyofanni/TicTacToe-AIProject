package AI.EF;

import AI.AIUtils;
import ticTacToe.AbsTicTacToeAI;

/**
 * This class implements an advance function of evaluation
 *
 * @author Davide Rigoni
 */
public class AdvanceEF  implements IEvalFunction{

    @Override
    public double eval(AbsTicTacToeAI state, int currentAI) {
        char[][] field = state.getField();
        int length = field.length;
        double result = 0;
        int currentPlayer = state.getActivePlayer();
        int otherPlayer = (currentPlayer + 1) % 2;
        int otherAI = (currentAI + 1) % 2;

        //Give an extra score to the center cell
        if(length%2 == 1){
            int index = (length/2);
            if(field[index][index] == AbsTicTacToeAI.PLAYERSVALUE[currentAI]){
                result = result + length;
            } else{
                if(field[index][index] == AbsTicTacToeAI.PLAYERSVALUE[otherAI]){
                    result = result - length;
                }
            }
        }

        if(field[0][0] == AbsTicTacToeAI.PLAYERSVALUE[currentAI]){
            result = result + 0.25;
        }else{
            if(field[0][0] == AbsTicTacToeAI.PLAYERSVALUE[otherAI])
                result = result - 0.25;
        }
        if(field[length-1][0] == AbsTicTacToeAI.PLAYERSVALUE[currentAI]){
            result = result + 0.25;
        }else{
            if(field[length-1][0] == AbsTicTacToeAI.PLAYERSVALUE[otherAI])
                result = result - 0.25;
        }
        if(field[0][length-1] == AbsTicTacToeAI.PLAYERSVALUE[currentAI]){
            result = result + 0.25;
        }else{
            if(field[0][length-1] == AbsTicTacToeAI.PLAYERSVALUE[otherAI])
                result = result - 0.25;
        }
        if(field[length-1][length-1] == AbsTicTacToeAI.PLAYERSVALUE[currentAI]){
            result = result + 0.25;
        }else{
            if (field[length-1][length-1] == AbsTicTacToeAI.PLAYERSVALUE[otherAI])
                result = result - 0.25;
        }

        if(state.checkEnd()){
            if(state.checkWinner()){
                if(currentPlayer == currentAI) {
                    return length * 4 + result; // this is sufficient with 3length + 3 + result
                }else{
                    return -(length * 4) + result;
                }
            } else{
                return 0;
            }
        } else{

            //Evaluate the fields from both the AI and player
            double valAI = AIUtils.SimpleAIAlgorithm(field,currentAI);
            double valPlayer = AIUtils.SimpleAIAlgorithm(field,otherAI);

            //Return the result
            //return result + valAI - (valPlayer / (length - valPlayer));
            return result + valAI - valPlayer ;
        }


    }
}
