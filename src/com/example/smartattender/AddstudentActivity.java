package com.example.smartattender;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AddstudentActivity extends Activity {
	
	Button submitButton;
	EditText textReg;
	EditText textName;
	Spinner spinnerbranch,spinneryear;
	String userrole,branch,year;
	private String[] branchString = new String[] {"CSE","MEC","EE","CIVIL"};
	private String[] yearString = new String[] {"1st","2nd","3rd"};

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addstudent);
		
		spinnerbranch=(Spinner)findViewById(R.id.spinnerdept);
		spinneryear=(Spinner)findViewById(R.id.spinnerclass);
		textReg=(EditText)findViewById(R.id.editregnum);
		textName=(EditText)findViewById(R.id.editSname);
		submitButton=(Button)findViewById(R.id.submitbtn);
		
		//spinner 1
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
		ArrayAdapter<String> adapter_branch = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, branchString);
		adapter_branch
		.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerbranch.setAdapter(adapter_branch);
		
		//spinner 2
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
				ArrayAdapter<String> adapter_year = new ArrayAdapter<String>(this,
						android.R.layout.simple_spinner_item, yearString);
				adapter_branch
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				spinneryear.setAdapter(adapter_year);
				
				
				submitButton.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						String reg = textReg.getText().toString();
						String name = textName.getText().toString();
						
						if (TextUtils.isEmpty(reg)) {
							textReg.setError("please enter reg");
						}

						else if (TextUtils.isEmpty(name)) {
							textName.setError("please enter name");
						}			
						else { 
							
							student studentBean = new student();
							
							studentBean.setStudent_Reg(reg);
							studentBean.setStudent_name(name);
							studentBean.setStudent_department(branch);
							studentBean.setStudent_class(year);
							
							DatabaseHelper dbAdapter= new DatabaseHelper(AddstudentActivity.this);
							dbAdapter.addStudent(studentBean);
							
							Intent intent =new Intent(AddstudentActivity.this,FltMenuActivity.class);
							startActivity(intent);
							Toast.makeText(getApplicationContext(), "student added successfully", Toast.LENGTH_SHORT).show();

						}
					}
				});
							
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addstudent, menu);
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
