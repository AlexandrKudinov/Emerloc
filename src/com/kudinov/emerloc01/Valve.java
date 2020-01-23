package com.kudinov.emerloc01;

public class Valve {
    private LocType type;
    private boolean open = true;
    private int i;
    private int j;

    public Valve(LocType type, int i, int j) {
        this.type = type;
        this.i = i;
        this.j = j;

        switch (type) {
            case UP:
                this.j = Display.baseX + j * Display.BLOCK + 4;
                this.i = Display.baseY + i * Display.BLOCK + 2;
                break;
            case LEFT:
                this.j = Display.baseX + j * Display.BLOCK + 2;
                this.i = Display.baseY + i * Display.BLOCK + 4;
                break;
            case RIGHT:
                this.i = Display.baseY + i * Display.BLOCK + 4;
                this.j = Display.baseX + j * Display.BLOCK + 6;
                break;

            case DOWN:
                this.i = Display.baseY + i * Display.BLOCK + 6;
                this.j = Display.baseX + j * Display.BLOCK + 4;
        }


    }

    public LocType getType() {
        return type;
    }

    public int getJ() {
        return j;
    }

    public int getI() {
        return i;
    }

    public boolean isOpen() {
        return open;
    }

    public void changeStage() {
        open = !open;
    }
}
