package edu.guilford;

/**
 * The entry point for the card game simulation.
 */
public class CardGameDriver {
    public static void main(String[] args) {
        // Number of games to simulate
        final int NGAMES = 10000;
        int dealerWins = 0;
        int playerWins = 0;
        
        // Initialize a new Blackjack game
        Blackjack game = new Blackjack();
        game.deal();
        
        // Game simulation loop
        int iGame = 0;
        while (iGame < NGAMES) {
            game.deal();
            // Check for immediate wins
            if (game.getPlayerHand().getTotalValue() == 21) {
                playerWins++;
            } else if (game.getDealerHand().getTotalValue() == 21) {
                dealerWins++;
            } else {
                // Simulate player and dealer turns
                boolean playerResult = game.playerTurn();
                boolean dealerResult = game.dealerTurn();
                if (!playerResult) {
                    dealerWins++;
                } else if (!dealerResult) {
                    playerWins++;
                } else if (game.getPlayerHand().getTotalValue() < game.getDealerHand().getTotalValue()) {
                    dealerWins++;
                } else if (game.getPlayerHand().getTotalValue() > game.getDealerHand().getTotalValue()) {
                    playerWins++;
                }
            }
            // Reset the game if the deck is low
            if (game.getDeck().size() < 10) {
                game.reset(true);
            }
            iGame++;
        }
        
        // Output the results of the simulation
        System.out.println("Dealer wins: " + dealerWins);
        System.out.println("Player wins: " + playerWins);
        System.out.println("Pushes: " + (NGAMES - dealerWins - playerWins));

        // Initialize and simulate a game of Lamarckian Poker
        LamarckianPoker lmpGame = new LamarckianPoker();
        lmpGame.deal();
        System.out.println("\nInitial Lamarckian hands\n" + lmpGame);

        boolean gameDone = false;
        while (!gameDone) {
            // Simulate turns until the game is done
            gameDone = !lmpGame.turn();
        }
  
        // Output the final hands in Lamarckian Poker
        System.out.println("Final Lamarckian hands\n" + lmpGame);   
    }
}
