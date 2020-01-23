package com.kudinov.emerloc01;

import static com.kudinov.emerloc01.Display.*;

public class Van {
    int i;
    int j;

    int MAX_J = baseX + 119 * BLOCK;
    int MAX_I = baseY + 59 * BLOCK;

    public Van(int i, int j) {
        this.i = baseY + i * BLOCK;
        this.j = baseX + j * BLOCK;
    }

    private boolean canMove(int i, int j) {
        int nodeI = (i - baseY) / BLOCK;
        int nodeJ = (j - baseX) / BLOCK;
        Node node = GameField.structure.getMap()[nodeI][nodeJ];
        return !node.isHouse();
    }

    private boolean onAccident() {
        int nodeI = (i - baseY) / BLOCK;
        int nodeJ = (j - baseX) / BLOCK;
        Node node = GameField.structure.getMap()[nodeI][nodeJ];
        return node.getPipe().isAccident();
    }


    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }

    public void turnLeft() {
        if (j == baseX) {
            j = MAX_J;
            return;
        }
        if (canMove(i + 1, j - 1) && canMove(i + BLOCK - 1, j - 1)) {
            j -= 10;
        }

        if (onAccident()) {
            onEmergency = true;
        } else onEmergency = false;
    }

    public void turnRight() {
        if (j == MAX_J) {
            j = baseX;
            return;
        }
        if (canMove(i + 1, j + BLOCK + 1) && canMove(i + BLOCK - 1, j + BLOCK + 1)) {
            j += 10;
        }
        if (onAccident()) {
            onEmergency = true;
        } else onEmergency = false;
    }

    public void turnUp() {
        if (i == baseY) {
            i = MAX_I;
            return;
        }
        if (canMove(i - 1, j + 1) && canMove(i - 1, j + BLOCK - 1)) {
            i -= 10;
        }
        if (onAccident()) {
            onEmergency = true;
        } else onEmergency = false;
    }

    public void turnDown() {
        if (i == MAX_I) {
            i = baseY;
            return;
        }
        if (canMove(i + BLOCK + 1, j + 1) && canMove(i + BLOCK + 1, j + BLOCK - 1)) {
            i += 10;
        }
        if (onAccident()) {
            onEmergency = true;
        } else onEmergency = false;

    }


}
