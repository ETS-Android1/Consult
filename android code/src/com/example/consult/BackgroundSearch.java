package com.example.consult;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import android.content.Intent;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.os.Handler;
import android.util.Log;
import android.widget.ListView;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;


public class BackgroundSearch extends Service {

	private final String TAG = "BackgroundSearch";
	ListView list;
	
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult"; //?쒕쾭 IP瑜??꾩뿭蹂?닔濡?.
	
	String tagname, content; //xml???쒓렇???댁슜???닿린 ?꾪븳 蹂?닔
	ArrayList<String> data;
	ArrayAdapter<String> adapter;
	boolean mQuit;
	int q;
	String stu_no = "";
	String stu_name = "";
	String stu_password = "";
	String stu_major = "";
	String stu_phone = "";
	String p_name = "";
	String p_no = "";
	String year = "";		// 받아올 아이들 미리 변수선언함
	String month = "";
	String Password = "";
	String yesno = "";
	String day = "";
	String time = "";
	String Cname = "";
	String name = "";
	String age = "";		// 받아올 아이들 미리 변수선언함
	String perpose = "";
	String phone = "";
	String a = "";
	String ID = "";
	String PW = "";
	String again = "1";
	int over=0;
	int count=0;
	String num = "0";
	
	public void onCreate(){
		super.onCreate();
		
	}
	
	
	public void onDestory(){
		super.onDestroy();
		
		
		Toast.makeText(this, "Service End",Toast.LENGTH_SHORT).show();
		mQuit = true;
		
	}
	
	
	@SuppressLint("NewApi")
	public int onStartCommand(Intent intent, int flags, int startId) {
		super.onStartCommand(intent, flags, startId);
		
		/* 비정상 백그라운드 생성 방지 */
		//Log.d(TAG,String.valueOf(DBinfo.statement)+"onStartCommand");//debug
	
		
		/* 감지 루틴 */
	
		
		  startForeground(1,new Notification());
		  
	        NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	        Notification notification;
	        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB){
	        	 
	            notification = new Notification.Builder(getApplicationContext())
	                    .setContentTitle("")
	                    .setContentText("")
	                    .build();
	 
	        }else{
	         //   notification = new Notification(0, "", System.currentTimeMillis());
	         //   notification.setLatestEventInfo(getApplicationContext(), "", "", null);
	        	 notification = new Notification.Builder(getApplicationContext())
                 .setContentTitle("")
                 .setContentText("")
                 .build();
	        }
	 
	        nm.notify(startId, notification);
	        nm.cancel(startId);
		
		/*
		
		
		Intent notificationIntent = new Intent(this, MainActivity.class);
        notificationIntent.setAction("com.example.consult.startforeground");
        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
  
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,notificationIntent, 0);
        Bitmap icon = BitmapFactory.decodeResource(getResources(),R.drawable.ic_launcher);
        

    	NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        Notification noti = new Notification.Builder(this)
                .setContentTitle("Gym")
                .setTicker("Gym")
                .setContentText("할일 찾는중..")
                .setSmallIcon(R.drawable.ic_launcher)
                .setLargeIcon(Bitmap.createScaledBitmap(icon, 128, 128, false))
                .setContentIntent(pendingIntent)
                .setOngoing(false)
                .build();
    
        startForeground(1, noti);
     
		*/
		
		
		
		mQuit = false;
		mQuit = intent.getBooleanExtra("serviceValue", false);
		
		Password=intent.getStringExtra("Password");	
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
		 day = intent.getStringExtra("dayOfMonth");		// dayOfMonth값을 받아옴
		a = intent.getStringExtra("a");
		
