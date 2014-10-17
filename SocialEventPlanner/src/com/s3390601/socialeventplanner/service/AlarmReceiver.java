package com.s3390601.socialeventplanner.service;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.util.Log;

public class AlarmReceiver extends WakefulBroadcastReceiver{

	private AlarmManager am;
	private PendingIntent alarmIntent;
	
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		Log.i("AlarmReceiver", "Broadcast received");
		Intent service = new Intent(context,AlarmService.class);
		startWakefulService(context,service);
	}
	
	public void setAlarm(Context context)
	{
		if(am == null)
		{
			Log.i("AlarmReceiver", "Alarm Set");
			am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
			Intent i = new Intent(context, AlarmReceiver.class);
			alarmIntent = PendingIntent.getBroadcast(context, 0, i, 0);
			am.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 1000*5*60, alarmIntent);
		}
	}
	
	public void cancelAlarm(Context context)
	{
		if(am != null)
		{
			Log.i("AlarmReceiver", "Alarm Cancelled");
			am.cancel(alarmIntent);
			am=null;
		}
	}
	
	

}
