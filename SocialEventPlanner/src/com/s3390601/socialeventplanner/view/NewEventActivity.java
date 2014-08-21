package com.s3390601.socialeventplanner.view;

import java.util.Calendar;



import com.s3390601.socialeventplanner.R;
import com.s3390601.socialeventplanner.controller.NewEventController;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.Button;

public class NewEventActivity extends Activity
{
	private EditText newTextField;
	private EditText newVenueField;
	private DatePicker dPicker;
	private TimePicker tPicker;
	private Button pickAttendeesBtn;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);

		newTextField = (EditText) findViewById(R.id.new_title_field);
		newVenueField = (EditText) findViewById(R.id.new_venue_field);
		dPicker = (DatePicker) findViewById(R.id.date_picker);
		tPicker = (TimePicker) findViewById(R.id.time_picker);
		pickAttendeesBtn = (Button) findViewById(R.id.attendees_picker_button);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle(R.string.action_add);
        
        /* set listeners */
        pickAttendeesBtn.setOnClickListener(new OnClickListener()
        {
			@Override
			public void onClick(View v) {
				pickAttendeesAlertDialog().show();
			}
        });
        

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
			case android.R.id.home:
				Toast.makeText(getApplicationContext(), R.string.action_cancel, Toast.LENGTH_LONG);
				finish();
				break;
			case R.id.action_save:
				if (checkValid())
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
	
	private long getDateFromPicker()
	{

		Calendar cal = Calendar.getInstance();
		cal.set(dPicker.getYear(),dPicker.getMonth(),dPicker.getDayOfMonth(),
				tPicker.getCurrentHour(),tPicker.getCurrentMinute());
		
		return cal.getTimeInMillis();
	}
	
	/* Validate - check if title is left blank */
	public boolean checkValid()
	{
		if (newTextField.getText().toString().trim().length() == 0)
		{
			return false;
		}
		return true;
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
	
	/* method to create and return an alert dialog 
	 * to get Attendees from contacts */
	public AlertDialog pickAttendeesAlertDialog()
	{
		return new AlertDialog.Builder(NewEventActivity.this)
        .setIcon(R.drawable.ic_action_add_group)
        .setTitle(R.string.attendees)
        .create();
	}
	
	public void saveEvent()
	{
		/* if event has a venue */
		if (newVenueField.getText().toString().trim().length() == 0)
		{
			NewEventController.getSingletonInstance().addEvent(
					getDateFromPicker(),
					newTextField.getText().toString());
		}
		else
		{
			NewEventController.getSingletonInstance().addEvent(
					getDateFromPicker(),
					newTextField.getText().toString(), 
					newVenueField.getText().toString());
		}
	}
	
}
