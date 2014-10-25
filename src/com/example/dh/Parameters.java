package com.example.dh;

import java.util.ArrayList;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.prescription.Parameter;
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

	Prescription p;
	String addedParameters;
	ListView ParametersList;
	// Defined Array values to show in ListView
	String[] values ;
	ArrayList<String> arrayListParameters ;
	EditText textViewKey,textViewValues;
	Button buttonAdd,addMore;
	int counter=0;
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
		
		counter = 0;
		myList.clear();
		p = Prescription.get(getActivity());
		
		ArrayList<Parameter> parameters = p.getParameters();
		
		for(Parameter parameter: parameters)
		{
			objDataParameters =new ListDataParameters();
			objDataParameters.setTitle("" + (counter+1));
			objDataParameters.setParametername(parameter.getmParameterType());
			objDataParameters.setParameterValue(parameter.getmParameterValue());
			myList.add(objDataParameters);
			counter++;
		}
		if(myList.isEmpty()) {
			getDataInList();
		}
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

			objDataParameters =new ListDataParameters();
			objDataParameters.setTitle(""+(counter+1));
			myList.add(counter, objDataParameters);
			counter++;
			if(myList.size()>1){
				adapter.notifyDataSetChanged();
			}
	}
}
