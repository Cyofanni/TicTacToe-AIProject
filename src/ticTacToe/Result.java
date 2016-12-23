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
     * @param nNodes    Number of nodes
     * @param time      Time
     * @param level     Level of the node nearest the root
     * @param bestScore Best score
     */
    public Result(int nNodes, int time, int level, double bestScore) {
        super(nNodes, time, level, bestScore);
    }
}
