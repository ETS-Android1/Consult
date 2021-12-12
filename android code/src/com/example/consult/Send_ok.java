package com.example.consult;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.annotation.SuppressLint;
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
import android.widget.TextView;
import android.widget.Toast;

public class Send_ok extends Activity implements OnClickListener {

	String stu_no = "";
	String stu_name = "";
	String stu_password = "";
	String stu_major = "";
	String stu_phone = "";
	String p_name = "";
	String p_no = "";
	String year = "";		// 받아올 아이들 미리 변수선언함
	String month = "";
	String perpose= "";
	String dayOfMonth = "";
	String time = "";
	String yesno="";
	String ready="";
	String check="";
	
	
	ArrayList<String> data;
	ArrayAdapter<String> adapter; // 선언
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send_ok);
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);

		 Intent intent = getIntent();
		 stu_no = intent.getStringExtra("stu_no");		
		 stu_name = intent.getStringExtra("stu_name");		
		 stu_password = intent.getStringExtra("stu_password");		
		 stu_major = intent.getStringExtra("stu_major");		
		 stu_phone = intent.getStringExtra("stu_phone");
		 perpose = intent.getStringExtra("perpose");
		 p_name = intent.getStringExtra("p_name");	
		 p_no = intent.getStringExtra("p_no");	
		 yesno = intent.getStringExtra("yesno");	
		 time = intent.getStringExtra("time");	
		 year = intent.getStringExtra("year");		// year값을 받아옴
		 month = intent.getStringExtra("month");		// month값을 받아옴
		 dayOfMonth = intent.getStringExtra("dayOfMonth");		// dayOfMonth값을 받아옴
		 if(yesno==null)
		 yesno="예약대기중";
		 if(perpose==null)// 로그인 후에는 필요없고 예약 후에는 필요해서 불필요한 검색 없애기 위해서
			{
		 
			 try{//////////////////////////////예약 신청 / 취소
	 				URL url = new URL(SERVER_ADDRESS + "/reservation_check_student.php?"
	 						+ "stu_no=" + URLEncoder.encode(stu_no,"UTF-8")
	 						+ "&stu_password=" + URLEncoder.encode(stu_password,"UTF-8")
	 						+ "&year=" + URLEncoder.encode(year,"UTF-8")
	 						+ "&month=" + URLEncoder.encode(month,"UTF-8")
	 						+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
	 						+ "&time=" + URLEncoder.encode(time,"UTF-8")
	 						);					
	 				url.openStream();		
	 				perpose = getXmlData("stu_problem/reservation_check_student_"+stu_no+".xml", "problem");
	
	 			} catch(Exception e) {
	 				Toast.makeText(Send_ok.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
	 				Log.e("Error", e.getMessage());
	 			}
			
			 
			 
		
			 
			 
			 
			 
			 
			 
			
	
			}
			
			
			
			
			

			
			
			
			
			
			
			 Button bt1 = (Button)findViewById(R.id.button1);
				bt1.setOnClickListener(this);// 
				
				TextView testView1 = (TextView)findViewById(R.id.textView1);
				testView1.setText(year+"/"+month+"/"+dayOfMonth);
				TextView testView2 = (TextView)findViewById(R.id.textView2);
				testView2.setText(time);
				TextView testView3 = (TextView)findViewById(R.id.textView3);
				testView3.setText(p_name);
				TextView testView4 = (TextView)findViewById(R.id.textView4);
				testView4.setText(stu_name);
				TextView testView5 = (TextView)findViewById(R.id.textView5);
				testView5.setText(stu_no);
				TextView testView6 = (TextView)findViewById(R.id.textView6);
				testView6.setText(stu_major);
				TextView testView7 = (TextView)findViewById(R.id.textView7);
				testView7.setText(perpose);// 위에 서버에서 가져왔던 지정 값들을 보여줌
				TextView testView8 = (TextView)findViewById(R.id.textView8);
				testView8.setText(yesno);
				
				
	
		 
		 
		
		
	}

	
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.button1:
			finish();
			break;
		}
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
		getMenuInflater().inflate(R.menu.send_ok, menu);
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
