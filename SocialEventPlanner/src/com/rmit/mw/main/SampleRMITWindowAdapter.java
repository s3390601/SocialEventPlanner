package com.rmit.mw.main;

import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap.InfoWindowAdapter;
import com.google.android.gms.maps.model.Marker;
import com.s3390601.socialeventplanner.R;
import com.s3390601.socialeventplanner.model.Event;
/**
 * Window Adapters are just like normal adapters, instead of describing how a list item looks
 * we are describing how a window for a map marker will look. 
 * I have provided a basic layout.
 * 
 * There is a slight difference between the two methods required for InfoWindowAdapter's but I didn't 
 * care for this simple purpose.
 * 
 * @author Matt Witherow
 *
 */
public class SampleRMITWindowAdapter implements InfoWindowAdapter {

	private Context mContext;
	private Map<Marker, Event> mModel;

	public SampleRMITWindowAdapter(Context context, Map<Marker, Event> map) {
		mContext = context;
		mModel = map;
	}

	@Override
	public View getInfoContents(Marker marker) {
		return getWindowFor(marker);
	}

	@Override
	public View getInfoWindow(Marker marker) {
		return getWindowFor(marker);
	}

	/**
	 * Returns the view to be shown for the popup window of a map marker
	 * 
	 * @param marker
	 * @return view
	 */
	private View getWindowFor(Marker marker) {

		LayoutInflater inflater = LayoutInflater.from(mContext);

		RelativeLayout view = (RelativeLayout) inflater.inflate(
				R.layout.layout_maps_info_window, null);
		Event event = mModel.get(marker);

		TextView name = (TextView) view.findViewById(R.id.txt_title);
		TextView place = (TextView) view.findViewById(R.id.txt_body);

		name.setText(event.getTitle());
		place.setText(event.getVenue());
		return view;
	}

}