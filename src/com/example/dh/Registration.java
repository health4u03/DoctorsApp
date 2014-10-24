package com.example.dh;

import com.example.asyctask.RegistrationTask;
import com.example.datamodels.RegistrationModel;
import com.example.dh.util.NetStatus;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class Registration extends Activity implements OnClickListener {

	EditText usernameR, emailR, phoneR, addressR, passwordR;
	Button registerR;
	SharedPreferences sp;
	CheckBox accpetTerms;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_register);
		init();

		registerR.setOnClickListener(this);

	}

	private void init() {
		sp = PreferenceManager.getDefaultSharedPreferences(Registration.this);
		usernameR = (EditText)findViewById(R.id.editTextUserNameRegister);
		emailR = (EditText)findViewById(R.id.editTextEmailRegister);
		phoneR = (EditText)findViewById(R.id.editTextPhoneRegister);
		addressR = (EditText)findViewById(R.id.editTextAddressRegister);
		passwordR = (EditText)findViewById(R.id.editTextPassword);
		registerR = (Button)findViewById(R.id.buttonRegisterRegistration);
	
		accpetTerms=(CheckBox)findViewById(R.id.checkBoxTerm);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){

		case R.id.buttonRegisterRegistration:

			
			if(usernameR.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(Registration.this,"Warning", "Please Enter UserName");
			}else if(emailR.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(Registration.this,"Warning", "Please Enter Email");
			}else if(phoneR.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(Registration.this,"Warning", "Please Enter Phone number");
			}else if(addressR.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(Registration.this,"Warning", "Please Enter Address");
			}else if(passwordR.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(Registration.this,"Warning", "Please Enter Password");
			}else if(!accpetTerms.isChecked()){
				ErrorDialog.ErrorDialogCreation(Registration.this,"Warning", "Please Accept Terms and Conditions");
			}
			
			else{
				
				if (!NetStatus.getInstance(getBaseContext()).isOnline(getBaseContext())) {

					Toast t = Toast.makeText(getBaseContext(),
							"Please connect to Internet.", Toast.LENGTH_SHORT);
					t.show();
					Log.v("Home", "You are not online!!!!");
					return;
				}
			
				

				// ------------------------------------------run a task to update details on db
				RegistrationModel rModel = new RegistrationModel();
				rModel.setName(usernameR.getText().toString());
				rModel.setPhone(phoneR.getText().toString());
				rModel.setAddress(addressR.getText().toString());
				rModel.setPassword(passwordR.getText().toString());
				rModel.setEmail(emailR.getText().toString());

				new RegistrationTask(Registration.this).execute(rModel);
				
			}
			
			//---Manipulate this intent when whole registration gets completed----------------
			
	
			//overridePendingTransition(R.anim.side_down, R.anim.slide_up);

			break;
		}
	}
}
