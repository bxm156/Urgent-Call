package com.bryanmarty.urgentcall;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.os.Vibrator;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;

public class RuleView extends View implements OnGestureListener {

	Paint textPaint, linePaint;
	Context context_ = null;
	UrgentEntry data_ = null;
	Vibrator vibe_ = null;
	GestureDetector gestureDetector_;
	Bitmap editArrow = null;
	
	private static final int ARROW_PADDING_RIGHT = 10;
	
	public RuleView(Context context, UrgentEntry entry) {
		super(context);
		context_ = context;
		data_ = entry;
		vibe_ = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		init();
		gestureDetector_ = new GestureDetector(context_, this);
	}
	
	private void init() {
		 textPaint = new Paint();
		 textPaint.setColor(Color.BLACK);
		 textPaint.setStyle(Style.FILL);
		 textPaint.setTextSize(40);
		 textPaint.setAntiAlias(true);
		 linePaint = new Paint();
		 linePaint.setColor(Color.BLACK);
		 editArrow = BitmapFactory.decodeResource(getResources(), R.drawable.expander_ic_minimized);
		 
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		canvas.drawColor(Color.LTGRAY);
		canvas.drawText(data_.getNickname(), 10, 0 - textPaint.ascent(), textPaint);
		canvas.drawBitmap(editArrow, getWidth()-editArrow.getWidth()-ARROW_PADDING_RIGHT, 0, null);
		canvas.drawLine(0,0,480,0,linePaint);
	}
	
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		return gestureDetector_.onTouchEvent(event);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
	}
	
	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);
		
		if(specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			//TODO: What to do?
			result = specSize;
		}
		return result;
	}
	
	private int measureHeight(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);
		
		if(specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			//TODO: What to do?
			result = 75;
		}
		return result;
	}

	@Override
	public boolean onDown(MotionEvent e) {
		
		if(e.getX() > getWidth()-editArrow.getWidth()) {
		//Edit
		}
		
		textPaint.setColor(Color.RED);
		invalidate();
		return true;
	}

	@Override
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void onLongPress(MotionEvent e) {
		textPaint.setColor(Color.MAGENTA);
		invalidate();
		vibe_.vibrate(100);
		return;
		
	}

	@Override
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void onShowPress(MotionEvent e) {
		return;
	}

	@Override
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
}
