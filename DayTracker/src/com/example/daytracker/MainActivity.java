package com.example.daytracker;



import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


import android.support.v4.app.Fragment;
import android.text.format.Formatter;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.DigitalClock;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Build;

public class MainActivity extends Activity implements View.OnClickListener {
	Button start;
	Button end;
	Spinner action;
	Chronometer stopwatch;
	TextView display;
	TextView display2;
	Button view;	
	DigitalClock clk;
	String activity;
	Calendar cal;
	int day;
	private Handler mHandler = new Handler(); 
	private long startTime, endTime; 
	private long elapsedTime; 
	private final int REFRESH_RATE = 100; 
	private String hours,minutes,seconds,milliseconds,hours_temp,minutes_temp,seconds_temp,milliseconds_temp;
	private long secs,mins,hrs; 
	DBTools dbTools = new DBTools(this);


	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.fragment_main);
			intialize();
			start.setOnClickListener(this);
			end.setOnClickListener(this);
			view.setOnClickListener(this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}


	private void intialize() {
		start=(Button)findViewById(R.id.Start);
		end=(Button)findViewById(R.id.End);
		action =(Spinner)findViewById(R.id.spinner1);
		display=(TextView)findViewById(R.id.textView1);
		display2=(TextView)findViewById(R.id.textView2);
		clk=(DigitalClock)findViewById(R.id.digitalClock1);
		view=(Button)findViewById(R.id.View);
		stopwatch=(Chronometer)findViewById(R.id.chronometer);
	}




	@Override
	public void onClick(View v) {
		cal = Calendar.getInstance();
		//Date dt = new Date();
		int mYear; 
		int mMonth;
		int mDay; 
		int year ;
		int month ;
		int day;
		String year_string;
		String month_string;
		String day_string;
		String year_string1;
		String month_string1;
		String day_string1;
		String startDate_stringFormat = null;
		String endDate_stringFormat=null;
		String timeOfStart = null;
		int startDateInt=0;
		String timeOfEnd=null;
		int timeOfStart_int = 0;
		int timeOfEnd_int;
		switch(v.getId())
		{
		case R.id.Start:
			start.setEnabled(false);	
			end.setEnabled(true);
			mYear = cal.get(Calendar.YEAR);
			mMonth = cal.get(Calendar.MONTH);
			mDay = cal.get(Calendar.DAY_OF_MONTH);

			year_string= String.valueOf(mYear);
			if(mMonth<10){
				month_string= "0"+String.valueOf(mMonth);
			}else
			{
				month_string= String.valueOf(mMonth);
			}
			if(mDay<10){
				day_string= "0"+String.valueOf(mDay);
			}
			else{
				day_string= String.valueOf(mDay);
			}

			startDate_stringFormat=year_string+"-"+month_string+"-"+day_string;

			try {
				startDateInt= Integer.parseInt(startDate_stringFormat);
			} catch (Exception e) {
				System.out.println(e);

			}

			startTime=System.currentTimeMillis();
			Split(System.currentTimeMillis());
			timeOfStart=hours_temp+minutes_temp;
			timeOfStart_int=Integer.parseInt(timeOfStart);
			display2.setText(month_string+"/"+day_string+"/"+year_string);
			mHandler.removeCallbacks(startTimer); 
			mHandler.postDelayed(startTimer, 0);
			break;


		case R.id.End:
			end.setEnabled(false);
			start.setEnabled(true);
			year = cal.get(Calendar.YEAR);
			month = cal.get(Calendar.MONTH);
			day = cal.get(Calendar.DAY_OF_MONTH);
			year_string1= String.valueOf(year);


			if(month<10){
				month_string1= "0"+String.valueOf(month);
			}else
			{
				month_string1= String.valueOf(month);
			}
			if(day<10){
				day_string1= "0"+String.valueOf(day);
			}
			else{
				day_string1= String.valueOf(day);
			}
			endDate_stringFormat=year_string1+"-"+month_string1+"-"+day_string1;
			Split(System.currentTimeMillis());
			timeOfEnd=hours_temp+minutes_temp;
			timeOfEnd_int=Integer.parseInt(timeOfEnd);
			activity= (String.valueOf(action.getSelectedItem()));
			mHandler.removeCallbacks(startTimer);
			String elapsedTime1=hours+minutes+seconds;
			
			Act act=new Act();
			int elapsed_time_int=Integer.parseInt(elapsedTime1);
			
			act.setActivity(activity);
			act.setDate(startDate_stringFormat);
			act.setFrom(timeOfStart_int);
			act.setTo(timeOfEnd_int);
			act.setHours(elapsed_time_int);		
			dbTools.insertContact(act);
			display2.setText(hours+":"+minutes+":"+seconds);
			break;

		case R.id.View:

			Intent openStartingPoint = new Intent("com.example.daytracker.datepick");
			startActivity(openStartingPoint);

		}
	}

	private  Runnable startTimer = new Runnable() { 
		public void run() { elapsedTime = System.currentTimeMillis() - startTime; 
		updateTimer(elapsedTime); 
		mHandler.postDelayed(this,REFRESH_RATE);
		} };




		private void updateTimer (float time){ 

			secs = (long)(time/1000); 
			mins = (long)((time/1000)/60); 
			hrs = (long)(((time/1000)/60)/60); /* Convert the seconds to String * and format to ensure it has * a leading zero when required */ 
			secs = secs % 60;
			seconds=String.valueOf(secs); 
			if(secs == 0){ seconds = "00"; } 
			if(secs <10 && secs > 0)
			{ 
				seconds = "0"+seconds; 
			} /* Convert the minutes to String and format the String */
			mins = mins % 60;
			minutes=String.valueOf(mins); 
			if(mins == 0){ 
				minutes = "00";
			} 
			if(mins <10 && mins > 0){
				minutes = "0"+minutes;
			} /* Convert the hours to String and format the String */ 
			hours=String.valueOf(hrs);
			if(hrs == 0){ 
				hours = "00";
			} 
			if(hrs <10 && hrs > 0){
				hours = "0"+hours;
			} /* Although we are not using milliseconds on the timer in this example * I included the code in the event that you wanted to include it on your own */
			milliseconds = String.valueOf((long)time);
			if(milliseconds.length()==2){ 
				milliseconds = "0"+milliseconds; 
			} 
			if(milliseconds.length()<=1){
				milliseconds = "00"; 
			}

			display.setText(hours + ":" + minutes + ":" + seconds); 


		}
		private void Split (float time){ 
			secs = (long)(time/1000); 
			mins = (long)((time/1000)/60); 
			hrs = (long)(((time/1000)/60)/60); /* Convert the seconds to String * and format to ensure it has * a leading zero when required */ 
			secs = secs % 60;
			seconds_temp=String.valueOf(secs); 
			if(secs == 0){ seconds_temp = "00"; } 
			if(secs <10 && secs > 0)
			{ 
				seconds_temp = "0"+seconds_temp; 
			} /* Convert the minutes to String and format the String */
			mins = mins % 60;
			minutes_temp=String.valueOf(mins); 
			if(mins == 0){ 
				minutes_temp = "00";
			} 
			if(mins <10 && mins > 0){
				minutes_temp = "0"+minutes_temp;
			} /* Convert the hours to String and format the String */ 
			hours_temp=String.valueOf(hrs);
			if(hrs == 0){ 
				hours_temp = "00";
			} 
			if(hrs <10 && hrs > 0){
				hours_temp = "0"+hours_temp;
			} /* Although we are not using milliseconds on the timer in this example * I included the code in the event that you wanted to include it on your own */
			milliseconds_temp = String.valueOf((long)time);
			if(milliseconds_temp.length()==2){ 
				milliseconds_temp = "0"+milliseconds_temp; 
			} 
			if(milliseconds_temp.length()<=1){
				milliseconds_temp = "00"; 
			}
			

		}
}
