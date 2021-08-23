package com.example.smartattender;





import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddfacultyActivity extends Activity{

	EditText textusername;
	EditText textpassword;
	EditText textphone;
	Button addflty;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addfaculty);
		
		textusername=(EditText)findViewById(R.id.editUserName);
		textpassword=(EditText)findViewById(R.id.editpasswd);
		textphone=(EditText)findViewById(R.id.editphno);
		addflty=(Button)findViewById(R.id.addfltybtn);
		
		addflty.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View v) 
			{
				// TODO Auto-generated method stub
				String username = textusername.getText().toString();
				String password = textpassword.getText().toString();
				String phoneNum = textphone.getText().toString();
				
				if (TextUtils.isEmpty(username)) {
					textusername.setError("please enter username");
				}
				else if (TextUtils.isEmpty(password)) {
					textpassword.setError("enter password");
				}
				else if (TextUtils.isEmpty(phoneNum)) {
						textphone.setError("enter phonenum");
				}
				else
				{
					faculity faculty = new faculity();
					faculty.setfacultyName(username);
					faculty.setfPassword(password);
					faculty.setContactNumber(phoneNum);
					
					DatabaseHelper dbAdapter = new DatabaseHelper(AddfacultyActivity.this);
					dbAdapter.addFaculty(faculty);
					
					Intent intent =new Intent(AddfacultyActivity.this,AdminMenuActivity.class);
					startActivity(intent);
					Toast.makeText(getApplicationContext(), "Faculty added successfully", Toast.LENGTH_SHORT).show();

				}
				
			}
			
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.addfaculty, menu);
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
