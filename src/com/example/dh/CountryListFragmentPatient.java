package com.example.dh;

import android.R;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListFragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class CountryListFragmentPatient extends ListFragment{

	/** List of countries to be displayed in the ListFragment */

	ListFragmentItemClickListener ifaceItemClickListener;	


	/* (non-Javadoc)
	 * @see android.app.Fragment#onActivityCreated(android.os.Bundle)
	 */
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		getListView().setBackgroundResource(com.example.dh.R.drawable.border);
		getListView().setSelector(com.example.dh.R.drawable.listview_selector);

	}

	/** An interface for defining the callback method */
	public interface ListFragmentItemClickListener {
		/** This method will be invoked when an item in the ListFragment is clicked */
		void onListFragmentItemClick(int position);
	}	
	@Override
	public void onStart() {
		super.onStart();

		//set first item activated by default
		getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);

		onListItemClick(getListView(), getView(), 1, 0);
		getListView().setItemChecked(0, true);

	}
	/** A callback function, executed when this fragment is attached to an activity */	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);

		try{
			/** This statement ensures that the hosting activity implements ListFragmentItemClickListener */
			Log.d("dh", "implements listFragmnet");
			ifaceItemClickListener = (ListFragmentItemClickListener) activity;			
		}catch(Exception e){
			Toast.makeText(activity.getBaseContext(), "Exception in on attach",Toast.LENGTH_SHORT).show();
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		Log.d("dh", "set array list patients");
		/** Data source for the ListFragment */
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(inflater.getContext(), android.R.layout.simple_list_item_1, CountryPatient.name);

		/** Setting the data source to the ListFragment */
		setListAdapter(adapter);	

		return super.onCreateView(inflater, container, savedInstanceState);
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {	

		/** Invokes the implementation of the method onListFragmentItemClick in the hosting activity */
		ifaceItemClickListener.onListFragmentItemClick(position);

	}

}
