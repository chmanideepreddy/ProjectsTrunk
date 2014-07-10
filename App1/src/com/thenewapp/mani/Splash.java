package com.thenewapp.mani;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity{
MediaPlayer oursong;
	@Override
	protected void onCreate(Bundle Android_Design_app) {
		// TODO Auto-generated method stub
		super.onCreate(Android_Design_app);
		setContentView(R.layout.splash);
	    oursong=MediaPlayer.create(Splash.this, R.raw.vista_startup_tone);
		oursong.start();
		Thread timer = new Thread(){

			public void run(){
				try{
					sleep(5000);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					Intent openStartingPoint = new Intent("com.thenewapp.mani.MENU");
                    startActivity(openStartingPoint);
				}
			}
		};
		timer.start();
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		oursong.release();
		finish();
	}
}

