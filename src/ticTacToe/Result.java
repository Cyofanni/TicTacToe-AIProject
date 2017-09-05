package ticTacToe;

/**
 * <b>This class represent a concrete result
 * that extends {@link AbsResult AbsResult}</b>
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
public class Result extends AbsResult {

    /**
     * Constructor
     *
     * @param nNodes Number of nodes
     * @param startTime Time
     * @param endTime Time
     * @param level Level of the node nearest the root
     * @param bestScore Best score
     */
    public Result(int nNodes, long startTime, long endTime , int level, double bestScore){
        super(nNodes, startTime, endTime, level, bestScore);
    }

    /**
     * Constructor
     */
    public Result(){}
}
