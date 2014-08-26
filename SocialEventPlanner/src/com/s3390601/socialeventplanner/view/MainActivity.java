package com.s3390601.socialeventplanner.view;

import com.s3390601.socialeventplanner.R;
import com.s3390601.socialeventplanner.model.ConcreteEvent;
import com.s3390601.socialeventplanner.model.Event;
import com.s3390601.socialeventplanner.model.EventModel;
import com.s3390601.socialeventplanner.view.model.EventAdapter;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.app.ListActivity;

	
public class MainActivity extends ListActivity {

	private ArrayAdapter<Event> eventAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
        testMethod();
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setTitle(null);
        
        /* Setting up Navigation Spinner on action bar */
        String[] viewlist = getResources().getStringArray(R.array.view_list);
        ArrayAdapter<String> views = new ArrayAdapter<String>(getBaseContext(), 
        		android.R.layout.simple_spinner_dropdown_item, 
        		viewlist);
        
        ActionBar.OnNavigationListener nl = new OnNavigationListener(){
            public boolean onNavigationItemSelected(int itemPosition, long itemId) 
            {
            	switch (itemPosition)
            	{
            		case 0:
            			Toast.makeText(getApplicationContext(), "ListView", Toast.LENGTH_SHORT).show();
            			break;
            		case 1:
            			Toast.makeText(getApplicationContext(), "MonthView", Toast.LENGTH_SHORT).show();
            			break;
            	}
                return false;
            }
        };
        getActionBar().setListNavigationCallbacks(views,nl);
        
        /* Populate list */
        eventAdapter = new EventAdapter(this,0, EventModel.getSingletonInstance().getAllEvents());
        setListAdapter(eventAdapter);
        
        
    }
    /* Called whenever we call invalidateOptionsMenu() */
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action buttons
        switch (item.getItemId())
        {
        case R.id.action_add:
        		Intent myIntent = new Intent(this,NewEventActivity.class);
        		startActivityForResult(myIntent, 0); 
    			break;
        default:
        		break;
        }
        return super.onOptionsItemSelected(item);
    }



    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		eventAdapter.clear();
		eventAdapter.addAll(EventModel.getSingletonInstance().getAllEvents());
		eventAdapter.notifyDataSetChanged();
	}
	@Override
    public void setTitle(CharSequence title) {
        getActionBar().setTitle(title);
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void testMethod()
	{
		EventModel e = EventModel.getSingletonInstance();
		e.addEvent(new ConcreteEvent(2000000000,"Party"));
		e.addEvent(new ConcreteEvent(1990000000,"More Party"));
		e.addEvent(new ConcreteEvent(2010000000,"Even More Party"));
	}
}