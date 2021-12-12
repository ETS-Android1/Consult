package com.example.consult;


import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Student_zoom extends Activity {

	
	String stu_no = "";
	String stu_name = "";
	String stu_phone = "";
	String stu_major = "";
	String p_no = "";
	Button ok,no;
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_student_zoom);
		
		 Intent intent = getIntent();
		 stu_no = intent.getStringExtra("stu_no");		
		 stu_name = intent.getStringExtra("stu_name");		
		 stu_major = intent.getStringExtra("stu_major");		
		 p_no = intent.getStringExtra("p_no");	
		 
		 ok= (Button)findViewById(R.id.button1);
			no= (Button)findViewById(R.id.button2);
		 
		 try{
				URL url_2 = new URL(SERVER_ADDRESS + "/stu_phone_check.php?"
						+ "stu_no=" + URLEncoder.encode(stu_no,"UTF-8")
						+ "&stu_name=" + URLEncoder.encode(stu_name,"UTF-8")
												
				);

				url_2.openStream();
				stu_phone = getXmlData("stu_phone_check.xml", "result");
			}
			catch(Exception e){
				Toast.makeText(Student_zoom.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());
			}// try catch 臾�醫낅즺 */

		 
		 TextView testView4 = (TextView)findViewById(R.id.textView23);
			testView4.setText(stu_name);
			TextView testView5 = (TextView)findViewById(R.id.textView24);
			testView5.setText(stu_no);
			TextView testView6 = (TextView)findViewById(R.id.textView25);
			testView6.setText(stu_major);
			TextView testView7 = (TextView)findViewById(R.id.textView26);
			testView7.setText(stu_phone);
		 
			
			
			
			
			
			
			no.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Student_zoom.this.finish();
					 Intent intent1 = new Intent(Student_zoom.this,My_student_list.class);
	        			intent1.putExtra("p_no",p_no);
	        		startActivity(intent1); 
				}
			});
	
			
			
			ok.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					new AlertDialog.Builder(Student_zoom.this)
        			.setTitle("학생삭제")
        			.setMessage(stu_no+" "+stu_name+"학생을 삭제 하시겠습니까?")
        			.setPositiveButton("예", new DialogInterface.OnClickListener() {
        				public void onClick(DialogInterface dialog, int which) {
        					
        					
        					
        					 try{//////////////////////////////예약 신청 / 취소
 								URL url = new URL(SERVER_ADDRESS + "/delete_student.php?"
 										+ "stu_no=" + URLEncoder.encode(stu_no,"UTF-8")
 										+ "&stu_name=" + URLEncoder.encode(stu_name,"UTF-8")
 										+ "&stu_major=" + URLEncoder.encode(stu_major,"UTF-8")
 										+ "&p_no=" + URLEncoder.encode(p_no,"UTF-8")
 										
 										);					
 								url.openStream();		
 								
 								
 							} catch(Exception e) {
 								Toast.makeText(Student_zoom.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
 								Log.e("Error", e.getMessage());
 							}
        					finish();
        					 Intent intent1 = new Intent(Student_zoom.this,My_student_list.class);
      	                   
     		        		
 		        			intent1.putExtra("p_no",p_no);
 		        		startActivity(intent1); 
        					 
        					
        					
        				}
        			})
        			.setNegativeButton("아니오", null)
        			.show();
                
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
		getMenuInflater().inflate(R.menu.student_zoom, menu);
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
