package com.example.asyctask;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.example.datamodels.PatientsParameterModel;
import com.example.datamodels.serialized.LoginResponse;
import com.example.dh.MainActivity;
import com.example.dh.PatientsLogin;
import com.example.dh.PatientsProfile;
import com.example.dh.R;
import com.example.dh.util.Constants;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.EditText;

public class PatientsProfileTask extends AsyncTask<PatientsParameterModel, String, String>{

	Activity activity;
	String jsonResponseString;
	ProgressDialog pd;
	PatientsParameterModel objPatientsParameterModel;
	SharedPreferences sp;
	EditText patientsId;

	public PatientsProfileTask(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		Gson gson = new Gson();

		LoginResponse response = gson.fromJson(result, LoginResponse.class);

		pd.dismiss();

		if (response.success == 1) {
		Intent i = new Intent(activity, PatientsProfile.class);
		activity.startActivity(i);
		
		sp = PreferenceManager.getDefaultSharedPreferences(activity);
		
		Editor ed = sp.edit();
		ed.putString(activity.getString(R.string.sp_patient_name), response.user.name);
		ed.putInt(activity.getString(R.string.sp_patient_id), response.user.patient_id);
		ed.putInt(activity.getString(R.string.sp_patient_person_id), response.user.person_id);
		ed.apply();
		//mLogin.overridePendingTransition(R.anim.side_down, R.anim.slide_up);
		} else {
			patientsId = (EditText)activity.findViewById(R.id.editTextPatientsUserName);
			patientsId.setError(response.error_msg);
		}
	}



	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pd= new ProgressDialog(activity);
		pd.setMessage("Please Wait..");
		pd.setCancelable(false);
		//	 pd.show();
	}



	@Override
	protected String doInBackground(PatientsParameterModel... params) {

		objPatientsParameterModel  = params[0];


		Log.d("PatiensId", ""+objPatientsParameterModel.getPatientsId());

		Gson gson = new Gson();
		String request = gson.toJson(params[0]);
		Log.d("gson is", ""+request);

		HttpResponse response;

		// Creating HTTP client
		HttpClient httpClient = new DefaultHttpClient();

		//Creating HttpPost
		//Modify your url
			HttpPost httpPost = new HttpPost(Constants.SERVER_URL +"/android_api/patient.php");

		Log.d("Call to servlet", "Call servlet");

		// Building post parameters, key and value pair
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
		nameValuePair.add(new BasicNameValuePair("tag", "login"));
		nameValuePair.add(new BasicNameValuePair("email",objPatientsParameterModel.getPatientsId()));

		Log.d("cac", "NameValuePair"+nameValuePair);
		// Url Encoding the POST parameters
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		}
		catch (UnsupportedEncodingException e) {
			// writing error to Log
			e.printStackTrace();
		}
		try {

			System.out.println("Executing...");
			response = httpClient.execute(httpPost);

			HttpEntity entity = response.getEntity();
			jsonResponseString = EntityUtils.toString(entity);
			Log.d("Http Response:",jsonResponseString);

		}catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonResponseString;

	}
}
