package com.example.handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	private static Handler mHandler;

    public static Handler getHandler() {
		return mHandler;	
	}

    private static class MyHandler extends Handler{
    	MainActivity mainActivity;
    	
    	MyHandler(MainActivity activity){
			super();
    		this.mainActivity = activity;
    	}
    	
    	@Override
		public void handleMessage(Message msg) {
		
			String theMsgFromService = msg.getData().getString("msgFromService");
			TextView textView = (TextView) mainActivity.findViewById(R.id.messageTextView);
			textView.setText(theMsgFromService);
			
			super.handleMessage(msg);
		}
		
    }


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        mHandler = new MyHandler(MainActivity.this);

    }
   
    
    public void onStartService(View v){
      	Intent intent = new Intent(MainActivity.this, MyService.class);
    	startService(intent);
 	
    }
    
    public void onStopService(View v){
      	Intent intent = new Intent(MainActivity.this, MyService.class);
    	stopService(intent);
 	  	
    }

  
 
}
