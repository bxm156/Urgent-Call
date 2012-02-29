package com.bryanmarty.urgentcall;

import android.media.AudioManager;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.util.Log;

public class UrgentCallThread extends Thread {

	private UrgentCallService listener_;
	private boolean shutdown = false;
	private TelephonyManager tManager_;
	private AudioManager aManager_;
	private Vibrator vib_;

	public UrgentCallThread(UrgentCallService listener, TelephonyManager tManager, AudioManager audioManager,Vibrator vibrator) {
		super();
		listener_ = listener;
		tManager_ = tManager;
		aManager_ = audioManager;
		vib_ = vibrator;
	}
	
	@Override
	public void run() {
		while(true) {
			synchronized(this) {
				try {
					if(shutdown) {
						break;
					}
					if(tManager_ != null && tManager_.getCallState() == TelephonyManager.CALL_STATE_RINGING && aManager_.getRingerMode() == AudioManager.RINGER_MODE_SILENT) {
						vib_.vibrate(1000);
						Log.i("VIB","VIB");
					}
					wait(2000);
					if(shutdown) {
						break;
					}
				} catch (InterruptedException e) {
					
				}
			}
		}
	}
	
	public void shutdown() {
		synchronized(this) {
			try {
				shutdown = true;
				this.interrupt();
				this.join();
			} catch (InterruptedException e) {
				
			} finally {
				
			}
		}
	}
	
	

}
