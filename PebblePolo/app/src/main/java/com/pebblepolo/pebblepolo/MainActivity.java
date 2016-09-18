package com.pebblepolo.pebblepolo;

import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import android.location.LocationListener;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.model.LatLng;

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
					if(LocationData.start == null) {
						LocationData.start = location;
					}
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
		PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();

		try {
			startActivityForResult(builder.build(this), Constants.PLACE_PICKER_REQUEST_ID);
		} catch(GooglePlayServicesNotAvailableException e) {
			Log.e("Destination", "Play services not available");
		} catch(GooglePlayServicesRepairableException e) {
			Log.e("Destination", "Play services repairable, apparently");
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == Constants.PLACE_PICKER_REQUEST_ID) {
			if (resultCode == RESULT_OK) {
				Place place = PlacePicker.getPlace(getApplicationContext(), data);

				if(place != null) {
					LatLng latLng = place.getLatLng();

					LocationData.destination = new Location("");

					LocationData.destination.setLatitude(latLng.latitude);
					LocationData.destination.setLongitude(latLng.longitude);
				}
			}
		}
	}
}