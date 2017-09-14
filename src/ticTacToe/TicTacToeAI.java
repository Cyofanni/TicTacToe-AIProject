package ticTacToe;

import AI.algorithms.*;
import config.AbsConfig;

/**
 * This class represent the logic of the game tic tac toe
 *
 * @author Davide Rigoni
 */
public class TicTacToeAI extends AbsTicTacToeAI {
    /**
     * Constructor
     */
    public TicTacToeAI(AbsConfig config) {
        super(config);
    }

    /**
     * Constructor
     */
    public TicTacToeAI(AbsConfig config, char[][] field, int player){
        super(config, field, player);
    }


    /* -------------------------------------------------------------------- */
    /* ------------------------------- METHODS ---------------------------- */
    /* -------------------------------------------------------------------- */

    @Override
    public void start() {
        System.out.println("---------- START --------");
        this.printField();
        AbsConfig config = this.getConfig();

        //Initialize the algorithm
        AbsMinimaxStructure alg = AbsTicTacToeAI.initAlgorithm(
                config.getAlgorithmP1(), config.getDepthP1(),config.getEFP1());

        while (!this.checkEnd())
        {
            AbsMove move;
            if(this.activePlayer == 0) {
                move = this.askMove(this.activePlayer);
            } else {
                move = alg.computeMove(this,1);
                this.getResultsP1().add(alg.getResult());
                System.out.println("Move of the AI: "
                        + move.getX() + " " + move.getY());
            }
            this.move(move);
            this.printField();
        }

        //Check the winner
        if(this.checkWinner()){
            System.out.println("The winner is: Player" + this.activePlayer);
        } else {
            System.out.println("The winner is: Draw");
        }

        //Print the statistics
        System.out.println("Following the statistics of the Player1");
        this.printResultP1();

        System.out.println("---------- END ----------");
    }

    @Override
    public AbsTicTacToe deepClone(){
        char[][] newField = AbsTicTacToe.copyField(this.field);
        AbsTicTacToeAI obj = new TicTacToeAI(this.config, newField, this.activePlayer);
        return obj;
    }

}
