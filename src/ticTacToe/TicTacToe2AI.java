package ticTacToe;

import AI.EF.AdvanceEF;
import AI.EF.IEvalFunction;
import AI.EF.SimpleEF;
import AI.algorithms.*;
import config.AbsConfig;

import java.util.ArrayList;

/**
 * This class represent the logic of the game tic tac toe
 *
 * @author Davide Rigoni
 */
public class TicTacToe2AI extends AbsTicTacToeAI {

    /**
     * The list of the all result of the AI of the player0
     */
    private ArrayList<AbsResult> resultsP0 = new ArrayList<AbsResult>();

    /**
     * Represent the print of the field
     */
    boolean isPrintField = false;

    /**
     * Constructor
     */
    public TicTacToe2AI(AbsConfig config) {
        super(config);
    }

    /**
     * Constructor
     */
    public TicTacToe2AI(AbsConfig config, char[][] field, int player){
        super(config, field, player);
    }



    /* -------------------------------------------------------------------- */
    /* ------------------------------- METHODS ---------------------------- */
    /* -------------------------------------------------------------------- */

    @Override
    public void start() {
        System.out.println("---------- START --------");
        if(this.isPrintField) {
            this.printField();
        }

        //Initialize the IA of the player0
        IEvalFunction fP0;
        switch (this.config.getEFP0()) {
            case 1:
                fP0 = new AdvanceEF();
                break;
            case 0:
            default:
                fP0 = new SimpleEF();
        }
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

        //Initialize the algorithmP0
        AbsMinimaxStructure algP0;
        switch (this.config.getAlgorithmP0()){
            case 0: {algP0 = new Minimax(
                    this.getConfig().getDepthP0(),fP0);}
            break;
            case 1: {algP0 = new MinimaxRot(
                    this.getConfig().getDepthP0(),fP0);}
            break;
            case 2: {algP0 = new MinimaxABP(
                    this.getConfig().getDepthP0(),fP0);}
            break;
            case 3: {algP0 = new MinimaxABPRot(
                    this.getConfig().getDepthP0(),fP0);}
            break;
            default: algP0 = new Minimax(
                    this.getConfig().getDepthP0(), fP0);
        }
        //Initialize the algorithmP1
        AbsMinimaxStructure algP1;
        switch (this.config.getAlgorithmP1()){
            case 0: {algP1 = new Minimax(
                    this.getConfig().getDepthP1(),fP1);}
            break;
            case 1: {algP1 = new MinimaxRot(
                    this.getConfig().getDepthP1(),fP1);}
            break;
            case 2: {algP1 = new MinimaxABP(
                    this.getConfig().getDepthP1(),fP1);}
            break;
            case 3: {algP1 = new MinimaxABPRot(
                    this.getConfig().getDepthP1(),fP1);}
            break;
            default: algP1 = new Minimax(
                    this.getConfig().getDepthP1(), fP1);
        }

        //Play until the end
        while (!this.checkEnd())
        {
            int player = this.activePlayer;
            AbsMove move;
            if(player == 0) {
                move = algP0.computeMove(this,0);
                this.getResultsP0().add(algP0.getResult());
            } else {
                move = algP1.computeMove(this,1);
                this.getResultsP1().add(algP1.getResult());
            }

            this.move(move);
            if(this.isPrintField){
                System.out.println("Move of the Player"+player+": "
                        + move.getX() + " " + move.getY());
                this.printField();
            }

        }

        //Check the winner
        if(this.checkWinner()){
            System.out.println("The winner is: Player" + this.activePlayer);
        } else {
            System.out.println("The winner is: Draw");
        }

        //Print the statistics
        System.out.println("Following the statistics of the Player0");
        this.printResultP0();
        System.out.println("Following the statistics of the Player1");
        this.printResultP1();

        System.out.println("---------- END ----------");

    }

    @Override
    public AbsTicTacToe deepClone(){
        char[][] newField = AbsTicTacToe.copyField(this.field);
        AbsTicTacToeAI obj = new TicTacToe2AI(this.config, newField, this.activePlayer);
        return obj;
    }

    /**
     * This method stamps all the results to the standard output of the Player0
     */
    public void printResultP0(){
        System.out.println("Following the results");
        for(int i = 0; i < this.resultsP0.size(); i++){
            AbsResult r = this.resultsP0.get(i);
            if(i==0){
                System.out.format("%-15s", "PLAY");
                r.print(true);
            }
            System.out.format("%-15d", i + 1);
            r.print(false);
            System.out.println();
        }
    }

    /* -------------------------------------------------------------------- */
    /* ------------------------------- GETTERS ---------------------------- */
    /* -------------------------------------------------------------------- */
    /**
     * This method allows access to the list of the results of the Player0
     * @return Results Player0
     */
    public ArrayList<AbsResult> getResultsP0() {
        return resultsP0;
    }

    /**
     * This method allows to access to the printField variable
     * @return The printField value
     */
    public boolean getIsPrintField() {
        return isPrintField;
    }


    /* -------------------------------------------------------------------- */
    /* ------------------------------- SETTERS ---------------------------- */
    /* -------------------------------------------------------------------- */
    /**
     * This method allows to access to the printField variable
     */
    public void setIsPrintField(boolean printField) {
        this.isPrintField = printField;
    }
}
