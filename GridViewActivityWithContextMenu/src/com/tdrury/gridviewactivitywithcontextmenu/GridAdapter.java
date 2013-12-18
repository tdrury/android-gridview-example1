package com.tdrury.gridviewactivitywithcontextmenu;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class GridAdapter extends BaseAdapter {

	private Context context;
	private List<String> elements;
	private int layoutId;
	private int selectedPosition = -1;
	
	public GridAdapter(Context context, int layoutId, List<String> elements) {
		this.context = context;
		this.layoutId = layoutId;
		this.elements = elements;
	}

	@Override
	public int getCount() {
		return elements.size();
	}

	@Override
	public Object getItem(int position) {
		return elements.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = inflater.inflate(layoutId, null);
		}
		String t = elements.get(position);
		TextView taskView = (TextView)convertView.findViewById(R.id.grid_element_text);
		taskView.setText(t);
		return convertView;
	}

	public void setSelectedPosition(int selectedPosition) {
		this.selectedPosition = selectedPosition;
	}
	
	public int getSelectedPosition() {
		return selectedPosition;
	}
	
	public String getSelectedTask() {
		if (selectedPosition >= 0) return elements.get(selectedPosition);
		return null;
	}

}
