package com.example.consult;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Send extends Activity {

	
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
	String a = "1";
	String t[]={"0"};
	String t10="",t11="",t12="",t13="",t14="",t15="",t16="",t17="",t18="",t19="",t20="",t21="";
	String i9="",i10="",i11="",i12="",i13="",i14="",i15="",i16="",i17="",i18="",i19="",i20="",i21="";
	ArrayList<String> data;
	ArrayList<String> array_id;
	ArrayAdapter<String> adapter;
	EditText edtperpose;
	Button ok,no;
	Context context;
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_send);
	
		 Intent intent = getIntent();
		 stu_no = intent.getStringExtra("stu_no");		
		 stu_name = intent.getStringExtra("stu_name");		
		 stu_password = intent.getStringExtra("stu_password");		
		 stu_major = intent.getStringExtra("stu_major");		
		 stu_phone = intent.getStringExtra("stu_phone");		
		 p_name = intent.getStringExtra("p_name");	
		 time = intent.getStringExtra("time");	
		 year = intent.getStringExtra("year");		// year값을 받아옴
		 month = intent.getStringExtra("month");		// month값을 받아옴
		 dayOfMonth = intent.getStringExtra("dayOfMonth");		// dayOfMonth값을 받아옴
		 
	

			TextView testView1 = (TextView)findViewById(R.id.textView21);
			testView1.setText(time);
			TextView testView2 = (TextView)findViewById(R.id.textView12);
			testView2.setText(year+"/"+month+"/"+dayOfMonth);
			TextView testView3 = (TextView)findViewById(R.id.textView22);
			testView3.setText(p_name);
			TextView testView4 = (TextView)findViewById(R.id.textView23);
			testView4.setText(stu_name);
			TextView testView5 = (TextView)findViewById(R.id.textView24);
			testView5.setText(stu_no);
			TextView testView6 = (TextView)findViewById(R.id.textView25);
			testView6.setText(stu_major);
			TextView testView7 = (TextView)findViewById(R.id.textView26);
			testView7.setText(stu_phone);
			
			
			
			
		 try{
				URL url_2 = new URL(SERVER_ADDRESS + "/p_no_check.php?"
						+ "p_name=" + URLEncoder.encode(p_name,"UTF-8")
												
				);

				url_2.openStream();
				p_no = getXmlData("p_no_check.xml", "result");
			}
			catch(Exception e){
				Toast.makeText(Send.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());
			}// try catch 臾�醫낅즺 */


			
			edtperpose = (EditText)findViewById(R.id.perpose);
		

			data = new ArrayList<String>();


			ok= (Button)findViewById(R.id.button1);
			no= (Button)findViewById(R.id.button2);


			no.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Send.this.finish();
				}
			});



			ok.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub

				//	CLoading.showLoading(context);    //show progress bar

					Intent intent1 = new Intent(Send.this,Send_ok.class);

					if(	edtperpose.getText().toString().equals("")){
						Toast.makeText(Send.this, "다 적어주세요.", Toast.LENGTH_SHORT).show();
						return;

					}
					runOnUiThread(new Runnable() {
						public void run() {

						
							perpose = edtperpose.getText().toString();
							


							try{
								URL url_2 = new URL(SERVER_ADDRESS + "/reservation_wait.php?"
										+ "stu_name=" + URLEncoder.encode(stu_name,"UTF-8")
										+ "&stu_no=" + URLEncoder.encode(stu_no,"UTF-8")
										+ "&stu_major=" + URLEncoder.encode(stu_major,"UTF-8")
										+ "&stu_phone=" + URLEncoder.encode(stu_phone,"UTF-8")
										+ "&perpose=" + URLEncoder.encode(perpose,"UTF-8")
										+ "&p_no=" + URLEncoder.encode(p_no,"UTF-8")
										+ "&year=" + URLEncoder.encode(year,"UTF-8")
										+ "&month=" + URLEncoder.encode(month,"UTF-8")
										+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
										+ "&time=" + URLEncoder.encode(time,"UTF-8")
										
								);

								url_2.openStream();
							}
							catch(Exception e){
								Toast.makeText(Send.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
								Log.e("Error", e.getMessage());
							}// try catch 臾�醫낅즺 */



					//		reservation_search();


												
												Intent intent;
												intent = new Intent(Send.this, BackgroundSearch.class);
												intent.putExtra("year",year);//여긴 년도를 보냅니다.
												intent.putExtra("month",month);//여긴 달을 보냅니다.
												intent.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
												intent.putExtra("time",time);//여긴 시간을 보냅니다.	
												intent.putExtra("p_name", p_name);//여긴 체육관이름을 보냅니다.
									            intent.putExtra("stu_no",stu_no);//여긴 아이디를 보냅니다.
									 			intent.putExtra("stu_password",stu_password);//여긴 비밀번호를 보냅니다.
									 			intent.putExtra("stu_name",stu_name);//여긴 아이디를 보냅니다.
									 			intent.putExtra("stu_major",stu_major);//여긴 비밀번호를 보냅니다.
									 			intent.putExtra("stu_phone",stu_phone);//여긴 아이디를 보냅니다.
									 			intent.putExtra("perpose",perpose);//여긴 아이디를 보냅니다.
									 			intent.putExtra("p_no",p_no);//여긴 아이디를 보냅니다.
												intent.putExtra("a", a);
												startService(intent);
																	
							Toast.makeText(Send.this, "전송 완료", Toast.LENGTH_SHORT).show();
						
							edtperpose.setText("");
						
							//	finish();

							
							
							
						} //run 醫낅즺
					});

					intent1.putExtra("year",year);//여긴 년도를 보냅니다.
					intent1.putExtra("month",month);//여긴 달을 보냅니다.
					intent1.putExtra("dayOfMonth",dayOfMonth);//여긴 날짜를 보냅니다.
					intent1.putExtra("time",time);//여긴 시간을 보냅니다.	
					intent1.putExtra("p_name", p_name);//여긴 체육관이름을 보냅니다.
		            intent1.putExtra("stu_no",stu_no);//여긴 아이디를 보냅니다.
		 			intent1.putExtra("stu_password",stu_password);//여긴 비밀번호를 보냅니다.
		 			intent1.putExtra("stu_name",stu_name);//여긴 아이디를 보냅니다.
		 			intent1.putExtra("stu_major",stu_major);//여긴 비밀번호를 보냅니다.
		 			intent1.putExtra("stu_phone",stu_phone);//여긴 아이디를 보냅니다.
		 			intent1.putExtra("perpose",perpose);//여긴 아이디를 보냅니다.
		 			intent1.putExtra("p_no",p_no);//여긴 아이디를 보냅니다.
					startActivity(intent1);



					CLoading.hideLoading();        //hide progress bar
					Handler handler = new Handler();
				       handler.postDelayed(new Runnable(){
				       	public void run(){
				       		CLoading.hideLoading();        //hide progress bar
				       		finish();
				      	}// 핸들러 몇초 뒤에 실행하라 하기 위한 함수.
				      },1000);//핸들러



				}
			});






		}



		@SuppressLint("NewApi")
		public void reservation_search()
		{

//		Intent intent = new Intent(BackgroundSearch.this, Reservation_check.class);
//		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		PendingIntent content = PendingIntent.getActivity(BackgroundSearch.this,0, intent,0);

			NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
			Notification noti = new Notification.Builder(Send.this)
					.setTicker("예약 확인")
					.setContentTitle("예약 확인중!")
					.setContentText(" ")
					.setSmallIcon(R.drawable.ic_launcher)
					.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))


