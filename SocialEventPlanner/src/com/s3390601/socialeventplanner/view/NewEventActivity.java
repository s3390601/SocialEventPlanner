package com.s3390601.socialeventplanner.view;

import java.util.Calendar;

import com.s3390601.socialeventplanner.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.DatePicker;
import android.widget.EditText;

public class NewEventActivity extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new);
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
	
	private long getDateFromPicker(DatePicker picker)
	{
		Calendar cal = Calendar.getInstance();
		cal.set(picker.getYear(),picker.getMonth(),picker.getDayOfMonth());
		
		return cal.getTimeInMillis();
	}
	
	private String getTitleFromField()
	{
		EditText newTextField = (EditText) findViewById(R.id.new_title_field);
		return newTextField.getText().toString();
	}
	
	private String getVenueFromField()
	{
		EditText newVenueField = (EditText) findViewById(R.id.new_venue_field);
		return newVenueField.getText().toString();
	}
}
