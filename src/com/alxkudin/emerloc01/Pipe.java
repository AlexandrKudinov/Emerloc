package com.alxkudin.emerloc01;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Pipe {
    private List<LocType> parts = new LinkedList<>();
    private List<LocType> intake = new LinkedList<>();
    private Valve valve;

    private House intakeHouse;

    public House getIntakeHouse() {
        return intakeHouse;
    }

    public void setIntakeHouse(House intakeHouse) {
        this.intakeHouse = intakeHouse;
    }

    private Node node;

    public Node getNode() {
        return node;
    }

    public void setNode(Node node) {
        this.node = node;
    }

    public boolean containIntake() {
        return intake.size() != 0;
    }

    public void setValve(Valve valve) {
        this.valve = valve;
    }

    public boolean containPart(LocType type) {
        return (parts.contains(type) || intake.contains(type));
    }

    public void addParts(LocType... types) {
        this.parts.addAll(Arrays.asList(types));
    }

    public void addIntakes(LocType... types) {
        this.intake.addAll(Arrays.asList(types));
    }

    public void setIntake(List<LocType> intake) {
        this.intake = intake;
    }

    public void setParts(List<LocType> parts) {
        this.parts = parts;
    }

    public List<LocType> getIntake() {
        return intake;
    }

    public List<LocType> getParts() {
        return parts;
    }

    public Valve getValve() {
        return valve;
    }
}
