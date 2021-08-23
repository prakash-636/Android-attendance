package com.example.smartattender;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends Activity{
	
	Button login;
	EditText username,password;
	Spinner spinnerloginas;
	String userrole;
	private String[] userRoleString = new String[] { "admin", "faculty"};
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.activity_login);
	
	login =(Button)findViewById(R.id.loginbtn);
	username=(EditText)findViewById(R.id.editUserName);
	password=(EditText)findViewById(R.id.editpassword);
	spinnerloginas=(Spinner)findViewById(R.id.spinnerloginas);
	
	spinnerloginas.setOnItemSelectedListener(new OnItemSelectedListener()
	{



		@Override
		public void onItemSelected(AdapterView<?> arg0, View view,
				int position, long id) {
			// TODO Auto-generated method stub
			((TextView) arg0.getChildAt(0)).setTextColor(Color.WHITE);
			userrole =(String) spinnerloginas.getSelectedItem();
			
		}

		@Override
		public void onNothingSelected(AdapterView<?> parent) {
			// TODO Auto-generated method stub
			
		}
		
	
	});
	ArrayAdapter<String> adapter_role = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, userRoleString);
	adapter_role
	.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	spinnerloginas.setAdapter(adapter_role);
	
	login.setOnClickListener(new OnClickListener()
	{

		@Override
		public void onClick(View v) 
		{
			// TODO Auto-generated method stub
			if(userrole.equals("admin"))
			{

				String user_name = username.getText().toString();
				String pass_word = password.getText().toString();

				if (TextUtils.isEmpty(user_name)) 
				{
					username.setError("Invalid User Name");
				}
				else if(TextUtils.isEmpty(pass_word))
				{
					password.setError("enter password");
				}
				else
				{
					if(user_name.equals("admin") & pass_word.equals("admin123")){
					Intent intent =new Intent(LoginActivity.this,AdminMenuActivity.class);
					startActivity(intent);
					Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
					}else{
						Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
					}
				}
			}
			
			else 
			{
				String user_name = username.getText().toString();
				String pass_word = password.getText().toString();

				if (TextUtils.isEmpty(user_name)) 
				{
					username.setError("Invalid User Name");
				}
				else if(TextUtils.isEmpty(pass_word))
				{
					password.setError("enter password");
				}
				else
				{
					DatabaseHelper dbAdapter = new DatabaseHelper(LoginActivity.this);
					faculity faculty = dbAdapter.validateFaculty(user_name, pass_word);
					
					if(faculty!=null)
					{
						Intent intent = new Intent(LoginActivity.this,FltMenuActivity.class);
						startActivity(intent);
						Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
					}
					else
					{
						Toast.makeText(getApplicationContext(), "Login failed", Toast.LENGTH_SHORT).show();
					}
					
				}
				
				
			
		}
	}
		
		
	});

}

}
