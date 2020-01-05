package com.alxkudin.emerloc01;

import java.awt.*;

import static com.alxkudin.emerloc01.MapStructure.BLOCK;
import static com.alxkudin.emerloc01.MapStructure.INSTANCE;


public class Display {
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
        g.fillRect(x+2, y+4, 2, 2);
        g.fillRect(x+6, y+4, 2, 2);
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
        g.fillRect(x + 1, y + 4, 2, 2);
    }


    public static void showUpValve(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen == true) {
            g.setColor(valveOpen);
        } else {
            g.setColor(valveClose);
        }
        g.fillRect(x + 4, y + 1, 2, 2);
    }

    public static void showRightValve(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen == true) {
            g.setColor(valveOpen);
        } else {
            g.setColor(valveClose);
        }
        g.fillRect(x + 7, y + 4, 2, 2);
    }


    public static void showDownValve(Graphics g, int x, int y, boolean isOpen) {
        if (isOpen == true) {
            g.setColor(valveOpen);
        } else {
            g.setColor(valveClose);
        }
        g.fillRect(x + 4, y + 7, 2, 2);
    }


    public static void showHousesMap(Graphics g) {
        for (int j = 0; j < 60; j++) {
            for (int i = 0; i < 120; i++) {
                Node node = INSTANCE.get(i, j);
                if (node.isHouse()) {
                    showHouse(g, baseX + i * BLOCK, baseY + j * BLOCK);
                    continue;
                }
/*
                if (fragment.typeIs(10)) {
                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showCross(g, baseX + i * BLOCK, baseY + j * BLOCK);
                    continue;
                }

                if (fragment.typeIs(14)) {

                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                continue;
                }

                if (fragment.typeIs(16)) {
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
               continue;
                }

                if (fragment.typeIs(24)) {
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
               continue;
                }


                if (fragment.typeIs(18)) {
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                continue;
                }


                if (fragment.typeIs(22)) {
                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                continue;
                }


                if (fragment.typeIs(20)) {
                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                continue;
                }

                if (fragment.typeIs(26)){
                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftValve(g, baseX + i * BLOCK, baseY + j * BLOCK, true);

                    continue;
                }

                if (fragment.typeIs(28)){
                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showUpValve(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    continue;
                }

                if (fragment.typeIs(30)){
                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightValve(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                continue;
                }

                if (fragment.typeIs(32)){
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownValve(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    continue;
                }

                if (fragment.typeIs(40)){
                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    continue;
                }

                if (fragment.typeIs(42)){
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    continue;
                }

                if (fragment.typeIs(46)){
                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    continue;
                }
                if (fragment.typeIs(44)){
                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    continue;
                }


                if (fragment.typeIs(56)){
                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftValve(g, baseX + i * BLOCK, baseY + j * BLOCK, true);

                    continue;
                }

                if (fragment.typeIs(58)){
                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showUpValve(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    continue;
                }

                if (fragment.typeIs(50)){
                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showRightValve(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    continue;
                }
                if (fragment.typeIs(52)){
                    Display.showRightPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showLeftPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showUpPlumb(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    Display.showDownValve(g, baseX + i * BLOCK, baseY + j * BLOCK, true);
                    continue;
                }

 */
            }


            }
        }


    }
