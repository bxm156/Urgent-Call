package com.bryanmarty.urgentcall;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.TimePicker;

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
		
		final Button dateButton = new Button(context_);
		dateButton.setText("Weekday");
		dateButton.setOnClickListener(new OnClickListener() {
		
			@Override
			public void onClick(View v) {
				final CharSequence[] items = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
				AlertDialog.Builder builder = new AlertDialog.Builder(context_);
				builder.setTitle("Pick a day");
				builder.setItems(items, new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						dateButton.setText(items[which]);
						
					}
				});
				
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
		
		final Button timeButton = new Button(context_);
		timeButton.setText("Time");
		timeButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Calendar cal = Calendar.getInstance();
				final OnTimeSetListener listener = new OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						timeButton.setText( hourOfDay + " " + minute);
						
					}
				};
				TimePickerDialog d = new TimePickerDialog(context_, listener, cal.get(Calendar.HOUR_OF_DAY),
					cal.get(Calendar.MINUTE), false);
				d.show();
				
			}
		});
		
		row.addView(dateButton);
		row.addView(timeButton);
		
		return row;
	}
	
	public static Dialog createTimePickerDialog() {
		Context mContext = context_.getApplicationContext();
		Dialog dialog = new Dialog(mContext);
		dialog.setContentView(1);
		dialog.setTitle("Pick A Time");
		return dialog;
	}
}
