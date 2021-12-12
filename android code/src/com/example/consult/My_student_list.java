package com.example.consult;


import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import android.app.AlertDialog;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
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
import android.widget.TextView;
import android.widget.Toast;

public class My_student_list extends Activity {

	
	String p_no = "";
	Button create;
	String[] xml_array= new String[1000] ;
	int pop=0;
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_student_list);
		
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    
	    
	    create= (Button)findViewById(R.id.button1);
	    final LinearLayout lm = (LinearLayout) findViewById(R.id.ll);
		 Intent intent = getIntent();
			p_no = intent.getStringExtra("p_no");		// ID값을 받아옴
		
		
			
			
			try{//////////////////////////////예약 신청 / 취소
				URL url = new URL(SERVER_ADDRESS + "/my_student_check_list.php?"
						+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
						);					
				url.openStream();		
				getXmlData2("my_student/my_student_check_list_"+p_no+".xml");
		
		
			} catch(Exception e) {
				Toast.makeText(My_student_list.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());
			}
			
			
			
			
			 LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
		        		LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
		        params.gravity = Gravity.CENTER;
		        params.topMargin=10;
		        params.bottomMargin=10;
		        params.rightMargin=3;
		        params.weight=15;
	        for (int j = 0; j < pop; j++) {
	            // LinearLayout 생성
	            LinearLayout ll = new LinearLayout(this);
	            ll.setOrientation(LinearLayout.HORIZONTAL);

	            
	            // TextView 생성
	            TextView tvProdc = new TextView(this);
	            tvProdc.setText(xml_array[j+1]+"     ");
	            tvProdc.setLayoutParams(params);
	            tvProdc.setTextSize(20);
	            
	            ll.addView(tvProdc);
	            
	            // TextView 생성
	            TextView tvAge = new TextView(this);
	            tvAge.setText(xml_array[j]+"     ");
	            tvAge.setLayoutParams(params);
	            tvAge.setTextSize(20);
	            ll.addView(tvAge);

	            TextView tvmajor = new TextView(this);
	            tvmajor.setText(xml_array[j+2]+"     ");
	            tvmajor.setLayoutParams(params);
	            tvmajor.setTextSize(20);
	            ll.addView(tvmajor);
	            
	            // 버튼 생성
	            final Button btn = new Button(this);
	            // setId 버튼에 대한 키값
	    //        btn.setId(j + 1);
	            btn.setText("자세히");
	            btn.setLayoutParams(params);

	            final int position = j;

	            btn.setOnClickListener(new OnClickListener() {
	                public void onClick(View v) {
	               //     Log.d("log", "position :" + position);
	              //      Toast.makeText(getApplicationContext(), "클릭한 position:" + position, Toast.LENGTH_LONG).show();
	               
	                    Intent intent1 = new Intent(My_student_list.this,Student_zoom.class);
	                   
	        			intent1.putExtra("stu_no",xml_array[position+1]);//여긴 아이디를 보냅니다.
	        			intent1.putExtra("stu_name",xml_array[position]);//여긴 아이디를 보냅니다.
	        			intent1.putExtra("stu_major",xml_array[position+2]);//여긴 비밀번호를 보냅니다.
	        			intent1.putExtra("p_no",p_no);
	        			startActivity(intent1);
	                
	        			
	        					finish();
	        					
	        					 
	        					
	        					
	        			
	                
	                
	                
	                }
	            });
	            
	            //버튼 add
	            ll.addView(btn);
	            
	            
	            
	            final Button btn2 = new Button(this);
	            // setId 버튼에 대한 키값
	    //        btn2.setId(j + 2);
	            btn2.setText("상담기록");
	            btn2.setLayoutParams(params);

	            btn2.setOnClickListener(new OnClickListener() {
	                public void onClick(View v) {
	               //     Log.d("log", "position :" + position);
	              //      Toast.makeText(getApplicationContext(), "클릭한 position:" + position, Toast.LENGTH_LONG).show();
	               
	             //       Intent intent1 = new Intent(My_student_list.this,Calendar.class);
	                   
	        	//		intent1.putExtra("stu_no",xml_array[position+1]);//여긴 아이디를 보냅니다.
	        		//	intent1.putExtra("stu_name",xml_array[position]);//여긴 아이디를 보냅니다.
	        	//		intent1.putExtra("stu_major",xml_array[position+2]);//여긴 비밀번호를 보냅니다.
	        	//		intent1.putExtra("p_no",p_no);
	        	//		startActivity(intent1);
	                
	        			new AlertDialog.Builder(My_student_list.this)
	        			.setTitle("상담기록확인")
	        			.setMessage(xml_array[position+1]+" "+xml_array[position]+"학생의 상담기록을 확인하시겠습니까?")
	        			.setPositiveButton("예", new DialogInterface.OnClickListener() {
	        				public void onClick(DialogInterface dialog, int which) {
	        					
	        					
	        					
	        					 Intent intent1 = new Intent(My_student_list.this,P_tab_consult_student_list.class);
		 		        			intent1.putExtra("p_no",p_no);
		 		        			intent1.putExtra("stu_no",xml_array[position+1]);
		 		        			intent1.putExtra("stu_name",xml_array[position]);
		 		        			intent1.putExtra("stu_major",xml_array[position+2]);
		 		        		startActivity(intent1); 
	        					
	        					
	        					
	        				}
	        			})
	        			.setNegativeButton("아니오", null)
	        			.show();
	                
	                
	                
	                
	                
	                
	                }
	            });
	            
	            //버튼 add
	            ll.addView(btn2);
	            
	            //LinearLayout 정의된거 add
	            lm.addView(ll);
	            j=j+2;
	        }
			
	       
			
	        create.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub


					
					
			       Intent intent1 = new Intent(My_student_list.this,Insert_student.class);
	                   
		        		
		        			intent1.putExtra("p_no",p_no);
		        		startActivity(intent1);
					
					finish();
					
					
					
					
				}
			});
			
			
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
		getMenuInflater().inflate(R.menu.my_student_list, menu);
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
