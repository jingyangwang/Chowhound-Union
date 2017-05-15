package com.nian.preferential.smenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jing.search.PoiSerch;
import com.jing.search.Search;
import com.nian.preferential.R;
import com.nian.preferential.SortActivity;


public class RecreationandEntertainment extends Activity {

	private static final String DIS_DATE[] = { "东城区", "西城区", "朝阳区",
		"丰台区" ,"石景山区","海淀区" ,"门头沟区","房山区" ,"通州区","顺义区" ,"昌平区","大兴区","怀柔区","平谷区","密云县","延庆县","开发区"};
private static final String CLASS_DATE[] = { "全部分类", "美食", "休闲娱乐", "丽人",
		"商场购物", "生活服务" };
private static final String AWAY_DATE[] = {  "最热门", "新发布" };

	private ArrayAdapter disAdapter, claAdapter, awayAdapter;

	public NearAdapter nAdapter;

	public Spinner disSpi, claSpi, awaySpi;

	public TextView merTital;
	

	public ListView showList;

	HoldView hold = new HoldView();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.recreation_entertainment);
		initResourceRefs();//定义找到其中控件id
		initSetting();
		String name = getIntent().getStringExtra("MER_NAME");
		merTital.setText(name);
	}

	public void initResourceRefs() {

		showList = (ListView) findViewById(R.id.choose_mer_list);

		merTital = (TextView)findViewById(R.id.choose_mer_tital);
		
		//公用的。。。
		disSpi = (Spinner) findViewById(R.id.nearby_distance_spinner);
		claSpi = (Spinner) findViewById(R.id.nearby_class_spinner);
		awaySpi = (Spinner) findViewById(R.id.nearby_away_spinner);


		nAdapter = new NearAdapter();
		disAdapter = new ArrayAdapter<String>(this,
				R.layout.nearby_spinner_text, DIS_DATE);
		claAdapter = new ArrayAdapter<String>(this,
				R.layout.nearby_spinner_text, CLASS_DATE);
		awayAdapter = new ArrayAdapter<String>(this,
				R.layout.nearby_spinner_text, AWAY_DATE);

	}

	public void initSetting() {
		disAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		claAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		awayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		showList.setAdapter(nAdapter);
		showList.setDivider(null);
		showList.setDividerHeight(20);

		disSpi.setAdapter(disAdapter);
		claSpi.setAdapter(claAdapter);//顶部spinner加入适配器
		awaySpi.setAdapter(awayAdapter);

		disSpi.setSelection(2);
		claSpi.setSelection(0);
		awaySpi.setSelection(0);
		showList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Bundle bundle= new Bundle();
				
				
				Intent swIntent  = null;
				switch (arg2) {
				case 0:
					 swIntent = new Intent(RecreationandEntertainment.this , PoiSerch.class);//ktv
					 bundle.putString("MyString", "ktv");  
						swIntent.putExtras(bundle);  
					//	startActivity(swIntent);
						break;
				case 1:
					 swIntent = new Intent(RecreationandEntertainment.this ,PoiSerch.class);//棋牌室
					 bundle.putString("MyString", "棋牌室");  
						swIntent.putExtras(bundle);
						startActivity(swIntent);
						break;
				case 2:
					 swIntent = new Intent(RecreationandEntertainment.this , PoiSerch.class);//健身房
					 bundle.putString("MyString", "健身房");  
						swIntent.putExtras(bundle);
						//startActivity(swIntent);
					break;
				case 3:
					 swIntent = new Intent(RecreationandEntertainment.this , PoiSerch.class);//游泳馆
					 bundle.putString("MyString", "游泳馆");  
						swIntent.putExtras(bundle);
						//startActivity(swIntent);
					break;
				case 4:
					 swIntent = new Intent(RecreationandEntertainment.this , PoiSerch.class);//按摩房
					 bundle.putString("MyString", "按摩房");  
						swIntent.putExtras(bundle);
						//startActivity(swIntent);
					break;
					
				case 5:
					 swIntent = new Intent(RecreationandEntertainment.this , PoiSerch.class);//酒吧
					 bundle.putString("MyString", "酒吧");  
						swIntent.putExtras(bundle);
						//startActivity(swIntent);
					break;
			
				}
				
				
				
				
				
				startActivity(swIntent);
				
				
//				Intent picIntent = new Intent(RecreationandEntertainment.this,
//						PoiSerch.class);
//				RecreationandEntertainment.this.startActivity(picIntent);
			}
		});

	}

	/**
	 * 复用 附近选项卡 中的 东西 
	 * 
	 * 尼玛 是的 他们太相似了
	 * 
	 * @author yuhaiyang
	 *
	 */
	public class NearAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 5;
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
		public View getView(int position, View convertView,ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = LayoutInflater
						.from(RecreationandEntertainment.this);
				convertView = inflater.inflate(R.layout.nearby_list_item, null);
			
				init(convertView);
				
				if(position==0){hold.name.setText("KTV");
				hold.local.setText("K歌");
				hold.dis1.setText("2000米内");
				//hold.dis2.setText(""); 
				//hold.dis3.setText("");
				}
				else if(position==1){hold.name.setText("棋牌室");
				hold.local.setText("棋牌");
				hold.dis1.setText("2000米内");
				//hold.dis2.setText(""); 
				//hold.dis3.setText("");
				}
				else if(position==2){hold.name.setText("健身房");
				hold.local.setText("锻炼");
				hold.dis1.setText("2000米内");
				//hold.dis2.setText(""); 
				//hold.dis3.setText("");
				}
				else if(position==3){hold.name.setText("游泳馆");
				hold.local.setText("戏水");
				hold.dis1.setText("2000米内");
//				hold.dis2.setText(""); 
//				hold.dis3.setText("");
				}
				else if(position==4){hold.name.setText("按摩房");
				hold.local.setText("惬意");
				hold.dis1.setText("2000米内");
//				hold.dis2.setText(""); 
//				hold.dis3.setText("");
				}
				
				else if(position==5){hold.name.setText("酒吧");
				hold.local.setText("娱乐");
				hold.dis1.setText("2000米内");
//				hold.dis2.setText(""); 
//				hold.dis3.setText("");
				
				}
				
				

			}

			return convertView;
		}

		public void init(View convertView) {
			hold.name = (TextView) convertView
					.findViewById(R.id.nearby_item_name);
			hold.local = (TextView) convertView
					.findViewById(R.id.nearby_item_local);
			hold.dis1 = (TextView) convertView
					.findViewById(R.id.nearby_item_dis1);
			hold.dis2 = (TextView) convertView
					.findViewById(R.id.nearby_item_dis2);
			hold.dis3 = (TextView) convertView
					.findViewById(R.id.nearby_item_dis3);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	class HoldView {
		TextView name, local, dis1, dis2, dis3;
	}
}
