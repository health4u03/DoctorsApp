package com.example.dh;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;

public class CountryDetailsActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.country_details_activity_layout);
		
		FragmentManager fragmentManager = getFragmentManager();
		
		FragmentTransaction fragmentTransacton = fragmentManager.beginTransaction();
		
		CountryDetailsFragment detailsFragment = new CountryDetailsFragment();
		
		Bundle b = new Bundle();

		b.putInt("position", getIntent().getIntExtra("position", 0));
		
		detailsFragment.setArguments(b);
		
		fragmentTransacton.add(R.id.country_details_fragment_container, detailsFragment);		
		
		fragmentTransacton.commit();
		
	}
}
