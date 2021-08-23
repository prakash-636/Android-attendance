package com.example.smartattender;

import java.util.ArrayList;

import android.app.Application;



public class ApplicationContext extends Application {
	private faculity facultyBean;
	private AttendanceSessionBean attendanceSessionBean;
	private ArrayList<student> studentBeanList;
	private ArrayList<AttendanceBean> attendanceBeanList;
	
	public faculity getFacultyBean() {
		return facultyBean;
	}
	public void setFacultyBean(faculity facultyBean) {
		this.facultyBean = facultyBean;
	}
	public AttendanceSessionBean getAttendanceSessionBean() {
		return attendanceSessionBean;
	}
	public void setAttendanceSessionBean(AttendanceSessionBean attendanceSessionBean) {
		this.attendanceSessionBean = attendanceSessionBean;
	}
	public ArrayList<student> getStudentBeanList() {
		return studentBeanList;
	}
	public void setStudentBeanList(ArrayList<student> studentBeanList) {
		this.studentBeanList = studentBeanList;
	}
	public ArrayList<AttendanceBean> getAttendanceBeanList() {
		return attendanceBeanList;
	}
	public void setAttendanceBeanList(ArrayList<AttendanceBean> attendanceBeanList) {
		this.attendanceBeanList = attendanceBeanList;
	}
	
	

}
