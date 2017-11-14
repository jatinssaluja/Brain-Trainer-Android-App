package com.example.braintrainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;



import android.app.Activity;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener {

  TextView mycounter;
  TextView myeq;
  TextView myscore;
  TextView myanswer;
  
  Button b1;
  Button b2;
  Button b3;
  Button b4;
  Button play;
  
  int flag = 0;
  int x3; //stores the correct sum
  
  int totalquestions = 0;
  int totalcorrectanswers = 0;
	
	List<Button> buttons;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mycounter = (TextView)findViewById(R.id.countertext);
		myeq = (TextView)findViewById(R.id.equationtext);
		myscore = (TextView)findViewById(R.id.scoretext);
		myanswer = (TextView)findViewById(R.id.rightwrongtext);
		
		b1 = (Button)findViewById(R.id.button1);
		b2 = (Button)findViewById(R.id.button2);
		b3 = (Button)findViewById(R.id.button3);
		b4 = (Button)findViewById(R.id.button4);
		play = (Button)findViewById(R.id.playagainbutton);
		
		play.setVisibility(View.INVISIBLE);
		myscore.setText("0/0");
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		play.setOnClickListener(this);
		
		
	   buttons = new ArrayList<Button>();
	    buttons.add(b1);
	    buttons.add(b2);
	    buttons.add(b3);
	    buttons.add(b4);

		
		showQuiz();
		
		
		
		
	}

	private void showQuiz() {
		
		if(flag==0){
			
			totalquestions = 0;
			totalcorrectanswers = 0;
			play.setVisibility(View.INVISIBLE);
			myscore.setText("0/0");
			
			b1.setEnabled(true);
			b2.setEnabled(true);
			b3.setEnabled(true);
			b4.setEnabled(true);
			
		}
		
		Random ran = new Random();
		int x1 = ran.nextInt(51);
		int x2 = ran.nextInt(51);
		
		myeq.setText(x1+" "+"+"+" "+x2);
		
		x3 = x1 + x2; // stores the correct sum of x1 and x2
		
		List<Integer> objects = new ArrayList<Integer>();
        objects.add(x3);
        objects.add(ran.nextInt(51));
        objects.add(ran.nextInt(51));
        objects.add(ran.nextInt(51));

    // Shuffle the collection
   Collections.shuffle(objects);
   
   
   
   
   for (int i = 0; i < objects.size(); i++) {
       buttons.get(i).setText(objects.get(i).toString());
   }
   
   if(flag==0){
   
	   flag = 1;
   new CountDownTimer(30*1000,1000){
		
		
		public void onTick(long millisecondsuntildone){
			
			int secs = (int)(millisecondsuntildone/1000);
			
			mycounter.setText(String.valueOf(secs));
			
			
		}
		
		public void onFinish(){
			
			mycounter.setText("0");
			myanswer.setText("Your score:"+" "+totalcorrectanswers
					+"/"+totalquestions);
			
			play.setVisibility(View.VISIBLE);
			b1.setEnabled(false);
			b2.setEnabled(false);
			b3.setEnabled(false);
			b4.setEnabled(false);
			flag=0;

		}
		
	}.start();
   }

		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		
		Button btt = (Button)v;
		
		//myanswer.setText("working");
		
		if(!(btt.getText().toString().equals("Play Again")))
		{
		
		int res = Integer.parseInt(btt.getText().toString());
		
		if(res==x3){
			
			totalquestions++;
			totalcorrectanswers++;
			
	myscore.setText(totalcorrectanswers+"/"+totalquestions);
	myanswer.setText("Correct");
	
	showQuiz();
	
		
		}
		
		else{
			
			totalquestions++;
			
	myscore.setText(totalcorrectanswers+"/"+totalquestions);
	myanswer.setText("Incorrect");
	
	showQuiz();
			
			
		}
		
		}
		
		else{
			
			showQuiz();
		}
		
	}
}
