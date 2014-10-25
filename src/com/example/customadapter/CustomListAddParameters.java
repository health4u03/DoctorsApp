package com.example.customadapter;

import java.util.ArrayList;
import java.util.List;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Prescription;
import com.example.datamodels.ListDataParameters;
import com.example.datamodels.ParameterModel;
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

public class CustomListAddParameters extends BaseAdapter {


	private Context activity;
	private LayoutInflater inflater;
	private List<ListDataParameters> movieItems;
	ArrayList<String> arrayListMedicines ;
	ParameterModel objParameterModel;
	Prescription p;
	PrescriptionController pc;

	public CustomListAddParameters(Context context, ArrayList<ListDataParameters> myList) {
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
		CheckBox checkBoxMorning,checkBoxAfternoon,checkBoxEvening,checkBoxNight;
		EditText editTextParametername,editTextParameterValue;


	}
	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub


		final ViewHolderMainHome holderMain;
		arrayListMedicines = new ArrayList<String>();
		objParameterModel =new ParameterModel();
		if(convertView == null)
		{
			holderMain = new ViewHolderMainHome();
			convertView = inflater.inflate(R.layout.add_parameter_value, null);

			holderMain.textViewCounter = (TextView)convertView.findViewById(R.id.textViewParameterCounter);

			holderMain.editTextParametername = (EditText)convertView.findViewById(R.id.editTextAddParameter);
			holderMain.editTextParameterValue = (EditText)convertView.findViewById(R.id.editTextAddParameterValue);
			holderMain.buttonDone = (ToggleButton)convertView.findViewById(R.id.buttonDoneParameter);


			convertView.setTag(holderMain);

		}else
		{
			holderMain = (ViewHolderMainHome)convertView.getTag();
		}

		holderMain.editTextParametername.setTag(position);
		holderMain.editTextParameterValue.setTag(position);

		holderMain.editTextParametername.setId(position);
		holderMain.editTextParameterValue.setId(position);

		final ListDataParameters m = movieItems.get(position);


		holderMain.textViewCounter.setText(m.getTitle());
		holderMain.editTextParametername.setText(m.getParametername());
		holderMain.editTextParameterValue.setText(m.getParameterValue());

		Log.d("pso", ""+position);

		if(position == 0){
			holderMain.editTextParametername.setEnabled(false);
		}
		if(position == 1){
			holderMain.editTextParametername.setEnabled(false);
		}
		
		holderMain.buttonDone.setTag(position);
		holderMain.buttonDone.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				int position = ((Integer) buttonView.getTag()).intValue();

				if(isChecked){
					// myItems.put(position, Caption.getText().toString());                    

					if(holderMain.editTextParametername.getText().toString().equalsIgnoreCase("")){

						Toast.makeText(activity, "Please Enter Parameter Name", Toast.LENGTH_SHORT).show();
						holderMain.buttonDone.setTextOff("Done");
						holderMain.buttonDone.toggle();
						
					}else if(holderMain.editTextParameterValue.getText().toString().equalsIgnoreCase("")){

						Toast.makeText(activity, "Please Enter Parameter Value", Toast.LENGTH_SHORT).show();
						holderMain.buttonDone.toggle();
						
					}else{
						holderMain.buttonDone.setTextOn("Edit");

						Log.d("parameter name values",""+holderMain.editTextParametername.getText().toString());
						Log.d("paratmeter values",""+holderMain.editTextParameterValue.getText().toString());

						//objClinicModel.setUserNames(holderMain.autoTextView.getText().toString());
						objParameterModel.setPatameters(holderMain.editTextParametername.getText().toString(), holderMain.editTextParameterValue.getText().toString());
						m.setParametername(holderMain.editTextParametername.getText().toString());
						m.setParameterValue(holderMain.editTextParameterValue.getText().toString());
						
						p = Prescription.get(activity);
						
						int historyId = p.getHistoryId();
						pc = new PrescriptionController(activity);
						
						int parameterId = pc.saveParameter(historyId, holderMain.editTextParametername.getText().toString(), holderMain.editTextParameterValue.getText().toString());
						p.getParameters().add(pc.getParameter(parameterId));
						
						disableFields();
						Log.d("get array", ""+objParameterModel.getparameters());

					}

				}else{
					enableFields();
				}
			}

			private void enableFields() {
				// TODO Auto-generated method stub
				holderMain.editTextParametername.setEnabled(true);
				holderMain.editTextParameterValue.setEnabled(true);

			}

			private void disableFields() {
				// TODO Auto-generated method stub
				holderMain.editTextParametername.setEnabled(false);
				holderMain.editTextParameterValue.setEnabled(false);

			}

		});
		return convertView;
	}

}