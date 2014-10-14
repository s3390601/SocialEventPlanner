package com.s3390601.socialeventplanner.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class AbsEvent implements Event
{
	/* date is a long which stores both date and time */
	private long date;
	private String id;
	private String title;
	private String venue;
	private String location;
	private List<String> attendees;
	private boolean notified = false;
	
	public AbsEvent()
	{
		this.id = UUID.randomUUID().toString();
		attendees = new ArrayList<String>();
	}
	
	public AbsEvent(long date, String title)
	{
		this();
		this.date=date;
		this.title=title;
	}

	public long getDate() {
		return date;
	}
	
	public void setDate(long date) {
		this.date = date;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getVenue() {
		return venue;
	}
	
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public List<String> getAttendees() {
		return attendees;
	}
	
	public void setAttendees(List<String> names) {
		this.attendees = names;
	}
	
	public String getId() {
		return id;
	}
	
	@Override
	public void setId(String id) {
		this.id = id;		
	}
	
	public String getLocation()
	{
		return this.location;
	}
	
	public void setLocation(String lat, String lon)
	{
		this.location=lat+" "+lon;
	}
	
	public void setLocation(String loc)
	{
		this.location=loc;
	}
	
	public String getDateAsString()
	{
		DateFormat df = new SimpleDateFormat("dd MMMM yyyy");
		return df.format(this.date);
	}
	
	public String getTimeAsString()
	{
		DateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(this.date);
	}

	public boolean getNotified()
	{
		return this.notified;
	}
	
	public void setNotified(boolean bool)
	{
		this.notified = bool;
	}
}
