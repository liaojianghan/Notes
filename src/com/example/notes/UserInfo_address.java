package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfo_address extends Activity implements OnClickListener {
	private ImageView back;
	private TextView address;
	private RelativeLayout location;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.userinfo_address);

		address = (TextView) findViewById(R.id.userinfo_address_show);
		address.setText("浙江杭州");
		location = (RelativeLayout) findViewById(R.id.note_location);
		back = (ImageView) findViewById(R.id.userinfo_address_back);

		location.setOnClickListener(this);
		back.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = null;
		switch (arg0.getId()) {
		case R.id.userinfo_address_back:
			intent = new Intent(this, Me_UserInfo.class);
			intent.putExtra("area", address.getText().toString());
			startActivity(intent);
			finish();
			break;
		case R.id.note_location:
			Toast.makeText(this, "定位", 2500).show();
			break;
		default:
			break;
		}

	}

	void Location(View v) {

	}
}
