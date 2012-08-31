package com.fr4gus.android.oammblo.ui;

import com.fr4gus.android.oammblo.R;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

/**
 * Shows App logo for few seconds.
 * 
 * @author Franklin Garcia
 * Created Mar 25, 2012
 */
public class SplashActivity extends OammbloActivity {
	
	Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	mHandler.postDelayed(new Runnable(){
    		public void run(){
    			Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
    			startActivity(intent);
    			finish();
    		}
    		
    	}, 2000);
    }

}
