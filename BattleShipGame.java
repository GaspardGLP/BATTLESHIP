package TEST;

public class BattleShipGame {
    public static void main(String[] args) {
        // Create two players
        Player player1 = new Player("Player 1");
        Player player2 = new Player("Player 2");

        // Players place all their ships
        System.out.println("=== " + player1.getName() + " places ships ===");
        player1.placeSingleShip();

        System.out.println("=== " + player2.getName() + " places ships ===");
        player2.placeSingleShip();

        // Game loop
        while (!player1.hasLost() && !player2.hasLost()) {
            // Display boards
            System.out.println("\n" + player1.getName() + "'s board:");
            player1.displayBoard(false); // Show Player 1's board with ships visible
            System.out.println("\n" + player2.getName() + "'s board (hidden):");
            player2.displayBoard(true); // Show Player 2's board with ships hidden

            // Player 1's turn
            System.out.println("\n" + player1.getName() + "'s turn:");
            player1.makeMove(player2);

            if (player2.hasLost()) {
                // Check if Player 2 has lost after Player 1's attack
                break;
            }

            // Display updated boards after Player 1's move
            System.out.println("\n" + player1.getName() + "'s board:");
            player1.displayBoard(false);
            System.out.println("\n" + player2.getName() + "'s board (hidden):");
            player2.displayBoard(true);

            // Player 2's turn
            System.out.println("\n" + player2.getName() + "'s turn:");
            player2.makeMove(player1);

            // Check if Player 1 has lost after Player 2's attack
            if (player1.hasLost()) {
                break;
            }
        }

        // Announce the winner
        String winner = player1.hasLost() ? player2.getName() : player1.getName();
        System.out.println("\nThe winner is: " + winner + "!");
    }
}
