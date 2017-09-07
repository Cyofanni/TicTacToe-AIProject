package ticTacToe;

import AI.EF.AdvanceEF;
import AI.EF.IEvalFunction;
import AI.EF.SimpleEF;
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

        //Initialize the IA of the player1
        IEvalFunction fP1;
        switch (this.config.getEFP1()) {
            case 1:
                fP1 = new AdvanceEF();
                break;
            case 0:
            default:
                fP1 = new SimpleEF();
        }

        //Initialize the algorithm
        AbsMinimaxStructure alg;
        switch (this.config.getAlgorithmP1()){
            case 0: {alg = new Minimax(
                    this.getConfig().getDepthP1(),fP1);}
            break;
            case 1: {alg = new MinimaxRot(
                    this.getConfig().getDepthP1(),fP1);}
            break;
            case 2: {alg = new MinimaxABP(
                    this.getConfig().getDepthP1(),fP1);}
            break;
            case 3: {alg = new MinimaxABPRot(
                    this.getConfig().getDepthP1(),fP1);}
            break;
            default: alg = new Minimax(
                    this.getConfig().getDepthP1(), fP1);
        }

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
