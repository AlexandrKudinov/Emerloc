package com.alxkudin.emerloc01;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Pipe {
    private List<ArmatureType> parts = new LinkedList<>();
    private List<ArmatureType> intake = new LinkedList<>();
    private Valve valve;

    public void setValve(Valve valve) {
        this.valve = valve;
    }

    public boolean containPart(ArmatureType type) {
        return (parts.contains(type) || intake.contains(type));
    }

    public void addParts(ArmatureType... types) {
        this.parts.addAll(Arrays.asList(types));
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
