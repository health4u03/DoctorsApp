package com.example.asyctask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import com.example.datamodels.DoctorsModel;
import com.example.dh.DoctorsProfile;
import com.google.gson.Gson;

import android.app.Activity;
import android.app.Fragment;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

public class DoctorsProfileTask extends AsyncTask<DoctorsModel, String, String>{

	
	Activity _doctorsProfile;
	ProgressDialog pd;
	Context context;
	String jsonResponseString;
	
	public DoctorsProfileTask(Activity activity) {
		// TODO Auto-generated constructor stub
		_doctorsProfile =activity;
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
		pd = new ProgressDialog(_doctorsProfile);
		pd.setMessage("Please Wait..");
		pd.setCancelable(false);
		//pd.show();
	}





	@Override
	protected String doInBackground(DoctorsModel... params) {

		DoctorsModel objDoctorsModel = params[0];
		
		Log.d("userId", ""+objDoctorsModel.getDoctorId());
		
		Gson gson = new Gson();
		String request = gson.toJson(params[0]);
		Log.d("gson is", ""+request);
		
		HttpResponse response;

		// Creating HTTP client
		HttpClient httpClient = new DefaultHttpClient();

		//Creating HttpPost
		//Modify your url
	/*	HttpPost httpPost = new HttpPost(Constants.SERVER_URL +"/controllername");
		
		Log.d("Call to servlet", "Call servlet");

		// Building post parameters, key and value pair
		List<NameValuePair> nameValuePair = new ArrayList<NameValuePair>(2);
		nameValuePair.add(new BasicNameValuePair("jsondata", request));

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
		}*/
		return jsonResponseString;
	}

	
	@Override
	protected void onPostExecute(String result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
	}


	
	
	
}
