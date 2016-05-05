package com.paliokimotoramano.alohatourguide;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class ExplorePage extends Activity {
	
	// Boolean to control backbutton on startup
	public static boolean justOpened = true;
	// String to keep track of previous page
	public String previousPage;
	// Variable to hold users selected events 
	public static ArrayList<OahuEvent> myList = new ArrayList<OahuEvent>();
	// Creates list of events from OahuEvent class
	public static ArrayList<OahuEvent> events = OahuEvent.createEvents();
	// Set the currentEvent to a random event from the OahuEvent class
	public static OahuEvent currentEvent;
	// Set the previousEvent to the most previously liked or disliked event
	public static OahuEvent previousEvent;
	// Set the displayed image to the currentImage
	ImageView img;
	TextView name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_explore_page);
		
		// Variables for all buttons
		final Button exploreButton = (Button) findViewById(R.id.exploreButton);
		final Button myListButton = (Button) findViewById(R.id.myListButton);
		final ImageButton like_button = (ImageButton) findViewById(R.id.like_button);
		final ImageButton dislike_button = (ImageButton) findViewById(R.id.dislike_button);
		final ImageButton more_information_button = (ImageButton) findViewById(R.id.more_information_button);
		final Button skip_button = (Button) findViewById(R.id.skip_button);
		final Button undo_button = (Button) findViewById(R.id.undo_button);

		
		// If the app was not just opened, getIntent from previousPage
		if (!(justOpened)) {
		Intent intent = getIntent();
		
		// Set local variables to the passed values in the bundle
		Bundle args = intent.getBundleExtra("BUNDLE");
		myList = (ArrayList<OahuEvent>) args.getSerializable("MYLIST");
		events = (ArrayList<OahuEvent>) args.getSerializable("EVENTS");
		previousPage = (String) args.getSerializable("PREVIOUSPAGE");
		currentEvent = (OahuEvent) args.getSerializable("OAHUEVENT");
		}
		
		
		// Check if a new event should be cycled
		if (justOpened || (OahuEvent.getName(currentEvent) == "       No More Events       ") || (previousPage.equals("MyListPage") && events.size() > 0)) {
		currentEvent = OahuEvent.nextEvent(events);
		}
		
		// Set the image being displayed to currentEvent's image
		img = (ImageView) findViewById(R.id.exploreImage);
		img.setImageResource(OahuEvent.getId(currentEvent));
		// Set the title to the currentEvent's name
		name = (TextView) findViewById(R.id.eventName);
		name.setText(OahuEvent.getName(currentEvent));
	
		// Set the Explore Button to Cyan upon opening app
		exploreButton.setBackgroundColor(Color.CYAN);
		
		// When Like Button gets clicked
		like_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Check that we are not at the end of the event pool
				if (OahuEvent.getName(currentEvent) != "       No More Events       ") {
					// Add currentEvent to myList & set previousEvent to the currentEvent & remove currentEvent from event pool & get next event
					myList.add(currentEvent);
					previousEvent = currentEvent;
					events.remove(currentEvent);
					currentEvent = OahuEvent.nextEvent(events);
				} 
					// Set views to new events fields
					img.setImageResource(OahuEvent.getId(currentEvent));
					name.setText(OahuEvent.getName(currentEvent));
			}
		});
		
		// When Dislike Button gets clicked
		dislike_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Check that we are not at the end of the event pool
				if (OahuEvent.getName(currentEvent) != "       No More Events       ") {
					// Set previousEvent to the currentEvent & remove currentEvent from event pool & get next event
					previousEvent = currentEvent;
					events.remove(currentEvent);
					currentEvent = OahuEvent.nextEvent(events);
				} 
				// Set views to new events fields
					img.setImageResource(OahuEvent.getId(currentEvent));
					name.setText(OahuEvent.getName(currentEvent));	
			}	
		});
		
		// When the SKIP button is pressed
				skip_button.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// Set previousEvent to currentEvent & get next event
						if (OahuEvent.getName(currentEvent) != "       No More Events       ") {
							previousEvent = currentEvent;
							currentEvent = OahuEvent.nextEvent(events);
						} 
							// Set views to new events fields
							img.setImageResource(OahuEvent.getId(currentEvent));
							name.setText(OahuEvent.getName(currentEvent));
					}	
				});
		
		// When the UNDO button is pressed
				undo_button.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						// Check that previousEvent is not null & then set currentEvent to previousEvent
						if (previousEvent != null) {
							currentEvent = previousEvent;
							// Check if the userliked the event & if so removes it from myList & add it back to event pool
							if (myList.contains(currentEvent)) {
								myList.remove(currentEvent);
								events.add(previousEvent);
							// Check if the user hit the SKIP button
							} else if (events.contains(currentEvent)) {
							// Do nothing because currentEvent will be set in beginning of loop
							// If the user didn't like or skip the event, they hit dislike. So add it back to event pool
							} else {
								events.add(currentEvent);
							}
							// Set views to new events fields
							img.setImageResource(OahuEvent.getId(currentEvent));
							name.setText(OahuEvent.getName(currentEvent));
						}	
					}
				});		
		
		
		// When MyList Button gets clicked
		myListButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), MyListPage.class);
				// Create Bundle and pass it to MyListPage, also set justOpened to false
				intent.putExtra("BUNDLE",createBundle());
				startActivity(intent);
				justOpened = false;
			}
			
		});
		
		// If more information button gets clicked
		more_information_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// Create Bundle and pass it to MoreInformationPage, also set justOpened to false, checks if currentEvent is an actual event
				if (OahuEvent.getName(currentEvent) != "       No More Events       ") {
					Intent intent = new Intent(v.getContext(), MoreInformationPage.class);
					// Pass myList to MyListPage
					Bundle args = createBundle();
					intent.putExtra("BUNDLE",args);
					startActivity(intent);
					justOpened = false;
				}
			}
			
		});
				
		 
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
		if (!(justOpened)) {
			if (previousPage.equals("MyListPage")) {
				Intent intent = new Intent(this, MyListPage.class);
				// Pass myList to MyListPage
				intent.putExtra("BUNDLE",createBundle());
				startActivity(intent);
			} else if (previousPage.equals("MoreInformationPage")) {
				Intent intent = new Intent(this, MoreInformationPage.class);
				intent.putExtra("BUNDLE",createBundle());
				startActivity(intent);
			} 
		}
	}
	
	// Creates Bundles which are passed between activities to exchange information
	private Bundle createBundle() {
		previousPage = "ExplorePage";
		Bundle args = new Bundle();
		args.putSerializable("MYLIST",(Serializable)myList);
		args.putSerializable("EVENTS",(Serializable)events);
		args.putSerializable("PREVIOUSPAGE", (Serializable)previousPage);
		args.putSerializable("OAHUEVENT", (Serializable)currentEvent);
		return args;
	}
	

	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
	  super.onSaveInstanceState(savedInstanceState);
	  // Save UI state changes to the savedInstanceState.
	  // This bundle will be passed to onCreate if the process is
	  // killed and restarted.
	  	savedInstanceState.putSerializable("MYLIST",(Serializable)myList);
	  	savedInstanceState.putSerializable("EVENTS",(Serializable)events);
	  	savedInstanceState.putSerializable("PREVIOUSPAGE", (Serializable)previousPage);
	  	savedInstanceState.putSerializable("OAHUEVENT", (Serializable)currentEvent);
	}
	
	@Override
	public void onRestoreInstanceState(Bundle savedInstanceState) {
	  super.onRestoreInstanceState(savedInstanceState);
	  // Restore UI state from the savedInstanceState.
	  // This bundle has also been passed to onCreate.
		myList = (ArrayList<OahuEvent>) savedInstanceState.getSerializable("MYLIST");
		events = (ArrayList<OahuEvent>) savedInstanceState.getSerializable("EVENTS");
		previousPage = (String) savedInstanceState.getSerializable("PREVIOUSPAGE");
		currentEvent = (OahuEvent) savedInstanceState.getSerializable("OAHUEVENT");
		
		// Set the image being displayed to currentEvent's image
		img = (ImageView) findViewById(R.id.exploreImage);
		img.setImageResource(OahuEvent.getId(currentEvent));
		// Set the title to the currentEvent's name
		name = (TextView) findViewById(R.id.eventName);
		name.setText(OahuEvent.getName(currentEvent));
	}
	
// onPause method for saving content when the app is paused.  Not fully implemented
/*	@Override
	public void onPause() {
		super.onPause();

		Editor prefsEditor = mPrefs.edit();
		Gson gson = new Gson();
		String jsonMyList = gson.toJson(myList);
		String jsonEvents = gson.toJson(events);
		prefsEditor.putString("MyList", jsonMyList);
		prefsEditor.putString("Events", jsonEvents);
		prefsEditor.commit();
		
	}
*/
}
