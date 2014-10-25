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
import com.example.datamodels.LoginModel;
import com.example.datamodels.serialized.LoginResponse;
//import com.example.datamodels.serialized.LoginResponse;
import com.example.dh.Login;
import com.example.dh.MainActivity;
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

public class LoginTask extends AsyncTask<LoginModel, String, String> {

	EditText username, password;
	Activity mLogin;
	ProgressDialog pd;
	String jsonResponseString;
	LoginModel objLoginModel;
	SharedPreferences sp;

	public LoginTask(Login login) {
		mLogin = login;
		username = (EditText) mLogin.findViewById(R.id.editTextUserNameLogIn);
		password = (EditText) mLogin.findViewById(R.id.editTextPasswordLogin);
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		Log.d("response json is ", "" + result);

		Gson gson = new Gson();

		LoginResponse response = gson.fromJson(result, LoginResponse.class);

		pd.dismiss();

		if (response.success == 1) {
		Intent i = new Intent(mLogin, MainActivity.class);
		mLogin.startActivity(i);
		sp = PreferenceManager.getDefaultSharedPreferences(mLogin);
		Editor ed = sp.edit();
		ed.putString(mLogin.getString(R.string.sp_doctor_user_name), objLoginModel.getUserName());
		ed.putString(mLogin.getString(R.string.sp_doctor_password), objLoginModel.getPassword());
		ed.putString(mLogin.getString(R.string.sp_doctor_name), response.user.name);
		ed.putInt(mLogin.getString(R.string.sp_doctor_doctor_id), response.user.doctor_id);
		ed.apply();
		//mLogin.overridePendingTransition(R.anim.side_down, R.anim.slide_up);
		} else {
			username.setError("Enter valid details");
			password.setError(response.error_msg);
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		pd = new ProgressDialog(mLogin);
		pd.setMessage("Please wait while we are signing you in..");
		pd.setTitle("Signing in");
		pd.setCancelable(false);
		pd.show();
	}

	@Override
	protected String doInBackground(LoginModel... params) {

		objLoginModel = params[0];

		Log.d("username", "" + objLoginModel.getUserName());
		Log.d("password", "" + objLoginModel.getPassword());

		Gson gson = new Gson();
		String request = gson.toJson(params[0]);
		Log.d("gson is", "" + request);

		HttpResponse response;

		// Creating HTTP client
		HttpClient httpClient = new DefaultHttpClient();

		// Creating HttpPost
		// Modify your url
		HttpPost httpPost = new HttpPost(Constants.SERVER_URL
				+ "/android_api/doctor.php");

		Log.d("Call to servlet", "Call servlet");

		// Building post parameters, key and value pair
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
		nameValuePair.add(new BasicNameValuePair("tag", "login"));
		nameValuePair.add(new BasicNameValuePair("email", objLoginModel
				.getUserName()));
		nameValuePair.add(new BasicNameValuePair("password", objLoginModel
				.getPassword()));

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
