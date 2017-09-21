/**
  Stub main 3
 */


import config.AbsConfig;
import config.Config;
import ticTacToe.AbsResult;
import ticTacToe.StatisticsTicTacToe2AI;


public class StatisticsMain4 {
    public static void main(String[] args) {
        String nameFile = "statisticsMain4.txt";
        printFile(nameFile,"----START-----");
        thirdPart(nameFile);
        printFile(nameFile,"----END-----");
    }


    public static void printFile(String path, String message){
        StatisticsMain1.printFile(path,message);
    }

    public  static void thirdPart(String nameFile){
        AbsConfig config0 = new Config(0,5,0,0,0,5,0,0,1);
        AbsConfig config1 = new Config(0,5,0,1,0,5,0,0,1);
        AbsConfig config2 = new Config(0,5,0,2,0,5,0,0,1);
        AbsConfig config3 = new Config(0,5,0,3,0,5,0,0,1);

        //Node statistics
        AbsResult res0;
        AbsResult res1;
        AbsResult res2;
        AbsResult res3;

        StatisticsTicTacToe2AI ticaTacToe = new StatisticsTicTacToe2AI(config0);
        ticaTacToe.start();
        res0 = ticaTacToe.getResultsP0().get(0);
        printFile(nameFile,"NODE STATISTICS 5x5 alg0 time: " + res0.getTime());
        printFile(nameFile,"NODE STATISTICS 5x5 alg0 node: " + res0.getnNodes());
        printFile(nameFile,"NODE STATISTICS 5x5 alg0 best score: " + res0.getBestScore());
        printFile(nameFile,"NODE STATISTICS 5x5 alg0 level: " + res0.getLevel());

        ticaTacToe = new StatisticsTicTacToe2AI(config2);
        ticaTacToe.start();
        res2 = ticaTacToe.getResultsP0().get(0);
        printFile(nameFile,"NODE STATISTICS 5x5 alg2 time: " + res2.getTime());
        printFile(nameFile,"NODE STATISTICS 5x5 alg2 node: " + res2.getnNodes());
        printFile(nameFile,"NODE STATISTICS 5x5 alg2 best score: " + res2.getBestScore());
        printFile(nameFile,"NODE STATISTICS 5x5 alg2 level: " + res2.getLevel());



        ticaTacToe = new StatisticsTicTacToe2AI(config1);
        ticaTacToe.start();
        res1 = ticaTacToe.getResultsP0().get(0);
        printFile(nameFile,"NODE STATISTICS 5x5 alg1 time: " + res1.getTime());
        printFile(nameFile,"NODE STATISTICS 5x5 alg1 node: " + res1.getnNodes());
        printFile(nameFile,"NODE STATISTICS 5x5 alg1 best score: " + res1.getBestScore());
        printFile(nameFile,"NODE STATISTICS 5x5 alg1 level: " + res1.getLevel());


        ticaTacToe = new StatisticsTicTacToe2AI(config3);
        ticaTacToe.start();
        res3 = ticaTacToe.getResultsP0().get(0);
        printFile(nameFile,"NODE STATISTICS 5x5 alg3 time: " + res3.getTime());
        printFile(nameFile,"NODE STATISTICS 5x5 alg3 node: " + res3.getnNodes());
        printFile(nameFile,"NODE STATISTICS 5x5 alg3 best score: " + res3.getBestScore());
        printFile(nameFile,"NODE STATISTICS 5x5 alg3 level: " + res3.getLevel());
    }
}
