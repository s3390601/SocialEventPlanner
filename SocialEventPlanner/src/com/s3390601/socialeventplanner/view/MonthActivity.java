package com.s3390601.socialeventplanner.view;

import java.util.ArrayList;
import java.util.Calendar;

import com.s3390601.socialeventplanner.R;
import com.s3390601.socialeventplanner.view.model.GridViewAdapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.GridView;

public class MonthActivity extends Activity {

	private GridView gridView;
	private ArrayList<String> days;
	private Calendar cal;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_month_view);
		getActionBar().setTitle(R.string.title_activity_month_view);
		getActionBar().setDisplayHomeAsUpEnabled(true);
		gridView = (GridView) findViewById(R.id.gridView1);
		cal = Calendar.getInstance();
		days = new ArrayList<String>();
		/*Set first row (Sunday - Saturday) */
		for (String d : getResources().getStringArray(R.array.days_in_week))
		{
			days.add(d);
		}
		
		/* Set empty spaces */
		cal.set(Calendar.DAY_OF_MONTH,Calendar.getInstance().getActualMinimum(Calendar.DAY_OF_MONTH));
		for (int i=1; i < cal.get(Calendar.DAY_OF_WEEK); i++)
		{
			days.add(" ");
		}
		/* Set number of days in month */
		for(int i=1; i<= cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++)
		{
			days.add(String.valueOf(i));
		}
		cal = Calendar.getInstance();
		gridView.setAdapter(new GridViewAdapter(this,days,
				cal.get(Calendar.DAY_OF_MONTH)));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.empty, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch (id)
		{
			case android.R.id.home:
				finish();
		}
		return super.onOptionsItemSelected(item);
	}
}
