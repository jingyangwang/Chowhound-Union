package com.nian.preferential.ui;

import java.util.Timer;
import java.util.TimerTask;

import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;

import com.nian.preferential.HomeActivity;
import com.nian.preferential.Kong;
import com.nian.preferential.Message;
import com.nian.preferential.MoreActivity;
import com.nian.preferential.NearByAcitivity;
import com.nian.preferential.NearByAcitivity11;
import com.nian.preferential.ProgressBar1;
import com.nian.preferential.R;
import com.nian.preferential.Shoucang;
import com.nian.preferential.SortActivity;
import com.nian.preferential.Xx;
import com.nian.preferential.shuju;
import com.nian.preferential.smenu.BusinessInformation_btxl;
import com.nian.preferential.util.NianUtil;
import com.nian.preferential.util.log;

public class TabShow extends TabActivity {
	private final static String TAG = "TabShow";
	private TabHost mHost;
	private RadioGroup tabItems;
	public Button koudai,qianbao,message;
	// MineTab �� ���ҵġ� ��һѡ�� ����ʾ
	private MineTab mineTab;
	private PopupWindow minePop;
	private RadioButton mineBut;
	private static  boolean FINISH = false;
    boolean isShow;
	Toast backToast;
	 public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    requestWindowFeature(Window.FEATURE_NO_TITLE);
	    setContentView(R.layout.tabshow);
	    initResourceRefs();
	    initSettings();
	   
	 }
	 private void initResourceRefs(){
		  mHost = getTabHost();
		  
		  mHost.addTab(mHost.newTabSpec("HOME").setIndicator("HOME")
		    		.setContent(new Intent(this , HomeActivity.class)));
		    
		  mHost.addTab(mHost.newTabSpec("NEAR").setIndicator("NEAR")
		    		.setContent(new Intent(this , ProgressBar1.class)));
		    
		  mHost.addTab(mHost.newTabSpec("SORT").setIndicator("SORT")
		    		.setContent(new Intent(this , SortActivity.class)));  
			    
		  mHost.addTab(mHost.newTabSpec("MORE").setIndicator("MORE")
		    		.setContent(new Intent(this , MoreActivity.class)));
		   
		  tabItems = (RadioGroup)findViewById(R.id.tab_items);
		  mineBut = (RadioButton)findViewById(R.id.tab_item_mine);
		  mineTab = new MineTab(this);
	 }
	 
	 private void initSettings(){
		 
		 
		 tabItems.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
				
				@Override
				public void onCheckedChanged(RadioGroup group, int checkedId) {
					// TODO Auto-generated method stub
					
					
					switch(checkedId){
					
					 case R.id.tab_item_home :
						 mHost.setCurrentTabByTag("HOME");
						 break;
					 case R.id.tab_item_nearby :
						 mHost.setCurrentTabByTag("NEAR");
						 break;
					 case R.id.tab_item_sort :
						 mHost.setCurrentTabByTag("SORT");
						 break;			
					 case R.id.tab_item_more :
						 mHost.setCurrentTabByTag("MORE");
						 break;
					
					}
					
					mineTab.dismissMine(true);
				}
			});
		 
		 mineBut.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				log.d(TAG,"mineButOnclick  " + minePop);
				if (minePop == null){
				  mineTab.showMine();
				}else {
				  mineTab.dismissMine(false);
				}
			}
		});
	 }
	 
	 /**
	  *  popWindow
	  * @author wangjingyang
	  *
	  */
	  
	 public class MineTab {
		 private Context mContext;
		 
		 private View popView,patentView;
		 public MineTab(Context context){
			 mContext = context;
			 initRes();
		 }
		 private void initRes(){
			 LayoutInflater inflater = LayoutInflater.from(mContext);
			 popView = inflater.inflate(R.layout.mine, null);
			 patentView = inflater.inflate(R.layout.tabshow, null);

			 
			 koudai=(Button) popView.findViewById(R.id.mine_package_but);//�ڴ�
			 qianbao=(Button) popView.findViewById(R.id.mine_money_but);//Ǯ��
			message=(Button) popView.findViewById(R.id.mine_message_but);//��Ϣ
			
			koudai.setOnClickListener(listener);
			 qianbao.setOnClickListener(listener);
			 message.setOnClickListener(listener);
		
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
			 
		 }
		 
		 
		 OnClickListener listener=new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub 
					
					
					
					
					
					
					
					switch (arg0.getId()) {
					case R.id.mine_package_but:

					
					Intent intent0=new Intent(getApplicationContext(),Shoucang.class);
					startActivity(intent0);
					
					
					
					break;
					case R.id.mine_money_but:

//						  Toast.makeText(TabShow.this, "�Բ������ǻ�û�п�ͨ����֧��ҵ����������֧����ʵʩ��½���������������ӵ���վ�Ͻ���֧��ҵ�񣬲鿴������Ƿ񹻶࣡��������",
//								     Toast.LENGTH_LONG).show();
						  if((shuju.self==true)){
								
//								Intent Intent4 = new Intent(ProgressBar1.this, Kong.class);//��˽����������ת��
//								startActivity(Intent4);
								//finish();
								 // finish();
							  
							  
							  
							  shuju.self=false;
							  
							  Toast.makeText(TabShow.this, "��˽�����ѹر�",
									     Toast.LENGTH_LONG).show();
							  
							  
								
							}
							
							
							else{
//								Intent Intent4 = new Intent(ProgressBar1.this, NearByAcitivity11.class);//��˽�����ر���ת��������
//								startActivity(Intent4);
								//finish();
								  shuju.self=true;
								  Toast.makeText(TabShow.this, "��˽�����ѿ���",
										     Toast.LENGTH_LONG).show();

							}
							
						//				Intent intent=new Intent(getApplicationContext(),Xx.class);
//						startActivity(intent);
						break;
					
					case R.id.mine_message_but:
//						Intent intent1=new Intent(getApplicationContext(),HomeActivity.class);
//						startActivity(intent1);
						 Toast.makeText(getApplicationContext(), "Ŀǰϵͳ���κ���Ϣ",
							     Toast.LENGTH_LONG).show();
//						 
						break;
					
					
					
					
					
					
					
					
					}	
					
					
					
				}
			};
		 public int getId(String tag){
			 if (tag.equals("HOME")){
				 return R.id.tab_item_home;
			 }else if(tag.equals("NEAR")){
				 return R.id.tab_item_nearby;
			 }else if (tag.equals("SORT")){
				 return R.id.tab_item_sort ;
			 }else if (tag.equals("MORE")){
				 return R.id.tab_item_more ;
			 }else{
				 return -1;
			 }
		 }
		 /**
		  * ��ʾ popWindow
		  */
		 public void showMine(){
			// if(!minePop.isShowing()){
			
			 minePop = new PopupWindow(popView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			 int y =tabItems.getHeight()  ;
			 int x = mineBut.getLeft() -  mineBut.getWidth()/2;
			 Log.i("nian","top == " + y + " , left == " + minePop.getWidth());
			 
			 minePop.showAtLocation(patentView, Gravity.BOTTOM | Gravity.LEFT, x, y);
			
			 //minePop.showAsDropDown(patentView, x, y);
			 // ���´����� popwindow�����Զ���ʧ�� ����
//			 minePop.setBackgroundDrawable(new BitmapDrawable());
//			 minePop.setOutsideTouchable(true);
//			 minePop.setOnDismissListener(new PopupWindow.OnDismissListener() {
//					
//					@Override
//					public void onDismiss() {
//						
//					    minePop =null;
//						String tag = mHost.getCurrentTabTag();
//						tabItems.check(getId(tag));
//						
//					}
//				});
			   
                
			// }
		 } 
		 
		 /**
		  *  ��ʧ�Ի���
		  * @param isRa �ж��Ƿ��ǵ���� radioButton ����� ����Ҫ�Լ�ȥ�л�
		  */
		 public void dismissMine(boolean isRa){
			 if(minePop!=null && minePop.isShowing())
				minePop.dismiss();
			    minePop =null;
			    if(!isRa){
			      String tag = mHost.getCurrentTabTag();
				  tabItems.check(getId(tag));
			    }
		  }
	
	 }




     /**
      *  �˳���ʧ
      */
	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
    		if(event.getAction() == KeyEvent.ACTION_DOWN
				&& event.getKeyCode() == KeyEvent.KEYCODE_BACK) {
			
				 if(minePop!=null && minePop.isShowing()){
					 mineTab.dismissMine(false);
				 }else if (!FINISH){
					 backToast = Toast.makeText(this, "�ٰ�һ���˳�����", Toast.LENGTH_SHORT);
					 backToast.show();
					 FINISH = true;
					 new Timer().schedule(new TimerTask() {
						
						@Override
						public void run() {
							 FINISH = false;
							
						}
					}, 2000);
				 }else {
					 return super.dispatchKeyEvent(event);
				 }
		      return true; 
	     } 
		return super.dispatchKeyEvent(event);
	}

	
	

	@Override
	protected void onPause() {
		if(FINISH){
		   backToast.cancel();	
		   FINISH = false;
		 }
		 mineTab.dismissMine(false);
		super.onPause();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		mineTab.dismissMine(false);
		return super.onTouchEvent(event);
	}
}
