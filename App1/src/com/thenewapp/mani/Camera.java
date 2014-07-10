package com.thenewapp.mani;

import android.app.Activity;

import java.io.IOException;
import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Camera extends Activity implements View.OnClickListener{
	ImageView iv;
	ImageButton ib;
	Button b;
	Intent i;
	Bitmap bmp;
	 static int cameradata;
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		initialize();
	}
	
	public void initialize(){
		 iv = (ImageView)findViewById(R.id.imageView1);
		 ib= (ImageButton)findViewById(R.id.imageButton1);
		 b= (Button)findViewById(R.id.button1);
		b.setOnClickListener(this);
		ib.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		
		switch(v.getId())
		{
		case R.id.button1:
			try {
				getApplicationContext().setWallpaper(bmp);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			break;
		case R.id.imageButton1:
			i= new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			
			startActivityForResult(i,cameradata);
			
			break;
		
		}
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
	if(resultCode ==RESULT_OK)
	{
		Bundle extras=data.getExtras();
		bmp=(Bitmap) extras.get("data");
		iv.setImageBitmap(bmp);
		
	}
	}

}
