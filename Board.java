package TEST;

public class Board {
    private final int SIZE = 10;
    private char[][] grid;

    public Board() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = '~';
            }
        }
    }

    public boolean placeShip(Ship ship, int x, int y, boolean isVertical) {
        if (isVertical) {
            if (x + ship.getLength() > SIZE) return false;
            for (int i = 0; i < ship.getLength(); i++) {
                if (grid[x + i][y] != '~') return false;
            }
            for (int i = 0; i < ship.getLength(); i++) {
                grid[x + i][y] = 'S';
                ship.addPosition(x + i, y);
            }
        } else {
            if (y + ship.getLength() > SIZE) return false;
            for (int i = 0; i < ship.getLength(); i++) {
                if (grid[x][y + i] != '~') return false;
            }
            for (int i = 0; i < ship.getLength(); i++) {
                grid[x][y + i] = 'S';
                ship.addPosition(x, y + i);
            }
        }
        return true;
    }

    public String receiveAttack(int x, int y) {
        if (grid[x][y] == 'S') {
            grid[x][y] = 'X';
            return "Touché!";
        } else {
            grid[x][y] = 'O';
            return "À l'eau!";
        }
    }

    public boolean allShipsSunk() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 'S') return false;
            }
        }
        return true;
    }

    public void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }
}
