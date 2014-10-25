package com.example.dh;

import org.json.JSONException;

import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.Prescription;
import com.example.asyctask.DoctorsProfileTask;
import com.example.asyctask.PatientsProfileTask;
import com.example.datamodels.DoctorsModel;
import com.example.datamodels.PatientsParameterModel;
import com.example.dh.CountryListFragmentPatient.ListFragmentItemClickListener;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.util.Log;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;

@SuppressLint("NewApi")
public class PatientsProfile extends Activity implements ListFragmentItemClickListener{

	PrescriptionController pc;
	SharedPreferences sp;
	Prescription prescription;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_patient);

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
		//initialize the prescription from the server;
		sp = PreferenceManager.getDefaultSharedPreferences(this);
		pc = new PrescriptionController(this);
		try {
			prescription = pc.getUnsavedPrescription(sp.getInt(this.getString(R.string.sp_patient_id), 1), 
					sp.getInt(this.getString(R.string.sp_patient_person_id), 2), 
					sp.getInt(this.getString(R.string.sp_doctor_doctor_id), 1));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		return super.onContextItemSelected(item);
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {

		super.onCreateContextMenu(menu, v, menuInfo);
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		Intent i = new Intent(PatientsProfile.this, MainActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); 
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(i);
		overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
	}

	/* (non-Javadoc)
	 * @see com.example.dh.CountryListFragmentPatient.ListFragmentItemClickListener#onListFragmentItemClick(int)
	 */
	@Override
	public void onListFragmentItemClick(int position) {
		int orientation = getResources().getConfiguration().orientation;

		if(orientation == Configuration.ORIENTATION_LANDSCAPE ){
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
			Fragment prevFrag = fragmentManager.findFragmentByTag("com.exampe.dh.country.details");

			if(prevFrag!=null)
				fragmentTransaction.remove(prevFrag);    		

			switch (position) {
			case 0:
				// Main Activity
				Intent i = new Intent(PatientsProfile.this, MainActivity.class);
				startActivity(i);
				break;

			case 1 :
				// Patients Profile-- create a class for patients profile seperately and change layout for main current patients profile
				PatientsProfileFragment pp = new PatientsProfileFragment();
				fragmentTransaction.add(R.id.detail_fragment_container_patient, pp,"com.exampe.dh.country.details");
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				break;

			case 2 :
				// Symptoms
				Symptoms amtc = new Symptoms();
				fragmentTransaction.add(R.id.detail_fragment_container_patient, amtc,"com.exampe.dh.country.details");
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				break;

			case 3 :
				// Parameters
				Parameters amtcP = new Parameters();
				fragmentTransaction.add(R.id.detail_fragment_container_patient, amtcP,"com.exampe.dh.country.details");
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();

				break;
			case 4 :
				// Disease Diagnosed
				// Parameters
				Disease_Diagnosed amtcD = new Disease_Diagnosed();
				fragmentTransaction.add(R.id.detail_fragment_container_patient, amtcD,"com.exampe.dh.country.details");
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();

				break;
			case 5 :
				// Medicines
				Medicines amtcM = new Medicines();
				fragmentTransaction.add(R.id.detail_fragment_container_patient, amtcM,"com.exampe.dh.country.details");
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();

				break;
			case 6 :
				// Tests
				Tests amtcT = new Tests();
				fragmentTransaction.add(R.id.detail_fragment_container_patient, amtcT,"com.exampe.dh.country.details");
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();

				break;

			case 7 :
				// Final Prescription
				//				Final_prescription amtcF = new Final_prescription();
				//				fragmentTransaction.add(R.id.detail_fragment_container_patient, amtcF,"com.exampe.dh.country.details");
				//				fragmentTransaction.addToBackStack(null);
				//				fragmentTransaction.commit();

				Intent prescription = new Intent(this, Final_new_Prescription.class);
				startActivity(prescription);


				break;
			default:
				break;
			}
		}
	}
}
