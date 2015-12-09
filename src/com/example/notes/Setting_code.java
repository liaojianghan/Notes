package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Setting_code extends Activity implements OnClickListener {
	private ImageView back;
	private EditText phone_num;
	private Button next;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting_code);

		back = (ImageView) findViewById(R.id.setting_code_previous);
		phone_num = (EditText) findViewById(R.id.setting_code_number);
		next = (Button) findViewById(R.id.setting_code_next);

		back.setOnClickListener(this);
		next.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.setting_code_previous:
			finish();
			break;
		case R.id.setting_code_next:
			Toast.makeText(this, "点击了下一步", 2500).show();
			String num = phone_num.getText().toString();
			if (num != null) {
				Intent intent = new Intent(this, Setting_code_query.class);
				intent.putExtra("num", num);
				startActivity(intent);
			}
			break;
		default:
			break;
		}
	}
}
