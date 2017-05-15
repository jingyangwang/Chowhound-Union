package com.jing.search;

import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapPoi;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.sug.OnGetSuggestionResultListener;
import com.baidu.mapapi.search.sug.SuggestionResult;
import com.baidu.mapapi.search.sug.SuggestionSearch;


import com.jing.search.MyorientationListener.OnOrientationListener;
import com.nian.preferential.R;

public class PoiSerch extends Activity implements OnGetPoiSearchResultListener ,OnGetSuggestionResultListener{
	private PoiSearch mPoiSearch = null;
	private SuggestionSearch mSuggestionSearch = null;//接口
	private BaiduMap mBaiduMap = null;
	private MapView mMapView;
	private BaiduMap  mBitmap;
	private Context context;
	//定位相关
	private LocationClient mLocationClient;
	private MyLocationListener mLocationListener;
	private boolean isFirstIn=true;
	private double   mlatide;
	private double mlongtitude;
	private LocationMode mlocationmode;
	//自定义定位图标
	private BitmapDescriptor mIconLocation;
	private  MyorientationListener myorientationListener;
	private float mCurentX;
	//覆盖物相关
	private  BitmapDescriptor mMarker;
	private RelativeLayout mMarkerLy;
	/**
	 * 搜索关键字输入窗口
	 */
	private AutoCompleteTextView keyWorldsView = null;
	private ArrayAdapter<String> sugAdapter = null;//适配器
	private int load_Index = 0;
	private  String poistring;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		SDKInitializer.initialize(getApplicationContext()); 
		setContentView(R.layout.activity_poi_serch);
		//PoiNearbySearchOption poibound;
//		PoiBoundSearchOption poibound=new PoiBoundSearchOption();
//	
//		
//		//poibound.keyword("饭店").bound("")
//		
//	PoiNearbySearchOption poi=new PoiNearbySearchOption();
	//poi.radius(1000).keyword("酒店").location()
		this.context=this;
		mPoiSearch = PoiSearch.newInstance();
		mPoiSearch.setOnGetPoiSearchResultListener(this);
		mSuggestionSearch = SuggestionSearch.newInstance();//获取检索实例
		mSuggestionSearch.setOnGetSuggestionResultListener(this);//设置结果回调监听的监听器。
//		keyWorldsView = (AutoCompleteTextView) findViewById(R.id.searchkey);
//		sugAdapter = new ArrayAdapter<String>(this,
//				android.R.layout.simple_dropdown_item_1line);//适配器
//		keyWorldsView.setAdapter(sugAdapter);
		
		sugAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_dropdown_item_1line);
		
		initview();
		//初始化定位
		initLocation();
		//inintMarket();
//		mPoiSearch.searchInCity((new PoiCitySearchOption())
//				.city(editCity.getText().toString())
//				.keyword(editSearchKey.getText().toString())
////				.pageNum(load_Index));
//		mPoiSearch.searchInCity((new PoiCitySearchOption())
//				.city(editCity.getText().toString())
//				.keyword("酒店")
//				.pageNum(load_Index));
		//addoverlay(Info.infos);
		
		//获取检索相关附近1000米内的poi
		Bundle bundle = getIntent().getExtras();  
		//ParcelableData parcelableData = bundle.getParcelable("MyData");  
		 poistring = bundle.getString("MyString"); 
		
	}
	
	
//	public class PoiNearbySearchOption{
//		
//		
//		
//	}
	
