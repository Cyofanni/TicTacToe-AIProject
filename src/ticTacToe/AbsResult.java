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
    private int time = 0;

    /**
     * Level of the node nearest the root
     */
    private int level  = 0;

    /**
     * Best score
     */
    private double bestScore = 0;

    /**
     * Constructor
     *
     * @param nNodes Number of nodes
     * @param time Time
     * @param level Level of the node nearest the root
     * @param bestScore Best score
     */
    public AbsResult(int nNodes, int time, int level, double bestScore){
        this.nNodes = nNodes;
        this.time = time;
        this.level = level;
        this.bestScore = bestScore;
    }

    /* -------------------------------------------------------------------- */
    /* ------------------------------- METHODS ---------------------------- */
    /* -------------------------------------------------------------------- */

    /**
     * This method print the values to the standard output
     * @param head TRUE to stamp the head, else FALSE
     */
    public void print(boolean head){
        if(head){
            System.out.format("%s", "NODES");
            System.out.format("%s", "TIME");
            System.out.format("%s", "BEST SCORE");
            System.out.format("%s", "LEVEL");
            System.out.println();
        }
        System.out.format("%-10d", this.getnNodes());
        System.out.format("%-10d", this.getTime());
        System.out.format("%-10f", this.getBestScore());
        System.out.format("%-10d", this.getLevel());
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
     * @return Time
     */
    public int getTime(){
        return this.time;
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
}
