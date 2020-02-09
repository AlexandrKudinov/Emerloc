package logic;



import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static logic.Display.BLOCK;
import static logic.Display.van;


public class GameField extends JPanel {
    Display display = new Display();
    private static int xPosition;
    private static int yPosition;


    public  Structure structure = new Structure();
    public  WaterSupplyMap waterSupplyMap = new WaterSupplyMap();



    public GameField() {
        display.setWaterSupplyMap(waterSupplyMap);
        setFocusable(true);
        waterSupplyMap.setStructure(structure);
        structure.bind();
        structure.buildHouseBlocks();
        structure.buildHouses();
        setBackground(display.background);
        waterSupplyMap.setMap(structure.getMap());
        waterSupplyMap.addPipes();
        waterSupplyMap.addWaterIntake();
        waterSupplyMap.pipelineUnion();
        waterSupplyMap.generateAccidents();
        structure.addVan();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                xPosition = e.getX();
                yPosition = e.getY();

                if (mouseClickOnValve(xPosition, yPosition) && display.onEmergency) {
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
                    structure.getVan().turnLeft();
                    GameField.super.repaint();
                }
                if (key == KeyEvent.VK_D) {
                    structure.getVan().turnRight();
                    GameField.super.repaint();
                }

                if (key == KeyEvent.VK_W) {
                    structure.getVan().turnUp();
                    GameField.super.repaint();
                }
                if (key == KeyEvent.VK_S) {
                   structure.getVan().turnDown();
                    GameField.super.repaint();
                }

                if (key == KeyEvent.VK_R) {
                    display.changeStage();
                    GameField.super.repaint();
                }
            }
        });
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        display.showHousesMap(g);
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
