package TEST;

import java.util.ArrayList;

public class Ship {
    private int length;
    private ArrayList<int[]> positions = new ArrayList<>();

    public Ship(int length) {
        this.length = length;
    }

    public int getLength() {
        return length;
    }

    public void addPosition(int x, int y) {
        positions.add(new int[]{x, y});
    }

    public boolean isSunk() {
        for (int[] pos : positions) {
            if (pos[2] == 0) return false;
        }
        return true;
    }

    public void takeHit(int x, int y) {
        for (int[] pos : positions) {
            if (pos[0] == x && pos[1] == y) {
                pos[2] = 1;
                break;
            }
        }
    }
}
