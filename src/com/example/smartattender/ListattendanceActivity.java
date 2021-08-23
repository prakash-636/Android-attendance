package com.example.smartattender;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ListattendanceActivity extends Activity {
	
	String branch;
	String year;
	Context context;
	String status="P";
	Button attendanceSubmit;
	int sessionId=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listattendance);
		
		context = this;
		DatabaseHelper db =  new DatabaseHelper(context);
		branch=getIntent().getExtras().getString("branch");
		year =getIntent().getExtras().getString("year");
		ArrayList<String> studentList=db.GetAllStudentslist(branch, year);
		ListView listst = (ListView) findViewById(R.id.attendancelist);
			
		ArrayAdapter adapter = new ArrayAdapter<String>(this, 
			       R.layout.activity_listview, studentList);
			listst.setAdapter(adapter);
			
			
			listst.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {

					arg0.getChildAt(arg2).setBackgroundColor(Color.TRANSPARENT);
					//arg0.setBackgroundColor(234567);
					arg1.setBackgroundColor(334455);
					final student studentBean = new student();
					final Dialog dialog = new Dialog(ListattendanceActivity.this);
					dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//...........
					dialog.setContentView(R.layout.test_layout);
					// set title and cancelable
					RadioGroup radioGroup;
					RadioButton present;
					RadioButton absent;
					radioGroup = (RadioGroup) dialog.findViewById(R.id.radioGroup);
					present=(RadioButton)dialog.findViewById(R.id.PresentradioButton);
					absent=(RadioButton)dialog.findViewById(R.id.AbsentradioButton);
					radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener() {

						@Override
						public void onCheckedChanged(RadioGroup group, int checkedId) {
							if(checkedId == R.id.PresentradioButton) {
								
								status = "P";
							} else if(checkedId == R.id.AbsentradioButton) {

								status = "A";
							} else {
							}
						}
					});

					attendanceSubmit = (Button)dialog.findViewById(R.id.attendanceSubmitButton);
					attendanceSubmit.setOnClickListener(new OnClickListener() {
						
						@Override
						public void onClick(View arg0) {
							AttendanceBean attendanceBean = new AttendanceBean();
							
							attendanceBean.setAttendance_session_id(sessionId);
							attendanceBean.setAttendance_student_id(studentBean.getStudent_id());
							attendanceBean.setAttendance_status(status);
							
							DatabaseHelper dbAdapter = new DatabaseHelper(ListattendanceActivity.this);
							dbAdapter.addNewAttendance(attendanceBean);
							
							dialog.dismiss();
							
						}
					});
					
					dialog.setCancelable(true);
					dialog.show();
				}
			});



	}

}
