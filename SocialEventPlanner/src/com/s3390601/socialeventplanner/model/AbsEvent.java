package com.s3390601.socialeventplanner.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import android.location.Location;
import android.provider.ContactsContract;

public abstract class AbsEvent implements Event
{
	private String id;
	private long date;
	private String title;
	private String venue;
	private Location location;
	private List<ContactsContract> attendees;
	
	public AbsEvent()
	{
		this.id = UUID.randomUUID().toString();
		attendees = new ArrayList<ContactsContract>();
	}
	
	public AbsEvent(long date, String title)
	{
		this();
		this.date=date;
		this.title=title;
	}
	
	public AbsEvent(long date, String title, String venue) {
		this();
		this.date = date;
		this.title = title;
		this.venue = venue;
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
	
	public List<ContactsContract> getAttendees() {
		return attendees;
	}
	
	public void addAttendee(ContactsContract attendee) {
		this.attendees.add(attendee);
	}
	
	public void removeAttendee(ContactsContract attendee)
	{
		this.attendees.remove(attendee);
	}
	
	public String getId() {
		return id;
	}
	
	public Location getLocation()
	{
		return this.location;
	}
	
	public void setLocation(Location loc)
	{
		this.location=loc;
	}
	
	
}
