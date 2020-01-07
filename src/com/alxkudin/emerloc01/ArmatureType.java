package com.alxkudin.emerloc01;

public enum ArmatureType {
    UP(5), DOWN(10), LEFT(15), RIGHT(20);
    private int value;

    ArmatureType(int value) {
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
