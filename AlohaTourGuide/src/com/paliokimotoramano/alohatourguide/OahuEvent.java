package com.paliokimotoramano.alohatourguide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class OahuEvent implements Serializable {
	
	//private final Drawable img;
	private final int id;
	// For uniformity, names will be 28 characters (add spaces if necessary)
	private final String name;
	private final String description;
	
	// Constructor for the OahuEvent class
	// Each OahuEvent has an Drawable, id, and description
	public OahuEvent(int id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}
	
	// This method is used to create the list of events that will be cycled on the Explore Page
	// NEED TO IMPLEMENT
	public static ArrayList<OahuEvent> createEvents() {
		ArrayList<OahuEvent> events = new ArrayList<OahuEvent>();
		OahuEvent waimeabw = new OahuEvent(R.drawable.waimea, "    Jump off Waimea Rock    ", "Located at Waimea Bay, go jump off the Rock!");
		OahuEvent giovannis = new OahuEvent(R.drawable.giovannis, "   Giovanni's Shrimp Truck  ", "Located on the North Shore, go get some shrimp!");
		events.add(waimeabw);
		events.add(giovannis);
		return events;
	}
	
	// This method is used to get the next event from a list of events
	public static OahuEvent nextEvent(ArrayList<OahuEvent> events) {
		if (events.size() > 0) {
			OahuEvent randomItem = events.get(new Random().nextInt(events.size()));
			return randomItem;	
		} else {
			OahuEvent noMoreEvents = new OahuEvent(R.drawable.nomoreevents, "       No More Events       ", "No need for description");
			return noMoreEvents;
		}
	}
	
	
	// This method is used to remove an event from an ArrayList of events
	public static ArrayList<OahuEvent> removeEvent(OahuEvent event, ArrayList<OahuEvent> eventList) {
		eventList.remove(event);
		return eventList;
	}
	
	// Accessor Methods
	public static int getId(OahuEvent event) {
		return event.id;
	}
	
	public static String getDescription(OahuEvent event) {
		return event.description;
	}
	
	public static String getName(OahuEvent event) {
		return event.name;
	}

}
