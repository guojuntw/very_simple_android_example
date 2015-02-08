package com.example.handler;

import java.util.Date;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.TextView;


public class MainActivity extends Activity {
	
	private boolean runTask = true;
	private static Handler mHandler;
	
	static class MyHandler extends Handler{
		private MainActivity mainActivity;
		
		public MyHandler(MainActivity activity){
			mainActivity = activity;
		}
		
		@Override
		public void handleMessage(Message msg) {
			
			TextView textView = (TextView) mainActivity.findViewById(R.id.messageTextView);
			String theMsgFromTask = msg.getData().getString("messageFromTask");
			textView.setText(theMsgFromTask);
			
			super.handleMessage(msg);
		}
 	
    }

	class MyTesk implements Runnable{
	
		@Override
		public void run() {

			while(runTask){
				String messageStr = new Date().toString();
				Bundle bundle = new Bundle();
				bundle.putString("messageFromTask", messageStr);
			
				Message message = new Message();
				message.setData(bundle);
				
				mHandler.sendMessage(message);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
				
			}			
		}
		
	};


	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
			
        mHandler = new MyHandler(MainActivity.this);

    } 
    
    public void onStartService(View v){
    	Thread t = new Thread(new MyTesk());
    	t.start();
 	
    }
    
    public void onStopService(View v){
      	
    	runTask = false;
    }

  
 
}
