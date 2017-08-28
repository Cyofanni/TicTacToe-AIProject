/**
  Stub main 
 */
package ticTacToe;

import AI.Algorithms.MinimaxRot;
import AI.EF.SimpleEF;


public class StubMain {
    public static void main(String[] args) {
	SimpleEF f = new SimpleEF();
        MinimaxRot min = new MinimaxRot(4, f);
    }
}
