package com.alxkudin.emerloc01;

public class Valve {
    private ArmatureType type;
    boolean isOpen;

    public Valve(ArmatureType type){
        this.type=type;
    }

    public ArmatureType getType() {
        return type;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
