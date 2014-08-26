package com.s3390601.socialeventplanner.model;

public class ConcreteEvent extends AbsEvent{

	private String notes;
	public ConcreteEvent()
	{
		super();
	}
	
	public ConcreteEvent(long date, String title)
	{
		super(date,title);
	}
	
	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
}
