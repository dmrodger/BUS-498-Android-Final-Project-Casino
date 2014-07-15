package com.example.thecasino;

import com.example.thecasino.R;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.MotionEvent;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;
import android.view.ViewGroup.LayoutParams;

public class SlotsActivity extends Activity {
	
    Button button_spin_;
    Button button_rules_;

    TextView bank_account_;
    VideoView VideoView1;
    VideoView VideoView2;
    VideoView VideoView3;
    
    String wheel_one;
    String wheel_two;
    String wheel_three;
    
    public static int bank_account; 
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_slots);
		
		button_spin_=(Button)findViewById(R.id.button_spin);
		button_rules_=(Button)findViewById(R.id.button_rules);
		// get control of videoView
		VideoView1 = (VideoView)findViewById(R.id.videoView1);
		VideoView2 = (VideoView)findViewById(R.id.videoView2);
		VideoView3 = (VideoView)findViewById(R.id.videoView3);
		bank_account_ = (TextView)findViewById(R.id.bank_account);

		button_spin_.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) 
		{     
			play_video();
		}});
		
		button_rules_.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View v) 
		{
			rules();
		}});
		
		//get screen size from device
		Point p = get_screen_size();

		//manually set the size of each VideoView so that it takes up the whole screen
		LayoutParams params=VideoView1.getLayoutParams();
		params.width=(int)(p.x-20)/3;
		params.height = (int)(p.x-20)/3;
		VideoView1.setLayoutParams(params);

		params=VideoView2.getLayoutParams();
		params.width=(int)(p.x-20)/3;
		params.height = (int)(p.x-20)/3;
		VideoView2.setLayoutParams(params);

		params=VideoView3.getLayoutParams();
		params.width=(int)(p.x-20)/3;
		params.height = (int)(p.x-20)/3;
		VideoView3.setLayoutParams(params);
		
		bank_account = HomeScreenActivity.bank_account;
		write_bank_account();
	}
	

	private void play_video()
	{
		wheel_one = "";
		wheel_two = "";
		wheel_three = "";
		
		bank_account = bank_account -2;
		write_bank_account();
		
		play_sound(R.raw.aaaaaaasoundspin);
		
		//make the video loop endlessly
		VideoView1.setOnCompletionListener(new OnCompletionListener() {
		    @Override	//on completion start it again...
		    public void onCompletion(MediaPlayer mp) {mp.start();}});

		VideoView2.setOnCompletionListener(new OnCompletionListener() {
		    @Override
		    public void onCompletion(MediaPlayer mp) {mp.start();}});

		VideoView3.setOnCompletionListener(new OnCompletionListener() {
		    @Override
		    public void onCompletion(MediaPlayer mp) {mp.start();}});
		
		//add some touch listeners so than when the videoView is touched it stops...
		VideoView1.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) 
			{
				if(wheel_one.equals(""))
				{
				  //stop the spin //do other things as needed
				  VideoView1.stopPlayback();
				  wheel_one = generate_random_outcome(VideoView1);
				  check_status();
				}
			
		return false;}});
		
		//add some touch listeners so than when the videoView is touched it stops...
		VideoView2.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) 
			{
				if(wheel_two.equals(""))
				{
				  //stop the spin //do other things as needed
				  VideoView2.stopPlayback();
				  wheel_two = generate_random_outcome(VideoView2);
				  check_status();
				}
			
		return false;}});
		
		//add some touch listeners so than when the videoView is touched it stops...
		VideoView3.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) 
			{
				if(wheel_three.equals(""))
				{
				  //stop the spin //do other things as needed
				  VideoView3.stopPlayback();
				  wheel_three = generate_random_outcome(VideoView3);
				  check_status();
				}
		return false;}});

		//point the each videoView to the correct raw file
		String uriPath = "android.resource://" + this.getPackageName() + "/" + R.raw.aaaaaaaspinning3sec;
		Uri uri = Uri.parse(uriPath);

		//play the file
		VideoView1.setVideoURI(uri);
		VideoView1.requestFocus();
		VideoView1.start();

		VideoView2.setVideoURI(uri);
		VideoView2.requestFocus();
		VideoView2.start();
		
		VideoView3.setVideoURI(uri);
		VideoView3.requestFocus();
		VideoView3.start();
		
	}
	
	private void check_status()
	{
		// scan through all of the wheels to see if they are stopped
		if(!wheel_one.equals("")  && !wheel_two.equals("") && !wheel_three.equals(""))
		{
			int duncan_count = 0;
			int plum_count = 0;
			int ace_count = 0;
			int seven_count = 0;
			
			//count the number of duncans
			if (wheel_one.equals("duncan"))
			{
				duncan_count++;
			}
			else if (wheel_one.equals("plum"))
			{
				plum_count++;
			}
			else if (wheel_one.equals("ace"))
			{
				ace_count++;
			}
			else if (wheel_one.equals("seven"))
			{
				seven_count++;
			}
			if (wheel_two.equals("duncan"))
			{
				duncan_count++;
			}
			else if (wheel_two.equals("plum"))
			{
				plum_count++;
			}
			else if (wheel_two.equals("ace"))
			{
				ace_count++;
			}
			else if (wheel_two.equals("seven"))
			{
				seven_count++;
			}
			if (wheel_three.equals("duncan"))
			{
				duncan_count++;
			}
			else if (wheel_three.equals("plum"))
			{
				plum_count++;
			}
			else if (wheel_three.equals("ace"))
			{
				ace_count++;
			}
			else if (wheel_three.equals("seven"))
			{
				seven_count++;
			}
			
			//give the payout to the bank account
			if(seven_count == 3)
			{
				bank_account = bank_account +500000;
				toast("You won $500000!");
				play_sound(R.raw.aaaaaaasoundyestalkinabout);
			}
			else if(seven_count == 2)
			{
				bank_account = bank_account +500;
				toast("You won $500!");
				play_sound(R.raw.aaaaaaasoundyestalkinabout);
			}
			else if(seven_count == 1)
			{
				bank_account = bank_account +50;
				toast("You won $50!");
				play_sound(R.raw.aaaaaaasoundyestalkinabout);
			}
			else if(duncan_count == 3)
			{
				bank_account = bank_account +12;
				toast("You won $12!");
				play_sound(R.raw.aaaaaaasoundyestalkinabout);
			}
			else if(plum_count == 3)
			{
				bank_account = bank_account +5;
				toast("You won $5!");
				play_sound(R.raw.aaaaaaasoundyestalkinabout);
			}
			else if(ace_count == 3)
			{
				bank_account = bank_account +3;
				toast("You won $3!");
				play_sound(R.raw.aaaaaaasoundyayaya);
			}
			else if(duncan_count == 2)
			{
				bank_account = bank_account +2;
				toast("You won $2!");
				play_sound(R.raw.aaaaaaasoundyeslaugh);
			}
			else if(duncan_count == 1)
			{
				bank_account = bank_account +1;
				toast("You won $1!");
				play_sound(R.raw.aaaaaaasoundyeslaugh);
			}
			else
			{
				toast("Better luck next time!");
			}
			
		}
		
		write_bank_account();
		main_bank_account();

		
	}
	
	
	public void write_bank_account()
	{
		bank_account_.setText("$" + Integer.toString(bank_account));
		HomeScreenActivity.bank_account = bank_account;
		HomeScreenActivity.global_bank_account_tv_.setText("$" + Integer.toString(bank_account));

	}
	
	public void main_bank_account()
 	//updates main bank account
	{
	}
	
	private String generate_random_outcome(VideoView v)
	{
		String outcome = "";
		String uriPath = "";
		
		int r = random_int_between(0,1000);
		
		if (r<= 300)
		{
			outcome = "duncan";
			uriPath = "android.resource://" + this.getPackageName() + "/" + R.raw.aaaaaaaduncan;
		}
		else if(r<=500)
		{
			outcome = "plum";
			uriPath = "android.resource://" + this.getPackageName() + "/" + R.raw.aaaaaaaplum;
		}
		else if(r<=997)
		{
			outcome = "ace";
			uriPath = "android.resource://" + this.getPackageName() + "/" + R.raw.aaaaaaaace;
		}
		else if(r<=1000)
		{
			outcome = "seven";
			uriPath = "android.resource://" + this.getPackageName() + "/" + R.raw.aaaaaaaseven;
		}
		
		Uri uri = Uri.parse(uriPath);

		//play the file
		v.setVideoURI(uri);
		v.requestFocus();
		v.start();
		
		return outcome;
	}
	
	public void rules()
	{
		AlertDialog alertDialog;
		alertDialog = new AlertDialog.Builder(this).create();
		alertDialog.setTitle("Slot Machine Rules");
		alertDialog.setMessage("Press spin to begin the game." + System.getProperty("line.separator")+
				"This is a $2 slot machine." + System.getProperty("line.separator")+ "Payouts:"+ System.getProperty("line.separator")+
				"1 Seven: $50"+ System.getProperty("line.separator")+ "1 Duncan: $1"+ System.getProperty("line.separator")+  "2 Duncans: $2"+ System.getProperty("line.separator")+
				"2 Sevens: $500" + System.getProperty("line.separator")+ "3 Aces: $3" + System.getProperty("line.separator")+ 
				"3 Plums: $5" + System.getProperty("line.separator")+ "3 Duncans: $12"+ System.getProperty("line.separator")+
				"3 Sevens: $500,000" + System.getProperty("line.separator")+ "If you get more than one win in a spin, only the " +
				"highest win will be paid.");
		alertDialog.show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.slots, menu);
		return true;
	}
	
	public int random_int_between(int Min, int Max)	{	
		//utility function for making random ints between two ints
			return Min + (int)(Math.random() * ((Max - Min) + 1));
		}
	 	public void toast(String text) 	
		//utility function for showing a toast (android text dialog)
		{
			Context context = getApplicationContext();
			Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
			toast.show();
		}
		
		public Point get_screen_size(){  
		//utility function to get device screen size
			//returns screen size:
			//usage:   int x = size.x;
			Display display = getWindowManager().getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			return size;
		}
		
		public void play_sound(int R_file_resource_id){
		//utility function to play a sound
			//usage:	play_sound(R.raw.sound_to_play);
			MediaPlayer mysound = null;
			mysound = MediaPlayer.create(this, R_file_resource_id);
			mysound.start();
		}
}