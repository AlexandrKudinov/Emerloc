package com.kudinov.emerloc01;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static com.kudinov.emerloc01.Display.*;

public class GameField extends JPanel {
    private static int xPosition;
    private static int yPosition;

    public static WaterSupplyMap waterSupplyMap = new WaterSupplyMap();
    public static Structure structure = Structure.getInstance();

    public GameField() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xPosition = e.getX();
                yPosition = e.getY();

                if (mouseClickOnValve(xPosition, yPosition) && Display.onEmergency) {
                    changeValveStage(xPosition, yPosition);
                    GameField.super.repaint();
                }
            }
        });
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                int key = e.getKeyCode();
                if (key == KeyEvent.VK_A) {
                    GameField.structure.getVan().turnLeft();
                    GameField.super.repaint();
                }
                if (key == KeyEvent.VK_D) {
                    GameField.structure.getVan().turnRight();
                    GameField.super.repaint();
                }

                if (key == KeyEvent.VK_W) {
                    GameField.structure.getVan().turnUp();
                    GameField.super.repaint();
                }
                if (key == KeyEvent.VK_S) {
                    GameField.structure.getVan().turnDown();
                    GameField.super.repaint();
                }

                if (key == KeyEvent.VK_R) {
                    Display.changeStage();
                    GameField.super.repaint();
                }

            }
        });
        setFocusable(true);
        structure.bind();
        structure.buildHouseBlocks();
        structure.buildHouses();
        structure.addVan();
        setBackground(Display.background);
        waterSupplyMap.addPipes();
        waterSupplyMap.addWaterIntake();
        waterSupplyMap.pipelineUnion();
        waterSupplyMap.checkValves();
        waterSupplyMap.generateAccidents();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Display.showHousesMap(g);
        if (!Display.onEmergency) {
            g.drawImage(van, structure.getVan().getJ(), structure.getVan().getI(),BLOCK,BLOCK,  this);
        }
    }


    public boolean mouseClickOnValve(int X, int Y) {
        for (Valve valve : waterSupplyMap.getValves()) {
            if ((X >= valve.getJ() && X <= valve.getJ() + BLOCK/5) &&
                    ((Y >= valve.getI() && Y <= valve.getI() + BLOCK/5))) {
                return true;
            }
        }
        return false;
    }

    public void changeValveStage(int X, int Y) {
        for (Valve valve : waterSupplyMap.getValves()) {
            if ((X >= valve.getJ() && X <= valve.getJ() + BLOCK/5) &&
                    ((Y >= valve.getI() && Y <= valve.getI() + BLOCK/5))) {
                valve.changeStage();
                return;
            }
        }
    }


}
/*
class FieldKeyListener extends KeyAdapter {
    @Override
    public void keyPressed(KeyEvent e) {
        super.keyPressed(e);
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_A) {
            GameField.structure.getVan().turnLeft();
        }
        if (key == KeyEvent.VK_D) {
            System.out.println("RIGHT");
        }

        if (key == KeyEvent.VK_W) {
            System.out.println("UP");
        }
        if (key == KeyEvent.VK_S) {
            System.out.println("DOWN");
        }
    }
}


 */