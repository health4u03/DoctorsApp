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
import com.example.dh.util.Constants;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.util.Log;

public class PatientsProfileTask extends AsyncTask<PatientsParameterModel, String, String>{

	Activity _patiensProfile;
	String jsonResponseString;
	ProgressDialog pd;
	PatientsParameterModel objPatientsParameterModel;

	public PatientsProfileTask(Activity activity) {
		// TODO Auto-generated constructor stub
		_patiensProfile = activity;
	}



	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		Gson gson = new Gson();

		LoginResponse response = gson.fromJson(result, LoginResponse.class);

		pd.dismiss();

		//if (response.success == 1) {
		Intent i = new Intent(_patiensProfile, PatientsProfile.class);
		_patiensProfile.startActivity(i);
		/*
		Editor ed = sp.edit();
		ed.putString("user_name_login", objLoginModel.getUserName());
		ed.putString("password_login", objLoginModel.getPassword());
		ed.putString("user_name", response.user.name);
		ed.apply();
		//mLogin.overridePendingTransition(R.anim.side_down, R.anim.slide_up);
		} else {
			username.setError("Enter valid details");
			password.setError(response.error_msg);
		}
		*/
		
	}



	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pd= new ProgressDialog(_patiensProfile);
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
