package com.example.dh;

import com.example.asyctask.DoctorsProfileTask;
import com.example.datamodels.DoctorsModel;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class DoctorsProfile extends Fragment {

	ImageView photo ;
	TextView userName, address, specialization, experience, contact;
	Button showOnamp;
	SharedPreferences sp;
	
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.new_doctor_profile, null);
		 Typeface sofiaPro = Typeface.createFromAsset(getActivity().getAssets(), "SofiaProLight.otf");

		photo = (ImageView)v.findViewById(R.id.imageViewUserPhoto);
		userName = (TextView)v.findViewById(R.id.textViewUserName);
		address = (TextView)v.findViewById(R.id.textViewAddressUser);
		specialization = (TextView)v.findViewById(R.id.textViewSpecialization);
		experience = (TextView)v.findViewById(R.id.textViewExperience);
		contact = (TextView)v.findViewById(R.id.textViewContactInfo);

		sp = PreferenceManager.getDefaultSharedPreferences(getActivity());
		userName.setTypeface(sofiaPro);
		userName.setText(sp.getString("user_name", "Suryansh Shukla"));
		address.setTypeface(sofiaPro);
		specialization.setTypeface(sofiaPro);
		experience.setTypeface(sofiaPro);
		contact.setTypeface(sofiaPro);
			
		return v;
	}


	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		
		//Adjust as per your db----------
		String userId="1";
		
		DoctorsModel objDoctorsModel = new DoctorsModel();
		objDoctorsModel.setDoctorId(userId);
		
		new DoctorsProfileTask(getActivity()).execute(objDoctorsModel);
		
	}

}
