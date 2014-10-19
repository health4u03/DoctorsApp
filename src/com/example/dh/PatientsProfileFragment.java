package com.example.dh;

import java.util.ArrayList;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class PatientsProfileFragment extends Fragment {

	
	TextView textViewName,textViewAddress,textViewLastLogin,textViewContactInfo,textViewnofovisits;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.new_patients_profile, null);
		 Typeface sofiaPro = Typeface.createFromAsset(getActivity().getAssets(), "SofiaProLight.otf");

		
		textViewAddress = (TextView)v.findViewById(R.id.textViewAddressUser);
		textViewContactInfo = (TextView)v.findViewById(R.id.textViewContactInfo);
		textViewLastLogin = (TextView)v.findViewById(R.id.textViewlastLogin);
		textViewnofovisits = (TextView)v.findViewById(R.id.textViewNoOfVisists);
		textViewName = (TextView)v.findViewById(R.id.textViewUserName);
				
		
		textViewAddress.setTypeface(sofiaPro);
		textViewContactInfo.setTypeface(sofiaPro);
		textViewLastLogin.setTypeface(sofiaPro);
		textViewnofovisits.setTypeface(sofiaPro);
		textViewName.setTypeface(sofiaPro);
			
		
		return v;
	}
}
