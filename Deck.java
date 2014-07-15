package com.example.thecasino;

import java.util.*;
//import com.example.theblackjackapp.*;
import android.app.Activity;

public class Deck extends Activity
{
   private Card[] deck;
   private int deckPosition = 0;
    
    public Deck()
    {
       deck = new Card[52];
       int counter = 0;
        for ( Card.Rank rank : Card.Rank.values())
        {
            for (Card.Suit suit : Card.Suit.values())
            {
                deck[counter] = new Card(rank, suit);
                counter++;
            }
        }
        
        shuffle();
        
    }

    public Card dealCard()
    {
       Card dealtCard = null;
       
       if( deckPosition >=0 && deckPosition < 52)
       {
          dealtCard = deck[deckPosition];
          deckPosition++;
       }
       else
       {
          shuffle();
          deckPosition = 0;
          dealtCard = deck[deckPosition];
       }

       return dealtCard;
    }
    
    private void shuffle()
    {       
        List<Card> listDeck = Arrays.asList(deck);
        Collections.shuffle(listDeck);
        for (int counter = 0; counter < deck.length; counter++)
        {
            deck[counter] = listDeck.get(counter);
        }
    }
    
}
