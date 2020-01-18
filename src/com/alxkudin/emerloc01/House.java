package com.alxkudin.emerloc01;

import java.util.LinkedList;
import java.util.List;

import static com.alxkudin.emerloc01.LocType.*;
import static com.alxkudin.emerloc01.NodeType.*;


public class House {


    private List<Node> houseFragments = new LinkedList<>();
    private List<House> allHousesInGroup = new LinkedList<>();
    private List<House> intakeHouses = new LinkedList<>();
  //  private List<House> outtakeHouses = new LinkedList<>();
    private int housesInChain = 0;


//    public List<House> getOuttakeHouses() {
//        return outtakeHouses;
//    }

    public void setIntakeHouses(List<House> intakeHouses) {
        this.intakeHouses = intakeHouses;
    }


    public void setAllHousesInGroup(List<House> allHousesInGroup) {
        this.allHousesInGroup = allHousesInGroup;
    }

    public List<House> getAllHousesInGroup() {
        return allHousesInGroup;
    }

//    public void setOuttakeHouses(List<House> outtakeHouses) {
//        this.outtakeHouses = outtakeHouses;
//    }

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

    public void add(Node node, int i, int j, int width, int height) {
        this.houseFragments.add(node);
        node.setType(HOUSE);
        node.setHouse(this);
        if (node.verify(HOUSE_BLOCK, LEFT) && j != 0) {
            add(node.getLeftNode(), i, j - 1, width, height);
        }
        if (node.verify(HOUSE_BLOCK, RIGHT) && j != width - 1) {
            add(node.getRightNode(), i, j + 1, width, height);
        }
        if (node.verify(HOUSE_BLOCK, UP) && i != 0) {
            add(node.getUpNode(), i - 1, j, width, height);
        }
        if (node.verify(HOUSE_BLOCK, DOWN) && i != height - 1) {
            add(node.getDownNode(), i + 1, j, width, height);
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

        }
        return "house contain: " + stringBuilder.toString();
    }


}
