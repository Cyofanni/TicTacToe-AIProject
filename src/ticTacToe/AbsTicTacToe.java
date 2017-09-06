package ticTacToe;

import AI.algorithms.*;
import AI.EF.AdvanceEF;
import AI.EF.IEvalFunction;
import AI.EF.SimpleEF;
import config.AbsConfig;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * This class represent the logic of the tic-tac-toe game
 *
 * @author Davide Rigoni
 */
public abstract class AbsTicTacToe {
    /**
     * Char corresponding to the blank cell
     */
    public final static char BLANKVALUE = 'B';

    /**
     * Char corresponding to the locked cell
     */
    public final static char LOCKEDVALUE = 'L';

    /**
     * Char corresponding to the players
     */
    public final static char[] PLAYERSVALUE = {'X','O'};

    /**
     * Reference to the Config object
     */
    protected AbsConfig config;

    /**
     * This matrix represent the field status
     */
    protected char[][] field;

    /**
     * The player who is playing
     */
    protected int activePlayer;

    /**
     * The list of the all result
     */
    private ArrayList<AbsResult> results = new ArrayList<AbsResult>();

    /**
     * Constructor
     */
    public AbsTicTacToe(AbsConfig config){
        this.config = config;
        this.initializeField(config.getNRows(), config.getNLockedCells());
        this.activePlayer = config.getFirstPlayer();
    }

    protected AbsTicTacToe(AbsConfig config, char[][] field, int player){
        this.config = config;
        this.field = field;
        this.activePlayer = player;
    }


    /* -------------------------------------------------------------------- */
    /* ------------------------------- METHODS ---------------------------- */
    /* -------------------------------------------------------------------- */

    public void start(){
        System.out.println("---------- START --------");
        this.printField();
        int n = this.config.getNRows();
        if(!this.config.getTypeOfGame()) {
            while (!this.checkEnd())
            {
                AbsMove move = this.askMove(this.activePlayer);
                this.move(move);
                this.printField();
            }
        } else{
            //Initialize the IA
            IEvalFunction f;
            switch (this.config.getEF()){
                case 1:
                    f = new AdvanceEF();
                    break;
                case 0:
                default: f = new SimpleEF();
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
                    switch (this.config.getAlgorithm()){
                        case 0: {alg = new Minimax(
                                this.getConfig().getDepth(),f);}
                        break;
                        case 1: {alg = new MinimaxRot(
                                this.getConfig().getDepth(),f);}
                        break;
                        case 2: {alg = new MinimaxABP(
                                this.getConfig().getDepth(),f);}
                        break;
                        case 3: {alg = new MinimaxABPRot(
                                this.getConfig().getDepth(),f);}
                        break;
                        default: alg = new Minimax(
                                this.getConfig().getDepth(),
                                f);
                    }

                    move = alg.computeMove(this);
                    results.add(alg.getResult());
                    System.out.println("Move of the AI: "
                            + move.getX() + " " + move.getY());
                }
                this.move(move);
                this.printField();
            }
            this.printResult();
        }

