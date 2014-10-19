package com.example.dh;


import java.util.ArrayList;

import org.w3c.dom.ls.LSInput;

import com.example.asyctask.AddMedicinesTask;
import com.example.asyctask.MedicinesListTask;
import com.example.customadapter.CustomListAddMedicines;
import com.example.datamodels.ListDataMedicines;
import com.example.datamodels.MedicinesClinicModel;

import android.media.audiofx.AutomaticGainControl;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.YuvImage;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ToggleButton;

@SuppressLint("NewApi")
public class Add_medicines_to_clinic extends Fragment implements OnClickListener {

	String addedMedicines;
	ListView MedicinesList;
	
	// Defined Array values to show in ListView
	String[] values ;
	ArrayList<String> arrayListMedicines ;
	Button addMore;
	private LinearLayout linearLayout=null;
	private LinearLayout mainlinearLayout;
	ToggleButton done;
	AutoCompleteTextView aut01,aut02;
	int counter=3;
	CheckBox checkBoxMorning,checkBoxAfternoon,checkBoxEvening,checkBoxNight,checkBoxMorningMore,checkBoxAfternoonMore,checkBoxEveningMore,checkBoxNightMore;
	ListView listViewMedicines;
	ArrayList<ListDataMedicines> myList = new ArrayList<ListDataMedicines>();
	ListDataMedicines objListDataMedicines;
	CustomListAddMedicines adapter;
	
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);

		new MedicinesListTask(getActivity()).execute();

	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		inflater.inflate(R.menu.add_medicines_to_clinic, menu);
		super.onCreateOptionsMenu(menu,inflater);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.new_coming_soon, null);
		/*mainlinearLayout = (LinearLayout)v.findViewById(R.id.linearLayoutMain);
		addMore = (Button)v.findViewById(R.id.buttonAddMore);
		
		checkBoxMorning  = (CheckBox)v.findViewById(R.id.checkBoxMorning);
		checkBoxAfternoon  = (CheckBox)v.findViewById(R.id.checkBoxAfternoon);
		checkBoxEvening  = (CheckBox)v.findViewById(R.id.checkBoxEvening);
		checkBoxNight  = (CheckBox)v.findViewById(R.id.checkBoxNight);

		listViewMedicines = (ListView)v.findViewById(R.id.listViewMedicines);

		arrayListMedicines = new ArrayList<String>();

		addMore.setOnClickListener(this);
		init();
		Log.d("suize", ""+myList.size());
		adapter = new CustomListAddMedicines(getActivity().getBaseContext(), myList);
		listViewMedicines.setAdapter(adapter);*/


		return v;
	}




		@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// handle item selection
		switch (item.getItemId()) {
		case R.id.add_add_medicines_to_clinic_menu :
			final Dialog dialog = new Dialog(getView().getContext());
			dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

			dialog.setContentView(R.layout.activity_add_symptoms);
			Button add = (Button)dialog.findViewById(R.id.buttonAddPopUp);
			final EditText symptomEdit = (EditText)dialog.findViewById(R.id.editTextAddPopUp);
			TextView textLabel = (TextView)dialog.findViewById(R.id.textViewAddPopUp);
			textLabel.setText("Add Medicines to clinic");

			dialog.show();

			
			add.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					addedMedicines = symptomEdit.getText().toString();
					arrayListMedicines.add(addedMedicines);
					dialog.dismiss();
					if(arrayListMedicines!=null)
					{
						String[] stockArr = new String[arrayListMedicines.size()];
						stockArr = arrayListMedicines.toArray(stockArr);

						ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getBaseContext(),
								android.R.layout.simple_list_item_1, android.R.id.text1, stockArr);
						MedicinesList.setAdapter(adapter);

						MedicinesClinicModel objMedicinesClinicModel  =new MedicinesClinicModel();
						objMedicinesClinicModel.setMedicineName(addedMedicines);

						new AddMedicinesTask(getActivity()).execute(objMedicinesClinicModel);

					}
				}
			});
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	private void init() {
		for(int i=1;i<4;i++){
			objListDataMedicines =new ListDataMedicines();
			objListDataMedicines.setTitle(""+i);
			myList.add(objListDataMedicines);
		}
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		switch(v.getId()){

		case R.id.buttonAddMore:


			/*if(counter!=0){
				linearLayout = (LinearLayout)View.inflate(getActivity(),
						R.layout.new_add_medicines, null);
				//mainlinearLayout.addView(linearLayout);
				mainlinearLayout.addView(linearLayout,counter);  
				counter++;
			}

			final ToggleButton doneADd = (ToggleButton)linearLayout.findViewById(R.id.buttonDone);
			TextView counter = (TextView)linearLayout.findViewById(R.id.textViewMedicineCounter);
			counter.setText(""+this.counter);
			aut02 = (AutoCompleteTextView)linearLayout.findViewById(R.id.editTextAddMedicine);

			checkBoxMorningMore  = (CheckBox)linearLayout.findViewById(R.id.checkBoxMorning);
			checkBoxAfternoonMore  = (CheckBox)linearLayout.findViewById(R.id.checkBoxAfternoon);
			checkBoxEveningMore  = (CheckBox)linearLayout.findViewById(R.id.checkBoxEvening);
			checkBoxNightMore  = (CheckBox)linearLayout.findViewById(R.id.checkBoxNight);



			doneADd.setOnCheckedChangeListener(new OnCheckedChangeListener() {

				@Override
				public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
					// TODO Auto-generated method stub

					if(isChecked){
						Log.d("Text is", ""+aut02.getText().toString());
						arrayListMedicines.add(aut02.getText().toString());


						validateCheckBoxesMore();

						disabledFieldsMore();

						aut02.setEnabled(false);


					}else{
						enabledFieldsMore();

						aut02.setEnabled(true);
					}
				}
			});*/

			getDataInList();
			Log.d("Size of list", ""+myList.size());
		

			for(int i=0;i<myList.size();i++){
				Log.d("el", ""+myList.get(i).getTitle());
			}
			break;
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

	protected void enabledFieldsMore() {
		// TODO Auto-generated method stub
		aut02.setEnabled(true);
		checkBoxAfternoonMore.setEnabled(true);
		checkBoxEveningMore.setEnabled(true);
		checkBoxMorningMore.setEnabled(true);
		checkBoxNightMore.setEnabled(true);

	}

	protected void disabledFieldsMore() {
		// TODO Auto-generated method stub
		aut02.setEnabled(false);
		checkBoxAfternoonMore.setEnabled(false);
		checkBoxEveningMore.setEnabled(false);
		checkBoxMorningMore.setEnabled(false);
		checkBoxNightMore.setEnabled(false);

	}

	protected void validateCheckBoxesMore() {
		// TODO Auto-generated method stub
		if(checkBoxMorningMore.isChecked()){
			Log.d("Checked is", ""+checkBoxMorningMore.getText().toString());
		}if(checkBoxAfternoonMore.isChecked()){
			Log.d("Checked is", ""+checkBoxAfternoonMore.getText().toString());
		}if(checkBoxEveningMore.isChecked()){
			Log.d("Checked is", ""+checkBoxEveningMore.getText().toString());
		}if(checkBoxNightMore.isChecked()){
			Log.d("Checked is", ""+checkBoxNightMore.getText().toString());
		}
	}
}

