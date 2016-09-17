package com.pebblepolo.pebblepolo;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.location.LocationListener;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    LocationListener locationListener = new LocationListener() {
		    @Override
		    public void onStatusChanged(String provider, int status, Bundle extras) {
		    }

		    @Override
		    public void onProviderEnabled(String provider) {
		    }

		    @Override
		    public void onProviderDisabled(String provider) {
		    }

		    @Override
		    public void onLocationChanged(Location location) {
			    if (LocationData.destination != null) {
				    LocationData.current = location;
			    }
		    }
	    };

	    LocationManager locationManager = (LocationManager)getSystemService(LOCATION_SERVICE);

	    try {
		    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, Constants.LOCATION_REFRESH_TIME, Constants.LOCATION_REFRESH_DISTANCE, locationListener);
	    } catch(SecurityException e) {
			Log.e("Error", Log.getStackTraceString(e));
		}

		Thread t = new Thread(new FrequencyUpdater(this));
		t.start();
    }

    public void selectDestination(View v) {
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
    }
}