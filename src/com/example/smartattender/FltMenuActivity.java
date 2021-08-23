package com.example.smartattender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FltMenuActivity extends Activity {
	Button addstudent;
	Button viewstudent;
	Button markattendance;
	Button viewattendance;
	Button logout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_flt_menu);
		
		addstudent=(Button)findViewById(R.id.addStudentbtn);
		viewstudent=(Button)findViewById(R.id.viewStudentbtn);
		markattendance=(Button)findViewById(R.id.markAttendancebtn);
		viewattendance=(Button)findViewById(R.id.viewAttendancebtn);
		logout=(Button)findViewById(R.id.logoutbtn);
		
		addstudent.setOnClickListener(new OnClickListener()
		{


			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(FltMenuActivity.this,AddstudentActivity.class);
				startActivity(intent);
				
			}
		});
		viewstudent.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(FltMenuActivity.this,ViewActivity.class);
				startActivity(intent);
				
			}
		});
		markattendance.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(FltMenuActivity.this,MarkattendanceActivity.class);
				startActivity(intent);
				
			}
		});
		viewattendance.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(FltMenuActivity.this,ViewattendanceActivity.class);
				startActivity(intent);
				
			}
		});
		logout.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(FltMenuActivity.this,LoginActivity.class);
				startActivity(intent);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.flt_menu, menu);
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
}
