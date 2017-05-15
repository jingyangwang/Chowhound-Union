package com.jing.search;






import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMap.OnMarkerDragListener;
import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.TextOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.nian.preferential.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.Window;

public class Search extends Activity {

	public MyLocationListenner myListener = new MyLocationListenner();
	MapView mMapView = null;
	BaiduMap mBaiduMap;
	Marker marker;
	private PoiSearch mPoiSearch = null;
	private LocationMode mCurrentMode;
	BitmapDescriptor mCurrentMarker;
	boolean isFirstLoc = true;// 是否首次定位
	LocationClient mLocClient;
	//	public MyLocationListenner myListener = new MyLocationListenner();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		SDKInitializer.initialize(getApplicationContext());  
		setContentView(R.layout.search);
		mMapView = (MapView) findViewById(R.id.bmapView); 
		mBaiduMap = mMapView.getMap();  
		mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL); 

		//  mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);//卫星地图

		mCurrentMode = LocationMode.FOLLOWING;

		mBaiduMap.setMyLocationEnabled(true);
		// 定位初始化
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// 打开gps
		option.setCoorType("bd09ll"); // 设置坐标类型
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
		mBaiduMap
		.setMyLocationConfigeration(new MyLocationConfiguration(
				mCurrentMode, true, null));
		
		
		//显示文字在地图上
		
		LatLng llText = new LatLng(39.86923, 116.397428);  
		//构建文字Option对象，用于在地图上添加文字  
		OverlayOptions textOption = new TextOptions()  
		    .bgColor(0xAAFFFF00)  
		    .fontSize(24)  
		    .fontColor(0xFFFF00FF)  
		    .text(getString(R.string.app_name))  
		    .rotate(-30)  
		    .position(llText); 
		
		
		mBaiduMap.addOverlay(textOption);
		
		
		
		
//		mPoiSearch = PoiSearch.newInstance();
//		
//		
//		mPoiSearch.setOnGetPoiSearchResultListener(poiListener);
//		
//		
//		mPoiSearch.searchInCity((new PoiCitySearchOption())  
//			    .city("北京")  
//			    .keyword("美食")  
//			    .pageNum(10));
//		
		
		
		
		
	}
	
	
	
	//poi接口
	
//	OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener(){  
//	    public void onGetPoiResult(PoiResult result){  
//	    //获取POI检索结果  
//	    }  
//	    public void onGetPoiDetailResult(PoiDetailResult result){  
//	    //获取Place详情页检索结果  
//	    }  
//	};
	
	
	
	
	
	
	
	
	
	
	/**
	 * 定位SDK监听函数
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view 销毁后不在处理新接收的位置
			if (location == null || mMapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
			.accuracy(location.getRadius())
			// 此处设置开发者获取到的方向信息，顺时针0-360
			.direction(1000).latitude(location.getLatitude())
			.longitude(location.getLongitude()).build();
			mBaiduMap.setMyLocationData(locData);
			if (isFirstLoc) {
				isFirstLoc = false;
				LatLng ll = new LatLng(location.getLatitude()*1E6,
						location.getLongitude()*1E6);
				//				LatLng ll = new LatLng(116.895579*1E6,
				//						24.306521*1E6);

				MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
				mBaiduMap.animateMapStatus(u);
			}
		}

		public void onReceivePoi(BDLocation poiLocation) {
		}
	}
	@Override  
	protected void onDestroy() {  
		super.onDestroy();  
		//在activity执行onDestroy时执行mMapView.onDestroy()，实现地图生命周期管理  
		// mMapView.onDestroy();  
		mLocClient.stop();
		// 关闭定位图层
		
		mBaiduMap.setMyLocationEnabled(false);
		//mPoiSearch.destroy();
		mMapView.onDestroy();
		mMapView = null;
		
		super.onDestroy();
	}  
	@Override  
	protected void onResume() {  
		super.onResume();  
		//在activity执行onResume时执行mMapView. onResume ()，实现地图生命周期管理  
		mMapView.onResume();  
	}  
	@Override  
	protected void onPause() {  
		super.onPause();  
		//在activity执行onPause时执行mMapView. onPause ()，实现地图生命周期管理  
		mMapView.onPause();  

	}  
}
