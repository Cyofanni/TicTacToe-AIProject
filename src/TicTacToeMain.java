import AI.EF.AdvanceEF;
import AI.EF.IEvalFunction;
import AI.EF.SimpleEF;
import AI.algorithms.*;
import config.AbsConfig;
import config.Config;
import ticTacToe.*;

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
        AbsTicTacToe ticaTacToe;
        AbsConfig config = new Config();

        //Take the config
        config.startInitialConfig();
        //Check for the type of play
        if(!config.getPlayer0AI() && !config.getPlayer1AI()) {
            //No AI
            ticaTacToe = new TicTacToe(config);
        } else{
            //Only one AI
            if(!config.getPlayer0AI()) {
                ticaTacToe = new TicTacToeAI(config);
            }else{
                //2 AI
                ticaTacToe = new TicTacToe2AI(config);
            }
        }

        ticaTacToe.start();
    }
}
