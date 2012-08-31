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
import com.fr4gus.android.oammblo.services.TwitterService;
import com.fr4gus.android.oammblo.services.TwitterServiceFactory;

public class LoginActivity extends Activity {

	// Button mLoginButton;
	EditText mUsername;
	EditText mPassword;
	TwitterService mService = TwitterServiceFactory.getService();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mUsername = (EditText) findViewById(R.id.editText1);
		mPassword = (EditText) findViewById(R.id.editText2);
		// mLoginButton.setOnClickListener(new OnClickListener(){

		// public void onClick(View v) {
		// TODO Auto-generated method stub
		// Intent intent = new
		// Intent(LoginActivity.this,TimeLineActivity.class);
		// startActivity(intent);
		// }
		// });
		// mService.addListener(new MyTwitterListener());
	}

	// public void doAuthenticate(View view){
	// new Thread(){
	// public void run(){
	// mService.authenticate(mUsername.getText().toString(),
	// mPassword.getText().toString());
	// }
	// }.start();
	// }

	public void doAuthenticate(View view){
    	AsyncTask<String,Void,Boolean> task = new AsyncTask<String,Void,Boolean>(){
    		ProgressDialog dialog;

			@Override
			protected Boolean doInBackground(String... params) {
				// TODO Auto-generated method stub
				boolean respuesta;
				
				if(params.length < 2){
					respuesta = false;
				}
				else{
					String username = params[0];
					String password = params[1];
					
					respuesta = mService.authenticate(username, password);
				}
				return respuesta;
			}
			
			@Override
			protected void onPostExecute(Boolean result) {
				// TODO Auto-generated method stub
				super.onPostExecute(result);
				dialog.dismiss();
				if(result){
					startActivity(new Intent(LoginActivity.this,TimeLineActivity.class));
				} else{
					Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
				}
			}
			
			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
				dialog = new ProgressDialog(LoginActivity.this);
				dialog.setMessage("Autenticando en este momento");
				dialog.show();
			}
    		
    	};
    	
    	task.execute(mUsername.getText().toString(),mPassword.getText().toString());
    }

//	private class MyTwitterListener implements TwitterListener {
//
//		@Override
//		public void onAuthentication(boolean success) {
//			// TODO Auto-generated method stub
//			System.out.println("lol");
//			if (success) {
//				startActivity(new Intent(LoginActivity.this,
//						TimeLineActivity.class));
//			} else {
//				Toast.makeText(getApplicationContext(), "Error",
//						Toast.LENGTH_LONG).show();
//			}
//		}
//	}
//
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		getMenuInflater().inflate(R.menu.activity_login, menu);
//		return true;
//	}
}
