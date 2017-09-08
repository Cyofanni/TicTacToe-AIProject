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
        int nRip = 100000;
        AbsConfig config0 = new Config(0,3,0,0,0,2,0,0,2);

        ArrayList<ArrayList<AbsResult>> resP0 = new ArrayList<ArrayList<AbsResult>>();
        ArrayList<ArrayList<AbsResult>> resP1 = new ArrayList<ArrayList<AbsResult>>();

        for(int i = 0; i < nRip; i++) {
            TicTacToe2AI ticaTacToe0 = new TicTacToe2AI(config0);
            ticaTacToe0.start();
            resP0.add(ticaTacToe0.getResultsP0());
            resP1.add(ticaTacToe0.getResultsP1());
        }
        
        //Time statistics
        double timeP0=0;
        for (ArrayList<AbsResult> obj: resP0) {
            timeP0 += (obj.get(0)).getTime();
        }

        timeP0 = (double)timeP0 / resP0.size();
        System.out.println("TIME STATISTICS: " + timeP0);
    }
}
