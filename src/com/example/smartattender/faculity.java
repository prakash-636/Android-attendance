package com.example.smartattender;

public class faculity
{
	
	private int faculty_id;
	private String facultyName;
	private String faculty_password;
	private String faculty_mobilenumber;
	public int getfacultyID() {
		return faculty_id;
	}
	public void getfacultyID(int faculty_id) {
		this.faculty_id = faculty_id;
	}
	public void setfacultyID(int faculty_id) {
		this.faculty_id = faculty_id;
	}
	public String getfacultyName() {
		return facultyName;
	}
	public void setfacultyName(String faculty_username) {
		this.facultyName = faculty_username;
	}
		
	public String getfPassword() {
		return faculty_password;
	}
	public void setfPassword(String faculty_password) {
		this.faculty_password = faculty_password;
	}
	public String getContactNumber() {
		return faculty_mobilenumber;
	}
	public void setContactNumber(String faculty_mobilenumber) {
		this.faculty_mobilenumber = faculty_mobilenumber;
	}

	


}
