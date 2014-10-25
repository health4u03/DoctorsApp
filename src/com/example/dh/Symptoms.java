package com.example.dh;

import java.util.ArrayList;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Prescription;
import com.erxproject.erx.model.prescription.Symptom;
import com.example.customadapter.CustomListAddParameters;
import com.example.customadapter.CustomListAddSymptoms;
import com.example.datamodels.ListDataParameters;
import com.example.datamodels.ListDataSymptoms;


import android.annotation.SuppressLint;
import android.app.Dialog;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
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
import android.widget.ToggleButton;

@SuppressLint("NewApi")
public class Symptoms extends Fragment implements OnClickListener{

	Prescription p;
	String addedSymptoms;
	ListView symptomsList;
	String[] values ;
	ArrayList<String> arrayListSymptoms ;
	Button addMore;
	ArrayList<ListDataSymptoms> myList = new ArrayList<ListDataSymptoms>();
	CustomListAddSymptoms adapter;
	ListDataSymptoms objDataSymptoms;
	int counter=0;
	
	/* (non-Javadoc)
	 * @see android.app.Fragment#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		Log.d("dh", "in on create symptoms");
		View v = inflater.inflate(R.layout.symptoms, null);
		symptomsList = (ListView)v.findViewById(R.id.listViewSymptoms);
		arrayListSymptoms = new ArrayList<String>();


		addMore = (Button)v.findViewById(R.id.buttonAddMoreSymptoms);

		init();
		addMore.setOnClickListener(this);

		adapter = new CustomListAddSymptoms(getActivity().getBaseContext(), myList);
		symptomsList.setAdapter(adapter);


		return v;
	}

	private void init() {
		// TODO Auto-generated method stub
			counter = 0;
			myList.clear();
		p = Prescription.get(getActivity());
		ArrayList<Symptom> symptoms = p.getSymptoms();
		
		for (Symptom s : symptoms) {
			objDataSymptoms =new ListDataSymptoms();
			objDataSymptoms.setTitle("" + (counter+1));
			objDataSymptoms.setParametername(s.getDetails());
			objDataSymptoms.isEditEnabled = false;
			myList.add(objDataSymptoms);
			counter++;
		}
		if(myList.isEmpty()) {
			getDataList();
		}
	}

	/*@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		Log.d("dh", "in oncreate options menu symptoms");
		inflater.inflate(R.menu.menu_add_symptoms, menu);
		super.onCreateOptionsMenu(menu,inflater);
	}*/

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){

		case R.id.buttonAddMoreSymptoms:

			getDataList();

			break;
		}
	}

	private void getDataList() {
		// TODO Auto-generated method stub
	
			objDataSymptoms =new ListDataSymptoms();
			objDataSymptoms.setTitle(""+(counter+1));
			myList.add(counter, objDataSymptoms);
			counter++;

			if(myList.size()>1){
				adapter.notifyDataSetChanged();
			}
	}

	/*	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	   // handle item selection
	   switch (item.getItemId()) {
	      case R.id.add_symptoms_menu:
	         // do s.th.
	    		final Dialog dialog = new Dialog(getView().getContext());
				dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

				dialog.setContentView(R.layout.activity_add_symptoms);
				Button add = (Button)dialog.findViewById(R.id.buttonAddPopUp);
				final EditText symptomEdit = (EditText)dialog.findViewById(R.id.editTextAddPopUp);
				TextView textLabel = (TextView)dialog.findViewById(R.id.textViewAddPopUp);
				textLabel.setText("Add Symptoms");
				dialog.show();

				add.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						addedSymptoms = symptomEdit.getText().toString();
						arrayListSymptoms.add(addedSymptoms);
						dialog.dismiss();
						if(arrayListSymptoms!=null)
						{
							String[] stockArr = new String[arrayListSymptoms.size()];
							stockArr = arrayListSymptoms.toArray(stockArr);

							ArrayAdapter<String> adapter = new ArrayAdapter<String>(getView().getContext(),
									android.R.layout.simple_list_item_1, android.R.id.text1, stockArr);
							symptomsList.setAdapter(adapter);
						}
					}
				});
	         return true;
	      default:
	         return super.onOptionsItemSelected(item);
	   }
	}*/



}
