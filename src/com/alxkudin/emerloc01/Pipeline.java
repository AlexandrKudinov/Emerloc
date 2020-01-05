package com.alxkudin.emerloc01;

public class Pipeline {
    private WaterIntake waterIntake;
    public boolean hasWaterIntake() {
        return waterIntake == null ? false : true;
    }

    public WaterIntake getWaterIntake() {
        return waterIntake;
    }

    public void setWaterIntake(int intakeType) {
        waterIntake = WaterIntake.values()[intakeType];
    }

}
