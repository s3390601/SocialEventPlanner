package com.s3390601.socialeventplanner.db;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import com.s3390601.socialeventplanner.model.ConcreteEvent;
import com.s3390601.socialeventplanner.model.Event;
import com.s3390601.socialeventplanner.model.EventModel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

public class DatabaseReader extends AsyncTask<Void, Void, List<Event>> {

	private Context context;
	private String[] allColumns={MySQLiteOpenHelper.COLUMN_ID, MySQLiteOpenHelper.COLUMN_TITLE, 
			MySQLiteOpenHelper.COLUMN_VENUE, MySQLiteOpenHelper.COLUMN_LOCATION, 
			MySQLiteOpenHelper.COLUMN_DATE, MySQLiteOpenHelper.COLUMN_NOTES, 
			MySQLiteOpenHelper.COLUMN_ATTENDEES };
	
	private MySQLiteOpenHelper helper;
	private SQLiteDatabase db;
	
	List<Event> events;
	public DatabaseReader(Context c)
	{
		this.context=c;
	}
	
	@Override
	protected void onPostExecute(List<Event> result) {
		for(Event e : events)
		{
			EventModel.getSingletonInstance(context).addEvent(e);
		}
		//Toast.makeText(context, "DB Read Success", Toast.LENGTH_LONG).show();
		super.onPostExecute(result);
	}

	@Override
	protected List<Event> doInBackground(Void... params) {
		helper = new MySQLiteOpenHelper(context);
		db = helper.getReadableDatabase();
		Cursor cursor = db.query(MySQLiteOpenHelper.EVENTS_TABLE_NAME, allColumns, null, null, null, null, null);
		cursor.moveToFirst();
		events = new ArrayList<Event>();
		while(!cursor.isAfterLast())
		{
			Event e = cursorToEvent(cursor);
			events.add(e);
			cursor.moveToNext();
		}
		return events;
	}
	
	private Event cursorToEvent(Cursor c)
	{
		Event e = new ConcreteEvent(c.getLong(4),c.getString(1));
		e.setId(c.getString(0));
		e.setVenue(c.getString(2));
		e.setLocation(c.getString(3));
		((ConcreteEvent)e).setNotes(c.getString(5));
		List<String> names = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(c.getString(6),"-");
		while(st.hasMoreElements())
		{
			names.add((String) st.nextElement());
		}
		e.setAttendees(names);
		
		return e;
	}

}
