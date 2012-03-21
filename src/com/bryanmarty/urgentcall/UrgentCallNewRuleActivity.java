package com.bryanmarty.urgentcall;

import java.util.concurrent.Future;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UrgentCallNewRuleActivity extends Activity {
	
	public Context getContext() {
		return this;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TrackManager.initialize(this);
		setContentView(R.layout.addrule);
		Button addRule = (Button) findViewById(R.id.addRule);
		addRule.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TextView nickname = (TextView) findViewById(R.id.add_rule_nickname);
				TextView phone = (TextView) findViewById(R.id.add_rule_phone);
				UrgentEntry newRule = new UrgentEntry();
				newRule.setNickname(nickname.getText().toString());
				newRule.setPhoneNumber(phone.getText().toString());
				Future<Long> result = TrackManager.addRule(newRule);
				String text = "unknown";
				try {
					text = result.get().toString();
				} catch (Exception e) {
					e.printStackTrace();
				}
				Toast.makeText(getContext(), text, Toast.LENGTH_LONG);
				onBackPressed();
			}
		});
		
		ImageButton newTime = (ImageButton) findViewById(R.id.add_new_time);
		newTime.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				TimeRuleFactory.getInstance(UrgentCallNewRuleActivity.this);
				LinearLayout list = (LinearLayout) findViewById(R.id.add_time_rule_list);
				list.addView(TimeRuleFactory.createTimeRuleView());
				
				
			}
		});
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}

}
