package com.alxkudin.emerloc01;

import java.util.LinkedList;
import java.util.List;


public class House {


    private List<Node> houseFragments = new LinkedList<>();
    private List<House> allHousesInGroup = new LinkedList<>();
    private List<House> intakeHouses = new LinkedList<>();
    private List<House> outtakeHouses = new LinkedList<>();
    private int housesInChain = 0;


    public List<House> getOuttakeHouses() {
        return outtakeHouses;
    }

    public void setIntakeHouses(List<House> intakeHouses) {
        this.intakeHouses = intakeHouses;
    }


    public void setAllHousesInGroup(List<House> allHousesInGroup) {
        this.allHousesInGroup = allHousesInGroup;
    }

    public List<House> getAllHousesInGroup() {
        return allHousesInGroup;
    }

    public void setOuttakeHouses(List<House> outtakeHouses) {
        this.outtakeHouses = outtakeHouses;
    }

    public int getHousesInChain() {
        return housesInChain;
    }

    public void setHousesInChain(int depInGroup) {
        this.housesInChain = depInGroup;
    }

    private Pipe pipe;

    public Pipe getPipe() {
        return pipe;
    }

    public void setPipe(Pipe pipe) {
        this.pipe = pipe;
    }

    public static int MAX_DEPENDANT_HOUSES = 10;


    public List<House> getIntakeHouses() {
        return intakeHouses;
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


    public void addHouseInChain(House house) {
        this.getAllHousesInGroup().add(house);
        for (House house1 : this.getAllHousesInGroup()) {
            house.addHouseInChain(house);
        }
    }


    public void addToHouseGroup() {
        ++this.housesInChain;
        if (!this.getIntakeHouses().isEmpty()) {
            for (House house : this.getIntakeHouses()) {
                house.addToHouseGroup();
            }
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
