package com.nian.preferential.smenu;

import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.nian.preferential.R;
import com.nian.preferential.TestListView;
import com.nian.preferential.Xx;
import com.nian.preferential.util.log;
import com.spoken.Jhj_Send;
import com.spoken.Jhj_spoken;
import com.spoken.Xhcy_Send;
import com.spoken.Xhcy_spoken;

public class BusinessInformation_xhcy extends Activity implements OnTouchListener {

	public static final String TAG = "BusinessInformation";
	public static final int UP = 1;
	public static final int DOWN = 2;
	public static final int EACH =4 ;
	boolean isShow; // �ж��Ż�ȯ��ʾ�Ƿ���ʾ
	public TextView dianping,shoucang,fenxiang,dianzan;
	View view;
	WindowManager windowManager;
	WindowManager.LayoutParams lp;
	ScrollView sv;
	TextView text;
	ImageButton img;
	GestureDetector mGestureDetector;
	int viewH;
	
	Handler handler = new Handler() {

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case UP:
				windowManager.updateViewLayout(view, lp);
				break;
            case DOWN:
				windowManager.updateViewLayout(view, lp);
				break;
			default:
				break;
			}
			
			super.handleMessage(msg);
		}
         
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.businesse_information_xhcy);
		//setContentView(R.layout.xhcy);
		initResourceRefs();

		windowManager.addView(view, lp);
		isShow = text.getVisibility() == View.VISIBLE;
		
		// �Ż�ȯ��ʾ
		img.setOnClickListener(new ImageButton.OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isShow) {
					text.setVisibility(View.GONE);
					img.setImageResource(R.drawable.arrow_down);
					isShow = false;
				} else {
					text.setVisibility(View.VISIBLE);
					img.setImageResource(R.drawable.arrow_up);
					isShow = true;
				}

			}
		});

	}
	
	
	
	
	
	OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub 
			
			
			
			
			
			
			
			switch (arg0.getId()) {
			case R.id.dianping:

				Intent intent0=new Intent(getApplicationContext(),Xhcy_spoken.class);
				intent0.putExtra("MER_NAME", "��������");
					//startActivity(swIntent);
				startActivity(intent0);
			
			
			
			break;
			case R.id.shoucang:
				Intent intent1=new Intent(getApplicationContext(),Xhcy_Send.class);
				intent1.putExtra("MER_NAME", "��������");
					//startActivity(swIntent);
				startActivity(intent1);
//				  Toast.makeText(BusinessInformation_xhcy.this, "���Ѿ��ɹ��ղش˲��ף����ͣ���ʳ�޾�ͷ",
//						     Toast.LENGTH_LONG).show();
				
				//				Intent intent=new Intent(getApplicationContext(),Xx.class);
//				startActivity(intent);
				break;
			
			case R.id.fenxiang:
				 Intent intent=new Intent(Intent.ACTION_SEND);
			      
			      intent.setType("text/plain");
			      intent.putExtra(Intent.EXTRA_SUBJECT, "����");
			      intent.putExtra(Intent.EXTRA_TEXT, "�������㣬���Գ��ʣ�������������Ԩ���㣬�˶�������Ҫ���𣡣�����(�����ԳԻ�����)");
			      startActivity(Intent.createChooser(intent, "�����Լ�������ʳ"));
			      Toast.makeText(BusinessInformation_xhcy.this, "�����Լ�ϲ������ζ����һ����������̬",
						     Toast.LENGTH_LONG).show();
				break;
			
			case R.id.zan:
//				Intent intent=new Intent(getApplicationContext(),Xx.class);
//				startActivity(intent);
			    Toast.makeText(BusinessInformation_xhcy.this, "���Ѿ��ɹ�����Ŷ",
					     Toast.LENGTH_LONG).show();
				break;
			
			
			
			
			
			
			}	
			
			
			
		}
	};
	
       /**
        *  ��ʼ��
        */
	private void initResourceRefs() {
		img = (ImageButton) findViewById(R.id.bus_info_detail_but);
		text = (TextView) findViewById(R.id.bus_info_explor);
		LayoutInflater inflater = LayoutInflater.from(this);
		view = inflater.inflate(R.layout.bus_info_windows_btxl, null);
		
		
		 dianping=(TextView) view.findViewById(R.id.dianping);
		 shoucang=(TextView) view.findViewById(R.id.shoucang);
		 fenxiang=(TextView) view.findViewById(R.id.fenxiang);
		 dianzan=(TextView) view.findViewById(R.id.zan);
		 dianping.setOnClickListener(listener);
		 shoucang.setOnClickListener(listener);
		 fenxiang.setOnClickListener(listener);
		 dianzan.setOnClickListener(listener);
		

		lp = new WindowManager.LayoutParams(LayoutParams.FILL_PARENT,
				LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				// ����Ϊ�޽���״̬
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
						| WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, // û�б߽�
				// ��͸��Ч��
				PixelFormat.TRANSLUCENT);
		lp.gravity = Gravity.BOTTOM;
        
        lp.windowAnimations = R.style.bus_view;
		sv = (ScrollView) findViewById(R.id.scrollView);
		sv.setOnTouchListener(this);
		
		windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);
	   
	}


	@Override
	protected void onDestroy() {
		windowManager.removeView(view);
		super.onDestroy();
	}

	// scroll view ����onTouch
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		if(event.getAction()==MotionEvent.ACTION_MOVE){
			view.setVisibility(View.GONE);
		}else{
			view.setVisibility(View.VISIBLE);
		} 
		return false;
	}

}
