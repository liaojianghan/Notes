package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.webkit.WebView;
import android.widget.ImageView;

public class About extends Activity implements OnClickListener {
	private ImageView back;
	private WebView about;
private   String  ad;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.about);

		back = (ImageView) findViewById(R.id.about_maliang_back);
		about = (WebView) findViewById(R.id.about_maliang);

	}

	@Override
	public void onClick(View arg0) {
		Intent intent = null;
		switch (arg0.getId()) {
		case R.id.about_maliang_back:
			finish();
			break;
		case R.id.about_maliang:

			break;

		default:
			break;
		}

	}
}
