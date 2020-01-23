package com.alxkudin.emerloc01;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GameField extends JPanel {
    private static int xPosition;
    private static int yPosition;


    public static WaterSupplyMap waterSupplyMap = new WaterSupplyMap();
    Structure structure = Structure.getInstance();

    public GameField() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xPosition = e.getX();
                yPosition = e.getY();
                if (mouseClickOnValve(xPosition, yPosition)) {
                    changeValveStage(xPosition, yPosition);
                    GameField.super.repaint();
                }
            }
        });
        addKeyListener(new FieldKeyListener());

        structure.bind();
        structure.buildHouseBlocks();
        structure.buildHouses();
        setBackground(Display.background);
        waterSupplyMap.addPipes();
        waterSupplyMap.addWaterIntake();
        waterSupplyMap.pipelineUnion();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Display.showHousesMap(g);
    }


    public boolean mouseClickOnValve(int X, int Y) {
        for (Valve valve : waterSupplyMap.getValves()) {
            if ((X >= valve.getJ() && X <= valve.getJ() + 2) && ((Y >= valve.getI() && Y <= valve.getI() + 2))) {
                return true;
            }
        }
        return false;
    }

    public void changeValveStage(int X, int Y) {
        for (Valve valve : waterSupplyMap.getValves()) {
            if ((X >= valve.getJ() && X <= valve.getJ() + 2) &&
                    ((Y >= valve.getI() && Y <= valve.getI() + 2))) {
                valve.changeStage();
                return;
            }
        }
    }


}
class FieldKeyListener extends KeyAdapter{
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();
        if(key == KeyEvent.VK_W ){
            System.out.println("LEFT");
        }
        if(key == KeyEvent.VK_RIGHT ){
            System.out.println("RIGHT");
        }

        if(key == KeyEvent.VK_UP ){
            System.out.println("UP");
        }
        if(key == KeyEvent.VK_DOWN ){
            System.out.println("DOWN");
        }
    }
}
