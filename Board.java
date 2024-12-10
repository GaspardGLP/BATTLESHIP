package TEST;

public class Board {
    private final int SIZE = 7; // Adjust size for A-G, 1-7
    private char[][] grid;

    public Board() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = '~'; // ~ represents water
            }
        }
    }

    // Convert letter to column index
    public int letterToIndex(char letter) {
        return Character.toUpperCase(letter) - 'A';
    }

    public boolean placeShip(Ship ship, int startX, int startY, int endX, int endY) {
        // Ensure ship is in a straight line and correct length
        if (startX == endX) { // Horizontal placement
            int length = Math.abs(endY - startY) + 1;
            if (length != ship.getLength()) return false;
            for (int y = Math.min(startY, endY); y <= Math.max(startY, endY); y++) {
                if (grid[startX][y] != '~') return false;
            }
            for (int y = Math.min(startY, endY); y <= Math.max(startY, endY); y++) {
                grid[startX][y] = 'S';
                ship.addPosition(startX, y);
            }
        } else if (startY == endY) { // Vertical placement
            int length = Math.abs(endX - startX) + 1;
            if (length != ship.getLength()) return false;
            for (int x = Math.min(startX, endX); x <= Math.max(startX, endX); x++) {
                if (grid[x][startY] != '~') return false;
            }
            for (int x = Math.min(startX, endX); x <= Math.max(startX, endX); x++) {
                grid[x][startY] = 'S';
                ship.addPosition(x, startY);
            }
        } else {
            return false; // Not a valid placement
        }
        return true;
    }

    public String receiveAttack(int x, int y) {
        if (grid[x][y] == 'S') {
            grid[x][y] = 'X'; // X for hit
            return "Hit!";
        } else if (grid[x][y] == '~') {
            grid[x][y] = 'O'; // O for miss
            return "Miss!";
        }
        return "Already attacked here!";
    }

    public boolean allShipsSunk() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 'S') return false;
            }
        }
        return true;
    }

    public void displayBoard(boolean hideShips) {
        System.out.print("  ");
        for (char c = 'A'; c < 'A' + SIZE; c++) {
            System.out.print(c + " ");
        }
        System.out.println();

        for (int i = 0; i < SIZE; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < SIZE; j++) {
                if (hideShips && grid[i][j] == 'S') {
                    System.out.print("~ "); // Hide ships
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
