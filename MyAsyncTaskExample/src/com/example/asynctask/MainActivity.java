package com.example.asynctask;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity {

	private ProgressBar progressbar;
	private TextView msgTextView;
	private Long mTimeUsage;
	private MyBackgroundtask mBackgroundTask;

	private enum MyTaskStatus {
		STOP, START, CANCEL, FINISHED
	}

	private static final Integer MAX_TASK_COUNTER = 10;
	private static final String tag = "MainActivity";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		progressbar = (ProgressBar) findViewById(R.id.progressBar1);
		msgTextView = (TextView) findViewById(R.id.textViewMsg);

		updateStatus(MyTaskStatus.STOP);
	}

	public void doBackgroundTask(View v) {
		mBackgroundTask = new MyBackgroundtask();
		mBackgroundTask.execute(MAX_TASK_COUNTER);

	}

	public void doCancelBackgroundtask(View v) {
		mBackgroundTask.cancel(false);

	}

	class MyBackgroundtask extends AsyncTask<Integer, Integer, Long> {
		int progress;

		@Override
		protected Long doInBackground(Integer... params) {
			int maxCount = params[0];
			progressbar.setMax(maxCount);
			long currentTimeMili = System.currentTimeMillis();

			for (int i = 0; i <= maxCount; i++) {
				progress += 1;

				// to publish the progress
				publishProgress(progress);

				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return System.currentTimeMillis() - currentTimeMili;
		}

		@Override
		protected void onCancelled() {
			updateStatus(MyTaskStatus.CANCEL);
			super.onCancelled();
		}

		@Override
		protected void onProgressUpdate(Integer... values) {
			Log.i(tag, "The progress: " + values[0]);
			progressbar.setProgress(values[0]);

		}

		@Override
		protected void onPostExecute(Long result) {
			mTimeUsage = result;
			updateStatus(MyTaskStatus.FINISHED);

			super.onPostExecute(result);
		}

		@Override
		protected void onPreExecute() {
			updateStatus(MyTaskStatus.START);
		}

	}

	private void updateStatus(MyTaskStatus status) {
		switch (status) {
		case STOP:
			msgTextView.setText("The background task stoped.");
			break;

		case START:
			msgTextView.setText("The background task has started.");
			break;

		case CANCEL:
			msgTextView.setText("The background task has been calceled.");
			break;
		case FINISHED:
			msgTextView.setText("The background task has finished. \nIt took "
					+ mTimeUsage + " miliseconds");
			break;

		default:
			break;
		}
	}

}
