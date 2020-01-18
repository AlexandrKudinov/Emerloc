package com.alxkudin.emerloc01;

import java.awt.*;

import static com.alxkudin.emerloc01.LocType.*;
import static com.alxkudin.emerloc01.NodeType.HOUSE_BLOCK;


public class Display {
    public static final int BLOCK = 10;
    public static int baseX = 4 * BLOCK;
    public static int baseY = BLOCK;
    public static Color background = new Color(24, 24, 24);
    public static Color house = new Color(45, 45, 45);
    public static Color plumbingOpen = new Color(0, 168, 243);
    public static Color plumbingClose = new Color(0, 67, 114);
    public static Color valveOpen = Color.white;
    public static Color valveClose = Color.red;

    public static void showHouse(Graphics g, int x, int y) {
        g.setColor(house);
        g.fillRect(x, y, BLOCK, BLOCK);
    }


    public static void showCross(Graphics g, int x, int y) {
        g.setColor(background);
        g.fillRect(x + 2, y + 4, 2, 2);
        g.fillRect(x + 6, y + 4, 2, 2);
    }


    public static void showUpPlumb(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen == true) {
            g.setColor(plumbingOpen);
        } else {
            g.setColor(plumbingClose);
        }
        g.fillRect(x + 4, y, 2, 6);
    }

    public static void showDownPlumb(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen == true) {
            g.setColor(plumbingOpen);
        } else {
            g.setColor(plumbingClose);
        }
        g.fillRect(x + 4, y + 4, 2, 6);
    }

    public static void showLeftPlumb(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen == true) {
            g.setColor(plumbingOpen);
        } else {
            g.setColor(plumbingClose);
        }
        g.fillRect(x, y + 4, 6, 2);
    }

    public static void showRightPlumb(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen == true) {
            g.setColor(plumbingOpen);
        } else {
            g.setColor(plumbingClose);
        }
        g.fillRect(x + 4, y + 4, 6, 2);
    }


    public static void showLeftValve(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen == true) {
            g.setColor(valveOpen);
        } else {
            g.setColor(valveClose);
        }
        g.fillRect(x + 2, y + 4, 2, 2);
    }


    public static void showUpValve(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen == true) {
            g.setColor(valveOpen);
        } else {
            g.setColor(valveClose);
        }
        g.fillRect(x + 4, y + 2, 2, 2);
    }

    public static void showRightValve(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen == true) {
            g.setColor(valveOpen);
        } else {
            g.setColor(valveClose);
        }
        g.fillRect(x + 6, y + 4, 2, 2);
    }


    public static void showDownValve(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen == true) {
            g.setColor(valveOpen);
        } else {
            g.setColor(valveClose);
        }
        g.fillRect(x + 4, y + 6, 2, 2);
    }



    public static void showHousesMap(Graphics g) {
        Node[][] map = Structure.getInstance().getMap();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Node node = map[i][j];
                if (node.isHouse()) {
                    showHouse(g, baseX + j * BLOCK, baseY + i * BLOCK);
                    continue;
                }

                if (node.isPipelineBlock()) {
                    if (node.getPipe().containPart(UP)) {
                        Display.showUpPlumb(g, baseX + j * BLOCK, baseY + i * BLOCK, true);
                    }
                    if (node.getPipe().containPart(LEFT)) {
                        Display.showLeftPlumb(g, baseX + j * BLOCK, baseY + i * BLOCK, true);
                    }
                    if (node.getPipe().containPart(DOWN)) {
                        Display.showDownPlumb(g, baseX + j * BLOCK, baseY + i * BLOCK, true);
                    }
                    if (node.getPipe().containPart(RIGHT)) {
                        Display.showRightPlumb(g, baseX + j * BLOCK, baseY + i * BLOCK, true);
                    }
                    if (node.getPipe().getParts().size() == 4 || (node.getPipe().getParts().size() == 2 && node.getPipe().getIntake().size() == 2)) {
                        Display.showCross(g, baseX + j * BLOCK, baseY + i * BLOCK);
                    }

                    if(node.getPipe().getValve()!=null){
                        Valve valve = node.getPipe().getValve();
                        if(valve.getType().equals(UP)){
                            Display.showUpValve(g, baseX + j * BLOCK, baseY + i * BLOCK, true);
                        }
                        if(valve.getType().equals(DOWN)){
                            Display.showDownValve(g, baseX + j * BLOCK, baseY + i * BLOCK, true);
                        }
                        if(valve.getType().equals(LEFT)){
                            Display.showLeftValve(g, baseX + j * BLOCK, baseY + i * BLOCK, true);
                        }
                        if(valve.getType().equals(RIGHT)){
                            Display.showRightValve(g, baseX + j * BLOCK, baseY + i * BLOCK, true);
                        }
                    }

                }

            }

        }
    }


}
