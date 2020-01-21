package com.alxkudin.emerloc01;

import java.util.*;
import java.util.stream.Stream;

import static com.alxkudin.emerloc01.LocType.*;
import static com.alxkudin.emerloc01.NodeType.HOUSE;
import static com.alxkudin.emerloc01.NodeType.PIPELINE_BLOCK;

public class WaterSupplyMap {
    private final int MAX_HOUSES_IN_GROUP = 7;
    Structure structure = Structure.getInstance();
    Node[][] map = structure.getMap();

    List<Valve> valves = new LinkedList<>();


    public void addPipes() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Node node = map[i][j];
                if (node.isPipelineBlock()) {
                    Pipe pipe = new Pipe();
                    node.setPipe(pipe);
                    pipe.setNode(node);

                    if (!node.verify(HOUSE, UP) &&     //cross
                            !node.verify(HOUSE, LEFT) &&
                            !node.verify(HOUSE, RIGHT) &&
                            !node.verify(HOUSE, DOWN)) {
                        pipe.addParts(LEFT, UP, RIGHT, DOWN);
                        continue;
                    }

                    if (!node.verify(HOUSE, LEFT) &&  //up valve
                            !node.verify(HOUSE, UP) &&
                            !node.verify(HOUSE, RIGHT) &&
                            (node.verify(HOUSE, DOWN))) {
                        pipe.addParts(UP, LEFT, RIGHT);
                        Valve valve = new Valve(UP);
                        pipe.setValve(valve);
                        valves.add(valve);
                        continue;
                    }


                    if ((!node.verify(HOUSE, RIGHT) && !node.verify(HOUSE, LEFT)) && //horizontal
                            ((node.verify(HOUSE, UP) && (node.verify(HOUSE, DOWN) ||
                                    (!node.verify(HOUSE, RIGHT, DOWN) || !node.verify(HOUSE, LEFT, DOWN)) ||
                                    (node.verify(HOUSE, DOWN) && (!node.verify(HOUSE, RIGHT, UP) ||
                                            !node.verify(HOUSE, LEFT, UP))))))) {
                        pipe.addParts(LEFT, RIGHT);
                        continue;
                    }


                    if (!node.verify(HOUSE, DOWN) && !node.verify(HOUSE, RIGHT) && // down valve
                            !node.verify(HOUSE, LEFT) &&
                            (node.verify(HOUSE, UP))) {
                        pipe.addParts(DOWN, LEFT, RIGHT);
                        Valve valve = new Valve(DOWN);
                        pipe.setValve(valve);
                        valves.add(valve);
                        continue;
                    }


                    if (!node.verify(HOUSE, LEFT) && !node.verify(HOUSE, DOWN) && //left down corner
                            node.verify(HOUSE, LEFT, DOWN) && node.verify(HOUSE, RIGHT) &&
                            (node.verify(HOUSE, UP) || !node.verify(HOUSE, LEFT, UP))) {
                        pipe.addParts(LEFT, DOWN);
                        continue;
                    }


                    if (!node.verify(HOUSE, LEFT) && node.verify(HOUSE, LEFT, UP) &&  //left up corner
                            !node.verify(HOUSE, UP) && node.verify(HOUSE, RIGHT) && node.verify(HOUSE, DOWN)) {
                        pipe.addParts(LEFT, UP);
                        continue;
                    }


                    if (node.verify(HOUSE, LEFT) && node.verify(HOUSE, DOWN) && // right up corner
                            node.verify(HOUSE, RIGHT, UP) &&
                            !node.verify(HOUSE, UP) && !node.verify(HOUSE, RIGHT)) {
                        pipe.addParts(RIGHT, UP);
                        continue;
                    }

                    if (node.verify(HOUSE, LEFT) && !node.verify(HOUSE, DOWN) && //right down corner
                            !node.verify(HOUSE, RIGHT) && node.verify(HOUSE, RIGHT, DOWN) &&
                            (node.verify(HOUSE, UP) || !node.verify(HOUSE, RIGHT, UP))) {
                        pipe.addParts(RIGHT, DOWN);
                        continue;
                    }


                    if (node.verify(HOUSE, RIGHT) && !node.verify(HOUSE, UP) && // left valve
                            !node.verify(HOUSE, DOWN) && !node.verify(HOUSE, LEFT) &&
                            (node.verify(HOUSE, LEFT, UP) || node.verify(HOUSE, LEFT, DOWN))) {
                        pipe.addParts(DOWN, LEFT, UP);
                        Valve valve = new Valve(LEFT);
                        pipe.setValve(valve);
                        valves.add(valve);
                        continue;
                    }


                    if (node.verify(HOUSE, LEFT) && !node.verify(HOUSE, RIGHT) && //right valve
                            !node.verify(HOUSE, UP) && !node.verify(HOUSE, DOWN) &&
                            (node.verify(HOUSE, RIGHT, UP) || node.verify(HOUSE, RIGHT, DOWN))) {
                        pipe.addParts(DOWN, RIGHT, UP);
                        Valve valve = new Valve(RIGHT);
                        pipe.setValve(valve);
                        valves.add(valve);
                        continue;
                    }

                    //vertical
                    pipe.addParts(UP, DOWN);
                }
            }
        }
    }


    public void addWaterIntake() {
        boolean allHousesInGroupNotFull = true;
        List<House> unionHouses = new LinkedList<>();
        List<House> sortedHouses = new ArrayList<>(Structure.getInstance().houses.values());
        sortedHouses.sort((o1, o2) -> {
            if (o1.getHouseFragments().size() > o2.getHouseFragments().size()) {
                return 1;
            } else if (o1.getHouseFragments().size() == o2.getHouseFragments().size()) {
                return 0;
            } else return -1;
        });


        for (House house : sortedHouses) {
            if (house.getAllHousesInGroup().size() >= MAX_HOUSES_IN_GROUP) {
                allHousesInGroupNotFull = false;
            }
            List<Node> houseFragments = house.getHouseFragments();
            Map<Pipe, Node> permIntakes = new HashMap<>();
            unionHouses.addAll(house.getAllHousesInGroup());

            for (Node node : houseFragments) {
                if (node.getJ() != 0 && node.verify(PIPELINE_BLOCK, LEFT) &&
                        node.getLeftNode().getPipe().getIntake().isEmpty()) {
                    Pipe pipe = new Pipe();
                    pipe.setParts(node.getLeftNode().getPipe().getParts());
                    pipe.setNode(node.getLeftNode());
                    pipe.addIntakes(RIGHT);
                    pipe.setIntakeHouse(house);
                    permIntakes.put(pipe, node);
                    if (allHousesInGroupNotFull && node.getLeftNode().getJ() != 0 &&
                            node.verify(HOUSE, LEFT, LEFT) &&
                            !unionHouses.contains(node.getLeftNode().getLeftNode().getHouse()) &&
                            node.getLeftNode().getLeftNode().getHouse().getAllHousesInGroup().size() <= MAX_HOUSES_IN_GROUP) {
                        Pipe pipe1 = new Pipe();
                        pipe1.setParts(node.getLeftNode().getPipe().getParts());
                        pipe1.addIntakes(RIGHT, LEFT);
                        pipe1.setNode(node.getLeftNode());
                        pipe1.setIntakeHouse(house);
                        permIntakes.put(pipe1, node);
                    }
                }

                if (node.getJ() != (structure.getWidth() - 1) &&
                        node.verify(PIPELINE_BLOCK, RIGHT) &&
                        node.getRightNode().getPipe().getIntake().isEmpty()) {
                    Pipe pipe = new Pipe();
                    pipe.setParts(node.getRightNode().getPipe().getParts());
                    pipe.addIntakes(LEFT);
                    pipe.setNode(node.getRightNode());
                    pipe.setIntakeHouse(house);
                    permIntakes.put(pipe, node);
                    if (allHousesInGroupNotFull &&
                            node.getRightNode().getJ() != (structure.getWidth() - 1) &&
                            node.verify(HOUSE, RIGHT, RIGHT) &&
                            !unionHouses.contains(node.getRightNode().getRightNode().getHouse()) &&
                            node.getRightNode().getRightNode().getHouse().getAllHousesInGroup().size() <= MAX_HOUSES_IN_GROUP) {
                        Pipe pipe1 = new Pipe();
                        pipe1.setParts(node.getRightNode().getPipe().getParts());
                        pipe1.addIntakes(RIGHT, LEFT);
                        pipe1.setNode(node.getRightNode());
                        pipe1.setIntakeHouse(house);
                        permIntakes.put(pipe1, node);
                    }
                }

                if (node.getI() != 0 && node.verify(PIPELINE_BLOCK, UP) &&
                        node.getUpNode().getPipe().getIntake().isEmpty()) {
                    Pipe pipe = new Pipe();
                    pipe.setParts(node.getUpNode().getPipe().getParts());
                    pipe.addIntakes(DOWN);
                    pipe.setNode(node.getUpNode());
                    pipe.setIntakeHouse(house);
                    permIntakes.put(pipe, node);

                    if (allHousesInGroupNotFull && node.getUpNode().getI() != 0 &&
                            node.verify(HOUSE, UP, UP) &&
                            !unionHouses.contains(node.getUpNode().getUpNode().getHouse()) &&
                            node.getUpNode().getUpNode().getHouse().getAllHousesInGroup().size() <= MAX_HOUSES_IN_GROUP) {
                        Pipe pipe1 = new Pipe();
                        pipe1.setParts(node.getUpNode().getPipe().getParts());
                        pipe1.addIntakes(UP, DOWN);
                        pipe1.setNode(node.getUpNode());
                        pipe1.setIntakeHouse(house);
                        permIntakes.put(pipe1, node);
                    }
                }

                if (node.getI() != (structure.getHeight() - 1) &&
                        node.verify(PIPELINE_BLOCK, DOWN) &&
                        node.getDownNode().getPipe().getIntake().isEmpty()) {
                    Pipe pipe = new Pipe();
                    pipe.setParts(node.getDownNode().getPipe().getParts());
                    pipe.addIntakes(UP);
                    pipe.setNode(node.getDownNode());
                    pipe.setIntakeHouse(house);
                    permIntakes.put(pipe, node);
                    if (allHousesInGroupNotFull &&
                            node.getDownNode().getI() != (structure.getHeight() - 1) &&
                            node.verify(HOUSE, DOWN, DOWN) &&
                            !unionHouses.contains(node.getDownNode().getDownNode().getHouse()) &&
                            node.getDownNode().getDownNode().getHouse().getAllHousesInGroup().size() <= MAX_HOUSES_IN_GROUP) {
                        Pipe pipe1 = new Pipe();
                        pipe1.setParts(node.getDownNode().getPipe().getParts());
                        pipe1.addIntakes(UP, DOWN);
                        pipe1.setNode(node.getDownNode());
                        pipe1.setIntakeHouse(house);
                        permIntakes.put(pipe1, node);
                    }
                }
            }

            int rnd = (int) (Math.random() * permIntakes.size());
            List<Pipe> pipeList = new ArrayList<>(permIntakes.keySet());
            Pipe pipe = pipeList.get(rnd);

            if (pipe.getIntake().size() == 2) {
                House mainHouse = null;
                Node node = pipe.getNode();
                if (pipe.getIntake().contains(LEFT)) {
                    if (node.getLeftNode().getHouse().equals(house)) {
                        mainHouse = node.getRightNode().getHouse();
                    } else {
                        mainHouse = node.getLeftNode().getHouse();
                    }
                }
                if (pipe.getIntake().contains(UP)) {
                    if (node.getUpNode().getHouse().equals(house)) {
                        mainHouse = node.getDownNode().getHouse();
                    } else {
                        mainHouse = node.getUpNode().getHouse();
                    }
                }
                house.addHouseInGroup(mainHouse);
                mainHouse.getOuttakeHouses().add(house);
                house.getIntakeHouses().add(mainHouse);
            }
            Pipe actualPipe = pipe.getNode().getPipe();
            actualPipe.setIntakeHouse(house);
            actualPipe.setIntake(pipe.getIntake());
            house.setPipe(actualPipe);
            unionHouses.clear();
        }


        for (House house : sortedHouses) {
            System.out.println("Base house: " + house + " dep size: " + house.getAllHousesInGroup().size());
            System.out.print("Dependant houses ");
            for (House house1 : house.getAllHousesInGroup()) {
                System.out.print(house1 + " , ");
            }
            System.out.println();
            System.out.print("Outake houses ");
            for (House house1 : house.getOuttakeHouses()) {
                System.out.print(house1 + " , ");
            }
            System.out.println();
            System.out.print("Intake houses ");
            for (House house1 : house.getIntakeHouses()) {
                System.out.print(house1 + " , ");
            }
            System.out.println();
            System.out.println();
        }

    }

    List<Pipeline> pipelines = new LinkedList<>();

    public void pipelineUnion() {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Node node = map[i][j];
                if (node.getType() == PIPELINE_BLOCK && node.getPipe().containValve()) {
                   Pipeline pipeline = new Pipeline();
                   LocType type = node.getPipe().getValve().getType();
                   pipeline.setCloseType(getOppositeType(type));
                   pipeline.generate(node,type);
                }
            }
        }
    }

}




