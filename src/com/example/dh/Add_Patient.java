package com.example.dh;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import com.example.asyctask.AddNewPatienTask;
import com.example.datamodels.NewPatientModel;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Toast;

@SuppressLint("NewApi")
public class Add_Patient extends Fragment implements OnClickListener {

	EditText patientsId;
	Button find, newUser;

	Button donee, setimage;
	EditText username, address, contact,DOB,firstName,lastName,password;
	RadioGroup PatientGender;
	ImageView imgUserPhoto;
	String nameP, addressP, contactP, dOBP, sexP, imageP;
	Calendar myCalendar;
	String gender;


	String encodedImage, path,filePath,fileName;
	Uri imageUri;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.new_add_patient, null);

		Typeface sofiaPro = Typeface.createFromAsset(getActivity().getAssets(), "SofiaProLight.otf");


		donee = (Button)v.findViewById(R.id.buttonAddPatient);
		username = (EditText)v.findViewById(R.id.UserNamePatientAdd);
		address = (EditText)v.findViewById(R.id.UserAddressPatientAdd);
		contact = (EditText )v.findViewById(R.id.UserContactPatientAdd);
		DOB= (EditText)v.findViewById(R.id.UserDOB);
		firstName = (EditText)v.findViewById(R.id.editTextFirstName);
		lastName = (EditText)v.findViewById(R.id.editTextLastName);
		password= (EditText)v.findViewById(R.id.patientPassword);
		PatientGender = (RadioGroup)v.findViewById(R.id.radioGroupGender);
		imgUserPhoto  = (ImageView)v.findViewById(R.id.imageViewUserPhotoPatient);
		imgUserPhoto.setOnClickListener(this);
		PatientGender.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId ==R.id.radioMale){
					gender = "Male";
				}else{
					gender="Female";
				}
			}
		});

		username.setTypeface(sofiaPro);
		address.setTypeface(sofiaPro);
		username.setTypeface(sofiaPro);
		contact.setTypeface(sofiaPro);
		DOB.setTypeface(sofiaPro);
		donee.setTypeface(sofiaPro);

		donee.setOnClickListener(this);

		//--------------------Setting Calender to dob-------------------//
		final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

			public void onDateSet(DatePicker view, int year, int monthOfYear,
					int dayOfMonth) {
				// TODO Auto-generated method stub
				myCalendar.set(Calendar.YEAR, year);
				myCalendar.set(Calendar.MONTH, monthOfYear);
				myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

				updateDate();
			}

		};

		DOB.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				if(event.getAction() == MotionEvent.ACTION_UP){
					new DatePickerDialog(getActivity(), date,1950, myCalendar.get(Calendar.MONTH),
							myCalendar.get(Calendar.DAY_OF_MONTH)).show();
				}
				return false;
			}
		});

		return v;
	}


	private void updateDate() {
		// TODO Auto-generated method stub
		String myFormat = "yyyy-MM-dd"; //In which you need put here

		SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
		DOB.setText(sdf.format(myCalendar.getTime()));

	}

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		myCalendar = Calendar.getInstance();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){

		case R.id.buttonAddPatient:

			if(firstName.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(getActivity(),"Warning", "Please Enter DOB");
			}else if(lastName.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(getActivity(),"Warning", "Please Enter DOB");
			}else if(username.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(getActivity(),"Warning", "Please Enter Email ID");
			}else if(address.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(getActivity(),"Warning", "Please Enter Address");
			}else if(contact.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(getActivity(),"Warning", "Please Enter Contact number");
			}else if(DOB.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(getActivity(),"Warning", "Please Enter DOB");
			}else if(password.getText().toString().equalsIgnoreCase("")){
				ErrorDialog.ErrorDialogCreation(getActivity(),"Warning", "Please Enter Password");
			}
			else{

				NewPatientModel objNewPatientModel = new NewPatientModel();
				objNewPatientModel.setPatientsName(username.getText().toString());
				objNewPatientModel.setPatientsAddress(address.getText().toString());
				objNewPatientModel.setPatientsContact(contact.getText().toString());
				objNewPatientModel.setPatientsDOB(DOB.getText().toString());
				objNewPatientModel.setPatientsLastName(lastName.getText().toString());
				objNewPatientModel.setPatientsFirstName(firstName.getText().toString());

				new AddNewPatienTask(getActivity()).execute(objNewPatientModel);

				//---Manipulate After addtion of patient completed
				Intent i = new Intent(getActivity(), PatientsProfile.class);
				startActivity(i);

			}

			break;

		case R.id.imageViewUserPhotoPatient:

			takePhoto();

			break;
		}
	}

	private void takePhoto() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		File photo = new File(Environment.getExternalStorageDirectory(),  "Pic.jpg");
		
		imageUri = Uri.fromFile(photo);
		intent.putExtra(MediaStore.EXTRA_OUTPUT,
				imageUri);
		Log.d("image", ""+imageUri);
		Log.d("intetn", ""+intent.getExtras().get("data"));
		getActivity().startActivityForResult(intent, 100);
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
	    public void onActivityResult(int requestCode, int resultCode, Intent data) {
	        super.onActivityResult(requestCode, resultCode, data);
	        switch (requestCode) {
	        case 100:
	            if (resultCode == Activity.RESULT_OK) {
	            	Log.d("image1", ""+data.getExtras().get("data"));
	            	            	
	                Uri selectedImage = imageUri;
	                getActivity().getContentResolver().notifyChange(selectedImage, null);
	                ContentResolver cr = getActivity().getContentResolver();
	                Bitmap bitmap;
	                try {
	                     bitmap = android.provider.MediaStore.Images.Media
	                     .getBitmap(cr, selectedImage);

	                     imgUserPhoto.setImageBitmap(bitmap);
	                    Toast.makeText(getActivity(), selectedImage.toString(),
	                            Toast.LENGTH_LONG).show();
	                } catch (Exception e) {
	                    Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_SHORT)
	                            .show();
	                    Log.e("Camera", e.toString());
	                }
	            }
	        }
	    }
}
