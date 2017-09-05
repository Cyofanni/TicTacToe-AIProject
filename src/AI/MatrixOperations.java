package AI;

import java.util.ArrayList;

/**
 * Created by davide on 05/09/17.
 */
public class MatrixOperations {

    //a private array (ArrayList of char[][]) could store the configurations
    private ArrayList<char[][]> storedStates = new ArrayList<char[][]>();


    //Allow access to the ArrayList
    public char[][] get(int i){
        return storedStates.get(i);
    }

    //Add a char[][] to the ArrayList
    public void add(char[][] field){
        storedStates.add(field);
    }

    //Check if already exist
    public boolean checkExistence(char[][] field){
        /***1) Compute 90-degree right rotation (transpose + reverse columns) of 'currFieldConf'***/
        char[][] rotated90_r = transposeMatrix(field);
        rotated90_r = reverseCols(rotated90_r);
        /***********************************************************************/

        /***2) Compute 90-degree left rotation (transpose + reverse rows) of 'currFieldConf'***/
        char[][] rotated90_l = transposeMatrix(field);
        rotated90_l = reverseRows(rotated90_l);
        /***********************************************************************/

        /***3) Compute 180-degree rotation (reverse rows + reverse columns) of 'currFieldConf'***/
        char[][] rotated180 = reverseRows(field);
        rotated180 = reverseCols(rotated180);
        /***********************************************************************/

			/*look for a match of this rotations against stored states*/
        boolean matchFound = false;
        for (int j = 0; j < storedStates.size() && matchFound == false; j++){
            //use private method 'checkFieldEq' to check equality of 2 char[][]
            if (checkFieldEq(storedStates.get(j), rotated90_r) ||
                    checkFieldEq(storedStates.get(j), rotated90_l) ||
                    checkFieldEq(storedStates.get(j), rotated180)){
                matchFound = true;
            }
        }
        return matchFound;
    }


    //----------------------------------------------------------------
    //------------------ STATIC METHODS ------------------------------
    //----------------------------------------------------------------

    /*method for matrix transposition, used to check rotations*/
    public static char[][] transposeMatrix(char [][] matr){
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
    public static char[][] reverseRows(char [][] matr){
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
    public static char[][] reverseCols(char [][] matr){
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
    public static boolean checkFieldEq(char[][] field1, char[][] field2){
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
}
