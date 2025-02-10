package edu.guilford;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represents the game of Lamarckian Poker.
 */
public class LamarckianPoker {
    // The hands of the two players
    private Hand player1Hand;
    private Hand player2Hand;
    private Hand pool;
    private Deck discard;
    private Deck deck;
    private Random rand = new Random();
    private int iTurn;

    /**
     * Initializes a new game of Lamarckian Poker and resets the deck.
     */
    public LamarckianPoker() {
        reset(true);
    }

    /**
     * Returns the hand of player 1.
     * @return the hand of player 1
     */
    public Hand getPlayer1Hand() {
        return player1Hand;
    }

    /**
     * Returns the hand of player 2.
     * @return the hand of player 2
     */
    public Hand getPlayer2Hand() {
        return player2Hand;
    }

    /**
     * Returns the pool of cards.
     * @return the pool of cards
     */
    public Hand getPool() {
        return pool;
    }

    /**
     * Resets the game, optionally creating a new deck and clearing the discard pile.
     * @param newDeck true to create a new deck, false to reuse the existing one
     */
    public void reset(boolean newDeck) {
        if (newDeck) {
            deck = new Deck();
            discard = new Deck();
            discard.clear();
            deck.shuffle();
        }
        iTurn = 0;
    }

    /**
     * Deals four cards to each player.
     */
    public void deal() {
        player1Hand = new Hand();
        player2Hand = new Hand();
        for (int iCard = 0; iCard < 4; iCard++) {
            player1Hand.addCard(deck.deal());
            player2Hand.addCard(deck.deal());
        }
        makePool(); // Create the pool after dealing cards
    }

    /**
     * Creates a pool of four cards from the deck.
     */
    public void makePool() {
        if (deck.size() < 4) {
            return; // Not enough cards to make a pool
        }
        pool = new Hand();
        for (int iCard = 0; iCard < 4; iCard++) {
            pool.addCard(deck.deal());
        }
    }

    /**
     * Executes a turn in the game, allowing players to draw cards from the pool based on their selected cards.
     * @return true if the turn was successful, false otherwise
     */
    public boolean turn() {
        if (player1Hand.size() < 7 || player2Hand.size() < 7) {
            makePool(); // Ensure the pool is created before proceeding

            if (player1Hand.size() == 0 || player2Hand.size() == 0) {
                return false; // Cannot proceed if either hand is empty
            }
            Card player1Card = player1Hand.getCard(rand.nextInt(player1Hand.size()));
            Card player2Card = player2Hand.getCard(rand.nextInt(player2Hand.size()));
            Hand firstHand, secondHand;
            Card firstCard, secondCard;
            if (player1Card.getRank().ordinal() > player2Card.getRank().ordinal()) {
                firstHand = player1Hand;
                secondHand = player2Hand;
                firstCard = player1Card;
                secondCard = player2Card;
            } else if (player1Card.getRank().ordinal() < player2Card.getRank().ordinal()) {
                firstHand = player2Hand;
                secondHand = player1Hand;
                firstCard = player2Card;
                secondCard = player1Card;
            } else {
                if (player1Card.getSuit().ordinal() > player2Card.getSuit().ordinal()) {
                    firstHand = player1Hand;
                    secondHand = player2Hand;
                    firstCard = player1Card;
                    secondCard = player2Card;
                } else {
                    firstHand = player2Hand;
                    secondHand = player1Hand;
                    firstCard = player2Card;
                    secondCard = player1Card;
                }
            }

            ArrayList<Card> poolRemove = new ArrayList<Card>();

            for (Card poolCard : pool.getHand()) {
                if (firstCard.getRank().ordinal() == poolCard.getRank().ordinal() ||
                        firstCard.getSuit().ordinal() == poolCard.getSuit().ordinal()) {
                    firstHand.addCard(poolCard);
                    poolRemove.add(poolCard);
                }
            }
            // Remove cards from pool
            for (Card poolCard : poolRemove) {
                pool.removeCard(poolCard);
            }
            poolRemove.clear();
            pool.addCard(firstCard);
            firstHand.removeCard(firstCard);
            for (Card poolCard : pool.getHand()) {
                if (secondCard.getRank().ordinal() == poolCard.getRank().ordinal() ||
                        secondCard.getSuit().ordinal() == poolCard.getSuit().ordinal()) {
                    secondHand.addCard(poolCard);
                    poolRemove.add(poolCard);
                }
            }
            for (Card poolCard : poolRemove) {
                pool.removeCard(poolCard);
            }
            pool.addCard(secondCard);
            secondHand.removeCard(secondCard);
            for (Card poolCard : pool.getHand()) {
                discard.getDeck().add(poolCard);
            }
            pool.getHand().clear();
            if (deck.size() < 4) {
                for (Card card : discard.getDeck()) {
                    deck.getDeck().add(card);
                }
                discard.clear();
            }
            iTurn++;
            
            return true; 
        } else {
            return false; 
        }
    }

    /**

    /**
     * Returns a string representation of the players' hands and the pool.
     * @return a string representation of the game state
     */
    @Override
    public String toString() {
        return "\nPlayer 1: \n" + player1Hand + "\nPlayer 2: \n" + player2Hand + "\nPool: " + pool + "\n";
    }
}
