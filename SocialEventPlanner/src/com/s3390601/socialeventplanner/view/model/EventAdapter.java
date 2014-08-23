package com.s3390601.socialeventplanner.view.model;

import java.util.List;

import com.s3390601.socialeventplanner.R;
import com.s3390601.socialeventplanner.controller.SingleEventController;
import com.s3390601.socialeventplanner.model.Event;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class EventAdapter extends ArrayAdapter<Event>
{
	private Activity activity;
	private LayoutInflater inflater = (LayoutInflater) getContext()
	         .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	
	 private static class ViewContentHolder implements OnClickListener
	   {
	      private TextView titleText;
	      private TextView dateText;
	      private TextView venueText;

		@Override
		public void onClick(View v) {

		}
	   }

	/* Constructor */
	public EventAdapter(Activity a, int textViewResourceId,
			List<Event> objects) {
		super(a, textViewResourceId, objects);
		// TODO Auto-generated constructor stub
		this.activity = a;
	}
	
	public View getView(int position, View inflatedView, ViewGroup parent)
	{
		Event e = getItem(position);
		if (inflatedView == null)
		{
			ViewContentHolder holder = new ViewContentHolder();
			
			inflatedView = inflater.inflate(R.layout.row, parent,false);
			holder.titleText = (TextView) inflatedView.findViewById(R.id.rowTitle);
			holder.dateText = (TextView) inflatedView.findViewById(R.id.rowDate);
			holder.venueText = (TextView) inflatedView.findViewById(R.id.rowVenue);
			inflatedView.setTag(holder);	
		}
		ViewContentHolder holder = (ViewContentHolder) inflatedView.getTag();
		
		holder.titleText.setText(e.getTitle());
		holder.dateText.setText(e.getDateAsString()+" ("+e.getTimeAsString()+")");
		holder.venueText.setText(e.getVenue());
		inflatedView.setOnClickListener(new SingleEventController(e.getId(),this.activity));
		return inflatedView;
	}
	
	

}
