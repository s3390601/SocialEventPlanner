package com.s3390601.socialeventplanner.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteOpenHelper extends SQLiteOpenHelper {

	private final static String DATABASE_NAME = "EventsDB";
	private final static int DATABASE_VERSION = 1;
	
	/* TABLE */
	public final static String EVENTS_TABLE_NAME="EVENTS_TABLE";
	
	/* COLUMNS */
	public final static String COLUMN_ID = "ID";
	public final static String COLUMN_TITLE="TITLE";
	public final static String COLUMN_VENUE="VENUE";
	public final static String COLUMN_LOCATION="LOCATION";
	public final static String COLUMN_DATE="DATE";
	public final static String COLUMN_NOTES="NOTES";
	public final static String COLUMN_ATTENDEES="ATTENDEES";
	
	/*Creating table */
	private final static String EVENTS_TABLE_CREATE=
			"CREATE TABLE "+EVENTS_TABLE_NAME+" ( " +
					COLUMN_ID +" STRING PRIMARY KEY,"+
					COLUMN_TITLE + " TEXT, "+
					COLUMN_VENUE + " TEXT, "+
					COLUMN_LOCATION + " TEXT, "+
					COLUMN_DATE + " INT, "+
					COLUMN_NOTES + " TEXT," +
					COLUMN_ATTENDEES + " TEXT);";
					
	public MySQLiteOpenHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(EVENTS_TABLE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + EVENTS_TABLE_NAME);
	    onCreate(db);
	}

}
