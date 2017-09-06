package config;

/**
 * This class extends the {@link AbsConfig}
 *
 * @author Davide Rigoni
 */
public class Config extends AbsConfig {
    /**
     * Constructor
     */
    public Config(){}

    /**
     * Constructor
     * @param firstPlayer
     * @param nRows
     * @param nLockedCells
     */
    public Config(int firstPlayer, int nRows, int nLockedCells){
        super(firstPlayer,nRows,nLockedCells);
    }

    /**
     * Constructor
     * @param firstPlayer
     * @param nRows
     * @param nLockedCells
     * @param algorithm
     * @param EF
     */
    public Config(int firstPlayer, int nRows, int nLockedCells, int algorithm, int EF, int depth){
        super(firstPlayer,nRows,nLockedCells,algorithm,EF,depth);
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
    public Config(int firstPlayer, int nRows, int nLockedCells,
                     int algorithmP0, int EFP0, int depthP0,
                     int algorithmP1, int EFP1, int depthP1){
        super(firstPlayer,nRows,nLockedCells,
                algorithmP0,EFP0,depthP0,
                algorithmP1,EFP1,depthP1);
    }
}
