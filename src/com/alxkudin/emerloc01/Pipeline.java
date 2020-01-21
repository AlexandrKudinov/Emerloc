package com.alxkudin.emerloc01;

import java.util.*;

import static com.alxkudin.emerloc01.LocType.*;
import static com.alxkudin.emerloc01.NodeType.*;

public class Pipeline {
    private Map<Pipe, LocType[]> pipes = new HashMap<>();
    private List<House> houses = new LinkedList<>();
    private LocType closeType;

    private boolean isOpen;

    private LocType[] locTypes(LocType... locTypes) {
        return locTypes;
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

    public void setCloseType(LocType closeType) {
        this.closeType = closeType;
    }

    public LocType getCloseType() {
        return closeType;
    }

    public void generate(Node node, LocType type) {
        Pipe pipe = node.getPipe();
        if (pipe.containValve()) {
            if (type == closeType) {
                pipes.put(pipe, locTypes(type));
                pipe.setMajorPartsIsTaken(true);
                return;
            }
            if (type == RIGHT || type == LEFT) {
                pipes.put(pipe, locTypes(UP, DOWN));
                pipe.setMinorPartsIsTaken(true);
            }
            if (type == UP || type == DOWN) {
                pipes.put(pipe, locTypes(LEFT, RIGHT));
                pipe.setMinorPartsIsTaken(true);
            }
        }
        





    }


    public List<House> getHouses() {
        return houses;
    }

    public void setHouses(List<House> houses) {
        this.houses = houses;
    }


}
