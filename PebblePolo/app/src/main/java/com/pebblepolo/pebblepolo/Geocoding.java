package com.pebblepolo.pebblepolo;

import android.location.Location;

import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
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
	private Location getLocationFromAddress(String addr) {
		URLConnection connection = new URL("http://maps.googleapis.com/maps/api/geocode/json?" + URLEncoder.encode(addr, "UTF-8")).openConnection();
		connection.setRequestProperty("Accept-Charset", "UTF-8");

		try {
			InputStream response = connection.getInputStream();

			Scanner s = new Scanner(response);
			try {
				String responseBody = s.useDelimiter("\\A").next();
				// TODO: DO IT HORATIU
			} catch(Exception e) {
				return null;
			}
		} catch (IOException e) {
			return null;
		}


	}
}
