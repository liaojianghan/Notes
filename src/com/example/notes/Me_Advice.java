package com.example.notes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class Me_Advice extends Activity {
	private EditText advices;
	private Button submit;
	private ImageView back;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.me_advice);

		advices = (EditText) findViewById(R.id.advices);

		submit = (Button) findViewById(R.id.submit);
		submit.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				Toast.makeText(Me_Advice.this, "点击了提交按钮", 3000).show();

			}
		});

		back = (ImageView) findViewById(R.id.me_advice_previous);
		back.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				finish();
			}
		});
	}
}
