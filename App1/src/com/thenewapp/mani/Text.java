package com.thenewapp.mani;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Text extends Activity implements View.OnClickListener{
	Button chkCmd;
	ToggleButton passTag;
	EditText input;
	TextView display;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
	    chkCmd=(Button)findViewById(R.id.button1);
		passTag=(ToggleButton)findViewById(R.id.toggleButton1);
		input=(EditText)findViewById(R.id.etCommands);
		display=(TextView)findViewById(R.id.tvResult);
		passTag.setOnClickListener(this);
		chkCmd.setOnClickListener(this);
		}
	
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.toggleButton1:
			if(passTag.isChecked())
			{
				input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
			}
			else
			{
				input.setInputType(InputType.TYPE_CLASS_TEXT);
			}

			break;
		case R.id.button1:
			
		String check = input.getText().toString();
		if(check.equals("left"))
		{
			display.setGravity(Gravity.LEFT);
			display.setText("On here");
		}else if(check.equals("center"))
		{
			display.setGravity(Gravity.CENTER);
			display.setText("On here");
		}else if(check.equals("right"))
		{
			display.setGravity(Gravity.RIGHT);
			display.setText("On here");
		}else if(check.equals("blue"))
		{display.setTextColor(Color.BLUE);
		display.setText("On here");
		 }
		else if(check.equals("Where are you"))
		{
			Random tri= new Random();
			display.setText("On here");
			display.setTextSize(tri.nextInt(75));
			display.setGravity(tri.nextInt());
			display.setTextColor(Color.rgb(tri.nextInt(265), tri.nextInt(265), tri.nextInt(265)));
			
		}
		// TODO Auto-generated method stub
		break;
		}
	}

}
