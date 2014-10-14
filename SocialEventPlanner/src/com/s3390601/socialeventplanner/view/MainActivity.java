package com.s3390601.socialeventplanner.view;


import com.s3390601.socialeventplanner.R;
import com.s3390601.socialeventplanner.model.ConcreteEvent;
import com.s3390601.socialeventplanner.model.Event;
import com.s3390601.socialeventplanner.model.EventModel;
import com.s3390601.socialeventplanner.service.AlarmReceiver;
import com.s3390601.socialeventplanner.view.model.EventAdapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.NumberPicker;
import android.app.AlertDialog;
import android.app.ListActivity;

	
public class MainActivity extends ListActivity {

	private ArrayAdapter<Event> eventAdapter;
	AlarmReceiver alarm = new AlarmReceiver();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
        //testMethod();
        getActionBar().setDisplayHomeAsUpEnabled(false);
        
        /* Populate list */
        eventAdapter = new EventAdapter(this,0, EventModel.getSingletonInstance(this).getAllEvents());
        setListAdapter(eventAdapter);
        alarm.setAlarm(this);
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
        		
        case R.id.action_threshold:
        		LayoutInflater inflater = (LayoutInflater)
            	getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        		View npView = inflater.inflate(R.layout.threshold_picker, null);
        		NumberPicker np = (NumberPicker) npView.findViewById(R.id.threshold_picker);
        		np.setMaxValue(60);
        		np.setMinValue(0);
        		np.setValue(15);
        		np.setWrapSelectorWheel(true);
        		AlertDialog.Builder builder = new AlertDialog.Builder(this);
        		builder.setTitle(R.string.action_threshold)
        			.setIcon(R.drawable.ic_action_edit)
        			.setView(npView)
        			.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							
						}
					})
					.setNegativeButton(android.R.string.cancel, null)
					.show();
        		
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
		eventAdapter.addAll(EventModel.getSingletonInstance(this).getAllEvents());
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
		EventModel e = EventModel.getSingletonInstance(this);
		Event test = new ConcreteEvent(2000000000,"Birthday");
		test.setVenue("RMIT University");
		test.setLocation("150", "250");
		e.addEvent(test);
	}
	@Override
	protected void onResume() {
		super.onResume();
		eventAdapter.notifyDataSetChanged();
	}
	@Override
	protected void onPause() {
		
		super.onPause();
	}
	
	
	
	
}