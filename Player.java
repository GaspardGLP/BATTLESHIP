package TEST;

import java.util.Scanner;

public class Player {
    private String name;
    private Board board;

    public Player(String name) {
        this.name = name;
        this.board = new Board();
    }

    public String getName() {
        return name;
    }

    public void placeSingleShip() {
        Scanner scanner = new Scanner(System.in);
        Ship ship = new Ship(3); // Single ship of length 3
        boolean placed = false;

        while (!placed) {
            System.out.println(name + ", enter coordinates for your ship :");
            String start = scanner.next();
            String end = scanner.next();

            try {
                // Parse starting and ending positions
                int startX = Integer.parseInt(start.substring(1)) - 1; // Convert row to index
                int startY = board.letterToIndex(start.charAt(0)); // Convert column letter to index
                int endX = Integer.parseInt(end.substring(1)) - 1; // Convert row to index
                int endY = board.letterToIndex(end.charAt(0)); // Convert column letter to index

                // Validate input for horizontal or vertical placement
                if (startX == endX || startY == endY) {
                    placed = board.placeShip(ship, startX, startY, endX, endY);
                    if (!placed) {
                        System.out.println("Invalid placement. Ensure the ship fits and doesn't overlap.");
                    }
                } else {
                    System.out.println("Ships must be placed in a straight line. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input format. Use something like 'A1 to A3'.");
            }
        }
    }

    public void makeMove(Player opponent) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + ", enter coordinates for your attack :");
        String input = scanner.next();

        try {
            int x = Integer.parseInt(input.substring(1)) - 1; // Convert row to index
            int y = board.letterToIndex(input.charAt(0)); // Convert column letter to index

            System.out.println(opponent.board.receiveAttack(x, y));
        } catch (Exception e) {
            System.out.println("Invalid input. Format should be like 'A1'.");
        }
    }

    public boolean hasLost() {
        return board.allShipsSunk();
    }

    public void displayBoard(boolean hideShips) {
        board.displayBoard(hideShips);
    }
}
