package edu.guilford;

/**
 * Represents the game of Blackjack.
 */
public class Blackjack {
    // The player's hand in the game
    private Hand playerHand;
    private Hand dealerHand;
    private Deck deck;

    /**
     * Initializes a new game of Blackjack and resets the deck.
     */
    public Blackjack() {
        reset(true);
    }

    /**
     * Returns the player's hand.
     * @return the player's hand
     */
    public Hand getPlayerHand() {
        return playerHand;
    }

    /**
     * Returns the dealer's hand.
     * @return the dealer's hand
     */
    public Hand getDealerHand() {
        return dealerHand;
    }

    /**
     * Returns the deck used in the game.
     * @return the deck
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     * Resets the game, optionally creating a new deck.
     * @param newDeck true to create a new deck, false to reuse the existing one
     */
    public void reset(boolean newDeck) {
        if (newDeck) {
            deck = new Deck();
            deck.shuffle();
        }
    }

    /**
     * Deals two cards to both the player and the dealer.
     */
    public void deal() {
        playerHand = new Hand();
        dealerHand = new Hand();
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
        playerHand.addCard(deck.deal());
        dealerHand.addCard(deck.deal());
    }

    /**
     * Simulates the player's turn, drawing cards until the total value is at least 16.
     * @return true if the player's total value is 21 or less, false otherwise
     */
    public boolean playerTurn() {
        while (playerHand.getTotalValue() < 16) {
            playerHand.addCard(deck.deal());
        }
        return playerHand.getTotalValue() <= 21;
    }

    /**
     * Simulates the dealer's turn, drawing cards until the total value is at least 17.
     * @return true if the dealer's total value is 21 or less, false otherwise
     */
    public boolean dealerTurn() {
        while (dealerHand.getTotalValue() < 17) {
            dealerHand.addCard(deck.deal());
        }
        return dealerHand.getTotalValue() <= 21;
    }

    /**
     * Returns a string representation of the player's and dealer's hands and their total values.
     * @return a string representation of the game state
     */
    @Override
    public String toString() {
        String result = "Player's Hand:\n";
        for (int i = 0; i < playerHand.size(); i++) {
            result += playerHand.getCard(i) + "\n";
        }
        result += "Player's Total: " + playerHand.getTotalValue() + "\n\n";
        result += "Dealer's Hand:\n";
        for (int i = 0; i < dealerHand.size(); i++) {
            result += dealerHand.getCard(i) + "\n";
        }
        result += "Dealer's Total: " + dealerHand.getTotalValue() + "\n\n";
        return result;
    }
}
