package com.example.consult;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Insert_student extends Activity {

	String p_no = "";
	String stu_no = "";
	String stu_name = "";
	String stu_major = "";
	String ID_result="";
	
	EditText edtno,edtname,edtmajor;
	Button ok,no;
	
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_insert_student);
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    
		
	    Intent intent = getIntent();
		p_no = intent.getStringExtra("p_no");		// ID값을 받아옴
	    
		ok= (Button)findViewById(R.id.button1);
        no= (Button)findViewById(R.id.button2);
		
		
		

        no.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		
        ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				edtno = (EditText)findViewById(R.id.stu_no);
				edtname = (EditText)findViewById(R.id.stu_name);
				edtmajor = (EditText)findViewById(R.id.stu_major);
			   
		      
			    if(edtno.getText().toString().equals("")||edtmajor.getText().toString().equals("")||
			    		edtname.getText().toString().equals("")){
					Toast.makeText(Insert_student.this, "다 적어주세요.", Toast.LENGTH_SHORT).show();
					return;
					
				}
			   	
			    runOnUiThread(new Runnable() {
					public void run() {
						
						 stu_no = edtno.getText().toString();
						 stu_name = edtname.getText().toString();
						 stu_major = edtmajor.getText().toString();
						
						 try{//////////////////////////////예약 신청 / 취소
								URL url = new URL(SERVER_ADDRESS + "/insert_student_check.php?"
										+ "stu_no=" + URLEncoder.encode(stu_no,"UTF-8")
										+ "&stu_name=" + URLEncoder.encode(stu_name,"UTF-8")
										+ "&stu_major=" + URLEncoder.encode(stu_major,"UTF-8")
										+ "&p_no=" + URLEncoder.encode(p_no,"UTF-8")
										
										);					
								url.openStream();		
								String result = getXmlData("insert_student_check.xml", "result");
								ID_result = result;
								
							} catch(Exception e) {
								Toast.makeText(Insert_student.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
								Log.e("Error", e.getMessage());
							}
						 if(ID_result.equals("1"))
						 {
						 try{//////////////////////////////예약 신청 / 취소
								URL url = new URL(SERVER_ADDRESS + "/insert_student.php?"
										+ "stu_no=" + URLEncoder.encode(stu_no,"UTF-8")
										+ "&stu_name=" + URLEncoder.encode(stu_name,"UTF-8")
										+ "&stu_major=" + URLEncoder.encode(stu_major,"UTF-8")
										+ "&p_no=" + URLEncoder.encode(p_no,"UTF-8")
										
										);					
								url.openStream();		
								
								
							} catch(Exception e) {
								Toast.makeText(Insert_student.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
								Log.e("Error", e.getMessage());
							}
						 
						 Intent intent1 = new Intent(Insert_student.this,My_student_list.class);
			    			intent1.putExtra("p_no",p_no);
				    		startActivity(intent1);
				    		Toast.makeText(Insert_student.this, stu_name+"학생이 추가되었습니다.", Toast.LENGTH_SHORT).show();
				    		finish();
							
						 }
						 else
						 {
							 Toast.makeText(Insert_student.this, "해당학번과 이름을 가진 사람이 없습니다.", Toast.LENGTH_SHORT).show();
						 return;
						 }
					}	 
			    });
				
				
				
				
			    
				
				
			}
		});
		
		
		
		
	}


	private String getXmlData(String filename, String str){
		String rss = SERVER_ADDRESS + "/";
		String ret = "";
		
		try{
			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			XmlPullParser xpp = factory.newPullParser();
			URL server = new URL(rss + filename);
			InputStream is = server.openStream();
			xpp.setInput(is, "UTF-8");
			
			int eventType = xpp.getEventType();
			
			while(eventType != XmlPullParser.END_DOCUMENT) {
				if(eventType == XmlPullParser.START_TAG) {
					if(xpp.getName().equals(str)) {
						ret = xpp.nextText();
					}
				}
				eventType = xpp.next();	
			}
		} catch(Exception e) {
			Log.e("Error", e.getMessage());
		}
		
		return ret;
	}
	
 
		
	 
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.insert_student, menu);
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
