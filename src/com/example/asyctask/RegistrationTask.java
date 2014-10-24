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

import com.example.datamodels.LoginModel;
import com.example.datamodels.RegistrationModel;
import com.example.datamodels.serialized.LoginResponse;
import com.example.datamodels.serialized.RegistrationResponse;
import com.example.dh.MainActivity;
import com.example.dh.Registration;
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

public class RegistrationTask extends AsyncTask<RegistrationModel, String, String>{
	
	Activity mRegistration;
	ProgressDialog pd;
	String jsonResponseString;
	SharedPreferences sp;
	RegistrationModel objRegistrationModel;
	
	public RegistrationTask(Registration registration) {

		mRegistration =registration;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pd = new ProgressDialog(mRegistration);
		pd.setMessage("Signing Up..!");
		pd.setTitle("Please Wait..");
		pd.show();
	}

	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		pd.dismiss();
		Log.d("response json is ", "" + result);

		Gson gson = new Gson();

		RegistrationResponse response = gson.fromJson(result, RegistrationResponse.class);

		pd.dismiss();

		if (response.success == 1) {
		Intent i = new Intent(mRegistration, MainActivity.class);
		mRegistration.startActivity(i);
		sp = PreferenceManager.getDefaultSharedPreferences(mRegistration);
		Editor ed = sp.edit();
		ed.putString("user_name_login", objRegistrationModel.getEmail());
		ed.putString("password_login", objRegistrationModel.getPassword());
		ed.apply();
		//mLogin.overridePendingTransition(R.anim.side_down, R.anim.slide_up);
		} else {
//			username.setError("Enter valid details");
//			password.setError(response.error_msg);
		}
	}
	
	@Override
	protected String doInBackground(RegistrationModel... params) {

		/*
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		objRegistrationModel = params[0];
		
		Log.d("username", ""+objRegistrationModel.getName());
		Log.d("password", ""+objRegistrationModel.getPassword());
		Log.d("address", ""+objRegistrationModel.getAddress());
		Log.d("email", ""+objRegistrationModel.getEmail());
		Log.d("phone", ""+objRegistrationModel.getPhone());
		
		Log.d("object", ""+params[0]);
		Gson gson = new Gson();
		String request = gson.toJson(params[0]);
		Log.d("gson is", ""+request);
		
		HttpResponse response;

		// Creating HTTP client
		HttpClient httpClient = new DefaultHttpClient();

		//Creating HttpPost
		//Modify your url
		HttpPost httpPost = new HttpPost(Constants.SERVER_URL +"/android_api/doctor.php");
		
		Log.d("Call to servlet", "Call servlet");

		// Building post parameters, key and value pair
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
		nameValuePair.add(new BasicNameValuePair("tag", "register"));
		nameValuePair.add(new BasicNameValuePair("name", objRegistrationModel.getName() ));
		nameValuePair.add(new BasicNameValuePair("email",objRegistrationModel.getEmail() ));
		nameValuePair.add(new BasicNameValuePair("password",objRegistrationModel.getPassword() ));
		nameValuePair.add(new BasicNameValuePair("address",objRegistrationModel.getAddress() ));
		nameValuePair.add(new BasicNameValuePair("contact",objRegistrationModel.getPhone() ));

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
