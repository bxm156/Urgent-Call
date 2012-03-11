package com.bryanmarty.urgentcall;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class UrgentCallEditRuleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		setContentView(R.layout.editrule);
		
	}
	
	

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
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
