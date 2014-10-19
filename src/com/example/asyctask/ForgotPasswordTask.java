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
import com.example.dh.R;
import com.example.datamodels.ForgotPasswordModel;
import com.example.datamodels.LoginModel;
import com.example.dh.ForgotPassword;
//import com.example.datamodels.serialized.LoginResponse;
import com.example.dh.Login;
import com.example.dh.MainActivity;
import com.example.dh.util.Constants;
import com.google.gson.Gson;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.EditText;

public class ForgotPasswordTask extends AsyncTask<ForgotPasswordModel, String, String> {

	EditText username, password;
	Activity mForgot;
	ProgressDialog pd;
	String jsonResponseString;

	public ForgotPasswordTask(ForgotPassword forgotPassword) {
		mForgot = forgotPassword;
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Log.d("response json is ", "" + result);

		Gson gson = new Gson();


		pd.dismiss();

	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pd = new ProgressDialog(mForgot);
		pd.setMessage("Please wait while we are signing you in..");
		pd.setTitle("Signing in");
		pd.setCancelable(false);
		pd.show();
	}

	@Override
	protected String doInBackground(ForgotPasswordModel... params) {

		ForgotPasswordModel objForgotPasswordModel = params[0];


		Gson gson = new Gson();
		String request = gson.toJson(params[0]);
		Log.d("gson is", "" + request);

		HttpResponse response;

		// Creating HTTP client
		HttpClient httpClient = new DefaultHttpClient();

		// Creating HttpPost
		// Modify your url for ForgotPassword
				HttpPost httpPost = new HttpPost(Constants.SERVER_URL
						+ "/android_api/forgotPassword.php");

		Log.d("Call to servlet", "Call servlet");

		// Building post parameters, key and value pair
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
		nameValuePair.add(new BasicNameValuePair("tag", "login"));
		nameValuePair.add(new BasicNameValuePair("email", objForgotPasswordModel
				.getEmail()));
		Log.d("cac", "NameValuePair" + nameValuePair);
		// Url Encoding the POST parameters
		try {
			httpPost.setEntity(new UrlEncodedFormEntity(nameValuePair));
		} catch (UnsupportedEncodingException e) {
			// writing error to Log
			e.printStackTrace();
		}
		try {

			System.out.println("Executing...");
			response = httpClient.execute(httpPost);

			HttpEntity entity = response.getEntity();
			jsonResponseString = EntityUtils.toString(entity);
			// Log.d("Http Response:",jsonResponseString);

		} catch (ClientProtocolException e) {
			// writing exception to log
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonResponseString;
	}

}
