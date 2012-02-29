package com.bryanmarty.urgentcall;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Process;
import android.os.Vibrator;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class UrgentCallService extends Service {
	
	public static final int TERMINATE = 0;
	
	private static TelephonyManager telephonyManager;
	private static Vibrator vibrator;
	private static AudioManager audioManager;

	private UrgentCallThread thread_;

	@Override
	public void onCreate() {
		Log.d("SERVICE", "CREATE");
		//Setup listeners
		telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
		vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
		
		
		//Setup thread
		thread_ = new UrgentCallThread(this,telephonyManager,audioManager,vibrator);
		thread_.start();
		super.onCreate();
	}
	
	 @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("LocalService", "Received start id " + startId + ": " + intent);
        // We want this service to continue running until it is explicitly
        // stopped, so return sticky.
        return START_STICKY;
    }
	 
		
	/**
     * Class for clients to access.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with
     * IPC.
     */
    public class LocalBinder extends Binder {
        UrgentCallService getService() {
            return UrgentCallService.this;
        }
    }
    
    // This is the object that receives interactions from clients.  See
    // RemoteService for a more complete example.
    private final IBinder mBinder = new LocalBinder();
    
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

	@Override
	public void onDestroy() {
		Log.d("SERVICE", "DESTROY");
		thread_.shutdown();
		Toast.makeText(this, "Service Done", Toast.LENGTH_SHORT);
		super.onDestroy();
	}

}
