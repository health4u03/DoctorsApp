package com.example.dh;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class ErrorDialog {

	public static void ErrorDialogCreation(Context c,String titleDesc,String desc){

		final Dialog dialog = new Dialog(c);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		// Include dialog.xml file
		dialog.setContentView(R.layout.custom_dialog);

		TextView title = (TextView)dialog.findViewById(R.id.textTitleDialog);
		TextView text = (TextView) dialog.findViewById(R.id.textViewDialog);
		text.setText(desc);
		title.setText(titleDesc);
		dialog.show();

		Button declineButton = (Button) dialog.findViewById(R.id.buttondialog);

		// if decline button is clicked, close the custom dialog
		declineButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
	}
}
