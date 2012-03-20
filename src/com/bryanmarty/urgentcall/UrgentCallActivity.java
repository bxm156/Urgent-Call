package com.bryanmarty.urgentcall;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class UrgentCallActivity extends Activity {
	
	private UrgentCallService boundService;
	private boolean mIsBound;
	private ServiceConnection mConnection = new ServiceConnection() {
		
	    public void onServiceConnected(ComponentName className, IBinder service) {
	        // This is called when the connection with the service has been
	        // established, giving us the service object we can use to
	        // interact with the service.  Because we have bound to a explicit
	        // service that we know is running in our own process, we can
	        // cast its IBinder to a concrete class and directly access it.
	        boundService = ((UrgentCallService.LocalBinder)service).getService();

	        // Tell the user about this for our demo.
	        Toast.makeText(UrgentCallActivity.this, "Service Connected",
	                Toast.LENGTH_SHORT).show();
	       
	    }

	    public void onServiceDisconnected(ComponentName className) {
	        // This is called when the connection with the service has been
	        // unexpectedly disconnected -- that is, its process crashed.
	        // Because it is running in our same process, we should never
	        // see this happen.
	        boundService = null;
	        Toast.makeText(UrgentCallActivity.this, "Service disconnected unexpectedly",
	                Toast.LENGTH_SHORT).show();
	    }
	};
	
	void doBindService() {
	    // Establish a connection with the service.  We use an explicit
	    // class name because we want a specific service implementation that
	    // we know will be running in our own process (and thus won't be
	    // supporting component replacement by other applications).
	    bindService(new Intent(this, 
	            UrgentCallService.class), mConnection, Context.BIND_AUTO_CREATE);
	    mIsBound = true;
	}
	
	void doUnbindService() {
	    if (mIsBound) {
	        // Detach our existing connection.
	        unbindService(mConnection);
	        mIsBound = false;
	    }
	}
	
	public Context getContext() {
		return this;
	}
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
         Button b = (Button) findViewById(R.id.editRulesButton);
        b.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(getContext(),UrgentCallViewRulesActivity.class);
				startActivity(i);
				overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
				
			}
		});
        TrackManager.initialize(this);
        doBindService();
    }

	@Override
	protected void onDestroy() {
		Toast.makeText(this, "Destroying", Toast.LENGTH_SHORT);
		doUnbindService();
		TrackManager.shutdown();
	}
    
    
    
}