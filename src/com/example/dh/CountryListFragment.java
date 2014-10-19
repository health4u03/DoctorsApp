package com.example.dh;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CountryListFragment extends ListFragment{

	/** List of countries to be displayed in the ListFragment */
	ListFragmentItemClickListener ifaceItemClickListener;	
	int mCurCheckPosition = 0;

	public interface ListFragmentItemClickListener {
		void onListFragmentItemClick(int position);
	}	

	/* (non-Javadoc)
	 * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);

		//getListView().setBackgroundColor(Color.parseColor("C8C8C8"));
		getListView().setBackgroundResource(com.example.dh.R.drawable.border);
		getListView().setSelector(com.example.dh.R.drawable.listview_selector);
	}

	/** A callback function, executed when this fragment is attached to an activity */	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try{
			ifaceItemClickListener = (ListFragmentItemClickListener) activity;			
		}catch(Exception e){
			Toast.makeText(activity.getBaseContext(), "Exception",Toast.LENGTH_SHORT).show();
		}
	}

	/* (non-Javadoc)
	 * @see android.app.ListFragment#onViewCreated(android.view.View, android.os.Bundle)
	 */
	@SuppressLint("NewApi")
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onViewCreated(view, savedInstanceState);

	}
	@Override
	public void onStart() {
		super.onStart();

		//set first item activated by default
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		
		onListItemClick(getListView(), getView(), 0, 0);
		getListView().setItemChecked(0, true);

	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, Country.name);
		setListAdapter(adapter);	


		//getListView().setSelection(0);
		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {	

		/** Invokes the implementation of the method onListFragmentItemClick in the hosting activity */
		ifaceItemClickListener.onListFragmentItemClick(position);

	}
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("curChoice", mCurCheckPosition);
	}

}
