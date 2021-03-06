package com.pebblepolo.pebblepolo;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.location.Location;
import android.os.Vibrator;
import android.util.Log;

/**
 * Created by Apaar on 2016-09-17.
 */
public class FrequencyUpdater implements Runnable {
	private Activity app;

	public FrequencyUpdater(Activity app) {
		this.app = app;
	}

	@Override
	public void run() {
		while(true) {
			Vibrator vibrator = (Vibrator)app.getSystemService(Context.VIBRATOR_SERVICE);

			if(LocationData.destination != null && LocationData.start != null && LocationData.current != null) {
				Location l = LocationData.start;
				float initialDistance = l.distanceTo(LocationData.destination);
				float dist = LocationData.current.distanceTo(LocationData.destination);

				long[] pattern = new long[] {(long)(Constants.MAX_VIBRATION_DELAY_DURATION * (dist / initialDistance)), 100};
				vibrator.vibrate(pattern, 0);
			} else {
				vibrator.cancel();
			}

			try {
				Thread.sleep(Constants.VIBRATION_FREQUENCY_UPDATE_DELAY);
			} catch(InterruptedException e) {
				Log.e("Error", Log.getStackTraceString(e));
			}
		}
	}
}
