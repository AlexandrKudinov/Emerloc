package com.alxkudin.emerloc01;

import java.util.*;

import static com.alxkudin.emerloc01.LocType.*;
import static com.alxkudin.emerloc01.NodeType.HOUSE;
import static com.alxkudin.emerloc01.NodeType.PIPELINE_BLOCK;

public class WaterSupplyMap {
    Structure structure = Structure.getInstance();
    Node[][] map = structure.getMap();

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

                    if (!node.verify(HOUSE,LEFT) &&  //up valve
                            !node.verify(HOUSE,UP) &&
                            !node.verify(HOUSE,RIGHT) &&
                            (node.verify(HOUSE,DOWN))) {
                        pipe.addParts(UP, LEFT, RIGHT);
                        pipe.setValve(new Valve(UP));
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


                    if (!node.verify(HOUSE,DOWN) && !node.verify(HOUSE,RIGHT) && // down valve
                            !node.verify(HOUSE,LEFT) &&
                            (node.verify(HOUSE,UP))) {
                        pipe.addParts(DOWN, LEFT, RIGHT);
                        pipe.setValve(new Valve(DOWN));

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
                        pipe.setValve(new Valve(LEFT));
                        continue;
                    }


                    if (node.verify(HOUSE, LEFT) && !node.verify(HOUSE, RIGHT) && //right valve
                            !node.verify(HOUSE, UP) && !node.verify(HOUSE, DOWN) &&
                            (node.verify(HOUSE, RIGHT, UP) || node.verify(HOUSE, RIGHT, DOWN))) {
                        pipe.addParts(DOWN, RIGHT, UP);
                        pipe.setValve(new Valve(RIGHT));
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
        System.out.println(sortedHouses.size());
        for (House house : sortedHouses) {
            if (house.getAllHousesInGroup().size() == 4) {
                allHousesInGroupNotFull = false;
            }
            List<Node> houseFragments = house.getHouseFragments();
            List<Pipe> permIntakes = new LinkedList<>();
            unionHouses.addAll(house.getAllHousesInGroup());

            for (Node node : houseFragments) {
                if (node.getJ() != 0 && node.verify(PIPELINE_BLOCK,LEFT) &&
                        node.getLeftNode().getPipe().getIntake().isEmpty()) {
                    Pipe pipe = new Pipe();
                    pipe.setParts(node.getLeftNode().getPipe().getParts());
                    pipe.setNode(node.getLeftNode());
                    pipe.addIntakes(RIGHT);
                    pipe.setIntakeHouse(house);
                    permIntakes.add(pipe);
                    if (allHousesInGroupNotFull && node.getLeftNode().getJ() != 0 &&
                            node.verify(HOUSE,LEFT,LEFT) &&
                            !unionHouses.contains(node.getLeftNode().getLeftNode().getHouse()) &&
                            node.getLeftNode().getLeftNode().getHouse().getAllHousesInGroup().size() <= 4) {
                        Pipe pipe1 = new Pipe();
                        pipe1.setParts(node.getLeftNode().getPipe().getParts());
                        pipe1.addIntakes(RIGHT, LEFT);
                        pipe1.setNode(node.getLeftNode());
                        pipe1.setIntakeHouse(house);
                        permIntakes.add(pipe1);
                    }
                }

                if (node.getJ() != (structure.getWidth() - 1) &&
                        node.verify(PIPELINE_BLOCK,RIGHT) &&
                        node.getRightNode().getPipe().getIntake().isEmpty()) {
                    Pipe pipe = new Pipe();
                    pipe.setParts(node.getRightNode().getPipe().getParts());
                    pipe.addIntakes(LEFT);
                    pipe.setNode(node.getRightNode());
                    pipe.setIntakeHouse(house);
                    permIntakes.add(pipe);
                    if (allHousesInGroupNotFull &&
                            node.getRightNode().getJ() != (structure.getWidth() - 1) &&
                            node.verify(HOUSE,RIGHT,RIGHT) &&
                            !unionHouses.contains(node.getRightNode().getRightNode().getHouse()) &&
                            node.getRightNode().getRightNode().getHouse().getAllHousesInGroup().size() <= 4) {
                        Pipe pipe1 = new Pipe();
                        pipe1.setParts(node.getRightNode().getPipe().getParts());
                        pipe1.addIntakes(RIGHT, LEFT);
                        pipe1.setNode(node.getRightNode());
                        pipe1.setIntakeHouse(house);
                        permIntakes.add(pipe1);
                    }
                }

                if (node.getI() != 0 && node.verify(PIPELINE_BLOCK,UP) &&
                        node.getUpNode().getPipe().getIntake().isEmpty()) {
                    Pipe pipe = new Pipe();
                    pipe.setParts(node.getUpNode().getPipe().getParts());
                    pipe.addIntakes(DOWN);
                    pipe.setNode(node.getUpNode());
                    pipe.setIntakeHouse(house);
                    permIntakes.add(pipe);

                    if (allHousesInGroupNotFull && node.getUpNode().getI() != 0 &&
                            node.verify(HOUSE,UP,UP) &&
                            !unionHouses.contains(node.getUpNode().getUpNode().getHouse()) &&
                            node.getUpNode().getUpNode().getHouse().getAllHousesInGroup().size() <= 4) {
                        Pipe pipe1 = new Pipe();
                        pipe1.setParts(node.getUpNode().getPipe().getParts());
                        pipe1.addIntakes(UP, DOWN);
                        pipe1.setNode(node.getUpNode());
                        pipe1.setIntakeHouse(house);
                        permIntakes.add(pipe1);
                    }
                }

                if (node.getI() != (structure.getHeight() - 1) &&
                        node.verify(PIPELINE_BLOCK,DOWN) &&
                        node.getDownNode().getPipe().getIntake().isEmpty()) {
                    Pipe pipe = new Pipe();
                    pipe.setParts(node.getDownNode().getPipe().getParts());
                    pipe.addIntakes(UP);
                    pipe.setNode(node.getDownNode());
                    pipe.setIntakeHouse(house);
                    permIntakes.add(pipe);
                    if (allHousesInGroupNotFull &&
                            node.getDownNode().getI() != (structure.getHeight() - 1) &&
                            node.verify(HOUSE,DOWN,DOWN) &&
                            !unionHouses.contains(node.getDownNode().getDownNode().getHouse()) &&
                            node.getDownNode().getDownNode().getHouse().getAllHousesInGroup().size() <= 4) {
                        Pipe pipe1 = new Pipe();
                        pipe1.setParts(node.getDownNode().getPipe().getParts());
                        pipe1.addIntakes(UP, DOWN);
                        pipe1.setNode(node.getDownNode());
                        pipe1.setIntakeHouse(house);
                        permIntakes.add(pipe1);
                    }
                }
            }

            int rnd = (int) (Math.random() * permIntakes.size());
            Pipe pipe = permIntakes.get(rnd);

           // house.addToHouseGroup();
           // pipe.getNode().getPipe().setIntakeHouse(house);
          //  pipe.getNode().getPipe().setIntake(pipe.getIntake());
          //  pipe.getNode().setPipe(pipe);

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
               // majorHouse.getOuttakeHouses().add(house);
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




    }
}



