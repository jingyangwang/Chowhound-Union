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
        //绑定Layout里面的ListView
        ListView list = (ListView) findViewById(R.id.ListView01);
        
        //生成动态数组，加入数据
        ArrayList<HashMap<String, Object>> listItem 
        	= new ArrayList<HashMap<String, Object>>();
      //  for(int i=0;i<5;i++)
        //{
        	HashMap<String, Object> map = new HashMap<String, Object>();
        	map.put("ItemImage", R.drawable.ee);//图像资源的ID
        	map.put("ItemTitle", "小明 ");
        	map.put("ItemText", "真的很好吃 ");
        	listItem.add(map);
        	
        	HashMap<String, Object> map0 = new HashMap<String, Object>();
        	map0.put("ItemImage", R.drawable.qq);//图像资源的ID
        	map0.put("ItemTitle", "花花 ");
        	map0.put("ItemText", "你值得拥有，美食爽歪歪了 ");
        	listItem.add(map0);
        	
        	HashMap<String, Object> map1 = new HashMap<String, Object>();
        	map1.put("ItemImage", R.drawable.rr);//图像资源的ID
        	map1.put("ItemTitle", "吃仔lll ");
        	map1.put("ItemText", "我已吃好，尚未吃饱 ");
        	listItem.add(map1);
        	
        	
        	HashMap<String, Object> map2 = new HashMap<String, Object>();
        	map2.put("ItemImage", R.drawable.tt);//图像资源的ID
        	map2.put("ItemTitle", "旺旺 ");
        	map2.put("ItemText", "美食做法不错，就是这个味");
        	listItem.add(map2);
        	
        	
        	HashMap<String, Object> map3 = new HashMap<String, Object>();
        	map3.put("ItemImage", R.drawable.ww);//图像资源的ID
        	map3.put("ItemTitle", "吃货老杨 ");
        	map3.put("ItemText", "真好吃 ，大家尝尝吧 ");
        	listItem.add(map3);
        	
        	
        	HashMap<String, Object> map4 = new HashMap<String, Object>();
        	map4.put("ItemImage", R.drawable.yy);//图像资源的ID
        	map4.put("ItemTitle", "big土豪 ");
        	map4.put("ItemText", "好吃，好吃 ");
        	listItem.add(map4);
        	
        	
        	HashMap<String, Object> map5 = new HashMap<String, Object>();
        	map5.put("ItemImage", R.drawable.uu);//图像资源的ID
        	map5.put("ItemTitle", "平平小聂");
        	map5.put("ItemText", "这个菜让我想起了妈妈的味道 ");
        	listItem.add(map5);
        	
        	
        	
        //}
        //生成适配器的Item和动态数组对应的元素
        SimpleAdapter listItemAdapter = new SimpleAdapter(this,listItem,//数据源 
            R.layout.list_items,//ListItem的XML实现
            //动态数组与ImageItem对应的子项        
            new String[] {"ItemImage","ItemTitle", "ItemText"}, 
            //ImageItem的XML文件里面的一个ImageView,两个TextView ID
            new int[] {R.id.ItemImage,R.id.ItemTitle,R.id.ItemText}
        );
       
        //添加并且显示
        list.setAdapter(listItemAdapter);
        
        //添加点击
        list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				 //setTitle("点击第"+arg2+"个项目");
			}
		});
        
      //添加长按点击
        list.setOnCreateContextMenuListener(new OnCreateContextMenuListener() {
			
			@Override
			public void onCreateContextMenu(ContextMenu menu, View v,
											ContextMenuInfo menuInfo) {
				menu.setHeaderTitle("回复”他“的评论");   
				menu.add(0, 0, 0, "回复他");
				menu.add(0, 1, 0, "不，我只是随便看看");   
			}
		}); 
    }
	
	//长按菜单响应函数
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		 //setTitle("点击了长按菜单里面的第"+item.getItemId()+"个项目"); 
		return super.onContextItemSelected(item);
	}
}