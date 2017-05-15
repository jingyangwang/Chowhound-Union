package com.spoken;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.nian.preferential.R;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Build;

public class Yxrs_Send extends Activity {
	
	private EditText et;
	private TextView text;
	String ss;
	HttpClient client;
	private static final String DIS_DATE[] = { "评论专区", "评论专区"};
private static final String CLASS_DATE[] = { "全部分类", "美食", "休闲娱乐", "丽人",
		"商场购物", "生活服务" };
private static final String AWAY_DATE[] = {  "最热门", "新发布" };

	private ArrayAdapter disAdapter, claAdapter, awayAdapter;

	

	public Spinner disSpi, claSpi, awaySpi;

	public TextView merTital;
	
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.yxrs_send);
		initResourceRefs();//定义找到其中控件id
		initSetting();
		
		
		client = new DefaultHttpClient();
		
		et = (EditText) findViewById(R.id.editText1);
		text = (TextView) findViewById(R.id.textView1);
		String name = getIntent().getStringExtra("MER_NAME");
		merTital.setText(name);
		findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				//ss=et.getText();
				//readNet("http://10.0.2.2:8080/MyWebTest/Do?name="+et.getText()?);
				readNet("http://115.28.233.97/chlm/index.php/Home/Index/insert/inser/12,,,,,"+et.getText());
			}
		});
		
	}

	public void readNet(String url) {
		new AsyncTask<String, Void, String>() {

			@Override
			protected String doInBackground(String... arg0) {
				String urlString = arg0[0];
				HttpGet get = new HttpGet(urlString);
				
				try {
					HttpResponse response = client.execute(get);
					
					String value = EntityUtils.toString(response.getEntity());
					
//					System.out.println(value);
					return value;
					
					
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				return null;
			}

			@Override
			protected void onPostExecute(String result) {
//				text.setText("发送成功");
				Toast.makeText(Yxrs_Send.this, "发送成功",Toast.LENGTH_LONG).show();
			}
			
			
		}.execute(url);
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	public void initResourceRefs() {

		//showList = (ListView) findViewById(R.id.choose_mer_list);

		merTital = (TextView)findViewById(R.id.choose_mer_tital);
		
		//公用的。。。
		disSpi = (Spinner) findViewById(R.id.nearby_distance_spinner);
		claSpi = (Spinner) findViewById(R.id.nearby_class_spinner);
		awaySpi = (Spinner) findViewById(R.id.nearby_away_spinner);


		//nAdapter = new NearAdapter();
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

//		showList.setAdapter(nAdapter);
//		listview.setDivider(null);
//		listview.setDividerHeight(20);

		disSpi.setAdapter(disAdapter);
		claSpi.setAdapter(claAdapter);//顶部spinner加入适配器
		awaySpi.setAdapter(awayAdapter);

		disSpi.setSelection(0);
		claSpi.setSelection(0);
		awaySpi.setSelection(0);	
	
	
	
	
	
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
