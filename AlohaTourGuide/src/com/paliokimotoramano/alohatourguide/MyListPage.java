package com.paliokimotoramano.alohatourguide;

import java.util.ArrayList;

import android.app.Activity;
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
	
	ListView displayedList;
	ArrayList<String> names = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_list_page);
		
		final Button exploreButton = (Button) findViewById(R.id.exploreButton);
		final Button myListButton = (Button) findViewById(R.id.myListButton);
		
		myListButton.setBackgroundColor(Color.CYAN);
		
		Intent intent = getIntent();
		Bundle args = intent.getBundleExtra("BUNDLE");
		ArrayList<OahuEvent> myList = (ArrayList<OahuEvent>) args.getSerializable("ARRAYLIST");
		
		// Get trimmed names of events
		for (OahuEvent current: myList) {
			  names.add(OahuEvent.getName(current).trim());
		}
		
		displayedList = (ListView) findViewById(R.id.mylistview);
		
		
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, names);
		displayedList.setAdapter(adapter);
		
		exploreButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(v.getContext(), ExplorePage.class);
				startActivityForResult(intent, 0);
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
	    startActivity(new Intent(this, ExplorePage.class));
	}
	
	
}
