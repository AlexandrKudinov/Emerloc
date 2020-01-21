package com.alxkudin.emerloc01;

import java.util.*;

import static com.alxkudin.emerloc01.LocType.*;
import static com.alxkudin.emerloc01.NodeType.*;

public class Pipeline {
    private Map<Pipe, List<LocType>> pipes = new HashMap<>();
    private List<House> houses = new LinkedList<>();


    private boolean isOpen;

    private boolean exit = true;

    private List<LocType> locTypes(LocType... locTypes) {
        List<LocType> locTypeList = new LinkedList<>();
        for (LocType locType : locTypes) {
            locTypeList.add(locType);
        }
        return locTypeList;
    }

    private void updateStatus() {
        Valve valve;
        for (Pipe pipe : pipes.keySet()) {
            if (pipe.containValve()) {
                valve = pipe.getValve();
                if (!valve.isOpen) {
                    continue;
                }
                isOpen = true;
                return;
            }
        }
        isOpen = false;
    }


    public void generate(Node node, LocType type) {
        System.out.print(node + ",  ");
        Pipe pipe = node.getPipe();
     // if (pipe != null) {
            if (pipe.containValve()) {
                if (getOppositeType(type) == pipe.getValve().getType()) {
                    pipes.put(pipe, locTypes(type));
                    pipe.setMajorPartsIsTaken(true);
                    if (pipe.getMinorPartsIsTaken()) {
                        pipe.getNode().setType(PIPELINE);
                    }
                    return;
                } else if (pipes.size() == 0) {
                    pipes.put(pipe, locTypes(type));
                    pipe.setMajorPartsIsTaken(true);
                    if (pipe.getMinorPartsIsTaken()) {
                        pipe.getNode().setType(PIPELINE);
                    }
                    generate(node.getNodeByType(type), type);
                } else if (pipe.getValve().getType() == RIGHT || pipe.getValve().getType() == LEFT) {
                    pipes.put(pipe, locTypes(UP, DOWN));
                    pipe.setMinorPartsIsTaken(true);
                    if (pipe.getMajorPartsIsTaken()) {
                        pipe.getNode().setType(PIPELINE);
                    }
                    generate(node.getNodeByType(type), type);
                } else if (pipe.getValve().getType() == UP || pipe.getValve().getType() == DOWN) {
                    pipes.put(pipe, locTypes(LEFT, RIGHT));
                    pipe.setMinorPartsIsTaken(true);
                    if (pipe.getMajorPartsIsTaken()) {
                        pipe.getNode().setType(PIPELINE);
                    }
                    generate(node.getNodeByType(type), type);
                }

            } else if (!pipe.partsContain(type)) {
                LocType newType = null;
                for (LocType locType : pipe.getParts()) {
                    if (locType!=getOppositeType(type)) {
                        newType = locType;
                        break;
                    }
                }
                pipes.put(pipe, locTypes(type, newType));
                pipe.getNode().setType(PIPELINE);
                System.out.print(newType);
                generate(node.getNodeByType(newType), newType);
            } else if (pipe.getParts().size() == 4) {
                if (type == LEFT || type == RIGHT) {
                    pipes.put(pipe, locTypes(LEFT, RIGHT));
                    pipe.setMinorPartsIsTaken(true);
                    if (pipe.getMajorPartsIsTaken()) {
                        pipe.getNode().setType(PIPELINE);
                    }
                    generate(node.getNodeByType(type), type);
                }
                if (type == UP || type == DOWN) {
                    pipes.put(pipe, locTypes(UP, DOWN));
                    pipe.setMajorPartsIsTaken(true);
                    if (pipe.getMinorPartsIsTaken()) {
                        pipe.getNode().setType(PIPELINE);
                    }
                    generate(node.getNodeByType(type), type);
                }
            } else {
                pipes.put(pipe, pipe.getParts());
                pipe.getNode().setType(PIPELINE);
                generate(node.getNodeByType(type), type);
            }
     // }
    }

    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }

}
