package com.s3390601.socialeventplanner.controller;

import com.s3390601.socialeventplanner.view.NewEventActivity;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class EditController implements OnClickListener{

	private String eventID;
	private Activity activity;
	
	public EditController(String eid, Activity a)
	{
		this.eventID=eid;
		this.activity=a;
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(activity,NewEventActivity.class);
		//intent.putExtra();
		//activity.startActivityForResult(intent, 0);
		
	}

}
