package com.soen387.registrationsystem.gateway;
public class Course {
		
	public String courseCode;
	public String title;
	public String semester;
	public String days;
	public String time;
	public String room;
	public String instructor;
	public String startDate;
	public String endDate;
	
public Course (String courseCode, String title, String semester, String days, String time, String room, String instructor, String startDate,String endDate ) {
	
	this.courseCode=courseCode;
	this.title=title;
	this.semester=semester;
	this.days=days;
	this.time=time;
	this.room=room;
	this.instructor=instructor;
	this.startDate=startDate;
	this.endDate=endDate;
	
}
public Course (Course course){
	this.courseCode=course.courseCode;
	this.title=course.title;
	this.semester=course.semester;
	this.days=course.days;
	this.time=course.time;
	this.room=course.room;
	this.instructor=course.instructor;
	this.startDate=course.startDate;
	this.endDate= course.endDate;
}

	
}
