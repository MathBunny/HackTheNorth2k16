package com.pebblepolo.pebblepolo;

import android.location.Location;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.URL;
import java.io.InputStream;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Apaar on 2016-09-17.
 */
public class Geocoding {
	private static final String API_KEY = "AIzaSyCE-DhbyzL-6on2HYxns_lmxV_kiN1zWlU";
	public static Location getLocationFromAddress(String addr) {
		try {
			URLConnection connection = new URL("http://maps.googleapis.com/maps/api/geocode/json?" + URLEncoder.encode(addr, "UTF-8") + "&" + URLEncoder.encode("key=" + API_KEY, "UTF-8")).openConnection();
			connection.setRequestProperty("Accept-Charset", "UTF-8");

			try {
				InputStream response = connection.getInputStream();

				Scanner s = new Scanner(response);
				try {
					String responseBody = s.useDelimiter("\\A").next();
					JSONObject obj = new JSONObject(responseBody);

					if (!obj.getString("status").equals("OK"))
						return null;

					// get the first result
					JSONObject res = obj.getJSONArray("results").getJSONObject(0);
					Log.d("Test", res.getString("formatted_address"));
					JSONObject loc = res.getJSONObject("geometry").getJSONObject("location");

					Log.d("Test", loc.getDouble("lat") + " vs " + loc.getDouble("lng"));

					Location l = new Location("");

					l.setLongitude(loc.getDouble("lng"));
					l.setLatitude(loc.getDouble("lat"));

					return l;
				} catch (Exception e) {
					return null;
				}
			} catch (IOException e) {
				return null;
			}
		} catch(Exception e) {
			return null;
		}
	}
}
