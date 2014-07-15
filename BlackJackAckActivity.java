package com.example.thecasino;

import com.example.thecasino.R;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BlackJackAckActivity extends Activity {

    public static int bank_account;          // Amount of money the user has.
    int bet = 5;            // Amount user bets on a game.
    boolean userWins;   // Did the user win the game?
    boolean stay = false;
    
	boolean youArray[] = {true, false, false, false, false, false, false, false};
	boolean dealerArray[] = {true, false, false, false, false, false, false, false};
    
    Deck deck;
    Hand userHand;
    Hand dealerHand;
	
	Button button_deal_;
	Button button_hit_;
	Button button_stay_;
	Button button_rules_;

	EditText EditText_bet_amount_;
	
	TextView bank_account_tv_;
	
	ImageView you_imageView1_;
	ImageView you_imageView2_;
	ImageView you_imageView3_;
	ImageView you_imageView4_;
	ImageView you_imageView5_;
	ImageView you_imageView6_;
	ImageView you_imageView7_;
	ImageView you_imageView8_;
	ImageView dealer_imageView1_;
	ImageView dealer_imageView2_;
	ImageView dealer_imageView3_;
	ImageView dealer_imageView4_;
	ImageView dealer_imageView5_;
	ImageView dealer_imageView6_;
	ImageView dealer_imageView7_;
	ImageView dealer_imageView8_;
	
	String you_one;
	String you_two;
	String you_three;
	String you_four;
	String you_five;
	String you_six;
	String you_seven;
	String you_eight;
	String dealer_one;
	String dealer_two;
	String dealer_three;
	String dealer_four;
	String dealer_five;
	String dealer_six;
	String dealer_seven;
	String dealer_eight;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_black_jack_ack);
		
		button_deal_=(Button)findViewById(R.id.button_deal);
		button_hit_=(Button)findViewById(R.id.button_hit);
		button_stay_=(Button)findViewById(R.id.button_stay);
		EditText_bet_amount_=(EditText)findViewById(R.id.EditText_bet_amount);
		bank_account_tv_=(TextView)findViewById(R.id.global_bank_account_tv);
		you_imageView1_=(ImageView)findViewById(R.id.you_imageView1);
		you_imageView2_=(ImageView)findViewById(R.id.you_imageView2);
		you_imageView3_=(ImageView)findViewById(R.id.you_imageView3);
		you_imageView4_=(ImageView)findViewById(R.id.you_imageView4);
		you_imageView5_=(ImageView)findViewById(R.id.you_imageView5);
		you_imageView6_=(ImageView)findViewById(R.id.you_imageView6);
		you_imageView7_=(ImageView)findViewById(R.id.you_imageView7);
		you_imageView8_=(ImageView)findViewById(R.id.you_imageView8);
		dealer_imageView1_=(ImageView)findViewById(R.id.dealer_imageView1);
		dealer_imageView2_=(ImageView)findViewById(R.id.dealer_imageView2);
		dealer_imageView3_=(ImageView)findViewById(R.id.dealer_imageView3);
		dealer_imageView4_=(ImageView)findViewById(R.id.dealer_imageView4);
		dealer_imageView5_=(ImageView)findViewById(R.id.dealer_imageView5);
		dealer_imageView6_=(ImageView)findViewById(R.id.dealer_imageView6);
		dealer_imageView7_=(ImageView)findViewById(R.id.dealer_imageView7);
		dealer_imageView8_=(ImageView)findViewById(R.id.dealer_imageView8);
		button_rules_=(Button)findViewById(R.id.button_rules);

		// set blackjack bank account to home screen bank account
		bank_account = HomeScreenActivity.bank_account;
		write_bank_account();

		button_deal_.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) 
		{          
			start_deal();
		}});
		button_hit_.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) 
		{
			hit_me_baby();
		}});
		button_stay_.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) 
		{
			stay_with_me();
		}});
		button_rules_.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) 
		{
			rules();
		}});
	}
	
	public void rules()
	{
			AlertDialog alertDialog;
			alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("BlackJack Rules");
			alertDialog.setMessage("Get the value of your cards as close to 21 as possbile without going over. " +
					"If you go over 21 you bust and the hand is over. All face cards have a value of 10." + System.getProperty("line.separator")+
					"Dealer stays once they have 17 or above. Press deal to start a new game. Good luck and have fun!");
			alertDialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.black_jack_ack, menu);
		return true;
	}

	
	
	private void start_deal()
	{
		try
		{
			double d = Math.round(Double.parseDouble(EditText_bet_amount_.getText().toString()));
			bet = (int) d;
			EditText_bet_amount_.setText(bet + "");
		}
		catch (Exception e)
		{
			bet = 0;
			EditText_bet_amount_.setText("0");
			toast("That isn't even a number... your IQ makes me sad.");
		}
		//if they have enough money for their bet (but more than 0), then clear the board and start the deal
		if (bet <= bank_account && bet > 0)
		{
			you_imageView1_.setImageURI(null);
			you_imageView2_.setImageURI(null);
			you_imageView3_.setImageURI(null);
			you_imageView4_.setImageURI(null);
			you_imageView5_.setImageURI(null);
			you_imageView6_.setImageURI(null);
			you_imageView7_.setImageURI(null);
			you_imageView8_.setImageURI(null);
			
			dealer_imageView1_.setImageURI(null);
			dealer_imageView2_.setImageURI(null);
			dealer_imageView3_.setImageURI(null);
			dealer_imageView4_.setImageURI(null);
			dealer_imageView5_.setImageURI(null);
			dealer_imageView6_.setImageURI(null);
			dealer_imageView7_.setImageURI(null);
			dealer_imageView8_.setImageURI(null);
			
			youArray[0] = true;
			youArray[1] = false;
			youArray[2] = false;
			youArray[3] = false;
			youArray[4] = false;
			youArray[5] = false;
			youArray[6] = false;
			youArray[7] = false;

			dealerArray[0] = true;
			dealerArray[1] = false;
			dealerArray[2] = false;
			dealerArray[3] = false;
			dealerArray[4] = false;
			dealerArray[5] = false;
			dealerArray[6] = false;
			dealerArray[7] = false;
			// Sets up the game and deals 2 cards to each.
	        deck = new Deck();
	        userHand = new Hand();
	        dealerHand = new Hand();
	        stay = false;
	        userHand.addCard(deck.dealCard());
	        show_me_the_pictures(true, userHand.getCard(0));
	        dealerHand.addCard(deck.dealCard());
	        show_me_the_pictures(false, null);
	        userHand.addCard(deck.dealCard());
	        show_me_the_pictures(true, userHand.getCard(1));
	        dealerHand.addCard(deck.dealCard());
	        show_me_the_pictures(false, dealerHand.getCard(1));
	        bank_account -= bet;
	        write_bank_account();
	        if (userHand.value() == 21)
	        {
	        	toast("You got blackjack! It pays out 1.5x your bet.");
	        	I_got_BlackJack();
	        	write_bank_account();
	        }
		}
		else
		{
			if (bet == 0)
			{
				toast("You're gonna bet $0? Really? NO! Bet some money.");
			}
			else if (bet <0)
			{
				toast("Why would you try betting a negative amount? Come on now.");
			}
			else
			{
				toast("Not enough money for that bet... awkward...");
			}
		}
	}
	
	private void hit_me_baby()
	{
		try
		{
			if (!you_imageView1_.equals(null))
			{
				if (userHand.value() < 21 && !stay)
				{
			        Card newCard = deck.dealCard();
			        show_me_the_pictures(true, newCard);
			        userHand.addCard(newCard);
			        if (userHand.value() > 21)
			        {
			        	toast("You busted! Sucks to suck.");
			        	flipDealerFirst();
			        	write_bank_account();
			        }
				}
				else
				{
					toast("You can't hit anymore!");
				}
			}
		}
		catch (Exception e)
		{
			toast("Hit deal first...");
		}
	}
	
	private void stay_with_me()
	{
		try
		{
			if (stay)
			{
				toast("Game's over, chill out bro.");
			}
			else if (userHand.value() <= 21)
			{
				flipDealerFirst();
			    stay = true;
		        while(dealerHand.value() <= 16)
		        {
		           Card newCard = deck.dealCard();
		           show_me_the_pictures(false, newCard);
		           dealerHand.addCard(newCard);
		        }
		        if (dealerHand.value() > 21)
		        {
		    	    toast("Dealer busted! You win!");
		    	    bank_account += 2*bet;
		        }
		        else if(dealerHand.value() < userHand.value())
		        {
		        	toast("You beat the dealer!");
		        	bank_account += 2*bet;
		        }
		        else if(dealerHand.value() == userHand.value())
		        {
		        	toast("Ya'll hittas tied. Take yo money back.");
		        	bank_account += bet;
		        }
		        else
		        {
		        	toast("You're bad and you should feel bad, you lost.");
		        }
		        write_bank_account();
			}
			else
			{
				toast("You already busted. Play a new round!");
			}
		}
			catch(Exception e)
			{
				toast ("Press deal to start a new game.");
			}
		}
	
	
	public void I_got_BlackJack()
	{
		if (stay)
		{
			toast("Game's over, chill out bro.");
		}
		else if (userHand.value() <= 21)
		{
			flipDealerFirst();
		    stay = true;
	        while(dealerHand.value() <= 16)
	        {
	           Card newCard = deck.dealCard();
	           show_me_the_pictures(false, newCard);
	           dealerHand.addCard(newCard);
	        }
	        if (dealerHand.value() > 21)
	        {
	    	    toast("Dealer busted! You win!");
	    	    bank_account += 3*bet;
	        }
	        else if(dealerHand.value() < userHand.value())
	        {
	        	toast("You beat the dealer!");
	        	bank_account += 3*bet;
	        }
	        else if(dealerHand.value() == userHand.value())
	        {
	        	toast("Ya'll hittas tied. Good thing you got BlackJack!");
	        	bank_account += 3*bet;
	        }
	        else
	        {
	        	toast("You're bad and you should feel bad, you lost.");
	        }
	        write_bank_account();
	        
		}
		else
		{
			toast("You already busted. Play a new round!");
		}
		
		

	}
	

	
	public void write_bank_account()
	{
		bank_account_tv_.setText("$" + Integer.toString(bank_account));
		HomeScreenActivity.bank_account = bank_account;
		HomeScreenActivity.global_bank_account_tv_.setText("$" + Integer.toString(bank_account));
	}
	
	
	
	public void flipDealerFirst()
	{
		int suitAddNum = 0;
		if (dealerHand.getCard(0).suit() == Card.Suit.DIAMONDS)
		{
			suitAddNum = 13;
		}
		else if(dealerHand.getCard(0).suit() == Card.Suit.HEARTS)
		{
			suitAddNum = 26;
		}
		else if(dealerHand.getCard(0).suit() == Card.Suit.SPADES)
		{
			suitAddNum = 39;
		}
		int x = R.raw.aaaaaaceofclubs - 1 + suitAddNum + dealerHand.getCard(0).value();
		String uriPath = "android.resource://" + this.getPackageName() + "/" + x; 
		Uri uri = Uri.parse(uriPath);
		dealer_imageView1_.setImageURI(uri);
	}
	
	public void toast(String text) 	
	//utility function for showing a toast (android text dialog)
	{
		Context context = getApplicationContext();
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		toast.show();
	}
	
	private void show_me_the_pictures(boolean user, Card card)
	{
		if (card != null)
		{
			int suitAddNum = 0;
			if (card.suit() == Card.Suit.DIAMONDS)
			{
				suitAddNum = 13;
			}
			else if(card.suit() == Card.Suit.HEARTS)
			{
				suitAddNum = 26;
			}
			else if(card.suit() == Card.Suit.SPADES)
			{
				suitAddNum = 39;
			}
			int x = R.raw.aaaaaaceofclubs + suitAddNum + card.rank().ordinal();
			String uriPath = "android.resource://" + this.getPackageName() + "/" + x; 
			Uri uri = Uri.parse(uriPath); 
			if (user == true)
			{
				if (youArray[0] == true)
				{
					youArray[0] = false;
					youArray[1] = true;
					you_imageView1_.setImageURI(uri);
				}
				else if (youArray[1] == true)
				{
					youArray[1] = false;
					youArray[2] = true;
					you_imageView2_.setImageURI(uri);
				}
				else if (youArray[2] == true)
				{
					youArray[2] = false;
					youArray[3] = true;
					you_imageView3_.setImageURI(uri);
				}
				else if (youArray[3] == true)
				{
					youArray[3] = false;
					youArray[4] = true;
					you_imageView4_.setImageURI(uri);
				}
				else if (youArray[4] == true)
				{
					youArray[4] = false;
					youArray[5] = true;
					you_imageView5_.setImageURI(uri);
				}
				else if (youArray[5] == true)
				{
					youArray[5] = false;
					youArray[6] = true;
					you_imageView6_.setImageURI(uri);
				}
				else if (youArray[6] == true)
				{
					youArray[6] = false;
					youArray[7] = true;
					you_imageView7_.setImageURI(uri);
				}
				else
				{
					youArray[7] = false;
					you_imageView8_.setImageURI(uri);
				}							
			}
			else
			{
				if (dealerArray[0] == true)
				{
					dealerArray[0] = false;
					dealerArray[1] = true;
					dealer_imageView1_.setImageURI(uri);
				}
				else if (dealerArray[1] == true)
				{
					dealerArray[1] = false;
					dealerArray[2] = true;
					dealer_imageView2_.setImageURI(uri);
				}
				else if (dealerArray[2] == true)
				{
					dealerArray[2] = false;
					dealerArray[3] = true;
					dealer_imageView3_.setImageURI(uri);
				}
				else if (dealerArray[3] == true)
				{
					dealerArray[3] = false;
					dealerArray[4] = true;
					dealer_imageView4_.setImageURI(uri);
				}
				else if (dealerArray[4] == true)
				{
					dealerArray[4] = false;
					dealerArray[5] = true;
					dealer_imageView5_.setImageURI(uri);
				}
				else if (dealerArray[5] == true)
				{
					dealerArray[5] = false;
					dealerArray[6] = true;
					dealer_imageView6_.setImageURI(uri);
				}
				else if (dealerArray[6] == true)
				{
					dealerArray[6] = false;
					dealerArray[7] = true;
					dealer_imageView7_.setImageURI(uri);
				}
				else
				{
					dealerArray[7] = false;
					dealer_imageView8_.setImageURI(uri);
				}
			}
		}
		else
		{
			String uriPath = "android.resource://" + this.getPackageName() + "/" + R.raw.aaaaaaaaaaaaaaaa; 
			Uri back = Uri.parse(uriPath); 
			dealerArray[0] = false;
			dealerArray[1] = true;
			dealer_imageView1_.setImageURI(back);
		}
		
	}

	public void main_bank_account()
 	//updates main bank account
	{
		
	}
}  // end class