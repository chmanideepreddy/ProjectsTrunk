package com.example.daytracker;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// SQLiteOpenHelper helps you open or create a database

public class DBTools  extends SQLiteOpenHelper {

	// Context : provides access to application-specific resources and classes

	public DBTools(Context applicationcontext) {

		// Call use the database or to create it

		super(applicationcontext, "activity.db", null, 1);

	}

	// onCreate is called the first time the database is created

	public void onCreate(SQLiteDatabase database) {

		// How to create a table in SQLite
		// Make sure you don't put a ; at the end of the query
		String query = null;
		String query2 = null;
		try {
			query = "CREATE TABLE Actiontime ( date DATE , fro INTEGER, totime INTERGER, activity TEXT, PRIMARY KEY(date,fro))";
			query2 = "CREATE TABLE Actionhours ( date DATE , activity TEXT, hours INTEGER, PRIMARY KEY(date, activity))";

		} catch (Exception e) {
			System.out.println(e);
		}
		// Executes the query provided as long as the query isn't a select
		// or if the query doesn't return any data

		database.execSQL(query);
		database.execSQL(query2);


	}

	// onUpgrade is used to drop tables, add tables, or do anything 
	// else it needs to upgrade
	// This is droping the table to delete the data and then calling
	// onCreate to make an empty table

	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		String query = "DROP TABLE IF EXISTS contacts";

		// Executes the query provided as long as the query isn't a select
		// or if the query doesn't return any data

		database.execSQL(query);
		onCreate(database);
	}

	public void insertContact(Act queryValues) {

		// Open a database for reading and writing

		SQLiteDatabase database = this.getWritableDatabase();
		SQLiteDatabase database1 = this.getWritableDatabase();


		// Stores key value pairs being the column name and the data
		// ContentValues data type is needed because the database
		// requires its data type to be passed

		ContentValues values = new ContentValues();
		ContentValues values1=new ContentValues();

		values.put("activity", queryValues.getActivity());
		values.put("date", queryValues.getDate());
		values.put("fro", queryValues.getFrom());
		values.put("totime", queryValues.getTo());

		values1.put("date", queryValues.getDate());
		values1.put("hours", queryValues.getHours());
		values1.put("activity", queryValues.getActivity());

		// Inserts the data in the form of ContentValues into the
		// table name provided

		database.insert("Actiontime", null, values);
		database1.insert("Actionhours", null, values1);

		// Release the reference to the SQLiteDatabase object

		database.close();
		database1.close();
	}

	public int updateContact(HashMap<String, String> queryValues) {

		// Open a database for reading and writing

		SQLiteDatabase database = this.getWritableDatabase();	

		// Stores key value pairs being the column name and the data

		ContentValues values = new ContentValues();

		values.put("firstName", queryValues.get("firstName"));
		values.put("lastName", queryValues.get("lastName"));
		values.put("phoneNumber", queryValues.get("phoneNumber"));
		values.put("emailAddress", queryValues.get("emailAddress"));
		values.put("homeAddress", queryValues.get("homeAddress"));

		// update(TableName, ContentValueForTable, WhereClause, ArgumentForWhereClause)

		return database.update("contacts", values, "contactId" + " = ?", new String[] { queryValues.get("contactId") });
	}

	// Used to delete a contact with the matching contactId

	public void deleteContact(String id) {

		// Open a database for reading and writing

		SQLiteDatabase database = this.getWritableDatabase();

		String deleteQuery = "DELETE FROM  contacts where contactId='"+ id +"'";

		// Executes the query provided as long as the query isn't a select
		// or if the query doesn't return any data

		database.execSQL(deleteQuery);
	}

	

	public ArrayList<HashMap<String, String>> getActivityInfo(String id) {
		HashMap<String, String> ActivityMap = new HashMap<String, String>();

		// Open a database for reading only
		ArrayList<HashMap<String, String>> ActivityArrayList;

		ActivityArrayList = new ArrayList<HashMap<String, String>>();

		SQLiteDatabase database = this.getReadableDatabase();

		String selectQuery = "SELECT * FROM Actionhours where date ='"+id+"'";

		// rawQuery executes the query and returns the result as a Cursor

		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				ActivityMap.put("activity", cursor.getString(1));
				ActivityMap.put("hours", cursor.getString(2));
				ActivityArrayList.add(ActivityMap);

			} while (cursor.moveToNext());
		}				    
		return ActivityArrayList;
	}	
}
