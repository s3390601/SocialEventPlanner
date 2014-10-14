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

import android.content.Context;

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
	private DatabaseReader reader;
	protected EventModel(Context c)
	{
		context = c;
		reader = new DatabaseReader(c);
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

}
