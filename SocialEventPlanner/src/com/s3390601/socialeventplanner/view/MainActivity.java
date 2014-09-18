package com.s3390601.socialeventplanner.view;

import com.s3390601.socialeventplanner.R;
import com.s3390601.socialeventplanner.model.ConcreteEvent;
import com.s3390601.socialeventplanner.model.Event;
import com.s3390601.socialeventplanner.model.EventModel;
import com.s3390601.socialeventplanner.view.model.EventAdapter;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.app.ListActivity;

	
public class MainActivity extends ListActivity {

	private ArrayAdapter<Event> eventAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
        //testMethod();
        getActionBar().setDisplayHomeAsUpEnabled(false);
  
        
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
    			
        case R.id.action_month_view:
        		Intent intent = new Intent(this,MonthActivity.class);
        		startActivity(intent);
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
		getMenuInflater().inflate(R.menu.listview_menu, menu);
		return true;
	}
	
	public void testMethod()
	{
		EventModel e = EventModel.getSingletonInstance();
		Event test = new ConcreteEvent(2000000000,"Birthday");
		test.setVenue("RMIT University");
		test.setLocation("150", "250");
		e.addEvent(test);
	}
}