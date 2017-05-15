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
	boolean isFirstLoc = true;// �Ƿ��״ζ�λ
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

		//  mBaiduMap.setMapType(BaiduMap.MAP_TYPE_SATELLITE);//���ǵ�ͼ

		mCurrentMode = LocationMode.FOLLOWING;

		mBaiduMap.setMyLocationEnabled(true);
		// ��λ��ʼ��
		mLocClient = new LocationClient(this);
		mLocClient.registerLocationListener(myListener);
		LocationClientOption option = new LocationClientOption();
		option.setOpenGps(true);// ��gps
		option.setCoorType("bd09ll"); // ������������
		option.setScanSpan(1000);
		mLocClient.setLocOption(option);
		mLocClient.start();
		mBaiduMap
		.setMyLocationConfigeration(new MyLocationConfiguration(
				mCurrentMode, true, null));
		
		
		//��ʾ�����ڵ�ͼ��
		
		LatLng llText = new LatLng(39.86923, 116.397428);  
		//��������Option���������ڵ�ͼ���������  
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
//			    .city("����")  
//			    .keyword("��ʳ")  
//			    .pageNum(10));
//		
		
		
		
		
	}
	
	
	
	//poi�ӿ�
	
//	OnGetPoiSearchResultListener poiListener = new OnGetPoiSearchResultListener(){  
//	    public void onGetPoiResult(PoiResult result){  
//	    //��ȡPOI�������  
//	    }  
//	    public void onGetPoiDetailResult(PoiDetailResult result){  
//	    //��ȡPlace����ҳ�������  
//	    }  
//	};
	
	
	
	
	
	
	
	
	
	
	/**
	 * ��λSDK��������
	 */
	public class MyLocationListenner implements BDLocationListener {

		@Override
		public void onReceiveLocation(BDLocation location) {
			// map view ���ٺ��ڴ����½��յ�λ��
			if (location == null || mMapView == null)
				return;
			MyLocationData locData = new MyLocationData.Builder()
			.accuracy(location.getRadius())
			// �˴����ÿ����߻�ȡ���ķ�����Ϣ��˳ʱ��0-360
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
		//��activityִ��onDestroyʱִ��mMapView.onDestroy()��ʵ�ֵ�ͼ�������ڹ���  
		// mMapView.onDestroy();  
		mLocClient.stop();
		// �رն�λͼ��
		
		mBaiduMap.setMyLocationEnabled(false);
		//mPoiSearch.destroy();
		mMapView.onDestroy();
		mMapView = null;
		
		super.onDestroy();
	}  
	@Override  
	protected void onResume() {  
		super.onResume();  
		//��activityִ��onResumeʱִ��mMapView. onResume ()��ʵ�ֵ�ͼ�������ڹ���  
		mMapView.onResume();  
	}  
	@Override  
	protected void onPause() {  
		super.onPause();  
		//��activityִ��onPauseʱִ��mMapView. onPause ()��ʵ�ֵ�ͼ�������ڹ���  
		mMapView.onPause();  

	}  
}
