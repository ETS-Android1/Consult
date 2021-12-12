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
import android.widget.CalendarView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Calendar extends Activity {

	
	String stu_no = "";
	String stu_name = "";
	String stu_password = "";
	String stu_major = "";
	String stu_phone = "";
	String p_name = "";
	String s_year = "";		// �޾ƿ� ���̵� �̸� ����������
	String s_month = "";
	String p_no = "";	
	String s_dayOfMonth = "";
	String time = "";
	
	
	int pop=0;
	String[] xml_array= new String[1000] ;
	ArrayList<String> data;
	ArrayList<String> array_id;
	ArrayAdapter<String> adapter;
	private static final String SERVER_ADDRESS = "http://115.144.172.24/consult";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calendar);
		
		 Intent intent = getIntent();
		 stu_no = intent.getStringExtra("stu_no");		
		 stu_name = intent.getStringExtra("stu_name");		
		 stu_password = intent.getStringExtra("stu_password");		
		 stu_major = intent.getStringExtra("stu_major");		
		 stu_phone = intent.getStringExtra("stu_phone");		
		 p_name = intent.getStringExtra("p_name");		
		 p_no = intent.getStringExtra("p_no");	
		

			
		 
		 xml_array[0]="09:00~10:00";
		 xml_array[1]="10:00~11:00";
		 xml_array[2]="11:00~12:00";
		 xml_array[3]="12:00~13:00";
		 xml_array[4]="13:00~14:00";
		 xml_array[5]="14:00~15:00";
		 xml_array[6]="15:00~16:00";
		 xml_array[7]="16:00~17:00";
		 xml_array[8]="17:00~18:00";
		 xml_array[9]="18:00~19:00";
		 xml_array[10]="19:00~20:00";
		 xml_array[11]="20:00~21:00";
		 
		
	        //CalendarView �ν��Ͻ� �����
	        
	        CalendarView calendar = (CalendarView) findViewById(R.id.calendarView1);
	  
	        final LinearLayout lm = (LinearLayout) findViewById(R.id.ll);
	        //������ ���

	        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {

	        	
	            @Override

	            public void onSelectedDayChange(CalendarView view, final int year, int month,

	                                            final int dayOfMonth) {

	            	String[] t=new String[1000] ;
	            	String[] i=new String[1000] ;
lm.removeAllViews();
	         
	        	     pop=0;
	         //     Toast.makeText(MainActivity.this, "" + year + "/" + (month + 1) + "/" + dayOfMonth, Toast.LENGTH_SHORT).show();
	            	
	            	/*
	                Intent intent1 = new Intent(Calendar.this, Time_pick.class);
	                intent1.putExtra("year", Integer.toString(year));//���� �⵵�� �����ϴ�.
	                intent1.putExtra("month", Integer.toString((month + 1)));//���� ���� �����ϴ�.
	                intent1.putExtra("dayOfMonth", Integer.toString(dayOfMonth));//���� ���� �����ϴ�.
	                intent1.putExtra("p_name", p_name);//���� ü�����̸��� �����ϴ�.
	                intent1.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
	    			intent1.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
	    			intent1.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
	    			intent1.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
	    			intent1.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
	                startActivity(intent1);
	               */
	            	
	            	month=month+1;
	            	
	            	TextView testView1 = (TextView)findViewById(R.id.textView1);
					testView1.setText(year+"/"+month+"/"+dayOfMonth+"�� ���డ�ɽð�");
	            	
	            	s_year=Integer.toString(year);
	            	s_month=Integer.toString(month);
	            	s_dayOfMonth=Integer.toString(dayOfMonth);
	            	
	            	
	       //     	Toast.makeText(Calendar.this, s_year+"/"+s_month+"/"+s_dayOfMonth, Toast.LENGTH_SHORT).show();
					try{//////////////////////////////���� ��û / ���
						URL url = new URL(SERVER_ADDRESS + "/time_pick_check.php?"
								+ "p_name=" + URLEncoder.encode(p_no,"UTF-8")
								+ "&month=" + URLEncoder.encode(s_month,"UTF-8")
								+ "&year=" + URLEncoder.encode(s_year,"UTF-8")
								+ "&day=" + URLEncoder.encode(s_dayOfMonth,"UTF-8")
								);					
						url.openStream();		
						t[0] = getXmlData("time_pick_check.xml", "result9");
						t[1] = getXmlData("time_pick_check.xml", "result10");
						t[2] = getXmlData("time_pick_check.xml", "result11");
						t[3] = getXmlData("time_pick_check.xml", "result12");
						t[4] = getXmlData("time_pick_check.xml", "result13");
						t[5] = getXmlData("time_pick_check.xml", "result14");
						t[6] = getXmlData("time_pick_check.xml", "result15");
						t[7] = getXmlData("time_pick_check.xml", "result16");
						t[8] = getXmlData("time_pick_check.xml", "result17");
						t[9] = getXmlData("time_pick_check.xml", "result18");
						t[10] = getXmlData("time_pick_check.xml", "result19");
						t[11] = getXmlData("time_pick_check.xml", "result20");
					} catch(Exception e) {
						Toast.makeText(Calendar.this, "���ͳ� ������ Ȯ���ϼ���.", Toast.LENGTH_SHORT).show();
						Log.e("Error", e.getMessage());
					}
	            	
	            	
	            	
	            	
	            	

			

					if(t[0].equals("0"))
					{
						t[0]="����";
						pop++;
					}
					
						
					else if(t[0].equals("1"))
					{
						i[0]="1";
						t[0]="��������";
						pop++;
					}
				
					
					if(t[1].equals("0"))
					{
						t[1]="����";
						pop++;
					}
						else if(t[1].equals("1"))
					{
						i[1]="1";
						t[1]="��������";
						pop++;
					}
					
					
					if(t[2].equals("0"))
					{
						t[2]="����";
						pop++;
					}
						else if(t[2].equals("1"))
					{
							i[2]="1";
						t[2]="��������";
						pop++;
					}
				
					if(t[3].equals("0"))
					{
						t[3]="����";
						pop++;
					}
						else if(t[3].equals("1"))
					{
							i[3]="1";
						t[3]="��������";
						pop++;
					}
				
					if(t[4].equals("0"))
					{
						t[4]="����";
						pop++;
					}
						else if(t[4].equals("1"))
					{
							i[4]="1";
						t[4]="��������";
						pop++;
					}
					
					
					if(t[5].equals("0"))
					{
						t[5]="����";
						pop++;
					}
						else if(t[5].equals("1"))
					{
							i[5]="1";
						t[5]="��������";
						pop++;
					}
					
					
					if(t[6].equals("0"))
					{
						t[6]="����";
						pop++;
					}
						else if(t[6].equals("1"))
					{
							i[6]="1";
						t[6]="��������";
						pop++;
					}
					
					
					if(t[7].equals("0"))
					{
						t[7]="����";
						pop++;
					}
					else if(t[7].equals("1"))
					{
						i[7]="1";
						t[7]="��������";
						pop++;
					}
					
					
					if(t[8].equals("0"))
					{
						t[8]="����";
						pop++;
					}
					else if(t[8].equals("1"))
					{
						i[8]="1";
						t[8]="��������";
						pop++;
					}
					
					
					if(t[9].equals("0"))
					{
						t[9]="����";
						pop++;
					}
					else if(t[9].equals("1"))
					{
						i[9]="1";
						t[9]="��������";
						pop++;
					}
					
					
					if(t[10].equals("0"))
					{
						t[10]="����";
						pop++;
					}
					else if(t[10].equals("1"))
					{
						i[10]="1";
						t[10]="��������";
						pop++;
					}
					
					
					if(t[11].equals("0"))
					{
						t[11]="����";
						pop++;
					}
						else if(t[11].equals("1"))
					{
							i[11]="1";
						t[11]="��������";
						pop++;
					}
					
					
					/*
					for(int a =0;a<pop;a++)
					{
						
						Toast.makeText(Calendar.this, "i["+a+"]="+i[a], Toast.LENGTH_SHORT).show();
						
						
						
						
					}
					*/
					
				
	            	
					
					
					

					  LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				        		LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
				        params.gravity = Gravity.CENTER;
			        
				        
				        for (int j = 0; j < pop; j++) {
				        	
				        	if(i[j]==null)
				        	{
				        		
				        		
				        	  // LinearLayout ����
					            LinearLayout ll = new LinearLayout(Calendar.this);
					            ll.setOrientation(LinearLayout.HORIZONTAL);

					           
					            // ��ư ����
					            final Button btn = new Button(Calendar.this);
					            // setId ��ư�� ���� Ű��
					            btn.setId(j + 1);
					            btn.setText(xml_array[j]);
					            btn.setLayoutParams(params);

					            final int position = j;
					           
					            btn.setOnClickListener(new OnClickListener() {
					                public void onClick(View v) {
					              //     Log.d("log", "position :" + position);
					              //      Toast.makeText(getApplicationContext(), "Ŭ���� position:" + position, Toast.LENGTH_LONG).show();
					               
					                    Intent intent1 = new Intent(Calendar.this,Send.class);
					                    intent1.putExtra("year",s_year);//���� ���̵� �����ϴ�.
					                    intent1.putExtra("month",s_month);//���� ���̵� �����ϴ�.
					                    intent1.putExtra("dayOfMonth",s_dayOfMonth);//���� ���̵� �����ϴ�.
					                    intent1.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
					        			intent1.putExtra("stu_no",stu_no);//���� ���̵� �����ϴ�.
					        			intent1.putExtra("stu_password",stu_password);//���� ��й�ȣ�� �����ϴ�.
					        			intent1.putExtra("stu_name",stu_name);//���� ���̵� �����ϴ�.
					        			intent1.putExtra("stu_major",stu_major);//���� ��й�ȣ�� �����ϴ�.
					        			intent1.putExtra("stu_phone",stu_phone);//���� ���̵� �����ϴ�.
					        			intent1.putExtra("p_name",p_name);
					        			intent1.putExtra("p_no",p_no);
					        			intent1.putExtra("time",xml_array[position]);
					        			startActivity(intent1);

					                
					                }
					            });
					            
					            //��ư add
					            ll.addView(btn);
					            //LinearLayout ���ǵȰ� add
					            lm.addView(ll);
					           
					            
					    //        Toast.makeText(Calendar.this, "i["+j+"]="+i[j]+"  pop="+pop, Toast.LENGTH_SHORT).show();
					            
				        	}
				        	else
				        	{
				        		
				        		 // LinearLayout ����
					            LinearLayout ll = new LinearLayout(Calendar.this);
					            ll.setOrientation(LinearLayout.HORIZONTAL);

					           
					            // ��ư ����
					            final Button btn = new Button(Calendar.this);
					            // setId ��ư�� ���� Ű��
					            btn.setId(j + 1);
					            btn.setText(xml_array[j]+"(����Ұ�)");
					            btn.setLayoutParams(params);

					            
					            
					            btn.setOnClickListener(new OnClickListener() {
					                public void onClick(View v) {
					              //     Log.d("log", "position :" + position);
					              //      Toast.makeText(getApplicationContext(), "Ŭ���� position:" + position, Toast.LENGTH_LONG).show();
					               
					                	  Toast.makeText(Calendar.this, "�̽ð��� �̹� ������ �Ǿ��ֽ��ϴ�.", Toast.LENGTH_SHORT).show();
								          

					                
					                }
					            });
					            
					            //��ư add
					            ll.addView(btn);
					            //LinearLayout ���ǵȰ� add
					            lm.addView(ll);
					            
					            
					          
				        		 
					          
					            
					            
				        	}
				            
				            
				            
				        }
				        

					
					
					
					
					
					
				 
					
	            	
	            	
	            	
	            	
	               
	     	      
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
		getMenuInflater().inflate(R.menu.calendar, menu);
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
