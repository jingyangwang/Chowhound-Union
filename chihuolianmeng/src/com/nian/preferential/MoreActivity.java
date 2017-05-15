package com.nian.preferential;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.nian.preferential.smenu.Lifeservice;
import com.nian.preferential.ui.SortList;


public class MoreActivity extends Activity {
      private Button button;
	SortList moreList;
	HoldView hold = new HoldView();
	MoreAdapter moreAda;
	int moreDate[] = {R.string.more_system , R.string.more_person ,R.string.more_mine , R.string.more_new_version}; 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more);
		//button=(Button)findViewById(R.id.button1);
		initResourceRefs();
		//button.setOnClickListener(listener1);
		moreList.setOnItemClickListener(listener);
	}
	
	
	
	
	
	OnItemClickListener listener=new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
			
			
			
			Intent swIntent  = null;
			switch (arg2) {
			case 0:
				Toast.makeText(MoreActivity.this, "暂时不能进入系统设置",
					     Toast.LENGTH_LONG).show();
				break;
			case 1:
				Toast.makeText(MoreActivity.this, "暂时不能进入个人设置",
					     Toast.LENGTH_LONG).show();
				break;
			case 2:
				 //swIntent = new Intent(MoreActivity.this , PoiSearchActivity_ATM.class);//ATM
				
				if(shuju.self==true){
					
					shuju.self=false;
							Toast.makeText(MoreActivity.this, "隐私已关闭关",
								     Toast.LENGTH_LONG).show();
					
				}
				else{
					shuju.self=true;
					
					
					Toast.makeText(MoreActivity.this, "隐私已打开",
						     Toast.LENGTH_LONG).show();
					
				}
				
				
				break;
			case 3:
				Toast.makeText(MoreActivity.this, "隐私已打开",
					     Toast.LENGTH_LONG).show();
				break;
			
				
		
			}
			
		     
			startActivity(swIntent);
			
		}
	};


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
//	OnClickListener listener1 = new OnClickListener() {
//		@Override
//		public void onClick(View v) {
//			switch (v.getId()) {
//			case R.id.button1:
//				if(shuju.self==true){
//				shuju.self=false;
//				Toast.makeText(MoreActivity.this, "隐私保护已关闭",Toast.LENGTH_LONG);
//				
//				
//				
//				}
//				else{
//					
//					
//					shuju.self=true;
//					Toast.makeText(MoreActivity.this, "隐私保护已开启",Toast.LENGTH_LONG);
//					
//					
//					
//					
//				}
//				
//				}
//				
//			
//				
//			}
//	};
	private void initResourceRefs(){
		moreList = (SortList)findViewById(R.id.more_list);
		moreAda = new MoreAdapter();
		moreList.setAdapter(moreAda);
	}

	private class MoreAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return moreDate.length;
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			
			if(convertView == null){
				LayoutInflater inflater = LayoutInflater.from(MoreActivity.this);
				convertView = inflater.inflate(R.layout.more_list_item, null);
				hold.bu = (Button)convertView.findViewById(R.id.more_item_bu);
				convertView.setTag(hold);
				
				
				
			}else{
				hold = (HoldView) convertView.getTag();
			}
			  hold.bu.setText(moreDate[position]);
			
			  
			//	Toast.makeText(MoreActivity.this, "隐私保护已关闭",Toast.LENGTH_LONG);
			  
			  
			return convertView;
		}
		
	}
	class HoldView {
		Button bu;
	}
}
