package com.example.consult;


import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class Intro extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_intro);
		
		
		
		 Handler handler = new Handler();
	       handler.postDelayed(new Runnable(){
	       	public void run(){
	        		finish();
	      	}// 핸들러 몇초 뒤에 실행하라 하기 위한 함수.
	      },2000);//핸들러
		
	       
	        
	        
	}


}
