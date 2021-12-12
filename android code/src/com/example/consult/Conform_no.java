package com.example.consult;


import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Conform_no extends Activity {


	String ID = "";
	String Password = "";		// 받아올 아이들 미리 변수선언함
	Button ok,no,refresh,after,my_stu;
	String ok1,no1;
	String year = "";		// 받아올 아이들 미리 변수선언함
	String month = "";
	String day = "";
	String time = "";
	String t="";
	String p_no = "";
	String name = "";
	String stu_no = "";		// 받아올 아이들 미리 변수선언함
	String perpose = "";
	String major = "";
	String yesno="";
	String ready="";
	String check="";
	String stu_phone="";
	String a="2";
	String wait="";
	String again="0";
	int i=0;
	Context context;
	
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_conform_no);
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    context = this;
	    
	    Intent intent = getIntent();
		p_no = intent.getStringExtra("ID");		// ID값을 받아옴
		Password = intent.getStringExtra("Password");		// Password값을 받아옴
		again = intent.getStringExtra("again");		// Password값을 받아옴
		
		ok= (Button)findViewById(R.id.button1);
        no= (Button)findViewById(R.id.button2);
        refresh= (Button)findViewById(R.id.button3);
        after= (Button)findViewById(R.id.button4);
        my_stu= (Button)findViewById(R.id.button5);
    
     
       
		
	  if(again==null)
	  {
        Intent intent2;
		intent2 = new Intent(Conform_no.this, BackgroundSearch.class);
		intent2.putExtra("a", a);
		intent2.putExtra("p_no", p_no);
		intent2.putExtra("ID", ID);
		intent2.putExtra("Password", Password);
		startService(intent2);	
	  }
       
        
	
		
        try{//////////////////////////////예약 신청 / 취소
			URL url = new URL(SERVER_ADDRESS + "/admin_check_ready.php?"
					+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
					);					
			url.openStream();		
			String result = getXmlData("admin_check_ready.xml", "result");
			ready = result;
		} catch(Exception e) {
			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			Log.e("Error", e.getMessage());
		}
		if(ready.equals("0"))
		{
			i=1;
			Toast.makeText(Conform_no.this, "예약신청이 없습니다.", Toast.LENGTH_SHORT).show();
		}
        
        if(i==0)  // 불필요한 작업을 줄이기 위해서
	    {
        	try{//////////////////////////////예약 신청 / 취소 , admin_check_t으로 먼저 온 순서 찾기 (t)
    			URL url = new URL(SERVER_ADDRESS + "/admin_check_t.php?"
    					+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
    					);					
    			url.openStream();		
    			String result = getXmlData("admin_check_t.xml", "result");
    			t = result;
    		} catch(Exception e) {
    			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
    			Log.e("Error", e.getMessage());
    		}
	        
        	
        	 try{//////////////////////////////예약 신청 / 취소
 				URL url = new URL(SERVER_ADDRESS + "/reservation_check_first.php?"
 						+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
 						+ "&t=" + URLEncoder.encode(t,"UTF-8")						
 						);					
 				url.openStream();		
 				day = getXmlData("reservation_check_first.xml", "day");
 				yesno = getXmlData("reservation_check_first.xml", "yesno");
 				major = getXmlData("reservation_check_first.xml", "major");
 				month = getXmlData("reservation_check_first.xml", "month");
 				name = getXmlData("reservation_check_first.xml", "stu_name");
 				perpose = getXmlData("reservation_check_first.xml", "problem");
 				stu_no = getXmlData("reservation_check_first.xml", "stu_no");
 				time = getXmlData("reservation_check_first.xml", "time");
 				year = getXmlData("reservation_check_first.xml", "year");
 				stu_phone = getXmlData("reservation_check_first.xml", "stu_phone");
			
 			} catch(Exception e) {
 				Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
 				Log.e("Error", e.getMessage());
 			}
        	
        	 try{
 	    		URL url_3 = new URL(SERVER_ADDRESS + "/Conform_reservation_search.php?"
 	    				//+ "&birth=" + birth
 	    				//+ "&seat=" + seat);
 	    			
 	    				+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
 	    				
 	    				);
 	    		
 	    		url_3.openStream();
 	    		}
 	    		catch(Exception e){
 	    			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
 	    			//Log.e("Error", e.getMessage()); //onDestory();
 	    		}// try catch 臾?醫낅즺
 	    	wait = getXmlData("Conform_reservation_search/search_"+p_no+".xml", "result"); 	// 실제 대기자수

 			
        	
        	
        	
	        
	    }
        else if(i==1) //ready가 0이면 i =1
        {
        name="예약신청 없음";
        			
        }
        
        no.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CLoading.showLoading(context);    //show progress bar
				if(i==1)
				{
					Toast.makeText(Conform_no.this, "거절할 예약이 없습니다.", Toast.LENGTH_SHORT).show();					
				}
				else if(i==0)
				{
				//	Toast.makeText(Conform_no.this, p_no +"/"+ name+"/"+t, Toast.LENGTH_SHORT).show();					
					
				//	Toast.makeText(Conform_no.this, p_no +"/"+ name+"/"+t, Toast.LENGTH_SHORT).show();					
					
					try{//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/no.php?"
								+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
								+ "&name=" + URLEncoder.encode(name,"UTF-8")
								+ "&t=" + URLEncoder.encode(t,"UTF-8")				
								);					
						url.openStream();		
						String result = getXmlData("no.xml", "result");
						no1 = result;
					} catch(Exception e) {
						Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
					try{//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/no_check.php?"
								+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
								+ "&name=" + URLEncoder.encode(name,"UTF-8")
								+ "&t=" + URLEncoder.encode(t,"UTF-8")				
								);					
						url.openStream();		
						String result = getXmlData("no_check.xml", "result");
						no1 = result;
					} catch(Exception e) {
						Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
					if(no1.equals("1"))
					{
					
					Toast.makeText(Conform_no.this, "거절하였습니다.", Toast.LENGTH_SHORT).show();
					
					
					
					
					/////////////////////////////////////////////////////////////////

			      
			        try{
			    		URL url_3 = new URL(SERVER_ADDRESS + "/Conform_reservation_search.php?"
			    				//+ "&birth=" + birth
			    				//+ "&seat=" + seat);
			    			
			    				+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
			    				
			    				);
			    		
			    		url_3.openStream();
			    		}
			    		catch(Exception e){
			    			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			    			//Log.e("Error", e.getMessage()); //onDestory();
			    		}// try catch 臾?醫낅즺
			    	wait = getXmlData("Conform_reservation_search/search_"+p_no+".xml", "result"); 	// 실제 대기자수
			    
			        try{//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/admin_check_ready.php?"
								+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
								);					
						url.openStream();		
						String result = getXmlData("admin_check_ready.xml", "result");
						ready = result;
					} catch(Exception e) {
						Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
					if(ready.equals("0"))
					{
						i=1;
						Toast.makeText(Conform_no.this, "예약신청이 없습니다.", Toast.LENGTH_SHORT).show();
					}
			        
			        if(i==0)
				    {
			        	try{//////////////////////////////예약 신청 / 취소
			    			URL url = new URL(SERVER_ADDRESS + "/admin_check_t.php?"
			    					+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
			    					);					
			    			url.openStream();		
			    			String result = getXmlData("admin_check_t.xml", "result");
			    			t = result;
			    		} catch(Exception e) {
			    			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			    			Log.e("Error", e.getMessage());
			    		}
				        

			        	 try{//////////////////////////////예약 신청 / 취소
			 				URL url = new URL(SERVER_ADDRESS + "/reservation_check_first.php?"
			 						+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
			 						+ "&t=" + URLEncoder.encode(t,"UTF-8")						
			 						);					
			 				url.openStream();		
			 				day = getXmlData("reservation_check_first.xml", "day");
			 				yesno = getXmlData("reservation_check_first.xml", "yesno");
			 				major = getXmlData("reservation_check_first.xml", "major");
			 				month = getXmlData("reservation_check_first.xml", "month");
			 				name = getXmlData("reservation_check_first.xml", "stu_name");
			 				perpose = getXmlData("reservation_check_first.xml", "problem");
			 				stu_no = getXmlData("reservation_check_first.xml", "stu_no");
			 				time = getXmlData("reservation_check_first.xml", "time");
			 				year = getXmlData("reservation_check_first.xml", "year");
			 				stu_phone = getXmlData("reservation_check_first.xml", "stu_phone");
						
			 			} catch(Exception e) {
			 				Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			 				Log.e("Error", e.getMessage());
			 			}
				        
				    }
			        else if(i==1)
			        {
			        name="예약신청 없음";
			        year="";
			        month="";
			        day="";
			        time="";
			        stu_no="";
			        perpose="";
			        major="";
			       stu_phone="";
			        yesno="";
			        
			        }
			        
					

					TextView testView1 = (TextView)findViewById(R.id.textView1);
					testView1.setText(year+"/"+month+"/"+day);
					TextView testView2 = (TextView)findViewById(R.id.textView2);
					testView2.setText(time);
					TextView testView3 = (TextView)findViewById(R.id.textView3);
					testView3.setText(name);
					TextView testView4 = (TextView)findViewById(R.id.textView4);
					testView4.setText(stu_no);
					TextView testView5 = (TextView)findViewById(R.id.textView5);
					testView5.setText(perpose);
					TextView testView6 = (TextView)findViewById(R.id.textView6);
					testView6.setText(major);
					TextView testView7 = (TextView)findViewById(R.id.textView7);
					testView7.setText(stu_phone);
					if(yesno.equals("0"))
						yesno="예약성공";
					else if(yesno.equals("1"))
						yesno="예약대기중";
					else if(yesno.equals("2"))
						yesno="예약실패";
					TextView testView8 = (TextView)findViewById(R.id.textView8);
					testView8.setText(yesno);
					TextView testView9 = (TextView)findViewById(R.id.textView9);
					testView9.setText(wait+"명");
				
					
					
					
					
					
					
					
					
					
					//////////////////////////////////////////////////////////////////
					
					
					}
					else
						Toast.makeText(Conform_no.this, "일시적인 오류가 발생했습니다 잠시후에 다시 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
					
				}
				
				
				Handler handler = new Handler();
			       handler.postDelayed(new Runnable(){
			       	public void run(){
			       		CLoading.hideLoading();        //hide progress bar
				   	}// 핸들러 몇초 뒤에 실행하라 하기 위한 함수.
					},2000);//핸들러
				
				
				
			}
		});
		
        
        ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CLoading.showLoading(context);    //show progress bar
				if(i==1)
				{
					Toast.makeText(Conform_no.this, "수락할 예약이 없습니다.", Toast.LENGTH_SHORT).show();										
				}
				else if(i==0) {
					//		Toast.makeText(Conform_no.this, p_no +"/"+ name+"/"+t, Toast.LENGTH_SHORT).show();
				
					//		Toast.makeText(Conform_no.this, p_no +"/"+ name+"/"+t, Toast.LENGTH_SHORT).show();

					/////////////////////////////////////////////////////////////////////////////////


					try {//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/ok_before_check.php?"
								+ "p_no=" + URLEncoder.encode(p_no, "UTF-8")
								+ "&name=" + URLEncoder.encode(name, "UTF-8")
								+ "&t=" + URLEncoder.encode(t, "UTF-8")
								+ "&year=" + URLEncoder.encode(year, "UTF-8")
								+ "&month=" + URLEncoder.encode(month, "UTF-8")
								+ "&day=" + URLEncoder.encode(day, "UTF-8")
								+ "&time=" + URLEncoder.encode(time, "UTF-8")
						);
						url.openStream();
						String result = getXmlData("ok_before_check.xml", "result");
						check = result;
					} catch (Exception e) {
						Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}


					if (check.equals("0")) {


						////////////////////////////////////////////////////////////////////////////////
						try {//////////////////////////////예약 신청 / 취소
							URL url = new URL(SERVER_ADDRESS + "/ok.php?"
									+ "p_no=" + URLEncoder.encode(p_no, "UTF-8")
									+ "&name=" + URLEncoder.encode(name, "UTF-8")
									+ "&t=" + URLEncoder.encode(t, "UTF-8")
							);
							url.openStream();
							

						} catch (Exception e) {
							Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
							Log.e("Error", e.getMessage());
						}
						try {//////////////////////////////예약 신청 / 취소
							URL url = new URL(SERVER_ADDRESS + "/ok_check.php?"
									+ "p_no=" + URLEncoder.encode(p_no, "UTF-8")
									+ "&name=" + URLEncoder.encode(name, "UTF-8")
									+ "&t=" + URLEncoder.encode(t, "UTF-8")
							);
							url.openStream();
							String result = getXmlData("ok_check.xml", "result");
							ok1 = result;
						} catch (Exception e) {
							Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
							Log.e("Error", e.getMessage());
						}
						if (ok1.equals("1")) {
							Toast.makeText(Conform_no.this, "수락하였습니다.", Toast.LENGTH_SHORT).show();
							
							
							
							///////////////////////////////////////////////////////////////////////
							
							

					        
					      
					        try{
					    		URL url_3 = new URL(SERVER_ADDRESS + "/Conform_reservation_search.php?"
					    				//+ "&birth=" + birth
					    				//+ "&seat=" + seat);
					    			
					    				+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
					    				
					    				);
					    		
					    		url_3.openStream();
					    		}
					    		catch(Exception e){
					    			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					    			//Log.e("Error", e.getMessage()); //onDestory();
					    		}// try catch 臾?醫낅즺
					    	wait = getXmlData("Conform_reservation_search/search_"+p_no+".xml", "result"); 	// 실제 대기자수
					    	
					        try{//////////////////////////////예약 신청 / 취소
								URL url = new URL(SERVER_ADDRESS + "/admin_check_ready.php?"
										+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
										);					
								url.openStream();		
								String result = getXmlData("admin_check_ready.xml", "result");
								ready = result;
							} catch(Exception e) {
								Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
								Log.e("Error", e.getMessage());
							}
							if(ready.equals("0"))
							{
								i=1;
								Toast.makeText(Conform_no.this, "예약신청이 없습니다.", Toast.LENGTH_SHORT).show();
							}
					        
					        if(i==0)
						    {
					        	try{//////////////////////////////예약 신청 / 취소
					    			URL url = new URL(SERVER_ADDRESS + "/admin_check_t.php?"
					    					+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
					    					);					
					    			url.openStream();		
					    			String result = getXmlData("admin_check_t.xml", "result");
					    			t = result;
					    		} catch(Exception e) {
					    			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					    			Log.e("Error", e.getMessage());
					    		}
						        
					        	 try{//////////////////////////////예약 신청 / 취소
						 				URL url = new URL(SERVER_ADDRESS + "/reservation_check_first.php?"
						 						+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
						 						+ "&t=" + URLEncoder.encode(t,"UTF-8")						
						 						);					
						 				url.openStream();		
						 				day = getXmlData("reservation_check_first.xml", "day");
						 				yesno = getXmlData("reservation_check_first.xml", "yesno");
						 				major = getXmlData("reservation_check_first.xml", "major");
						 				month = getXmlData("reservation_check_first.xml", "month");
						 				name = getXmlData("reservation_check_first.xml", "stu_name");
						 				perpose = getXmlData("reservation_check_first.xml", "problem");
						 				stu_no = getXmlData("reservation_check_first.xml", "stu_no");
						 				time = getXmlData("reservation_check_first.xml", "time");
						 				year = getXmlData("reservation_check_first.xml", "year");
						 				stu_phone = getXmlData("reservation_check_first.xml", "stu_phone");
									
						 			} catch(Exception e) {
						 				Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						 				Log.e("Error", e.getMessage());
						 			}
						        
						    }
					        else if(i==1)
					        {
					        name="예약신청 없음";
					        year="";
					        month="";
					        day="";
					        time="";
					        stu_no="";
					        perpose="";
					        major="";
					         stu_phone="";
					        yesno="";
					        
					        			
					        }
					        
							

							TextView testView1 = (TextView)findViewById(R.id.textView1);
							testView1.setText(year+"/"+month+"/"+day);
							TextView testView2 = (TextView)findViewById(R.id.textView2);
							testView2.setText(time);
							TextView testView3 = (TextView)findViewById(R.id.textView3);
							testView3.setText(name);
							TextView testView4 = (TextView)findViewById(R.id.textView4);
							testView4.setText(stu_no);
							TextView testView5 = (TextView)findViewById(R.id.textView5);
							testView5.setText(perpose);
							TextView testView6 = (TextView)findViewById(R.id.textView6);
							testView6.setText(major);
							TextView testView7 = (TextView)findViewById(R.id.textView7);
							testView7.setText(stu_phone);
						
							if(yesno.equals("0"))
								yesno="예약성공";
							else if(yesno.equals("1"))
								yesno="예약대기중";
							else if(yesno.equals("2"))
								yesno="예약실패";
							TextView testView8 = (TextView)findViewById(R.id.textView8);
							testView8.setText(yesno);
							TextView testView9 = (TextView)findViewById(R.id.textView9);
							testView9.setText(wait+"명");
						
							
							
							
							
							
							
							
							
							
							///////////////////////////////////////////////////////////////////////
							
							
						} else
							Toast.makeText(Conform_no.this, "일시적인 오류가 발생했습니다 잠시후에 다시 시도해주시기 바랍니다.", Toast.LENGTH_SHORT).show();

					}
					else if(check.equals("1"))
						Toast.makeText(Conform_no.this, "같은시간에 예약이 이미있습니다. 거절해주세요.", Toast.LENGTH_SHORT).show();

				}
				
				
				Handler handler = new Handler();
			       handler.postDelayed(new Runnable(){
			       	public void run(){
			       		CLoading.hideLoading();        //hide progress bar
				   	}// 핸들러 몇초 뒤에 실행하라 하기 위한 함수.
					},2000);//핸들러
				
				
				
				
				
			}
		});
        
        
        
			/////////////////////////////////////////////////////////////////////////////after
			/////////////////////////////////////////////////////////////////////////////
			/////////////////////////////////////////////////////////////////////////////
			        
        
        
        
        after.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
					/////////////////////////////////////////////////////////////////////////////////



                Intent intent1 = new Intent(Conform_no.this,After_consult_student_list.class);
               
    			intent1.putExtra("p_no",p_no);//여긴 아이디를 보냅니다.
    			
    			startActivity(intent1);
            
    	//		Toast.makeText(Conform_no.this, p_no, Toast.LENGTH_SHORT).show();
				
				
			}
		});
        
        
        
        
        
        
        my_stu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
					/////////////////////////////////////////////////////////////////////////////////



                Intent intent1 = new Intent(Conform_no.this,My_student_list.class);
               
    			intent1.putExtra("p_no",p_no);//여긴 아이디를 보냅니다.
    			
    			startActivity(intent1);
            
    	//		Toast.makeText(Conform_no.this, p_no, Toast.LENGTH_SHORT).show();
				
				
			}
		});
        
        
        
        
        
        
        
        
        
        
        	/////////////////////////////////////////////////////////////////////////////refresh
        	/////////////////////////////////////////////////////////////////////////////
        	/////////////////////////////////////////////////////////////////////////////
        
        
        
        
        
 refresh.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				 try{//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/admin_check_ready.php?"
								+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
								);					
						url.openStream();		
						String result = getXmlData("admin_check_ready.xml", "result");
						ready = result;
					} catch(Exception e) {
						Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
					if(ready.equals("0"))
					{
						i=1;
						Toast.makeText(Conform_no.this, "예약신청이 없습니다.", Toast.LENGTH_SHORT).show();
					}
					else{
						i=0;
					}
				
				CLoading.showLoading(context);    //show progress bar
				if(i==1)
				{
					Toast.makeText(Conform_no.this, "예약이 없습니다.", Toast.LENGTH_SHORT).show();					
				}
				else if(i==0)
				{
				//	Toast.makeText(Conform_no.this, p_no +"/"+ name+"/"+t, Toast.LENGTH_SHORT).show();					
					
				//	Toast.makeText(Conform_no.this, p_no +"/"+ name+"/"+t, Toast.LENGTH_SHORT).show();					
					
				}	
				
					
					
					/////////////////////////////////////////////////////////////////

			        
			      
			        try{
			    		URL url_3 = new URL(SERVER_ADDRESS + "/Conform_reservation_search.php?"
			    				//+ "&birth=" + birth
			    				//+ "&seat=" + seat);
			    			
			    				+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
			    				
			    				);
			    		
			    		url_3.openStream();
			    		}
			    		catch(Exception e){
			    			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			    			//Log.e("Error", e.getMessage()); //onDestory();
			    		}// try catch 臾?醫낅즺
			    	wait = getXmlData("Conform_reservation_search/search_"+p_no+".xml", "result"); 	// 실제 대기자수
			    	
			        try{//////////////////////////////예약 신청 / 취소
						URL url = new URL(SERVER_ADDRESS + "/admin_check_ready.php?"
								+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
								);					
						url.openStream();		
						String result = getXmlData("admin_check_ready.xml", "result");
						ready = result;
					} catch(Exception e) {
						Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
					if(ready.equals("0"))
					{
						i=1;
						Toast.makeText(Conform_no.this, "예약신청이 없습니다.", Toast.LENGTH_SHORT).show();
					}
			        
			        if(i==0)
				    {
			        	try{//////////////////////////////예약 신청 / 취소
			    			URL url = new URL(SERVER_ADDRESS + "/admin_check_t.php?"
			    					+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
			    					);					
			    			url.openStream();		
			    			String result = getXmlData("admin_check_t.xml", "result");
			    			t = result;
			    		} catch(Exception e) {
			    			Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			    			Log.e("Error", e.getMessage());
			    		}
				        
			        	 try{//////////////////////////////예약 신청 / 취소
				 				URL url = new URL(SERVER_ADDRESS + "/reservation_check_first.php?"
				 						+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
				 						+ "&t=" + URLEncoder.encode(t,"UTF-8")						
				 						);					
				 				url.openStream();		
				 				day = getXmlData("reservation_check_first.xml", "day");
				 				yesno = getXmlData("reservation_check_first.xml", "yesno");
				 				major = getXmlData("reservation_check_first.xml", "major");
				 				month = getXmlData("reservation_check_first.xml", "month");
				 				name = getXmlData("reservation_check_first.xml", "stu_name");
				 				perpose = getXmlData("reservation_check_first.xml", "problem");
				 				stu_no = getXmlData("reservation_check_first.xml", "stu_no");
				 				time = getXmlData("reservation_check_first.xml", "time");
				 				year = getXmlData("reservation_check_first.xml", "year");
				 				stu_phone = getXmlData("reservation_check_first.xml", "stu_phone");
							
				 			} catch(Exception e) {
				 				Toast.makeText(Conform_no.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				 				Log.e("Error", e.getMessage());
				 			}
				        
				    }
			        else if(i==1)
			        {
			        name="예약신청 없음";
			        year="";
			        month="";
			        day="";
			        time="";
			        stu_no="";
			        perpose="";
			        major="";
			         stu_phone="";
			        yesno="";
			        
			        }
			        
					

					TextView testView1 = (TextView)findViewById(R.id.textView1);
					testView1.setText(year+"/"+month+"/"+day);
					TextView testView2 = (TextView)findViewById(R.id.textView2);
					testView2.setText(time);
					TextView testView3 = (TextView)findViewById(R.id.textView3);
					testView3.setText(name);
					TextView testView4 = (TextView)findViewById(R.id.textView4);
					testView4.setText(stu_no);
					TextView testView5 = (TextView)findViewById(R.id.textView5);
					testView5.setText(perpose);
					TextView testView6 = (TextView)findViewById(R.id.textView6);
					testView6.setText(major);
					TextView testView7 = (TextView)findViewById(R.id.textView7);
					testView7.setText(stu_phone);
				
					if(yesno.equals("0"))
						yesno="예약성공";
					else if(yesno.equals("1"))
						yesno="예약대기중";
					else if(yesno.equals("2"))
						yesno="예약실패";
					TextView testView8 = (TextView)findViewById(R.id.textView8);
					testView8.setText(yesno);
					TextView testView9 = (TextView)findViewById(R.id.textView9);
					testView9.setText(wait+"명");
				
					Toast.makeText(Conform_no.this, "새로고침 완료.", Toast.LENGTH_SHORT).show();
					
					Handler handler = new Handler();
				       handler.postDelayed(new Runnable(){
				       	public void run(){
				       		CLoading.hideLoading();        //hide progress bar
					   	}// 핸들러 몇초 뒤에 실행하라 하기 위한 함수.
						},2000);//핸들러
					
					
					
					
					
					
					//////////////////////////////////////////////////////////////////
					
					
					
				
				
				
				
				
				
			}
		});
        
        
        
        
        
        
        
        
        
        
        
        
        
        
		TextView testView1 = (TextView)findViewById(R.id.textView1);
		testView1.setText(year+"/"+month+"/"+day);
		TextView testView2 = (TextView)findViewById(R.id.textView2);
		testView2.setText(time);
		TextView testView3 = (TextView)findViewById(R.id.textView3);
		testView3.setText(name);
		TextView testView4 = (TextView)findViewById(R.id.textView4);
		testView4.setText(stu_no);
		TextView testView5 = (TextView)findViewById(R.id.textView5);
		testView5.setText(perpose);
		TextView testView6 = (TextView)findViewById(R.id.textView6);
		testView6.setText(major);
		//엑티비티 시작하자마자 실행됨, 한번 실행하고 끝.
		TextView testView7 = (TextView)findViewById(R.id.textView7);
		testView7.setText(stu_phone);
		if(yesno.equals("0"))
			yesno="예약성공";
		else if(yesno.equals("1"))
			yesno="예약대기중";
		else if(yesno.equals("2"))
			yesno="예약실패";
		TextView testView8 = (TextView)findViewById(R.id.textView8);
		testView8.setText(yesno);
		TextView testView9 = (TextView)findViewById(R.id.textView9);
		testView9.setText(wait+"명");
	
		
		
		
		
		
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
		getMenuInflater().inflate(R.menu.conform_no, menu);
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
