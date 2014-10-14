package com.s3390601.socialeventplanner.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;

import org.json.JSONObject;

import com.s3390601.socialeventplanner.R;
import com.s3390601.socialeventplanner.model.Event;
import com.s3390601.socialeventplanner.model.EventModel;
import com.s3390601.socialeventplanner.utils.DistanceMatrixJSONFetcher;
import com.s3390601.socialeventplanner.utils.JSONParser;
import com.s3390601.socialeventplanner.view.SingleEventActivity;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;

public class AlarmService extends IntentService {

	public AlarmService() 
	{
		super("AlarmService");
	}

	private NotificationManager nm;
	private int threshold;
	
	@Override
	protected void onHandleIntent(Intent intent) 
	{
			Log.i("Service","Service called");
	    /* Set threshold */
		SharedPreferences sp = getSharedPreferences(getString(R.string.preferences_file), MODE_PRIVATE);
		threshold = sp.getInt(getString(R.string.threshold), 15);
		Log.i("Service","Threshold: "+threshold);
		/* Get Location */
		List<Integer> times;
		LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		Criteria crit = new Criteria();
		crit.setAccuracy(Criteria.ACCURACY_FINE);
		Location currentLoc = lm.getLastKnownLocation(lm.getBestProvider(crit, true));
		List<String> locs= new ArrayList<String>();
		if (currentLoc == null)
		{
			Log.e("Service","Service failed: Location NULL");
			return;
		}
		String currentLocString = currentLoc.getLatitude()+" "+currentLoc.getLongitude();
		
			Log.i("Service","Current Location "+currentLocString);
		locs.add(currentLocString);
		/* Get all events */
		for (Event e : EventModel.getSingletonInstance(this).getAllEvents())
		{
			if(e.getLocation() != null)
			{
				/* Add all future events to list */
				Calendar now = Calendar.getInstance();
				Calendar event = Calendar.getInstance();
				event.setTimeInMillis(e.getDate());
				if(e.getNotified())
				{
					continue;
				}
				if(event.after(now))
				{
					locs.add(e.getLocation());
				}
			}
		}
			Log.i("Service","Locations: "+locs.toString());
		/* If no events are added */
		if (locs.size() < 2)
		{
			Log.e("Service","Service failed: No event location set");
			return;
		}
		/* Query Distance matrix API */
		/* For each Event, get travel time from current loc */
		DistanceMatrixJSONFetcher dm = new DistanceMatrixJSONFetcher();
		try 
		{
			JSONObject object = dm.execute(locs.toArray(new String[locs.size()])).get();
			JSONParser parser = new JSONParser(object);
			times = parser.getTimes();
			if (times == null)
			{
				Log.e("Service","Error with JSON Object");
				return;
			}
			/* If Currenttime+traveltime+threshold > Event start time*/
			int i=0;
			for (Event e : EventModel.getSingletonInstance(this).getAllEvents())
			{
				/* To prevent future events which have already been notified
				 * to be notified again
				 */
				if(e.getNotified())
				{
					continue;
				}
				Calendar now = Calendar.getInstance();
				Calendar event = Calendar.getInstance();
				event.setTimeInMillis(e.getDate());
				/* To prevent past events from gettin notified again */
				if(event.before(now))
				{
					continue;
				}
				now.add(Calendar.SECOND,times.get(i));
				now.add(Calendar.MINUTE,threshold);
				
				
				
				if(now.after(event))
				{
					/* Notify user */
					nm = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
					Intent notifIntent = new Intent(this,SingleEventActivity.class);
					notifIntent.putExtra(EventModel.EVENT_ID_EXTRA, e.getId());
					PendingIntent pIntent = PendingIntent.getActivity(this, 0, notifIntent, Intent.FLAG_ACTIVITY_NEW_TASK);
					Notification n = new Notification.Builder(this)
						.setContentTitle("Event Starting soon")
						.setContentText(e.getTitle())
						.setSmallIcon(R.drawable.ic_stat_name)
						.setAutoCancel(true)
						.setContentIntent(pIntent)
						.setTicker(e.getTitle()+" starting soon")
						.setLargeIcon((((BitmapDrawable)this.getResources().getDrawable(R.drawable.ic_stat_name)).getBitmap()))
						.build();
					nm.notify(i, n);
					e.setNotified(true);
				}
				i++;
			}	
		} catch (InterruptedException e1) 
		{
			Log.i("Service","InterruptedException");
			e1.printStackTrace();
		} catch (ExecutionException e1) 
		{
			Log.i("Service","ExcecutionException");
			e1.printStackTrace();
		}
	}

}
