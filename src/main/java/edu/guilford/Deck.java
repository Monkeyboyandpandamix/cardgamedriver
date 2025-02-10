package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents a deck of playing cards.
 */
public class Deck {
    private ArrayList<Card> deck = new ArrayList<Card>();
    private Random rand = new Random();

    /**
     * Constructor to create a new deck of cards.
     */
    public Deck() {
        build();
    }

    /**
     * Returns the current deck of cards.
     * @return the current deck of cards
     */
    public ArrayList<Card> getDeck() {
        return deck;
    }

    /**
     * Clears the deck of cards.
     */
    public void clear() {
        deck.clear();
    }

    /**
     * Builds a new deck of cards.
     */
    public void build() {
        for (Card.Suit suit : Card.Suit.values()) {
            for (Card.Rank rank : Card.Rank.values()) {
                deck.add(new Card(suit, rank));
            }
        }
    }

    /**
     * Shuffles the deck of cards.
     */
    public void shuffle() {
        ArrayList<Card> tempDeck = new ArrayList<Card>();
        while (deck.size() > 0) {
            int loc = rand.nextInt(deck.size());
            tempDeck.add(deck.get(loc));
            deck.remove(loc);
        }
        deck = tempDeck;
    }

    /**
     * Deals a card from the deck.
     * @return the dealt card
     */
    public Card deal() {
        if (deck.isEmpty()) {
            return null; // or throw an exception
        }
        return deck.remove(0);
    }

    /**
     * Returns the number of cards in the deck.
     * @return the number of cards in the deck
     */
    public int size() {
        return deck.size();
    }

    @Override
    public String toString() {
        String deckString = "";
        for (Card card : deck) {
            deckString += card.toString() + "\n";
        }
        return deckString;
    }
}
