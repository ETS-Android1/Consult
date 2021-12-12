package com.example.consult;

import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Time_pick extends Activity implements OnClickListener {

	
	String stu_no = "";
	String stu_name = "";
	String stu_password = "";
	String stu_major = "";
	String stu_phone = "";
	String p_name = "";
	String year = "";		// �޾ƿ� ���̵� �̸� ����������
	String month = "";
	String p_no = "";	
	String dayOfMonth = "";
	String time = "";
	String name = "";
	String t[]={"0"};
	String t10="",t11="",t12="",t13="",t14="",t15="",t16="",t17="",t18="",t19="",t20="",t21="";
	String i9="",i10="",i11="",i12="",i13="",i14="",i15="",i16="",i17="",i18="",i19="",i20="",i21="";
	ArrayList<String> data;
	ArrayList<String> array_id;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_time_pick);
		
		
		 Intent intent = getIntent();
		 stu_no = intent.getStringExtra("stu_no");		
		 stu_name = intent.getStringExtra("stu_name");		
		 stu_password = intent.getStringExtra("stu_password");		
		 stu_major = intent.getStringExtra("stu_major");		
		 stu_phone = intent.getStringExtra("stu_phone");		
		 p_name = intent.getStringExtra("p_name");	
		 year = intent.getStringExtra("year");		// year���� �޾ƿ�
		 month = intent.getStringExtra("month");		// month���� �޾ƿ�
		 dayOfMonth = intent.getStringExtra("dayOfMonth");		// dayOfMonth���� �޾ƿ�
		 name = intent.getStringExtra("name");		// name���� �޾ƿ�
		
		
		
		 
		 try{//////////////////////////////���� ��û / ���
				URL url = new URL(SERVER_ADDRESS + "/p_no_check.php?"
						+ "p_name=" + URLEncoder.encode(p_name,"UTF-8")
						+ "&month=" + URLEncoder.encode(month,"UTF-8")
						+ "&year=" + URLEncoder.encode(year,"UTF-8")
						+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
						);					
				url.openStream();		
				
				p_no = getXmlData("p_no_check.xml", "result");
			} catch(Exception e) {
				Toast.makeText(Time_pick.this, "���ͳ� ������ Ȯ���ϼ���.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());
			}
		 
		 
		 
		 

		  
		    Button bt1 = (Button)findViewById(R.id.button1);
			bt1.setOnClickListener(this);
			Button bt2 = (Button)findViewById(R.id.button2);
			bt2.setOnClickListener(this);
			Button bt3 = (Button)findViewById(R.id.button3);
			bt3.setOnClickListener(this);
			Button bt4 = (Button)findViewById(R.id.button4);
			bt4.setOnClickListener(this);
			Button bt5 = (Button)findViewById(R.id.button5);
			bt5.setOnClickListener(this);
			Button bt6 = (Button)findViewById(R.id.button6);
			bt6.setOnClickListener(this);
			Button bt7 = (Button)findViewById(R.id.button7);
			bt7.setOnClickListener(this);
			Button bt8 = (Button)findViewById(R.id.button8);
			bt8.setOnClickListener(this);
			Button bt9 = (Button)findViewById(R.id.button9);
			bt9.setOnClickListener(this);
			Button bt10 = (Button)findViewById(R.id.button10);
			bt10.setOnClickListener(this);
			Button bt11 = (Button)findViewById(R.id.button11);
			bt11.setOnClickListener(this);
			Button bt12 = (Button)findViewById(R.id.button12);
			bt12.setOnClickListener(this);
			
	
		

			
			try{//////////////////////////////���� ��û / ���
				URL url = new URL(SERVER_ADDRESS + "/time_pick_check.php?"
						+ "p_name=" + URLEncoder.encode(p_no,"UTF-8")
						+ "&month=" + URLEncoder.encode(month,"UTF-8")
						+ "&year=" + URLEncoder.encode(year,"UTF-8")
						+ "&day=" + URLEncoder.encode(dayOfMonth,"UTF-8")
						);					
				url.openStream();		
				t[0] = getXmlData("time_pick_check.xml", "result9");
				t10 = getXmlData("time_pick_check.xml", "result10");
				t11 = getXmlData("time_pick_check.xml", "result11");
				t12 = getXmlData("time_pick_check.xml", "result12");
				t13 = getXmlData("time_pick_check.xml", "result13");
				t14 = getXmlData("time_pick_check.xml", "result14");
				t15 = getXmlData("time_pick_check.xml", "result15");
				t16 = getXmlData("time_pick_check.xml", "result16");
				t17 = getXmlData("time_pick_check.xml", "result17");
				t18 = getXmlData("time_pick_check.xml", "result18");
				t19 = getXmlData("time_pick_check.xml", "result19");
				t20 = getXmlData("time_pick_check.xml", "result20");
			} catch(Exception e) {
				Toast.makeText(Time_pick.this, "���ͳ� ������ Ȯ���ϼ���.", Toast.LENGTH_SHORT).show();
				Log.e("Error", e.getMessage());
			}
			
			
			
				

				if(t[0].equals("0"))
					t[0]="����";
				else if(t[0].equals("1"))
				{
					i9="1";
					t[0]="��������";
				}
				TextView testView3 = (TextView)findViewById(R.id.textView3);
				testView3.setText(t[0]);	
				
				if(t10.equals("0"))
					t10="����";
				else if(t10.equals("1"))
				{
					i10="1";
					t10="��������";
				}
				TextView testView4 = (TextView)findViewById(R.id.textView4);
				testView4.setText(t10);
				
				if(t11.equals("0"))
					t11="����";
				else if(t11.equals("1"))
				{
					i11="1";
					t11="��������";
				}
				TextView testView5 = (TextView)findViewById(R.id.textView5);
				testView5.setText(t11);
				
				if(t12.equals("0"))
					t12="����";
				else if(t12.equals("1"))
				{
					i12="1";
					t12="��������";
				}
				TextView testView6 = (TextView)findViewById(R.id.textView6);
				testView6.setText(t12);
				
				if(t13.equals("0"))
					t13="����";
				else if(t13.equals("1"))
				{
					i13="1";
					t13="��������";
				}
				TextView testView7 = (TextView)findViewById(R.id.textView7);
				testView7.setText(t13);
				
				if(t14.equals("0"))
					t14="����";
				else if(t14.equals("1"))
				{
					i14="1";
					t14="��������";
				}
				TextView testView8 = (TextView)findViewById(R.id.textView8);
				testView8.setText(t14);
				
				if(t15.equals("0"))
					t15="����";
				else if(t15.equals("1"))
				{
					i15="1";
					t15="��������";
				}
				TextView testView9 = (TextView)findViewById(R.id.textView9);
				testView9.setText(t15);
				
				if(t16.equals("0"))
					t16="����";
				else if(t16.equals("1"))
				{
					i16="1";
					t16="��������";
				}
				TextView testView10 = (TextView)findViewById(R.id.textView10);
				testView10.setText(t16);
				
				if(t17.equals("0"))
					t17="����";
				else if(t17.equals("1"))
				{
					i17="1";
					t17="��������";
				}
				TextView testView11 = (TextView)findViewById(R.id.textView11);
				testView11.setText(t17);
				
				if(t18.equals("0"))
					t18="����";
				else if(t18.equals("1"))
				{
					i18="1";
					t18="��������";
				}
				TextView testView12 = (TextView)findViewById(R.id.textView12);
				testView12.setText(t18);
				
				if(t19.equals("0"))
					t19="����";
				else if(t19.equals("1"))
				{
					i19="1";
					t19="��������";
				}
				TextView testView13 = (TextView)findViewById(R.id.textView13);
				testView13.setText(t19);
				
				if(t20.equals("0"))
					t20="����";
				else if(t20.equals("1"))
				{
					i20="1";
					t20="��������";
				}
				TextView testView14 = (TextView)findViewById(R.id.textView14);
				testView14.setText(t20);
				
				
				
				
				  
		  
		  
		  
		  
		  
		  
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
		
	   
	
	
	
	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.button1:
			if(i9.equals("1"))
			{
				Toast.makeText(Time_pick.this, "������ �ֽ��ϴ� �ٸ� �ð��� ������ �ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
			Intent intent1 = new Intent(this,Send.class);
			intent1.putExtra("year",year);//���� �⵵�� �����ϴ�.
			intent1.putExtra("month",month);//���� ���� �����ϴ�.
			intent1.putExtra("dayOfMonth",dayOfMonth);//���� ��¥�� �����ϴ�.
			intent1.putExtra("time","09:00~10:00");//���� �ð��� �����ϴ�.	
			 intent1.putExtra("p_name", p_name);//���� ü�����̸��� �����ϴ�.
             intent1.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
 			intent1.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
 			intent1.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
 			intent1.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
 			intent1.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
			startActivity(intent1);	
			}
			break;

		case R.id.button2:
			if(i10.equals("1"))
			{
				Toast.makeText(Time_pick.this, "������ �ֽ��ϴ� �ٸ� �ð��� ������ �ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
			Intent intent2 = new Intent(this,Send.class);
			intent2.putExtra("year",year);//���� �⵵�� �����ϴ�.
			intent2.putExtra("month",month);//���� ���� �����ϴ�.
			intent2.putExtra("dayOfMonth",dayOfMonth);//���� ��¥�� �����ϴ�.
			intent2.putExtra("time","10:00~11:00");//���� �ð��� �����ϴ�.
			 intent2.putExtra("p_name", p_name);//���� ü�����̸��� �����ϴ�.
             intent2.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
 			intent2.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
 			intent2.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
 			intent2.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
 			intent2.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
			startActivity(intent2);	
			}
			break;
				
		case R.id.button3:
			if(i11.equals("1"))
			{
				Toast.makeText(Time_pick.this, "������ �ֽ��ϴ� �ٸ� �ð��� ������ �ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
			Intent intent3 = new Intent(this,Send.class);
			intent3.putExtra("year",year);//���� �⵵�� �����ϴ�.
			intent3.putExtra("month",month);//���� ���� �����ϴ�.
			intent3.putExtra("dayOfMonth",dayOfMonth);//���� ��¥�� �����ϴ�.
			intent3.putExtra("time","11:00~12:00");//���� �ð��� �����ϴ�.
			 intent3.putExtra("p_name", p_name);//���� ü�����̸��� �����ϴ�.
             intent3.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
 			intent3.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
 			intent3.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
 			intent3.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
 			intent3.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
			startActivity(intent3);	
			}
			break;
				
		case R.id.button4:
			if(i12.equals("1"))
			{
				Toast.makeText(Time_pick.this, "������ �ֽ��ϴ� �ٸ� �ð��� ������ �ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
			Intent intent4 = new Intent(this,Send.class);
			intent4.putExtra("year",year);//���� �⵵�� �����ϴ�.
			intent4.putExtra("month",month);//���� ���� �����ϴ�.
			intent4.putExtra("dayOfMonth",dayOfMonth);//���� ��¥�� �����ϴ�.
			intent4.putExtra("time","12:00~13:00");//���� �ð��� �����ϴ�.
			 intent4.putExtra("p_name", p_name);//���� ü�����̸��� �����ϴ�.
             intent4.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
 			intent4.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
 			intent4.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
 			intent4.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
 			intent4.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
			startActivity(intent4);	
			}
			break;
		case R.id.button5:
			if(i13.equals("1"))
			{
				Toast.makeText(Time_pick.this, "������ �ֽ��ϴ� �ٸ� �ð��� ������ �ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
			Intent intent5 = new Intent(this,Send.class);
			intent5.putExtra("year",year);//���� �⵵�� �����ϴ�.
			intent5.putExtra("month",month);//���� ���� �����ϴ�.
			intent5.putExtra("dayOfMonth",dayOfMonth);//���� ��¥�� �����ϴ�.
			intent5.putExtra("time","13:00~14:00");//���� �ð��� �����ϴ�.
			 intent5.putExtra("p_name", p_name);//���� ü�����̸��� �����ϴ�.
             intent5.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
 			intent5.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
 			intent5.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
 			intent5.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
 			intent5.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
			startActivity(intent5);		
			}
			break;
		case R.id.button6:
			if(i14.equals("1"))
			{
				Toast.makeText(Time_pick.this, "������ �ֽ��ϴ� �ٸ� �ð��� ������ �ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
			Intent intent6 = new Intent(this,Send.class);
			intent6.putExtra("year",year);//���� �⵵�� �����ϴ�.
			intent6.putExtra("month",month);//���� ���� �����ϴ�.
			intent6.putExtra("dayOfMonth",dayOfMonth);//���� ��¥�� �����ϴ�.
			intent6.putExtra("time","14:00~15:00");//���� �ð��� �����ϴ�.
			 intent6.putExtra("p_name", p_name);//���� ü�����̸��� �����ϴ�.
             intent6.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
 			intent6.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
 			intent6.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
 			intent6.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
 			intent6.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
			startActivity(intent6);		
			}
			break;
		case R.id.button7:
			if(i15.equals("1"))
			{
				Toast.makeText(Time_pick.this, "������ �ֽ��ϴ� �ٸ� �ð��� ������ �ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
			Intent intent7 = new Intent(this,Send.class);
			intent7.putExtra("year",year);//���� �⵵�� �����ϴ�.
			intent7.putExtra("month",month);//���� ���� �����ϴ�.
			intent7.putExtra("dayOfMonth",dayOfMonth);//���� ��¥�� �����ϴ�.
			intent7.putExtra("time","15:00~16:00");//���� �ð��� �����ϴ�.
			 intent7.putExtra("p_name", p_name);//���� ü�����̸��� �����ϴ�.
             intent7.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
 			intent7.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
 			intent7.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
 			intent7.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
 			intent7.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
			startActivity(intent7);	
			}
			break;
		case R.id.button8:
			if(i16.equals("1"))
			{
				Toast.makeText(Time_pick.this, "������ �ֽ��ϴ� �ٸ� �ð��� ������ �ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
			Intent intent8 = new Intent(this,Send.class);
			intent8.putExtra("year",year);//���� �⵵�� �����ϴ�.
			intent8.putExtra("month",month);//���� ���� �����ϴ�.
			intent8.putExtra("dayOfMonth",dayOfMonth);//���� ��¥�� �����ϴ�.
			intent8.putExtra("time","16:00~17:00");//���� �ð��� �����ϴ�.
			 intent8.putExtra("p_name", p_name);//���� ü�����̸��� �����ϴ�.
             intent8.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
 			intent8.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
 			intent8.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
 			intent8.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
 			intent8.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
			startActivity(intent8);		
			}
			break;
		case R.id.button9:
			if(i17.equals("1"))
			{
				Toast.makeText(Time_pick.this, "������ �ֽ��ϴ� �ٸ� �ð��� ������ �ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
			Intent intent9 = new Intent(this,Send.class);
			intent9.putExtra("year",year);//���� �⵵�� �����ϴ�.
			intent9.putExtra("month",month);//���� ���� �����ϴ�.
			intent9.putExtra("dayOfMonth",dayOfMonth);//���� ��¥�� �����ϴ�.
			intent9.putExtra("time","17:00~18:00");//���� �ð��� �����ϴ�.
			 intent9.putExtra("p_name", p_name);//���� ü�����̸��� �����ϴ�.
             intent9.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
 			intent9.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
 			intent9.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
 			intent9.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
 			intent9.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
			startActivity(intent9);	
			}
			break;
		case R.id.button10:
			if(i18.equals("1"))
			{
				Toast.makeText(Time_pick.this, "������ �ֽ��ϴ� �ٸ� �ð��� ������ �ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
			Intent intent10 = new Intent(this,Send.class);
			intent10.putExtra("year",year);//���� �⵵�� �����ϴ�.
			intent10.putExtra("month",month);//���� ���� �����ϴ�.
			intent10.putExtra("dayOfMonth",dayOfMonth);//���� ��¥�� �����ϴ�.
			intent10.putExtra("time","18:00~19:00");//���� �ð��� �����ϴ�.
			 intent10.putExtra("p_name", p_name);//���� ü�����̸��� �����ϴ�.
             intent10.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
 			intent10.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
 			intent10.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
 			intent10.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
 			intent10.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
			startActivity(intent10);	
			}
			break;
		case R.id.button11:
			if(i19.equals("1"))
			{
				Toast.makeText(Time_pick.this, "������ �ֽ��ϴ� �ٸ� �ð��� ������ �ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
			Intent intent11 = new Intent(this,Send.class);
			intent11.putExtra("year",year);//���� �⵵�� �����ϴ�.
			intent11.putExtra("month",month);//���� ���� �����ϴ�.
			intent11.putExtra("dayOfMonth",dayOfMonth);//���� ��¥�� �����ϴ�.
			intent11.putExtra("time","19:00~20:00");//���� �ð��� �����ϴ�.
			 intent11.putExtra("p_name", p_name);//���� ü�����̸��� �����ϴ�.
             intent11.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
 			intent11.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
 			intent11.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
 			intent11.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
 			intent11.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
			startActivity(intent11);
			}
			break;
		case R.id.button12:
			if(i20.equals("1"))
			{
				Toast.makeText(Time_pick.this, "������ �ֽ��ϴ� �ٸ� �ð��� ������ �ּ���.", Toast.LENGTH_SHORT).show();
			}
			else{
			Intent intent12 = new Intent(this,Send.class);
			intent12.putExtra("year",year);//���� �⵵�� �����ϴ�.
			intent12.putExtra("month",month);//���� ���� �����ϴ�.
			intent12.putExtra("dayOfMonth",dayOfMonth);//���� ��¥�� �����ϴ�.
			intent12.putExtra("time","20:00~21:00");//���� �ð��� �����ϴ�.
			 intent12.putExtra("p_name", p_name);//���� ü�����̸��� �����ϴ�.
             intent12.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
 			intent12.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
 			intent12.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
 			intent12.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
 			intent12.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
			startActivity(intent12);		
			}
			break;
			
		}		
		
		
		
		
		
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.time_pick, menu);
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
