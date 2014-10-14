package com.s3390601.socialeventplanner.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.util.Log;

/* This class is an Async task which takes in user's location, and location for all events
 * it queries distance matrix API and parses the JSON. It then returns the values of time needed 
 * in seconds to get there for each event
 */
public class DistanceMatrixJSONFetcher extends AsyncTask<String,Void,JSONObject>
{
	private final static String URL = "http://maps.googleapis.com/maps/api/distancematrix/json?"; 
	private static String MODE = "driving";
	protected StringBuilder sb;
	
	@Override
	protected JSONObject doInBackground(String... params) {
		/* Create Request URL */
		sb = new StringBuilder();
		sb.append(URL);
		sb.append("origins=");
		sb.append(params[0]);
		sb.append("&destinations=");
		for(int i=1; i<params.length; i++)
		{
			sb.append(params[i]);
			if(i < (params.length-1))
				sb.append("|");
		}
		sb.append("&mode=");
		sb.append(MODE);
		HttpURLConnection connection = null;
		Log.i("DistanceMatrix","Request URL: "+sb.toString().replaceAll(" ", "%20"));
		try
		{
			URL url = new URL(sb.toString().replaceAll(" ", "%20"));
			connection = (HttpURLConnection) url.openConnection();
	        connection.setRequestMethod("GET");
	        connection.setRequestProperty("Accept", "text/html");
	        connection.setReadTimeout(7000);
	        connection.setConnectTimeout(7000);
	        int statusCode = connection.getResponseCode();
	        if (statusCode != HttpURLConnection.HTTP_OK)
	        {
	        	return null;
	        }
	        BufferedReader br = new BufferedReader(new InputStreamReader(
	                connection.getInputStream()));
	        String line;
	        sb = new StringBuilder();
	        while((line = br.readLine()) != null)
	        {
	        	sb.append(line);
	        }
		}
		catch(Exception e)
		{
		}
		finally
		{
			if(connection != null)
				connection.disconnect();
		}
		try {
			return new JSONObject(sb.toString());
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	protected void onPostExecute(JSONObject result) {
		Log.i("DistanceMatrixJSONFetcher", result.toString());
		super.onPostExecute(result);
	}
	
	
	
}
