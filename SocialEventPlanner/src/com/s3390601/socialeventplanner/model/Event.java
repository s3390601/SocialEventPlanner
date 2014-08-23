package com.s3390601.socialeventplanner.model;

import java.util.List;

import android.location.Location;
import android.provider.ContactsContract;

public interface Event {

	public abstract long getDate();
	public abstract void setDate(long date);
	public abstract String getTitle();
	public abstract void setTitle(String title);
	public abstract String getVenue();
	public abstract void setVenue(String venue);
	public abstract List<ContactsContract> getAttendees();
	public abstract void addAttendee(ContactsContract attendee);
	public abstract String getId();
	public abstract Location getLocation();
	public abstract void setLocation(Location location);
	public String getTimeAsString();
	public String getDateAsString();
}
