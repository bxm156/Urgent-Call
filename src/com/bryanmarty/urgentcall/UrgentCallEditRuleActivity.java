package com.bryanmarty.urgentcall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class UrgentCallEditRuleActivity extends Activity {
	
	private static UrgentEntry entry = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.editrule);
		TrackManager.initialize(this);
		Intent intent = getIntent();
		setup(intent);
	}
	
	

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setup(intent);
	}

	private void setup(Intent intent) {
		TextView nickname = (TextView) findViewById(R.id.editNickname);
		TextView phone = (TextView) findViewById(R.id.editPhone);
		UrgentEntry data = intent.getParcelableExtra("UrgentEntry");
		entry = data;
		nickname.setText(data.getNickname());
		phone.setText(data.getPhoneNumber() );
		//nickname.invalidate();
		
		Button saveButton = (Button) findViewById(R.id.edit_save);
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(entry != null) {
					TextView nickname = (TextView) findViewById(R.id.editNickname);
					TextView phone = (TextView) findViewById(R.id.editPhone);
					entry.setNickname(nickname.getText().toString());
					entry.setPhoneNumber(phone.getText().toString());
					TrackManager.updateRule(entry);
					onBackPressed();
				}
			}
		});
	}

	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
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

}
