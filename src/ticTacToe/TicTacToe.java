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

    @Override
    public void start() {
        System.out.println("---------- START --------");
        this.printField();

        while (!this.checkEnd())
        {
            AbsMove move = this.askMove(this.activePlayer);
            this.move(move);
            this.printField();
        }

        if(this.checkWinner()){
            System.out.println("The winner is: Player" + this.activePlayer);
        } else {
            System.out.println("The winner is: Draw");
        }
        System.out.println("---------- END ----------");

    }

    @Override
    public AbsTicTacToe deepClone(){
        char[][] newField = AbsTicTacToe.copyField(this.field);
        TicTacToe obj = new TicTacToe(this.config, newField, this.activePlayer);
        return obj;
    }

}
