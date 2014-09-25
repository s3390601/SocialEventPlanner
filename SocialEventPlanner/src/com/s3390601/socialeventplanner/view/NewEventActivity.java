package com.s3390601.socialeventplanner.view;

import java.util.ArrayList;
import java.util.Calendar;



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
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Button;

public class NewEventActivity extends Activity
{
	public static final int CHANGED = 1;
	private EditText newTextField;
	private EditText newVenueField;
	private EditText newNotesField;
	private EditText newLatField;
	private EditText newLonField;
	private DatePicker dPicker;
	private TimePicker tPicker;
	private Button pickAttendeesBtn;
	private Event event;
	private ArrayList<String> names;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);

		newTextField = (EditText) findViewById(R.id.new_title_field);
		newVenueField = (EditText) findViewById(R.id.new_venue_field);
		newNotesField = (EditText) findViewById(R.id.new_notes_field);
		dPicker = (DatePicker) findViewById(R.id.date_picker);
		tPicker = (TimePicker) findViewById(R.id.time_picker);
		pickAttendeesBtn = (Button) findViewById(R.id.attendees_picker_button);
		newLatField = (EditText) findViewById(R.id.new_latitude_field);
		newLonField = (EditText) findViewById(R.id.new_longitude_field);
		
		final Intent myIntent = new Intent(this,ContactChooser.class);
		getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setHomeButtonEnabled(false);
        getActionBar().setTitle(R.string.action_add);
        
        names = new ArrayList<String>();
        /* set listeners */
        pickAttendeesBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				startActivityForResult(myIntent, 0);
			}
        });
        
        /*set time picker to 24 hour */
        tPicker.setIs24HourView(true);
        
        /*Check if editing existing event*/
        event = EventModel.getSingletonInstance(this).findEventByID(
				getIntent().getStringExtra(EventModel.EVENT_ID_EXTRA));
        if(event != null)
        {
        	newTextField.setText(event.getTitle());
        	newVenueField.setText(event.getVenue());
        	newNotesField.setText(((ConcreteEvent) event).getNotes());
        	Calendar cal = Calendar.getInstance();
        	cal.setTimeInMillis(event.getDate());
        	dPicker.init(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DAY_OF_MONTH), null);
        	tPicker.setCurrentHour(cal.get(Calendar.HOUR_OF_DAY));
        	tPicker.setCurrentMinute(cal.get(Calendar.MINUTE));
        	names = (ArrayList<String>) event.getAttendees();
        	if (event.getLocation() != null)
        	{
        		String location[] = event.getLocation().split(" ");
        		newLatField.setText(location[0]);
        		newLonField.setText(location[1]);
        	}
        }
      

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_new, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		switch(id)
		{
			case R.id.action_save:
				/* checks if title is not blank */
				if (newTextField.getText().toString().trim().length() > 0)
				{
					saveEvent();
					finish();
				}
				else
				{
					blankTitleAlertDialog().show();
				}
				break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	public long getDateFromPicker()
	{

		Calendar cal = Calendar.getInstance();
		cal.set(dPicker.getYear(),dPicker.getMonth(),dPicker.getDayOfMonth(),
				tPicker.getCurrentHour(),tPicker.getCurrentMinute());
		return cal.getTimeInMillis();
	}
	

	/* method to create and return an alert dialog 
	 * in case of title field is blank */
	public AlertDialog blankTitleAlertDialog()
	{
		return new AlertDialog.Builder(NewEventActivity.this)
        .setIcon(R.drawable.ic_action_warning)
        .setTitle(R.string.blankTitle)
        .setPositiveButton(android.R.string.ok,
              new DialogInterface.OnClickListener() {
                 public void onClick(DialogInterface dialog,
                       int whichButton)
                 {}
              })
        .create();
	}
	
	
	public void saveEvent()
	{
		/* In case of new event */
		if (event == null)
		{
			event = new ConcreteEvent(getDateFromPicker(),
					newTextField.getText().toString());
			EventModel.getSingletonInstance(this).addEvent(event);

			/* Set Venue */
			if (newVenueField.getText().toString().trim().length() > 0)
			{
				event.setVenue(newVenueField.getText().toString());
				
			}
			/* Set Notes */
			if (newNotesField.getText().toString().trim().length() > 0)
			{
				((ConcreteEvent)event).setNotes(newNotesField.getText().toString());
			}
			/* Add Attendees */
			if (names != null)
			{
				event.setAttendees(names);
			}
			/* Set location */
			if ((newLatField.getText().toString().trim().length() > 0) 
				&& (newLonField.getText().toString().trim().length() > 0))
			{
				event.setLocation(newLatField.getText().toString(), newLonField.getText().toString());
			}
		}
		/* In case of editing existing event */
		else
		{
			event.setTitle(newTextField.getText().toString());
			event.setVenue(newVenueField.getText().toString());
			((ConcreteEvent)event).setNotes(newNotesField.getText().toString());
			event.setDate(getDateFromPicker());
			event.setAttendees(names);
			event.setLocation(newLatField.getText().toString(), newLonField.getText().toString());
		}
		setResult(NewEventActivity.CHANGED);
	}
	
	protected void onActivityResult(int requestCode, int resultCode,Intent data)
	{
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK)
		{
			Bundle bundle = data.getExtras();
			names = bundle.getStringArrayList("NAMES");
		}
	}
	
	
}
