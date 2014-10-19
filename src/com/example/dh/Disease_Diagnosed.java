package com.example.dh;

import java.util.ArrayList;

import com.example.customadapter.CustomListAddDisease;
import com.example.customadapter.CustomListAddSymptoms;
import com.example.datamodels.ListDataDisease;
import com.example.datamodels.ListDataSymptoms;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Disease_Diagnosed extends Fragment implements OnClickListener {



	String addedDisease;
	ListView DiseaseList;
	// Defined Array values to show in ListView
	String[] values ;
	ArrayList<String> arrayListDisease ;

	Button addMore;
	ArrayList<ListDataDisease> myList = new ArrayList<ListDataDisease>();
	CustomListAddDisease adapter;
	ListDataDisease objDataDisease;
	int counter=1;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.activity_patients_disease_diagnosed, null);
		DiseaseList = (ListView)v.findViewById(R.id.listViewDisease);
		arrayListDisease = new ArrayList<String>();




		addMore = (Button)v.findViewById(R.id.buttonAddMoreDisease);

		init();
		addMore.setOnClickListener(this);

		adapter = new CustomListAddDisease(getActivity().getBaseContext(), myList);
		DiseaseList.setAdapter(adapter);


		return v;
	}

	private void init() {
		// TODO Auto-generated method stub

		objDataDisease =new ListDataDisease();
		objDataDisease.setTitle("1");
		myList.add(objDataDisease);

	}

	private void getDataList() {
		// TODO Auto-generated method stub
		if(counter!=0)
		{		
			objDataDisease =new ListDataDisease();
			counter++;
			objDataDisease.setTitle(""+counter);
			myList.add(counter-1, objDataDisease);
		}
		adapter.notifyDataSetChanged();
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){

		case R.id.buttonAddMoreDisease:

			getDataList();
			break;

		}
	}


}