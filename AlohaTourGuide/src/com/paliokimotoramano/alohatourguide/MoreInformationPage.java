package com.paliokimotoramano.alohatourguide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MoreInformationPage extends Activity {
	// Local variables
	OahuEvent currentEvent;
	ArrayList<OahuEvent> myList;
	ArrayList<OahuEvent> events;
	String previousPage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_information_page);
		
		// Not default page so get intent from previousPage & set varibles
		Intent intent = getIntent();
		Bundle args = intent.getBundleExtra("BUNDLE");
		currentEvent = (OahuEvent) args.getSerializable("OAHUEVENT");
		myList = (ArrayList<OahuEvent>) args.getSerializable("MYLIST");
		events = (ArrayList<OahuEvent>) args.getSerializable("EVENTS");
		previousPage = (String) args.getSerializable("PREVIOUSPAGE");
		
		// Create variables for displayed views
		TextView eventName = (TextView)findViewById(R.id.eventName);
		ImageView eventImage = (ImageView)findViewById(R.id.eventImage);
		TextView eventDescription = (TextView)findViewById(R.id.eventDescription);
		
		// Set views to corresponding fields of currentEvent
		eventImage.setImageResource(OahuEvent.getId(currentEvent));
		eventName.setText(OahuEvent.getName(currentEvent));
		eventDescription.setText(OahuEvent.getDescription(currentEvent));
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.explore_page, menu);
		return true;
	}

	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	// Function to handle back button presses, takes into account the previousPage and sends you back there
	@Override
	public void onBackPressed() {
		if (previousPage.equals("MyListPage")) {
			Intent intent = new Intent(this, MyListPage.class);
			// Pass myList to MyListPage
			intent.putExtra("BUNDLE",createBundle());
			startActivity(intent);
		} else if (previousPage.equals("ExplorePage")) {
			Intent intent = new Intent(this, ExplorePage.class);
			Bundle args = createBundle();
			intent.putExtra("BUNDLE",args);
			startActivity(intent);
		} 
	    
	}
	
	// Creates Bundles which are passed between activities to exchange informations
	private Bundle createBundle() {
		previousPage = "MoreInformationPage";
		Bundle args = new Bundle();
		args.putSerializable("MYLIST",(Serializable)myList);
		args.putSerializable("EVENTS",(Serializable)events);
		args.putSerializable("PREVIOUSPAGE", (Serializable)previousPage);
		args.putSerializable("OAHUEVENT", (Serializable)currentEvent);
		return args;
	}
	
}
