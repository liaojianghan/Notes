package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfo_name extends Activity implements OnClickListener {
	private ImageView back;
	private TextView title;
	private EditText search;
	private String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_friend_search);

		title = (TextView) findViewById(R.id.head_title);
		title.setText("�û���");
		back = (ImageView) findViewById(R.id.contact_search_back);
		search = (EditText) findViewById(R.id.contact_search_friend);

		back.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.contact_search_back:
			doBack();
			break;
		default:
			break;
		}

	}

	void doBack() {
		Toast.makeText(this, "--����--", 2500).show();
		name = search.getText().toString();
		Intent intent = new Intent(this, Me_UserInfo.class);
		intent.putExtra("name", name);
		startActivity(intent);
		finish();// TODO
	}

	// ��д���ؼ��ķ���
	public void onBackPressed() {
		// super.onBackPressed();
		doBack();
	}
}
