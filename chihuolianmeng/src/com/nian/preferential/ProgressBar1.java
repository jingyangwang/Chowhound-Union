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
//			 //���������Ⱥʹν���
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
			//���ý�������񣬷��ΪԲ�Σ���ת�� 
			dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER); 
			//����ProgressDialog ���� 
			dialog.setTitle("���ݼ�����"); 
			//����ProgressDialog ��ʾ��Ϣ 
			dialog.setMessage("���ż�  ����"); 
			//����ProgressDialog ����ͼ�� 
			dialog.setIcon(android.R.drawable.ic_dialog_map); 
			//����ProgressDialog ��һ��Button 
//			dialog.setButton("ȷ��", new ProgressDialog.OnClickListener(){ 
//			    @Override 
//			    public void onClick(DialogInterface dialog, int which) { 
//			         
//			    } 
//			}); 
			//����ProgressDialog �Ľ������Ƿ���ȷ 
			dialog.setIndeterminate(false); 
			//����ProgressDialog �Ƿ���԰��˻ذ���ȡ�� 
			dialog.setCancelable(true); 
			//��ʾ 
			dialog.show();
				
		}
		
		if((shuju.self==true)){
			
			Intent Intent4 = new Intent(ProgressBar1.this, Kong.class);//��˽����������ת��
			startActivity(Intent4);
			//finish();
			 // finish();
			
		}
		
		
		else{
			Intent Intent4 = new Intent(ProgressBar1.this, NearByAcitivity11.class);//��˽�����ر���ת��������
			startActivity(Intent4);
			//finish();

		}
		
		
		
	
	}
}
