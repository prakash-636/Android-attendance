package com.example.smartattender;

import java.util.ArrayList;
import java.util.Calendar;





import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

public class MarkattendanceActivity extends Activity {
	
	private ImageButton date;
	private Calendar cal;
	private int day;
	private int month;
	private int dyear;
	private EditText dateEditText;
	Spinner spinnerbranch,spinneryear,spinnersubject;
	String userrole,branch,year,subject;
	private String[] branchString = new String[] {"CSE","MEC","EE","CIVIL"};
	private String[] yearString = new String[] {"1st","2nd","3rd"};
	private String[] sub = new String[] {"Data Structers","Software testing","SoftwareEngg","PP lab",""};
	Button submit,va;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_markattendance);
		
		spinnerbranch=(Spinner)findViewById(R.id.spinnerbranch1);
		spinneryear=(Spinner)findViewById(R.id.spinneryear);
		spinnersubject=(Spinner)findViewById(R.id.spinnersubject);
		submit=(Button)findViewById(R.id.buttonsubmit);
		va=(Button)findViewById(R.id.viewTotalAttendanceButton);
		
		ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, branchString);
		adapter_branch.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerbranch.setAdapter(adapter_branch);
		spinnerbranch.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				branch =(String) spinnerbranch.getSelectedItem();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
			
		ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, yearString);
		adapter_year.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinneryear.setAdapter(adapter_year);
		
		spinneryear.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				year =(String) spinneryear.getSelectedItem();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		ArrayAdapter<String> adapter_sub = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, sub);
		adapter_sub.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnersubject.setAdapter(adapter_sub);
		
		spinnersubject.setOnItemSelectedListener(new OnItemSelectedListener(){

			@Override
			public void onItemSelected(AdapterView<?> arg0, View view,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
				subject =(String) spinnersubject.getSelectedItem();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				AttendanceSessionBean attendanceSessionBean = new AttendanceSessionBean();
				faculity bean=new faculity();

				attendanceSessionBean.setAttendance_session_faculty_id(bean.getfacultyID());
				attendanceSessionBean.setAttendance_session_department(branch);
				attendanceSessionBean.setAttendance_session_class(year);
				attendanceSessionBean.setAttendance_session_date(dateEditText.getText().toString());
				attendanceSessionBean.setAttendance_session_subject(subject);

				DatabaseHelper dbAdapter = new DatabaseHelper(MarkattendanceActivity.this);
				int sessionId=	dbAdapter.addAttendanceSession(attendanceSessionBean);

		
				
				Intent intent = new Intent(MarkattendanceActivity.this,ListattendanceActivity.class);
				intent.putExtra("branch", branch);
				intent.putExtra("year", year);
				startActivity(intent);
				
			}
			
		});
		date = (ImageButton) findViewById(R.id.DateImageButton);
		cal = Calendar.getInstance();
		day = cal.get(Calendar.DAY_OF_MONTH);
		month = cal.get(Calendar.MONTH);
		dyear = cal.get(Calendar.YEAR);
		dateEditText = (EditText) findViewById(R.id.DateEditText);
		date.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				showDialog(0);

			}
		});

		
		va.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
		
				
				Intent intent = new Intent(MarkattendanceActivity.this,ViewattendanceActivity.class);
				
				startActivity(intent);
				
			}
			
		});
	}

	protected Dialog onCreateDialog(int id) {
		return new DatePickerDialog(this, datePickerListener, dyear, month, day);
	}
	private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int selectedYear,
				int selectedMonth, int selectedDay) {
			dateEditText.setText(selectedDay + " / " + (selectedMonth + 1) + " / "
					+ selectedYear);
		}
	};

}
