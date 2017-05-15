package com.nian.preferential;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
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
import com.nian.preferential.smenu.BusinessInformation_yxrs;
import com.nian.preferential.smenu.ChooseMer;
import com.nian.preferential.smenu.Lifeservice;
import com.nian.preferential.smenu.Market;
import com.nian.preferential.smenu.RecreationandEntertainment;
import com.nian.preferential.smenu.TwoDimensionalCode;

import com.nian.preferential.ui.SortGrid;
import com.nian.preferential.ui.SortList;
import com.nian.preferential.util.NianUtil;

import com.zxing.activity.CaptureActivity;

public class SortActivity extends Activity{
 
	public final static String TAG = "SortActivity";
	
	private static final int MERCHANT_GRID_ADA_ID = 1; // 最顶端的 图片显示
	private static final int SORT_ALL_ADA_ID = 2 ;  // 中间分类
	private static final int SWITCH_ADA_ID = 3;  // 下端显示
	
	Button titalBut;
	EditText titalEdit;
	SortGrid merchantGrid;
	SortList allList ,switchList;
	SortAdapter merchantAda,allAda , switchAda;
	HoldViewAll allHold;
	HoldViewSwitch switchHold;
	LayoutInflater inflater;
	ButListener butlis;
	SharedPreferences shared;
	
	private int merchantData [] = {R.drawable.sort_merchant_img1,R.drawable.sort_merchant_img2,R.drawable.sort_merchant_img3,R.drawable.sort_merchant_img4,
			R.drawable.wqlm,R.drawable.mdl,R.drawable.kdj,R.drawable.bsk};
	
	private int allListData[][] = new int [][]{{R.string.sort_alllist_string_1_1,R.string.sort_alllist_string_1_2,R.string.sort_alllist_string_1_3,R.string.sort_alllist_string_1_4,},
			{R.string.sort_alllist_string_2_1,R.string.sort_alllist_string_2_2,R.string.sort_alllist_string_2_3,R.string.sort_alllist_string_2_4,},
			{R.string.sort_alllist_string_3_1,R.string.sort_alllist_string_3_2,R.string.sort_alllist_string_3_3,R.string.sort_alllist_string_3_4,},
			{R.string.sort_alllist_string_4_1,R.string.sort_alllist_string_4_2,R.string.sort_alllist_string_4_3,R.string.sort_alllist_string_4_4,},
			}; 
	
