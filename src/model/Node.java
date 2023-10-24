package model;

import java.util.Stack;

public class Node {
    private Stack<Integer> tower1,tower2,tower3;
    private int cost;
    private Node parent;

    //Constructor para inicializar estado inicial
    public Node(Stack<Integer> tower1){
        this.tower1 = tower1;
        this.tower2 = new Stack<>();
        this.tower3 = new Stack<>();
        this.cost = 0;
        this.parent = null;
    }
    //Constructor para inicializar estados nuevos
    public Node(Stack<Integer> tower1, Stack<Integer> tower2,Stack<Integer> tower3, int cost, Node parent){
        this.tower1 = tower1;
        this.tower2 = tower2;
        this.tower3 = tower3;
        this.cost = cost;
        this.parent = parent;
    }

    public void printTowers(){
        System.out.print("   Torre 1 :  ");
        System.out.println(tower1.toString());
        System.out.print("   Torre 2 :  ");
        System.out.println(tower2.toString());
        System.out.print("   Torre 3 :  ");
        System.out.println(tower3.toString());
        System.out.println("");
    }

    public Stack<Integer> getTower1() {
        return tower1;
    }

    public Stack<Integer> getTower2() {
        return tower2;
    }

    public Stack<Integer> getTower3() {
        return tower3;
    }

    public int getCost() {
        return cost;
    }

    public Node getParent() {
        return parent;
    }
}
