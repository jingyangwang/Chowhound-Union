package com.spoken;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.nian.preferential.R;
import com.nian.preferential.smenu.Market;
import com.nian.preferential.smenu.Market.NearAdapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;


@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Tcy_spoken extends Activity {
	
	
	
	private static final String DIS_DATE[] = { "评论专区", "评论专区"};
private static final String CLASS_DATE[] = { "全部分类", "美食", "休闲娱乐", "丽人",
		"商场购物", "生活服务" };
private static final String AWAY_DATE[] = {  "最热门", "新发布" };

	private ArrayAdapter disAdapter, claAdapter, awayAdapter;

	

	public Spinner disSpi, claSpi, awaySpi;

	public TextView merTital;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private ArrayList<String> list = new ArrayList<String>();
	private ArrayList<String> templist = new ArrayList<String>();
	private List<HashMap<String, String>> listmap = new ArrayList<HashMap<String, String>>();
	private ListView listview;
	private SimpleAdapter simpleAdapter;
	private TextView textview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.tcy_spoken);
		listview = (ListView) findViewById(R.id.choose_mer_list);
		initResourceRefs();//定义找到其中控件id
		initSetting();
		
		String name = getIntent().getStringExtra("MER_NAME");
		merTital.setText(name);
            // textview=(TextView)findViewById(R.id.textView1);
		// findViewById(R.id.button1).setOnClickListener(new
		// View.OnClickListener() {

		// @Override
		// public void onClick(View arg0) {
		//
		// }
		// });

		// ArrayList<String> list=new ArrayList<String>();

		new AsyncTask<String, Void, ArrayList>() {

			@Override
			protected ArrayList doInBackground(String... params) {
				try {
					URL url = new URL(params[0]);
					URLConnection connection = url.openConnection();
					InputStream is = connection.getInputStream();
					InputStreamReader isr = new InputStreamReader(is, "utf-8");
					BufferedReader br = new BufferedReader(isr);

					String line;
					StringBuilder builder = new StringBuilder();
					while ((line = br.readLine()) != null) {

						System.out.println(line);
						builder.append(line);

					}

					br.close();
					isr.close();
					is.close();

					JSONObject root = new JSONObject(builder.toString());
					// System.out.println("id="+root.getInt("id"));
					// System.out.println("fid="+root.getString("fid"));
					// System.out.println("phone="+root.getString("phone"));
					// System.out.println("cont="+root.getString("cont"));
					// System.out.println("time="+root.getString("time"));

					int ss = 0;

					JSONArray array = root.getJSONArray("s0");

					JSONObject lan = array.getJSONObject(0);
					System.out.println("id=" + lan.getInt("count"));

					ss = lan.getInt("count");
				
					//
					for (int w = 1; w <= ss; w++) {
						//

						JSONArray array1 = root.getJSONArray("s" + w);
						for (int i = 0; i < array1.length(); i++) {

							JSONObject lan1 = array1.getJSONObject(i);
							System.out.println("-------------");
							System.out.println("id=" + lan1.getString("id"));
							System.out.println("fid=" + lan1.getString("fid"));
							System.out.println("ip=" + lan1.getString("ip"));
							System.out
									.println("cont=" + lan1.getString("cont"));
							System.out
									.println("time=" + lan1.getString("time"));
							System.out.println("place="
									+ lan1.getString("place"));
							list.add(lan1.getString("id"));
							list.add(lan1.getString("fid"));
							list.add(lan1.getString("ip"));
							list.add(lan1.getString("cont"));
							list.add(lan1.getString("time"));
							list.add(lan1.getString("place"));

						}

					}

				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				return list;
			}

			@Override
			protected void onCancelled() {
				// TODO Auto-generated method stub
				super.onCancelled();
			}

			@Override
			protected void onCancelled(ArrayList result) {
				// TODO Auto-generated method stub
				super.onCancelled(result);
			}

			@Override
			protected void onPostExecute(ArrayList result) {
				// TODO Auto-generated method stub
				
				
				
				
				
				
				
				
				
				
				for (int i = 0; i < result.size() - 1; i += 6) {

					HashMap<String, String> hashmap = new HashMap<String, String>();

					hashmap.put("id", (String) result.get(i));
					System.out.println("<<<<<<<<<<<<<<<");
					hashmap.put("fid", (String) result.get(i + 1));
					hashmap.put("ip", (String) result.get(i + 2));
					hashmap.put("cont", (String) result.get(i + 3));
					hashmap.put("time", (String) result.get(i + 4));
					hashmap.put("place", (String) result.get(i + 5));
					listmap.add(hashmap);
					System.out.println("<<<<<<<<<<<<<<<" + "SDSDSD");

				}

				simpleAdapter = new SimpleAdapter(Tcy_spoken.this, listmap, R.layout.nearby_list_item,
						new String[] { "cont","id",  "ip",  "time", "place" },
						new int[] { R.id.nearby_item_name, R.id.nearby_item_local, R.id.nearby_item_dis1, R.id.nearby_item_dis2,
								R.id.nearby_item_dis3  });

				listview.setAdapter(simpleAdapter);
				listview.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
							long arg3) {
						
						
						
						
						
//						if (convertView == null) {
//							LayoutInflater inflater = LayoutInflater
//									.from(Market.this);
//							convertView = inflater.inflate(R.layout.nearby_list_item, null);
//						
//							init(convertView);
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						
						setTitle("你点击了第" + arg2 + "行");// 设置标题栏显示点击的行
					}
				});
		
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				
				super.onPostExecute(result);
			}

			@Override
			protected void onPreExecute() {
				// TODO Auto-generated method stub
				super.onPreExecute();
			}

			@Override
			protected void onProgressUpdate(Void... values) {
				// TODO Auto-generated method stub
				super.onProgressUpdate(values);
			}

			// }.execute("http://fanyi.youdao.com/openapi.do?keyfrom=testHttpGet&key=850021564&type=data&doctype=xml&version=1.1&q=good");
		}.execute("http://115.28.233.97/chlm/index.php/Home/Index/pl/fid/8");

