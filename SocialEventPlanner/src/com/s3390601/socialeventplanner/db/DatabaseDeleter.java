package com.s3390601.socialeventplanner.db;

import com.s3390601.socialeventplanner.model.Event;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

public class DatabaseDeleter extends AsyncTask<Event, Void, Void> {

	private Context context;
	public DatabaseDeleter(Context c)
	{
		context = c;
	}
	@Override
	protected Void doInBackground(Event... params) {
		MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();

		for(Event e: params)
		{
			db.execSQL("Delete from "+ MySQLiteOpenHelper.EVENTS_TABLE_NAME + " where "
					+MySQLiteOpenHelper.COLUMN_ID+"='"+e.getId()+"';");
		}
		
		return null;
	}

}
