package com.alxkudin.emerloc01;

import java.util.*;

import static com.alxkudin.emerloc01.ArmatureType.*;
import static com.alxkudin.emerloc01.MapStructure.*;
import static com.alxkudin.emerloc01.NodeType.HOUSE;
import static com.alxkudin.emerloc01.NodeType.PIPELINE_BLOCK;

public class WaterSupplyMap {

    public void build() {
        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {

                if (INSTANCE.get(i, j).isPipelineBlock()) {
                    Node node = INSTANCE.get(i, j);
                    Pipe pipe1 = new Pipe();
                    INSTANCE.get(i, j).setPipe(pipe1);

                    if (!node.upNodeTypeIs(HOUSE) &&     //cross
                            !node.leftNodeTypeIs(HOUSE) &&
                            !node.rightNodeTypeIs(HOUSE) &&
                            !node.downNodeTypeIs(HOUSE)) {
                        Pipe pipe = new Pipe();
                        pipe.setNode(node);
                        pipe.addParts(LEFT, UP, RIGHT, DOWN);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }

                    if (!node.leftNodeTypeIs(HOUSE) &&  //up valve
                            !node.upNodeTypeIs(HOUSE) &&
                            !node.rightNodeTypeIs(HOUSE) &&
                            (node.downNodeTypeIs(HOUSE))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(UP, LEFT, RIGHT);
                        pipe.setValve(new Valve(UP));
                        pipe.setNode(node);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if ((!node.rightNodeTypeIs(HOUSE) && !node.leftNodeTypeIs(HOUSE)) && //horizontal
                            ((node.upNodeTypeIs(HOUSE) && (node.downNodeTypeIs(HOUSE) ||
                                    ((!node.rightDownNodeTypeIs(HOUSE)) || !node.leftDownNodeTypeIs(HOUSE)))) ||
                                    (node.downNodeTypeIs(HOUSE) && (!node.rightUpNodeTypeIs(HOUSE) ||
                                            !node.leftUpNodeTypeIs(HOUSE))))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(LEFT, RIGHT);
                        pipe.setNode(node);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if (!node.downNodeTypeIs(HOUSE) && !node.rightNodeTypeIs(HOUSE) && // down valve
                            !node.leftNodeTypeIs(HOUSE) &&
                            (node.upNodeTypeIs(HOUSE))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(DOWN, LEFT, RIGHT);
                        pipe.setValve(new Valve(DOWN));
                        pipe.setNode(node);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if (!node.leftNodeTypeIs(HOUSE) && !node.downNodeTypeIs(HOUSE) && //left down corner
                            node.leftDownNodeTypeIs(HOUSE) && node.rightNodeTypeIs(HOUSE) &&
                            (node.upNodeTypeIs(HOUSE) || !node.leftUpNodeTypeIs(HOUSE))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(LEFT, DOWN);
                        pipe.setNode(node);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if (!node.leftNodeTypeIs(HOUSE) && node.leftUpNodeTypeIs(HOUSE) &&  //left up corner
                            !node.upNodeTypeIs(HOUSE) && node.rightNodeTypeIs(HOUSE) && node.downNodeTypeIs(HOUSE)) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(LEFT, UP);
                        pipe.setNode(node);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if (node.leftNodeTypeIs(HOUSE) && node.downNodeTypeIs(HOUSE) && // right up corner
                            node.rightUpNodeTypeIs(HOUSE) &&
                            !node.upNodeTypeIs(HOUSE) && !node.rightNodeTypeIs(HOUSE)) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(RIGHT, UP);
                        pipe.setNode(node);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }

                    if (node.leftNodeTypeIs(HOUSE) && !node.downNodeTypeIs(HOUSE) && //right down corner
                            !node.rightNodeTypeIs(HOUSE) && node.rightDownNodeTypeIs(HOUSE) &&
                            (node.upNodeTypeIs(HOUSE) || !node.rightUpNodeTypeIs(HOUSE))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(RIGHT, DOWN);
                        pipe.setNode(node);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if (node.rightNodeTypeIs(HOUSE) && !node.upNodeTypeIs(HOUSE) && // left valve
                            !node.downNodeTypeIs(HOUSE) && !node.leftNodeTypeIs(HOUSE) &&
                            (node.leftUpNodeTypeIs(HOUSE) || node.leftDownNodeTypeIs(HOUSE))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(DOWN, LEFT, UP);
                        pipe.setValve(new Valve(LEFT));
                        pipe.setNode(node);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }


                    if (node.leftNodeTypeIs(HOUSE) && !node.rightNodeTypeIs(HOUSE) && //right valve
                            !node.upNodeTypeIs(HOUSE) && !node.downNodeTypeIs(HOUSE) &&
                            (node.rightUpNodeTypeIs(HOUSE) || node.rightDownNodeTypeIs(HOUSE))) {
                        Pipe pipe = new Pipe();
                        pipe.addParts(DOWN, RIGHT, UP);
                        pipe.setValve(new Valve(RIGHT));
                        pipe.setNode(node);
                        INSTANCE.get(i, j).setPipe(pipe);
                        continue;
                    }

                    Pipe pipe = new Pipe(); //vertical
                    pipe.addParts(UP, DOWN);
                    pipe.setNode(node);
                    INSTANCE.get(i, j).setPipe(pipe);
                }
            }
        }


        for (int j = 0; j < HEIGHT; j++) {
            for (int i = 0; i < WIDTH; i++) {
                System.out.print(INSTANCE.get(i, j).getType());
            }
            System.out.println();
        }


    }


    public void addWaterIntake() {
        boolean allHousesInGroupNotFull = true;
        List<House> unionHouses = new LinkedList<>();
        List<House> sortedHouses = new ArrayList<>(GameField.housesMap.houses.values());
        sortedHouses.sort((o1, o2) -> {
            if (o1.getHouseFragments().size() > o2.getHouseFragments().size()) {
                return 1;
            } else if (o1.getHouseFragments().size() == o2.getHouseFragments().size()) {
                return 0;
            } else return -1;
        });

        for (House house : sortedHouses) {
            if (house.getAllHousesInGroup().size() == 4) {
                allHousesInGroupNotFull = false;
            }
            List<Node> houseFragments = house.getHouseFragments();
            List<Pipe> pipesWithIntakes = new LinkedList<>();
            unionHouses.addAll(house.getAllHousesInGroup());

            for (Node node : houseFragments) {
                if (node.getX() != 0 && node.leftNodeTypeIs(PIPELINE_BLOCK) && node.getLeftNode().getPipe().getIntake().size() == 0) {
                    Pipe pipe = new Pipe();
                    pipe.setParts(node.getLeftNode().getPipe().getParts());
                    pipe.setNode(node.getLeftNode());
                    pipe.addIntakes(RIGHT);
                    pipe.setIntakeHouse(house);
                    pipesWithIntakes.add(pipe);
                    if (allHousesInGroupNotFull && node.getLeftNode().getX() != 0 && node.doubleLeftNodeTypeIs(HOUSE) && !unionHouses.contains(node.getDoubleLeftNode().getHouse()) && node.getDoubleLeftNode().getHouse().getAllHousesInGroup().size() <= 4) {
                        Pipe pipe1 = new Pipe();
                        pipe1.setParts(node.getLeftNode().getPipe().getParts());
                        pipe1.addIntakes(RIGHT, LEFT);
                        pipe1.setNode(node.getLeftNode());
                        pipe1.setIntakeHouse(house);
                        pipesWithIntakes.add(pipe1);
                    }
                }

                if (node.getX() != (WIDTH - 1) && node.rightNodeTypeIs(PIPELINE_BLOCK) && node.getRightNode().getPipe().getIntake().size() == 0) {
                    Pipe pipe = new Pipe();
                    pipe.setParts(node.getRightNode().getPipe().getParts());
                    pipe.addIntakes(LEFT);
                    pipe.setNode(node.getRightNode());
                    pipe.setIntakeHouse(house);
                    pipesWithIntakes.add(pipe);
                    if (allHousesInGroupNotFull && node.getRightNode().getX() != (WIDTH - 1) && node.doubleRightNodeTypeIs(HOUSE) && !unionHouses.contains(node.getDoubleRightNode().getHouse()) && node.getDoubleRightNode().getHouse().getAllHousesInGroup().size() <= 4) {
                        Pipe pipe1 = new Pipe();
                        pipe1.setParts(node.getRightNode().getPipe().getParts());
                        pipe1.addIntakes(RIGHT, LEFT);
                        pipe1.setNode(node.getRightNode());
                        pipe1.setIntakeHouse(house);
                        pipesWithIntakes.add(pipe1);
                    }
                }

                if (node.getY() != 0 && node.upNodeTypeIs(PIPELINE_BLOCK) && node.getUpNode().getPipe().getIntake().size() == 0) {
                    Pipe pipe = new Pipe();
                    pipe.setParts(node.getUpNode().getPipe().getParts());
                    pipe.addIntakes(DOWN);
                    pipe.setNode(node.getUpNode());
                    pipe.setIntakeHouse(house);
                    pipesWithIntakes.add(pipe);

                    if (allHousesInGroupNotFull && node.getUpNode().getX() != 0 && node.doubleUpNodeTypeIs(HOUSE) && !unionHouses.contains(node.getDoubleUpNode().getHouse()) && node.getDoubleUpNode().getHouse().getAllHousesInGroup().size() <= 4) {
                        Pipe pipe1 = new Pipe();
                        pipe1.setParts(node.getUpNode().getPipe().getParts());
                        pipe1.addIntakes(UP, DOWN);
                        pipe1.setNode(node.getUpNode());
                        pipe1.setIntakeHouse(house);
                        pipesWithIntakes.add(pipe1);
                    }
                }

                if (node.getY() != (HEIGHT - 1) && node.downNodeTypeIs(PIPELINE_BLOCK) && node.getDownNode().getPipe().getIntake().size() == 0) {
                    Pipe pipe = new Pipe();
                    pipe.setParts(node.getDownNode().getPipe().getParts());
                    pipe.addIntakes(UP);
                    pipe.setNode(node.getDownNode());
                    pipe.setIntakeHouse(house);
                    pipesWithIntakes.add(pipe);
                    if (allHousesInGroupNotFull && node.getDownNode().getX() != (HEIGHT - 1) && node.doubleDownNodeTypeIs(HOUSE) && !unionHouses.contains(node.getDoubleDownNode().getHouse()) && node.getDoubleDownNode().getHouse().getAllHousesInGroup().size() <= 4) {
                        Pipe pipe1 = new Pipe();
                        pipe1.setParts(node.getDownNode().getPipe().getParts());
                        pipe1.addIntakes(UP, DOWN);
                        pipe1.setNode(node.getDownNode());
                        pipe1.setIntakeHouse(house);
                        pipesWithIntakes.add(pipe1);
                    }
                }
            }

            int rnd = (int) (Math.random() * pipesWithIntakes.size());
            Pipe pipe = pipesWithIntakes.get(rnd);

            if (pipe.getIntake().size() == 2) {
                House majorHouse = null;
                if (pipe.getIntake().contains(LEFT) && pipe.getIntake().contains(RIGHT)) {
                    if (pipe.getNode().getLeftNode().getHouse() != pipe.getIntakeHouse()) {
                        majorHouse = pipe.getNode().getLeftNode().getHouse();
                    } else {
                        majorHouse = pipe.getNode().getRightNode().getHouse();
                    }
                }
                if (pipe.getIntake().contains(UP) && pipe.getIntake().contains(DOWN)) {
                    if (pipe.getNode().getUpNode().getHouse() != pipe.getIntakeHouse()) {
                        majorHouse = pipe.getNode().getUpNode().getHouse();
                    } else {
                        majorHouse = pipe.getNode().getDownNode().getHouse();
                    }
                }
                majorHouse.getOuttakeHouses().add(house);
                house.getIntakeHouses().add(majorHouse);
                for (House house1 : house.getAllHousesInGroup()) {
                    house1.getAllHousesInGroup().add(majorHouse);
                    house1.getAllHousesInGroup().addAll(majorHouse.getAllHousesInGroup());
                }
                for (House house1 : majorHouse.getAllHousesInGroup()) {
                    house1.getAllHousesInGroup().add(house);
                    house1.getAllHousesInGroup().addAll(house.getAllHousesInGroup());
                }
                house.getAllHousesInGroup().addAll(majorHouse.getAllHousesInGroup());
                majorHouse.getAllHousesInGroup().addAll(house.getAllHousesInGroup());
                house.getAllHousesInGroup().add(majorHouse);
                majorHouse.getAllHousesInGroup().add(house);


            }
            house.addToHouseGroup();
            pipe.getNode().getPipe().setIntakeHouse(house);
            pipe.getNode().getPipe().setIntake(pipe.getIntake());

            for (House house22 : sortedHouses) {
                for (int i = 0; i < house22.getAllHousesInGroup().size(); i++) {
                    House house2 = house22.getAllHousesInGroup().get(i);
                    for (int j = i + 1; j < house22.getAllHousesInGroup().size(); j++) {
                        if (house2 == house22.getAllHousesInGroup().get(j)) {
                            house22.getAllHousesInGroup().remove(j);
                            j--;
                        }
                    }

                }
            }


        }


        for (House house : sortedHouses) {

            System.out.println(house);
            System.out.println(house.getAllHousesInGroup().size());
            // System.out.println(house.getIntakeHouses().size());
            for (House house1 : house.getAllHousesInGroup()) {
                System.out.print(house1 + "||  ");
            }
            System.out.println();
        }


        for (int j = 0; j < HEIGHT; j++) {  //set the main square
            for (int i = 0; i < WIDTH; i++) {
                Node node = INSTANCE.get(i, j);
                if (node.isPipelineBlock()) {
                    if (node.getPipe().getIntake().size() != 0) {
                        System.out.println(node.getX() + "  " + node.getY());
                        for (ArmatureType type : node.getPipe().getIntake()) {
                            System.out.print(type);
                        }
                        System.out.println(node.getPipe().getIntakeHouse());
                    }
                }
            }
        }
    }
}



