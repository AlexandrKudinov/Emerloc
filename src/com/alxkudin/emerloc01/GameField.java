package com.alxkudin.emerloc01;

import javax.swing.*;
import java.awt.*;

public class GameField extends JPanel {
    public static WaterSupplyMap waterSupplyMap = new WaterSupplyMap();

    public GameField() {
        Structure structure = Structure.getInstance();
        structure.bind();
        structure.buildHouseBlocks();
        structure.buildHouses();
        setBackground(Display.background);

        WaterSupplyMap waterSupplyMap = new WaterSupplyMap();
        waterSupplyMap.addPipes();
        waterSupplyMap.addWaterIntake();
        waterSupplyMap.pipelineUnion();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Display.showHousesMap(g);


    }


}