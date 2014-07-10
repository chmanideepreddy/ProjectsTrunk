package com.example.daytracker;



import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

public class Start extends Activity implements View.OnClickListener{
	MediaPlayer oursong;
	@Override
	protected void onCreate(Bundle Android_Design_app) {
		// TODO Auto-generated method stub
		super.onCreate(Android_Design_app);
		setContentView(R.layout.opener);
	 //   oursong=MediaPlayer.create(Start.this, R.raw.vista_startup_tone);
	//	oursong.start();
		Thread timer = new Thread(){

			public void run(){
				try{
					sleep(5000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openStartingPoint = new Intent("com.example.daytracker.MainActivity");
                    startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}
/*	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		oursong.release();
		finish();
	}*/
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
