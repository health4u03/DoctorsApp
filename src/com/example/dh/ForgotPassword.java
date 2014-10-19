package com.example.dh;

import com.example.asyctask.ForgotPasswordTask;
import com.example.asyctask.LoginTask;
import com.example.datamodels.ForgotPasswordModel;
import com.example.datamodels.LoginModel;
import com.example.dh.util.NetStatus;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ForgotPassword extends Activity implements OnClickListener{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	TextView forgotPassword;
	EditText editTextEmail;
	Button buttnSend;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_forgot_password);
		
		init();
		
		buttnSend.setOnClickListener(this);
		
	}


	private void init() {
		// TODO Auto-generated method stub
		
	    Typeface sofiaPro = Typeface.createFromAsset(getAssets(), "SofiaProLight.otf");

		forgotPassword = (TextView)findViewById(R.id.textViewPassword);
		editTextEmail = (EditText)findViewById(R.id.editTextUserNamePassword);
		buttnSend = (Button)findViewById(R.id.buttonForgotpassword);
		
		forgotPassword.setTypeface(sofiaPro);
		buttnSend.setTypeface(sofiaPro);
		editTextEmail.setTypeface(sofiaPro);
		
	}


	@Override
	public void onClick(View v) {

		switch(v.getId()){
		
		case R.id.buttonForgotpassword:
			
			if(editTextEmail.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(ForgotPassword.this,"Warning", "Please Enter Email");
			}else{
				
				if (!NetStatus.getInstance(getBaseContext()).isOnline(getBaseContext())) {

					Toast t = Toast.makeText(getBaseContext(),
							"Please connect to Internet.", Toast.LENGTH_SHORT);
					t.show();
					Log.v("Home", "You are not online!!!!");
					return;
				}
				ForgotPasswordModel objForgotPasswordModel = new ForgotPasswordModel();
				objForgotPasswordModel.setEmail(editTextEmail.getText().toString());
				new ForgotPasswordTask(ForgotPassword.this).execute(objForgotPasswordModel);
			}
			
			break;
		}
	}

}
