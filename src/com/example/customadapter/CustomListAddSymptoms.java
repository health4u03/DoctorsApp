package com.example.customadapter;

import java.util.ArrayList;
import java.util.List;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Prescription;
import com.example.datamodels.ListDataParameters;
import com.example.datamodels.ListDataSymptoms;
import com.example.dh.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CustomListAddSymptoms extends BaseAdapter {


	private PrescriptionController pc;
	private Prescription p;
	private Context activity;
	private LayoutInflater inflater;
	private List<ListDataSymptoms> movieItems;
	ArrayList<String> arrayListMedicines ;
ListDataSymptoms objDataSymptoms;

	public CustomListAddSymptoms(Context context, ArrayList<ListDataSymptoms> myList) {
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
		EditText editTextSymptomname;


	}
	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub


		final ViewHolderMainHome holderMain;
		arrayListMedicines = new ArrayList<String>();
			objDataSymptoms = (ListDataSymptoms) this.getItem(position);
		if(convertView == null)
		{
			holderMain = new ViewHolderMainHome();
			convertView = inflater.inflate(R.layout.add_symptom_value, null);

			holderMain.textViewCounter = (TextView)convertView.findViewById(R.id.textViewSymptomCounter);

			holderMain.editTextSymptomname = (EditText)convertView.findViewById(R.id.editTextAddSymptom);
			holderMain.buttonDone = (ToggleButton)convertView.findViewById(R.id.buttonDoneSymptom);


			convertView.setTag(holderMain);

		}else
		{
			holderMain = (ViewHolderMainHome)convertView.getTag();
		}

		holderMain.editTextSymptomname.setTag(position);

		holderMain.editTextSymptomname.setId(position);//objDataSymptoms
		holderMain.editTextSymptomname.setText(objDataSymptoms.getParametername());

		ListDataSymptoms m = movieItems.get(position);

		if(!objDataSymptoms.isEditEnabled)
		{
			holderMain.editTextSymptomname.setEnabled(false);
			holderMain.buttonDone.setTextOn("Edit");
			//holderMain.buttonDone.toggle();
		}

		holderMain.textViewCounter.setText(m.getTitle());
		//holderMain.editTextSymptomname.setText(m.getParametername());

		holderMain.buttonDone.setTag(position);
		holderMain.buttonDone.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				int position = ((Integer) buttonView.getTag()).intValue();

				if(isChecked){
					// myItems.put(position, Caption.getText().toString());                    
					if(holderMain.editTextSymptomname.getText().toString().equalsIgnoreCase("")){

						Toast.makeText(activity, "Please Enter Symptom Name", Toast.LENGTH_SHORT).show();
						//holderMain.buttonDone.setTextOn("Done");
						holderMain.buttonDone.toggle();
						
					}else{
						Log.d("Symptom name values",""+holderMain.editTextSymptomname.getText().toString());
						
						p = Prescription.get(activity);
						int historyId = p.getHistoryId();
						pc = new PrescriptionController(activity);
						int symptomId = pc.saveSymptom(historyId, holderMain.editTextSymptomname.getText().toString());
						
						p.getSymptoms().add(pc.getSymptom(symptomId));
						
						objDataSymptoms.setParametername(holderMain.editTextSymptomname.getText().toString());
						disableFields();
					}
				}else{
					//holderMain.autoTextView.setEnabled(true);
					enableFields();
				}
			}

			private void enableFields() {
				// TODO Auto-generated method stub
				holderMain.editTextSymptomname.setEnabled(true);
				objDataSymptoms.isEditEnabled = true;
			}
			private void disableFields() {
				// TODO Auto-generated method stub
				holderMain.editTextSymptomname.setEnabled(false);
				holderMain.buttonDone.setTextOn("Edit");
				objDataSymptoms.isEditEnabled = false;
			}
		});
		return convertView;
	}
}