package com.s3390601.socialeventplanner.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class ConnectivityReceiver extends BroadcastReceiver {

	AlarmReceiver alarm = new AlarmReceiver();
	@Override
	public void onReceive(Context context, Intent intent) {
		ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo info = cm.getActiveNetworkInfo();
		boolean isConnected = info != null && info.isConnectedOrConnecting();
		if(isConnected)
		{
			Log.i("ConnectivityReceiver","Connectivity Gained");
			alarm.setAlarm(context);
		}else
		{
			Log.i("ConnectivityReceiver","Connectivity Lost");
			alarm.cancelAlarm(context);
		}
		
	}

}
