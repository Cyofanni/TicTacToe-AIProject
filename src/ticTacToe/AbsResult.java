package ticTacToe;


/**
 * This class represents the result object
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
public abstract class AbsResult {
    /**
     * Number of nodes
     */
    private int nNodes = 0;

    /**
     * Time
     */
    private long startTime = 0;

    /**
     * Time
     */
    private long endTime = 0;

    /**
     * Level of the node nearest the root
     */
    private int level  = Integer.MAX_VALUE;

    /**
     * Best score
     */
    private double bestScore = Integer.MIN_VALUE;

    /**
     * Constructor
     *
     * @param nNodes Number of nodes
     * @param startTime Time
     * @param endTime Time
     * @param level Level of the node nearest the root
     * @param bestScore Best score
     */
    public AbsResult(int nNodes, long startTime, long endTime , int level, double bestScore){
        this.nNodes = nNodes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.level = level;
        this.bestScore = bestScore;
    }

    /**
     * Constructor
     */
    public AbsResult(){}

    /* -------------------------------------------------------------------- */
    /* ------------------------------- METHODS ---------------------------- */
    /* -------------------------------------------------------------------- */

    /**
     * This method print the values to the standard output
     * @param head TRUE to stamp the head, else FALSE
     */
    public void print(boolean head){
        if(head){
            System.out.format("%-15s", "NODES");
            System.out.format("%-15s", "TIME");
            System.out.format("%-15s", "BEST SCORE");
            System.out.format("%-15s", "LEVEL");
            System.out.println();
        } else {
            System.out.format("%-15d", this.getnNodes());
            System.out.format("%-15d", this.getTime());
            System.out.format("%-15f", this.getBestScore());
            System.out.format("%-15d", this.getLevel());
        }
    }

    /* -------------------------------------------------------------------- */
    /* ------------------------------- GETTERS ---------------------------- */
    /* -------------------------------------------------------------------- */

    /**
     * This method allows access to the number of nodes
     * @return Number of nodes
     */
    public int getnNodes(){
        return this.nNodes;
    }

    /**
     * This method allows access to the time
     * @return Elapsed Time in second
     */
    public long getTime(){
        long elapsed = this.endTime - this.startTime;
        return elapsed;
    }

    /**
     * This method allows access to the best score
     * @return Best score
     */
    public double getBestScore() {
        return bestScore;
    }

    /**
     * This method allows access to deep of the node nearest the root
     * @return Level
     */
    public int getLevel() {
        return level;
    }


    /* -------------------------------------------------------------------- */
    /* ------------------------------- SETTERS ---------------------------- */
    /* -------------------------------------------------------------------- */

    /**
     * Add 1 to the variable nNodes
     */
    public void addNode(){
        nNodes++;
    }

    /**
     * Add n to the variable nNodes
     */
    public void addNode(int n){
        nNodes = nNodes + n;
    }

    /**
     * Start the timer
     */
    public void startTime(){
        startTime = System.nanoTime();
    }

    /**
     * End the timer
     */
    public void endTime(){
        endTime = System.nanoTime();
    }

    /**
     * Set the nearest level to the root found during the search
     * @param i Number representing the distance from the root (starting by 0)
     */
    public void setNearestLevel(int i){
        if(i < level){
            level = i;
        }
    }

    /**
     * Set the best score found during the search
     * @param score Score found during the search
     */
    public void setBestScore(double score){
        if(score > bestScore){
            bestScore = score;
        }
    }
}
