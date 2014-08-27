	/* Loosely based on
	 * http://stackoverflow.com/questions/9386783/how-to-get-multiple-selected-items-from-a-listactivity-in-android
	 * and
	 * http://stackoverflow.com/questions/12413159/android-contact-picker-with-checkbox/ 
	 */
package com.s3390601.socialeventplanner.view;

import java.util.ArrayList;

import com.s3390601.socialeventplanner.R;


import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ListAdapter;

public class ContactChooser extends ListActivity
{
	private TextView label;
	private ListView listView;
	private ListAdapter listAdapter;
	private ArrayList<String> names;
	
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_view);
		
		label=(TextView) findViewById(R.id.upcoming_label);
		label.setText(R.string.contact_chooser);
		
		Cursor cursor = getContacts();
		
		startManagingCursor(cursor);
		listAdapter = new SimpleCursorAdapter(
				this, android.R.layout.simple_list_item_multiple_choice,
				cursor,new String[] { ContactsContract.Contacts.DISPLAY_NAME },
                new int[] { android.R.id.text1 });
		
		setListAdapter(listAdapter);
		listView = getListView();        
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        /* Select All */
        for (int k=0; k<listView.getCount(); k++)
        {
        	listView.setItemChecked(k, true);
        }
	}
    
	private Cursor getContacts() {
		
        Uri uri = ContactsContract.Contacts.CONTENT_URI;
        String[] projection = new String[] { ContactsContract.Contacts._ID,
                ContactsContract.Contacts.DISPLAY_NAME };
        String selection = ContactsContract.Contacts.HAS_PHONE_NUMBER + " = '"
                + ("1") + "'";
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.Contacts.DISPLAY_NAME
                + " COLLATE LOCALIZED ASC";

        return getContentResolver().query(uri, projection, selection, selectionArgs,
                sortOrder);
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
				names = new ArrayList<String>();
				SparseBooleanArray checked = listView.getCheckedItemPositions();

	            if (checked != null) {
	                for (int k = 0; k < checked.size(); k++) {
	                     if (checked.valueAt(k)) {
	                          String name =
	                                 ((Cursor)listView.getAdapter().getItem(k)).getString(1);
	                            names.add(name);
	                        }
	                }
	            }

				Intent i = new Intent();
				Bundle b = new Bundle();
				b.putStringArrayList("NAMES", names);
				setResult(RESULT_OK,i);
				i.putExtras(b);
				finish();
				break;			
		}
		return super.onOptionsItemSelected(item);
	}
}
