package com.kudinov.emerloc01;

import java.util.*;

import static com.kudinov.emerloc01.LocType.*;

public class Pipeline {
    private Map<Pipe, List<LocType>> pipes = new HashMap<>();
    private List<House> houses = new LinkedList<>();
    private boolean open;
    private boolean accident;

    public void setAccident() {
        this.accident = true;
    }

    public void changeAccidentStage() {
        accident = !accident;
    }

    public boolean isAccident() {
        return accident;
    }

    public Map<Pipe, List<LocType>> getPipes() {
        return pipes;
    }

    public boolean isOpen() {
        return open;
    }

    private List<LocType> locTypesList(LocType... locTypes) {
        List<LocType> locTypeList = new LinkedList<>();
        for (LocType locType : locTypes) {
            locTypeList.add(locType);
        }
        return locTypeList;
    }

    public void updateStatus() {
        Valve valve;
        for (Pipe pipe : pipes.keySet()) {
            if (pipe.containValve()) {
                valve = pipe.getValve();
                if (!valve.isOpen()) {
                    continue;
                }
                open = true;
                return;
            }
        }
        open = false;
    }

    private void addToPipesMap(Pipe pipe, LocType... types) {
        if (pipes.containsKey(pipe)) {
            List<LocType> locTypes = pipes.get(pipe);
            for (LocType locType : types) {
                locTypes.add(locType);
            }
            pipes.put(pipe, locTypes);
        } else {
            pipes.put(pipe, locTypesList(types));
        }
    }

    public void addDepHouses() {
        List<House> newHouses = new LinkedList<>();
        for (House house : houses) {
            newHouses.addAll(house.getAllHousesInGroup());
        }
        houses.addAll(newHouses);
        for (House house : houses) {
            if (!pipes.containsKey(house.getPipe())) {
                pipes.put(house.getPipe(), house.getPipe().getIntake());
            } else {
                List<LocType> types = pipes.get(house.getPipe());
                types.addAll(house.getPipe().getIntake());
                pipes.put(house.getPipe(), types);
            }
        }

    }

    public void generate(Node node, LocType type) {
        Pipe pipe = node.getPipe();
        if (pipe.containValve()) {
            if (getOppositeType(type) == pipe.getValve().getType()) {
                addToPipesMap(pipe, getOppositeType(type));
                pipe.setMajorPartsIsTaken(true);
                pipe.ifMinorTakenSetPipeline();
            } else if (pipes.size() == 0) {
                pipes.put(pipe, locTypesList(type));
                pipe.setMajorPartsIsTaken(true);
                pipe.ifMinorTakenSetPipeline();
                generate(node.getNodeByType(type), type);
            } else if (pipe.getValve().getType() == RIGHT || pipe.getValve().getType() == LEFT) {
                List<LocType> types = new LinkedList<>();
                if (pipe.intakeContain(LEFT)) {
                    houses.add(pipe.getIntakeHouse());
                    types.add(LEFT);
                } else if (pipe.intakeContain(RIGHT)) {
                    houses.add(pipe.getIntakeHouse());
                    types.add(RIGHT);
                }
                types.add(UP);
                types.add(DOWN);
                pipes.put(pipe, types);
                pipe.setMinorPartsIsTaken(true);
                pipe.ifMajorTakenSetPipeline();
                generate(node.getNodeByType(type), type);
            } else if (pipe.getValve().getType() == UP || pipe.getValve().getType() == DOWN) {
                List<LocType> types = new LinkedList<>();
                if (pipe.intakeContain(UP)) {
                    houses.add(pipe.getIntakeHouse());
                    types.add(UP);
                } else if (pipe.intakeContain(DOWN)) {
                    houses.add(pipe.getIntakeHouse());
                    types.add(DOWN);
                }
                types.add(LEFT);
                types.add(RIGHT);
                pipes.put(pipe, types);
                pipe.setMinorPartsIsTaken(true);
                pipe.ifMajorTakenSetPipeline();
                generate(node.getNodeByType(type), type);
            }
        } else if (!pipe.partsContain(type)) {
            LocType newType = null;
            for (LocType locType : pipe.getParts()) {
                if (locType != getOppositeType(type)) {
                    newType = locType;
                    break;
                }
            }
            List<LocType> types = new LinkedList<>();
            types.add(getOppositeType(type));
            types.add(newType);
            if (!pipe.getIntake().isEmpty()) {
                houses.add(pipe.getIntakeHouse());
                types.add(pipe.getIntake().get(0));

            }
            pipes.put(pipe, types);
            pipe.getNode().setType(NodeType.PIPELINE);
            generate(node.getNodeByType(newType), newType);
        } else if (pipe.getParts().size() == 4) {
            if (type == LEFT || type == RIGHT) {
                pipes.put(pipe, locTypesList(LEFT, RIGHT));
                pipe.setMinorPartsIsTaken(true);
                pipe.ifMajorTakenSetPipeline();
                generate(node.getNodeByType(type), type);
            }
            if (type == UP || type == DOWN) {
                pipes.put(pipe, locTypesList(UP, DOWN));
                pipe.setMajorPartsIsTaken(true);
                pipe.ifMinorTakenSetPipeline();
                generate(node.getNodeByType(type), type);
            }
        } else {
            List<LocType> types = new LinkedList<>(pipe.getParts());
            if (!pipe.getIntake().isEmpty() && pipe.getIntake().size() != 2) {
                houses.add(pipe.getIntakeHouse());
                types.add(pipe.getIntake().get(0));
            }
            pipes.put(pipe, types);
            pipe.getNode().setType(NodeType.PIPELINE);
            generate(node.getNodeByType(type), type);
        }
    }

    public List<House> getHouses() {
        return houses;
    }


}
