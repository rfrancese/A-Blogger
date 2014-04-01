package a_blogger.activity;


import com.example.a_blogger.R;

import a_blogger.components.auth.Auth;
import a_blogger.components.storage.LocalStorage;
import a_blogger.model.User;
import android.os.Bundle;
import android.os.StrictMode;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	public static MainActivity ma;
	
	public final static int REQUEST_ACTIVITY_LOGIN = 0;
	public final static int REQUEST_ACTIVITY_HOME  = 1;
	
	public TextView v;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		Log.v("hhh","hhh main create");
		ma = this;
		
		LocalStorage.removeAll();
		
		if(Auth.check()){
			callHome();
		} else { 
			callLogin();
		}
	}
	
	public void callLogin(){
		Intent i = new Intent(this, LoginActivity.class);
		//i.putExtra(...);    
		startActivityForResult(i, REQUEST_ACTIVITY_LOGIN);
	}
	
	public void callHome(){
		Intent i = new Intent(this, PostListActivity.class);
		//i.putExtra(...);    
		startActivityForResult(i, REQUEST_ACTIVITY_HOME);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
	    switch(requestCode) {
        	case REQUEST_ACTIVITY_LOGIN:
        		if (resultCode == Activity.RESULT_OK) { 
        			callHome();
        		}
	            //use data.getExtra(...) to retrieve the returned data
	        	break;
        	case REQUEST_ACTIVITY_HOME:
        		finish();
    			break;
	    }
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
}
