package AI;

import ticTacToe.AbsTicTacToe;
import ticTacToe.AbsMove;
import ticTacToe.Move;
import java.util.ArrayList;

/**
 * This class contains functions useful for the AI functions and algorithms
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
public class AIUtils {

	/**
	 * This method find all the possible moves
	 * @param board Board of the game
	 * @return An ArrayList of move
	 */
	public static ArrayList<AbsMove> computeActions(char[][] board){
		int n = board.length;
		ArrayList<AbsMove> resActions = new ArrayList<AbsMove>();
		for (int i = 0; i < n; i++){
			for (int j = 0; j < n; j++){
				if (board[i][j] == AbsTicTacToe.BLANKVALUE){
					resActions.add(new Move(i, j));
				}
			}
		}
		return resActions;
	}

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
}
