package ticTacToe;

/**
 * This class represents a move
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
public class AbsMove {
    /**
     * Row
     */
    private int x;
    /**
     * Column
     */
    private int y;

    /**
     * Constructor
     *
     * @param x Row
     * @param y Column
     */
    public AbsMove(int x, int y){
        this.x = x;
        this.y = y;
    }

    /**
     * This method allow the access to the number of the row
     * @return Row
     */
    public int getX(){
        return x;
    }

    /**
     * This method allow the access to the number of the column
     * @return Column
     */
    public int getY(){
        return y;
    }

}