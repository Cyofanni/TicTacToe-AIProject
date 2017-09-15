package ticTacToe;

import AI.algorithms.AbsMinimaxStructure;
import config.AbsConfig;

import java.util.ArrayList;

/**
 * This class represent the logic of the game tic tac toe
 *
 * @author Davide Rigoni
 */
public class StatisticsTicTacToe2AI extends AbsTicTacToeAI {

    /**
     * The list of the all result of the AI of the player0
     */
    private ArrayList<AbsResult> resultsP0 = new ArrayList<AbsResult>();

    /**
     * Constructor
     */
    public StatisticsTicTacToe2AI(AbsConfig config) {
        super(config);
    }

    /**
     * Constructor
     */
    public StatisticsTicTacToe2AI(AbsConfig config, char[][] field, int player){
        super(config, field, player);
    }



    /* -------------------------------------------------------------------- */
    /* ------------------------------- METHODS ---------------------------- */
    /* -------------------------------------------------------------------- */

    @Override
    public void start() {
        AbsConfig config = this.getConfig();

        //Initialize the algorithmP0
        AbsMinimaxStructure algP0 = AbsTicTacToeAI.initAlgorithm(
                config.getAlgorithmP0(), config.getDepthP0(),config.getEFP0());
        //Initialize the algorithmP1
        AbsMinimaxStructure algP1 = AbsTicTacToeAI.initAlgorithm(
                config.getAlgorithmP1(), config.getDepthP1(),config.getEFP1());


        int player = this.activePlayer;
        if(player == 0) {
            algP0.computeMove(this,player);
            this.getResultsP0().add(algP0.getResult());
        } else {
            algP1.computeMove(this,player);
            this.getResultsP1().add(algP1.getResult());
        }
    }

    @Override
    public AbsTicTacToe deepClone(){
        char[][] newField = AbsTicTacToe.copyField(this.field);
        AbsTicTacToeAI obj = new StatisticsTicTacToe2AI(this.config, newField, this.activePlayer);
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


}
