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
import android.widget.EditText;
import android.widget.Toast;

public class Professor_login extends Activity {

	Context context;
	String ID_result="";
	String ID = "";
	String Password = "";		// 받아올 아이들 미리 변수선언함
	EditText edtID,edtPassword;
	Button ok,create;
	String Cname = "";
	String a="2";
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_professor_login);
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
	    context = this;
		
		ok= (Button)findViewById(R.id.button1);
        create= (Button)findViewById(R.id.button2);
   	
		
        create.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent(Professor_login.this,Create_admin.class);
				startActivity(intent2);		
				
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
						
						
						if(edtID.getText().toString().equals("")||edtPassword.getText().toString().equals("")){
							Toast.makeText(Professor_login.this, "다 적어주세요.", Toast.LENGTH_SHORT).show();
							CLoading.hideLoading();        //hide progress bar
							return;
							
						}
						runOnUiThread(new Runnable() {
							public void run() {
						
						
						
						ID = edtID.getText().toString();
						Password = edtPassword.getText().toString();
							
						
						
						try{//////////////////////////////예약 신청 / 취소
							URL url = new URL(SERVER_ADDRESS + "/admin_login.php?"
									+ "ID=" + URLEncoder.encode(ID,"UTF-8")
									+ "&Password=" + URLEncoder.encode(Password,"UTF-8")
									);					
							url.openStream();		
							String result = getXmlData("admin_login.xml", "result");
							ID_result = result;
						} catch(Exception e) {
							Toast.makeText(Professor_login.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
							Log.e("Error", e.getMessage());
						}
						
						if(ID_result.equals("1"))
						{
							 
							  
					      
					        
					    	
							
						  
					        
					        /*
					        Intent intent2;
							intent2 = new Intent(Professor_login.this, BackgroundSearch.class);
							intent2.putExtra("a", a);
							intent2.putExtra("p_no", ID);
							intent2.putExtra("p_password", Password);
							startService(intent2);	
						      */
							Toast.makeText(Professor_login.this, "로그인 성공.", Toast.LENGTH_SHORT).show();
							
							Intent intent1 = new Intent(Professor_login.this,Conform_no.class);
							
							intent1.putExtra("ID",ID);//여긴 아이디를 보냅니다.
							intent1.putExtra("Password",Password);//여긴 비밀번호를 보냅니다.
							
							startActivity(intent1);		
							
							edtID.setText("");
							edtPassword.setText("");
							
						    
							
							
					        
							
							
							
							
						}
						else if(ID_result.equals("0"))
						{
							Toast.makeText(Professor_login.this, "아이디 혹은 비밀번호 오류입니다.", Toast.LENGTH_SHORT).show();
							
						
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
		
	  
		
	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.professor_login, menu);
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
