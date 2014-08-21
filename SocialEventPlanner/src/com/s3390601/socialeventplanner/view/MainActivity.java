package com.s3390601.socialeventplanner.view;

import com.s3390601.socialeventplanner.R;

import android.app.ActionBar;
import android.app.ActionBar.OnNavigationListener;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;

	;
public class MainActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
        
		getActionBar().setNavigationMode(ActionBar.NAVIGATION_MODE_LIST);
        getActionBar().setDisplayHomeAsUpEnabled(false);
        getActionBar().setTitle(null);
        
        String[] viewlist = getResources().getStringArray(R.array.view_list);
        ArrayAdapter<String> views = new ArrayAdapter<String>(getBaseContext(), 
        		android.R.layout.simple_spinner_dropdown_item, 
        		viewlist);
        
        ActionBar.OnNavigationListener nl = new OnNavigationListener(){
            public boolean onNavigationItemSelected(int itemPosition, long itemId) 
            {

                return false;
            }
        };
 
        getActionBar().setListNavigationCallbacks(views,nl);
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
        		startActivity(myIntent);
    			break;
        default:
        		break;
        }
        return super.onOptionsItemSelected(item);
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
}