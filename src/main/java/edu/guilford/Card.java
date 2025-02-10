package edu.guilford;

import java.util.Random;

/**
 * Represents a playing card with a suit and rank.
 */
public class Card implements Comparable<Card> {
    // Enum for the suits of the card


    public enum Suit {
        CLUBS, DIAMONDS, HEARTS, SPADES
    }

    // Enum for the ranks of the card

    public enum Rank {
        ACE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN,
        KING
    }

    // Instance variables
    private Suit suit;
    private Rank rank;

    /**
     * Constructor to create a card with a specific suit and rank.
     * @param suit the suit of the card
     * @param rank the rank of the card
     */
    public Card(Suit suit, Rank rank) {
        this.suit = suit;
        this.rank = rank;
    }

    // Constructor to create a random card
  
    public Card() {
        Random rand = new Random();
        int suit = rand.nextInt(Suit.values().length);
        int rank = rand.nextInt(Rank.values().length);
        this.suit = Suit.values()[suit];
        this.rank = Rank.values()[rank];
    }

    /**
     * Returns the suit of the card.
     * @return the suit of the card
     */
    public Suit getSuit() {
        return suit;
    }

    // Getter for the rank of the card
    /**
     * Returns the rank of the card.
     * @return the rank of the card
     */
    public Rank getRank() {
        return rank;
    }

    // Returns a string representation of the card
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public int compareTo(Card otherCard) {
        // Compare cards first by rank, then by suit
        if (this.rank.ordinal() > otherCard.rank.ordinal()) {
            return 1;
        } else if (this.rank.ordinal() < otherCard.rank.ordinal()) {
            return -1;
        } else {
            if (this.suit.ordinal() > otherCard.suit.ordinal()) {
                return 1;
            } else if (this.suit.ordinal() < otherCard.suit.ordinal()) {
                return -1;
            }
        }
        return 0;
    }
}
