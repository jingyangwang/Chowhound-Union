package com.nian.preferential.smenu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.nian.preferential.HomeActivity;
import com.nian.preferential.R;

public class ChooseCity extends Activity {

	private static final String TAG = "ChooseCity";
	
	TextView topText;
	Intent gIntent;
	ListView listView;
	LayoutInflater inflater;
	View headView;
	Button headItemBut;
	ChooseAdapter coAdapter;
	
	String DATA[][] = new String [][] {
		/*eg  A , 地区*/	
			{"A","鞍山","安阳"},
		{"B","北京","保定","包头","本溪","白山","蚌埠","安庆","白银","宝鸡","北海"},
		{"C","成都","重庆","长春","长沙","常州","承德","沧州","长治","赤峰","朝阳","常州","滁州","巢湖","潮州","常德","郴州"},
		{"D","大连","大同","丹东","大庆","德阳","达州","东莞","东营","德州"},
		{"E","鄂州"},
		{"F","福州","抚顺","阜新","阜阳","防城港","佛山"},
		{"G","广州","贵阳","广元","广安","桂林","贵港","赣州"},
		{"H","杭州","哈尔滨","合肥","海口","邯郸","衡水","呼和浩特","葫芦岛","鹤岗","黑河","淮阴","湖州","淮南","淮北","黄山","汉中","惠州 ","河源","衡阳","怀化","鹤壁","黄石","黄冈"},
		{"J","济南","晋城","锦州","吉林","鸡西","佳木斯","嘉兴","金华","嘉峪关","金昌","江门","揭阳","焦作","荆门","荆州","济宁","景德镇","九江"},
		{"K","昆明","克拉玛依","开封"},
		{"L","兰州","廊坊","辽阳","辽源","连云港","六安","龙岩","六盘水","泸州","乐山","柳州","梅州","娄底","洛阳","漯河","莱芜","临沂","聊城"},
		{"M","牡丹江","绵阳","茂名"},
		{"N","南京","南昌","宁波","南宁","南通","南平","内江","南充","南阳"},
		{"P","盘锦","莆田","攀枝花","平顶山","濮阳","萍乡"},
		{"Q","青岛","秦皇岛","齐齐哈尔","七台河","衢州","泉州","曲靖","钦州","清远"},
		{"R","日照"},
		{"S","上海","深圳","沈阳","苏州","石家庄","朔州","四平","松原","白城","双鸭山","苏州","宿迁","绍兴","宿州","三明","石嘴山","遂宁","三亚","韶关","汕头","汕尾","邵阳","三门峡","商丘","十堰"},
		{"T","天津","太原","唐山","通辽","铁岭","通化","泰州","台州","铜陵","天水","铜川","泰安"},
		{"W","武汉","无锡","温州","乌海","芜湖","乌鲁木齐","吴忠","渭南","梧州","潍坊","威海"},
		{"X","西安","厦门","邢台","徐州","西宁","咸阳","湘潭","新乡","许昌","信阳","襄樊","孝感","咸宁","新余"},
		{"Y","烟台","扬州","阳泉","营口","伊春","盐城","扬州","银川","延安","榆林","玉溪","宜宾","玉林","阳江","云浮","岳阳","益阳","永州","宜昌","烟台","鹰潭"},
		{"Z","珠海","郑州","张家口","镇江","舟山","漳州","遵义","自贡","湛江","肇庆","中山","株洲","张家界","枣庄"},
	
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_city);                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
		initResourceRefs();
		initSettings();
	}
	private void initResourceRefs(){
		
		topText = (TextView)findViewById(R.id.choose_top_tital);
		listView = (ListView)findViewById(R.id.choose_city);
		gIntent = getIntent();
		inflater = LayoutInflater.from(this);
		headView = inflater.inflate(R.layout.choose_city_headview, null);
		
		headItemBut = (Button)headView.findViewById(R.id.choose_headview_but);
		coAdapter = new ChooseAdapter();
	}
	private void initSettings(){
		final String city = gIntent.getStringExtra("NowCity");
		topText.setText(getString(R.string.choose_city_text, city));
		
		listView.addHeaderView(headView);
		headItemBut.setText(getString(R.string.choose_current_city, getString(R.string.home_location_default)/*完成定位系统后切换*/));
		listView.setAdapter(coAdapter);
		headView.setClickable(true);
		headView.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(ChooseCity.this ,HomeActivity.class);
				ChooseCity.this.setResult(RESULT_CANCELED);;
				ChooseCity.this.finish();
			}
		});
	}
	@Override
	protected void onDestroy() {
		// 退出的时候 返回一个值
		setResult(RESULT_CANCELED);
		super.onDestroy();
	}
    // listView 的 adapter
	private class ChooseAdapter extends BaseAdapter{

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return DATA.length;
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
			ChooseCityListItem item ;
			if(convertView == null){
				item = new ChooseCityListItem(ChooseCity.this);
				convertView = item.getView();
				convertView.setTag(item);
			}else{
				item =(ChooseCityListItem)convertView.getTag();
			}
			 item.bind(getData(position));
			return convertView;
		}
		 
		public String[] getData(int position){
			String []a = new String[DATA[position].length];;
			for(int i=0 ;i<DATA[position].length ;i++){
				a[i]=DATA[position][i];
			}
			return a;
		}
	}
	
	/**
	 *
	 * item
	 */
	public class ChooseCityListItem {
		
		public static final String TAG = "ChooseCityListItem";
		private Context mContext;
		private String[] mData;
		private TextView text;
		private GridView grid;
		ChooseItemAdapter ciAdapter;
		View mView;

		public ChooseCityListItem(Context context) {
			mContext = context;
			init();
		}

		public View getView(){
			return mView;
		}

		public void bind(String[]data){
			mData = data;
			if(ciAdapter == null){
				ciAdapter = new ChooseItemAdapter();
				grid.setAdapter(ciAdapter);
			}else{
				ciAdapter.notifyDataSetChanged();
			}
			text.setText(mData[0]);
		}

		public void init(){
			LayoutInflater inflater = LayoutInflater.from(mContext);
			mView =inflater.inflate(R.layout.choose_city_list_item, null);
			text = (TextView)mView.findViewById(R.id.choose_list_item_text);
			grid = (GridView)mView.findViewById(R.id.choose_list_item_grid);
			
		}
		// listView 中的  GridView 的 adapter
		private class ChooseItemAdapter extends BaseAdapter{

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mData.length-1;
			}

			@Override
			public Object getItem(int position) {
				// TODO Auto-generated method stub
				return position+1;
			}

			@Override
			public long getItemId(int position) {
				// TODO Auto-generated method stub
				return position+1;
			}

			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				final TextView text ;
				if(convertView == null){
					text = new TextView(mContext);
					text.setTextColor(Color.BLACK);
					text.setGravity(Gravity.CENTER_HORIZONTAL);
					text.setTextSize(20);
					text.setClickable(true);
					text.setOnClickListener(new TextView.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							String city= text.getText().toString();
							Intent intent = new Intent(ChooseCity.this ,HomeActivity.class);
							intent.putExtra("Choose_city", city);
							ChooseCity.this.setResult(RESULT_OK, intent);
							ChooseCity.this.finish();
						}
					});
				}else{
					text = (TextView)convertView ;
				}
     
	            text.setText(mData[position+1]);	      
				return text;
			}
		 
		  private void setTextSeting(){
			  
		  }
			
		}

	}

}
