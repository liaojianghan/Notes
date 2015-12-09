package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Setting_code_query extends Activity implements OnClickListener {
	private String phone_num;
	private ImageView back;
	private TextView title, delay_time;
	private EditText input_code;
	private Button query;
	private final Handler handler = new Handler();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.query_code);
		phone_num = getIntent().getStringExtra("num");

		back = (ImageView) findViewById(R.id.query_code_previous);
		title = (TextView) findViewById(R.id.query_code_title);
		delay_time = (TextView) findViewById(R.id.delay_time);
		input_code = (EditText) findViewById(R.id.input_code);
		query = (Button) findViewById(R.id.query);

		title.setText("请输入手机号" + phone_num + "收到的验证码");
		// delay_time.setClickable(false); //无效
		doHandle();

		back.setOnClickListener(this);
		delay_time.setOnClickListener(this);
		query.setOnClickListener(this);

	}

	void doHandle() {

		handler.postDelayed(new Runnable() {
			int i = 60;

			public void run() {
				i--;
				if (i == 0) {
					delay_time.setText("重新发送");
					delay_time.setClickable(true);
					return;
				}
				delay_time.setText(i + "秒后重新发送");
				handler.postDelayed(this, 1000);
			}
		}, 1000);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.query_code_previous:
			finish();
			break;
		case R.id.delay_time:
			Toast.makeText(this, "重新发送", 2500).show();
			break;
		case R.id.query:
			Toast.makeText(this, "点击了验证", 2500).show();
			// Intent intent = new Intent(this, Setting_code_query.class);
			// startActivity(intent);

			break;
		default:
			break;
		}

	}
}
