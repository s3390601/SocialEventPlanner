package com.s3390601.socialeventplanner.view;

import java.util.Calendar;

import com.s3390601.socialeventplanner.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

public class NewEventActivity extends Activity
{
	private EditText newTextField;
	private EditText newVenueField;
	private DatePicker dpicker;
	private TimePicker tpicker;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);
		
		newTextField = (EditText) findViewById(R.id.new_title_field);
		newVenueField = (EditText) findViewById(R.id.new_venue_field);
		dpicker = (DatePicker) findViewById(R.id.date_picker);
		tpicker = (TimePicker) findViewById(R.id.time_picker);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);
        getActionBar().setTitle(R.string.action_add);
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
				finish();
				break;
			case R.id.action_save:
				finish();
				break;
		}
		
		return super.onOptionsItemSelected(item);
	}
	
	private long getDateFromPicker()
	{
		Calendar cal = Calendar.getInstance();
		cal.set(dpicker.getYear(),dpicker.getMonth(),dpicker.getDayOfMonth(),
				tpicker.getCurrentHour(),tpicker.getCurrentMinute());

		return cal.getTimeInMillis();
	}
	
}
