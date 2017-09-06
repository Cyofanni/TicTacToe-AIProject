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
public class TicTacToeAI extends AbsTicTacToe {
    /**
     * The list of the all result of the AI of the player01
     */
    private ArrayList<AbsResult> resultsP1 = new ArrayList<AbsResult>();

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

        while (!this.checkEnd())
        {
            AbsMove move;
            if(this.activePlayer == 0) {
                move = this.askMove(this.activePlayer);
            } else {
                //Initialize the algorithm
                //N.B. Inside the while because the objects MatrixOperator and Results in Minimax
                //need to be created every time.
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

                move = alg.computeMove(this);
                this.getResults().add(alg.getResult());
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
        // Coping the field
        int n = this.config.getNRows();
        char[][] newField = new char[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                newField[i][j] = this.field[i][j];
            }
        }
        TicTacToeAI obj = new TicTacToeAI(this.config, newField, this.activePlayer);
        return obj;
    }

    /**
     * This method stamps all the results to the standard output of the Player1
     */
    public void printResultP1(){
        System.out.println("Following the results");
        for(int i = 0; i < this.resultsP1.size(); i++){
            AbsResult r = this.resultsP1.get(i);
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
     * This method allows access to the list of the results of the Player1
     * @return Results Player1
     */
    public ArrayList<AbsResult> getResults() {
        return resultsP1;
    }
}
