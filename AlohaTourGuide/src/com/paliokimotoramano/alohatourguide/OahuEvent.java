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
		OahuEvent waimeabw = new OahuEvent(R.drawable.waimea, "    Jump off Waimea Rock    ", "Located at Waimea Bay, go jump off the Rock!");
		OahuEvent giovannis = new OahuEvent(R.drawable.giovannis, "   Giovanni's Shrimp Truck  ", "Located on the North Shore, go get some shrimp!");
		OahuEvent maksLighthouse = new OahuEvent(R.drawable.makslighthouse, "   Makapu'u Lighthouse Trail   ", "Located on the east side of Oahu, enjoy the view!");
		OahuEvent diamondHeadHike = new OahuEvent(R.drawable.diamondheadhike, "   Diamond Head Hike Trail    ", "Located east of Waikiki, enjoy the view!");
		OahuEvent spittingCaves = new OahuEvent (R.drawable.spittingcaves, "   Spitting Caves Cliffs   ", "Located on the east side of Oahu, enjoy the view!");
		OahuEvent hanaaumaBay = new OahuEvent (R.drawable.hanaaumabay, "   Hana'ama Bay   ", "Located on the east side of oahu, swim with the fishes!");
		OahuEvent kokoHead = new OahuEvent (R.drawable.kokohead, "    Koko Head Hike    ", "Located on the east side of Oahu, reward yourself with the view!");
		OahuEvent maunawili = new OahuEvent (R.drawable.maunawili, "    Maunawili Waterfall Hike   ", "Enjoy the hike and be rewarded with a beautiful waterfall!");
		OahuEvent stairway = new OahuEvent (R.drawable.stairway, "    Stairway to Heaven Hike   ", "Located on the east side of Oahu, challenge yourself and enjoy the view!");
		OahuEvent yokohama = new OahuEvent (R.drawable.yokohama, "    Yokohama Beach    ", "Located on the west side of Oahu, Enjoy the the beautiful ocean!");
		events.add(waimeabw);
		events.add(giovannis);
		events.add(maksLighthouse);
		events.add(diamondHeadHike);
		events.add(spittingCaves);
		events.add(hanaaumaBay);
		events.add(kokoHead);
		events.add(maunawili);
		events.add(stairway);
		events.add(yokohama);
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
