package com.nian.preferential;

import java.util.Random;



import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ProgressBar;

public class ProgressBar1 extends Activity {
//	 private ProgressBar pb=null; 
	 //public int i=100;
	 public int aa=0;
	 public static ProgressBar1 instance = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.progressbar);
		instance = this;
		
	//	pb = (ProgressBar)findViewById(R.id.pbb);

       
		
//		public void pro(){
//			
//			
//			
//			
//			
//			
//			
//			 if(i==pb.set)
//			  {
//				 pb.setVisibility(View.VISIBLE);
//			
//			  }
//			  else if( i < bar1.getMax() )
//			  {
//				  
//				  largeIndeterminate.setVisibility(View.GONE);
//				  
//			 //设置主进度和次进度
//			 bar1.setProgress(i);
//			 bar1.setSecondaryProgress(i+10);
//			  }
//			  i+=10;
//			
//			
//			
//			
//			
//			
//			
//		}
//		

		
		
		
		aa=(int)(Math.random() * 10+1);
		for(int i=0;i<aa;i++){
			
			ProgressDialog dialog = new ProgressDialog(this); 
			//设置进度条风格，风格为圆形，旋转的 
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
			//设置ProgressDialog 标题 
			dialog.setTitle("数据加载中"); 
			//设置ProgressDialog 提示信息 
			dialog.setMessage("表着急  嘻嘻"); 
			//设置ProgressDialog 标题图标 
			dialog.setIcon(android.R.drawable.ic_dialog_map); 
			//设置ProgressDialog 的一个Button 
//			dialog.setButton("确定", new ProgressDialog.OnClickListener(){ 
//			    @Override 
//			    public void onClick(DialogInterface dialog, int which) { 
//			         
//			    } 
//			}); 
			//设置ProgressDialog 的进度条是否不明确 
			dialog.setIndeterminate(false); 
			//设置ProgressDialog 是否可以按退回按键取消 
			dialog.setCancelable(true); 
			//显示 
			dialog.show();
				
		}
		
		if((shuju.self==true)){
			
			Intent Intent4 = new Intent(ProgressBar1.this, Kong.class);//隐私保护开启跳转空
			startActivity(Intent4);
			//finish();
			 // finish();
			
		}
		
		
		else{
			Intent Intent4 = new Intent(ProgressBar1.this, NearByAcitivity11.class);//隐私保护关闭跳转附近界面
			startActivity(Intent4);
			//finish();

		}
		
		
		
	
	}
}
