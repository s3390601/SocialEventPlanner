package com.s3390601.socialeventplanner.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SortedMap; 
import java.util.TreeMap;

import com.s3390601.socialeventplanner.db.DatabaseDeleter;
import com.s3390601.socialeventplanner.db.DatabaseReader;
import com.s3390601.socialeventplanner.db.DatabaseWriter;
import com.s3390601.socialeventplanner.db.MySQLiteOpenHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

public class EventModel
{
	public static final String EVENT_ID_EXTRA = "eventIDExtra";

	/*stores all events
	 * I used a single sorted map rather than 2 maps like the workshop
	 * because there won't be too many events, therefore, we can
	 * get eventID by a foreach loop each time ID is needed.*/
	private SortedMap<String, Event> sortedMap = new TreeMap<String,Event>();
	private static EventModel singletonInstance;
	
	private Context context;
	protected EventModel(Context c)
	{
		context = c;
		DatabaseReader reader = new DatabaseReader(c);
		reader.execute();
	}
	

	public void addEvent(Event e)
	{
		sortedMap.put(e.getId(), e);
		DatabaseWriter writer = new DatabaseWriter(context);
		writer.execute(e);
	}
	
	public boolean delEvent(Event e)
	{
		DatabaseDeleter deleter = new DatabaseDeleter(context);
		deleter.execute(e);
		return sortedMap.remove(e.getId()) != null;
	}
	
	public boolean editEvent(Event e)
	{
		DatabaseWriter writer = new DatabaseWriter(context);
		writer.execute(e);
		return true;
	}
	
	public static EventModel getSingletonInstance(Context c)
	{
		if (singletonInstance == null)
		{
			singletonInstance = new EventModel(c);
		}
		return singletonInstance;
	}
	
	public Event findEventByID(String id)
	{
		for (Event e : sortedMap.values())
		{
			if (e.getId().equals(id))
			{
				return e;
			}
		}
		return null;
	}
	
	public static String getDateString(long date)
	{
		Calendar cal=Calendar.getInstance();
		cal.setTimeInMillis(date);
	      
		return DateFormat.getDateInstance().format(cal.getTime());
	}
	
	public List<Event> getAllEvents()
	{
		return new ArrayList<Event>(sortedMap.values());
	}
	
	
	/* Writes all events from memory to db */
//	public void writeToDB()
//	{
//		db.delete(MySQLiteOpenHelper.EVENTS_TABLE_NAME, null, null);
//		for(Event e : sortedMap.values())
//		{
//			ContentValues values = new ContentValues();
//			values.put(MySQLiteOpenHelper.COLUMN_ID, e.getId());
//			values.put(MySQLiteOpenHelper.COLUMN_TITLE,e.getTitle());
//			values.put(MySQLiteOpenHelper.COLUMN_VENUE,e.getVenue());
//			values.put(MySQLiteOpenHelper.COLUMN_LOCATION,e.getLocation());
//			values.put(MySQLiteOpenHelper.COLUMN_DATE, e.getDate());
//			values.put(MySQLiteOpenHelper.COLUMN_NOTES,((ConcreteEvent) e).getNotes());
//			db.insert(MySQLiteOpenHelper.EVENTS_TABLE_NAME, null, values);
//		}
//	}
	
	/* Reads all events from DB to memory 
	 * runs on UI thread, used for testing*/
//	public void readFromDB()
//	{
//		Cursor cursor = db.query(MySQLiteOpenHelper.EVENTS_TABLE_NAME, allColumns, null, null, null, null, null);
//		cursor.moveToFirst();
//		while(!cursor.isAfterLast())
//		{
//			Event e = cursorToEvent(cursor);
//			addEvent(e);
//			cursor.moveToNext();
//		}
//	}
	
//	private Event cursorToEvent(Cursor c)
//	{
//		Event e = new ConcreteEvent(c.getLong(4),c.getString(1));
//		e.setId(c.getString(0));
//		e.setVenue(c.getString(2));
//		e.setLocation(c.getString(3));
//		((ConcreteEvent)e).setNotes(c.getString(5));
//		return e;
//	}

}