	private int switchListData[][] = new int [][]{
			// img id  text id 
			{R.drawable.sort_sq_new ,R.string.sort_switch_string_1},
			{R.drawable.sort_ms_new ,R.string.sort_switch_string_2},
			{R.drawable.sort_lr_new ,R.string.sort_switch_string_3},
			{R.drawable.sort_xxyl_new ,R.string.sort_switch_string_4},
			{R.drawable.sort_shfw_new ,R.string.sort_switch_string_5},
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sort);
		initResourceRefs();
		initSettings();
	}
    public void initResourceRefs(){
    	
    	titalBut = (Button)findViewById(R.id.sort_tital_but);
    	titalEdit = (EditText)findViewById(R.id.sort_tital_edit);
    	merchantGrid = (SortGrid)findViewById(R.id.sort_merchant_grid);
    	
    	allList = (SortList)findViewById(R.id.sort_all_list);
    	switchList = (SortList)findViewById(R.id.sort_switch_list);
    	
    	merchantAda = new SortAdapter(MERCHANT_GRID_ADA_ID);
    	allAda = new SortAdapter(SORT_ALL_ADA_ID);
    	switchAda = new SortAdapter(SWITCH_ADA_ID);
    	
    	inflater = LayoutInflater.from(SortActivity.this);
    	
    	butlis = new ButListener();
    	
    	shared = getSharedPreferences(NianUtil.TWO_SHARW_TAG, 0);
    }
    
    public void initSettings(){
    	merchantGrid.setAdapter(merchantAda);
    	merchantGrid.setSelector(new ColorDrawable(Color.TRANSPARENT));
    	
    	allList.setAdapter(allAda);
    	switchList.setAdapter(switchAda);
    	
    	merchantGrid.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
//				Intent picIntent = new Intent(SortActivity.this ,BusinessInformation.class);
//				SortActivity.this.startActivity(picIntent);
				
				
				
				
				
				Intent it = null;
				switch (arg2) {
				case 0:
					Uri uri1 = Uri.parse("http://www.pinpaibao.org/brand/Yoshinoya/"); // 吉野家
					Intent it1 = new Intent(Intent.ACTION_VIEW, uri1);
							startActivity(it1);
					break;
				case 1:
					Uri uri2 = Uri.parse("http://www.zkungfu.com/"); //真功夫
					Intent it2 = new Intent(Intent.ACTION_VIEW, uri2);
					startActivity(it2);
					break;
				case 2:
					Uri uri3 = Uri.parse("http://www.yonghe.com.cn/"); // 永和大王
					Intent it3 = new Intent(Intent.ACTION_VIEW, uri3);
					startActivity(it3);
					break;
				case 3:
					Uri uri4 = Uri.parse("http://www.littlesheep.com/"); // 小肥羊
					Intent it4 = new Intent(Intent.ACTION_VIEW, uri4);
					startActivity(it4);
					break;
				case 4:
					Uri uri5 = Uri.parse("http://www.ajisen.com.cn/"); //味千拉面
					Intent it5 = new Intent(Intent.ACTION_VIEW, uri5);
					startActivity(it5);
					break;
				case 5:
					Uri uri6 = Uri.parse("http://www.mcdonalds.com.cn/cn/ch/index.html"); //麦当劳
					Intent it6 = new Intent(Intent.ACTION_VIEW, uri6);
					startActivity(it6);
					break;
				case 6:
					Uri uri7 = Uri.parse("http://www.kfc.com.cn/kfccda/index.aspx"); // 肯德基
					Intent it7 = new Intent(Intent.ACTION_VIEW, uri7);
					startActivity(it7);
					break;
				case 7:
					Uri uri8 = Uri.parse("http://www.pizzahut.com.cn/phdi/index.aspx#!handler/home.ashx?&tagid=&proid="); // 必胜客
					Intent it8 = new Intent(Intent.ACTION_VIEW, uri8);
					startActivity(it8);
					break;
				

				}
			   //startActivity(it);

				// Intent picIntent = new Intent(HomeActivity.this
				// ,BusinessInformation.class);
				// HomeActivity.this.startActivity(picIntent);
			}
		});
				
				
				
				
				
	
				
				
		
    	
    	switchList.setOnItemClickListener( new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Bundle bundle=new Bundle();
				

				Intent swIntent  = null;
				switch (arg2) {
				case 0:
					 swIntent = new Intent(SortActivity.this , Market.class);//商圈选择
					break;
				case 1:
					 swIntent = new Intent(SortActivity.this ,PoiSerch.class);//美食
					 bundle.putString("MyString", "美食");  
						swIntent.putExtras(bundle);  
					break;
				case 2:
					 swIntent = new Intent(SortActivity.this , PoiSerch.class);//丽人
					 bundle.putString("MyString", "美容院");  
						swIntent.putExtras(bundle); 
					break;
				case 3:
					 swIntent = new Intent(SortActivity.this , RecreationandEntertainment.class);//休闲娱乐
					 
					break;
				case 4:
					 swIntent = new Intent(SortActivity.this , Lifeservice.class);//生活服务
					 
					break;
					
			
				}
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
			     String name = getString(switchListData[arg2][1]); 
				
			     swIntent.putExtra("MER_NAME", name);
				startActivity(swIntent);
			}
		});
    	
    	titalBut.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
