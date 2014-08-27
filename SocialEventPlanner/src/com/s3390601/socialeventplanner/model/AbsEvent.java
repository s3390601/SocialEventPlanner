package com.s3390601.socialeventplanner.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.provider.ContactsContract;

public abstract class AbsEvent implements Event
{
	/* date is a long which stores both date and time */
	private long date;
	private String id;
	private String title;
	private String venue;
	private String location;
	private List<String> attendees;
	
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
	
	public void removeAttendee(ContactsContract attendee)
	{
		this.attendees.remove(attendee);
	}
	
	public String getId() {
		return id;
	}
	
	public String getLocation()
	{
		return this.location;
	}
	
	public void setLocation(String lat, String lon)
	{
		this.location=lat+" "+lon;
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
	
}
