package com.example.handler;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button button;
	TextView textView;
	Handler mHandler;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		button = (Button) findViewById(R.id.button1);
		textView = (TextView) findViewById(R.id.textView1);

		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				cuntdown();

			}
		});

	}

	private void cuntdown() {
		final int count = 20;

		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {

				for (int i = count; i >= 0; i--) {
					final int value = i;
					
					 new Handler(Looper.getMainLooper()).post(new Runnable() {
					//textView.post(new Runnable() {

						@Override
						public void run() {
							textView.setText(String.valueOf(value));
						}
					});

					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			}
		});

		t.start();

	}
}
