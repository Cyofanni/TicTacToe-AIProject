package config;

import java.util.Scanner;

/**
 * <p>This abstract class takes inputs from user and save the initial
 * configurations params</p>
 *
 * @author Davide Rigoni
 */
public class AbsConfig  {

    /**
     * Player who start for first
     */
    private int firstPlayer = 0;
    /**
     * Number of rows and columns
     */
    private int nRows = 3;
    /**
     * Number of the locked cell
     */
    private int nLockedCells = 0;
    /**
     * If the player play against the PC
     */
    private boolean againstPC = false;
    /**
     * The algorithm used by the PC
     */
    private int algorithm = 0;
    /**
     * The evaluation function used by the algorithm choose
     */
    private int EF = 0;
    /**
     * Max depth used by the algorithm
     */
    private int depth = 0;

    /**
     * Array representing the different EF
     */
    private String[] EFs = {
            "Function number 1", "Function number 2"
    };

    /**
     * Array representing the different algorithms
     */
    private String[] algorithms = {
            "Normal", "Rotate", "Alpha-Beta Pruning", "Pruning with Rotate"
    };


    /**
     * Constructor
     */
    public AbsConfig(){}

    /**
     * Constructor
     * @param firstPlayer
     * @param nRows
     * @param nLockedCells
     */
    public AbsConfig(int firstPlayer, int nRows, int nLockedCells){
        this.firstPlayer = firstPlayer;
        this.nRows = nRows;
        this.nLockedCells = nLockedCells;
    }

    /**
     * Constructor
     * @param firstPlayer
     * @param nRows
     * @param nLockedCells
     * @param algorithm
     * @param EF
     */
    public AbsConfig(int firstPlayer, int nRows, int nLockedCells, int algorithm, int EF, int depth){
        this(firstPlayer,nRows,nLockedCells);
        this.algorithm = algorithm;
        this.EF = EF;
        this.depth = depth;
        this.againstPC = true;
    }


    /**
     * This method have the logic control of the initialization
     */
    public void startInitialConfig() {
        System.out.println("---------- Initial Config ----------");
        this.askFirstPlayer();
        this.askNRows();
        this.askNLockedCells();
        this.askTypeOfGame();
        if(this.againstPC){
            this.askAlgorithm();
            this.askEF();
            this.askMaxDepth();
        }
        System.out.println("---------- End initial Config ----------");
    }

    /* -------------------------------------------------------------------- */
    /* ------------------------------- ASKS ------------------------------- */
    /* -------------------------------------------------------------------- */

    /**
     * Ask the user to enter the player who start for first
     */
    protected void askFirstPlayer(){
        System.out.println("Insert the number of the first player: ");
        System.out.println(" 0 - Player0");
        System.out.println(" 1 - Player1");
        System.out.print("Number: ");
        Scanner reader = new Scanner(System.in);
        int number = reader.nextInt();
        if(number >= 0 && number <=1)
        {
            this.firstPlayer = number;
        }else{
            System.out.print("Error: Try again");
            this.askFirstPlayer();
        }
    }

    /**
     * Ask the user to enter the size of the field
     */
    protected void askNRows(){
        System.out.print("Insert the number of rows and columns: ");
        Scanner reader = new Scanner(System.in);
        int number = reader.nextInt();
        if(number >= 3)
        {
            this.nRows = number;
        }else{
            System.out.print("Error: Try again");
            this.askNRows();
        }
    }

    /**
     * Ask the user to enter the number of locked cells
     */
    protected void askNLockedCells(){
        System.out.print("Insert the number of the locked cells: ");
        Scanner reader = new Scanner(System.in);
        int number = reader.nextInt();
        if(number >= 0)
        {
            this.nLockedCells = number;
        }else{
            System.out.print("Error: Try again");
            this.askNLockedCells();
        }
    }

    /**
     * Ask the user if want to play against the PC
     */
    protected void askTypeOfGame(){
        System.out.print("Do you want to play against the PC? 'yes' or 'no': ");
        Scanner reader = new Scanner(System.in);
        String resp = reader.nextLine();
        if(resp.equals("yes"))
        {
            this.againstPC = true;
        }else{
            if(!resp.equals("no")){
                System.out.print("Error: Try again");
                this.askTypeOfGame();
            }
        }
    }

    /**
     * Ask the user the algorithm of AI
     */
    protected void askAlgorithm(){
        System.out.println("Choose one of these algorithms");
        int max = this.algorithms.length;
        for(int i = 0; i < max; i++){
            System.out.println("  " + i + " - " + this.algorithms[i]);
        }
        System.out.print("Number: ");
        Scanner reader = new Scanner(System.in);
        int number = reader.nextInt();
        if(number >= 0 && number < max)
        {
            this.algorithm = number;
        }else{
            System.out.print("Error: Try again");
            this.askAlgorithm();
        }
    }

    /**
     * Ask the user the EF of the AI
     */
    protected void askEF(){
        System.out.println("Choose one of these EF");
        int max = this.EFs.length;
        for(int i = 0; i < max; i++){
            System.out.println("  " + i + " - " + this.EFs[i]);
        }
        System.out.print("Number: ");
        Scanner reader = new Scanner(System.in);
        int number = reader.nextInt();
        if(number >= 0 && number < max)
        {
            this.EF = number;
        }else{
            System.out.print("Error: Try again");
            this.askEF();
        }
    }

    /**
     * Ask the user the max depth
     */
    protected void askMaxDepth(){
        System.out.print("Insert the max depth: ");
        Scanner reader = new Scanner(System.in);
        int number = reader.nextInt();
        if(number > 0)
        {
            this.depth = number;
        }else{
            System.out.print("Error: Try again");
            this.askMaxDepth();
        }
    }

    /* -------------------------------------------------------------------- */
    /* ------------------------------- GETTERS ---------------------------- */
    /* -------------------------------------------------------------------- */

    /**
     * Return the number of the player who start for first
     * @return Number of the player
     */
    public int getFirstPlayer(){
        return this.firstPlayer;
    }

    /**
     * Return the number of rows and columns
     * @return Number of rows and columns
     */
    public int getNRows(){
        return this.nRows;
    }

    /**
     * Return the number of locked cells
     * @return Number of locked cells
     */
    public int getNLockedCells(){
        return this.nLockedCells;
    }

    /**
     * Return true if the player play against the PC,  otherwise false
     * @return TRUE if the player play against the PC
     */
    public boolean getTypeOfGame(){
        return this.againstPC;
    }

    /**
     * Return the algorithm chosen by the player
     * @return algorithm
     */
    public int getAlgorithm(){
        return this.algorithm;
    }

    /**
     * Return the function chosen by the player
     * @return function
     */
    public int getEF(){
        return this.EF;
    }

    /**
     * Return the max number of depth
     * @return depth
     */
    public int getDepth(){
        return this.depth;
    }

    /* -------------------------------------------------------------------- */
    /* ------------------------------- METHODS ---------------------------- */
    /* -------------------------------------------------------------------- */

    /**
     * Print the description of the configs in the standard output
     */
    public void print(){
        System.out.println("Number of rows and columns: " + this.getNRows());
        System.out.println("Number of locked cells: " + this.getNLockedCells());
        boolean b = this.getTypeOfGame();
        System.out.println("Against PC?: " + b);
        if(b) {
            int nAlgorithm = this.getAlgorithm();
            System.out.println("Algorithm: " + nAlgorithm + " - "
                    + this.algorithms[nAlgorithm]);
            int nEF = this.getEF();
            System.out.println("EF: " + nEF + " - " + this.EFs[nEF]);
            System.out.println("Max depth: " + this.getDepth());
        }
    }

}
