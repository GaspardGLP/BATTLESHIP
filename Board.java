package TEST;

public class Board {
    private final int SIZE = 10;
    private char[][] grid;

    public Board() {
        grid = new char[SIZE][SIZE];
        // Initialize the grid with '~' (empty water)
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = '~';
            }
        }
    }

    // Place a ship on the board
    public boolean placeShip(Ship ship, int x, int y, boolean isVertical) {
        if (isVertical) {
            // Check if the ship fits vertically
            if (x + ship.getLength() > SIZE) return false;
            for (int i = 0; i < ship.getLength(); i++) {
                if (grid[x + i][y] != '~') return false; // Ensure no overlapping
            }
            // Place the ship
            for (int i = 0; i < ship.getLength(); i++) {
                grid[x + i][y] = 'S';
                ship.addPosition(x + i, y);
            }
        } else {
            // Check if the ship fits horizontally
            if (y + ship.getLength() > SIZE) return false;
            for (int i = 0; i < ship.getLength(); i++) {
                if (grid[x][y + i] != '~') return false; // Ensure no overlapping
            }
            // Place the ship
            for (int i = 0; i < ship.getLength(); i++) {
                grid[x][y + i] = 'S';
                ship.addPosition(x, y + i);
            }
        }
        return true;
    }

    // Handle an attack on the board
    public String receiveAttack(int x, int y) {
        if (grid[x][y] == 'S') {
            grid[x][y] = 'X'; // Mark as hit
            return "Hit!";
        } else {
            grid[x][y] = 'O'; // Mark as miss
            return "Miss!";
        }
    }

    // Check if all ships on the board are sunk
    public boolean allShipsSunk() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (grid[i][j] == 'S') return false;
            }
        }
        return true;
    }

    // Display the board, optionally hiding enemy ships
    public void displayBoard(boolean hideShips) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (hideShips && grid[i][j] == 'S') {
                    System.out.print("x "); // Mask ships with 'x'
                } else {
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
