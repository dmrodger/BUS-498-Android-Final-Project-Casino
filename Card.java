package com.example.thecasino;

//import com.example.theblackjackapp.*;
import android.app.Activity;
public class Card {
    public enum Rank { ACE, DEUCE, THREE, FOUR, FIVE, SIX,
        SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING }

    public enum Suit { CLUBS, DIAMONDS, HEARTS, SPADES }

    private final Rank rank;
    private final Suit suit;
    
    public Card(Rank rank, Suit suit)
    {
        this.rank = rank;
        this.suit = suit;
    }

    public Rank rank() { return rank; }
    public Suit suit() { return suit; }
    public String toString() { return rank + "of" + suit; }
    
    public int value()
    {
        return Math.min(rank.ordinal() + 1, 10);
    }
    
    public String show()
    {
        return toString();
    }
}