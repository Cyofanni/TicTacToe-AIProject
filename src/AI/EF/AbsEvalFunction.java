package AI.EF;

import ticTacToe.AbsTicTacToe;
import ticTacToe.AbsTicTacToeAI;

/**
 * This class implements a simple function of evaluation
 *
 * @author Davide Rigoni
 */
public abstract class AbsEvalFunction implements IEvalFunction{

    /**
     * This method check how near the player is to the win of the game
     * @param field
     * @param player
     * @return Points of the player
     */
    public static double SimpleAIAlgorithm(char[][] field, int player){
        double maxVal = 0;
        int n = field.length;

        // Find repetitions in columns
        for(int row = 0; row < n; row ++){
            int cColumn = 0;
            for(int column = 0; column < n; column ++){
                if(field[row][column] == AbsTicTacToe.PLAYERSVALUE[player]){
                    cColumn ++;
                } else{
                    if(field[row][column] != AbsTicTacToe.BLANKVALUE) {
                        cColumn = 0;
                        break;
                    }
                }
            }
            if(cColumn > maxVal){
                maxVal = cColumn;
            }
        }

        // Find repetitions in rows
        for(int column = 0; column < n; column ++){
            int cRow = 0;
            for(int row = 0; row < n; row ++){
                if(field[row][column] == AbsTicTacToe.PLAYERSVALUE[player]){
                    cRow ++;
                } else{
                    if(field[row][column] != AbsTicTacToe.BLANKVALUE) {
                        cRow = 0;
                        break;
                    }
                }
            }
            if(cRow > maxVal){
                maxVal = cRow;
            }
        }

        // Find repetitions in diagonals
        int cDiagonal1 = 0;
        for(int i = 0; i < n; i ++){
            if(field[i][i] == AbsTicTacToe.PLAYERSVALUE[player]){
                cDiagonal1 ++;
            }else{
                if(field[i][i] != AbsTicTacToe.BLANKVALUE) {
                    cDiagonal1 = 0;
                    break;
                }
            }
        }
        if(cDiagonal1 > maxVal){
            maxVal = cDiagonal1;
        }

        int cDiagonal2 = 0;
        for(int i = 0; i < n; i ++){
            if(field[i][n-i-1] == AbsTicTacToe.PLAYERSVALUE[player]){
                cDiagonal2 ++;
            } else{
                if(field[i][n-i-1] != AbsTicTacToe.BLANKVALUE) {
                    cDiagonal2 = 0;
                    break;
                }
            }
        }
        if(cDiagonal2 > maxVal){
            maxVal = cDiagonal2;
        }

        return maxVal;
    }

    /**
     * This method returns an extra score evaluating the position of the player's points
     * considering first the center and after the corners
     * @param field
     * @param currentAI
     * @return The extra score
     */
    public static double ExtraScore(char[][] field, int currentAI) {
        int length = field.length;
        double result = 0;
        int otherAI = (currentAI + 1) % 2;
        double score = 0.25;

        //Give an extra score to the center cell
        if (length % 2 == 1) {
            int index = (length / 2);
            if (field[index][index] == AbsTicTacToe.PLAYERSVALUE[currentAI]) {
                result +=  length;
            } else {
                if (field[index][index] == AbsTicTacToe.PLAYERSVALUE[otherAI]) {
                    result -= length;
                }
            }
        }

        if (field[0][0] == AbsTicTacToe.PLAYERSVALUE[currentAI]) {
            result +=  score;
        } else {
            if (field[0][0] == AbsTicTacToe.PLAYERSVALUE[otherAI])
                result -= score;
        }
        if (field[length - 1][0] == AbsTicTacToe.PLAYERSVALUE[currentAI]) {
            result += score;
        } else {
            if (field[length - 1][0] == AbsTicTacToe.PLAYERSVALUE[otherAI])
                result -= score;
        }
        if (field[0][length - 1] == AbsTicTacToe.PLAYERSVALUE[currentAI]) {
            result += score;
        } else {
            if (field[0][length - 1] == AbsTicTacToe.PLAYERSVALUE[otherAI])
                result -= score;
        }
        if (field[length - 1][length - 1] == AbsTicTacToe.PLAYERSVALUE[currentAI]) {
            result += score;
        } else {
            if (field[length - 1][length - 1] == AbsTicTacToe.PLAYERSVALUE[otherAI])
                result -= score;
        }
        return result;
    }

    /**
     * This method returns an extra score evaluating the position of the player's points
     * considering first the center and after the corners
     * @param field
     * @param currentAI
     * @return The extra score
     */
    public static double ExtraScoreSimple(char[][] field, int currentAI) {
        int length = field.length;
        double result = 0;
        double score = 0.25;

        //Give an extra score to the center cell
        if (length % 2 == 1) {
            int index = (length / 2);
            if (field[index][index] == AbsTicTacToe.PLAYERSVALUE[currentAI]) {
                result +=  length;
            }
        }

        if (field[0][0] == AbsTicTacToe.PLAYERSVALUE[currentAI]) {
            result +=  score;
        }
        if (field[length - 1][0] == AbsTicTacToe.PLAYERSVALUE[currentAI]) {
            result += score;
        }
        if (field[0][length - 1] == AbsTicTacToe.PLAYERSVALUE[currentAI]) {
            result += score;
        }
        if (field[length - 1][length - 1] == AbsTicTacToe.PLAYERSVALUE[currentAI]) {
            result += score;
        }
        return result;
    }

    /**
     * This method returns the the core of the winner
     * @param state
     * @param currentAI
     * @return The score
     */
    public static double WinScore(AbsTicTacToeAI state, int currentAI) {
        char[][] field = state.getField();
        int length = field.length;
        double result = 0;

        if(state.checkWinner()){
            if(state.getActivePlayer() == currentAI) {
                result+= length * 4; // this is sufficient with 3length + 3 + result
            }else{
                result-= length * 4;
            }
        }

        return result;
    }
}
