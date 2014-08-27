package com.s3390601.socialeventplanner.view;

import com.s3390601.socialeventplanner.R;
import com.s3390601.socialeventplanner.model.ConcreteEvent;
import com.s3390601.socialeventplanner.model.Event;
import com.s3390601.socialeventplanner.model.EventModel;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SingleEventActivity extends Activity {

	private Event event;
	private TextView titleView,dateView,timeView,venueView,locationView,notesView;
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_single_event);
		
		getActionBar().setTitle(R.string.view_event);
		getActionBar().setDisplayHomeAsUpEnabled(true);
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
		listView = (ListView) findViewById(R.id.attendees_listView);
		loadValues();
		
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
			case R.id.action_delete:
				confirmDelete();
				break;
			case R.id.action_edit:
				Intent myIntent = new Intent(this,NewEventActivity.class);
				myIntent.putExtra(EventModel.EVENT_ID_EXTRA, event.getId());
				startActivityForResult(myIntent, 0);
			case android.R.id.home:
				finish();
			default: 
				break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void confirmDelete()
	{
		new AlertDialog.Builder(SingleEventActivity.this)
	        .setIcon(R.drawable.ic_action_warning)
	        .setTitle(R.string.confirm_delete)
	        .setPositiveButton(android.R.string.yes, 
	        		new DialogInterface.OnClickListener()
	        		{
						@Override
						public void onClick(DialogInterface dialog, int which) {
							if (EventModel.getSingletonInstance().delEvent(event))
							{
								Toast.makeText(getApplicationContext(), R.string.action_event_delete, Toast.LENGTH_SHORT).show();
								finish();
							}
						}
	        		}
	        )
	        .setNegativeButton(android.R.string.no,null)
	        .create().show();
	}
	
	public void loadValues()
	{
		/* set text fields */
		titleView.setText(event.getTitle());
		dateView.setText(event.getDateAsString());
		timeView.setText(event.getTimeAsString());
		venueView.setText(event.getVenue());
		notesView.setText(((ConcreteEvent) event).getNotes());
		locationView.setText(event.getLocation());
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this, 
                android.R.layout.simple_list_item_1,
                event.getAttendees());
		listView.setAdapter(arrayAdapter);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		loadValues();
	}
	
	
}
