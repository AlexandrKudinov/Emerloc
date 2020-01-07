package com.alxkudin.emerloc01;

import java.util.LinkedList;
import java.util.List;


public class House {


    private List<Node> houseFragments = new LinkedList<>();

    private List<House> dependantHouses = new LinkedList<>();

    public List<House> getDependantHouses() {
        return dependantHouses;
    }

    public List<Node> getHouseFragments() {
        return houseFragments;
    }

    public void add(Node node) {
        this.houseFragments.add(node);
        node.setType(NodeType.HOUSE);
        node.setHouse(this);
        if (node.leftNodeTypeIs(NodeType.HOUSE_BLOCK) && node.getX() != 0) {
            add(node.getLeftNode());
        }
        if (node.rightNodeTypeIs(NodeType.HOUSE_BLOCK) && node.getX() != MapStructure.WIDTH - 1) {
            add(node.getRightNode());
        }
        if (node.upNodeTypeIs(NodeType.HOUSE_BLOCK) && node.getY() != 0) {
            add(node.getUpNode());
        }
        if (node.downNodeTypeIs(NodeType.HOUSE_BLOCK) && node.getY() != MapStructure.HEIGHT - 1) {
            add(node.getDownNode());
        }
    }


    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node : houseFragments) {
            stringBuilder.append(node.getX());
            stringBuilder.append(" ");
            stringBuilder.append(node.getY());
            stringBuilder.append(", ");
        }
        return "house contain: " + stringBuilder.toString();
    }


}
