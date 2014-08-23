package com.s3390601.socialeventplanner.controller;

import com.s3390601.socialeventplanner.model.EventModel;
import com.s3390601.socialeventplanner.view.SingleEventActivity;


import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class SingleEventController implements OnClickListener{

	private String eventID;
	private Activity activity;
	
	public SingleEventController(String eid, Activity a)
	{
		this.eventID=eid;
		this.activity=a;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(activity,SingleEventActivity.class);
		intent.putExtra(EventModel.EVENT_ID_EXTRA, eventID);
		activity.startActivityForResult(intent, 0);
	}

}
