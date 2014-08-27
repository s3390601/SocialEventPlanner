package com.s3390601.socialeventplanner.view.model;

import java.util.ArrayList;
import java.util.Calendar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class GridViewAdapter extends BaseAdapter {

	private Activity activity;
	private ArrayList<String> days;
	private LayoutInflater inflater;
	private Calendar cal;
	
	public GridViewAdapter(Activity activity)
	{
		super();
		days=new ArrayList<String>();
		this.activity=activity;
		cal = Calendar.getInstance();
		for(int i=0; i<cal.getActualMaximum(Calendar.DAY_OF_MONTH); i++)
		{
			days.add(String.valueOf(i));
		}
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return days.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return days.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewContentHolder view;
		inflater = activity.getLayoutInflater();
		if(convertView == null)
		{
			view = new ViewContentHolder();
			convertView = inflater.inflate(android.R.layout.simple_list_item_1, null);
			
			view.textView = (TextView) convertView.findViewById(android.R.id.text1);
			convertView.setTag(view);
		}
		else
		{
			view = (ViewContentHolder) convertView.getTag();
		}
		view.textView.setText(days.get(position));
		return convertView;
	}
	

	public static class ViewContentHolder
	{
		public TextView textView;
	}

}
