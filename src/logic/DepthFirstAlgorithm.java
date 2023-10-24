package logic;

import model.Node;

import java.util.Stack;

public class DepthFirstAlgorithm {
    private Stack<Integer> initialState,goalState;
    private Stack<Node> stack, visited, route;
    private Node goal,initial;

    public DepthFirstAlgorithm(Stack<Integer> tower1, Stack<Integer> tower2){
        this.initialState = tower1;
        this.goalState = tower2;
        this.stack = new Stack<>();
        this.visited = new Stack<>();
        this.route = new Stack<>();
        initial = new Node(initialState);
        goal = new Node(new Stack<Integer>(),new Stack<Integer>(),goalState,0, initial);
        stack.add(initial);
        printTitle();
    }
    private void printTitle(){
        System.out.println("Estado inicial");
        stack.peek().printTowers();
        System.out.println("Estado Objetivo");
        goal.printTowers();
        System.out.print("Buscando...");
    }
    //se implementa el algoritmo
    public void start(){

        while (!stack.isEmpty()) {
            Node currentNode = stack.pop();

            if (areStacksEqual(currentNode.getTower3(),goalState)) {
                System.out.println("!!Encontrado!!!");
                // Hemos encontrado la solución, reconstruir la secuencia de movimientos aquí.
                route.push(currentNode);
                Node parent = currentNode.getParent();
                while (parent != null) {
                    route.push(parent);
                    parent = parent.getParent();
                }
                return;
            }
            // Genera y agrega los nodos hijos válidos a la pila.
            generateAndAddValidChildNodes(currentNode);
            visited.add(currentNode);
        }
    }
    private void generateAndAddValidChildNodes(Node currentNode) {
        Stack<Integer> tower1 = currentNode.getTower1();
        Stack<Integer> tower2 = currentNode.getTower2();
        Stack<Integer> tower3 = currentNode.getTower3();

        // Para cada torre, se verifica si es posible mover un disco a otra torre.
        // Si es posible se genera un nuevo nodo para cada movimiento válido.

        // Desde la torre 1:
        if (!tower1.isEmpty()) {
            int topDisk = tower1.peek();
            // Mover a la torre 2 si es válido.
            if (tower2.isEmpty() || topDisk < tower2.peek()) {
                Stack<Integer> newTower1 = new Stack<>();
                newTower1.addAll(tower1);
                Stack<Integer> newTower2 = new Stack<>();
                newTower2.addAll(tower2);
                newTower2.push(newTower1.pop());

                Node newNode = new Node(newTower1, newTower2, tower3, currentNode.getCost() + 1, currentNode);
                if (!isInVisited(newNode) && !isInStack(newNode)) {
                    stack.push(newNode);
                }
            }
            // Mover a la torre 3 si es válido.
            if (tower3.isEmpty() || topDisk < tower3.peek()) {
                Stack<Integer> newTower1 = new Stack<>();
                newTower1.addAll(tower1);
                Stack<Integer> newTower3 = new Stack<>();
                newTower3.addAll(tower3);
                newTower3.push(newTower1.pop());

                Node newNode = new Node(newTower1, tower2, newTower3, currentNode.getCost() + 1, currentNode);
                if (!isInVisited(newNode) && !isInStack(newNode)) {
                    stack.push(newNode);
                }
            }
        }
        // Desde la torre 2:
        if (!tower2.isEmpty()) {
            int topDisk = tower2.peek();
            // Mover a la torre 1 si es válido.
            if (tower1.isEmpty() || topDisk < tower1.peek()) {
                Stack<Integer> newTower2 = new Stack<>();
                newTower2.addAll(tower2);
                Stack<Integer> newTower1 = new Stack<>();
                newTower1.addAll(tower1);
                newTower1.push(newTower2.pop());

                Node newNode = new Node(newTower1, newTower2, tower3, currentNode.getCost() + 1, currentNode);
                if (!isInVisited(newNode) && !isInStack(newNode)) {
                    stack.push(newNode);
                }
            }
            // Mover a la torre 3 si es válido.
            if (tower3.isEmpty() || topDisk < tower3.peek()) {
                Stack<Integer> newTower2 = new Stack<>();
                newTower2.addAll(tower2);
                Stack<Integer> newTower3 = new Stack<>();
                newTower3.addAll(tower3);
                newTower3.push(newTower2.pop());

                Node newNode = new Node(tower1, newTower2, newTower3, currentNode.getCost() + 1, currentNode);
                if (!isInVisited(newNode) && !isInStack(newNode)) {
                    stack.push(newNode);
                }
            }
        }
        // Desde la torre 3:
        if (!tower3.isEmpty()) {
            int topDisk = tower3.peek();
            // Mover a la torre 1 si es válido.
            if (tower1.isEmpty() || topDisk < tower1.peek()) {
                Stack<Integer> newTower3 = new Stack<>();
                newTower3.addAll(tower3);
                Stack<Integer> newTower1 = new Stack<>();
                newTower1.addAll(tower1);
                newTower1.push(newTower3.pop());

                Node newNode = new Node(newTower1, tower2, newTower3, currentNode.getCost() + 1, currentNode);
                if (!isInVisited(newNode) && !isInStack(newNode)) {
                    stack.push(newNode);
                }
            }
            // Mover a la torre 2 si es válido.
            if (tower2.isEmpty() || topDisk < tower2.peek()) {
                Stack<Integer> newTower3 = new Stack<>();
                newTower3.addAll(tower3);
                Stack<Integer> newTower2 = new Stack<>();
                newTower2.addAll(tower2);
                newTower2.push(newTower3.pop());

                Node newNode = new Node(tower1, newTower2, newTower3, currentNode.getCost() + 1, currentNode);
                if (!isInVisited(newNode) && !isInStack(newNode)) {
                    stack.push(newNode);
                }
            }
        }
    }
    private boolean isInVisited(Node newNode){
        for (Node n : visited) {
            if (areStacksEqual(newNode.getTower1(), n.getTower1()) &&
                    areStacksEqual(newNode.getTower2(), n.getTower2()) &&
                    areStacksEqual(newNode.getTower3(), n.getTower3())) {
                return true; // El nodo ya está en visited.
            }
        }
        return false; // El nodo no está en la visited.
    }
    private boolean isInStack(Node newNode){
        for (Node n : stack) {
            if (areStacksEqual(newNode.getTower1(), n.getTower1()) &&
                    areStacksEqual(newNode.getTower2(), n.getTower2()) &&
                    areStacksEqual(newNode.getTower3(), n.getTower3())) {
                return true; // El nodo ya está en la pila.
            }
        }
        return false; // El nodo no está en la pila.
    }
    private  boolean areStacksEqual(Stack<Integer> stack1, Stack<Integer> stack2) {
        // Paso 1: Verificar la longitud
        if (stack1.size() != stack2.size()) {
            return false;
        }
        // Paso 2: Crear pilas auxiliares
        Stack<Integer> auxStack1 = new Stack<>();
        Stack<Integer> auxStack2 = new Stack<>();
        // Vaciar pilas originales en pilas auxiliares
        auxStack1.addAll(stack1);
        auxStack2.addAll(stack2);
        // Paso 3: Comparar elementos
        while (!auxStack1.isEmpty() && !auxStack2.isEmpty()) {
            Integer elem1 = auxStack1.pop();
            Integer elem2 = auxStack2.pop();
            if (!elem1.equals(elem2)) {
                return false;
            }
        }
        // Si todas las comparaciones son iguales, las pilas son iguales
        return true;
    }
    public void printSolution() {
        if (route.isEmpty()) {
            System.out.println("No se encontró solución.");
        } else {
            System.out.println("\nSolución encontrada en " + route.size() + " pasos:");
            System.out.println("----------------------");
            while (!route.isEmpty()) {
                Node node = route.pop();
                printTowerState(node.getTower1(), "Torre 1");
                printTowerState(node.getTower2(), "Torre 2");
                printTowerState(node.getTower3(), "Torre 3");
                System.out.println("----------------------");
            }
        }
    }
    private void printTowerState(Stack<Integer> tower, String towerName) {
        System.out.print(towerName + ": ");
        if (tower.isEmpty()) {
            System.out.println("[]");
        } else {
            System.out.println(tower.toString());
        }
    }

    public Stack<Node> getRoute() {
        return route;
    }
}
