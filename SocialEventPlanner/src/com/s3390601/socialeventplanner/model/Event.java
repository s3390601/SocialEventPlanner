package com.s3390601.socialeventplanner.model;

import java.util.List;

public interface Event {

	public abstract long getDate();
	public abstract void setDate(long date);
	public abstract String getTitle();
	public abstract void setTitle(String title);
	public abstract String getVenue();
	public abstract void setVenue(String venue);
	public abstract List<String> getAttendees();
	public abstract void setAttendees(List<String> names);
	public abstract String getId();
	public abstract String getLocation();
	public abstract void setLocation(String lat, String lon);
	public String getTimeAsString();
	public String getDateAsString();
}
