package com.example.consult;


import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Insert_content_after_consult extends Activity implements OnClickListener {

	String p_no="";
	String stu_no = "";
	String stu_name = "";
	String stu_password = "";
	String stu_major = "";
	String stu_phone = "";
	String p_name = "";
	String year = "";		// 받아올 아이들 미리 변수선언함
	String month = "";
	String day = "";
	String time = "";
	String problem="";
	String content="";
	EditText edtContent;
	int a=0;
	
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	String[] xml_array= new String[1000] ;
	int pop=0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_content_after_consult);
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		
	    
	    Button bt1 = (Button)findViewById(R.id.button1);
		bt1.setOnClickListener(this);// 
	    Button bt2 = (Button)findViewById(R.id.button2);
		bt2.setOnClickListener(this);// 
		
		 Intent intent = getIntent();
		 p_no = intent.getStringExtra("p_no");		
		 year = intent.getStringExtra("year");	
		 month = intent.getStringExtra("month");	
		 day = intent.getStringExtra("day");	
		 time = intent.getStringExtra("time");	
		 stu_no = intent.getStringExtra("stu_no");	
		 stu_name = intent.getStringExtra("stu_name");	
		 problem = intent.getStringExtra("problem");	
		 stu_major = intent.getStringExtra("stu_major");	
		 content = intent.getStringExtra("content");	
		 if(content.equals("아직 작성되지 않았음."))
		 {
			 content="";
		 }
		 else 
			 a=1;
		 TextView testView1 = (TextView)findViewById(R.id.textView1);
			testView1.setText(year+"/"+month+"/"+day);
			TextView testView2 = (TextView)findViewById(R.id.textView2);
			testView2.setText(time);
			TextView testView3 = (TextView)findViewById(R.id.textView3);
			testView3.setText(stu_name);
			TextView testView4 = (TextView)findViewById(R.id.textView4);
			testView4.setText(stu_no);
			TextView testView5 = (TextView)findViewById(R.id.textView5);
			testView5.setText(problem);
		 edtContent = (EditText)findViewById(R.id.editText1);
		 edtContent.setText(content);
		 
		 
		 
		 
		 
		 
		 
		 
		
	}

	
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.button1:
			
			
			content=edtContent.getText().toString();
			
			
			if(a==0)
			{
				try{//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/insert_content.php?"
							+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
							+ "&year=" + URLEncoder.encode(year,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&stu_name=" + URLEncoder.encode(stu_name,"UTF-8")
							+ "&stu_major=" + URLEncoder.encode(stu_major,"UTF-8")
							+ "&stu_no=" + URLEncoder.encode(stu_no,"UTF-8")
							+ "&day=" + URLEncoder.encode(day,"UTF-8")
							+ "&time=" + URLEncoder.encode(time,"UTF-8")
							+ "&content=" + URLEncoder.encode(content,"UTF-8")
							);					
					url.openStream();		
					
	
				} catch(Exception e) {
					Toast.makeText(Insert_content_after_consult.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
			}
			else
			{
				try{//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/update_content.php?"
							+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
							+ "&year=" + URLEncoder.encode(year,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&stu_name=" + URLEncoder.encode(stu_name,"UTF-8")
							+ "&stu_major=" + URLEncoder.encode(stu_major,"UTF-8")
							+ "&stu_no=" + URLEncoder.encode(stu_no,"UTF-8")
							+ "&day=" + URLEncoder.encode(day,"UTF-8")
							+ "&time=" + URLEncoder.encode(time,"UTF-8")
							+ "&content=" + URLEncoder.encode(content,"UTF-8")
							);					
					url.openStream();		
					
	
				} catch(Exception e) {
					Toast.makeText(Insert_content_after_consult.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
			}
			
			
			
			
			
			Intent intent1 = new Intent(Insert_content_after_consult.this,After_consult.class);
            intent1.putExtra("year",year);
 			intent1.putExtra("month",month);
 			intent1.putExtra("day",day);
 			intent1.putExtra("time",time);
 			intent1.putExtra("stu_name",stu_name);
 			intent1.putExtra("stu_no",stu_no);
 			intent1.putExtra("problem",problem);
 			intent1.putExtra("stu_major",stu_major);
 		//	intent1.putExtra("content",content);
 			intent1.putExtra("p_no",p_no);
 			startActivity(intent1);
 			finish();
			break;
		case R.id.button2:
			Intent intent2 = new Intent(Insert_content_after_consult.this,After_consult.class);
            intent2.putExtra("year",year);
 			intent2.putExtra("month",month);
 			intent2.putExtra("day",day);
 			intent2.putExtra("time",time);
 			intent2.putExtra("stu_name",stu_name);
 			intent2.putExtra("stu_no",stu_no);
 			intent2.putExtra("problem",problem);
 			intent2.putExtra("stu_major",stu_major);
 		//	intent1.putExtra("content",content);
 			intent2.putExtra("p_no",p_no);
 			startActivity(intent2);
			finish();
			break;
		}
		
	}
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insert_content_after_consult, menu);
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
