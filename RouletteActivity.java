package com.example.thecasino;

import com.example.thecasino.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RouletteActivity extends Activity {

	int bank_account = HomeScreenActivity.bank_account;
	Roulette roulette = new Roulette();
	
	Button BJ_Instructions_;
	Button spin_btn_;
	Button reset_bets_btn_;

	EditText first12_et_;
	EditText second12_et_;
	EditText third12_et_;	
	EditText row1_et_;
	EditText row2_et_;
	EditText row3_et_;
	EditText one_to_eighteen_et_;
	EditText even_et_;
	EditText black_et_;
	EditText red_et_;
	EditText odd_et_;
	EditText nineteen_to_thirtysix_et_;
	EditText single_bet_numbers_et_;
	EditText single_numbers_bet_;
	
	TextView number_display_tv_;
	TextView bank_account_tv_;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_roulette);
		
		first12_et_=(EditText)findViewById(R.id.first12_et);
		second12_et_=(EditText)findViewById(R.id.second12_et);
		third12_et_=(EditText)findViewById(R.id.third12_et);
		spin_btn_=(Button)findViewById(R.id.spin_btn);
		row1_et_=(EditText)findViewById(R.id.row1_et);
		row2_et_=(EditText)findViewById(R.id.row2_et);
		row3_et_=(EditText)findViewById(R.id.row3_et);
		one_to_eighteen_et_=(EditText)findViewById(R.id.one_to_eighteen_et);
		even_et_=(EditText)findViewById(R.id.even_et);
		black_et_=(EditText)findViewById(R.id.black_et);
		red_et_=(EditText)findViewById(R.id.red_et);
		odd_et_=(EditText)findViewById(R.id.odd_et);
		nineteen_to_thirtysix_et_=(EditText)findViewById(R.id.nineteen_to_thirtysix_et);
		single_bet_numbers_et_=(EditText)findViewById(R.id.single_bet_numbers_et);
		single_numbers_bet_=(EditText)findViewById(R.id.single_numbers_bet);
		number_display_tv_=(TextView)findViewById(R.id.number_display_tv);
		bank_account_tv_=(TextView)findViewById(R.id.global_bank_account_tv);
		BJ_Instructions_=(Button)findViewById(R.id.BJ_Instructions);
		reset_bets_btn_=(Button)findViewById(R.id.reset_bets_btn);

		number_display_tv_.setBackgroundResource(R.raw.aaaaaaawhitebackground);
		write_bank_account();
		
		spin_btn_.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) 
		{
			spin();
		}});
		BJ_Instructions_.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) 
		{
			instructions();
		}});
		reset_bets_btn_.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) 
		{
			reset_bets();
		}});
	}
	
		public void spin()
	    {
	        int totalBet = 0;
	        int winnings = 0;
	        int howMany = 0;
	        String[] arrayNums = null;
	        String numbersTextField = null;
	        try{
	        	try
	        	{
		            numbersTextField = single_bet_numbers_et_.getText().toString();
		            arrayNums = numbersTextField.split(",");
		            for (int i = 0; i < arrayNums.length; i++)
		            {
			            @SuppressWarnings("unused")
	            		int x = Integer.parseInt(arrayNums[i].toString());
			            howMany++;
		            }
	        	}
	        	catch(Exception e)
	        	{
	        		if (Integer.parseInt(single_numbers_bet_.getText().toString()) > 0)
	        		{
	        			toast("Don't bet like an idiot, I'm not counting your numbers bet..");
	        		}
	        		howMany = 0;
	        	}
		        totalBet += Integer.parseInt(first12_et_.getText().toString());
		        totalBet += Integer.parseInt(second12_et_.getText().toString());
		        totalBet += Integer.parseInt(third12_et_.getText().toString());
		        totalBet += Integer.parseInt(row1_et_.getText().toString());
		        totalBet += Integer.parseInt(row2_et_.getText().toString());
		        totalBet += Integer.parseInt(row3_et_.getText().toString());
		        totalBet += Integer.parseInt(one_to_eighteen_et_.getText().toString());
		        totalBet += Integer.parseInt(even_et_.getText().toString());
		        totalBet += Integer.parseInt(black_et_.getText().toString());
		        totalBet += Integer.parseInt(red_et_.getText().toString());
		        totalBet += Integer.parseInt(odd_et_.getText().toString());
		        totalBet += Integer.parseInt(nineteen_to_thirtysix_et_.getText().toString());
		        totalBet += howMany*Integer.parseInt(single_numbers_bet_.getText().toString());
	        }
	        catch (Exception e)
	        {
	        	toast("I'm going to go ahead and disregard all " +
	        			"impossible/empty bet values and run the rest.");
	        }
	        if (totalBet > bank_account)
	        {
	            toast("You're betting too much..");
	        }
	        else if (totalBet > 0)
	        {
	            bank_account -= totalBet;
	            int spinNumber = roulette.spin();
	            if (spinNumber == -1)
	            {
	            	number_display_tv_.setTextColor(Color.parseColor("#088A08"));
	            	number_display_tv_.setText("00");
	            }
	            else
	            {
	            	if (spinNumber == 2|| spinNumber == 4 || spinNumber == 6|| spinNumber == 8|| spinNumber == 10|| 
	            			spinNumber == 11|| spinNumber == 13|| spinNumber == 15|| spinNumber == 17|| 
	            			spinNumber == 20|| spinNumber == 22|| spinNumber == 24|| spinNumber == 26|| 
	            			spinNumber == 28 || spinNumber == 29|| spinNumber == 31|| spinNumber == 33|| spinNumber == 35)
	            	{
	            		number_display_tv_.setTextColor(Color.parseColor("#000000"));
	            		number_display_tv_.setText("" + spinNumber);
	            	}
	            	else if (spinNumber == 1|| spinNumber == 3 || spinNumber == 5|| spinNumber == 7|| spinNumber == 9|| 
	            			spinNumber == 12|| spinNumber == 14|| spinNumber == 16|| spinNumber == 18|| 
	            			spinNumber == 19|| spinNumber == 21|| spinNumber == 23|| spinNumber == 25|| 
	            			spinNumber == 27 || spinNumber == 30|| spinNumber == 32|| spinNumber == 34|| spinNumber == 36)
	            	{
	            		number_display_tv_.setTextColor(Color.parseColor("#FF0000"));
	            		number_display_tv_.setText("" + spinNumber);
	            	}
	            	else if (spinNumber == 0)
	            	{
	            		number_display_tv_.setTextColor(Color.parseColor("#088A08"));
	            		number_display_tv_.setText("" + spinNumber);
	            	}
	            }
	            try
	            {
		            for (int i = 0; i < arrayNums.length; i++)
		            {
		            	try
		                {
		            		int numberBet = Integer.parseInt(single_numbers_bet_.getText().toString());
		                	if (numberBet != 0 && howMany > 0)
		                	{
		                		if (arrayNums[i].equals("00"))
		                		{
		                			winnings += roulette.payout(0, -1, spinNumber, numberBet);
		                		}
		                		else
		                		{
				                    int valueAt = Integer.parseInt(arrayNums[i]);
				                    if (valueAt > -1 && valueAt <= 36)
				                    {
				                        winnings += roulette.payout(0, valueAt, spinNumber, numberBet);
				                    }
				                    else
				                    {
				                    	bank_account += Integer.parseInt(single_numbers_bet_.getText().toString());
				                        toast("Didn't count your " + valueAt + " because that entry doesn't even make sense.");
				                    }
		                		}
		                	}
		                }
		            	catch(Exception e)
		                {
		            		bank_account += Integer.parseInt(single_numbers_bet_.getText().toString());
		                    toast("Not counting your fake number bets.");
		                }
			           }
	            }
	            catch (Exception e)
	            {
	            	
	            }
	            try
	            {
		            winnings += roulette.payout(1, 0, spinNumber, Integer.parseInt(first12_et_.getText().toString()));
		            winnings += roulette.payout(2, 0, spinNumber, Integer.parseInt(second12_et_.getText().toString()));
		            winnings += roulette.payout(3, 0, spinNumber, Integer.parseInt(third12_et_.getText().toString()));
		            winnings += roulette.payout(4, 0, spinNumber, Integer.parseInt(row1_et_.getText().toString()));
		            winnings += roulette.payout(5, 0, spinNumber, Integer.parseInt(row2_et_.getText().toString()));
		            winnings += roulette.payout(6, 0, spinNumber, Integer.parseInt(row3_et_.getText().toString()));
		            winnings += roulette.payout(7, 0, spinNumber, Integer.parseInt(one_to_eighteen_et_.getText().toString()));
		            winnings += roulette.payout(8, 0, spinNumber, Integer.parseInt(nineteen_to_thirtysix_et_.getText().toString()));
		            winnings += roulette.payout(9, 0, spinNumber, Integer.parseInt(even_et_.getText().toString()));
		            winnings += roulette.payout(10, 0, spinNumber, Integer.parseInt(odd_et_.getText().toString()));
		            winnings += roulette.payout(11, 0, spinNumber, Integer.parseInt(black_et_.getText().toString()));
		            winnings += roulette.payout(12, 0, spinNumber, Integer.parseInt(red_et_.getText().toString()));
	            }
	            catch(Exception e)
	            {
	            	toast("Caught exception in winnings.");
	            }
	            bank_account += winnings;
	            write_bank_account();
	            main_bank_account();
	        }
	    }
		public void write_bank_account()
		{
			bank_account_tv_.setText("$" + Integer.toString(bank_account));
			HomeScreenActivity.bank_account = bank_account;
			HomeScreenActivity.global_bank_account_tv_.setText("$" + Integer.toString(bank_account));

		}
		
		public void main_bank_account()
	 	//updates main bank account
		{
		}
		
		public void instructions()
		{
			AlertDialog alertDialog;
			alertDialog = new AlertDialog.Builder(this).create();
			alertDialog.setTitle("Roulette Instructions");
			alertDialog.setMessage("Enter your desired bet in the boxes around the board. The boxes correspond to the set of numbers on the board that they are closest to. " +
					"For example, the box above '1st 12' is a bet for the first 12 numbers." + 
					System.getProperty("line.separator")+ "To bet on individual numbers (including 0 and 00) enter them in the large box at the " +
					"bottom. Separate numbers by comas, with no spaces." + System.getProperty("line.separator")+ "Press spin to start the game and have fun!");
			alertDialog.show();
		}
		
		public void reset_bets()
		{
			first12_et_.setText("0");
			second12_et_.setText("0");
			third12_et_.setText("0");	
			row1_et_.setText("0");
			row2_et_.setText("0");
			row3_et_.setText("0");
			one_to_eighteen_et_.setText("0");
			even_et_.setText("0");
			black_et_.setText("0");
			red_et_.setText("0");
			odd_et_.setText("0");
			nineteen_to_thirtysix_et_.setText("0");
			single_bet_numbers_et_.setText(null);
			single_numbers_bet_.setText("0");
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.roulette, menu);
		return true;
	}
	
 	public void toast(String text) 	
	//utility function for showing a toast (android text dialog)
	{
		Context context = getApplicationContext();
		Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
		toast.show();
	}
 	
 	
}