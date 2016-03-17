package com.paliokimotoramano.alohatourguide;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

/*
 * Adapter class to read in OahuEvents into ours ListView
 */
public class OahuEventListAdapter extends ArrayAdapter<OahuEvent> {

	private List<OahuEvent> items;
	private int layoutResourceId;
	private Context context;

	public OahuEventListAdapter(Context context, int layoutResourceId, List<OahuEvent> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.items = items;
	}

	// Gets the view from the created adapter instance and sets appropriate fields
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		OahuEventHolder holder = null;

		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		row = inflater.inflate(layoutResourceId, parent, false);

		holder = new OahuEventHolder();
		holder.OahuEvent = items.get(position);
		
		holder.deleteButton = (ImageButton)row.findViewById(R.id.delete_button);
		holder.deleteButton.setTag(holder.OahuEvent);
		
		holder.moreInformationButton = (ImageButton)row.findViewById(R.id.more_information_button);
		holder.moreInformationButton.setTag(holder.OahuEvent);

		holder.name = (TextView)row.findViewById(R.id.list_item_string);

		row.setTag(holder);

		setupItem(holder);
		return row;
	}

	// Setup item so that fieds can be retrieved from the ListView element
	private void setupItem(OahuEventHolder holder) {
		holder.name.setText(OahuEvent.getName(holder.OahuEvent).trim());
		holder.description = OahuEvent.getDescription(holder.OahuEvent);
		holder.id = OahuEvent.getId(holder.OahuEvent);
	}
	
	// Holder for holding all fields for a ListView element
	public static class OahuEventHolder {
		OahuEvent OahuEvent;
		TextView name;
		String description;
		int id;
		ImageButton moreInformationButton;
		ImageButton deleteButton;
	}
}
