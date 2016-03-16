package com.paliokimotoramano.alohatourguide;

import java.io.Serializable;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
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
		
		final Button exploreButton = (Button) findViewById(R.id.exploreButton);
		final Button myListButton = (Button) findViewById(R.id.myListButton);
		final ImageButton like_button = (ImageButton) findViewById(R.id.like_button);
		final ImageButton dislike_button = (ImageButton) findViewById(R.id.dislike_button);
		final Button skip_button = (Button) findViewById(R.id.skip_button);
		final Button undo_button = (Button) findViewById(R.id.undo_button);
		
		
		if (!(justOpened)) {
		Intent intent = getIntent();
		
		Bundle args = intent.getBundleExtra("BUNDLE");
		myList = (ArrayList<OahuEvent>) args.getSerializable("MYLIST");
		events = (ArrayList<OahuEvent>) args.getSerializable("EVENTS");
		previousPage = (String) args.getSerializable("PREVIOUSPAGE");
		}
		
		currentEvent = OahuEvent.nextEvent(events);
		
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
				if (OahuEvent.getName(currentEvent) != "       No More Events       ") {
					myList.add(currentEvent);
					previousEvent = currentEvent;
					events.remove(currentEvent);
					currentEvent = OahuEvent.nextEvent(events);
				} 
					img.setImageResource(OahuEvent.getId(currentEvent));
					name.setText(OahuEvent.getName(currentEvent));
				
			}
		});
		
		// When Dislike Button gets clicked
		dislike_button.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (OahuEvent.getName(currentEvent) != "       No More Events       ") {
					previousEvent = currentEvent;
					events.remove(currentEvent);
					currentEvent = OahuEvent.nextEvent(events);
				} 
					img.setImageResource(OahuEvent.getId(currentEvent));
					name.setText(OahuEvent.getName(currentEvent));
				
			}	
		});
		
		// When the SKIP button is pressed
				skip_button.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (OahuEvent.getName(currentEvent) != "       No More Events       ") {
							previousEvent = currentEvent;
							currentEvent = OahuEvent.nextEvent(events);
						} 
							img.setImageResource(OahuEvent.getId(currentEvent));
							name.setText(OahuEvent.getName(currentEvent));
						
					}	
				});
		
		// When the UNDO button is pressed
				undo_button.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						if (previousEvent != null) {
							currentEvent = previousEvent;
							if (myList.contains(currentEvent)) {
								myList.remove(currentEvent);
								events.add(previousEvent);
							} else if (events.contains(currentEvent)) {
								myList.remove(currentEvent);
							} else {
								events.add(currentEvent);
							}
						 
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
				// Pass myList to MyListPage
				intent.putExtra("BUNDLE",createBundle());
				startActivity(intent);
				justOpened = false;
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
				Bundle args = createBundle();
				args.putSerializable("OAHUEVENT", (Serializable)currentEvent);
				intent.putExtra("BUNDLE",args);
				startActivity(intent);
			} 
		}
	}
	
	private Bundle createBundle() {
		previousPage = "ExplorePage";
		Bundle args = new Bundle();
		args.putSerializable("MYLIST",(Serializable)myList);
		args.putSerializable("EVENTS",(Serializable)events);
		args.putSerializable("PREVIOUSPAGE", (Serializable)previousPage);
		return args;
	}
}
