package com.example.notes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class Group_set_limit extends Activity {
	private ImageView back;

	private RadioGroup limit;
	private RadioButton toAll, secret;
	private EditText question, answer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.note_set_limit);

		toAll = (RadioButton) findViewById(R.id.toAll);
		secret = (RadioButton) findViewById(R.id.secret);
		question = (EditText) findViewById(R.id.set_question);
		answer = (EditText) findViewById(R.id.set_answer);

		limit = (RadioGroup) findViewById(R.id.note_limit);
		limit.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup arg0, int arg1) {
				int radioButtonId = arg0.getCheckedRadioButtonId();
				// 根据ID获取RadioButton的实例
				RadioButton rb = (RadioButton) Group_set_limit.this
						.findViewById(radioButtonId);
				Toast.makeText(Group_set_limit.this, "选择了" + rb.getText(), 2500)
						.show();

			}
		});

		back = (ImageView) findViewById(R.id.contact_search_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();// TODO
			}
		});
	}
}
