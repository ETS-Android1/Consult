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

public class P_tab_consult_student_list extends Activity {

	
String p_no="";
String stu_no="";
String stu_name="";
String stu_major="";
	
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	String[] xml_array= new String[1000] ;
	int pop=0;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_p_tab_consult_student_list);
		
		 
		
		 final LinearLayout lm = (LinearLayout) findViewById(R.id.ll);
		 Intent intent = getIntent();
		 stu_no = intent.getStringExtra("stu_no");	
		 stu_name = intent.getStringExtra("stu_name");		
		 stu_major = intent.getStringExtra("stu_major");		
		 p_no = intent.getStringExtra("p_no");		
	//	 Toast.makeText(After_consult_student_list.this, "p_no="+p_no, Toast.LENGTH_SHORT).show();
		
		 try{//////////////////////////////예약 신청 / 취소
				URL url = new URL(SERVER_ADDRESS + "/professor_reservation_check_list2.php?"
						+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
						+ "&stu_no=" + URLEncoder.encode(stu_no,"UTF-8")
						+ "&stu_name=" + URLEncoder.encode(stu_name,"UTF-8")
						+ "&stu_major=" + URLEncoder.encode(stu_major,"UTF-8")
						);					
				url.openStream();		
				getXmlData2("professor_reservation_check_list/check_list2_"+p_no+".xml");
		//		stu_name = getXmlData2("test.xml","stu_no");
		//		stu_password = getXmlData2("test.xml","stu_no");
		//		stu_major = getXmlData2("test.xml","stu_no");
		//		stu_phone = getXmlData2("test.xml","stu_no");
		
			} catch(Exception e) {
				Toast.makeText(P_tab_consult_student_list.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
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
	            btn.setText(xml_array[j]+"/"+xml_array[j+1]+"/"+xml_array[j+2]+" - "+xml_array[j+3]+"   "+xml_array[j+5]+xml_array[j+4]
	            		+"  "+xml_array[j+7]+"(학과)  \n("+xml_array[j+6]+")");
	            btn.setLayoutParams(params);

	            final int position = j;

	            btn.setOnClickListener(new OnClickListener() {
	                public void onClick(View v) {
	               //     Log.d("log", "position :" + position);
	              //      Toast.makeText(getApplicationContext(), "클릭한 position:" + position, Toast.LENGTH_LONG).show();
	               
	                    Intent intent1 = new Intent(P_tab_consult_student_list.this,After_consult.class);
	                   
	                    intent1.putExtra("year",xml_array[position]);
	        			intent1.putExtra("month",xml_array[position+1]);
	        			intent1.putExtra("day",xml_array[position+2]);
	        			intent1.putExtra("time",xml_array[position+3]);
	        			intent1.putExtra("stu_name",xml_array[position+4]);
	        			intent1.putExtra("stu_no",xml_array[position+5]);
	        			intent1.putExtra("problem",xml_array[position+6]);
	        			intent1.putExtra("stu_major",xml_array[position+7]);
	        			intent1.putExtra("p_no",p_no);
	        			startActivity(intent1);
	                
	                
	                
	                
	                
	                
	                }
	            });
	            
	            //버튼 add
	            ll.addView(btn);
	            //LinearLayout 정의된거 add
	            lm.addView(ll);
	            j=j+7;
	        }
		 
		
		
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
		getMenuInflater().inflate(R.menu.p_tab_consult_student_list, menu);
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
