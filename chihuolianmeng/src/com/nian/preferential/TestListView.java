package com.nian.preferential;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnCreateContextMenuListener;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class TestListView extends Activity {
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_list);
        //��Layout�����ListView
        ListView list = (ListView) findViewById(R.id.ListView01);
        
        //���ɶ�̬���飬��������
        ArrayList<HashMap<String, Object>> listItem 
        	= new ArrayList<HashMap<String, Object>>();
      //  for(int i=0;i<5;i++)
        //{
        	HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("ItemImage", R.drawable.ee);//ͼ����Դ��ID
        	map.put("ItemTitle", "С�� ");
        	map.put("ItemText", "��ĺܺó� ");
        	listItem.add(map);
        	
        	HashMap<String, Object> map0 = new HashMap<String, Object>();
        	map0.put("ItemImage", R.drawable.qq);//ͼ����Դ��ID
        	map0.put("ItemTitle", "���� ");
        	map0.put("ItemText", "��ֵ��ӵ�У���ʳˬ������ ");
        	listItem.add(map0);
        	
        	HashMap<String, Object> map1 = new HashMap<String, Object>();
        	map1.put("ItemImage", R.drawable.rr);//ͼ����Դ��ID
        	map1.put("ItemTitle", "����lll ");
        	map1.put("ItemText", "���ѳԺã���δ�Ա� ");
        	listItem.add(map1);
        	
        	
        	HashMap<String, Object> map2 = new HashMap<String, Object>();
        	map2.put("ItemImage", R.drawable.tt);//ͼ����Դ��ID
        	map2.put("ItemTitle", "���� ");
        	map2.put("ItemText", "��ʳ���������������ζ");
        	listItem.add(map2);
        	
        	
        	HashMap<String, Object> map3 = new HashMap<String, Object>();
        	map3.put("ItemImage", R.drawable.ww);//ͼ����Դ��ID
        	map3.put("ItemTitle", "�Ի����� ");
        	map3.put("ItemText", "��ó� ����ҳ����� ");
        	listItem.add(map3);
        	
        	
        	HashMap<String, Object> map4 = new HashMap<String, Object>();
        	map4.put("ItemImage", R.drawable.yy);//ͼ����Դ��ID
        	map4.put("ItemTitle", "big���� ");
        	map4.put("ItemText", "�óԣ��ó� ");
        	listItem.add(map4);
        	
        	
        	HashMap<String, Object> map5 = new HashMap<String, Object>();
        	map5.put("ItemImage", R.drawable.uu);//ͼ����Դ��ID
        	map5.put("ItemTitle", "ƽƽС��");
        	map5.put("ItemText", "��������������������ζ�� ");
        	listItem.add(map5);
        	
        	
        	
        //}
        //������������Item�Ͷ�̬�����Ӧ��Ԫ��
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//����Դ 
            R.layout.list_items,//ListItem��XMLʵ��
            //��̬������ImageItem��Ӧ������        
            new String[] {"ItemImage","ItemTitle", "ItemText"}, 
            //ImageItem��XML�ļ������һ��ImageView,����TextView ID
            new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText}
        );
       
        //��Ӳ�����ʾ
        list.setAdapter(listItemAdapter);
        
        //��ӵ��
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				 //setTitle("�����"+arg2+"����Ŀ");
			}
		});
        
      //��ӳ������
        list.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
											ContextMenuInfo menuInfo) {
				menu.setHeaderTitle("�ظ�������������");   
				menu.add(0, 0, 0, "�ظ���");
				menu.add(0, 1, 0, "������ֻ����㿴��");   
			}
		}); 
    }
	
	//�����˵���Ӧ����
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		 //setTitle("����˳����˵�����ĵ�"+item.getItemId()+"����Ŀ"); 
		return super.onContextItemSelected(item);
	}
}