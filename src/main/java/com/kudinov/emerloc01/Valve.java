package com.kudinov.emerloc01;

import java.util.List;
import java.util.Set;

import static com.kudinov.emerloc01.Display.*;

public class Valve {
    private LocType type;
    private boolean open = true;
    private int i;
    private int j;
    private Pipeline firstPipeline;
    private Pipeline secondPipeline;

    public Pipeline getFirstPipeline() {
        return firstPipeline;
    }




    public Pipeline getAnother(Pipeline pipeline) {
        if (pipeline == firstPipeline) {
            return secondPipeline;
        } else if (pipeline == secondPipeline) {
            return firstPipeline;
        } else return null;
    }

    public void setPipeline(Pipeline pipeline) {
        if(pipeline==secondPipeline || pipeline==firstPipeline){
            return;
        }
        if (firstPipeline == null) {
            firstPipeline = pipeline;
        } else if (secondPipeline == null) {
            secondPipeline = pipeline;
        }
    }

    public Pipeline getSecondPipeline() {
        return secondPipeline;
    }

    public void setFirstPipeline(Pipeline firstPipeline) {
        this.firstPipeline = firstPipeline;
    }

    public void setSecondPipeline(Pipeline secondPipeline) {
        this.secondPipeline = secondPipeline;
    }

    public Valve(LocType type, int i, int j) {
        this.type = type;
        this.i = i;
        this.j = j;

        switch (type) {
            case UP:
                this.j = baseX + j * Display.BLOCK + BLOCK / 5 * 2;
                this.i = Display.baseY + i * Display.BLOCK + BLOCK / 5;
                break;
            case LEFT:
                this.j = baseX + j * Display.BLOCK + BLOCK / 5;
                this.i = Display.baseY + i * Display.BLOCK + BLOCK / 5 * 2;
                break;
            case RIGHT:
                this.i = Display.baseY + i * Display.BLOCK + BLOCK / 5 * 2;
                this.j = baseX + j * Display.BLOCK + BLOCK / 5 * 3;
                break;

            case DOWN:
                this.i = Display.baseY + i * Display.BLOCK + BLOCK / 5 * 3;
                this.j = baseX + j * Display.BLOCK + BLOCK / 5 * 2;
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
