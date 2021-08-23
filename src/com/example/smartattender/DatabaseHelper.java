package com.example.smartattender;

import java.util.ArrayList;






import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper
{
	private static String DB_PATH = "/data/Android/databases/";
	private static String DB_NAME = "ATTENDANCE";
	private static final int DATABASE_VERSION = 1;
	private final Context DBContext;
	
	public DatabaseHelper(Context context)
	{
		super(context, DB_NAME, null,DATABASE_VERSION);
		this.DBContext = context;
	}	

	/** Database Table Names **/
	public static final String FACULTY_TABLE = "facultyTable";
	public static final String STUDENT_TABLE = "StudentTable";
	private static final String ATTENDANCE_SESSION_TABLE = "attendance_session_table";
	private static final String ATTENDANCE_TABLE = "attendance_table";
	

    // faculty Table fields
    public static final String FACULTY_ID = "facultyID";
    public static final String FACULTY_NAME = "facultyName";
    public static final String PASSWORD = "fPassword";
  
    public static final String CONTACT_NUMBER = "ContactNumber";
    
 // student Table fields
    public static final String STUDENT_ID = "StudentID";
    public static final String STUDENT_NAME = "Name";
    public static final String STUDENT_REG = "Reg_no";
	private static final String STUDENT_DEPARTMENT = "student_department";
	private static final String STUDENT_CLASS = "student_class";
    
 // Attendance sessionTable fields
	private static final String SESSION_ID = "attendance_session_id";
	private static final String SESSION_FACULTY_ID = "attendance_session_faculty_id";
	private static final String SESSION_DEPARTMENT = "attendance_session_department";
	private static final String SESSION_CLASS = "attendance_session_class";
	private static final String SESSION_DATE = "attendance_session_date";
	private static final String SESSION_SUBJECT = "attendance_session_subject";
// student Table fields
	private static final String KEY_SESSION_ID = "attendance_session_id";
	private static final String ATTENDANCE_STUDENT_ID = "attendance_student_id";
	private static final String ATTENDANCE_STATUS = "attendance_status";

    
	@Override
	public void onCreate(SQLiteDatabase db)
	{	
		String CREATE_FACULTY_TABLE = "CREATE TABLE "+ FACULTY_TABLE + "("
				+ FACULTY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//0
				+ FACULTY_NAME + " NVARCHAR(50) , " //1
				+ PASSWORD + " NVARCHAR(50), "//2
				+ CONTACT_NUMBER + " NVARCHAR(20) )";//3
		Log.d("queryFaculty",CREATE_FACULTY_TABLE);
		
		String CREATE_STUDENT_TABLE = "CREATE TABLE "+ STUDENT_TABLE + "("
				+ STUDENT_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "//0
				+ STUDENT_REG + " NVARCHAR(50) UNIQUE, "//1
				+ STUDENT_NAME + " NVARCHAR(50) , " //2
				+ STUDENT_DEPARTMENT + " TEXT," 
				+STUDENT_CLASS + " TEXT " + ")"; //3
		Log.d("queryFaculty",CREATE_STUDENT_TABLE);
		
		
		String queryAttendanceSession="CREATE TABLE "+ ATTENDANCE_SESSION_TABLE +" (" +
				SESSION_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
				SESSION_FACULTY_ID + " INTEGER, " + 
				SESSION_DEPARTMENT + " TEXT, " +
				SESSION_CLASS + " TEXT, " +
				SESSION_DATE + " DATE," +
				SESSION_SUBJECT + " TEXT" + ")";
		Log.d("queryAttendanceSession",queryAttendanceSession );


		String queryAttendance="CREATE TABLE "+ ATTENDANCE_TABLE +" (" +
				KEY_SESSION_ID + " INTEGER, " +
				ATTENDANCE_STUDENT_ID + " INTEGER, " +
				ATTENDANCE_STATUS + " TEXT " + ")";
		Log.d("queryAttendance",queryAttendance );
				
		try
		{
			// creating all tables
			db.execSQL(CREATE_FACULTY_TABLE);
			db.execSQL(CREATE_STUDENT_TABLE);
			db.execSQL(queryAttendanceSession);
			db.execSQL(queryAttendance);
		
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}


	//deleting the duplicate tables
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	{
		try
		{
			db.execSQL("DROP TABLE IF EXISTS " + FACULTY_TABLE);
			db.execSQL("DROP TABLE IF EXISTS " + STUDENT_TABLE);
			
			
			// create new tables
			onCreate(db);
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}
	
	public ArrayList<String> GetAllFacultylist()
	{
		ArrayList<String> Faculty = new ArrayList<String>() ;
		try
		{
			
			SQLiteDatabase db = this.getWritableDatabase();
			
			String query = "SELECT " + FACULTY_NAME +" FROM facultyTable ";
			Cursor cursor = db.rawQuery(query, null);
			if(cursor.moveToFirst()) 
			{
			
				do
				{
					Faculty.add(cursor.getString(0));
					
				}while(cursor.moveToNext());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return Faculty;
	}
	public ArrayList<String> GetAllStudentslist(String branch, String year)
	{
		ArrayList<String> list = new ArrayList<String>() ;
		try
		{
			
			SQLiteDatabase db = this.getWritableDatabase();
			
			String query = "SELECT " + STUDENT_REG +" FROM StudentTable where student_department='"+branch+"' and student_class='"+year+"'";
			Cursor cursor = db.rawQuery(query, null);
			if(cursor.moveToFirst()) 
			{
			
				do
				{
					
					list.add(cursor.getString(0));
					
				}while(cursor.moveToNext());
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
		
	public void addFaculty(faculity faculty) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO facultyTable (facultyName,fPassword,ContactNumber) values ('"+ 
				
				faculty.getfacultyName()+"', '"+
				faculty.getfPassword() +"', '"+
				faculty.getContactNumber() + "')";
		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}
	
	public faculity validateFaculty(String username,String password)
	{

		SQLiteDatabase db = this.getWritableDatabase();
		
		String query = "SELECT * FROM facultyTable where facultyName='"+username +"' and fPassword='"+password+"'";
		Cursor cursor = db.rawQuery(query, null);

	
		
		if(cursor.moveToFirst()) 
		{
			
				faculity faculty = new faculity();
				
				faculty.setfacultyName(cursor.getString(0));
				faculty.setfPassword(cursor.getString(1));
				
				
				return faculty;
		}
		
			return null;
		
		
	
	}
	
	
	
	public int getuserid(String username)
	{
		try
		{
			SQLiteDatabase read = this.getReadableDatabase();
			Cursor  c = read.rawQuery(" select " + FACULTY_ID + " from "+ 
					FACULTY_TABLE + " where " + FACULTY_NAME + " ='" +
					username + "'", null);
			if(c.moveToFirst())
			{
				//SessionManagement session = new SessionManagement(DBContext);
				//session.insertUserId(c.getInt(0));
				return c.getInt(0);	
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return 0;
	}


	public void addStudent(student studentBean) 
	{
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO studenttable (Reg_no,Name,student_department,student_class) values ('"+ 
				studentBean.getStudent_Reg()+"', '"+
				studentBean.getStudent_name()+"','"+
				studentBean.getStudent_department()+"', '"+
				studentBean.getStudent_class()+"')";
		Log.d("query", query);
		db.execSQL(query);
		db.close();
		
	}
	public void addNewAttendance(AttendanceBean attendanceBean) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO attendance_table values ("+ 
				attendanceBean.getAttendance_session_id()+", "+
				attendanceBean.getAttendance_student_id()+", '"+
				attendanceBean.getAttendance_status()+"')";
		Log.d("query", query);
		db.execSQL(query);
		db.close();
	}
	public int addAttendanceSession(AttendanceSessionBean attendanceSessionBean) {
		SQLiteDatabase db = this.getWritableDatabase();

		String query = "INSERT INTO attendance_session_table (attendance_session_faculty_id,attendance_session_department,attendance_session_class,attendance_session_date,attendance_session_subject) values ('"+ 
				attendanceSessionBean.getAttendance_session_faculty_id()+"', '"+
				attendanceSessionBean.getAttendance_session_department()+"','"+
				attendanceSessionBean.getAttendance_session_class()+"', '"+
				attendanceSessionBean.getAttendance_session_date()+"', '"+
				attendanceSessionBean.getAttendance_session_subject()+"')";
		Log.d("query", query);
		db.execSQL(query);

		String query1= "select max(attendance_session_id) from attendance_session_table";
		Cursor cursor = db.rawQuery(query1, null);
		
		if(cursor.moveToFirst())
		{
			int sessionId = Integer.parseInt(cursor.getString(0));
			
			return sessionId;
		}
			
		
		db.close();
		return 0;
	}
	public student getStudentById(int studentId)
	{
		student studentBean = new student();
		SQLiteDatabase db = this.getWritableDatabase();
		String query = "SELECT * FROM student_table where student_id="+studentId;
		Cursor cursor = db.rawQuery(query, null);

		if(cursor.moveToFirst()) 
		{
			do{
				
				studentBean.setStudent_id(Integer.parseInt(cursor.getString(0)));
				studentBean.setStudent_Reg(cursor.getString(1));
				studentBean.setStudent_name(cursor.getString(2));
				studentBean.setStudent_department(cursor.getString(3));
				studentBean.setStudent_class(cursor.getString(4));
				
			}while(cursor.moveToNext());
		}
		return studentBean;
	}


	public ArrayList<String> GetAttendancelist() 
	{
	ArrayList<String> attendance = new ArrayList<String>() ;
	try
	{
		
		SQLiteDatabase db = this.getWritableDatabase();
		
		String query = "SELECT "+KEY_SESSION_ID+","+ATTENDANCE_STUDENT_ID+" , "+ ATTENDANCE_STATUS +" FROM attendance_table ";
		Cursor cursor = db.rawQuery(query, null);
		if(cursor.moveToFirst()) 
		{
			attendance.add("Id | StudentName |  Status");
		
			do
			{
				attendance.add(cursor.getString(0)+ "				|	 " + cursor.getString(1) + "				|	 " + cursor.getString(2));
				//attendance.add(cursor.getString(1));
				//attendance.add(cursor.getString(2));
				
			}while(cursor.moveToNext());
		}
	}
	catch(Exception e)
	{
		e.printStackTrace();
	}
	return attendance;	
	}
	
	
	/*public boolean checkduplicationofusernames(String name)
	{
		try
		{
			SQLiteDatabase read = this.getReadableDatabase();
			Cursor  c = read.rawQuery(" select " + FACULTY_ID + " from "+
					FACULTY_TABLE + " where " + 
					FACULTY_NAME + " ='" + name + "'", null);
			if(c.moveToFirst())
			{
				return false;
			}
			else
			{
				return true;
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return false;
	}*/
}
