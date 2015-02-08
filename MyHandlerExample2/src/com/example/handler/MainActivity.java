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


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        
        mHandler = new Handler(){

			@Override
			public void handleMessage(Message msg) {
				
				TextView textView = (TextView) findViewById(R.id.messageTextView);
				String theMsgFromService = msg.getData().getString("msgFromService");
				textView.setText(theMsgFromService);
				
				super.handleMessage(msg);
			}
     	
        };
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
