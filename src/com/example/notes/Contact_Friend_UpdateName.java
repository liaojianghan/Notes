package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Contact_Friend_UpdateName extends Activity implements
		OnClickListener {
	private ImageView back;
	private TextView title;
	private EditText search;
	private String name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置无标题
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_friend_search);

		title = (TextView) findViewById(R.id.head_title);
		title.setText("用户名");
		back = (ImageView) findViewById(R.id.contact_search_back);
		search = (EditText) findViewById(R.id.contact_search_friend);

		back.setOnClickListener(this);

	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.contact_search_back:
			Toast.makeText(this, "--返回--", 2500).show();
			name = search.getText().toString();
			Intent intent = new Intent(this, Contact_Friend_Info.class);
			intent.putExtra("name", name);
			startActivity(intent);
			finish();// TODO
			break;
		// case R.id.group_edit_update:
		// Toast.makeText(this, "编辑多人笔记本", 2500).show();
		// break;
		// case R.id.set_note_msg:
		// Toast.makeText(this, "设置笔记的信息", 2500).show();
		// break;
		// case R.id.myname_in_note:
		// Toast.makeText(this, "我在笔记本中的昵称", 2500).show();
		// break;
		// case R.id.jubao:
		// Toast.makeText(this, "举报一时爽，呵呵呵呵", 2500).show();
		// break;
		// case R.id.quit_group:
		// Toast.makeText(this, "退出笔记本", 2500).show();
		// break;
		default:
			break;
		}

	}
}
