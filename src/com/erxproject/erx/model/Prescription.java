package com.erxproject.erx.model;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.example.dh.R;
import com.erxproject.erx.controller.PrescriptionController;
import com.erxproject.erx.model.prescription.*;

public class Prescription {

	private int patientId;
	private int doctorId;
	private int historyId;
	private String dateCreated;
	private Context mContext;
	private ArrayList<Symptom> mSymptoms;
	private ArrayList<Parameter> mParamters;
	private ArrayList<Disease> mDiseases;
	private ArrayList<PrescriptionMedicine> mMedicine;
	private Note mNote;
	private static Prescription mPrescription;
	private PrescriptionController prescriptionController;

	private Prescription(Context context) {
		mContext = context.getApplicationContext();
		prescriptionController = new PrescriptionController(mContext);
	}

	public static Prescription get(Context context) {
		if (mPrescription == null) {
			mPrescription = new Prescription(context);
		}
		return mPrescription;
	}

	public static Prescription getNewPrescription(Context context) {
		if (mPrescription != null) {
			mPrescription = null;
		}
		mPrescription = new Prescription(context);
		return mPrescription;
	}

	public void getPrescriptionFromJSON(JSONObject json)
			throws NumberFormatException, JSONException {
		patientId = Integer.parseInt(json.getString(mContext
				.getString(R.string.key_patient_id)));
		doctorId = Integer.parseInt(json.getString(mContext
				.getString(R.string.key_doctor_id)));
		historyId = Integer.parseInt(json.getString(mContext
				.getString(R.string.key_history_id)));
		dateCreated = json.getString(mContext
				.getString(R.string.key_date_modified));
	}

	public ArrayList<Symptom> getSymptoms() {
		
		if(mSymptoms == null)
		{
			try {
				mSymptoms = prescriptionController.getSymptomList(historyId);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mSymptoms;
	}

	public ArrayList<Parameter> getParameters() {
		
		if(mParamters == null)
		{
			try {
				mParamters = prescriptionController.getParameterList(historyId);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return mParamters;
	}

	public ArrayList<Disease> getDisease() {
		
		if(mDiseases == null)
		{
			try {
				mDiseases = prescriptionController.getDiseasesDiagnosedList(historyId);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mDiseases;
	}

	public ArrayList<PrescriptionMedicine> getMedicine() {
		if(mMedicine==null)
		{
			try {
				mMedicine = prescriptionController.getPrescriptionMedicineList(historyId);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return mMedicine;
	}

	public Note getNote() {
		return mNote;
	}

	public int getHistoryId() {
		return historyId;
	}

	public void setSymptoms(ArrayList<Symptom> mSymptoms) {
		this.mSymptoms = mSymptoms;
	}

	public void setParameters(ArrayList<Parameter> mParameter) {
		this.mParamters = mParameter;
	}

	public void setDiseases(ArrayList<Disease> mDiseases) {
		this.mDiseases = mDiseases;
	}

	public ArrayList<PrescriptionMedicine> getPrescriptionMedicine() {
		return mMedicine;
	}

	public void setPrescriptionedicine(
			ArrayList<PrescriptionMedicine> prescriptionMedicine) {
		this.mMedicine = prescriptionMedicine;
	}

}
