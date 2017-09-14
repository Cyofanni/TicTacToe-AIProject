/**
  Stub main 3
 */


import config.AbsConfig;
import config.Config;
import ticTacToe.TicTacToe2AI;
import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;


public class StatisticsMain1 {
    public static void main(String[] args) {
        String nameFile = "statisticsMain1.txt";
        int nRip = 50000;
        printFile(nameFile,"----START-----");
        firstPart(nameFile, nRip);
        printFile(nameFile,"----END-----");
    }

    public  static void firstPart(String nameFile, int nRip){

        AbsConfig config0 = new Config(0,3,0,0,0,100,0,0,1);
        AbsConfig config1 = new Config(0,3,0,1,0,100,0,0,1);
        AbsConfig config2 = new Config(0,3,0,2,0,100,0,0,1);
        AbsConfig config3 = new Config(0,3,0,3,0,100,0,0,1);

        printFile(nameFile,"TIME STATISTICS 3x3 alg0: " + cycle(config0,nRip));
        printFile(nameFile,"TIME STATISTICS 3x3 alg1: " + cycle(config1,nRip));
        printFile(nameFile,"TIME STATISTICS 3x3 alg2: " + cycle(config2,nRip));
        printFile(nameFile,"TIME STATISTICS 3x3 alg3: " + cycle(config3,nRip));
    }

    public static BigDecimal cycle(AbsConfig config, int nRip){
        BigInteger time = BigInteger.valueOf(0);
        BigDecimal bigNRip = BigDecimal.valueOf(nRip);

        for(int i = 0; i < nRip; i++) {
            TicTacToe2AI ticaTacToe = new TicTacToe2AI(config);
            ticaTacToe.start();
            long value = ticaTacToe.getResultsP0().get(0).getTime();
            time = time.add(BigInteger.valueOf(value));
        }
        BigDecimal finalValue = new BigDecimal(time).divide(bigNRip);
        return finalValue;
    }

    public static void printFile(String path, String message){
        PrintWriter writer = null;
        try{
            writer = new PrintWriter(new BufferedWriter(new FileWriter(path, true)));
            writer.println(message);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            System.out.println("Error opening or writing to file.");
        }
    }
}
