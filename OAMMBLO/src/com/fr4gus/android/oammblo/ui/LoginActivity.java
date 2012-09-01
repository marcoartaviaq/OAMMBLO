package com.fr4gus.android.oammblo.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.fr4gus.android.oammblo.R;
import com.fr4gus.android.oammblo.service.TwitterService;
import com.fr4gus.android.oammblo.service.TwitterServiceException;
import com.fr4gus.android.oammblo.service.TwitterServiceFactory;

public class LoginActivity extends Activity {
	// Button mLoginButton;
	EditText mUsername;
	EditText mPassword;
	TwitterService mService = TwitterServiceFactory.getService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		// mLoginButton = (Button) findViewById(R.id.login_button);
		mUsername = (EditText) findViewById(R.id.login_username);
		mPassword = (EditText) findViewById(R.id.login_password);
		
		//
		// mLoginButton.setOnClickListener(new View.OnClickListener() {
		//
		// public void onClick(View v) {
		// }
		// });

	}

	public void doAuthenticate(View view) {
		AsyncTask<String, Void, Boolean> task = new AsyncTask<String, Void, Boolean>() {
			ProgressDialog dialog;

			@Override
			protected Boolean doInBackground(String... params) {
				if (params.length < 2) {
					return false;
				}
				String username = params[0];
				String password = params[1];

				boolean result = false;
				try {
					result = mService.authenticate(username, password);
				} catch (TwitterServiceException e) {
				}
				return result;
			}

			@Override
			protected void onPostExecute(Boolean result) {
				super.onPostExecute(result);
				dialog.dismiss();
				if (result) {
					startActivity(new Intent(LoginActivity.this,
							PublicTimelineActivity.class));
				} else {
					Toast.makeText(getApplicationContext(), "Error",
							Toast.LENGTH_LONG).show();
				}

			}

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				dialog = new ProgressDialog(LoginActivity.this);
				dialog.setMessage("Autenticando\u2026");
				dialog.show();
			}

		};
		task.execute(mUsername.getText().toString(), mPassword.getText()
				.toString());
	}
	// private class MyTwitterListener implements TwitterListener {
	//
	// public void onAunthentication(boolean success) {
	// if (success) {
	// startActivity(new Intent(LoginActivity.this,
	// TimelineActivity.class));
	// } else {
	// Toast.makeText(getApplicationContext(), "Error",
	// Toast.LENGTH_LONG).show();
	// }
	// }
	//
	// }
	//
	// @Override
	// public boolean onCreateOptionsMenu(Menu menu) {
	// getMenuInflater().inflate(R.menu.activity_login, menu);
	// return true;
	// }
}
