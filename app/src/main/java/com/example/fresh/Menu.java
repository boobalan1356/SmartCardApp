package com.example.fresh;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Intent;
import android.database.DataSetObserver;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Menu extends ListActivity {

	ArrayList<String> s = new ArrayList<String>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	
	s.add("MainActivity");
	s.add("SmartCard");
	s.add("TextPlay");
	s.add("Example1");
	
	setListAdapter(new ArrayAdapter<String>(Menu.this, android.R.layout.simple_list_item_1, s));
	
	}
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);

		String pos = s.get(position);
		try {
			Class altClass = Class.forName("com.example.fresh."+ pos);
			
			
			Intent menuIntent = new Intent(Menu.this, altClass);
			
			startActivity(menuIntent);
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
	}


	

}
