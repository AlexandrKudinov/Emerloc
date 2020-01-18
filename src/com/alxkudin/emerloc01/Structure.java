package com.alxkudin.emerloc01;


import java.util.HashMap;
import java.util.Map;

import static com.alxkudin.emerloc01.LocType.*;
import static com.alxkudin.emerloc01.NodeType.HOUSE_BLOCK;


public class Structure {
    private static volatile Structure INSTANCE;

    private Structure() {
    }

    public static Structure getInstance() {
        Structure structure = INSTANCE;
        if (structure == null) {
            synchronized (Structure.class) {
                structure = INSTANCE;
                if (structure == null) {
                    structure = INSTANCE = new Structure();
                }
            }
        }
        return structure;
    }

    private int height = 60;
    private int width = 120;
    private Node[][] map = new Node[height][width];
    public Map<Node, House> houses = new HashMap<>();


    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public Node[][] getMap() {
        return map;
    }

    public void bind() {

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                map[i][j] = new Node(i,j);

            }
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Node node = map[i][j];
                node.setUpNode((i == 0) ? map[height - 1][j] : map[i - 1][j]);
                node.setDownNode((i == height - 1) ? map[0][j] : map[i + 1][j]);
                node.setLeftNode((j == 0) ? map[i][width - 1] : map[i][j - 1]);
                node.setRightNode((j == width - 1) ? map[i][0] : map[i][j + 1]);
            }
        }
    }


    public void buildHouseBlocks() {
        Node node;
        map[0][0].setType(HOUSE_BLOCK);  // set corner nodes type
        map[height - 1][0].setType(HOUSE_BLOCK);
        map[0][width - 1].setType(HOUSE_BLOCK);
        map[height - 1][width - 1].setType(HOUSE_BLOCK);
        map[0][1].setRandomAsHouseBlock();
        map[height - 1][1].setTypeSame(map[0][1]);
        map[1][0].setRandomAsHouseBlock();
        map[1][width - 1].setTypeSame(map[1][0]);

        for (int i = 2; i < width - 1; i++) { // set first & last line
            node = map[0][i];
            if (!node.verify(HOUSE_BLOCK, LEFT)) {
                node.setType(HOUSE_BLOCK);
                map[height - 1][i].setTypeSame(node);
                continue;
            }
            if (node.verify(HOUSE_BLOCK, LEFT) && node.verify(HOUSE_BLOCK, LEFT, LEFT)) {
                continue;
            }
            if (node.verify(HOUSE_BLOCK, RIGHT) && node.verify(HOUSE_BLOCK, RIGHT, RIGHT)) {
                continue;
            }
            node.setRandomAsHouseBlock();
            map[height - 1][i].setTypeSame(node);
        }

        for (int i = 2; i < height - 1; i++) { //set first and last column
            node = map[i][0];
            if (!node.verify(HOUSE_BLOCK, UP)) {
                node.setType(HOUSE_BLOCK);
                map[i][width - 1].setTypeSame(node);
                continue;
            }
            if (node.verify(HOUSE_BLOCK, UP) && node.verify(HOUSE_BLOCK, UP, UP)) {
                continue;
            }
            if (node.verify(HOUSE_BLOCK, DOWN) && node.verify(HOUSE_BLOCK, DOWN, DOWN)) {
                continue;
            }
            node.setRandomAsHouseBlock();
            map[i][width - 1].setTypeSame(node);
        }

        for (int i = 1; i < width - 1; i++) { //set second line
            node = map[1][i];
            if (!node.verify(HOUSE_BLOCK, UP) || node.verify(HOUSE_BLOCK, RIGHT, DOWN) ||
                    node.verify(HOUSE_BLOCK, LEFT, DOWN)) {
                continue;
            }
            node.setRandomAsHouseBlock();
        }

        for (int i = 1; i < width - 1; i++) { //set before last line
            node = map[height - 2][i];
            if (!node.verify(HOUSE_BLOCK, DOWN) || node.verify(HOUSE_BLOCK, RIGHT, UP) ||
                    node.verify(HOUSE_BLOCK, LEFT, UP)) {
                continue;
            }
            map[height - 2][i].setRandomAsHouseBlock();
        }

        for (int i = 2; i < height - 2; i++) { //set second column
            node = map[i][1];
            if (check_A(node) || (!node.verify(HOUSE_BLOCK, LEFT))) {
                continue;
            }
            node.setRandomAsHouseBlock();
        }

        for (int i = 2; i < height - 2; i++) {  //set before last column
            node = map[i][width - 2];
            if (check_A(node) || !node.verify(HOUSE_BLOCK, RIGHT)) {
                continue;
            }
            node.setRandomAsHouseBlock();
        }

        for (int i = 2; i < height - 2; i++) {  //set the main square
            for (int j = 2; j < width - 2; j++) {
                node = map[i][j];
                if (check_E(node)) {
                    node.setType(HOUSE_BLOCK);
                    continue;
                }
                if (check_D(node)) {
                    continue;
                }
                node.setRandomAsHouseBlock();
            }
        }

        for (int i = 0; i < height; i++) {   //fill spaces not surrounded  with HOUSE_BLOCKS
            for (int j = 0; j < width; j++) {
                node = map[i][j];
                if (!node.verify(HOUSE_BLOCK)) {
                    if (check_B(node) || check_C(node)) {
                        node.setType(HOUSE_BLOCK);
                    }
                }
            }
        }

    }

    public void buildHouses() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Node node = map[i][j];
                if (node.typeIs(HOUSE_BLOCK)) {
                    House house = new House();
                    house.add(node,i,j,width,height);
                    houses.put(map[i][j], house);
                }
            }
        }
        System.out.println(houses.size());
    }


    private boolean check_A(Node node) {
        return (node.verify(HOUSE_BLOCK, LEFT, UP) && node.verify(HOUSE_BLOCK, LEFT, UP, UP)) ||
                (node.verify(HOUSE_BLOCK, RIGHT, UP) && node.verify(HOUSE_BLOCK, RIGHT, UP, UP)) ||
                (node.verify(HOUSE_BLOCK, LEFT, DOWN) && node.verify(HOUSE_BLOCK, LEFT, DOWN, DOWN)) ||
                (node.verify(HOUSE_BLOCK, RIGHT, DOWN) && node.verify(HOUSE_BLOCK, RIGHT, DOWN, DOWN));
    }

    private boolean check_B(Node node) {
        if (node.verify(HOUSE_BLOCK, LEFT) && node.verify(HOUSE_BLOCK, LEFT, UP) &&
                node.verify(HOUSE_BLOCK, UP) && !node.verify(HOUSE_BLOCK, RIGHT) &&
                !node.verify(HOUSE_BLOCK, RIGHT, DOWN) && !node.verify(HOUSE_BLOCK, DOWN)) {
            return true;
        }

        if (node.verify(HOUSE_BLOCK, UP) && node.verify(HOUSE_BLOCK, UP, RIGHT) &&
                node.verify(HOUSE_BLOCK, RIGHT) && !node.verify(HOUSE_BLOCK, LEFT) &&
                !node.verify(HOUSE_BLOCK, LEFT, DOWN) && !node.verify(HOUSE_BLOCK, DOWN)) {
            return true;
        }

        if (node.verify(HOUSE_BLOCK, DOWN) && node.verify(HOUSE_BLOCK, RIGHT, DOWN) &&
                node.verify(HOUSE_BLOCK, RIGHT) && node.verify(HOUSE_BLOCK, LEFT) &&
                node.verify(HOUSE_BLOCK, LEFT, UP) && node.verify(HOUSE_BLOCK, UP)) {
            return true;
        }

        if (node.verify(HOUSE_BLOCK, LEFT) && node.verify(HOUSE_BLOCK, LEFT, DOWN) &&
                node.verify(HOUSE_BLOCK, DOWN) && node.verify(HOUSE_BLOCK, RIGHT) &&
                !node.verify(HOUSE_BLOCK, RIGHT, UP) && !node.verify(HOUSE_BLOCK, UP)) {
            return true;
        }

        if (node.verify(HOUSE_BLOCK, UP) && node.verify(HOUSE_BLOCK, LEFT) &&
                node.verify(HOUSE_BLOCK, LEFT, UP) && node.verify(HOUSE_BLOCK, RIGHT) &&
                node.verify(HOUSE_BLOCK, RIGHT)) {
            return true;
        }
        if (node.verify(HOUSE_BLOCK, DOWN) && node.verify(HOUSE_BLOCK, LEFT, DOWN) &&
                node.verify(HOUSE_BLOCK, LEFT) && node.verify(HOUSE_BLOCK, LEFT, UP) &&
                node.verify(HOUSE_BLOCK, UP)) {
            return true;
        }
        return false;
    }


    private boolean check_C(Node node) {
        if (node.verify(HOUSE_BLOCK, DOWN) && !node.verify(HOUSE_BLOCK, LEFT, UP) &&
                !node.verify(HOUSE_BLOCK, UP) && !node.verify(HOUSE_BLOCK, RIGHT, UP)) {
            return true;
        }

        if (node.verify(HOUSE_BLOCK, RIGHT) && !node.verify(HOUSE_BLOCK, LEFT, UP) &&
                !node.verify(HOUSE_BLOCK, LEFT) && !node.verify(HOUSE_BLOCK, LEFT, DOWN)) {
            return true;
        }
        if (node.verify(HOUSE_BLOCK, LEFT) && !node.verify(HOUSE_BLOCK, RIGHT, UP) &&
                !node.verify(HOUSE_BLOCK, RIGHT) && !node.verify(HOUSE_BLOCK, RIGHT, DOWN)) {
            return true;
        }
        if (node.verify(HOUSE_BLOCK, UP) && !node.verify(HOUSE_BLOCK, LEFT, DOWN) &&
                !node.verify(HOUSE_BLOCK, DOWN) && !node.verify(HOUSE_BLOCK, RIGHT, DOWN)) {
            return true;
        }
        return false;
    }

    private boolean check_D(Node node) {
        return (node.verify(HOUSE_BLOCK, LEFT) && node.verify(HOUSE_BLOCK, LEFT, LEFT)) ||
                (node.verify(HOUSE_BLOCK, UP) && node.verify(HOUSE_BLOCK, UP, UP)) ||
                (node.verify(HOUSE_BLOCK, DOWN) && node.verify(HOUSE_BLOCK, DOWN, DOWN)) ||
                (node.verify(HOUSE_BLOCK, RIGHT) && node.verify(HOUSE_BLOCK, RIGHT, RIGHT)) ||
                (node.verify(HOUSE_BLOCK, RIGHT, UP)) ||
                (node.verify(HOUSE_BLOCK, RIGHT, DOWN)) ||
                (node.verify(HOUSE_BLOCK, LEFT, UP)) ||
                (node.verify(HOUSE_BLOCK, LEFT, DOWN));
    }

    private boolean check_E(Node node) {
        return (!node.verify(HOUSE_BLOCK, LEFT) && !node.verify(HOUSE_BLOCK, LEFT, LEFT)) &&
                (!node.verify(HOUSE_BLOCK, UP) && !node.verify(HOUSE_BLOCK, UP, UP)) &&
                (!node.verify(HOUSE_BLOCK, DOWN) && !node.verify(HOUSE_BLOCK, DOWN, DOWN)) &&
                (!node.verify(HOUSE_BLOCK, RIGHT) && !node.verify(HOUSE_BLOCK, RIGHT, RIGHT)) &&
                (!node.verify(HOUSE_BLOCK, RIGHT, UP)) &&
                (!node.verify(HOUSE_BLOCK, RIGHT, DOWN)) &&
                (!node.verify(HOUSE_BLOCK, LEFT, UP)) &&
                (!node.verify(HOUSE_BLOCK, LEFT, DOWN));
    }




}