//		.setContentIntent(content)
					.build();
			
			nm.notify(1235,noti);



			try{
				URL url_3 = new URL(SERVER_ADDRESS + "/reservation_search.php?"
						//+ "&birth=" + birth
						//+ "&seat=" + seat);
						+ "stu_name=" + URLEncoder.encode(stu_name,"UTF-8")
						+ "&stu_no=" + URLEncoder.encode(stu_no,"UTF-8")
						+ "&stu_major=" + URLEncoder.encode(stu_major,"UTF-8")
						+ "&stu_phone=" + URLEncoder.encode(stu_phone,"UTF-8")
						+ "&perpose=" + URLEncoder.encode(perpose,"UTF-8")
						+ "&p_no=" + URLEncoder.encode(p_no,"UTF-8")
						+ "&year=" + URLEncoder.encode(year,"UTF-8")
						+ "&month=" + URLEncoder.encode(month,"UTF-8")
						+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
						+ "&time=" + URLEncoder.encode(time,"UTF-8")
				);

				url_3.openStream();
			}
			catch(Exception e){
				Toast.makeText(Send.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				//Log.e("Error", e.getMessage()); //onDestory();
			}// try catch 臾�醫낅즺
			String result2 = getXmlData("consult_customer/reservation_search_"+stu_no+".xml", "result");
		//	Log.d(TAG, "reservation_search() result2 : "+result2);//debug
			if(result2.equals("1")) {
				//리턴받은경우

				try{///////////////////////////////////////////////////////////////예약 성공
					URL url_5 = new URL(SERVER_ADDRESS + "/reservation_success.php?"
							+ "stu_name=" + URLEncoder.encode(stu_name,"UTF-8")
							+ "&stu_no=" + URLEncoder.encode(stu_no,"UTF-8")
							+ "&stu_major=" + URLEncoder.encode(stu_major,"UTF-8")
							+ "&stu_phone=" + URLEncoder.encode(stu_phone,"UTF-8")
							+ "&perpose=" + URLEncoder.encode(perpose,"UTF-8")
							+ "&p_no=" + URLEncoder.encode(p_no,"UTF-8")
							+ "&year=" + URLEncoder.encode(year,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
							+ "&time=" + URLEncoder.encode(time,"UTF-8")
					);

					url_5.openStream();
				}
				catch(Exception e){
					Toast.makeText(Send.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					//Log.e("Error", e.getMessage());
				}// try catch 臾�醫낅즺
				String result3 = getXmlData("reservation_success.xml", "result");
				if(result3.equals("1")){
					success();

				}


				try{///////////////////////////////////////////////////////////////예약 실패
					URL url_6 = new URL(SERVER_ADDRESS + "/reservation_fail.php?"
							+ "stu_name=" + URLEncoder.encode(stu_name,"UTF-8")
							+ "&stu_no=" + URLEncoder.encode(stu_no,"UTF-8")
							+ "&stu_major=" + URLEncoder.encode(stu_major,"UTF-8")
							+ "&stu_phone=" + URLEncoder.encode(stu_phone,"UTF-8")
							+ "&perpose=" + URLEncoder.encode(perpose,"UTF-8")
							+ "&p_no=" + URLEncoder.encode(p_no,"UTF-8")
							+ "&year=" + URLEncoder.encode(year,"UTF-8")
							+ "&month=" + URLEncoder.encode(month,"UTF-8")
							+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
							+ "&time=" + URLEncoder.encode(time,"UTF-8")
					);

					url_6.openStream();
				}
				catch(Exception e){
					Toast.makeText(Send.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					//Log.e("Error", e.getMessage());
				}// try catch 臾�醫낅즺
				String result4 = getXmlData("reservation_fail.xml", "result");
				if(result4.equals("1")) {
					fail();
				}


			}
			else{
				Handler handler = new Handler();
				handler.postDelayed(new Runnable() {
					public void run() {
						reservation_search();
					}
				},2000);//핸들러


			}
		}

		/* 실패시 알림*/
		@SuppressLint("NewApi")
		public void fail()
		{

//		Intent intent = new Intent(BackgroundSearch.this, Reservation_check.class);
//		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		PendingIntent content = PendingIntent.getActivity(BackgroundSearch.this,0, intent,0);

			NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
			Notification noti = new Notification.Builder(Send.this)
					.setTicker("예약 실패")
					.setContentTitle("예약 실패")
					.setContentText("예약에 실패했습니다!")
					.setSmallIcon(R.drawable.ic_launcher)
					.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
					.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)

//		.setContentIntent(content)
					.build();
			
			nm.notify(1235,noti);





		/*
		*/





		}

		/* 성공시 알림*/
		@SuppressLint("NewApi")
		public void success()
		{
		/* 완료 Notification */
//		Intent intent = new Intent(BackgroundSearch.this, Reservation_check.class);
//		intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//		PendingIntent content = PendingIntent.getActivity(BackgroundSearch.this,0, intent,0);


			NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
			Notification noti = new Notification.Builder(Send.this)
					.setTicker("예약 성공")
					.setContentTitle("예약 성공")
					.setContentText("교수님이름: " + p_name)
					.setSubText("이름: " + stu_name + "시간: " + time + " 날짜: " + month + "/" + dayOfMonth)
					.setSmallIcon(R.drawable.ic_launcher)
					.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher))
					.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE)

//		.setContentIntent(content)
					.build();
		
			nm.notify(1234, noti);


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
		getMenuInflater().inflate(R.menu.send, menu);
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
