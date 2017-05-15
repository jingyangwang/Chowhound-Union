package com.nian.preferential.smenu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.jing.search.PoiSerch;
import com.jing.search.Search;
import com.nian.preferential.R;
import com.nian.preferential.SortActivity;


public class Lifeservice extends Activity {

	private static final String DIS_DATE[] = { "������", "������", "������",
		"��̨��" ,"ʯ��ɽ��","������" ,"��ͷ����","��ɽ��" ,"ͨ����","˳����" ,"��ƽ��","������","������","ƽ����","������","������","������"};
private static final String CLASS_DATE[] = { "ȫ������", "��ʳ", "��������", "����",
		"�̳�����", "�������" };
private static final String AWAY_DATE[] = {  "������", "�·���" };

	private ArrayAdapter disAdapter, claAdapter, awayAdapter;

	public NearAdapter nAdapter;

	public Spinner disSpi, claSpi, awaySpi;

	public TextView merTital;
	

	public ListView showList;

	HoldView hold = new HoldView();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.lifeservice);
		initResourceRefs();//�����ҵ����пؼ�id
		initSetting();
		String name = getIntent().getStringExtra("MER_NAME");
		merTital.setText(name);
	}

	public void initResourceRefs() {

		showList = (ListView) findViewById(R.id.choose_mer_list);

		merTital = (TextView)findViewById(R.id.choose_mer_tital);
		
		//���õġ�����
		disSpi = (Spinner) findViewById(R.id.nearby_distance_spinner);
		claSpi = (Spinner) findViewById(R.id.nearby_class_spinner);
		awaySpi = (Spinner) findViewById(R.id.nearby_away_spinner);


		nAdapter = new NearAdapter();
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

		showList.setAdapter(nAdapter);
		showList.setDivider(null);
		showList.setDividerHeight(20);

		disSpi.setAdapter(disAdapter);
		claSpi.setAdapter(claAdapter);//����spinner����������
		awaySpi.setAdapter(awayAdapter);

		disSpi.setSelection(2);
		claSpi.setSelection(0);
		awaySpi.setSelection(0);
		showList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				Bundle bundle=new Bundle();
				
				
				Intent swIntent  = null;
				switch (arg2) {
				case 0:
					 swIntent = new Intent(Lifeservice.this , PoiSerch.class);//ҽԺ
					 bundle.putString("MyString", "ҽԺ");  
					 swIntent.putExtras(bundle); 
					 
					break;
				case 1:
					 swIntent = new Intent(Lifeservice.this ,PoiSerch.class);//����
					 bundle.putString("MyString", "����");  
					 swIntent.putExtras(bundle); 
					break;
				case 2:
					 swIntent = new Intent(Lifeservice.this , PoiSerch.class);//ATM
					 bundle.putString("MyString", "ATM");  
					 swIntent.putExtras(bundle); 
					break;
				case 3:
					 swIntent = new Intent(Lifeservice.this , PoiSerch.class);//�ΰ�
					 bundle.putString("MyString", "�ɳ���");  
					 swIntent.putExtras(bundle); 
					break;
				
					
			
				}
				
			     
				startActivity(swIntent);
				
			}
		});

	}

	/**
	 * ���� ����ѡ� �е� ���� 
	 * 
	 * ���� �ǵ� ����̫������
	 * 
	 * @author yuhaiyang
	 *
	 */
	public class NearAdapter extends BaseAdapter {

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return 4;
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
		public View getView(int position, View convertView,ViewGroup parent) {

			if (convertView == null) {
				LayoutInflater inflater = LayoutInflater
						.from(Lifeservice.this);
				convertView = inflater.inflate(R.layout.nearby_list_item, null);
			
				init(convertView);
				
				if(position==0){hold.name.setText("ҽԺ");
				hold.local.setText("ҽ��");
				hold.dis1.setText("2000����");
				//hold.dis2.setText(""); 
				//hold.dis3.setText("");
				}
				else if(position==1){hold.name.setText("����");
				hold.local.setText("����");
				hold.dis1.setText("2000����");
				//hold.dis2.setText(""); 
				//hold.dis3.setText("");
				}
				else if(position==2){hold.name.setText("ATM");
				hold.local.setText("�Զ�ȡ���");
				hold.dis1.setText("2000����");
				//hold.dis2.setText(""); 
				//hold.dis3.setText("");
				}
				else if(position==3){hold.name.setText("�ɳ���");
				hold.local.setText("�ΰ�");
				hold.dis1.setText("2000����");
//				hold.dis2.setText(""); 
//				hold.dis3.setText("");
				}
			
				
				

			}

			return convertView;
		}

		public void init(View convertView) {
			hold.name = (TextView) convertView
					.findViewById(R.id.nearby_item_name);
			hold.local = (TextView) convertView
					.findViewById(R.id.nearby_item_local);
			hold.dis1 = (TextView) convertView
					.findViewById(R.id.nearby_item_dis1);
			hold.dis2 = (TextView) convertView
					.findViewById(R.id.nearby_item_dis2);
			hold.dis3 = (TextView) convertView
					.findViewById(R.id.nearby_item_dis3);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

	class HoldView {
		TextView name, local, dis1, dis2, dis3;
	}
}
