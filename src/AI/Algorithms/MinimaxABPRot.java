package AI.Algorithms;

import AI.AIUtils;
import AI.EF.IEvalFunction;
import ticTacToe.AbsTicTacToe;
import ticTacToe.TicTacToe;
import ticTacToe.AbsMove;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This class implements the {@link IMinimax IMinimax} algorithm
 * with alpha beta pruning
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
final public class MinimaxABPRot extends AbsMinimaxABP{

	//a private array (ArrayList of char[][]) could store the configurations
	//right initialization?
	private ArrayList<char[][]> storedStates = new ArrayList<char[][]>();


 	public MinimaxABPRot(int depth, IEvalFunction f){
		super(depth,f);
	}

	/*method for matrix transposition, used to check rotations*/
        private char[][] transposeMatrix(char [][] matr){
		int size = matr[0].length;
	        char[][] transMatrix = new char[size][];
		
		/*allocate memory for each row*/
		for (int i = 0; i < size; i++){
			transMatrix[i] = new char[size];
		}
  
		for (int i = 0; i < size; i++){
		    for (int j = 0; j < size; j++){
			transMatrix[i][j] = matr[j][i];
		    }
		}     

		return transMatrix;	
	}

	/*method for reversing matrix' rows, used to check rotations*/
        private char[][] reverseRows(char [][] matr){
		int size = matr[0].length;
		char[][] revMatrix = new char[size][];
		
		/*allocate memory for each row*/
		for (int i = 0; i < size; i++){
			revMatrix[i] = new char[size];
		}

		int countRightOrder = 0;
		for (int i = size - 1; i >= 0; i--){
		    for (int j = 0; j < size; j++){
			revMatrix[countRightOrder][j] = matr[i][j];
		    }
		    countRightOrder++;
		} 	

		return revMatrix;
	}

	/*method for reversing matrix' columns, used to check rotations*/
	private char[][] reverseCols(char [][] matr){
		int size = matr[0].length;			
		char[][] revMatrix = new char[size][];

		/*allocate memory for each row*/
		for (int i = 0; i < size; i++){
			revMatrix[i] = new char[size];
		}

		int countRightOrder = 0;
		for (int i = size - 1; i >= 0; i--){
		    for (int j = 0; j < size; j++){
			revMatrix[j][countRightOrder] = matr[j][i] ;
		    }
		    countRightOrder++;
		} 

		return revMatrix;
	}

	/*method for checking equality between two char[][]*/
	private boolean checkFieldEq(char[][] field1, char[][] field2){
		boolean ret = true;
		int size1 = field1[0].length;
		int size2 = field2[0].length;

		if (size1 != size2){
		    return false;
		}

		for (int i = 0; i < size1 && ret == true; i++){
		    for (int j = 0; j < size1; j++){
			if (field1[i][j] != field2[i][j]){
				ret = false;
			}
		    }
		} 

		return ret;
	}

	@Override
	protected double maxValue(TicTacToe state, double alpha, double beta, int depthP) {
		if (state.checkEnd() || depthP == 0)
			return this.getF().eval(state);

		/*right place?*/
		storedStates.add(state.getField());

		double v = Double.NEGATIVE_INFINITY;
		ArrayList<AbsMove> actions = AIUtils.
				computeActions(state.getField());
		for (int i = 0; i < actions.size(); i++){
			TicTacToe newState = state.deepClone();
			newState.move(actions.get(i));

			/* 1) Compute 90-degree right rotation (transpose + reverse columns)
		           2) Compute 90-degree left rotation (transpose + reverse rows)
			   3) Compute 180-degree rotation (reverse rows + reverse columns)
			   Finally look for a match of this rotations against 
			   stored states
			*/
			char[][] currFieldConf = newState.getField();   //get the field from the new state
			
			/***1) Compute 90-degree right rotation (transpose + reverse columns) of 'currFieldConf'***/
			char[][] rotated90_r = transposeMatrix(currFieldConf);
			rotated90_r = reverseCols(rotated90_r);
			/***********************************************************************/						
						
			/***1) Compute 90-degree left rotation (transpose + reverse rows) of 'currFieldConf'***/
			char[][] rotated90_l = transposeMatrix(currFieldConf);
			rotated90_l = reverseRows(rotated90_l);
			/***********************************************************************/			
			
			/***1) Compute 180-degree rotation (reverse rows + reverse columns) of 'currFieldConf'***/
			char[][] rotated180 = reverseRows(currFieldConf);
			rotated180 = reverseCols(rotated180);
			/***********************************************************************/

			boolean matchFound = false;
			for (int j = 0; j < storedStates.size() && matchFound == false; j++){
				//use private method 'checkFieldEq' to check equality of 2 char[][]
				if (checkFieldEq(storedStates.get(j), rotated90_r) || checkFieldEq(storedStates.get(j), rotated90_l) || 
					checkFieldEq(storedStates.get(j), rotated180)){
					matchFound = true;
				}
			}		

			if (matchFound == false){
            			double min = minValue(newState,alpha, beta, depthP - 1);
				if (min > v)
					v = min;

				if (v >= beta)
					return v;
				if (v > alpha)
					alpha = v;
			}
		}

		return v;
	}

	@Override
	protected double minValue(TicTacToe state, double alpha, double beta, int depthP) {
		if (state.checkEnd() || depthP == 0)
			return this.getF().eval(state);

		/*right place?*/
		storedStates.add(state.getField());

		double v = Double.POSITIVE_INFINITY;
		ArrayList< AbsMove> actions = AIUtils.
				computeActions(state.getField());
		for (int i = 0; i < actions.size(); i++){
			TicTacToe newState = state.deepClone();
			newState.move(actions.get(i));

			/* 1) Compute 90-degree right rotation (transpose + reverse columns)
		           2) Compute 90-degree left rotation (transpose + reverse rows)
			   3) Compute 180-degree rotation (reverse rows + reverse columns)
			   Finally look for a match of this rotations against 
			   stored states
			*/
			char[][] currFieldConf = newState.getField();   //get the field from the new state
			
			/***1) Compute 90-degree right rotation (transpose + reverse columns) of 'currFieldConf'***/
			char[][] rotated90_r = transposeMatrix(currFieldConf);
			rotated90_r = reverseCols(rotated90_r);
			/***********************************************************************/						
						
			/***1) Compute 90-degree left rotation (transpose + reverse rows) of 'currFieldConf'***/
			char[][] rotated90_l = transposeMatrix(currFieldConf);
			rotated90_l = reverseRows(rotated90_l);
			/***********************************************************************/			
			
			/***1) Compute 180-degree rotation (reverse rows + reverse columns) of 'currFieldConf'***/
			char[][] rotated180 = reverseRows(currFieldConf);
			rotated180 = reverseCols(rotated180);
			/***********************************************************************/

			boolean matchFound = false;
			for (int j = 0; j < storedStates.size() && matchFound == false; j++){
				//use private method 'checkFieldEq' to check equality of 2 char[][]
				if (checkFieldEq(storedStates.get(j), rotated90_r) || checkFieldEq(storedStates.get(j), rotated90_l) || 
					checkFieldEq(storedStates.get(j), rotated180)){
					matchFound = true;
				}
			}		

			if (matchFound == false){
				double max = maxValue(newState, alpha, beta, depthP - 1);
				if (max < v){
					v = max;
				}	

				if (v <= alpha)
					return v;
				if (v < beta)
					beta = v;
			}
		}

		return v;
	}
}
