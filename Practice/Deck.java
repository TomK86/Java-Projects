/**
 * Deck.java
 * Implementation of a standard 52-card deck, allowing the user to draw a card or
 * shuffle.  Each card also has an array of possible blackjack values, meaning this
 * class can be used to implement a game of blackjack.
 */


import java.io.*;
import java.util.*;

public class Deck {

  private enum Value {
    ACE("Ace", new int[]{1,11}),
    TWO("Two", new int[]{2}),
    THREE("Three", new int[]{3}),
    FOUR("Four", new int[]{4}),
    FIVE("Five", new int[]{5}),
    SIX("Six", new int[]{6}),
    SEVEN("Seven", new int[]{7}),
    EIGHT("Eight", new int[]{8}),
    NINE("Nine", new int[]{9}),
    TEN("Ten", new int[]{10}),
    JACK("Jack", new int[]{10}),
    QUEEN("Queen", new int[]{10}),
    KING("King", new int[]{10});

    private final String str;
    private final int[] bjv;
    
    Value(String str, int[] bjv) {
      this.str = str;
      this.bjv = bjv;
    }

    @Override
    public String toString() {
      return str;
    }

    public int[] getBlackJackValues() {
      return bjv;
    }
  }

  private enum Suit {
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs"),
    SPADES("Spades");

    private final String str;

    Suit(String str) {
      this.str = str;
    }

    @Override
    public String toString() {
      return str;
    }
  }

  private class Card {
    public final Value value;
    public final Suit suit;
    public final String name;
    public final boolean isRed;

    public Card(Value value, Suit suit) {
      this.value = value;
      this.suit = suit;
      this.name = value.toString() + " of " + suit.toString();
      this.isRed = (suit == Suit.HEARTS) || (suit == Suit.DIAMONDS);
    }
  }

  private List<Card> deck;
  private List<Card> discard;

  public Deck() {
    this.deck = new ArrayList<>(52);
    this.discard = new ArrayList<>(52);
    for (Value v : Value.values()) {
      for (Suit s : Suit.values()) {
        deck.add(new Card(v, s));
      }
    }
  }

  public Card draw() {
    Card card = deck.remove(deck.size()-1);
    discard.add(card);
    if (deck.isEmpty()) {
      deck.addAll(discard);
      discard.clear();
    }
    return card;
  }

  public void shuffle() {
    Collections.shuffle(deck);
  }

}