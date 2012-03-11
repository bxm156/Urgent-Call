package com.bryanmarty.urgentcall;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Vibrator;
import android.view.GestureDetector;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public final class RuleViewFactory {

	/*public RuleView createRuleView(Context context, UrgentEntry data) {
		RuleView v = new RuleView(context, data);
		v.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 50));
		return v;
	}*/
	
	private static RuleViewFactory instance_ = null;
	private static Activity context_;
	private static OnGestureListener handler = new OnGestureListener() {
		
		@Override
		public boolean onSingleTapUp(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public void onShowPress(MotionEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
				float distanceY) {
			return false;
		}
		
		@Override
		public void onLongPress(MotionEvent e) {
			Vibrator vibe_ = (Vibrator) context_.getSystemService(Context.VIBRATOR_SERVICE);
			vibe_.vibrate(100);
			
		}
		
		@Override
		public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
				float velocityY) {
			// TODO Auto-generated method stub
			return false;
		}
		
		@Override
		public boolean onDown(MotionEvent e) {
			// TODO Auto-generated method stub
			return false;
		}
	};
	private static GestureDetector detector = new GestureDetector(context_, handler);
	private static OnTouchListener listener = new OnTouchListener() {
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			return detector.onTouchEvent(event);
		}
	};
	public static RuleViewFactory getInstance(Activity context) {
		if(instance_ == null) {
			instance_ = new RuleViewFactory(context);
		}
		return instance_;
	}
	
	public RuleViewFactory(Activity context) {
		context_ = context;
	}
	
	public LinearLayout createRuleView(UrgentEntry data) {
		
		LinearLayout row = new LinearLayout(context_);
		LayoutParams lp = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,0f);
		row.setLayoutParams(lp);
		
		row.setOrientation(LinearLayout.HORIZONTAL);
		
		CheckBox checkBox = new CheckBox(context_);
		LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,1f);
		checkBox.setText(data.getNickname());
		checkBox.setOnTouchListener(listener);
		
		ImageButton img = new ImageButton(context_);
		LayoutParams lp3 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT,0f);
		img.setImageBitmap(BitmapFactory.decodeResource(context_.getResources(), R.drawable.expander_ic_minimized));
		img.setBackgroundColor(Color.TRANSPARENT);
		img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(context_, UrgentCallEditRuleActivity.class);
				context_.startActivity(i);
				context_.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			}
		});
		
		
		row.addView(checkBox, lp2);
		row.addView(img,lp3);
		
		return row;
		
	}
	
	
	
	
	
	
}
