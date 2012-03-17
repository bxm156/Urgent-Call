package com.bryanmarty.urgentcall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class UrgentCallEditRuleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.editrule);
		Intent intent = getIntent();
		TextView nickname = (TextView) findViewById(R.id.editNickname);
		UrgentEntry data = intent.getParcelableExtra("UrgentEntry");
		nickname.setText(data.getNickname());
		nickname.invalidate();
	}
	
	

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		TextView nickname = (TextView) findViewById(R.id.editNickname);
		UrgentEntry data = intent.getParcelableExtra("UrgentEntry");
		nickname.setText(data.getNickname());
		nickname.invalidate();
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
