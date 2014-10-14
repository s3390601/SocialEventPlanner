package com.s3390601.socialeventplanner.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class JSONParser
{
	private JSONObject root;
	public JSONParser(JSONObject object)
	{
		this.root=object;
	}
	
	public List<Integer> getTimes()
	{
		List<Integer> ret = new ArrayList<Integer>();
		try {
			if (!root.getString("status").equals("OK"))
			{
				Log.e("Parser","Root status: "+root.getString("status"));
				return null;
			}
			JSONArray rows = root.getJSONArray("rows");
			JSONArray elements = rows.getJSONObject(0).getJSONArray("elements");

			for(int i=0; i<elements.length(); i++)
			{
				JSONObject duration = elements.getJSONObject(i).getJSONObject("duration");
				ret.add(duration.getInt("value"));
			}
			return ret;
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
