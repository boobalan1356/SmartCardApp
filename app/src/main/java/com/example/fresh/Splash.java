package com.example.fresh;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;

public class Splash extends Activity {

	MediaPlayer splashSong;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);
		
		splashSong = MediaPlayer.create(Splash.this, R.raw.xylo);
		splashSong.start();
		splashSong.setLooping(true);
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(1000);
				}catch(InterruptedException ie){
					ie.getStackTrace();
				}finally{
					Intent openMainActivity = new Intent("com.example.fresh.SMARTCARD");
					startActivity(openMainActivity);
				}
			}
		};
		
		timer.start();
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		splashSong.release();
		finish();
	}

}
