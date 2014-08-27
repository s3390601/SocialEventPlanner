package com.s3390601.socialeventplanner.view.model;

import java.util.ArrayList;

import com.s3390601.socialeventplanner.R;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridViewAdapter extends BaseAdapter {

	private Context context;
	private final ArrayList<String> days;
	private final int today;
	public GridViewAdapter(Context context,ArrayList<String> days, int today) {
		super();
		this.context = context;
		this.days = days;
		this.today = today;
	}
 
	public View getView(int position, View convertView, ViewGroup parent) {
 
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View gridView;
 
		if (convertView == null) {
 
			gridView = new View(context);
 
			gridView = inflater.inflate(R.layout.day, null);
 
			TextView textView = (TextView) gridView.findViewById(R.id.grid_item_label);
			textView.setText(days.get(position));
			if (position <= 6)
			{
				textView.setTextColor(context.getResources().getColor(android.R.color.black));
			}
			if (position > 6)
			{
				textView.setTextColor(context.getResources().getColor(android.R.color.holo_blue_light));
			}
			if (days.get(position).equals(String.valueOf(today)))
			{
				textView.setTextColor(Color.parseColor("#FF0000"));
			}
		} else {
			gridView = (View) convertView;
		}
 
		return gridView;
	}
 
	@Override
	public int getCount() {
		return days.size();
	}
 
	@Override
	public Object getItem(int position) {
		return null;
	}
 
	@Override
	public long getItemId(int position) {
		return 0;
	}
 
}
