package com.nian.preferential;


import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Gallery;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;




import com.jing.search.PoiSerch;
import com.jing.search.Search;
import com.nian.preferential.smenu.BusinessInformation;
import com.nian.preferential.smenu.BusinessInformation_btxl;
import com.nian.preferential.smenu.BusinessInformation_cprz;
import com.nian.preferential.smenu.BusinessInformation_ftq;
import com.nian.preferential.smenu.BusinessInformation_gbjd;
import com.nian.preferential.smenu.BusinessInformation_hyszt;
import com.nian.preferential.smenu.BusinessInformation_jhj;
import com.nian.preferential.smenu.BusinessInformation_mlzj;
import com.nian.preferential.smenu.BusinessInformation_tcy;
import com.nian.preferential.smenu.BusinessInformation_tjlx;
import com.nian.preferential.smenu.BusinessInformation_wcj;
import com.nian.preferential.smenu.BusinessInformation_xhcy;
import com.nian.preferential.smenu.BusinessInformation_yxrs;
import com.nian.preferential.smenu.ChooseCity;
import com.nian.preferential.smenu.ChooseMer;
import com.nian.preferential.smenu.TwoDimensionalCode;
import com.nian.preferential.util.NianUtil;
import com.nian.preferential.util.log;

import com.zxing.activity.CaptureActivity;



public class HomeActivity extends Activity implements OnClickListener {
	private final static String TAG = "HomeActivity";

