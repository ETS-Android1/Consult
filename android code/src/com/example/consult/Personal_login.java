package com.example.consult;


import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Handler;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Personal_login extends Activity {

	
	Context context;
	String ID_result="";
	String ID = "";
	String P = "";
	String p_no = "";
	String Password = "";		// 받아올 아이들 미리 변수선언함
	String stu_no = "";
	String stu_name = "";
	String stu_password = "";
	String stu_major = "";
	String stu_phone = "";
	EditText edtID,edtPassword,edtP;
	Button ok,create,search;
	String Cname = "";
	String a="2";
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	Cursor cursor;
	Cursor mCursor = null;
	String[] xml_array= new String[1000] ;
	int pop=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personal_login);
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    context = this;
																
		ok= (Button)findViewById(R.id.button1);
        create= (Button)findViewById(R.id.button2);
        search= (Button)findViewById(R.id.button3);
		
        create.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent(Personal_login.this,Create_student.class);
				startActivity(intent2);		
				
			}
		});
		
        
        search.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CLoading.showLoading(context);    //show progress bar
				Handler handler = new Handler();
			       handler.postDelayed(new Runnable(){
			       	public void run(){
						edtID = (EditText)findViewById(R.id.ID);
					    edtPassword = (EditText)findViewById(R.id.Password);
						
						
						if(edtID.getText().toString().equals("")||edtPassword.getText().toString().equals("")){
							Toast.makeText(Personal_login.this, "다 적어주세요.", Toast.LENGTH_SHORT).show();
							CLoading.hideLoading();        //hide progress bar
							return;
							
						}
						runOnUiThread(new Runnable() {
							public void run() {
						
						
						
						stu_no = edtID.getText().toString();
						stu_password = edtPassword.getText().toString();
							
						
						
						try{//////////////////////////////예약 신청 / 취소
							URL url = new URL(SERVER_ADDRESS + "/student_login.php?"
									+ "ID=" + URLEncoder.encode(stu_no,"UTF-8")
									+ "&Password=" + URLEncoder.encode(stu_password,"UTF-8")
									);					
							url.openStream();		
							String result = getXmlData("student_login.xml", "result");
							ID_result = result;
							stu_name= getXmlData("student_login.xml", "result2");
							stu_major = getXmlData("student_login.xml", "result3");
							stu_phone = getXmlData("student_login.xml", "result4");
						} catch(Exception e) {
							Toast.makeText(Personal_login.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
							Log.e("Error", e.getMessage());
						}
						
						if(ID_result.equals("1"))
						{
							 
							  
					      
					        
					    	
							
						  
					      /*  
					        Intent intent2;
							intent2 = new Intent(Login.this, BackgroundSearch.class);
							intent2.putExtra("a", a);
							intent2.putExtra("Cname", Cname);
							intent2.putExtra("ID", ID);
							intent2.putExtra("PW", Password);
							startService(intent2);	
						      */
							Toast.makeText(Personal_login.this, "로그인 성공.", Toast.LENGTH_SHORT).show();
							
							Intent intent1 = new Intent(Personal_login.this,Personal_reservation_check.class);
							
							intent1.putExtra("stu_no",stu_no);//여긴 아이디를 보냅니다.
							intent1.putExtra("stu_password",stu_password);//여긴 비밀번호를 보냅니다.
							intent1.putExtra("stu_name",stu_name);//여긴 아이디를 보냅니다.
							intent1.putExtra("stu_major",stu_major);//여긴 비밀번호를 보냅니다.
							intent1.putExtra("stu_phone",stu_phone);//여긴 아이디를 보냅니다.
							
							
							startActivity(intent1);		
							
							edtID.setText("");
							edtPassword.setText("");
							edtP.setText("");
							
							
							
						}
						else if(ID_result.equals("0"))
						{
							Toast.makeText(Personal_login.this, "아이디 혹은 비밀번호 오류입니다.", Toast.LENGTH_SHORT).show();
							
						
						}
							} //run 醫낅즺
						});
						CLoading.hideLoading();        //hide progress bar
				   	}// 핸들러 몇초 뒤에 실행하라 하기 위한 함수.
				},100);//핸들러
				
				
				
				
				
			}
		});
        
        
        ok.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				CLoading.showLoading(context);    //show progress bar
				Handler handler = new Handler();
			       handler.postDelayed(new Runnable(){
			       	public void run(){
						edtID = (EditText)findViewById(R.id.ID);
					    edtPassword = (EditText)findViewById(R.id.Password);
					    edtP = (EditText)findViewById(R.id.P);
						
						if(edtID.getText().toString().equals("")||edtPassword.getText().toString().equals("")
								||edtP.getText().toString().equals("")){
							Toast.makeText(Personal_login.this, "교수님 이름까지 다 적어주세요.", Toast.LENGTH_SHORT).show();
							CLoading.hideLoading();        //hide progress bar
							return;
							
						}
						runOnUiThread(new Runnable() {
							public void run() {
						
						
						
						stu_no = edtID.getText().toString();
						stu_password = edtPassword.getText().toString();
						P = edtP.getText().toString();	
						
						
						
						try{//////////////////////////////예약 신청 / 취소
							URL url = new URL(SERVER_ADDRESS + "/p_no_check.php?"
									+ "p_name=" + URLEncoder.encode(P,"UTF-8")
									+ "&Password=" + URLEncoder.encode(stu_password,"UTF-8")
									);					
							url.openStream();		
							String result = getXmlData("p_no_check.xml", "result");
							p_no = result;
						} catch(Exception e) {
							Toast.makeText(Personal_login.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
							Log.e("Error", e.getMessage());
						}
						
						
						if(p_no.equals(""))
						{
							Toast.makeText(Personal_login.this, "교수님 이름을 제대로 입력해주세요 등록되어있지 않은 이름입니다.", Toast.LENGTH_SHORT).show();
							
						}
						else
						{
						
						
							try{//////////////////////////////예약 신청 / 취소
								URL url = new URL(SERVER_ADDRESS + "/student_login.php?"
										+ "ID=" + URLEncoder.encode(stu_no,"UTF-8")
										+ "&Password=" + URLEncoder.encode(stu_password,"UTF-8")
										);					
								url.openStream();		
								String result = getXmlData("student_login.xml", "result");
								ID_result = result;
								stu_name= getXmlData("student_login.xml", "result2");
								stu_major = getXmlData("student_login.xml", "result3");
								stu_phone = getXmlData("student_login.xml", "result4");
							} catch(Exception e) {
								Toast.makeText(Personal_login.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
								Log.e("Error", e.getMessage());
							}
							
							if(ID_result.equals("1"))
							{
								 
								  
						      
						        
						    	
								
							  
						        /*
						        
						        Intent intent2;
								intent2 = new Intent(Login.this, BackgroundSearch.class);
								intent2.putExtra("a", a);
								intent2.putExtra("Cname", Cname);
								intent2.putExtra("ID", ID);
								intent2.putExtra("PW", Password);
								startService(intent2);	
							      */
								Toast.makeText(Personal_login.this, "로그인 성공.", Toast.LENGTH_SHORT).show();
								
								Intent intent1 = new Intent(Personal_login.this,Calendar.class);
								
								intent1.putExtra("stu_no",stu_no);//여긴 아이디를 보냅니다.
								intent1.putExtra("stu_password",stu_password);//여긴 비밀번호를 보냅니다.
								intent1.putExtra("stu_name",stu_name);//여긴 아이디를 보냅니다.
								intent1.putExtra("stu_major",stu_major);//여긴 비밀번호를 보냅니다.
								intent1.putExtra("stu_phone",stu_phone);//여긴 아이디를 보냅니다.
								intent1.putExtra("p_name",P);//여긴 아이디를 보냅니다.
								intent1.putExtra("p_no",p_no);//여긴 아이디를 보냅니다.
								
								
								startActivity(intent1);		
								
								edtID.setText("");
								edtPassword.setText("");
								edtP.setText("");
							    
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
						//////////////////////////////////////////////////////////////////////////////xml array test		
								
								
								
							/*	
	
								try{//////////////////////////////예약 신청 / 취소
									URL url = new URL(SERVER_ADDRESS + "/test.php?"
											+ "ID=" + URLEncoder.encode(ID,"UTF-8")
											+ "&Password=" + URLEncoder.encode(Password,"UTF-8")
											);					
									url.openStream();		
									stu_no = getXmlData2("test.xml");
							//		stu_name = getXmlData2("test.xml","stu_no");
							//		stu_password = getXmlData2("test.xml","stu_no");
							//		stu_major = getXmlData2("test.xml","stu_no");
							//		stu_phone = getXmlData2("test.xml","stu_no");
							
								} catch(Exception e) {
									Toast.makeText(Personal_login.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
									Log.e("Error", e.getMessage());
								}
								
							
						//		Toast.makeText(Personal_login.this, xml_array[5], Toast.LENGTH_SHORT).show();
								int a=0;
								while(xml_array[a]!=null)
								{
									Toast.makeText(Personal_login.this,"xml_array["+a+"]="+ xml_array[a], Toast.LENGTH_SHORT).show();
									a++;
								}
								
						
							*/				
								
								
								
								
						//////////////////////////////////////////////////////////////////////////////xml array test			
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
								
							}
							else if(ID_result.equals("0"))
							{
								Toast.makeText(Personal_login.this, "아이디 혹은 비밀번호 오류입니다.", Toast.LENGTH_SHORT).show();
								
							
							}
						}
							} //run 醫낅즺
						});
						CLoading.hideLoading();        //hide progress bar
				   	}// 핸들러 몇초 뒤에 실행하라 하기 위한 함수.
				},100);//핸들러
				
				
				
				
				
				
				
				
				
				
				
				
				
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
		
	  
	
	
	/*
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
	*/
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.personal_login, menu);
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