//		for (int i = 0; i < list.size() - 1; i += 6) {
//
//			HashMap<String, String> hashmap = new HashMap<String, String>();
//
//			hashmap.put("id", list.get(i));
//			System.out.println("<<<<<<<<<<<<<<<");
//			hashmap.put("fid", list.get(i + 1));
//			hashmap.put("ip", list.get(i + 2));
//			hashmap.put("cont", list.get(i + 3));
//			hashmap.put("time", list.get(i + 4));
//			hashmap.put("place", list.get(i + 5));
//			listmap.add(hashmap);
//			System.out.println("<<<<<<<<<<<<<<<" + "SDSDSD");
//
//		}
//
//		simpleAdapter = new SimpleAdapter(this, listmap, R.layout.adapter_item,
//				new String[] { "id", "fid", "ip", "count", "time", "place" },
//				new int[] { R.id.Title, R.id.Title1, R.id.Title2, R.id.Title3,
//						R.id.Title4, R.id.Title5 });
//
//		listview.setAdapter(simpleAdapter);
//		listview.setOnItemClickListener(new OnItemClickListener() {
//
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//					long arg3) {
//				setTitle("你点击了第" + arg2 + "行");// 设置标题栏显示点击的行
//			}
//		});

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	
		
		
		
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
		listview.setDivider(null);
		listview.setDividerHeight(20);

		disSpi.setAdapter(disAdapter);
		claSpi.setAdapter(claAdapter);//顶部spinner加入适配器
		awaySpi.setAdapter(awayAdapter);

		disSpi.setSelection(0);
		claSpi.setSelection(0);
		awaySpi.setSelection(0);	
	
	
	
	
	
	}
}