        if(this.checkWinner()){
            System.out.println("The winner is: Player" + this.activePlayer);
        } else {
            System.out.println("The winner is: Draw");
        }
        System.out.println("---------- END ----------");

    }


    /**
     * Initialize the field with the default values and the locked cells
     * @param n Number of the dimension of the field
     * @param nLC Number of the locked cells
     */
    protected void initializeField(int n, int nLC){
        // Instance
        this.field = new char[n][n];
        for(int i = 0; i< n; i++){
            for(int j = 0; j < n ; j++){
                this.field[i][j] = this.BLANKVALUE;
            }
        }
        // Add locked cells
        Random random = new Random();
        while(nLC > 0){
            int row = random.nextInt(n);
            int column = random.nextInt(n);
            if(this.field[row][column] == this.BLANKVALUE) {
                this.field[row][column] = this.LOCKEDVALUE;
                nLC--;
            }
        }
    }

    /**
     * This method make a move in the field
     * @param player Player number
     * @param move Move
     */
    protected void move(int player, AbsMove move) {
        int row = move.getX();
        int column = move.getY();
        this.field[row][column] = this.PLAYERSVALUE[player];
    }

    /**
     * This method make a move in the field using the current active player
     * @param move Move
     * @return TRUE if the player win he game
     */
    public boolean move(AbsMove move){
        this.move(this.activePlayer, move);
        boolean result = this.checkWinner();
        if(!result)
            this.activePlayer = (this.activePlayer + 1) % 2;
        return result;
    }

    /**
     * This method checks if the current active player is the winner
     * @return TRUE if the player win the game
     */
    public boolean checkWinner(){
        int n = this.config.getNRows();
        // Find solutions in columns
        int cColumn;
        for(int row = 0; row < n; row ++){
            cColumn = 0;
            for(int column = 0; column < n; column ++){
                if(this.field[row][column] ==
                        this.PLAYERSVALUE[this.activePlayer]){
                    cColumn ++;
                } else{
                    break;
                }
            }
            if(cColumn == n){
                return true;
            }
        }

        // Find solutions in rows
        int cRow;
        for(int column = 0; column < n; column ++){
            cRow = 0;
            for(int row = 0; row < n; row ++){
                if(this.field[row][column] ==
                        this.PLAYERSVALUE[this.activePlayer]){
                    cRow ++;
                } else{
                    break;
                }
            }
            if(cRow == n){
                return true;
            }
        }

        // Find solutions in diagonals
        int cDiagonal1 = 0;
        int cDiagonal2 = 0;
        for(int i = 0; i < n; i ++){
            if(this.field[i][i] == this.PLAYERSVALUE[this.activePlayer]){
                cDiagonal1 ++;
            }
            if(this.field[i][n-i-1] == this.PLAYERSVALUE[this.activePlayer]){
                cDiagonal2 ++;
            }
        }
        if(cDiagonal1 == n || cDiagonal2 == n){
            return true;
        }
        return false;
    }

    /**
     * This method checks for the end of the game
     * @return TRUE or FALSE
     */
    public boolean checkEnd(){
        int n = this.config.getNRows();
        boolean checkField = true;
        for(int row = 0; row < n && checkField; row ++){
            for(int column = 0; column < n && checkField; column ++){
                if (this.field[row][column] == AbsTicTacToe.BLANKVALUE){
                   checkField = false;
                }
            }
        }

        return checkField || this.checkWinner();
    }

    /**
     * Print the field
     */
    public void printField(){
        System.out.println("Following the field:");
        int n = this.config.getNRows();
        for(int row = 0; row < n; row ++){
            for(int column = 0; column < n; column ++){
                char value = this.field[row][column];
                if(value == this.BLANKVALUE){
                    System.out.print("|   |");
                } else{
                    System.out.print("| " + value + " |");
                }
            }
            System.out.println();
        }
    }


    /**
     * This method ask the rows and the column to the player
     * @param player
     * @return Move
     */
    protected AbsMove askMove(int player){
        Scanner reader = new Scanner(System.in);
        System.out.println("Make your move player" + player);
        int n = this.config.getNRows();
        int nRow;
        int nColumn;
        do{
            do {
                System.out.print("Enter the row number: ");
                nRow = reader.nextInt();
                if(nRow < 0 || nRow >= n)
                    System.out.println("Error. Row number is not correct");
            }while(nRow < 0 || nRow >= n);
            do {
                System.out.print("Enter the column number: ");
                nColumn = reader.nextInt();
                if(nColumn < 0 || nColumn >= n)
                    System.out.println("Error. Column number is not correct");
            }while(nColumn < 0 || nColumn >= n);
            if(this.field[nRow][nColumn] != this.BLANKVALUE)
                System.out.println("Error. The cell is not free");
        } while(this.field[nRow][nColumn] != this.BLANKVALUE);

        return new Move(nRow,nColumn);
    }

    /**
     * This method stamps all the results to the standard output
     */
    public void printResult(){
        System.out.println("Following the results");
        for(int i = 0; i < this.results.size(); i++){
            AbsResult r = this.results.get(i);
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
     * This method allows access to the Config object
     * @return The Config object
     */
    public AbsConfig getConfig(){
        return this.config;
    }

    /**
     * This method allows access to the state of the field.
     * @return The field
     */
    public char[][] getField(){
        int n = this.config.getNRows();
        char[][] newField = new char[n][n];
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                newField[i][j] = this.field[i][j];
            }
        }
        return newField;
    }

    /**
     * This method allows access to the player that is playing
     * @return The number of the player
     */
    public int getActivePlayer(){
        return this.activePlayer;
    }

    /**
     * This method executes the deepClone of the tic tac toe object
     * @return The new tic tac object
     */
    public abstract TicTacToe deepClone();

    /**
     * This method allows access to the list of the results
     * @return Results
     */
    public ArrayList<AbsResult> getResults() {
        return results;
    }
}
