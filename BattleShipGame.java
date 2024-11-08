package TEST;

public class BattleShipGame {
    public static void main(String[] args) {
        Player player1 = new Player("Joueur 1");
        Player player2 = new Player("Joueur 2");

        player1.placeAllShips();
        player2.placeAllShips();

        while (!player1.hasLost() && !player2.hasLost()) {
            player1.makeMove(player2);
            if (!player2.hasLost()) {
                player2.makeMove(player1);
            }
        }

        System.out.println("Le gagnant est : " + (player1.hasLost() ? player2.getName() : player1.getName()));
    }
}
