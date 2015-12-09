package com.example.notes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Group_set_code extends Activity implements OnClickListener {
	private ImageView head, img;
	private TextView name, mail, save;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.barcode);

		head = (ImageView) findViewById(R.id.barcode_pic);
		img = (ImageView) findViewById(R.id.barcode_img);
		name = (TextView) findViewById(R.id.barcode_name);
		mail = (TextView) findViewById(R.id.barcode_mail);
		save = (TextView) findViewById(R.id.barcode_save);

		mail.setOnClickListener(this);
		save.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.barcode_mail:
			Toast.makeText(this, "” œ‰∑¢ÀÕ", 2500).show();
			break;
		case R.id.barcode_save:
			Toast.makeText(this, "±£¥ÊŒ™", 2500).show();
			break;
		default:
			break;
		}

	}
}
