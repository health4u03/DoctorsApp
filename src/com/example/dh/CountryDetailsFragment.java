package com.example.dh;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CountryDetailsFragment extends Fragment {
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		
		View v = inflater.inflate(R.layout.country_details_fragment_layout, null);
		TextView tv = (TextView) v.findViewById(R.id.country_details);		
		Bundle b = getArguments();
		tv.setText("Details of " + Country.name[b.getInt("position")]);		
		
		return v;
	}
	
}
