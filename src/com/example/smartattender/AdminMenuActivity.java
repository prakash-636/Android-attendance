package com.example.smartattender;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class AdminMenuActivity extends Activity {
	
	Button addfaculty;
	Button viewfaculty;
	Button viewattendance;
	Button viewstudent;
	Button logout;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin_menu);
		
		addfaculty=(Button)findViewById(R.id.addFaculty);
		viewfaculty=(Button)findViewById(R.id.viewFaculty);
		viewstudent=(Button)findViewById(R.id.viewstudenta);
		viewattendance=(Button)findViewById(R.id.viewAttendancea);
		logout=(Button)findViewById(R.id.logoutbtna);
		
		addfaculty.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(AdminMenuActivity.this,AddfacultyActivity.class);
				startActivity(intent);
				
				
			}
		});
		viewfaculty.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(AdminMenuActivity.this,ViewfacultyActivity.class);
				startActivity(intent);
				
			}
		});
		viewstudent.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(AdminMenuActivity.this,ViewActivity.class);
				startActivity(intent);
				
			}
		});
		viewattendance.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(AdminMenuActivity.this,ViewattendanceActivity.class);
				startActivity(intent);
				
			}
		});
		logout.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent =new Intent(AdminMenuActivity.this,LoginActivity.class);
				startActivity(intent);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.admin_menu, menu);
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
