package com.alxkudin.emerloc01;

public class MapStructure {
    public MapStructure(){
        this.build();
    }
    public static int WIDTH = 120;
    public static int HEIGHT = 60;
    public static int BLOCK = 10;
    public static final MapStructure INSTANCE = new MapStructure();
    private Node firstInMapStructure;
    private Node firstInLine;
    private Node current;
    private Node previous;
    private  int X;
    private  int Y;

    public void build (){
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                this.add();
            }
        }
    }

    public Node get(int X, int Y) {
        Node current = firstInMapStructure;
        for (int i = 0; i < X; i++) {
            current = current.getRightNode();
        }
        for (int j = 0; j < Y; j++) {
            current = current.getDownNode();
        }
        return current;
    }

    public void add() {
        if (Y == 0) {
            if (X == 0) {
                firstInLine = new Node(X, Y);
                firstInLine.setLeftNode(new Node(WIDTH - 1, Y));
                firstInLine.getLeftNode().setRightNode(firstInLine);
                firstInLine.getLeftNode().setUpNode(new Node(WIDTH - 1, HEIGHT - 1));
                firstInLine.getLeftNode().getUpNode().setDownNode(firstInLine.getLeftNode());
                firstInLine.setUpNode(new Node(X, HEIGHT - 1));
                firstInLine.getUpNode().setDownNode(firstInLine);
                current = firstInLine;
                firstInMapStructure = firstInLine;
                X++;
                return;
            }
            if (X < WIDTH - 1) {
                current.setRightNode(new Node(X, Y));
                current.getRightNode().setLeftNode(current);
                current = current.getRightNode();
                current.setUpNode(new Node(X, HEIGHT - 1));
                current.getUpNode().setDownNode(current);
                X++;
                return;
            }
            if (X == WIDTH - 1) {
                current.setRightNode(firstInLine.getLeftNode());
                firstInLine.getLeftNode().setLeftNode(current);
                X = 0;
                firstInLine.setDownNode(new Node(X, ++Y));
                previous = firstInLine;
                firstInLine = firstInLine.getDownNode();
                firstInLine.setUpNode(previous);
                current = firstInLine;
                return;
            }
        }

        if (Y > 0 && Y < HEIGHT - 1) {
            if (X == WIDTH - 1) {
                current.setRightNode(firstInLine.getLeftNode());
                firstInLine.getLeftNode().setLeftNode(current);
                X = 0;
                if (Y != HEIGHT - 2) {
                    firstInLine.setDownNode(new Node(X, ++Y));
                    firstInLine.getDownNode().setUpNode(firstInLine);
                } else {
                    firstInLine.setDownNode(firstInMapStructure.getUpNode());
                    ++Y;
                }
                firstInLine = firstInLine.getDownNode();
                current = firstInLine;
                return;
            }
            if (X == 0) {
                firstInLine.setLeftNode(new Node(WIDTH - 1, Y));
                firstInLine.getLeftNode().setRightNode(firstInLine);
                firstInLine.getLeftNode().setUpNode(get(WIDTH - 1, Y - 1));
                firstInLine.getLeftNode().getUpNode().setDownNode(firstInLine.getLeftNode());
                current = firstInLine;
                X++;
                return;
            }
            if (X < WIDTH - 1) {
                current.setRightNode(new Node(X, Y));
                current.getRightNode().setLeftNode(current);
                current = current.getRightNode();
                current.setUpNode(get(X, Y - 1));
                current.getUpNode().setDownNode(current);
                X++;
                return;
            }
        }
        if (Y == HEIGHT - 1) {
            if (X == 0) {
                firstInLine.setLeftNode(firstInMapStructure.getLeftNode().getUpNode());
                firstInLine.getLeftNode().setRightNode(firstInLine);
                firstInLine.getLeftNode().setUpNode(get(WIDTH - 1, Y - 1));
                firstInLine.getLeftNode().getUpNode().setDownNode(firstInLine.getLeftNode());
                firstInLine.setUpNode(get(X, Y - 1));
                firstInLine.getUpNode().setDownNode(firstInLine);
                current = firstInLine;
                X++;
                return;
            }
            if (X < WIDTH - 1) {
                current.setRightNode(get(X, 0).getUpNode());
                current.getRightNode().setLeftNode(current);
                current = current.getRightNode();
                current.setUpNode(get(X, Y - 1));
                current.getUpNode().setDownNode(current);
                X++;
                return;
            }
            if (X == WIDTH - 1) {
                current.setRightNode(firstInLine.getLeftNode());
                firstInLine.getLeftNode().setLeftNode(current);
                return;
            }
        }

    }

}






