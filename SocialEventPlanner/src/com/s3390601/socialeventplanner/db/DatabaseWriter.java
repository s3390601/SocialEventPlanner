package com.s3390601.socialeventplanner.db;

import com.s3390601.socialeventplanner.model.ConcreteEvent;
import com.s3390601.socialeventplanner.model.Event;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;

public class DatabaseWriter extends AsyncTask<Event, Void, Void> {

	private Context context;
	
	public DatabaseWriter(Context c)
	{
		this.context = c;
	}
	@Override
	protected Void doInBackground(Event... params) {
		MySQLiteOpenHelper helper = new MySQLiteOpenHelper(context);
		SQLiteDatabase db = helper.getWritableDatabase();
		for(Event e : params)
			{
				ContentValues values = new ContentValues();
				values.put(MySQLiteOpenHelper.COLUMN_ID, e.getId());
				values.put(MySQLiteOpenHelper.COLUMN_TITLE,e.getTitle());
				values.put(MySQLiteOpenHelper.COLUMN_VENUE,e.getVenue());
				values.put(MySQLiteOpenHelper.COLUMN_LOCATION,e.getLocation());
				values.put(MySQLiteOpenHelper.COLUMN_DATE, e.getDate());
				values.put(MySQLiteOpenHelper.COLUMN_NOTES,((ConcreteEvent) e).getNotes());
				StringBuilder sb = new StringBuilder();
				String separator ="";
				for(String name: e.getAttendees())
				{
					sb.append(separator);
					sb.append(name);
					separator=",";
				}
				values.put(MySQLiteOpenHelper.COLUMN_ATTENDEES,sb.toString());
				db.insertWithOnConflict(MySQLiteOpenHelper.EVENTS_TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_REPLACE);
			}
		return null;
	}
	@Override
	protected void onPostExecute(Void result) {
		//Toast.makeText(context, "DB Write Success", Toast.LENGTH_LONG).show();
		super.onPostExecute(result);
	}

}
