package com.example.otherapp;



import android.support.v7.app.ActionBarActivity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

/*
 * This class will test the LocalBoundService.class in Service project.
 */
public class MainActivity extends ActionBarActivity {
	public static final String TAG = "MainActivity";
	private ServiceConnection localBoundServiceConnection;
	private IBinder binder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void starBoundServiceInAnotherApp(View view){
    	Intent intent = new Intent();
    	intent.setAction("com.example.services.binders.START_LOCAL_BOUND_SERVICE");
    	bindService(intent, localBoundServiceConnection = new LocalBoundServiceConnection(), Context.BIND_AUTO_CREATE);
    }
    
    public void stopBoundServiceInAnotherAppUsingUnBindSerice(View view){
//    	Intent intent = new Intent();
//    	intent.setAction("com.example.services.binders.START_LOCAL_BOUND_SERVICE");
//    	startService(intent);
    	unbindService(localBoundServiceConnection);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    class LocalBoundServiceConnection implements ServiceConnection{

		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
		    Log.d(TAG,"onServiceConnected"+":"+service.getClass().getName());
		    //boundService = ((LocalServiceBinder)service).getService();
		}

		@Override
		public void onServiceDisconnected(ComponentName name) {
			Log.d(TAG,"onServiceDisconnected");
		}
		
	}
}
