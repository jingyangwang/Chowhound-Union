package com.nian.preferential;

import java.util.Timer;
import java.util.TimerTask;

import com.nian.preferential.ui.TabShow;


import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class Kong extends Activity {
private Button button,button2;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.kong);
		ProgressBar1.instance.finish();
		button=(Button)findViewById(R.id.button1);
		button2=(Button)findViewById(R.id.button2);
		button.setOnClickListener(listener1);
		
		button2.setOnClickListener(listener1);
		
		
	}
	OnClickListener listener1 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.button1:
				Intent picIntent = new Intent(Kong.this ,TabShow.class);
				startActivity(picIntent);
				break;
			case R.id.button2:
				shuju.self=false;
				Intent picIntent1 = new Intent(Kong.this ,NearByAcitivity11.class);
				startActivity(picIntent1);
				finish();
				break;
				
//			case R.id.nearby_serach_but11:
//				Uri uri8 = Uri.parse("http://www.baidu.com"); // ËÑÑ°ºÃ³ÔµÄ
//				Intent it8 = new Intent(Intent.ACTION_VIEW, uri8);
//				startActivity(it8);
//				break;}
			}}};
	
			
			
			
			
			
			
			
			public boolean onKeyDown(int keyCode, KeyEvent event) {
				 
	             if (keyCode == KeyEvent.KEYCODE_BACK
	                      && event.getRepeatCount() == 0) {
	                 
	            	 
	            	 Intent picIntent = new Intent(Kong.this ,TabShow.class);
	 				startActivity(picIntent);
	            	 
	            	 Kong.this.finish();
	             	 //do something...
	 			  // return true;
	 				return false; 
	              }
	             return false; 	              //return super.onKeyDown(keyCode, event);
	          }	
			
			
			
			
			
			
			
//		

}
