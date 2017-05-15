package com.jing.search;

import com.baidu.mapapi.map.MyLocationConfiguration.LocationMode;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class MyorientationListener implements SensorEventListener {
	private  Context mcontext;
	private  SensorManager msensormanager;
	private Sensor msensor;
	private float lastX;
	
	public MyorientationListener(Context context) {
		// TODO Auto-generated constructor stub
		this.mcontext=context;

	}

	public void start(){
		msensormanager=(SensorManager) mcontext.getSystemService(Context.SENSOR_SERVICE);

		if(msensormanager!=null){
			//获得方向传感器
			msensormanager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		}
		if(msensor!=null){
			msensormanager.registerListener(this, msensor,SensorManager.SENSOR_DELAY_UI);



		}

	}
	public void stop(){
		msensormanager.unregisterListener(this);
	}



	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if(event.sensor.getType()==Sensor.TYPE_ORIENTATION){
			float x=event.values[SensorManager.DATA_X];
			if(Math.abs(x-lastX)>1.0){
				if(mOnOrientationListener!=null){}
				mOnOrientationListener.onOrientationchanged(x);
			}
			lastX=x;
		}
	}
	public void setOnOrientationListener(
			OnOrientationListener mOnOrientationListener) {
		this.mOnOrientationListener = mOnOrientationListener;
	}
	private  OnOrientationListener mOnOrientationListener;

	public interface OnOrientationListener{
		void onOrientationchanged(float x);


	}

}
