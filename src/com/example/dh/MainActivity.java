package com.example.dh;

import com.example.dh.CountryListFragment.ListFragmentItemClickListener;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;

@SuppressLint("NewApi")
public class MainActivity extends Activity implements ListFragmentItemClickListener {

	Uri imageUri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);
		
	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		// TODO Auto-generated method stub
		super.onSaveInstanceState(outState);
		if (imageUri != null) {
			Log.d("cac", "Endocedimage uri in onsave"+imageUri);
			outState.putString("cameraImageUri", imageUri.toString());
		}		
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if (savedInstanceState.containsKey("cameraImageUri")) {
			Log.d("cac", "inresto"+imageUri);
			imageUri = Uri.parse(savedInstanceState.getString("cameraImageUri"));
		}
	}
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

				DoctorsProfile dp = new DoctorsProfile();
				fragmentTransaction.add(R.id.detail_fragment_container, dp,"com.exampe.dh.country.details");
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();

				break;

			case 1 :
				
				// Patients Login
				
				PatientsLogin pl = new PatientsLogin();
				fragmentTransaction.add(R.id.detail_fragment_container, pl,"com.exampe.dh.country.details");
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				break;

			case 2 :
				Add_Patient objAddPatient = new Add_Patient();
				fragmentTransaction.add(R.id.detail_fragment_container, objAddPatient,"com.exampe.dh.country.details");
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				break;

			case 3:
				// Add medicines to clinic
				Add_medicines_to_clinic amtc = new Add_medicines_to_clinic();
				fragmentTransaction.add(R.id.detail_fragment_container, amtc,"com.exampe.dh.country.details");
				fragmentTransaction.addToBackStack(null);
				fragmentTransaction.commit();
				break;

			case 4 :
				// Login
				Intent i =new Intent(MainActivity.this, Login.class);
				startActivity(i);
				//overridePendingTransition(R.anim.slide_up, R.anim.side_down);

				break;
			default:
				break;
			}
		}
	}



	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) { 
			// do something on back.
			Intent startMain = new Intent(Intent.ACTION_MAIN); 
			startMain.addCategory(Intent.CATEGORY_HOME); 
			startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(startMain); 
			return true; 
		}
		return super.onKeyDown(keyCode, event);
	}


}
