package com.alxkudin.emerloc01;

public class Valve {
    private LocType type;
    boolean isOpen;

    public Valve(LocType type){
        this.type=type;
    }

    public LocType getType() {
        return type;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setStage(boolean open) {
        isOpen = open;
    }
}
