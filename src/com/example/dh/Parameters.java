package com.example.dh;

import java.util.ArrayList;

import com.example.customadapter.CustomListAddMedicines;
import com.example.customadapter.CustomListAddParameters;
import com.example.datamodels.ListDataMedicines;
import com.example.datamodels.ListDataParameters;


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
public class Parameters extends Fragment implements OnClickListener {

	String addedParameters;
	ListView ParametersList;
	// Defined Array values to show in ListView
	String[] values ;
	ArrayList<String> arrayListParameters ;
	EditText textViewKey,textViewValues;
	Button buttonAdd,addMore;
	int counter=2;
	ArrayList<ListDataParameters> myList = new ArrayList<ListDataParameters>();
	CustomListAddParameters adapter;
	ListDataParameters objDataParameters;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.parameters, null);

		ParametersList = (ListView)v.findViewById(R.id.listViewParameters);
		arrayListParameters = new ArrayList<String>();

		addMore = (Button)v.findViewById(R.id.buttonAddMoreParameter);

		ParametersList = (ListView)v.findViewById(R.id.listViewParameter);

		init();
		addMore.setOnClickListener(this);

		adapter = new CustomListAddParameters(getActivity().getBaseContext(), myList);
		ParametersList.setAdapter(adapter);

		return v;
	}

	private void init() {

			objDataParameters =new ListDataParameters();
			objDataParameters.setTitle("1");
			objDataParameters.setParametername("Temperature");
			myList.add(objDataParameters);
	
			objDataParameters =new ListDataParameters();
			objDataParameters.setTitle("2");
			objDataParameters.setParametername("Blood Pressure");
			myList.add(objDataParameters);
	
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	
	@Override
	public void onClick(View v) {

		switch(v.getId()){

		case R.id.buttonAddMoreParameter:



			getDataInList();

			break;

		}

	}
	private void getDataInList() {
		// TODO Auto-generated method stub
		if(counter!=0)
		{		
			objDataParameters =new ListDataParameters();
			counter++;
			objDataParameters.setTitle(""+counter);
			myList.add(counter-1, objDataParameters);
		}
		adapter.notifyDataSetChanged();
	}
}
