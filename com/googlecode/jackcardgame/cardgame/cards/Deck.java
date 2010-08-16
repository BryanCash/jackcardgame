/**
 *
 * @author ssoldatos
 */
package com.googlecode.jackcardgame.cardgame.cards;

import java.io.File;

/**
 *  An object of type Deck represents a deck of playing cards.  The deck
 *  is a regulate poker deck that contains 52 regular cards and that can
 *  also optionally include two Jokers.
 */
public class Deck implements CardConstants {

  public static final String DEFAULT = "default";
  public static final String DOO = "doo";
  public static final String FANTASY = "fantasy";
  public static final String HANDAPORT = "handaport";
  public static final String NUNAM = "nunam";
  public static final String TUXEDO = "tuxedo";
  public static int TOTAL_CARDS = 52;
  public static int NUMBER_OF_SUITES = 4;
  public static int NUMBER_OF_RANKS = 13;
  public static String DECK_PATH = "./decks";
  public static final String[] DECKS = {DEFAULT, DOO, FANTASY, HANDAPORT, NUNAM, TUXEDO};
  /**
   * An array of 52 or 54 cards.  A 54-card deck contains two Jokers,
   * in addtion to the 52 cards of a regular poker deck.
   */
  private Card[] deck;
  /**
   * Keeps track of the number of cards that have been dealt from
   * the deck so far.
   */
  private int cardsUsed;

  /**
   * Constructs a regular 52-card poker deck.  Initially, the cards
   * are in a sorted order.  The shuffle() method can be called to
   * randomize the order.  (Note that "new Deck()" is equivalent
   * to "new Deck(false)".)
   */
  public Deck() {

    deck = new Card[TOTAL_CARDS];

    int cardCt = 0; // How many cards have been created so far.
    for (int suit = 0; suit < NUMBER_OF_SUITES; suit++) {
      for (int rank = 1; rank <= NUMBER_OF_RANKS; rank++) {
        deck[cardCt] = new Card(rank, suit);
        cardCt++;
      }
    }

    cardsUsed = 0;
  }

  /**
   * Put all the used cards back into the deck (if any), and
   * shuffle the deck into a random order.
   */
  public void shuffle() {
    for (int i = deck.length - 1; i > 0; i--) {
      int rand = (int) (Math.random() * (i + 1));
      Card temp = deck[i];
      deck[i] = deck[rand];
      deck[rand] = temp;
    }
    cardsUsed = 0;
  }

  /**
   * As cards are dealt from the deck, the number of cards left
   * decreases.  This function returns the number of cards that
   * are still left in the deck.  The return value would be
   * 52 or 54 (depending on whether the deck includes Jokers)
   * when the deck is first created or after the deck has been
   * shuffled.  It decreases by 1 each time the dealCard() method
   * is called.
   */
  public int cardsLeft() {
    return deck.length - cardsUsed;
  }

  /**
   * Removes the next card from the deck and return it.  It is illegal
   * to call this method if there are no more cards in the deck.  You can
   * check the number of cards remaining by calling the cardsLeft() function.
   * @return the card which is removed from the deck.
   * @throws IllegalStateException if there are no cards left in the deck
   */
  public Card dealCard() {
    if (cardsUsed == deck.length) {
      throw new IllegalStateException("No cards are left in the deck.");
    }
    cardsUsed++;
    return deck[cardsUsed - 1];
    // Programming note:  Cards are not literally removed from the array
    // that represents the deck.  We just keep track of how many cards
    // have been used.
  }

  public void displayCards() {
    for (int i = 0; i < deck.length; i++) {
      Card card = deck[i];
    }
  }

  public static boolean isValidDeck(String deckname) {
    File deck = new File(DECK_PATH + "/" + deckname);
    File[] cards = deck.listFiles();
    if (cards.length < 53) {
      return false;
    }
    for (int i = 0; i < NUMBER_OF_SUITES; i++) {
      for (int j = 1; j <= NUMBER_OF_RANKS; j++) {
        Card card = new Card(j, i);
        String name = card.getSuitName().toLowerCase() + "-" + j + ".png";
        if (!new File(DECK_PATH + "/" + deckname + "/" + name).exists()) {
          com.googlecode.jackcardgame.functions.Functions.Error("Choose Deck", "The card's \"" + card + "\" image is missing from the deck");
          return false;
        }
      }
    }

    if (!new File(DECK_PATH + "/" + deckname + "/back.png").exists()) {
      com.googlecode.jackcardgame.functions.Functions.Error("Choose Deck", "The deck's back image is missing");
      return false;
    }
    return true;
  }
} // end class Deck

