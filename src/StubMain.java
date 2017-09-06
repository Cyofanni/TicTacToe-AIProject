/**
  Stub main 
 */


import config.AbsConfig;
import config.Config;
import ticTacToe.AbsTicTacToe;
import ticTacToe.TicTacToe;
import ticTacToe.TicTacToeAI;


public class StubMain{
    public static void main(String[] args) {
        AbsConfig config0 = new Config(0,3,0,0,0,100);
        AbsConfig config1 = new Config(0,3,0,1,0,100);
        AbsConfig config2 = new Config(0,3,0,2,0,100);
        AbsConfig config3 = new Config(0,3,0,3,0,100);
        AbsTicTacToe ticaTacToe0 = new TicTacToeAI(config0);
        ticaTacToe0.start();

        AbsTicTacToe ticaTacToe1 = new TicTacToeAI(config1);
        ticaTacToe1.start();

        AbsTicTacToe ticaTacToe2 = new TicTacToeAI(config2);
        ticaTacToe2.start();

        AbsTicTacToe ticaTacToe3 = new TicTacToeAI(config3);
        ticaTacToe3.start();
    }
}
