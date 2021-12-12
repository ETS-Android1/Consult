package com.example.consult;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity implements OnClickListener {

	Button ok;
	String Cname = "";

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		startActivity(new Intent(this, Intro.class));
		Button bt1 = (Button)findViewById(R.id.button1);
		bt1.setOnClickListener(this);
		Button bt2 = (Button)findViewById(R.id.button2);
		bt2.setOnClickListener(this);
		
		
		
		
	}

	public void onClick(View v)
	{
		switch (v.getId()) {
		case R.id.button1:		//일반사용자
			Intent intent1 = new Intent(this,Personal_login.class);//Personal_tworoad 로 보냄.	
			startActivity(intent1);			
			break;

		case R.id.button2:		//관리자
			Intent intent2 = new Intent(this,Professor_login.class);//Login으로 보냄
			startActivity(intent2);		
			break;
				
		
		}	// intent
		
		finish();
		
		
		
		
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
