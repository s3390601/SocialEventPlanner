package com.s3390601.socialeventplanner.model;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.SortedMap; 
import java.util.TreeMap;

public class EventModel
{
	public static final String EVENT_ID_EXTRA = "eventIDExtra";

	/*stores all events
	 * I used a single sorted map rather than 2 maps like the workshop
	 * because there won't be too many events, therefore, we can
	 * get eventID by a foreach loop each time ID is needed.*/
	private SortedMap<Long, Event> sortedMap = new TreeMap<Long,Event>();
	private static EventModel singletonInstance;
	
	protected EventModel()
	{
		
	}
	
	public void addEvent(Event e)
	{
		sortedMap.put(e.getDate(), e);
	}
	
	public boolean delEvent(Event e)
	{
		return sortedMap.remove(e.getDate()) != null;
	}
	
	public static EventModel getSingletonInstance()
	{
		if (singletonInstance == null)
		{
			singletonInstance = new EventModel();
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
