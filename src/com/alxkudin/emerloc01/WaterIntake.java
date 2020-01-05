package com.alxkudin.emerloc01;

public enum WaterIntake {
    UP(0), DOWN(5),LEFT(10),RIGHT(15),FROM_HOUSE(20);
    private int value;

    WaterIntake(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }
}

