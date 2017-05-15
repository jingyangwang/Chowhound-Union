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

		//����UI���
		mWebView = (WebView) findViewById(R.id.webView1);
		Button b1 = (Button) findViewById(R.id.button1);
		final EditText et = (EditText) findViewById(R.id.editText1);
		et.setText("http://www.baidu.com");
		//et.setText("http://www.nextwpthemes.com/themes/pocket/restaurant/blog-single-post.html");
		et.setText("");
		//�õ�WebSetting��������֧��Javascript�Ĳ���
		mWebView.getSettings().setJavaScriptEnabled(true);
		//����URL
		
		//mWebView.loadUrl("http://www.baidu.com");
		mWebView.loadUrl("http://www.nextwpthemes.com/themes/pocket/restaurant/blog-single-post.html");
		//ʹҳ���ý���
		mWebView.requestFocus();
		//����ť�󶨵���������
		b1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//���ʱ༭���е���ַ
				mWebView.loadUrl(et.getText().toString());
			}
		});
		mWebView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);   //�ڵ�ǰ��webview����ת���µ�url
				return true;
			}
		});
	}



}
