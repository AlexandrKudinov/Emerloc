package com.alxkudin.emerloc01;

import java.util.*;

import static com.alxkudin.emerloc01.MapStructure.*;


public class HousesMap {
    public Map<Node, House> houses = new HashMap<>();

    public void buildHouseBlocks() {
        INSTANCE.get(0, 0).  setType(NodeType.HOUSE_BLOCK);  // set corner nodes type
        INSTANCE.get(0, HEIGHT - 1).setType(NodeType.HOUSE_BLOCK);
        INSTANCE.get(WIDTH - 1, 0).setType(NodeType.HOUSE_BLOCK);
        INSTANCE.get(WIDTH - 1, HEIGHT - 1).setType(NodeType.HOUSE_BLOCK);
        INSTANCE.get(1, 0).setRandomAsHouseBlock();
        INSTANCE.get(1, HEIGHT - 1).setEqualType(INSTANCE.get(1, 0));
        INSTANCE.get(0, 1).setRandomAsHouseBlock();
        INSTANCE.get(WIDTH - 1, 1).setEqualType(INSTANCE.get(0, 1));

        for (int i = 2; i < WIDTH - 1; i++) { // set first & last line
            if (!INSTANCE.get(i, 0).leftNodeTypeIs(NodeType.HOUSE_BLOCK)) {
                INSTANCE.get(i, 0).setType(NodeType.HOUSE_BLOCK);
                INSTANCE.get(i, HEIGHT - 1).setEqualType(INSTANCE.get(i, 0));
                continue;
            }
            if (INSTANCE.get(i, 0).twoLeftNodesTypesAre(NodeType.HOUSE_BLOCK)) {
                continue;
            }
            if (INSTANCE.get(i, 0).twoRightNodesTypesAre(NodeType.HOUSE_BLOCK)) {
                continue;
            }
            INSTANCE.get(i, 0).setRandomAsHouseBlock();
            INSTANCE.get(i, HEIGHT - 1).setEqualType(INSTANCE.get(i, 0));
        }

        for (int i = 2; i < HEIGHT - 1; i++) { //set first and last column
            if (!INSTANCE.get(0, i).upNodeTypeIs(NodeType.HOUSE_BLOCK)) {
                INSTANCE.get(0, i).setType(NodeType.HOUSE_BLOCK);
                INSTANCE.get(WIDTH - 1, i).setEqualType(INSTANCE.get(0, i));
                continue;
            }
            if (INSTANCE.get(0, i).twoUpNodesTypesAre(NodeType.HOUSE_BLOCK)) {
                continue;
            }
            if (INSTANCE.get(0, i).twoDownNodesTypesAre(NodeType.HOUSE_BLOCK)) {
                continue;
            }
            INSTANCE.get(0, i).setRandomAsHouseBlock();
            INSTANCE.get(WIDTH - 1, i).setEqualType(INSTANCE.get(0, i));
        }

        for (int i = 1; i < WIDTH - 1; i++) { //set second line
            if (INSTANCE.get(i, 1).leftDownNodeTypeIs(NodeType.HOUSE_BLOCK) ||
                    (!INSTANCE.get(i, 0).thisAndLeftNodesTypesAre(NodeType.HOUSE_BLOCK)) ||
                    (INSTANCE.get(i - 1, 1).isHouseBlock() &&
                            INSTANCE.get(i, 0).thisAndLeftNodesTypesAre(NodeType.HOUSE_BLOCK))) {
                continue;
            }
            INSTANCE.get(i, 1).setRandomAsHouseBlock();
        }

        for (int i = 1; i < WIDTH - 1; i++) { //set before last line
            if ((INSTANCE.get(i, HEIGHT - 1).getLeftNode().isHouseBlock()) ||
                    (INSTANCE.get(i, HEIGHT - 2).getRightNode().getUpNode().isHouseBlock())) {
                continue;
            }
            INSTANCE.get(i, HEIGHT - 2).setRandomAsHouseBlock();
        }

        for (int i = 2; i < HEIGHT - 2; i++) { //set second column
            if ((INSTANCE.get(1, i).columnNodeAreaContainHouseBlocks() ||
                    !INSTANCE.get(1, i).twoLeftNodesTypesAre(NodeType.HOUSE_BLOCK))) {
                continue;
            }
            INSTANCE.get(1, i).setRandomAsHouseBlock();
        }

        for (int i = 2; i < HEIGHT - 2; i++) {  //set before last column
            if ((INSTANCE.get(WIDTH - 2, i).columnNodeAreaContainHouseBlocks() ||
                    (!INSTANCE.get(WIDTH - 2, i).getRightNode().thisAndUpNodesTypeIs(NodeType.HOUSE_BLOCK)))) {
                continue;
            }
            INSTANCE.get(WIDTH - 2, i).setRandomAsHouseBlock();

        }

        for (int j = 2; j < HEIGHT - 2; j++) {  //set the main square
            for (int i = 2; i < WIDTH - 2; i++) {
                Node node = INSTANCE.get(i, j);
                if (node.areaIsEmpty()) {
                    node.setType(NodeType.HOUSE_BLOCK);
                    continue;
                }
                if (node.areaContainHouseBlocks()) {
                    continue;
                }
                node.setRandomAsHouseBlock();
            }
        }

        for (int j = 0; j < HEIGHT; j++) {   //fill spaces surrounded not with HOUSE_BLOCKS
            for (int i = 0; i < WIDTH; i++) {
                Node node = INSTANCE.get(i, j);
                if (!node.typeIs(NodeType.HOUSE_BLOCK)) {
                    if (node.houseBlockCanBeAdded()) {
                        node.setType(NodeType.HOUSE_BLOCK);
                    }
                    if (node.partOfAreaIsEmpty()) {
                        node.setType(NodeType.HOUSE_BLOCK);
                    }
                }
            }
        }
    }


    public void unionHouses() {
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                if (INSTANCE.get(i, j).typeIs(NodeType.HOUSE_BLOCK)) {
                    House house = new House();
                    house.add(INSTANCE.get(i, j));
                    houses.put(INSTANCE.get(i, j), house);
                    System.out.println(house.toString());
                }
            }
            System.out.println("new string");
        }
        System.out.println(houses.size());

        System.out.println();

    }

}
