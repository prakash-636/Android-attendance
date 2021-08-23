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

public class ViewstudentActivity extends Activity {

	String branch;
	String year;
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_viewstudent);
		
		context = this;
		DatabaseHelper db =  new DatabaseHelper(context);
		branch=getIntent().getExtras().getString("branch");
		year =getIntent().getExtras().getString("year");
		ArrayList<String> studentList=db.GetAllStudentslist(branch, year);
		ListView listst = (ListView) findViewById(R.id.Liststudent);
			
		ArrayAdapter adapter = new ArrayAdapter<String>(this, 
			       R.layout.activity_listview, studentList);
			listst.setAdapter(adapter);
			
			
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.viewstudent, menu);
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
