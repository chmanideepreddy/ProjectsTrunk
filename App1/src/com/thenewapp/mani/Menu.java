   package com.thenewapp.mani;

import android.app.Dialog;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

@SuppressWarnings("unused")
public class Menu extends ListActivity{
	String classes[]={"MainActivity", "Text","Email","Camera","example4","example5","example6",};

	@SuppressWarnings("rawtypes")
	
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Menu.this,android.R.layout.simple_list_item_1,classes) );
	}

	
	
	

	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String cheese = classes[position];
		@SuppressWarnings("rawtypes")
		Class ourClass = null;
		try {
			ourClass = Class.forName(this.getPackageName()+"."+cheese);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
		Intent ourIntent = new Intent(Menu.this, ourClass);
		startActivity(ourIntent);
		}
	}

	
}
