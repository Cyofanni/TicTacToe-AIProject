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
                result+= length * 100; // this is sufficient with 3length + 3 + result for simple AI
            }else{
                result-= length * 100;
            }
        }

        return result;
    }

    /**
     * This AI return the number of rows, columns and diagonals in which the player can do tris
     * @param field
     * @param player
     * @return The number
     */
    public static double FreeSpace(char[][] field, int player){
        int n = field.length;
        int count = 0;
        int length = field.length;
        int otherPlayer = (player+1) %2;

        // Find repetitions in columns
        for(int row = 0; row < n; row ++){
            boolean find = false;
            for(int column = 0; column < n; column ++){
                if(field[row][column] == AbsTicTacToe.PLAYERSVALUE[otherPlayer] ||
                        field[row][column] == AbsTicTacToe.LOCKEDVALUE){
                    find = true;
                    break;
                }
            }
            if(!find){
                count++;
            }
        }

        // Find repetitions in rows
        for(int column = 0; column < n; column ++){
            boolean find = false;
            for(int row = 0; row < n; row ++){
                if(field[row][column] == AbsTicTacToe.PLAYERSVALUE[otherPlayer] ||
                        field[row][column] == AbsTicTacToe.LOCKEDVALUE){
                    find = true;
                    break;
                }
            }
            if(!find){
                count++;
            }
        }

        // Find repetitions in diagonals
        boolean find1 = false;
        for(int i = 0; i < n; i ++){
            if(field[i][i] == AbsTicTacToe.PLAYERSVALUE[otherPlayer] ||
                    field[i][i] == AbsTicTacToe.LOCKEDVALUE){
                find1 = true;
                break;
            }
        }
        if(!find1){
            count++;
        }

        boolean find2 = false;
        for(int i = 0; i < n; i ++){
            if(field[i][n - i - 1] == AbsTicTacToe.PLAYERSVALUE[otherPlayer] ||
                    field[i][n - i -1] == AbsTicTacToe.LOCKEDVALUE){
                find2 = true;
                break;
            }
        }
        if(!find2){
            count++;
        }

        return count * 10;
    }
}
