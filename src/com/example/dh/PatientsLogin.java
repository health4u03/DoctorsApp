package com.example.dh;

import javax.crypto.spec.OAEPParameterSpec;

import com.example.asyctask.PatientsProfileTask;
import com.example.datamodels.PatientsParameterModel;
import com.example.dh.util.NetStatus;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class PatientsLogin extends Fragment {

	EditText patientsId;
	Button find, newUser;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.new_patients_login, null);

		Typeface sofiaPro = Typeface.createFromAsset(getActivity().getAssets(), "SofiaProLight.otf");


		patientsId = (EditText)v.findViewById(R.id.editTextPatientsUserName);
		find = (Button)v.findViewById(R.id.buttonFindPatient);

		patientsId.setTypeface(sofiaPro);
		find.setTypeface(sofiaPro);

		find.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if(patientsId.getText().toString().equalsIgnoreCase("")){
					ErrorDialog.ErrorDialogCreation(getActivity(),"Warning", "Please Enter Patient ID");
				}else{

					if (!NetStatus.getInstance(getActivity().getBaseContext()).isOnline(getActivity().getBaseContext())) {

						Toast t = Toast.makeText(getActivity().getBaseContext(),
								"Please connect to Internet.", Toast.LENGTH_SHORT);
						t.show();
						Log.v("Home", "You are not online!!!!");
						return;
					}
					//------------Send as per db structure
					String userId=patientsId.getText().toString();

					PatientsParameterModel objPatientsParameterModel = new PatientsParameterModel();
					objPatientsParameterModel.setPatientsId(userId);

					new PatientsProfileTask(getActivity()).execute(objPatientsParameterModel);

			
					//Intent i = new Intent(getView().getContext(), PatientsProfile.class);
					//startActivity(i);

				}
				
			

			}
		});

		return v;
	}


	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}


}
