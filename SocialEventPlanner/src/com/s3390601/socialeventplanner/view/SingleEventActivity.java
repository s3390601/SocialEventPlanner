package com.s3390601.socialeventplanner.view;

import com.s3390601.socialeventplanner.R;
import com.s3390601.socialeventplanner.R.id;
import com.s3390601.socialeventplanner.R.layout;
import com.s3390601.socialeventplanner.R.menu;
import com.s3390601.socialeventplanner.model.ConcreteEvent;
import com.s3390601.socialeventplanner.model.Event;
import com.s3390601.socialeventplanner.model.EventModel;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class SingleEventActivity extends Activity {

	private Event event;
	private TextView titleView,dateView,timeView,venueView,locationView,notesView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_event);
		
		getActionBar().setTitle(R.string.view_event);
		/* get event instance */
		event = EventModel.getSingletonInstance().findEventByID(
				getIntent().getStringExtra(EventModel.EVENT_ID_EXTRA));
		
		/* Assign views */
		titleView = (TextView) findViewById(R.id.title_view);
		dateView = (TextView) findViewById(R.id.date_view);
		timeView = (TextView) findViewById(R.id.time_view);
		venueView = (TextView) findViewById(R.id.venue_view);
		locationView = (TextView) findViewById(R.id.location_view);
		notesView = (TextView) findViewById(R.id.notes_view);
		
		/* set text fields */
		titleView.setText(event.getTitle());
		dateView.setText(event.getDateAsString());
		timeView.setText(event.getTimeAsString());
		venueView.setText(event.getVenue());
		notesView.setText(((ConcreteEvent) event).getNotes());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_view, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id)
		{
			
			default: break;
		}
		return super.onOptionsItemSelected(item);
	}
}