//				if(shared.getBoolean("TWO_DIM", false)){
//					Toast.makeText(SortActivity.this, "很遗憾你选中了不再提醒，而且咱还没有这个功能哦 ~ ", Toast.LENGTH_LONG).show();
//				}else{
//					Intent dimIntent = new Intent(SortActivity.this,TwoDimensionalCode.class);
//					startActivity(dimIntent);
//				}	
					Intent openCameraIntent = new Intent(SortActivity.this,CaptureActivity.class);
					startActivityForResult(openCameraIntent, 0);
			}
		});
    	
    }
    
    /**
     * 
     * @author yuhaiyang
     *
     */
    private class SortAdapter extends BaseAdapter{

    	int SORT_ID;
    	
    	public SortAdapter(int id ){
    		SORT_ID = id ;
    	}
    	
		@Override
		public int getCount() {
            if(SORT_ID == MERCHANT_GRID_ADA_ID){
				return merchantData.length;
			}else if (SORT_ID == SORT_ALL_ADA_ID){
         		return allListData.length;
			}else{
				return switchListData.length;
			}
			
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
			
			if(SORT_ID == MERCHANT_GRID_ADA_ID){
				 return getMerchantView(convertView,position);
			}else if (SORT_ID == SORT_ALL_ADA_ID){
				return getAllView(convertView, position);
			}else {
				return getSwtichView(convertView, position);
			}
			
		}
		
		/**
		 * 
		 * 商户图片的ViewItem
		 * 
		 * @param convertView
		 * @param position
		 * @return
		 */
		private View getMerchantView( View convertView ,int position){
			ImageView img ;
			 if (convertView == null ){
				 img = new ImageView(SortActivity.this);

		     }else{
				 img = (ImageView)convertView;  		
			 }	
			 img.setLayoutParams(new GridView.LayoutParams((int)(128*0.8),(int)(98*0.8)));
			 img.setScaleType(ImageView.ScaleType.FIT_CENTER); 
			 img.setImageResource(merchantData[position]);
		     return img;
        }
		
		/**
		 * 获取恶心的 16个选项的 View 
		 * @param convertView
		 * @param position
		 * @return
		 */
		private View getAllView(View convertView ,int position){
			allHold = new HoldViewAll();
			if(convertView == null){
				convertView = inflater.inflate(R.layout.sort_alllist_item, null);
				allHold.allBu1 = (Button)convertView.findViewById(R.id.sort_all_item_but_1);
				allHold.allBu2 = (Button)convertView.findViewById(R.id.sort_all_item_but_2);
				allHold.allBu3 = (Button)convertView.findViewById(R.id.sort_all_item_but_3);
				allHold.allBu4 = (Button)convertView.findViewById(R.id.sort_all_item_but_4);
				
				allHold.allBu1.setOnClickListener(butlis);
				allHold.allBu2.setOnClickListener(butlis);
				allHold.allBu3.setOnClickListener(butlis); 
				allHold.allBu4.setOnClickListener(butlis);
				
				convertView.setTag(allHold);
			}else{
				allHold = (HoldViewAll) convertView.getTag();
			}
				allHold.allBu1.setText(allListData[position][0]);
				allHold.allBu2.setText(allListData[position][1]);
				allHold.allBu3.setText(allListData[position][2]);
				allHold.allBu4.setText(allListData[position][3]);
			return convertView;
		}
		
		/**
		 * 获取最下方的VIew
		 * @param convertView
		 * @param position
		 * @return
		 */
		private View getSwtichView(View convertView ,int position){
			switchHold = new HoldViewSwitch();
			if(convertView == null){
				convertView = inflater.inflate(R.layout.sort_switch_item, null);
				switchHold.img =(ImageView)convertView.findViewById(R.id.sort_switch_item_img);
				switchHold.text =(TextView)convertView.findViewById(R.id.sort_switch_item_text);
				convertView.setTag(switchHold);
			}else{
				switchHold = (HoldViewSwitch)convertView.getTag();
			}
			switchHold.img.setImageResource(switchListData[position][0]);
			switchHold.text.setText(switchListData[position][1]);
			return convertView;
		}
		
		
		
    }
    public class ButListener implements OnClickListener{

		@Override
		public void onClick(View v) {
			Button clickBu = (Button)v;
			String name = clickBu.getText().toString();
			Intent butIntent = new Intent(SortActivity.this , fenlei.class);
			butIntent.putExtra("MER_NAME", name);
			startActivity(butIntent);
			
		}
		
	}
    class HoldViewAll{
       Button allBu1,allBu2,allBu3,allBu4;	
    }
    class HoldViewSwitch{
        ImageView img ;
        TextView text;
        
     }
}
