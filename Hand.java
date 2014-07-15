package com.example.thecasino;
import android.app.Activity;
import java.util.*;
//import com.example.theblackjackapp.*;
public class Hand
{
    private Vector<Card> hand;

    public Hand()
    {
       hand = new Vector<Card>();
    }
    
    public int size()
    {
       return hand.size();
    }
    
    public void addCard( Card card )
    {
       hand.add(card);
    }
    
    public Card getCard(int position)
    {
        // Get the card from the hand in given position, where positions
        // are numbered starting from 0.  If the specified position is
        // not the position number of a card in the hand, then null
        // is returned.
       Card selectedCard = null;
       
       if( position >= 0 || position < hand.size())
       {
          selectedCard = hand.get(position);
       }
        return selectedCard;
    }

    public int value()
    {
        // Returns the value of this hand for the
        // game of Blackjack.
        int handValue = 0;      // The value computed for the hand.
        boolean hasAce = false;  // This will be set to true if the
        //   hand contains an ace.
        Iterator<Card> iter = hand.iterator();

        while( iter.hasNext() )
        {
           Card card = iter.next();
           if( card.rank() == Card.Rank.ACE)
           {
              hasAce = true;
           }
           
           handValue += card.value();
        }

        // Now, handValue is the value of the hand, counting any ace as 1.
        // If there is an ace, and if changing its value from 1 to
        // 11 would leave the score less than or equal to 21,
        // then do so by adding the extra 10 points to handValue.

        if (hasAce == true && handValue + 10 <= 21)
        {
            handValue += 10;
        }
        
        return handValue;
    }
}