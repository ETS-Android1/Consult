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
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Personal_reservation_check extends Activity {


	String stu_no = "";
	String stu_name = "";
	String stu_password = "";
	String stu_major = "";
	String stu_phone = "";
	
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	String[] xml_array= new String[1000] ;
	int pop=0;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_reservation_check);
		
		 Intent intent = getIntent();
		 stu_no = intent.getStringExtra("stu_no");		
		 stu_name = intent.getStringExtra("stu_name");		
		 stu_password = intent.getStringExtra("stu_password");		
		 stu_major = intent.getStringExtra("stu_major");		
		 stu_phone = intent.getStringExtra("stu_phone");		
		
		
		 final LinearLayout lm = (LinearLayout) findViewById(R.id.ll);

			try{//////////////////////////////예약 신청 / 취소
				URL url = new URL(SERVER_ADDRESS + "/personal_reservation_check_list.php?"
						+ "stu_phone=" + URLEncoder.encode(stu_phone,"UTF-8")
						+ "&stu_no=" + URLEncoder.encode(stu_no,"UTF-8")
						);					
				url.openStream();		
				getXmlData2("personal_reservation_check_list/check_list_"+stu_phone+".xml");
		//		stu_name = getXmlData2("test.xml","stu_no");
		//		stu_password = getXmlData2("test.xml","stu_no");
		//		stu_major = getXmlData2("test.xml","stu_no");
		//		stu_phone = getXmlData2("test.xml","stu_no");
		
			} catch(Exception e) {
				Toast.makeText(Personal_reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());
			}
			
		
	//		Toast.makeText(Personal_login.this, xml_array[5], Toast.LENGTH_SHORT).show();
			
	
		 
		 
			 // linearLayout params 정의
	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	        		LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        params.gravity = Gravity.CENTER;
	        
	        for (int j = 0; j < pop; j++) {
	            // LinearLayout 생성
	            LinearLayout ll = new LinearLayout(this);
	            ll.setOrientation(LinearLayout.HORIZONTAL);

	            
	            
	            try{//////////////////////////////예약 신청 / 취소
					URL url = new URL(SERVER_ADDRESS + "/p_name_check.php?"
							+ "p_no=" + URLEncoder.encode(xml_array[j+4],"UTF-8")
							);					
					url.openStream();		
					xml_array[j+4]=getXmlData("p_name_check.xml","result");
			//		stu_name = getXmlData2("test.xml","stu_no");
			//		stu_password = getXmlData2("test.xml","stu_no");
			//		stu_major = getXmlData2("test.xml","stu_no");
			//		stu_phone = getXmlData2("test.xml","stu_no");
			
				} catch(Exception e) {
					Toast.makeText(Personal_reservation_check.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					Log.e("Error", e.getMessage());
				}
	            
	            
	            if(xml_array[j+5].equals("2"))
	            {
	            	xml_array[j+5]="거절";
	            }
	            if(xml_array[j+5].equals("1"))
	            {
	            	xml_array[j+5]="대기중";
	            }
	            if(xml_array[j+5].equals("0"))
	            {
	            	xml_array[j+5]="수락";
	            }
	            // TextView 생성
	    ///       TextView tvProdc = new TextView(this);
	    //        tvProdc.setText("Name" + j + " ");
	    //        ll.addView(tvProdc);

	            // TextView 생성
	    //        TextView tvAge = new TextView(this);
	    //        tvAge.setText("   Age" + j + "  ");
	    //        ll.addView(tvAge);

	            // 버튼 생성
	            final Button btn = new Button(this);
	            // setId 버튼에 대한 키값
	            btn.setId(j + 1);
	            btn.setText(xml_array[j]+"/"+xml_array[j+1]+"/"+xml_array[j+2]+" - "+xml_array[j+3]+"  "+xml_array[j+4]+"교수님 예약결과 : "+xml_array[j+5]);
	            btn.setLayoutParams(params);

	            final int position = j;

	            btn.setOnClickListener(new OnClickListener() {
	                public void onClick(View v) {
	               //     Log.d("log", "position :" + position);
	              //      Toast.makeText(getApplicationContext(), "클릭한 position:" + position, Toast.LENGTH_LONG).show();
	               
	                    Intent intent1 = new Intent(Personal_reservation_check.this,Send_ok.class);
	                   
	        			intent1.putExtra("stu_no",stu_no);//여긴 아이디를 보냅니다.
	        			intent1.putExtra("stu_password",stu_password);//여긴 비밀번호를 보냅니다.
	        			intent1.putExtra("stu_name",stu_name);//여긴 아이디를 보냅니다.
	        			intent1.putExtra("stu_major",stu_major);//여긴 비밀번호를 보냅니다.
	        			intent1.putExtra("stu_phone",stu_phone);//여긴 아이디를 보냅니다.
	        			intent1.putExtra("year",xml_array[position]);
	        			intent1.putExtra("month",xml_array[position+1]);
	        			intent1.putExtra("dayOfMonth",xml_array[position+2]);
	        			intent1.putExtra("time",xml_array[position+3]);
	        			intent1.putExtra("p_name",xml_array[position+4]);
	        			intent1.putExtra("yesno",xml_array[position+5]);
	        			startActivity(intent1);
	                
	                
	                
	                
	                
	                
	                }
	            });
	            
	            //버튼 add
	            ll.addView(btn);
	            //LinearLayout 정의된거 add
	            lm.addView(ll);
	            j=j+5;
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
		
	
	
	
	
	
	private String getXmlData2(String filename){
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
					eventType = xpp.next();
					
				}
				if(eventType == XmlPullParser.TEXT) {
					
					ret = xpp.getText();
					if(ret.equals("\n"))
					{
					//	Toast.makeText(Personal_login.this,"ret=한줄띄움", Toast.LENGTH_SHORT).show();
					}
					else
					{
						
			//		Toast.makeText(Personal_login.this,"ret="+ret, Toast.LENGTH_SHORT).show();
					xml_array[pop]= ret;
					pop++;
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
		getMenuInflater().inflate(R.menu.personal_reservation_check, menu);
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