//private class PoiBoundSearchOption{
//	
//	public  PoiBoundSearchOption keyword(String keyword){
//		
//		return ;
//		
//	}
//}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.poi_serch, menu);
		return true;
	}

	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub


		switch (item.getItemId()) {
		case R.id.map_common:
			mBitmap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
			break;
		case R.id.map_site:
			mBitmap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);
			break;
		case R.id.map_traffic:
			if(mBitmap.isTrafficEnabled()){
				mBitmap.setTrafficEnabled(false);
			}
			else{
				mBitmap.setTrafficEnabled(true);
			}
			break;
		case R.id.map_mywei:
			LatLng latLng=new LatLng(mlatide,mlongtitude);
			MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latLng);
			mBitmap.animateMapStatus(msu);
			break;
		case R.id.map_mode_common:
		mlocationmode=LocationMode.NORMAL;
			break;
		case R.id.map_mode_update:
			mlocationmode=LocationMode.FOLLOWING;
			break;
		case R.id.map_compass:
			mlocationmode=LocationMode.COMPASS;
			break;
		case R.id.add_overlay:
			//addoverlay(Info.infos);
			break;
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	
	@Override  //回调接口方法
	public void onGetPoiDetailResult(PoiDetailResult result) {
		// TODO Auto-generated method stub
		
		
		
		if (result.error != SearchResult.ERRORNO.NO_ERROR) {
			Toast.makeText(PoiSerch.this, "抱歉，未找到结果", Toast.LENGTH_SHORT)
					.show();
		} else {
			Toast.makeText(PoiSerch.this, result.getName() + ": " + result.getAddress(), Toast.LENGTH_SHORT)
			.show();
		}
	}
	private class MyPoiOverlay extends PoiOverlay {

		public MyPoiOverlay(BaiduMap baiduMap) {
			super(baiduMap);
		}

		@Override
		public boolean onPoiClick(int index) {
			super.onPoiClick(index);
//			PoiInfo poi = getPoiResult().getAllPoi().get(index);
//			// if (poi.hasCaterDetails) {
//				mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
//						.poiUid(poi.uid));
			// onReceiveLocation(BDLocation location);
//			PoiNearbySearchOption poi=new PoiNearbySearchOption();
			//LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
//				poi.radius(1000).location(latLng).keyword("酒店");
//			
			//mPoiSearch.searchNearby(new PoiNearbySearchOption().radius(1000).keyword("酒店").pageCapacity(10).location(latLng));
			// }
//			MapPoi mappoi=new MapPoi();
//			
//			String ss=mappoi.getName();
//			String sss=ss+"";
			
			PoiInfo aa=new PoiInfo();
			String ss=(String)aa.name;
			Toast.makeText(PoiSerch.this, poistring, Toast.LENGTH_LONG).show();
			//Toast.makeText(context, "想知道具体信息吗 ，你自己去看啊看不就知道了吗", Toast.LENGTH_LONG).show();
			

			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
			
//			AlertDialog.Builder builder = new Builder(PoiSerch.this);
//			builder.setMessage(ss);
//			builder.setTitle("dshsajdh");
//			builder.setPositiveButton("确认", new OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//					PoiSerch.this.finish();
//				}
//			});
//			builder.setNegativeButton("取消", new OnClickListener() {
//				@Override
//				public void onClick(DialogInterface dialog, int which) {
//					dialog.dismiss();
//				}
//			});
//			builder.create().show();
//			
			
			
			
			
			
			
			
			
			
			
			
			
			return true;
		}
	}
	
	
//	protected void dialog() {
//		AlertDialog.Builder builder = new Builder(PoiSerch.this);
//		builder.setMessage("确认退出吗？");
//		builder.setTitle("提示");
//		builder.setPositiveButton("确认", new OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//				PoiSerch.this.finish();
//			}
//		});
//		builder.setNegativeButton("取消", new OnClickListener() {
//			@Override
//			public void onClick(DialogInterface dialog, int which) {
//				dialog.dismiss();
//			}
//		});
//		builder.create().show();
//	}
	
	
	
	@Override //回调接口方法
	public void onGetPoiResult(PoiResult result) {
		// TODO Auto-generated method stub
		//PoiOverlay  poim=new PoiOverlay(arg0);
		
		if (result == null
				|| result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
			Toast.makeText(PoiSerch.this, "未找到结果", Toast.LENGTH_LONG)
			.show();
			return;
		}
		if (result.error == SearchResult.ERRORNO.NO_ERROR) {
			mBitmap.clear();
			PoiOverlay overlay = new MyPoiOverlay(mBitmap);
			mBitmap.setOnMarkerClickListener(overlay);
			overlay.setData(result);
			overlay.addToMap();
			overlay.zoomToSpan();
			return;
		}
		if (result.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {

			// 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
			String strInfo = "在";
			for (CityInfo cityInfo : result.getSuggestCityList()) {
				strInfo += cityInfo.city;
				strInfo += ",";
			}
			strInfo += "找到结果";
			Toast.makeText(PoiSerch.this, strInfo, Toast.LENGTH_LONG)
					.show();
			
			
			
			
			
			
			
			
//			if (result == null || result.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {  
//		        return;  
//		    }  
//		    if (result.error == SearchResult.ERRORNO.NO_ERROR) {  
//		        mBitmap.clear();  
//		        //创建PoiOverlay  
//		        PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);  
//		        //设置overlay可以处理标注点击事件  
//		        mBitmap.setOnMarkerClickListener(overlay);  
//		        //设置PoiOverlay数据  
//		        overlay.setData(result);  
//		        //添加PoiOverlay到地图中  
//		        overlay.addToMap();  
//		        overlay.zoomToSpan();  
//		        return;  
//		    }  

			
			
		}
	}
	
	
	//添加覆盖物
//		private void addoverlay(List<Info> infos) {
//			// TODO Auto-generated method stub
//			mBitmap.clear();
//			LatLng latlng=null;
//			Marker marker=null;
//			OverlayOptions options;
//			
//			
//			for(Info info:infos){
//			//经纬度
//				latlng=new LatLng(info.getLatitude(),info.getLongitude());
//				//图标
//				options=new MarkerOptions().position(latlng).icon(mIconLocation).zIndex(5);
//				marker=(Marker) mBitmap.addOverlay(options);
//				Bundle arg0=new Bundle();
//				arg0.putSerializable("info", info);
//				marker.setExtraInfo(arg0);
//			}
//			MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latlng);
//			mBitmap.setMapStatus(msu);
//			
//			
//		}


	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void initLocation() {
		// TODO Auto-generated method stub
		mlocationmode=LocationMode.NORMAL;
		mLocationClient=new  LocationClient(this);
		mLocationListener =new MyLocationListener();
		mLocationClient.registerLocationListener(mLocationListener);
		LocationClientOption option=new LocationClientOption();
		option.setCoorType("bd09ll");
		//option.setNeedAddress(true);
		option.setOpenGps(true);
		//	option.setIsNeedAddress(true);
		option.setScanSpan(1000);
		mLocationClient.setLocOption(option);
		//初始化定位图标
		mIconLocation=BitmapDescriptorFactory.fromResource(R.drawable.weizhi);
		myorientationListener=new MyorientationListener(context);
		myorientationListener.setOnOrientationListener(new OnOrientationListener(){

			@Override
			public void onOrientationchanged(float x) {
				// TODO Auto-generated method stub
				mCurentX=x;
			}

		});


	}

	//定位显示的图标
	private void initview(){
		mMapView=(MapView) findViewById(R.id.idmapview);
		mBitmap=mMapView.getMap();
		MapStatusUpdate  msu=MapStatusUpdateFactory.zoomTo(15.0f);
		mBitmap.setMapStatus(msu);

		//		//定义Maker坐标点  
		//		LatLng point = new LatLng(39.963175, 116.400244);  
		//		//构建Marker图标  
		//		BitmapDescriptor bitmap = BitmapDescriptorFactory  
		//				.fromResource(R.drawable.ic_launcher);  
		//		//构建MarkerOption，用于在地图上添加Marker  
		//		OverlayOptions option = new MarkerOptions()  
		//		.position(point)  
		//		.icon(bitmap);  
		//		//在地图上添加Marker，并显示  
		//		mBitmap.addOverlay(option);

	} 
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		mBitmap.setMyLocationEnabled(true);
		if(!mLocationClient.isStarted()){
			mLocationClient.start();
		}
		//开启方向传感器
		myorientationListener.start();

	}
	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		//开启定位
		mBitmap.setMyLocationEnabled(false);
		mLocationClient.stop();
		//停止方向传感器
		myorientationListener.stop();	


	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		mMapView.onResume();  
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mPoiSearch.destroy();
		mSuggestionSearch.destroy();
		super.onDestroy();
		mMapView.onDestroy(); 
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mMapView.onPause();  
	}
	
