package com.example.smartattender;

import java.util.ArrayList;



import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ViewattendanceActivity extends Activity {

	ArrayList<AttendanceBean> attendanceBeanList;
	private ListView list ;  
	private ArrayAdapter<String> listAdapter;
	Context context;
	
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_viewattendance);
			context = this;
			DatabaseHelper db =  new DatabaseHelper(context);
			final ArrayList<String> a =   db.GetAttendancelist();

			list=(ListView)findViewById(R.id.listaa);
			ArrayAdapter adapter = new ArrayAdapter<String>(this, 
			         R.layout.view_attendance_list,a);
			list.setAdapter(adapter);

	}
}
