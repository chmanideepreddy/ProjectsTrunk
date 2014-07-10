package com.example.daytracker;

public class Act {
	String date;
	int from;
	int to;
	String Activity;
	int hours;
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getFrom() {
		return from;
	}
	public void setFrom(int timeOfStart_int) {
		this.from = timeOfStart_int;
	}
	public int getTo() {
		return to;
	}
	public void setTo(int timeOfEnd_int) {
		this.to = timeOfEnd_int;
	}
	public String getActivity() {
		return Activity;
	}
	public void setActivity(String activity) {
		Activity = activity;
	}
	public int getHours() {
		return hours;
	}
	public void setHours(int hours) {
		this.hours = hours;
	}
	
	

}
