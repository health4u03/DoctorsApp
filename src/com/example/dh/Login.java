package com.example.dh;

import com.example.asyctask.LoginTask;
import com.example.datamodels.LoginModel;
import com.example.dh.util.NetStatus;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Login extends Activity implements OnClickListener {

	SharedPreferences sp ;
	EditText username, password ;
	Button login, registration;
	TextView textViewForgot,textViewLogin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_activity_login); 

		Typeface sofiaPro = Typeface.createFromAsset(getAssets(), "SofiaProLight.otf");

		username = (EditText)findViewById(R.id.editTextUserNameLogIn);
		login = (Button)findViewById(R.id.buttonLogin);
		registration = (Button)findViewById(R.id.buttonRegister);
		password = (EditText)findViewById(R.id.editTextPasswordLogin);
		textViewForgot = (TextView)findViewById(R.id.textViewForgot);
		textViewLogin = (TextView)findViewById(R.id.textViewLogin);

		sp = PreferenceManager.getDefaultSharedPreferences(Login.this);

		username.setTypeface(sofiaPro);
		password.setTypeface(sofiaPro);
		login.setTypeface(sofiaPro);
		registration.setTypeface(sofiaPro);
		textViewForgot.setTypeface(sofiaPro);
		login.setOnClickListener(this);
		registration.setOnClickListener(this);
		textViewLogin.setTypeface(sofiaPro);
		textViewForgot.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.buttonLogin:

			if(username.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(Login.this,"Warning", "Please Enter UserName");
			}else if(password.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(Login.this,"Warning", "Please Enter Password");
			}else{

				if (!NetStatus.getInstance(getBaseContext()).isOnline(getBaseContext())) {

					Toast t = Toast.makeText(getBaseContext(),
							"Please connect to Internet.", Toast.LENGTH_SHORT);
					t.show();
					Log.v("Home", "You are not online!!!!");
					return;
				}

				LoginModel objLoginModel = new LoginModel();
				objLoginModel.setUserName(username.getText().toString());
				objLoginModel.setPassword(password.getText().toString());
				new LoginTask(Login.this).execute(objLoginModel);
			}

			break;

		case R.id.buttonRegister :
			Intent i = new Intent(Login.this, Registration.class);
			startActivity(i);
			//overridePendingTransition(R.anim.side_down, R.anim.slide_up);
			break;

		case R.id.textViewForgot:

			Intent forgotPassword= new Intent(Login.this,ForgotPassword.class);
			startActivity(forgotPassword);

			break;

		default:
			break;
		}
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { 
			// do something on back.
			Intent startMain = new Intent(Intent.ACTION_MAIN); 
			startMain.addCategory(Intent.CATEGORY_HOME); 
			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(startMain); 
			return true; 
		}
		return super.onKeyDown(keyCode, event);
	}

}
