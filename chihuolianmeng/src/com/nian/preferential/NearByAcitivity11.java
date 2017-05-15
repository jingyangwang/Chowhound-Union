package com.nian.preferential;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.jing.search.Search;
import com.nian.preferential.smenu.BusinessInformation;
import com.nian.preferential.smenu.ChooseMer;
import com.nian.preferential.ui.TabShow;


public class NearByAcitivity11 extends Activity{
    
	private static final String DIS_DATE[]={"500米","1000米","2000米","3000米"};
	private static final String CLASS_DATE[] = {"全部分类","美食","休闲娱乐","丽人","商场购物","生活服务"};
	private static final String AWAY_DATE[] = {"近距离","最热门","新发布"};
	
	private ArrayAdapter  disAdapter , claAdapter , awayAdapter;
	
	public  NearAdapter nAdapter;
	public  NearAdapter1 nAdapter1;
	public Spinner disSpi , claSpi , awaySpi;
	
	public Button searchBut , mapBut;
	
	public ListView showList,showList1;
	
	HoldView hold = new HoldView();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.nearby1);
		initResourceRefs();
		initSetting();
	}
	
   public void initResourceRefs(){
	   
	   showList = (ListView)findViewById(R.id.nearby_list_content);
	   showList1 = (ListView)findViewById(R.id.list);
	   
	   disSpi = (Spinner) findViewById(R.id.nearby_distance_spinner);
	   claSpi = (Spinner) findViewById(R.id.nearby_class_spinner);
	   awaySpi = (Spinner) findViewById(R.id.nearby_away_spinner);
	   
	   searchBut = (Button) findViewById(R.id.nearby_serach_but11);
	   mapBut = (Button) findViewById(R.id.nearby_map_but);
	   
	   nAdapter = new NearAdapter();  //定位自己的适配器listview
	   nAdapter1 = new NearAdapter1(); //显示吃货伙伴的适配器listview
	   
	   disAdapter = new ArrayAdapter<String>(this,R.layout.nearby_spinner_text,DIS_DATE);
	   claAdapter = new ArrayAdapter<String>(this,R.layout.nearby_spinner_text,CLASS_DATE);
	   awayAdapter = new ArrayAdapter<String>(this,R.layout.nearby_spinner_text,AWAY_DATE);
	   mapBut.setOnClickListener(listener1);
	   searchBut.setOnClickListener(listener1);
	   
   }
   

	OnClickListener listener1 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.nearby_map_but:
				Intent picIntent = new Intent(NearByAcitivity11.this ,Search.class);
				NearByAcitivity11.this.startActivity(picIntent);
				break;
				
//			case R.id.nearby_serach_but11:
//				Uri uri8 = Uri.parse("http://www.baidu.com"); // 搜寻好吃的
//				Intent it8 = new Intent(Intent.ACTION_VIEW, uri8);
//				startActivity(it8);
//				break;
				
			case R.id.nearby_serach_but11:
				Intent it8 = new Intent(NearByAcitivity11.this, WebView1.class);
				startActivity(it8);
				
				break;
				
			
			}
	}};
   public void initSetting(){
	   disAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	   claAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	   awayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
	   
	   showList.setAdapter(nAdapter);
	   showList.setDivider(null);
	   showList.setDividerHeight(20);
	   
	   showList1.setAdapter(nAdapter1);
	   showList1.setDivider(null);
	   showList1.setDividerHeight(20);
	   
	   disSpi.setAdapter(disAdapter);
	   claSpi.setAdapter(claAdapter);
	   awaySpi.setAdapter(awayAdapter);
	   
	   disSpi.setSelection(2);
	   claSpi.setSelection(0);
	   awaySpi.setSelection(0);
	   showList.setOnItemClickListener(new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			Toast.makeText(getApplicationContext(), "没有小伙伴报告自己战斗的位置你要到第一个吗？           嘻嘻",
				     Toast.LENGTH_LONG).show();
			
//			Intent picIntent = new Intent(NearByAcitivity11.this ,ChooseMer.class);
//			NearByAcitivity11.this.startActivity(picIntent);
		}
	});
	   
	   
	   
	   
	   
	   showList1.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				
				Intent picIntent = new Intent(NearByAcitivity11.this ,Search.class);
				NearByAcitivity11.this.startActivity(picIntent);
			}
		});
	   
	   
	   
   }
   
   public class NearAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 1;
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
				LayoutInflater inflater = LayoutInflater.from(NearByAcitivity11.this);
				convertView = inflater.inflate(R.layout.nobody, null);
				init(convertView);
				
			
				
		
				
				
				
			}
			
			
			return convertView;
		}
	   
		public void init(View convertView){
			hold.name = (TextView)convertView.findViewById(R.id.nearby_item_name);
			hold.local = (TextView)convertView.findViewById(R.id.nearby_item_local);
			hold.dis1 = (TextView)convertView.findViewById(R.id.nearby_item_dis1);
			hold.dis2 = (TextView)convertView.findViewById(R.id.nearby_item_dis2);
			hold.dis3 = (TextView)convertView.findViewById(R.id.nearby_item_dis3);
		}
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   public class NearAdapter1 extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 1;
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
				LayoutInflater inflater = LayoutInflater.from(NearByAcitivity11.this);
				convertView = inflater.inflate(R.layout.mylocal, null);
				init(convertView);
				
			
				
		
				
				
				
			}
			
			
			return convertView;
		}
	   
		public void init(View convertView){
			hold.name = (TextView)convertView.findViewById(R.id.nearby_item_name);
			hold.local = (TextView)convertView.findViewById(R.id.nearby_item_local);
			hold.dis1 = (TextView)convertView.findViewById(R.id.nearby_item_dis1);
			hold.dis2 = (TextView)convertView.findViewById(R.id.nearby_item_dis2);
			hold.dis3 = (TextView)convertView.findViewById(R.id.nearby_item_dis3);
		}
   }
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   class HoldView{
	   TextView name , local , dis1 ,dis2 , dis3;
   }
   
   
   
	
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		 
        if (keyCode == KeyEvent.KEYCODE_BACK
                 && event.getRepeatCount() == 0) {
            
       	 
       	 Intent picIntent = new Intent(NearByAcitivity11.this ,TabShow.class);
			startActivity(picIntent);
       	 
			NearByAcitivity11.this.finish();
        	 //do something...
		  // return true;
			return false; 
         }
        return false; 	              //return super.onKeyDown(keyCode, event);
     }	
}
