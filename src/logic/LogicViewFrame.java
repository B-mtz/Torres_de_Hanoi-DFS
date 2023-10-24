package logic;

import model.Node;
import view.FrameView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

public class LogicViewFrame extends Thread implements ActionListener {
    FrameView frameView;
    Stack<Node> route;
    DepthFirstAlgorithm depthFirstAlgorithm;

    public LogicViewFrame(FrameView frameView) {
        this.frameView = frameView;
        this.route = new Stack<>();
        frameView.getBtnStart().addActionListener(this);
        startAnimation();
    }

    public void startAnimation(){
        int numDiscs = 3; // Número de discos en la Torre de Hanoi
        //Torre Inicial y objetivo
        Stack<Integer> tower1 = new Stack<>();
        for (int i = numDiscs; i >= 1; i--) {
            tower1.push(i);
        }
        depthFirstAlgorithm = new DepthFirstAlgorithm(tower1, tower1);
        depthFirstAlgorithm.start();
        route = depthFirstAlgorithm.getRoute();
        this.start();
    }
    public void printConsele(){
        depthFirstAlgorithm.start();
        depthFirstAlgorithm.printSolution();
    }

    public void run() {
        while (!route.isEmpty()) {
            Node aux = route.pop();
            frameView.generateRoute(aux);
            frameView.repaint();
            try {
                Thread.sleep(1000); // Agrega un retraso de 500 milisegundos
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        }
        printConsele();
    }

    //Reinicia la animación
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(frameView.getBtnStart())) {
            frameView.dispose();
            LogicViewFrame logicViewFrame = new LogicViewFrame(new FrameView());
        }
    }
}
