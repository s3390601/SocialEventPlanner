package com.s3390601.socialeventplanner.controller;


import com.s3390601.socialeventplanner.model.ConcreteEvent;
import com.s3390601.socialeventplanner.model.Event;
import com.s3390601.socialeventplanner.model.EventModel;


public class NewEventController
{
	private static NewEventController singletonInstance;
	
	protected NewEventController()
	{
	}
	
	public static NewEventController getSingletonInstance()
	{
		if(singletonInstance == null)
		{
			singletonInstance = new NewEventController();
		}
		return singletonInstance;
	}
	
	public void addEvent(long date,String title)
	{
		Event e = new ConcreteEvent(date,title);
		EventModel.getSingletonInstance().addEvent(e);
	}
	
	public void addEvent(long date,String title,String venue)
	{
		Event e = new ConcreteEvent(date,title,venue);
		EventModel.getSingletonInstance().addEvent(e);
	}
	
	public void addAttendee()
	{
		
	}
	
}
