import config.AbsConfig;
import config.Config;
import ticTacToe.AbsTicTacToe;
import ticTacToe.TicTacToe;

/**
 * The main class of the tic-tac-toe games
 *
 * @author Davide Rigoni, Giovanni Mazzocchin, Alex Beccaro
 */
public class TicTacToeMain {
    /**
     * The main class of the game
     * @param args Params passed at the beginning
     */
    public static void main(String[] args) {
        AbsConfig config = new Config();
        config.startInitialConfig();
        AbsTicTacToe ticaTacToe = new TicTacToe(config);
        ticaTacToe.start();
    }
}
