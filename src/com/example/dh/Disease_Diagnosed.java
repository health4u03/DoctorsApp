package com.example.dh;

import java.util.ArrayList;

import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.prescription.Disease;
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


	Prescription p;
	String addedDisease;
	ListView DiseaseList;
	// Defined Array values to show in ListView
	String[] values ;
	ArrayList<String> arrayListDisease ;

	Button addMore;
	ArrayList<ListDataDisease> myList = new ArrayList<ListDataDisease>();
	CustomListAddDisease adapter;
	ListDataDisease objDataDisease;
	int counter=0;


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

		p = Prescription.get(getActivity());
		/*
		ArrayList<Disease> diseases = p.getDisease();
		
		for(Disease d: diseases){
			objDataDisease =new ListDataDisease();
			objDataDisease.setTitle("" + (counter+1));
			objDataDisease.setParametername(d.getDisease());
			myList.add(objDataDisease);
			counter++;
		}
		*/
		if(myList.isEmpty()) {
			getDataList();
		}
	}

	private void getDataList() {
		// TODO Auto-generated method stub
	
			objDataDisease =new ListDataDisease();
			objDataDisease.setTitle(""+(counter+1));
			myList.add(counter, objDataDisease);
			counter++;

			if(myList.size()>1){
				adapter.notifyDataSetChanged();
			}
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