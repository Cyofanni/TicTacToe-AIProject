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
     * If the player0 play against the PC
     */
    private boolean player0AI = false;

    /**
     * If the player1 play against the PC
     */
    private boolean player1AI = false;
    /**
     * The algorithm used by the player0
     */
    private int algorithmP0 = 0;
    /**
     * The evaluation function used by the algorithm choose the player0
     */
    private int EFP0 = 0;
    /**
     * Max depth used by the algorithm of the player0
     */
    private int depthP0 = 0;
    /**
     * The algorithm used by the player1
     */
    private int algorithmP1 = 0;
    /**
     * The evaluation function used by the algorithm choose by the player1
     */
    private int EFP1 = 0;
    /**
     * Max depth used by the algorithm of the player1
     */
    private int depthP1 = 0;

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
     * Constructor that allow 2 person to play
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
     * Constructor that allow 1 AI to play
     * @param firstPlayer
     * @param nRows
     * @param nLockedCells
     * @param algorithmP1
     * @param EFP1
     * @param depthP1
     */
    public AbsConfig(int firstPlayer, int nRows, int nLockedCells,
                     int algorithmP1, int EFP1, int depthP1){
        this(firstPlayer,nRows,nLockedCells);
        this.algorithmP1 = algorithmP1;
        this.EFP1 = EFP1;
        this.depthP1 = depthP1;
        this.player1AI = true;
    }

    /**
     * Constructor that allow 2 AI to play
     * @param firstPlayer
     * @param nRows
     * @param nLockedCells
     * @param algorithmP0
     * @param EFP0
     * @param depthP0
     * @param algorithmP1
     * @param EFP1
     * @param depthP1
     */
    public AbsConfig(int firstPlayer, int nRows, int nLockedCells,
                     int algorithmP0, int EFP0, int depthP0,
                     int algorithmP1, int EFP1, int depthP1){
        this(firstPlayer,nRows,nLockedCells,algorithmP1,EFP1,depthP1);
        this.algorithmP0 = algorithmP0;
        this.EFP0 = EFP0;
        this.depthP0 = depthP0;
        this.player0AI = true;
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
        if(this.player0AI){
            System.out.println("Following the data insertion for the AI of the Player0");
            this.algorithmP0 = this.askAlgorithm();
            this.EFP0 = this.askEF();
            this.depthP0 = this.askMaxDepth();
        }
        if(this.player1AI){
            System.out.println("Following the data insertion for the AI of the Player1");
            this.algorithmP1 = this.askAlgorithm();
            this.EFP1 = this.askEF();
            this.depthP1 = this.askMaxDepth();
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
        System.out.println(" 1 - Player1 or AI");
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
        System.out.println("Mode of the game: ");
        System.out.println(" 0 - Player0 against Player1");
        System.out.println(" 1 - Player0 against AI");
        System.out.println(" 2 - AI against AI");
        System.out.print("Number: ");
        Scanner reader = new Scanner(System.in);
        int number = reader.nextInt();
        switch (number){
            case 0:{
                this.player0AI = false;
                this.player1AI = false;
            }break;
            case 1:{
                this.player0AI = false;
                this.player1AI = true;
            } break;
            case 2: {
                this.player0AI = true;
                this.player1AI = true;
            } break;
            default:{
                System.out.print("Error: Try again");
                this.askTypeOfGame();
            }
        }
    }

    /**
     * Ask the user the algorithm of AI
     * @return the number of the chosen one
     */
    protected int askAlgorithm(){
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
            return number;
        }else{
            System.out.print("Error: Try again");
            return this.askAlgorithm();
        }
    }

    /**
     * Ask the user the EF of the AI
     * @return the number of the chosen one
     */
    protected int askEF(){
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
            return number;
        }else{
            System.out.print("Error: Try again");
            return this.askEF();
        }
    }

    /**
     * Ask the user the max depth
     * @return the number of the chosen one
     */
    protected int askMaxDepth(){
        System.out.print("Insert the max depth: ");
        Scanner reader = new Scanner(System.in);
        int number = reader.nextInt();
        if(number > 0)
        {
            return number;
        }else{
            System.out.print("Error: Try again");
            return this.askMaxDepth();
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
     * Return true if the player0 represent the AI,  otherwise false
     * @return TRUE if the player0 play against the PC
     */
    public boolean getPlayer0AI(){
        return this.player0AI;
    }

    /**
     * Return true if the player1 represent the AI,  otherwise false
     * @return TRUE if the player1 play against the PC
     */
    public boolean getPlayer1AI(){
        return this.player1AI;
    }

    /**
     * Return the algorithm chosen by the Player0
     * @return algorithm
     */
    public int getAlgorithmP0(){
        return this.algorithmP0;
    }

    /**
     * Return the function chosen by the Player0
     * @return function
     */
    public int getEFP0(){
        return this.EFP0;
    }

    /**
     * Return the max depth number of Player0
     * @return depth
     */
    public int getDepthP0(){
        return this.depthP0;
    }

    /**
     * Return the algorithm chosen by the Player1
     * @return algorithm
     */
    public int getAlgorithmP1(){
        return this.algorithmP1;
    }

    /**
     * Return the function chosen by the Player1
     * @return function
     */
    public int getEFP1(){
        return this.EFP1;
    }

    /**
     * Return the max depth number of Player1
     * @return depth
     */
    public int getDepthP1(){
        return this.depthP1;
    }

    /* -------------------------------------------------------------------- */
    /* ------------------------------- METHODS ---------------------------- */
    /* -------------------------------------------------------------------- */

    /**
     * Print the description of the configs in the standard output
     */
    public void print(){
        boolean p0 = this.getPlayer0AI();
        boolean p1 = this.getPlayer1AI();
        System.out.println("First player: " + this.firstPlayer);
        System.out.println("Number of rows and columns: " + this.getNRows());
        System.out.println("Number of locked cells: " + this.getNLockedCells());
        System.out.println("Player0 is an AI?: " + p0);
        if(p0) {
            int nAlgorithm = this.getAlgorithmP0();
            System.out.println("Algorithm: " + nAlgorithm + " - "
                    + this.algorithms[nAlgorithm]);
            int nEF = this.getEFP0();
            System.out.println("EF: " + nEF + " - " + this.EFs[nEF]);
            System.out.println("Max depth: " + this.getDepthP0());
        }
        System.out.println("Player1 is an AI?: " + p1);
        if(p1) {
            int nAlgorithm = this.getAlgorithmP1();
            System.out.println("Algorithm: " + nAlgorithm + " - "
                    + this.algorithms[nAlgorithm]);
            int nEF = this.getEFP1();
            System.out.println("EF: " + nEF + " - " + this.EFs[nEF]);
            System.out.println("Max depth: " + this.getDepthP1());
        }
    }

}
