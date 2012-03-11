package com.bryanmarty.urgentcall;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

public class UrgentCallViewRulesActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		RuleViewFactory factory = RuleViewFactory.getInstance(this);
		 setContentView(R.layout.rules);
	        LinearLayout layout = (LinearLayout) findViewById(R.id.rulesLinearLayout);
	        for(int x = 0; x < 20; x++) {
		        UrgentEntry test = new UrgentEntry();
		        test.setNickname("My Test " + x);
		        LinearLayout item = factory.createRuleView(test);
		        layout.addView(item);
	        }
	        layout.invalidate();
		super.onCreate(savedInstanceState);
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
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}
	

}
