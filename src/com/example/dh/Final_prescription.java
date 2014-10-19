package com.example.dh;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.example.asyctask.SavePrescriptionTask;
import com.example.datamodels.PatientsParameterModel;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.Fragment;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Base64;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Final_prescription extends Fragment {
	Button save , exitPatient;
	int i = 0;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.activity_patients_final_prescription, null);
		save = (Button)v.findViewById(R.id.buttonSavePrescription);
		exitPatient = (Button)v.findViewById(R.id.buttonLogoutPrescription);
		exitPatient.setOnClickListener(new  OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent(getView().getContext(), MainActivity.class);
				startActivity(i);
				
			}
		});

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(save.getText().equals("Save"))
				{
					save.setText("Print");

					View view = getActivity().findViewById(R.id.relativelayout);
					view.setDrawingCacheEnabled(true);
					Bitmap bitmap = view.getDrawingCache();
					BitmapDrawable bitmapDrawable = new BitmapDrawable(bitmap);
					
					//imgv_showscreenshot = (ImageView) findViewById(R.id.imgv_showscreenshot);
					// set screenshot bitmapdrawable to imageview
					//imgv_showscreenshot.setBackgroundDrawable(bitmapDrawable);

					if (Environment.MEDIA_MOUNTED.equals(Environment
							.getExternalStorageState())) {
						// we check if external storage is available, otherwise
						// display an error message to the user using Toast Message
						File sdCard = Environment.getExternalStorageDirectory();
						File directory = new File(sdCard.getAbsolutePath()
								+ "/ScreenShots");
						directory.mkdirs();

						String filename = "screenshot" + i + ".jpg";
						File yourFile = new File(directory, filename);

						while (yourFile.exists()) {
							i++;
							filename = "screenshot" + i + ".jpg";
							yourFile = new File(directory, filename);
						}

						if (!yourFile.exists()) {
							if (directory.canWrite()) {
								try {
									FileOutputStream out = new FileOutputStream(
											yourFile, true);
									bitmap.compress(Bitmap.CompressFormat.PNG, 90,
											out);
									out.flush();
									out.close();
									Toast.makeText(
											getActivity(),
											"File exported to /sdcard/ScreenShots/screenshot"
													+ i + ".jpg",
											Toast.LENGTH_SHORT).show();
									i++;
								} catch (IOException e) {
									e.printStackTrace();
								}

							}
						}

					} else {
						Toast.makeText(getActivity(),
								"Sorry SD Card not available in your Device!",
								Toast.LENGTH_SHORT).show();
					}

					
					
					//------------------Covert bitmap to string------------------
					ByteArrayOutputStream baos = new ByteArrayOutputStream();  
					bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos); //bm is the bitmap object   
					byte[] b = baos.toByteArray();
					String encodedImage = Base64.encodeToString(b, Base64.DEFAULT);

					
					PatientsParameterModel objParameterModel  =new PatientsParameterModel();

					//set id of respective patient and respective date
					objParameterModel.setPatientsId("1");
					objParameterModel.setTreatmentDate("12aug");
					
					//-----pass here the screenshot of created perscription------------
					objParameterModel.setParameterName(encodedImage);
					
					new SavePrescriptionTask(getActivity()).execute(objParameterModel);
				}
			}
		});

		return v;
	}


}