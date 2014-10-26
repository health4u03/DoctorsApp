package com.example.customadapter;

import java.util.ArrayList;
import java.util.List;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Prescription;
import com.example.datamodels.ListDataDisease;
import com.example.datamodels.ListDataParameters;
import com.example.datamodels.ListDataSymptoms;
import com.example.dh.ErrorDialog;
import com.example.dh.Login;
import com.example.dh.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class CustomListAddDisease extends BaseAdapter {

	PrescriptionController pc;
	Prescription p;
	private Context activity;
	private LayoutInflater inflater;
	private List<ListDataDisease> movieItems;
	ArrayList<String> arrayListMedicines ;
	//MedicinesClinicModel objClinicModel;


	public CustomListAddDisease(Context context, ArrayList<ListDataDisease> myList) {
		inflater = LayoutInflater.from(context);
		this.activity = context;
		this.movieItems = myList;
	}

	@Override
	public int getCount() {
		return movieItems.size();
	}

	@Override
	public Object getItem(int location) {
		return movieItems.get(location);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}


	public class ViewHolderMainHome {

		TextView textViewCounter;
		ToggleButton buttonDone;
		EditText editTextDiseasename;
	}
	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub

		final ViewHolderMainHome holderMain;
		arrayListMedicines = new ArrayList<String>();
		if(convertView == null)
		{
			holderMain = new ViewHolderMainHome();
			convertView = inflater.inflate(R.layout.add_disease_value, null);

			holderMain.textViewCounter = (TextView)convertView.findViewById(R.id.textViewDiseaseCounter);

			holderMain.editTextDiseasename = (EditText)convertView.findViewById(R.id.editTextAddDisease);
			holderMain.buttonDone = (ToggleButton)convertView.findViewById(R.id.buttonDoneDisease);
			convertView.setTag(holderMain);

		}else
		{
			holderMain = (ViewHolderMainHome)convertView.getTag();
		}

		holderMain.editTextDiseasename.setTag(position);

		holderMain.editTextDiseasename.setId(position);

		final ListDataDisease m = movieItems.get(position);

		holderMain.textViewCounter.setText(m.getTitle());
		holderMain.editTextDiseasename.setText(m.getParametername());

		holderMain.buttonDone.setTag(position);
		holderMain.buttonDone.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				int position = ((Integer) buttonView.getTag()).intValue();

				if(isChecked){
					// myItems.put(position, Caption.getText().toString());                    
					
					if(holderMain.editTextDiseasename.getText().toString().equalsIgnoreCase("")){
					
						Toast.makeText(activity, "Please Enter Disease Name", Toast.LENGTH_SHORT).show();
						Log.d("parameter name values",""+holderMain.editTextDiseasename.getText().toString());
						holderMain.buttonDone.setTextOn("Done");
						holderMain.buttonDone.toggle();
						
					}else{
						holderMain.buttonDone.setTextOn("Edit");

						pc = new PrescriptionController(activity);
						p = Prescription.get(activity);
						
						int historyId = p.getHistoryId();
						
						int diseaseId = pc.saveDiseaseDiagnosed(historyId, holderMain.editTextDiseasename.getText().toString());
						
						p.getDisease().add(pc.getDiseaseFromId(diseaseId));
						
						m.setParametername(holderMain.editTextDiseasename.getText().toString());
						//objClinicModel.setUserNames(holderMain.autoTextView.getText().toString());
						disableFields();
					}
				
				}else{
					//holderMain.autoTextView.setEnabled(true);
					enableFields();
				}
			}

			private void enableFields() {
				// TODO Auto-generated method stub
				holderMain.editTextDiseasename.setEnabled(true);
			
			}

			private void disableFields() {
				// TODO Auto-generated method stub
				holderMain.editTextDiseasename.setEnabled(false);
				
			}

		});
		return convertView;
	}

}