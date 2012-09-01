package com.fr4gus.android.oammblo.ui;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.fr4gus.android.oammblo.R;

/**
 * Shows App logo for few seconds.
 * 
 * @author Franklin Garcia Created Mar 25, 2012
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
		super.onResume();
		mHandler.postDelayed(new Runnable() {

			@Override
			public void run() {
				startActivity( new Intent(getApplicationContext(), LoginActivity.class));
				finish();
			}
		}, 3000);
	}

}
