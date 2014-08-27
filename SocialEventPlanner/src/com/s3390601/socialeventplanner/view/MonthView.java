package com.s3390601.socialeventplanner.view;



import com.s3390601.socialeventplanner.R;
import com.s3390601.socialeventplanner.view.model.GridViewAdapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.GridView;

public class MonthView extends Activity {

	private GridView grid;

	private GridViewAdapter gridViewAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_month_view);
		grid = (GridView) findViewById(R.id.gridView1);
		
		gridViewAdapter = new GridViewAdapter(this);
		grid.setAdapter(gridViewAdapter);
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
			case R.id.action_add:
				Intent myIntent = new Intent(this,NewEventActivity.class);
				startActivityForResult(myIntent, 0); 
				break;
		}
		return super.onOptionsItemSelected(item);
	}
    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }


}
