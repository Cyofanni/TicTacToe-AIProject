/**
  Stub main 3
 */


import config.AbsConfig;
import config.Config;
import ticTacToe.AbsResult;
import ticTacToe.TicTacToe2AI;

import java.util.ArrayList;


public class StubMain3 {
    public static void main(String[] args) {
        int nRip = 10000;
        AbsConfig config0 = new Config(0,3,0,0,0,100,0,0,100);

        ArrayList<Long> resP0 = new ArrayList<Long>();

        //Time statistics
        double timeP0=0;

        for(int i = 0; i < nRip; i++) {
            TicTacToe2AI ticaTacToe0 = new TicTacToe2AI(config0);
            ticaTacToe0.start();
            //resP0.add(ticaTacToe0.getResultsP0().get(0).getTime());
            timeP0 += ticaTacToe0.getResultsP0().get(0).getTime();
        }


        timeP0 = (double)timeP0 / nRip;
        System.out.println("TIME STATISTICS: " + timeP0);
    }
}
