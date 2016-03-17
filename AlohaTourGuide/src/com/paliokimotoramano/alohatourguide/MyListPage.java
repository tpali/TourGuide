package com.paliokimotoramano.alohatourguide;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

public class MyListPage extends Activity {
	
	// Variables for the confirmDelete prompt
	protected static final int DIALOG_REMOVE_CALC = 1;
	protected static final int DIALOG_REMOVE_PERSON = 2;
	
	ListView displayedList;
	ArrayList<String> names = new ArrayList<String>();
	OahuEventListAdapter adapter;
	ArrayList<OahuEvent> myList;
	ArrayList<OahuEvent> events;
	String previousPage;
	OahuEvent currentEvent;
	Bundle args;
	Intent intent;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_list_page);
		
		final Button exploreButton = (Button) findViewById(R.id.exploreButton);
		final Button myListButton = (Button) findViewById(R.id.myListButton);
		final Button resetButton = (Button) findViewById(R.id.reset_button);
		
		myListButton.setBackgroundColor(Color.CYAN);
		
		
		intent = getIntent();
		args = intent.getBundleExtra("BUNDLE");
		myList = (ArrayList<OahuEvent>) args.getSerializable("MYLIST");
		events = (ArrayList<OahuEvent>) args.getSerializable("EVENTS");
		previousPage = (String) args.getSerializable("PREVIOUSPAGE");
		currentEvent = (OahuEvent) args.getSerializable("OAHUEVENT");
		
		adapter = new OahuEventListAdapter(MyListPage.this, R.layout.oahu_event_listview_item, (List<OahuEvent>)myList);
		ListView myListView = (ListView)findViewById(R.id.mylistview);
		myListView.setAdapter(adapter);
		
		
		exploreButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ExplorePage.class);
				intent.putExtra("BUNDLE",createBundle());
				startActivity(intent);
			}
			
		});
		
		resetButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				resetEventList();
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
		if (previousPage.equals("ExplorePage")) {
			Intent intent = new Intent(this, ExplorePage.class);
			// Pass myList to ExplorePage
			intent.putExtra("BUNDLE",createBundle());
			startActivity(intent);
		} else if (previousPage.equals("MoreInformationPage")) {
			currentEvent = (OahuEvent) args.getSerializable("OAHUEVENT");
			Intent intent = new Intent(this, MoreInformationPage.class);
			Bundle args = createBundle();
			args.putSerializable("OAHUEVENT", (Serializable)currentEvent);
			intent.putExtra("BUNDLE",args);
			startActivity(intent);
		} 
	}
	
	public void removeOahuEventOnClickHandler(View v) {
		final OahuEvent itemToRemove = (OahuEvent)v.getTag();
		AlertDialog.Builder confirm = new AlertDialog.Builder(this);
		confirm.setMessage("Delete event from My List?");
		confirm.setCancelable(false);
		
		confirm.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				adapter.remove(itemToRemove);
				myList.remove(itemToRemove);
			}
		});
		
		confirm.setNegativeButton("No", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				// Do nothing
			}
		});
		
		confirm.create().show();
		
	}
	
	
	public void informationPageOnClickHandler(View v) {
		currentEvent = (OahuEvent)v.getTag();
		Intent intent = new Intent(this, MoreInformationPage.class);
		// Pass myList to MyListPage
		Bundle args = createBundle();
		args.putSerializable("OAHUEVENT", (Serializable)currentEvent);
		intent.putExtra("BUNDLE",args);
		startActivity(intent);
	}
	
	public void resetEventList() {
	events = OahuEvent.createEvents();
		for (OahuEvent cur : myList) {
			if (myList.contains(cur)) {
				events.remove(cur);
			}
		}
	}
	
	private Bundle createBundle() {
		previousPage = "MyListPage";
		Bundle args = new Bundle();
		args.putSerializable("MYLIST",(Serializable)myList);
		args.putSerializable("EVENTS",(Serializable)events);
		args.putSerializable("PREVIOUSPAGE", (Serializable)previousPage);
		args.putSerializable("OAHUEVENT", (Serializable)currentEvent);
		return args;
	}
	
	/*
	private Dialog createDialogRemoveConfirm(final int dialogRemove) {
	return new AlertDialog.Builder(getApplicationContext())
	.setIcon(R.drawable.trashbin_icon)
	.setTitle(R.string.calculation_dialog_remove_text)
	.setPositiveButton(R.string.calculation_dialog_button_ok, new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int whichButton) {
			handleRemoveConfirm(dialogRemove);
		}
	})
	.setNegativeButton(R.string.calculation_dialog_button_cancel, null)
	.create();
	}
	*/
	
	
	
}
