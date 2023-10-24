import logic.DepthFirstAlgorithm;
import logic.LogicViewFrame;
import view.FrameView;

import java.util.Stack;

public class TestConsole {
    public static void main(String[] args) {
        int numDiscs = 5; // Número de discos en la Torre de Hanoi

        //Torre Inicial y objetivo
        Stack<Integer> tower1 = new Stack<>();
        for (int i = numDiscs; i >= 1; i--) {
            tower1.push(i);
        }

        DepthFirstAlgorithm logic = new DepthFirstAlgorithm(tower1, tower1);
        logic.start();
        logic.printSolution();

    }
}
