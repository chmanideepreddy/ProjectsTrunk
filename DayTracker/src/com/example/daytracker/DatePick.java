package com.example.daytracker;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import android.app.Activity;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.DatePicker;
import android.widget.DatePicker.OnDateChangedListener;
import android.widget.Toast;

public class DatePick extends Activity implements View.OnClickListener//, OnDateChangedListener,OnDateSetListener
{
	DBTools dbtools = new DBTools(this);
	DatePicker dateChoosen;


	Button button;

	protected void onCreate(Bundle savedInstanceState) {
		try {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.date_pick);
			Initialize();

			button.setOnClickListener(this);
			/*dateChoosen.setOnDateChangeListener(new OnDateChangeListener(){

						public void onSelectedDayChange(CalendarView view, int year, int month, int day){

			   // add one because month starts at 0

			   month = month + 1;
			   // output to log cat **not sure how to format year to two places here**
			   String newDate = year+"-"+month+"-"+day;
			   Log.d("NEW_DATE", newDate);
			}
			});*/


		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	private void Initialize() {
		button=(Button)findViewById(R.id.button1);

		dateChoosen=(DatePicker)findViewById(R.id.datePicker);
	}

	@Override
	public void onClick(View v) {



		int month=dateChoosen.getMonth();
		int day=dateChoosen.getDayOfMonth();
		int year=dateChoosen.getYear();
		String newDate = year+"-"+month+"-"+day;
		System.out.println(newDate);
		ArrayList<HashMap<String, String>> Activities=	dbtools.getActivityInfo(newDate);
		Intent intent = new Intent(this, Previous.class);
		intent.putExtra("map", Activities);
		startActivity(intent);



	}

	/*@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onDateChanged(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub

	}
	 */


}
