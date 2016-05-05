package com.paliokimotoramano.alohatourguide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/*
 * Object class for holding OahuEvents
 * Each event has an id (to retrive its corresponding image)
 * A name to be dispalyed as a title
 * A description for the More Information Pages
 * Implements Serializable in order to pass ArrayLists of type OahuEvent in Bundles
 */
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
	// Must add more to thisss
	public static ArrayList<OahuEvent> createEvents() {
		ArrayList<OahuEvent> events = new ArrayList<OahuEvent>();
		OahuEvent waimeabw = new OahuEvent(R.drawable.waimea, "    Jump off Waimea Rock    ", "Located at Waimea Bay, go jump off the Rock! There is also an underwater tunnel located below the rock.  Be careful! \n\nTurtles can often be seen on the left side of the rock!");
		OahuEvent giovannis = new OahuEvent(R.drawable.giovannis, "   Giovanni's Shrimp Truck  ", "Located on the North Shore, go get some shrimp!  \n\nThere are many other shrimp trucks, be sure to eat the very best shrimp at Giovanni's!");
		OahuEvent maksLighthouse = new OahuEvent(R.drawable.makslighthouse, "   Makapu'u Lighthouse Trail   ", "Located on the east side of Oahu, enjoy the view!  \n\nThere is also an area to swim nearby known as alan davis, go find it! \n\nHINT: Look to your right during the beginning of the trail.");
		OahuEvent diamondHeadHike = new OahuEvent(R.drawable.diamondheadhike, "   Diamond Head Hike Trail    ", "Located east of Waikiki, enjoy the view!  \n\nWear shoes and bring water! It gets hot! ");
		OahuEvent spittingCaves = new OahuEvent (R.drawable.spittingcaves, "   Spitting Caves Cliffs   ", "Located on the east side of Oahu, enjoy the view! \n\nIf you watch as waves enter the cave, the cave appears to be spitting them out! Hence the name!");
		OahuEvent hanaaumaBay = new OahuEvent (R.drawable.hanaaumabay, "   Hanauma Bay   ", "Located on the east side of oahu, swim with the fishes! \n\nYou can see all sorts of Hawaiian marine life while staying safe in the calm waters of the bay.");
		OahuEvent kokoHead = new OahuEvent (R.drawable.kokohead, "    Koko Head Hike    ", "Located on the east side of Oahu, reward yourself with the view!  \n\nIt's only 1,048 steps! It's hot and steep but you can do it! \n\n\nHINT: Go at sunset for a great view of the sunset and a cooler temperature.");
		OahuEvent maunawili = new OahuEvent (R.drawable.maunawili, "    Maunawili Waterfall Hike   ", "Enjoy the hike and be rewarded with a beautiful waterfall!");
		OahuEvent stairway = new OahuEvent (R.drawable.stairway, "    Stairway to Heaven Hike   ", "Located on the east side of Oahu, challenge yourself and enjoy the view! \n\nIt's ONLY 3,922 steps! Feeling up to the challenge?");
		OahuEvent yokohama = new OahuEvent (R.drawable.yokohama, "    Yokohama Beach    ", "Located on the west side of Oahu, Enjoy the the beautiful ocean!  \n\nBring a bodyboard if you're up to it, but beware of rocks!");
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
	
	// Had to rewrite equals in order to properly use ArrayList.contains(Object o) for OahuEvents
	// Needed in order to get reset function to work properly
	@Override
    public boolean equals(Object object)
    {
		boolean sameSame = false;

        if (object != null && object instanceof OahuEvent)
        {
            sameSame = OahuEvent.getId(this) == ((OahuEvent) object).id;
        }

        return sameSame;
    }

}
