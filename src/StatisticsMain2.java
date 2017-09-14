/**
  Stub main 3
 */


import config.AbsConfig;
import config.Config;
import ticTacToe.TicTacToe2AI;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.BigInteger;


public class StatisticsMain2 {
    public static void main(String[] args) {
        String nameFile = "statisticsMain2.txt";
        int nRip = 1000;
        StatisticsMain1.printFile(nameFile,"----START-----");
        secondPart(nameFile, nRip);
        StatisticsMain1.printFile(nameFile,"----END-----");
    }

    public  static void secondPart(String nameFile, int nRip){
        AbsConfig config0 = new Config(0,5,0,0,0,100,0,0,1);
        AbsConfig config1 = new Config(0,5,0,1,0,100,0,0,1);
        AbsConfig config2 = new Config(0,5,0,2,0,100,0,0,1);
        AbsConfig config3 = new Config(0,5,0,3,0,100,0,0,1);


        StatisticsMain1.printFile(nameFile,"TIME STATISTICS 5x5 alg0: " + StatisticsMain1.cycle(config0,nRip));
        StatisticsMain1.printFile(nameFile,"TIME STATISTICS 5x5 alg1: " + StatisticsMain1.cycle(config1,nRip));
        StatisticsMain1.printFile(nameFile,"TIME STATISTICS 5x5 alg2: " + StatisticsMain1.cycle(config2,nRip));
        StatisticsMain1.printFile(nameFile,"TIME STATISTICS 5x5 alg3: " + StatisticsMain1.cycle(config3,nRip));
    }
}
