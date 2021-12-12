package com.example.consult;

import java.io.InputStream;
import java.net.URL;
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


public class Professor_list extends Activity {

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
		setContentView(R.layout.activity_professor_list);

		
		 Intent intent = getIntent();
		 stu_no = intent.getStringExtra("stu_no");		
		 stu_name = intent.getStringExtra("stu_name");		
		 stu_password = intent.getStringExtra("stu_password");		
		 stu_major = intent.getStringExtra("stu_major");		
		 stu_phone = intent.getStringExtra("stu_phone");		
			
		
		 final LinearLayout lm = (LinearLayout) findViewById(R.id.ll);

			try{//////////////////////////////예약 신청 / 취소
				URL url = new URL(SERVER_ADDRESS + "/p_name_list.php?"
						
						);					
				url.openStream();		
				getXmlData2("p_name_list.xml");
		//		stu_name = getXmlData2("test.xml","stu_no");
		//		stu_password = getXmlData2("test.xml","stu_no");
		//		stu_major = getXmlData2("test.xml","stu_no");
		//		stu_phone = getXmlData2("test.xml","stu_no");
		
			} catch(Exception e) {
				Toast.makeText(Professor_list.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());
			}
			
		
	//		Toast.makeText(Personal_login.this, xml_array[5], Toast.LENGTH_SHORT).show();
			int a=0;
			while(xml_array[a]!=null)
			{
		//		Toast.makeText(Professor_list.this,"xml_array["+a+"]="+ xml_array[a], Toast.LENGTH_SHORT).show();
				a++;
			}
			
	
		 
		 
			 // linearLayout params 정의
	        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
	        		LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
	        params.gravity = Gravity.CENTER;
	        
	        for (int j = 0; j < pop; j++) {
	            // LinearLayout 생성
	            LinearLayout ll = new LinearLayout(this);
	            ll.setOrientation(LinearLayout.HORIZONTAL);

	            
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
	            btn.setText(xml_array[j]+" 교수님 - "+xml_array[j+1]);
	            btn.setLayoutParams(params);

	            final int position = j;

	            btn.setOnClickListener(new OnClickListener() {
	                public void onClick(View v) {
	               //     Log.d("log", "position :" + position);
	              //      Toast.makeText(getApplicationContext(), "클릭한 position:" + position, Toast.LENGTH_LONG).show();
	               
	                    Intent intent1 = new Intent(Professor_list.this,Calendar.class);
	                   
	        			intent1.putExtra("stu_no",stu_no);//여긴 아이디를 보냅니다.
	        			intent1.putExtra("stu_password",stu_password);//여긴 비밀번호를 보냅니다.
	        			intent1.putExtra("stu_name",stu_name);//여긴 아이디를 보냅니다.
	        			intent1.putExtra("stu_major",stu_major);//여긴 비밀번호를 보냅니다.
	        			intent1.putExtra("stu_phone",stu_phone);//여긴 아이디를 보냅니다.
	        			intent1.putExtra("p_name",xml_array[position]);
	        			startActivity(intent1);
	                
	                
	                
	                
	                
	                
	                }
	            });
	            
	            //버튼 add
	            ll.addView(btn);
	            //LinearLayout 정의된거 add
	            lm.addView(ll);
	            j++;
	        }
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 
		 /*
		 
		
		Button bt1 = (Button)findViewById(R.id.button1);
		bt1.setOnClickListener(this);
		Button bt2 = (Button)findViewById(R.id.button2);
		bt2.setOnClickListener(this);
		Button bt3 = (Button)findViewById(R.id.button3);
		bt3.setOnClickListener(this);
		
		*/
		
	}
/*
	
	public void onClick(View v)
	{
		
		switch (v.getId()) {
		case R.id.button1:
			Intent intent1 = new Intent(this,Calendar.class);
			intent1.putExtra("stu_no",stu_no);//여긴 아이디를 보냅니다.
			intent1.putExtra("stu_password",stu_password);//여긴 비밀번호를 보냅니다.
			intent1.putExtra("stu_name",stu_name);//여긴 아이디를 보냅니다.
			intent1.putExtra("stu_major",stu_major);//여긴 비밀번호를 보냅니다.
			intent1.putExtra("stu_phone",stu_phone);//여긴 아이디를 보냅니다.
			intent1.putExtra("p_name","김광수");
			startActivity(intent1);			
			break;

		case R.id.button2:
			Intent intent2 = new Intent(this,Calendar.class);
			intent2.putExtra("stu_no",stu_no);//여긴 아이디를 보냅니다.
			intent2.putExtra("stu_password",stu_password);//여긴 비밀번호를 보냅니다.
			intent2.putExtra("stu_name",stu_name);//여긴 아이디를 보냅니다.
			intent2.putExtra("stu_major",stu_major);//여긴 비밀번호를 보냅니다.
			intent2.putExtra("stu_phone",stu_phone);//여긴 아이디를 보냅니다.
			intent2.putExtra("p_name","진흥길");
			startActivity(intent2);		
			break;
				
		case R.id.button3:
			Intent intent3 = new Intent(this,Calendar.class);
			intent3.putExtra("stu_no",stu_no);//여긴 아이디를 보냅니다.
			intent3.putExtra("stu_password",stu_password);//여긴 비밀번호를 보냅니다.
			intent3.putExtra("stu_name",stu_name);//여긴 아이디를 보냅니다.
			intent3.putExtra("stu_major",stu_major);//여긴 비밀번호를 보냅니다.
			intent3.putExtra("stu_phone",stu_phone);//여긴 아이디를 보냅니다.
			intent3.putExtra("p_name","은창식");
			startActivity(intent3);		
			break;
		}		
		
		
		Handler handler = new Handler();
	       handler.postDelayed(new Runnable(){
	       	public void run(){
	       		CLoading.hideLoading();        //hide progress bar
	      	}// 핸들러 몇초 뒤에 실행하라 하기 위한 함수.
	      },2000);//핸들러


		
		
		
		
	}
	*/
	
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
		getMenuInflater().inflate(R.menu.professor_list, menu);
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
