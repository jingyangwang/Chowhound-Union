package com.nian.preferential;



import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class WebView1 extends Activity {
	WebView mWebView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.webview1);

		//定义UI组件
		mWebView = (WebView) findViewById(R.id.webView1);
		Button b1 = (Button) findViewById(R.id.button1);
		final EditText et = (EditText) findViewById(R.id.editText1);
		et.setText("http://www.baidu.com");
		//et.setText("http://www.nextwpthemes.com/themes/pocket/restaurant/blog-single-post.html");
		et.setText("");
		//得到WebSetting对象，设置支持Javascript的参数
		mWebView.getSettings().setJavaScriptEnabled(true);
		//载入URL
		
		//mWebView.loadUrl("http://www.baidu.com");
		mWebView.loadUrl("http://www.nextwpthemes.com/themes/pocket/restaurant/blog-single-post.html");
		//使页面获得焦点
		mWebView.requestFocus();
		//给按钮绑定单击监听器
		b1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//访问编辑框中的网址
				mWebView.loadUrl(et.getText().toString());
			}
		});
		mWebView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);   //在当前的webview中跳转到新的url
				return true;
			}
		});
	}



}
