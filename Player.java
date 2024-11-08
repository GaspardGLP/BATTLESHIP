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

    public void placeAllShips() {
        Scanner scanner = new Scanner(System.in);
        for (int length : new int[]{5, 4, 3, 3, 2}) {
            Ship ship = new Ship(length);
            boolean placed = false;
            while (!placed) {
                System.out.println(name + ", entrez les coordonnées de votre navire de longueur " + length);
                int x = scanner.nextInt();
                int y = scanner.nextInt();
                System.out.println("Vertical ? (true/false)");
                boolean isVertical = scanner.nextBoolean();
                placed = board.placeShip(ship, x, y, isVertical);
                if (!placed) System.out.println("Placement invalide. Réessayez.");
            }
        }
    }

    public void makeMove(Player opponent) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(name + ", entrez les coordonnées de votre attaque : ");
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        System.out.println(opponent.board.receiveAttack(x, y));
    }

    public boolean hasLost() {
        return board.allShipsSunk();
    }
}
