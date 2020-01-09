package com.alxkudin.emerloc01;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    public static HousesMap housesMap = new HousesMap();
    public static WaterSupplyMap waterSupplyMap = new WaterSupplyMap();

    public GameField() {
        setBackground(Display.background);
        housesMap.buildHouseBlocks();
        housesMap.unionHouses();
        waterSupplyMap.build();
        waterSupplyMap.addWaterIntake();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Display.showHousesMap(g);

    }


}