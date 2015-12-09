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

public class Group_set_name extends Activity implements OnClickListener {
	private ImageView back;
	private TextView title;
	private EditText search;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// �����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_friend_search);

		title = (TextView) findViewById(R.id.head_title);
		title.setText("�޸ıʼ�����");
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

	public void doBack() {
		Toast.makeText(this, "--����--", 2500).show();
		String name = search.getText().toString();
		Intent intent = new Intent(this, Contact_GroupInfo_Edit.class);
		intent.putExtra("name", name);
		setResult(100, intent);
		finish();
	}

	// ��д���ؼ��ķ���
	public void onBackPressed() {
		// super.onBackPressed();
		doBack();
	}
}
