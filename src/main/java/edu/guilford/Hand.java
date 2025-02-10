package edu.guilford;

import java.util.ArrayList;

/**
 * Represents a hand of playing cards.
 */
public class Hand {
    // The cards in the hand
    private ArrayList<Card> hand;

    /**
     * Constructor to create a new hand of cards.
     */
    public Hand() {
        hand = new ArrayList<Card>();
    }
    /**
     * Adds a card to the hand.
     * @param card the card to add
     */
    public void addCard(Card card) {
        hand.add(card);
    }

    /**
     * Removes a card from the hand.
     * @param card the card to remove
     */
    public void removeCard(Card card) {
        hand.remove(card);
    }

    /**
     * Returns the card at the specified index.
     * @param index the index of the card to return
     * @return the card at the specified index
     */
    public Card getCard(int index) {
        return hand.get(index);
    }

    /**
     * Returns the total value of the hand.
     * @return the total value of the hand
     */
    public int getTotalValue() {
        int totalValue = 0;
        int aceCount = 0;
        for (Card card : hand) {
            if (card.getRank() == Card.Rank.ACE) {
                aceCount++;
                totalValue += 11; // Initially count Ace as 11
            } else {
                totalValue += Math.min(card.getRank().ordinal() + 1, 10); // Count face cards as 10
            }
        }
        // Adjust for Aces if total value exceeds 21
        while (totalValue > 21 && aceCount > 0) {
            totalValue -= 10; // Count Ace as 1 instead of 11
            aceCount--;
        }
        return totalValue;
    }

    /**
     * Returns the number of cards in the hand.
     * @return the number of cards in the hand
     */
    public int size() {
        return hand.size();
    }

    /**
     * Resets the hand to an empty state.
     */
    public void reset() {
        hand.clear();
    }

    /**
     * Returns the cards in the hand.
     * @return the cards in the hand
     */
    public ArrayList<Card> getHand() {
        return hand;
    }

    @Override
    public String toString() {
        StringBuilder handString = new StringBuilder();
        for (Card card : hand) {
            handString.append(card.toString()).append("\n");
        }
        return handString.toString();
    }
}