	public static final int REQUEST_CODE_CHOOSE_CITY = 1;
	private ImageButton gdjb, yxrs;
	private Button locationBut, capBut, fireBut, riBut, xiaochiBut,
			jiangzheBut, chacanBut, zizhuBut, spaBut, shoushenBut;
	//home_search_edit
	private EditText searchEdit;
	private  TextView t1;
	private Gallery showPic;
	private SharedPreferences shared;
	private int data[] = { R.drawable.gbjd, R.drawable.yxrs, R.drawable.btxl,
			R.drawable.cprz, R.drawable.ftq, R.drawable.hyszt, R.drawable.jhj,
			R.drawable.mlzj, R.drawable.tcy, R.drawable.tjmx, R.drawable.wcj,
			R.drawable.xhcy ,R.drawable.updata};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.home);
		initResourceRefs();
		initSettings();
	}

	private void initResourceRefs() {

		// yxrs=(ImageButton)findViewByid(R.id.yxrs);

		locationBut = (Button) findViewById(R.id.home_location_button);
		capBut = (Button) findViewById(R.id.home_dimensional_code_but);

		fireBut = (Button) findViewById(R.id.home_buttons_mengzhao);
		riBut = (Button) findViewById(R.id.home_buttons_szhw);
		xiaochiBut = (Button) findViewById(R.id.home_buttons_xckc);
		jiangzheBut = (Button) findViewById(R.id.home_buttons_ccdf);
		chacanBut = (Button) findViewById(R.id.home_buttons_ttsy);
		zizhuBut = (Button) findViewById(R.id.home_buttons_zizhu);
		spaBut = (Button) findViewById(R.id.home_buttons_sc);
		shoushenBut = (Button) findViewById(R.id.home_buttons_yp);

		searchEdit = (EditText) findViewById(R.id.home_search_edit);
		showPic = (Gallery) findViewById(R.id.home_gallery_showpic);

		shared = getSharedPreferences(NianUtil.TWO_SHARW_TAG, 0);
		
		t1 = (TextView) findViewById(R.id.t1);
		//capBut = (Button) findViewById(R.id.home_dimensional_code_but);
		
		
		
		
		Typeface fontFace = Typeface.createFromAsset(getAssets(),
				"fonts/yingbi.TTF");
		// 字体文件必须是true type font的格式(ttf)；
		// 当使用外部字体却又发现字体没有变化的时候(以 Droid Sans代替)，通常是因为
		// 这个字体android没有支持,而非你的程序发生了错误

		
		t1.setTypeface(fontFace);
		t1.setText("今日推荐");
		t1.setTextColor(t1.getResources().getColor(R.color.silver));
		
		
		
	}

	private void initSettings() {

		locationBut.setOnClickListener(this);
		capBut.setOnClickListener(this);
		fireBut.setOnClickListener(this);
		riBut.setOnClickListener(this);
		xiaochiBut.setOnClickListener(this);
		jiangzheBut.setOnClickListener(this);
		chacanBut.setOnClickListener(this);
		zizhuBut.setOnClickListener(this);
		spaBut.setOnClickListener(this);
		shoushenBut.setOnClickListener(this);
		searchEdit.setOnClickListener(this);

		showPic.setAdapter(new ImageAdapter());
		showPic.setOnItemClickListener(new Gallery.OnItemClickListener() {
			
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				log.i(TAG, "showPic ,onItemClick");

				Intent it = null;
				switch (arg2%13) {
				case 0:
				
					it = new Intent(HomeActivity.this,
							BusinessInformation_gbjd.class);//宫保鸡丁
					break;
				case 1:
					it = new Intent(HomeActivity.this, BusinessInformation_yxrs.class);//鱼香肉丝
					break;
				case 2:
					it = new Intent(HomeActivity.this,
							BusinessInformation_btxl.class);//冰糖湘莲
					break;
				case 3:
					it = new Intent(HomeActivity.this, BusinessInformation_cprz.class);//脆皮乳猪
					break;
				case 4:
					it = new Intent(HomeActivity.this,
							BusinessInformation_ftq.class);//佛跳墙
					break;
				case 5:
					it = new Intent(HomeActivity.this, BusinessInformation_hyszt.class);//淮扬狮子头
					break;
				case 6:
					it = new Intent(HomeActivity.this,
							BusinessInformation_jhj.class);
					break;
				case 7:
					it = new Intent(HomeActivity.this, BusinessInformation_mlzj.class);
					break;
				case 8:
					it = new Intent(HomeActivity.this, BusinessInformation_tcy.class);
					break;
				case 9:
					it = new Intent(HomeActivity.this, BusinessInformation_tjlx.class);
					break;
				case 10:
					it = new Intent(HomeActivity.this, BusinessInformation_wcj.class);
					break;
				case 11:
					it = new Intent(HomeActivity.this, BusinessInformation_xhcy.class);
				case 12:
					it = new Intent(HomeActivity.this, WebView1.class);
					break;

				}
				HomeActivity.this.startActivity(it);

				// Intent picIntent = new Intent(HomeActivity.this
				// ,BusinessInformation.class);
				// HomeActivity.this.startActivity(picIntent);
			}
		});
	}

	@Override
	public void onClick(View v) {
		Bundle bundle = new Bundle();  
		switch (v.getId()) {
		case R.id.home_location_button:
			Intent localIntent = new Intent(this, ChooseCity.class);
			localIntent.putExtra("NowCity", locationBut.getText());
			startActivityForResult(localIntent, REQUEST_CODE_CHOOSE_CITY);
			
			
		//	Intent butIntent = new Intent(HomeActivity.this,PoiSearchActivity .class);
			//butIntent.putExtra("MER_NAME", name);
		//	startActivity(butIntent);
			
			break;
		case R.id.home_dimensional_code_but:
//			if (shared.getBoolean("TWO_DIM", false)) {
//				Toast.makeText(this, "很遗憾你选中了不再提醒，而且咱还没有这个功能哦 ~ ",
//						Toast.LENGTH_LONG).show();
//			} else {
				//Intent dimIntent = new Intent(this, TwoDimensionalCode.class);
//				Intent dimIntent = new Intent(this, PoiSearchActivity.class);
//				startActivity(dimIntent);
				Intent openCameraIntent = new Intent(HomeActivity.this,CaptureActivity.class);
				startActivityForResult(openCameraIntent, 0);
//			}
			break;
		case R.id.home_buttons_mengzhao:
			
			   Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);  //拍照
               startActivityForResult(intent, 1);  
               break;
		case R.id.home_buttons_szhw:   
			Intent dimIntent = new Intent(HomeActivity.this, PoiSerch.class);// 山珍海味
			  
			bundle.putString("MyString", "海鲜");  
			
			dimIntent.putExtras(bundle);  
			
			startActivity(dimIntent);
			
			
			
//			Uri uri1 = Uri.parse("http://www.haodou.com/recipe/album/60824/"); // 山珍海味
//			Intent it1 = new Intent(Intent.ACTION_VIEW, uri1);
//			startActivity(it1);

//			Intent intent0 = new Intent(HomeActivity.this,PoiSearchActivity.class);
//			startActivity(intent0);
            break;
			
			
		case R.id.home_buttons_xckc:
			Intent Intent1 = new Intent(HomeActivity.this, PoiSerch.class);//小吃快餐
			bundle.putString("MyString", "小吃");  
			Intent1.putExtras(bundle);  
			startActivity(Intent1);
			break;
		case R.id.home_buttons_ccdf:
			
			//http://www.dianping.com/nanjing/food
			Intent Intent2 = new Intent(HomeActivity.this, PoiSerch.class);//粗茶淡饭
			bundle.putString("MyString", "农家乐");  
			Intent2.putExtras(bundle);  
			startActivity(Intent2);;
			break;
			
		case R.id.home_buttons_ttsy:
			Intent Intent3 = new Intent(HomeActivity.this, PoiSerch.class);//饕鶗盛宴
			bundle.putString("MyString", "酒店");  
			Intent3.putExtras(bundle);  
			startActivity(Intent3);
			break;
			
			
			
			
		case R.id.home_buttons_zizhu:
			
			Intent Intent4 = new Intent(HomeActivity.this, PoiSerch.class);//自助
			bundle.putString("MyString", "自助");  
			Intent4.putExtras(bundle);  
			startActivity(Intent4);
			break;
			
			
		case R.id.home_buttons_sc:
			
			Intent Intent5 = new Intent(HomeActivity.this, PoiSerch.class);//烧烤
			bundle.putString("MyString", "烧烤");  
			Intent5.putExtras(bundle);  
			startActivity(Intent5);
			break;
			
		case R.id.home_buttons_yp:
			Button clickBu = (Button) v;
			String name = clickBu.getText().toString();
			//Intent butIntent = new Intent(HomeActivity.this, ChooseMer.class);
			Intent butIntent1 = new Intent(HomeActivity.this,PoiSerch.class);//饮品
			bundle.putString("MyString", "水吧");  
			butIntent1.putExtras(bundle);  
			
			
			//butIntent1.putExtra("MER_NAME", name);
			startActivity(butIntent1);
			break;
		case R.id.home_search_edit:
			Uri uri1 = Uri.parse("http://home.meishichina.com/topic-6.html"); // 吃货雷达   连接每日美食   美食天下
			Intent it1 = new Intent(Intent.ACTION_VIEW, uri1);
					startActivity(it1);
			break;
		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		switch (requestCode) {
		case REQUEST_CODE_CHOOSE_CITY:
			if (data != null) {
				String city = data.getStringExtra("Choose_city");
				if (!TextUtils.isEmpty(city)) {
					locationBut.setText(city);
				} else {
					locationBut.setText(R.string.home_location_default);
				}
			}
			break;
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private class ImageAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return Integer.MAX_VALUE;
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
			ImageView image;
			if (convertView == null) {
				image = new ImageView(HomeActivity.this);
				image.setLayoutParams(new Gallery.LayoutParams(
						(int) (380 * 0.65), (int) (480 * 0.65)));
				image.setScaleType(ImageView.ScaleType.FIT_CENTER);
			} else {
				image = (ImageView) convertView;
			}
			image.setImageResource(data[position % data.length]);

			return image;
		}
	}
}
