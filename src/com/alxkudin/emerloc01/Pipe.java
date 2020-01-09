package com.alxkudin.emerloc01;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Pipe {
    private List<ArmatureType> parts = new LinkedList<>();
    private List<ArmatureType> intake = new LinkedList<>();
    private Valve valve;
    //private List<House> intakeHouses = new LinkedList<>();

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
/*
    public List<House> getIntakeHouses() {
        return intakeHouses;
    }

    public void setIntakeHouses(List<House> intakeHouses) {
        this.intakeHouses = intakeHouses;
    }

    public void addHouse(House house) {
        if (intakeHouses == null) {
            intakeHouses = new LinkedList<>();
        }
        intakeHouses.add(house);
    }


 */

    public boolean containIntake() {
        return intake.size() != 0;
    }

    public void setValve(Valve valve) {
        this.valve = valve;
    }

    public boolean containPart(ArmatureType type) {
        return (parts.contains(type) || intake.contains(type));
    }

    public void addParts(ArmatureType... types) {
        this.parts.addAll(Arrays.asList(types));
    }

    public void addIntakes(ArmatureType... types) {
        this.intake.addAll(Arrays.asList(types));
    }

    public void setIntake(List<ArmatureType> intake) {
        this.intake = intake;
    }

    public void setParts(List<ArmatureType> parts) {
        this.parts = parts;
    }

    public List<ArmatureType> getIntake() {
        return intake;
    }

    public List<ArmatureType> getParts() {
        return parts;
    }

    public Valve getValve() {
        return valve;
    }
}
