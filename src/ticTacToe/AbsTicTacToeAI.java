package ticTacToe;

import config.AbsConfig;
import java.util.ArrayList;

/**
 * This class represent the logic of the game tic tac toe
 *
 * @author Davide Rigoni
 */
public abstract class AbsTicTacToeAI extends AbsTicTacToe {
    /**
     * The list of the all result of the AI of the player01
     */
    private ArrayList<AbsResult> resultsP1 = new ArrayList<AbsResult>();

    /**
     * Constructor
     */
    public AbsTicTacToeAI(AbsConfig config) {
        super(config);
    }

    /**
     * Constructor
     */
    public AbsTicTacToeAI(AbsConfig config, char[][] field, int player){
        super(config, field, player);
    }


    /* -------------------------------------------------------------------- */
    /* ------------------------------- METHODS ---------------------------- */
    /* -------------------------------------------------------------------- */

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
    public ArrayList<AbsResult> getResultsP1() {
        return resultsP1;
    }
}
