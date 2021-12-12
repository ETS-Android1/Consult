package com.example.consult;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import android.annotation.SuppressLint;
import android.app.Activity;
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

public class Create_student extends Activity {

	String ID_result="";
	String ID = "";
	String Password = "";		// 받아올 아이들 미리 변수선언함
	String name = "";
	String Cname = "";
	String Phone = "";

	EditText edtID,edtPassword,edtnum,edtmajor,edtphone;
	Button ok,no;
	
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_student);
		data = new ArrayList<String>();
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
	    StrictMode.setThreadPolicy(policy);
		

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
				
				
				edtphone = (EditText)findViewById(R.id.Phone);
				edtnum = (EditText)findViewById(R.id.p_num);
				edtmajor = (EditText)findViewById(R.id.major);
			    edtID = (EditText)findViewById(R.id.ID);
			    edtPassword = (EditText)findViewById(R.id.Password);
		      
			    if(edtnum.getText().toString().equals("")||edtmajor.getText().toString().equals("")||
						edtID.getText().toString().equals("")||edtPassword.getText().toString().equals("")
						||edtphone.getText().toString().equals("")){
					Toast.makeText(Create_student.this, "다 적어주세요.", Toast.LENGTH_SHORT).show();
					return;
					
				}
			   	
			    runOnUiThread(new Runnable() {
					public void run() {
						
						 name = edtnum.getText().toString();
						 Cname = edtmajor.getText().toString();
						 ID = edtID.getText().toString();
						 Password = edtPassword.getText().toString();
						 Phone = edtphone.getText().toString();
						
						 
						 try{//////////////////////////////예약 신청 / 취소
								URL url = new URL(SERVER_ADDRESS + "/student_create_check.php?"
										+ "name=" + URLEncoder.encode(name,"UTF-8")
										+ "&Cname=" + URLEncoder.encode(Cname,"UTF-8")
										+ "&Phone=" + URLEncoder.encode(Phone,"UTF-8")
										+ "&ID=" + URLEncoder.encode(name,"UTF-8")
										+ "&Password=" + URLEncoder.encode(Password,"UTF-8")
										);					
								url.openStream();		
								ID_result = getXmlData("student_create_check.xml", "result");
							
							} catch(Exception e) {
								Toast.makeText(Create_student.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
								Log.e("Error", e.getMessage());
							}
							
						 if(ID_result.equals("0"))
						 {
							 
							
								 try{//////////////////////////////예약 신청 / 취소
										URL url = new URL(SERVER_ADDRESS + "/student_create.php?"
												+ "name=" + URLEncoder.encode(name,"UTF-8")
												+ "&Cname=" + URLEncoder.encode(Cname,"UTF-8")
												+ "&phone=" + URLEncoder.encode(Phone,"UTF-8")
												+ "&ID=" + URLEncoder.encode(ID,"UTF-8")
												+ "&Password=" + URLEncoder.encode(Password,"UTF-8")
												);					
										url.openStream();		
										String result = getXmlData("student_create.xml", "result");
										ID_result = result;
									} catch(Exception e) {
										Toast.makeText(Create_student.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
										Log.e("Error", e.getMessage());
									}
									
								 try{//////////////////////////////예약 신청 / 취소
										URL url = new URL(SERVER_ADDRESS + "/student_insert_check.php?"
												+ "name=" + URLEncoder.encode(name,"UTF-8")
												+ "&Cname=" + URLEncoder.encode(Cname,"UTF-8")
												+ "&phone=" + URLEncoder.encode(Phone,"UTF-8")
												+ "&ID=" + URLEncoder.encode(ID,"UTF-8")
												+ "&Password=" + URLEncoder.encode(Password,"UTF-8")
												);					
										url.openStream();		
										String result = getXmlData("student_insert_check.xml", "result");
										ID_result = result;
									} catch(Exception e) {
										Toast.makeText(Create_student.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
										Log.e("Error", e.getMessage());
									}
									
								 if(ID_result.equals("1"))
								 {
									 edtnum.setText("");
									 edtmajor.setText("");
								edtID.setText("");
								edtPassword.setText("");
								edtphone.setText("");
								
								Toast.makeText(Create_student.this, "회원가입 완료.", Toast.LENGTH_SHORT).show();
								finish();
								 }
								 else
									 Toast.makeText(Create_student.this, "회원가입 실패.", Toast.LENGTH_SHORT).show();
									
								
							
						}
						 else if(ID_result.equals("1"))
						 {
							 Toast.makeText(Create_student.this, "해당 학생번호가 이미 가입되어 있습니다.", Toast.LENGTH_SHORT).show();
							
							 
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
		getMenuInflater().inflate(R.menu.create_student, menu);
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
