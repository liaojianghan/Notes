package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class UserInfo_barcode extends Activity implements OnClickListener {
	private ImageView back;
	private Button mail, save;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.barcode);

		back = (ImageView) findViewById(R.id.barcode_back);
		mail = (Button) findViewById(R.id.barcode_mail);
		save = (Button) findViewById(R.id.barcode_save);

		back.setOnClickListener(this);
		mail.setOnClickListener(this);
		save.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = null;
		switch (arg0.getId()) {
		case R.id.barcode_mail:
			Toast.makeText(this, "点击了邮箱发送", 3000).show();
			// TODO
			break;
		case R.id.barcode_save:
			Toast.makeText(this, "点击了保存", 3000).show();
			// TODO
			break;
		case R.id.barcode_back:
			finish();
			break;
		default:
			break;
		}

	}
}
