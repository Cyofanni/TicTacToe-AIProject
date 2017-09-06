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
}
