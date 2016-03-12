package com.paliokimotoramano.alohatourguide;

import java.util.ArrayList;
import java.util.Random;

import android.media.Image;

public class OahuEvent {
	
	private final Image img;
	private final int id;
	private final String description;
	
	// Constructor for the OahuEvent class
	// Each OahuEvent has an image, id, and description
	public OahuEvent(Image img, int id, String description) {
		this.img = img;
		this.id = id;
		this.description = description;
	}
	
	// This method is used to create the list of events that will be cycled on the Explore Page
	// NEED TO IMPLEMENT
	public static ArrayList<OahuEvent> createEvents() {
		ArrayList<OahuEvent> events = new ArrayList<OahuEvent>();
		return events;
	}
	
	// This method is used to get the next event from a list of events
	public static OahuEvent nextEvent(ArrayList<OahuEvent> events) {
		Random rand = null;
		@SuppressWarnings("null")
		int randomNum = rand.nextInt(((events.size()-1) - 0) + 1) + 0;
		
		return events.get(randomNum);
		
	}
	
	// This method is used to remove an event from an ArrayList of events
	public static ArrayList<OahuEvent> removeEvent(OahuEvent event, ArrayList<OahuEvent> eventList) {
		eventList.remove(event);
		return eventList;
	}
	
	// Accessor Methods for OahuEvent fields
	public static Image getImage(OahuEvent event) {
		return event.img;
	}
	
	public static int getId(OahuEvent event) {
		return event.id;
	}
	
	public static String getDescription(OahuEvent event) {
		return event.description;
	}
	

}
