package com.example.thecasino;

import com.example.thecasino.BlackJackAckActivity;
import com.example.thecasino.RouletteActivity;
import com.example.thecasino.R;
import com.example.thecasino.SlotsActivity;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class HomeScreenActivity extends Activity {

	

	Button slots_button_;
	Button blackjack_button_;
	Button roulette_button_;
	
	public static TextView global_bank_account_tv_;
		
	public static int bank_account;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_screen);
		go_to_slots();
		go_to_roulette();
		go_to_blackjack();


		global_bank_account_tv_=(TextView)findViewById(R.id.global_bank_account_tv);
		
		bank_account = 500;

		write_bank_account();

	}
		
		public void go_to_slots() 
		{
			final Context context = this;
			slots_button_ = (Button) findViewById(R.id.slots_button);
			slots_button_.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View arg0) 
				{
				    Intent intent = new Intent(context, SlotsActivity.class);
				    startActivity(intent);
				    
				    //update bank account
				    write_bank_account();
				}
			});			
		}
		
		public void go_to_roulette()
		{
			final Context context2 = this;
			roulette_button_ = (Button) findViewById(R.id.roulette_button);
			roulette_button_.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View arg0) 
				{
				    Intent intent = new Intent(context2, RouletteActivity.class);
				    startActivity(intent);
				    
				  //update bank account
				    write_bank_account();
				}
			});
			
		
		}
		
		public void go_to_blackjack()
		{
			final Context context3 = this;
			blackjack_button_ = (Button) findViewById(R.id.blackjack_button);
			blackjack_button_.setOnClickListener(new OnClickListener() 
			{
				@Override
				public void onClick(View arg0) 
				{
				    Intent intent = new Intent(context3, BlackJackAckActivity.class);
				    startActivity(intent);
					
				  //update bank account
				    write_bank_account();
				}

			});
		    bank_account = BlackJackAckActivity.bank_account;

		}

		public void toast(String text) 	
		//utility function for showing a toast (android text dialog)
		{
			Context context = getApplicationContext();
			Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
			toast.show();
		}
		
		public void write_bank_account()
		{
			global_bank_account_tv_.setText("$" + Integer.toString(bank_account));
		}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_screen, menu);
		return true;
	}
	


}