//	private void inintMarket() {
//		// TODO Auto-generated method stub
//		
//		mMarker=BitmapDescriptorFactory.fromResource(R.drawable.asd);
//		mMarkerLy=(RelativeLayout) findViewById(R.id.id_mark_ly);
//		
//		
//	}
	
	private class MyLocationListener implements  BDLocationListener{

		@Override
		public void onReceiveLocation(BDLocation location) {
			// TODO Auto-generated method stub
			MyLocationData data=new MyLocationData.Builder()
			.direction(mCurentX)
			.accuracy(location.getRadius())
			.latitude(location.getLatitude())
			.longitude(location.getLongitude())
			.build();
			
			
		
			mBitmap.setMyLocationData(data);
			// 设置自定义图标
			MyLocationConfiguration config=new MyLocationConfiguration(mlocationmode, true, mIconLocation);

			mBitmap.setMyLocationConfigeration(config);
			//更新经纬度			
			mlatide=location.getLatitude();
			mlongtitude=location.getLongitude();
			if(isFirstIn){
				LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
				MapStatusUpdate msu=MapStatusUpdateFactory.newLatLng(latLng);
				mBitmap.animateMapStatus(msu);
//				PoiNearbySearchOption poi=new PoiNearbySearchOption();
//			//	LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
//				poi.radius(1000).location(latLng).keyword("酒店");
				mPoiSearch.searchNearby(new PoiNearbySearchOption().radius(5000).keyword(poistring).pageCapacity(20).location(latLng));
				isFirstIn=false;
				//Toast.makeText(context, location.getAddrStr(),Toast.LENGTH_LONG).show();
			}
		}

		@Override
		public void onReceivePoi(BDLocation location1) {
			// TODO Auto-generated method stub
			
//			PoiNearbySearchOption poi=new PoiNearbySearchOption();
//			LatLng latLng=new LatLng(location1.getLatitude(),location1.getLongitude());
//			poi.radius(1000).location(latLng).keyword("酒店");
			
//			PoiOverlay poioverlay = new PoiOverlay(PoiSearchActivity_Agritainment.this, mMapView);
//			// 设置搜索到的POI数据
//			poioverlay.setData(result.getAllPoi());
//			// 在地图上显示PoiOverlay（将搜索到的兴趣点标注在地图上）
//			mapView.getOverlays().add(poioverlay);
			if (mLocationClient != null && mLocationClient.isStarted())
				mLocationClient.requestPoi();
		}
	
	
		
	
	
	
	
	
	
	
	
	
}

	@Override
	public void onGetSuggestionResult(SuggestionResult res) {
		// TODO Auto-generated method stub
		if (res == null || res.getAllSuggestions() == null) {
			return;
		}
		sugAdapter.clear();
		for (SuggestionResult.SuggestionInfo info : res.getAllSuggestions()) {
			if (info.key != null)
				sugAdapter.add(info.key);
		}
		sugAdapter.notifyDataSetChanged();
	}}
