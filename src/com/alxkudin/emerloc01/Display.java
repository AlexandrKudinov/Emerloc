package com.alxkudin.emerloc01;

import java.awt.*;
import java.util.List;
import java.util.Map;

import static com.alxkudin.emerloc01.LocType.*;
import static com.alxkudin.emerloc01.NodeType.HOUSE_BLOCK;
import static com.alxkudin.emerloc01.NodeType.PIPELINE;


public class Display {
    public static final int BLOCK = 10;
    public static int baseX = 4 * BLOCK;
    public static int baseY = BLOCK;
    public static Color background = new Color(24, 24, 24);
    public static Color houseOpen = new Color(65, 65, 65);
    public static Color houseClose = new Color(45, 45, 45);
    public static Color plumbingOpen = new Color(0, 168, 243);
    public static Color plumbingClose = new Color(0, 67, 114);
    public static Color valveOpen = Color.white;
    public static Color valveClose = Color.red;

    public static void showHouseBlock(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen) {
            g.setColor(houseOpen);
        } else {
            g.setColor(houseClose);
        }
        g.fillRect(x, y, BLOCK, BLOCK);
    }


    public static void showUpDot(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen) {
            g.setColor(plumbingOpen);
        } else {
            g.setColor(plumbingClose);
        }
        g.fillRect(x + 4, y, 2, 2);
    }


    public static void showUpPlumb(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen) {
            g.setColor(plumbingOpen);
        } else {
            g.setColor(plumbingClose);
        }
        g.fillRect(x + 4, y, 2, 6);
    }


    public static void showDownDot(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen) {
            g.setColor(plumbingOpen);
        } else {
            g.setColor(plumbingClose);
        }
        g.fillRect(x + 4, y + 8, 2, 2);
    }

    public static void showDownPlumb(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen) {
            g.setColor(plumbingOpen);
        } else {
            g.setColor(plumbingClose);
        }
        g.fillRect(x + 4, y + 4, 2, 6);
    }

    public static void showLeftDot(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen == true) {
            g.setColor(plumbingOpen);
        } else {
            g.setColor(plumbingClose);
        }
        g.fillRect(x, y + 4, 2, 2);
    }

    public static void showLeftPlumb(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen) {
            g.setColor(plumbingOpen);
        } else {
            g.setColor(plumbingClose);
        }
        g.fillRect(x, y + 4, 6, 2);
    }


    public static void showRightDot(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen == true) {
            g.setColor(plumbingOpen);
        } else {
            g.setColor(plumbingClose);
        }
        g.fillRect(x + 8, y + 4, 2, 2);
    }

    public static void showRightPlumb(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen) {
            g.setColor(plumbingOpen);
        } else {
            g.setColor(plumbingClose);
        }
        g.fillRect(x + 4, y + 4, 6, 2);
    }


    public static void showValve(Graphics g, int i, int j, boolean isOpen) {
        if (isOpen) {
            g.setColor(valveOpen);
        } else {
            g.setColor(valveClose);
        }
        g.fillRect(i, j, 2, 2);
    }


    public static void showHousesMap(Graphics g) {
        Node[][] map = Structure.getInstance().getMap();

/*
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                Node node = map[i][j];
                if (node.isHouse()) {
                    showHouse(g, baseX + j * BLOCK, baseY + i * BLOCK, true);
                    continue;
                }
            }
        }
 */

        for (Pipeline pipeline : GameField.waterSupplyMap.getPipelines()) {
            Map<Pipe, List<LocType>> pipelineMap = pipeline.getPipes();
            pipeline.updateStatus();
            boolean open = pipeline.isOpen();
            for (Map.Entry<Pipe, List<LocType>> listEntry : pipelineMap.entrySet()) {
                int i = listEntry.getKey().getNode().getI();
                int j = listEntry.getKey().getNode().getJ();
                Pipe pipe = listEntry.getKey();
                if (listEntry.getValue().contains(UP)) {
                    if (pipe.containValve() && pipe.getValve().getType() == UP) {
                        showUpDot(g, baseX + j * BLOCK, baseY + i * BLOCK, open);
                    } else {
                        showUpPlumb(g, baseX + j * BLOCK, baseY + i * BLOCK, open);
                    }
                }
                if (listEntry.getValue().contains(LEFT)) {
                    if (pipe.containValve() && pipe.getValve().getType() == LEFT ||
                            pipe.containFourParts()) {
                        showLeftDot(g, baseX + j * BLOCK, baseY + i * BLOCK, open);
                    } else {
                        showLeftPlumb(g, baseX + j * BLOCK, baseY + i * BLOCK, open);
                    }
                }
                if (listEntry.getValue().contains(DOWN)) {
                    if (pipe.containValve() && pipe.getValve().getType() == DOWN) {
                        showDownDot(g, baseX + j * BLOCK, baseY + i * BLOCK, open);
                    } else {
                        showDownPlumb(g, baseX + j * BLOCK, baseY + i * BLOCK, open);
                    }
                }
                if (listEntry.getValue().contains(RIGHT)) {
                    if (pipe.containValve() && pipe.getValve().getType() == RIGHT ||
                            pipe.containFourParts()) {
                        showRightDot(g, baseX + j * BLOCK, baseY + i * BLOCK, open);
                    } else {
                        showRightPlumb(g, baseX + j * BLOCK, baseY + i * BLOCK, open);
                    }
                }

            }
            for (House house : pipeline.getHouses()) {
                for (Node node : house.getHouseFragments()) {
                    showHouseBlock(g, baseX + node.getJ() * BLOCK, baseY + node.getI() * BLOCK, open);
                }
            }

        }
        for (Valve valve : GameField.waterSupplyMap.getValves()) {
            showValve(g, valve.getJ(), valve.getI(), valve.isOpen());
        }
    }


}
