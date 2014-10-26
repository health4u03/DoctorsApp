package com.example.customadapter;

import java.util.ArrayList;
import java.util.List;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Prescription;
import com.example.datamodels.ListDataMedicines;
import com.example.datamodels.MedicinesClinicModel;
import com.example.dh.R;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CustomListAddMedicines extends BaseAdapter {

	private PrescriptionController pc;
	private Prescription p;
	private Context activity;
	private LayoutInflater inflater;
	private List<ListDataMedicines> movieItems;
	ArrayList<String> arrayListMedicines ;
	MedicinesClinicModel objClinicModel;
	ListDataMedicines objListDataMedicines;
	
	public CustomListAddMedicines(Context context, List<ListDataMedicines> movieItems) {
		inflater = LayoutInflater.from(context);
		this.activity = context;
		this.movieItems = movieItems;
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
		CheckBox checkBoxMorning,checkBoxAfternoon,checkBoxEvening,checkBoxNight;
		AutoCompleteTextView autoTextView;


	}
	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub


		final ViewHolderMainHome holderMain;
		arrayListMedicines = new ArrayList<String>();
		objClinicModel = new MedicinesClinicModel();
		objListDataMedicines = movieItems.get(position);
		if(convertView == null)
		{
			holderMain = new ViewHolderMainHome();
			convertView = inflater.inflate(R.layout.new_add_medicines, null);

			holderMain.textViewCounter = (TextView)convertView.findViewById(R.id.textViewMedicineCounter);
			holderMain.autoTextView = (AutoCompleteTextView)convertView.findViewById(R.id.editTextAddMedicine);
			holderMain.buttonDone = (ToggleButton)convertView.findViewById(R.id.buttonDone);

			holderMain.checkBoxMorning  = (CheckBox)convertView.findViewById(R.id.checkBoxMorning);
			holderMain.checkBoxAfternoon  = (CheckBox)convertView.findViewById(R.id.checkBoxAfternoon);
			holderMain.checkBoxEvening  = (CheckBox)convertView.findViewById(R.id.checkBoxEvening);
			holderMain.checkBoxNight  = (CheckBox)convertView.findViewById(R.id.checkBoxNight);


			convertView.setTag(holderMain);

		}else
		{
			holderMain = (ViewHolderMainHome)convertView.getTag();
		}

		holderMain.autoTextView.setTag(position);

		holderMain.autoTextView.setId(position);
		holderMain.autoTextView.setText(objListDataMedicines.getMedicineName());
		holderMain.textViewCounter.setText(objListDataMedicines.getTitle());
		holderMain.checkBoxMorning.setChecked(objListDataMedicines.isMorning());
		holderMain.checkBoxAfternoon.setChecked(objListDataMedicines.isAfternoon());
		holderMain.checkBoxEvening.setChecked(objListDataMedicines.isEvening());
		holderMain.checkBoxNight.setChecked(objListDataMedicines.isNight());

		holderMain.buttonDone.setTag(position);
		holderMain.buttonDone.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				int position = ((Integer) buttonView.getTag()).intValue();

				if(isChecked){
					// myItems.put(position, Caption.getText().toString());                    

					if(holderMain.autoTextView.getText().toString().equalsIgnoreCase("")){

						Toast.makeText(activity, "Please Enter Medicine", Toast.LENGTH_SHORT).show();
						holderMain.buttonDone.setChecked(true);
						holderMain.buttonDone.setText("Done");

					}else{
						Log.d("auto values",""+holderMain.autoTextView.getText().toString());
						objClinicModel.setUserNames(holderMain.autoTextView.getText().toString());
						getValuesOfCheckboxes();
						objListDataMedicines.setMedicineName(holderMain.autoTextView.getText().toString());
						objListDataMedicines.setMorning(holderMain.checkBoxMorning.isChecked());
						objListDataMedicines.setAfternoon(holderMain.checkBoxAfternoon.isChecked());
						objListDataMedicines.setEvening(holderMain.checkBoxEvening.isChecked());
						objListDataMedicines.setNight(holderMain.checkBoxNight.isChecked());
						
						pc = new PrescriptionController(activity);
						p = Prescription.get(activity);
						int medicineDataId = pc.savePrescriptionMedicine(p.getHistoryId(), 0, holderMain.autoTextView.getText().toString(), 
								holderMain.checkBoxMorning.isChecked(), 
								holderMain.checkBoxAfternoon.isChecked(), 
								holderMain.checkBoxEvening.isChecked(),
								holderMain.checkBoxNight.isChecked());
								
						p.getMedicine().add(pc.getPrescriptionMedicineFromId(medicineDataId));
						
						disabledFields();
					}

					Log.d("Array is:",""+objClinicModel.getUserNames());

				}else{
					//holderMain.autoTextView.setEnabled(true);
					enabledFields();

				}
			}

			private void enabledFields() {
				// TODO Auto-generated method stub
				holderMain.autoTextView.setEnabled(true);
				holderMain.checkBoxAfternoon.setEnabled(true);
				holderMain.checkBoxEvening.setEnabled(true);
				holderMain.checkBoxMorning.setEnabled(true);
				holderMain.checkBoxNight.setEnabled(true);
			}

			private void disabledFields() {
				// TODO Auto-generated method stub
				holderMain.autoTextView.setEnabled(false);
				holderMain.checkBoxAfternoon.setEnabled(false);
				holderMain.checkBoxEvening.setEnabled(false);
				holderMain.	checkBoxMorning.setEnabled(false);
				holderMain.	checkBoxNight.setEnabled(false);
			}

			private void getValuesOfCheckboxes() {
				// TODO Auto-generated method stub

				if(holderMain.checkBoxMorning.isChecked()){
					Log.d("Checked is", ""+holderMain.checkBoxMorning.getText().toString());
				}if(holderMain.checkBoxAfternoon.isChecked()){
					Log.d("Checked is", ""+holderMain.checkBoxAfternoon.getText().toString());
				}if(holderMain.checkBoxEvening.isChecked()){
					Log.d("Checked is", ""+holderMain.checkBoxEvening.getText().toString());
				}if(holderMain.checkBoxNight.isChecked()){
					Log.d("Checked is", ""+holderMain.checkBoxNight.getText().toString());
				}
			}
		});
		return convertView;
	}

}