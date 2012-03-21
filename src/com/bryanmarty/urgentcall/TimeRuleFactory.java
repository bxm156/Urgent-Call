package com.bryanmarty.urgentcall;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class TimeRuleFactory {
	
	private static TimeRuleFactory instance_ = null;
	private static Activity context_;
	
	public static TimeRuleFactory getInstance(Activity context) {
		if(instance_ == null) {
			instance_ = new TimeRuleFactory(context);
		}
		return instance_;
	}
	
	public TimeRuleFactory(Activity context) {
		context_ = context;
	}
	
	final static OnDateSetListener dateListener = new OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			Log.i("DATE", "Returned: " + monthOfYear + " " + dayOfMonth + " " + year);
			
		}
	};
	
	
	public static LinearLayout createTimeRuleView() {
		LinearLayout row = new LinearLayout(context_);
		LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,0f);
		row.setLayoutParams(lp);
		
		row.setOrientation(LinearLayout.HORIZONTAL);
		
		Button dateButton = new Button(context_);
		dateButton.setText("Time");
		dateButton.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				Calendar cal = Calendar.getInstance();
				DatePickerDialog datePickDiag = new DatePickerDialog(context_, dateListener, cal.get(Calendar.YEAR),
						cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH));
				datePickDiag.show();
				
				
			}
		});
		
		row.addView(dateButton);
		
		return row;
	}
}