		NewsThread thread = new NewsThread(this, mHandler);
		thread.start();
		return START_NOT_STICKY;
	}
	
	
	
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}

	class NewsThread extends Thread {
		BackgroundSearch mParent;
		Handler mHandler;
		
		public NewsThread(BackgroundSearch parent, Handler handler){
			mParent = parent;
			mHandler = handler;
		}
		public void run() {       ///////////////////////////////////여기다가 예약검색 뭐 할지 넣으면 됨
			for(int idx = 0; mQuit==false; idx++) {
				
				//Log.d(TAG, "run");//debug
			//	Message msg = new Message();
			//	msg.what =0;
			
			//	mHandler.sendMessage(msg);
				
				if(a.equals("1"))
				{
					Handler mHandler = new Handler(Looper.getMainLooper());////////////////////////////////예약에서 은근히 중요한 부분을 차지하는 수시검색
					mHandler.postDelayed(new Runnable() {
					         public void run() {
					        	 reservation_search();  
					    //    	 Toast.makeText(BackgroundSearch.this, a, Toast.LENGTH_SHORT).show();
					         }
					 }, 1000);
				}
				
				else if(a.equals("2"))
				{
					
					Handler mHandler = new Handler(Looper.getMainLooper());////////////////////////////////예약에서 은근히 중요한 부분을 차지하는 수시검색
					mHandler.postDelayed(new Runnable() {
					         public void run() {
					        	 Conform_reservation_search();
					         }
					 }, 1000);
					
					
				}
					
					
				try {
					Thread.sleep(1000*2);//2초간격
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					Toast.makeText(BackgroundSearch.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					e.printStackTrace();
					
				}			
							
			
			}
			
		}
		
	}
	
	

	Handler mHandler = new Handler() {
		public void handleMessage (Message msg) {
			if (msg.what ==0){
				String news = (String)msg.obj;
				Toast.makeText(BackgroundSearch.this, news, Toast.LENGTH_SHORT).show();
			}
				
		}
	};
	
	
	


private void reservation_search()
{
//	Toast.makeText(BackgroundSearch.this, "stu_name="+stu_name+ "\n"+"stu_no="+stu_no+ "\n"+"year="+year+ "\n"
//			+"month="+month+ "\n"+"day="+day+ "\n"+"time="+time+ "\n", Toast.LENGTH_SHORT).show();

	
	Log.d(TAG, "reservation_search() start "+String.valueOf(count++));//debug
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
				+ "&day=" + URLEncoder.encode(day,"UTF-8")
				+ "&time=" + URLEncoder.encode(time,"UTF-8")
		);

		url_3.openStream();
	}
	catch(Exception e){
		Toast.makeText(BackgroundSearch.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
		//Log.e("Error", e.getMessage()); //onDestory();
	}// try catch 臾�醫낅즺
	String result2 = getXmlData("consult_customer/reservation_search_"+stu_no+".xml", "result");
	Log.d(TAG, "reservation_search() result2 : "+result2);//debug
	 if(result2.equals("1")) {//리턴받은경우
		 
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
						+ "&day=" + URLEncoder.encode(day,"UTF-8")
						+ "&time=" + URLEncoder.encode(time,"UTF-8")
				);

				url_5.openStream();
			}
			catch(Exception e){
				Toast.makeText(BackgroundSearch.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				//Log.e("Error", e.getMessage());
			}// try catch 臾�醫낅즺
			String result3 = getXmlData("reservation_success.xml", "result");
			if(result3.equals("1")){
			success();
			mQuit =true;	
			
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
						+ "&day=" + URLEncoder.encode(day,"UTF-8")
						+ "&time=" + URLEncoder.encode(time,"UTF-8")
				);

				url_6.openStream();
			}
			catch(Exception e){
				Toast.makeText(BackgroundSearch.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
				//Log.e("Error", e.getMessage());
			}// try catch 臾�醫낅즺
			String result4 = getXmlData("reservation_fail.xml", "result");
			if(result4.equals("1")){
			fail();
			mQuit =true;			
			}	
			
	}	 
	
}

/* 실패시 알림*/
@SuppressLint("NewApi")
public void fail()
{
//	if(over==0)
//	{
//		over=1;
	Intent intent = new Intent(BackgroundSearch.this, Send.class);
	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	PendingIntent content = PendingIntent.getActivity(BackgroundSearch.this,0, intent,0);
	
	NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	Notification noti = new Notification.Builder(BackgroundSearch.this)
	.setTicker("예약 실패")
	.setContentTitle("예약 실패")
	.setContentText("예약에 실패했습니다!")
	.setSmallIcon(R.drawable.ic_launcher)
	.setLargeIcon(BitmapFactory.decodeResource(getResources(),  R.drawable.ic_launcher))
	.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
	.setVibrate(new long[] {1000,1000,500,500,200,200,200,200,200,200})
	.setLights(0xff00ff00, 500, 500)
//	.setContentIntent(content)
	.build();
	noti.flags = noti.flags | Notification.FLAG_AUTO_CANCEL;
	nm.notify(1235,noti);
	
//	}
	
	
	
	/* 백그라운드 감지 중지 */
	stopSelf();
	
	/* 백그라운드 감지 중지 */
//	Intent stopIntent = new Intent(this, BackgroundSearch.class);
//    stopIntent.setAction("com.example.consult.startforeground");
//    startService(stopIntent);
//    stopService(new Intent("com.example.dustview.ForegroundService"));// lollipop version
 //       Intent intent2 = new Intent("com.example.consult.BackgroundSearch");
 //      intent.setPackage("com.example.consult");
   //     getContext().bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE);
 //      stopService(intent2);

 //   stopService(new Intent(getApplicationContext(), BackgroundSearch.class));
    
	
}

