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

    /**
     * This static method init the eval function corresponding to the number
     * @param numberEF
     * @return The eval function
     */
    public static IEvalFunction initEF(int numberEF){
        //Initialize the IA of the player1
        IEvalFunction fP1;
        switch (numberEF) {
            case 1:
                fP1 = new AdvanceEF();
                break;
            case 0:
            default:
                fP1 = new SimpleEF();
        }
        return fP1;
    }

    /**
     * This static method init the algorithm
     * @param numberAlgorithm
     * @param depth
     * @param ef Eval Function
     * @return The algorithm
     */
    public static AbsMinimaxStructure initAlgorithm(int numberAlgorithm,int depth, IEvalFunction ef){
        //Initialize the algorithm
        AbsMinimaxStructure alg;
        switch (numberAlgorithm){
            case 0: {alg = new Minimax(depth,ef);}
            break;
            case 1: {alg = new MinimaxRot(depth,ef);}
            break;
            case 2: {alg = new MinimaxABP(depth,ef);}
            break;
            case 3: {alg = new MinimaxABPRot(depth,ef);}
            break;
            default: alg = new Minimax(depth, ef);
        }
        return alg;
    }

    /**
     * This static method init the algorithm
     * @param numberAlgorithm
     * @param depth
     * @param ef Number of the Eval Function
     * @return The algorithm
     */
    public static AbsMinimaxStructure initAlgorithm(int numberAlgorithm,int depth, int ef){
        return initAlgorithm(numberAlgorithm,depth,initEF(ef));
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
