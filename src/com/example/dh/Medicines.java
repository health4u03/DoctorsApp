package com.example.dh;

import java.util.ArrayList;

import com.example.customadapter.CustomListAddMedicines;
import com.example.datamodels.ListDataMedicines;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("NewApi")
public class Medicines extends Fragment implements OnClickListener {



	String addedMedicines;
	ListView MedicinesList;
	// Defined Array values to show in ListView
	String[] values ;
	ArrayList<String> arrayListMedicines ;
	Button addMore;
	CustomListAddMedicines adapter;
	ArrayList<ListDataMedicines> myList = new ArrayList<ListDataMedicines>();
	ListDataMedicines objListDataMedicines;
	int counter=3;


	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.medicines, null);
		MedicinesList = (ListView)v.findViewById(R.id.listViewMedicines);
		arrayListMedicines = new ArrayList<String>();

		addMore = (Button)v.findViewById(R.id.buttonAddMore);




		addMore.setOnClickListener(this);
		init();
		Log.d("suize", ""+myList.size());
		adapter = new CustomListAddMedicines(getActivity().getBaseContext(), myList);
		MedicinesList.setAdapter(adapter);

		return v;
	}

	private void init() {
		// TODO Auto-generated method stub
		for(int i=1;i<4;i++){
			objListDataMedicines =new ListDataMedicines();
			objListDataMedicines.setTitle(""+i);
			myList.add(objListDataMedicines);
		}
	}

	private void getDataInList() {
		// TODO Auto-generated method stub
		if(counter!=0)
		{		
			objListDataMedicines =new ListDataMedicines();
			counter++;
			objListDataMedicines.setTitle(""+counter);
			myList.add(counter-1, objListDataMedicines);
		}
		adapter.notifyDataSetChanged();
	}

	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.buttonAddMore:
			getDataInList();
			break;
		}
	}



}
