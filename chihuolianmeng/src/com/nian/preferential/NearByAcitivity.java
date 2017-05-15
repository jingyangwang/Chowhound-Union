package com.nian.preferential;


import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

import com.jing.search.Search;
import com.nian.preferential.smenu.BusinessInformation;
import com.nian.preferential.smenu.ChooseMer;


public class NearByAcitivity extends Activity{
    
	private static final String DIS_DATE[]={"500米","1000米","2000米","3000米"};
	private static final String CLASS_DATE[] = {"全部分类","美食","休闲娱乐","丽人","商场购物","生活服务"};
	private static final String AWAY_DATE[] = {"近距离","最热门","新发布"};
	
	private ArrayAdapter  disAdapter , claAdapter , awayAdapter;
	
	public  NearAdapter nAdapter;
	
	public Spinner disSpi , claSpi , awaySpi;
	
	public Button searchBut , mapBut;
	
	public ListView showList;
	private Button btn;
	
	HoldView hold = new HoldView();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.nearby);
		initResourceRefs();
		initSetting();
	}
	
   public void initResourceRefs(){
	   
	   showList = (ListView)findViewById(R.id.nearby_list_content);
	   
	   disSpi = (Spinner) findViewById(R.id.nearby_distance_spinner);
	   claSpi = (Spinner) findViewById(R.id.nearby_class_spinner);
	   awaySpi = (Spinner) findViewById(R.id.nearby_away_spinner);
	   
	   searchBut = (Button) findViewById(R.id.nearby_serach_but11);
	   mapBut = (Button) findViewById(R.id.nearby_map_but);
	   btn=(Button) findViewById(R.id.nearby_serach_but11);
	   
	   nAdapter = new NearAdapter();
	   disAdapter = new ArrayAdapter<String>(this,R.layout.nearby_spinner_text,DIS_DATE);
	   claAdapter = new ArrayAdapter<String>(this,R.layout.nearby_spinner_text,CLASS_DATE);
	   awayAdapter = new ArrayAdapter<String>(this,R.layout.nearby_spinner_text,AWAY_DATE);
	   btn.setOnClickListener(listener1);
	   mapBut.setOnClickListener(listener1);
	   searchBut.setOnClickListener(listener1);
	   
   }
   
   

	OnClickListener listener1 = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.nearby_map_but:
				Intent picIntent = new Intent(NearByAcitivity.this ,Search.class);
				NearByAcitivity.this.startActivity(picIntent);
				break;
				
//			case R.id.nearby_serach_but11:
//				Uri uri8 = Uri.parse("http://www.baidu.com"); // 搜寻好吃的
//				Intent it8 = new Intent(Intent.ACTION_VIEW, uri8);
//				startActivity(it8);
//				break;
				
			case R.id.nearby_serach_but11:
				//Uri uri8 = Uri.parse("http://www.baidu.com"); // 搜寻好吃的
				Intent it8 = new Intent(NearByAcitivity.this, WebView1.class);
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
			Intent picIntent = new Intent(NearByAcitivity.this ,ChooseMer.class);
			NearByAcitivity.this.startActivity(picIntent);
		}
	});
	   
   }
   
   public class NearAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 30;
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
				LayoutInflater inflater = LayoutInflater.from(NearByAcitivity.this);
				convertView = inflater.inflate(R.layout.nearby_list_item, null);
				init(convertView);
				
				
				
				
				
				
				
				
				
				
				
				
//				 ArrayList<HashMap<String, Object>> listItem 
//		        	= new ArrayList<HashMap<String, Object>>();
//		        for(int i=0;i<5;i++)
//		       {
//		        	HashMap<String, Object> map = new HashMap<String, Object>();
//		        	map.put("ItemImage", R.drawable.ee);//图像资源的ID
//		        	map.put("ItemTitle", "小明 ");
//		        	map.put("ItemText", "真的很好吃 ");
//		        	listItem.add(map);
//		        	
//		        	HashMap<String, Object> map0 = new HashMap<String, Object>();
//		        	map0.put("ItemImage", R.drawable.qq);//图像资源的ID
//		        	map0.put("ItemTitle", "花花 ");
//		        	map0.put("ItemText", "你值得拥有，美食爽歪歪了 ");
//		        	listItem.add(map0);
//		        	
//		        	HashMap<String, Object> map1 = new HashMap<String, Object>();
//		        	map1.put("ItemImage", R.drawable.rr);//图像资源的ID
//		        	map1.put("ItemTitle", "吃仔lll ");
//		        	map1.put("ItemText", "我已吃好，尚未吃饱 ");
//		        	listItem.add(map1);
//		        	
//		        	
//		        	HashMap<String, Object> map2 = new HashMap<String, Object>();
//		        	map2.put("ItemImage", R.drawable.tt);//图像资源的ID
//		        	map2.put("ItemTitle", "旺旺 ");
//		        	map2.put("ItemText", "美食做法不错，就是这个味");
//		        	listItem.add(map2);
//		        	
//		        	
//		        	HashMap<String, Object> map3 = new HashMap<String, Object>();
//		        	map3.put("ItemImage", R.drawable.ww);//图像资源的ID
//		        	map3.put("ItemTitle", "吃货老杨 ");
//		        	map3.put("ItemText", "真好吃 ，大家尝尝吧 ");
//		        	listItem.add(map3);
//		        	
//		        	
//		        	HashMap<String, Object> map4 = new HashMap<String, Object>();
//		        	map4.put("ItemImage", R.drawable.yy);//图像资源的ID
//		        	map4.put("ItemTitle", "big土豪 ");
//		        	map4.put("ItemText", "好吃，好吃 ");
//		        	listItem.add(map4);
//		        	
//		        	
//		        	HashMap<String, Object> map5 = new HashMap<String, Object>();
//		        	map5.put("ItemImage", R.drawable.uu);//图像资源的ID
//		        	map5.put("ItemTitle", "平平小聂");
//		        	map5.put("ItemText", "这个菜让我想起了妈妈的味道 ");
//		        	listItem.add(map5);
//		        	
		        	
		        	
//		        //}
//		        //生成适配器的Item和动态数组对应的元素
//		        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源 
//		            R.layout.list_items,//ListItem的XML实现
//		            //动态数组与ImageItem对应的子项        
//		            new String[] {"ItemImage","ItemTitle", "ItemText"}, 
//		            //ImageItem的XML文件里面的一个ImageView,两个TextView ID
//		            new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText}
//		        );
//		       
//		        //添加并且显示
//		        list.setAdapter(listItemAdapter);		
//				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
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
}
