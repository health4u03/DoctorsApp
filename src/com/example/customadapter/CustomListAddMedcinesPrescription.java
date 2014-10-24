package com.example.customadapter;

import java.util.ArrayList;
import java.util.List;

import com.example.datamodels.ListDataDisease;
import com.example.datamodels.ListDataMedicines;
import com.example.datamodels.ListDataParameters;
import com.example.datamodels.ListDataSymptoms;
import com.example.dh.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

/*public class CustomListAddMedcinesPrescription extends BaseAdapter {


	private Context activity;
	private LayoutInflater inflater;
	private List<ListDataMedicines> movieItems;
	ArrayList<String> arrayListMedicines ;


	public CustomListAddMedcinesPrescription(Context context, ArrayList<ListDataMedicines> myList) {
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

		TextView textViewMedicineName,textViewDosage;
		//ToggleButton buttonDone;
		//EditText editTextDiseasename;


	}
	@Override
	public View getView(final int position, View convertView, final ViewGroup parent) {
		// TODO Auto-generated method stub

		final ViewHolderMainHome holderMain;
		arrayListMedicines = new ArrayList<String>();
		
		
		if(convertView == null)
		{
			holderMain = new ViewHolderMainHome();
			convertView = inflater.inflate(R.layout.new_medicine_prescription, null);

			holderMain.textViewMedicineName = (TextView)convertView.findViewById(R.id.textViewMedicinename);
			holderMain.textViewDosage = (TextView)convertView.findViewById(R.id.textViewMedicineDosage);

			//holderMain.editTextDiseasename = (EditText)convertView.findViewById(R.id.editTextAddDisease);
			//holderMain.buttonDone = (ToggleButton)convertView.findViewById(R.id.buttonDoneDisease);


			convertView.setTag(holderMain);

		}else
		{
			holderMain = (ViewHolderMainHome)convertView.getTag();
		}

		//holderMain.editTextDiseasename.setTag(position);

		//holderMain.editTextDiseasename.setId(position);

		ListDataMedicines m = movieItems.get(position);

		holderMain.textViewMedicineName.setText(m.getMedicineName());
		holderMain.textViewDosage.setText(m.getMedicineDosage());
		
		//holderMain.textViewCounter.setText(m.getTitle());
		//holderMain.editTextSymptomname.setText(m.getParametername());


		//holderMain.buttonDone.setTag(position);
	holderMain.buttonDone.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				int position = ((Integer) buttonView.getTag()).intValue();

				if(isChecked){
					// myItems.put(position, Caption.getText().toString());                    
					Log.d("parameter name values",""+holderMain.editTextDiseasename.getText().toString());

					//objClinicModel.setUserNames(holderMain.autoTextView.getText().toString());
					disableFields();

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

}*/
public class CustomListAddMedcinesPrescription extends ArrayAdapter<ListDataMedicines> {
	 
    private final Context context;
    private final ArrayList<ListDataMedicines> modelsArrayList;

    public CustomListAddMedcinesPrescription(Context context, ArrayList<ListDataMedicines> modelsArrayList) {

        super(context, R.layout.new_medicine_prescription, modelsArrayList);

        this.context = context;
        this.modelsArrayList = modelsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // 1. Create inflater 
        LayoutInflater inflater = (LayoutInflater) context
            .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        // 2. Get rowView from inflater

        View rowView = null;
        if(!modelsArrayList.get(position).isGroupHeader()){
            rowView = inflater.inflate(R.layout.new_medicine_prescription, parent, false);

            // 3. Get icon,title & counter views from the rowView
            TextView medcineName = (TextView) rowView.findViewById(R.id.textViewMedicinename);
            TextView medicineDosage = (TextView) rowView.findViewById(R.id.textViewMedicineDosage);

            // 4. Set the text for textView 
         //   imgView.setImageResource(modelsArrayList.get(position).getIcon());
            medcineName.setText(modelsArrayList.get(position).getMedicineName());
            medicineDosage.setText(modelsArrayList.get(position).getMedicineDosage());
        }
        else{
                rowView = inflater.inflate(R.layout.header_item, parent, false);
                TextView titleView = (TextView) rowView.findViewById(R.id.header);
                titleView.setText(modelsArrayList.get(position).getTitle());

        }

        // 5. retrn rowView
        return rowView;
    }
}