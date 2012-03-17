package com.bryanmarty.urgentcall;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class UrgentCallViewRulesActivity extends Activity {

	public Context getContext() {
		return this;
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.rules);
		
		//Show rule list
		RuleViewFactory factory = RuleViewFactory.getInstance(this);
        LinearLayout layout = (LinearLayout) findViewById(R.id.rulesLinearLayout);
        for(int x = 0; x < 20; x++) {
	        UrgentEntry test = new UrgentEntry();
	        test.setNickname("My Test " + x);
	        LinearLayout item = factory.createRuleView(test);
	        layout.addView(item);
        }
        layout.invalidate();
        
        Button addRule = (Button) findViewById(R.id.addRule);
        addRule.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getContext(), UrgentCallNewRuleActivity.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);			
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
		// TODO Auto-generated method stub
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}
	

}
