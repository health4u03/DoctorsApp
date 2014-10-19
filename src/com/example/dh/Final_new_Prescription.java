package com.example.dh;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.customadapter.CustomListAddMedcinesPrescription;
import com.example.datamodels.ListDataMedicines;
import com.example.datamodels.ListDataSymptoms;
import com.example.datamodels.ListDataTests;
import com.example.datamodels.ParameterModel;
import com.example.dh.util.PrintAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.print.PrintManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Final_new_Prescription extends Activity{

	/* (non-Javadoc)
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	ListView listOfMedicnes;

	ArrayList<String> arrayListMedicines ;
	Button addMore;
	CustomListAddMedcinesPrescription adapter;
	ArrayList<ListDataMedicines> myList = new ArrayList<ListDataMedicines>();
	ListDataMedicines objListDataMedicines;
	ListDataTests objDataTests = new ListDataTests();
	ParameterModel objParameterModel;
	ListDataSymptoms objDataSymptoms ;
	ListDataMedicines objDataMedicines;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_prescription);

		init();

		adapter = new CustomListAddMedcinesPrescription(this, generateData());
		listOfMedicnes.setAdapter(adapter);
	}


	/* (non-Javadoc)
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.prescription,menu);

		return super.onCreateOptionsMenu(menu);
	}



	/* (non-Javadoc)
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){

		case R.id.add_print:

			if (android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.KITKAT) {
				Toast.makeText(getApplicationContext(), "This feature is not compatible", Toast.LENGTH_SHORT).show();
			}

			// Get a PrintManager instance
			PrintManager printManager = (PrintManager)getApplicationContext().getSystemService(Context.PRINT_SERVICE);

			// Set job name, which will be displayed in the print queue
			String jobName = getString(R.string.app_name) + "_Document";

			// Start a print job, passing in a PrintDocumentAdapter implementation
			// to handle the generation of a print document
			printManager.print(jobName,
					new PrintAdapter(getApplicationContext()), null); //

			break;

		}


		return super.onOptionsItemSelected(item);
	}


	private ArrayList<ListDataMedicines> generateData() {
		// TODO Auto-generated method stub

		ArrayList<ListDataMedicines> models = new ArrayList<ListDataMedicines>();

		ArrayList<String> tests = objDataTests.getTests();
		List<Map<String, String>> parameters = objParameterModel.getparameters();
		ArrayList<String> symptoms= objDataSymptoms.getParameters();

		ArrayList<String> medicines = objDataMedicines.getMedicineList();

		Log.d("poa array", ""+parameters);
		/*
		 * Add elements array wise so if any array is empty
		 *  it will not to be added into list
		 */


		//--------------Send Medicines array --------

		if(medicines.size()!=0){
			models.add(new ListDataMedicines("Medicines"));
			for(int i=0;i<medicines.size();i++){

				models.add(new ListDataMedicines(0,medicines.get(i),"200mg"));

			}
			//-------Send symptoms arrray-------------

			if(symptoms.size()!=0){
				models.add(new ListDataMedicines("Symptoms"));

				for(int i=0;i<symptoms.size();i++){
					models.add(new ListDataMedicines(0,symptoms.get(i),""));
				}
			}
			//-------Send Tests arrray-------------
			if(tests.size()!=0){
				models.add(new ListDataMedicines("Tests"));
				for(int i=0;i<tests.size();i++){
					models.add(new ListDataMedicines(0,""+tests.get(i),""));
				}
			}


			//-------Send Parameter arrray-------------
			if(parameters.size()!=0){
				models.add(new ListDataMedicines("Prameter"));
				for(int i=0;i<parameters.size();i++){
					models.add(new ListDataMedicines(0,parameters.get(i).get("parametername"),parameters.get(i).get("parametervalue")));
				}
			}
		}
		return models;
	}

		private void init() {
			// TODO Auto-generated method stub
			listOfMedicnes = (ListView)findViewById(R.id.listViewMedicinesPrescription);
			objDataTests  = new ListDataTests();

			objParameterModel= new ParameterModel();
			objDataSymptoms = new ListDataSymptoms();
			objDataMedicines = new ListDataMedicines();
			//objListDataMedicines =new ListDataMedicines();


			/*objListDataMedicines =new ListDataMedicines();
			objListDataMedicines.setMedicineName("Crocin");
			objListDataMedicines.setMedicineDosage("200mg");
			myList.add(objListDataMedicines);

			objListDataMedicines =new ListDataMedicines();
			objListDataMedicines.setMedicineName("Aceact");
			objListDataMedicines.setMedicineDosage("250mg");
			myList.add(objListDataMedicines);

			objListDataMedicines =new ListDataMedicines();
			objListDataMedicines.setMedicineName("Crocin");
			objListDataMedicines.setMedicineDosage("200mg");
			myList.add(objListDataMedicines);*/

		}

	}
