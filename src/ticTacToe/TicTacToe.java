package ticTacToe;

import config.AbsConfig;

/**
 * This class represent the logic of the game tic tac toe
 *
 * @author Davide Rigoni
 */
public class TicTacToe extends AbsTicTacToe {

    /**
     * Constructor
     */
    public TicTacToe(AbsConfig config) {
        super(config);
    }

    /**
     * Constructor
     */
    public TicTacToe(AbsConfig config, char[][] field, int player){
        super(config, field, player);
    }

    public TicTacToe deepClone(){
        // Coping the field
        int n = this.config.getNRows();
        char[][] newField = new char[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                newField[i][j] = this.field[i][j];
            }
        }
        TicTacToe obj = new TicTacToe(this.config, newField, this.activePlayer);
        return obj;
    }

}