/* 성공시 알림*/
@SuppressLint("NewApi")
public void success()
{

	/* 완료 Notification */
	Intent intent = new Intent(BackgroundSearch.this, Send.class);
	intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
	PendingIntent content = PendingIntent.getActivity(BackgroundSearch.this,0, intent,0);
	
	
	NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
	Notification noti = new Notification.Builder(BackgroundSearch.this)
	.setTicker("예약 성공")
	.setContentTitle("예약 성공")
	.setContentText("교수님성함: "+ p_name)
	.setSubText("이름: "+stu_name + "시간: "+time+ " 날짜: "+month+"/"+day)
	.setSmallIcon(R.drawable.ic_launcher)
	.setLargeIcon(BitmapFactory.decodeResource(getResources(),  R.drawable.ic_launcher))
	.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
	.setVibrate(new long[] {1000,1000,500,500,200,200,200,200,200,200})
	.setLights(0xff00ff00, 500, 500)	
//	.setContentIntent(content)
	.build();	
	noti.flags = noti.flags | Notification.FLAG_AUTO_CANCEL;
	nm.notify(1234,noti);
	

	
	/* 백그라운드 감지 중지 */
	stopSelf();
	/* 백그라운드 감지 중지 */
//	Intent stopIntent = new Intent(this, BackgroundSearch.class);
 //   stopIntent.setAction("com.example.consult.startforeground");
 //   startService(stopIntent);
 //   stopService(new Intent("com.zero.dustview.ForegroundService"));// lollipop version
  //      Intent intent2 = new Intent("com.example.consult.BackgroundSearch");
   //     intent2.setPackage("com.example.consult");
  //      getContext().bindService(intent2, serviceConnection, Context.BIND_AUTO_CREATE);
   //     stopService(intent2);

  //  stopService(new Intent(getApplicationContext(), BackgroundSearch.class));
   
}




///////////////////////////////////////////////////////////////////////// Conform_no version





private void Conform_reservation_search()
{
	

	
//	Log.d(TAG, "Conform_reservation_search() start "+String.valueOf(count++));//debug
	try{
		URL url_3 = new URL(SERVER_ADDRESS + "/Conform_reservation_search.php?"
				//+ "&birth=" + birth
				//+ "&seat=" + seat);
			
				+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
				
				);
		
		url_3.openStream();
		}
		catch(Exception e){
			Toast.makeText(BackgroundSearch.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
			//Log.e("Error", e.getMessage()); //onDestory();
		}// try catch 臾?醫낅즺
	String result = getXmlData("Conform_reservation_search/search_"+p_no+".xml", "result"); 	// 실제 대기자수
	String result2 = getXmlData("Conform_reservation_search/search_"+p_no+".xml", "result2"); 	// 알림을 보냈는지 안보냈는지 확인
	Log.d(TAG, "reservation_search() result2 : "+result2);//debug
	 if(result2.equals("0")) {//리턴받은경우
		 
					
					
			
	}	
	 else
	 {
		 
		 Intent intent = new Intent(BackgroundSearch.this, Conform_no.class);
		 intent.putExtra("ID",p_no);//여긴 아이디를 보냅니다.
			intent.putExtra("Password",Password);//여긴 비밀번호를 보냅니다.
			intent.putExtra("again",again);//여긴 비밀번호를 보냅니다.
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
			PendingIntent content = PendingIntent.getActivity(BackgroundSearch.this,0, intent,PendingIntent.FLAG_UPDATE_CURRENT);
			
			NotificationManager nm = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
			Notification noti = new Notification.Builder(BackgroundSearch.this)
			.setTicker(result+"명 신청대기중")
			.setContentTitle(result+"명 신청대기중")
			.setContentText(result+"명이 대기중입니다.")
			.setSmallIcon(R.drawable.ic_launcher)
			.setDefaults(Notification.DEFAULT_SOUND|Notification.DEFAULT_VIBRATE)
			.setVibrate(new long[] {1000,1000,500,500,200,200,200,200,200,200})
			.setLights(0xff00ff00, 500, 500)
			.setContentIntent(content)
			.build();
			noti.flags = noti.flags | Notification.FLAG_AUTO_CANCEL;
			nm.notify(1235,noti);
		 
		 
		 
		 
		 
		 
		 
		 try{///////////////////////////////////////////////////////////////예약 성공
				URL url_5 = new URL(SERVER_ADDRESS + "/read_ok.php?"
						+ "p_no=" + URLEncoder.encode(p_no,"UTF-8")
						
						);
				
				url_5.openStream();
				}
				catch(Exception e){
					Toast.makeText(BackgroundSearch.this, "인터넷 연결을 확인하세요.", Toast.LENGTH_SHORT).show();
					//Log.e("Error", e.getMessage());	
				}// try catch 臾?醫낅즺
			String result3 = getXmlData("read_ok.xml", "result");
			
	//		Toast.makeText(BackgroundSearch.this, Cname, Toast.LENGTH_SHORT).show();
			
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
	
}
	
	
	
	




