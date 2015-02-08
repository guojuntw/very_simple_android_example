package com.example.handler;

import java.util.Date;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class MyService extends Service {
	Handler mMainActivityHandler = MainActivity.getHandler();
	boolean mIsRunClock = true;

	public MyService() {

		Bundle mmBondle = new Bundle();
		mmBondle.putString("msgFromService",
				"Hi! This message comes from service.");

		Message mmMessage = new Message();
		mmMessage.setData(mmBondle);

		mMainActivityHandler.sendMessage(mmMessage);

		Thread t = new Thread(new Runnable() {
			Bundle mmmBondle = new Bundle();

			@Override
			public void run() {
				while (mIsRunClock) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {}
					
					mmmBondle.putString("msgFromService", (new Date()).toString());
					Message mmmMessage = new Message();
					mmmMessage.setData(mmmBondle);

					mMainActivityHandler.sendMessage(mmmMessage);

				}

			}
		});

		t.start();
	}

	
	
	@Override
	public void onDestroy() {
		mIsRunClock = false; 
		
		super.onDestroy();
	}



	@Override
	public IBinder onBind(Intent intent) {
		// TODO: Return the communication channel to the service.
		throw new UnsupportedOperationException("Not yet implemented");
	}
	
	
}
