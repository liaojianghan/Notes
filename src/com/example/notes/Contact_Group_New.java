package com.example.notes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.notes.entity.Friend;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Contact_Group_New extends Activity implements OnClickListener {
	private ImageView head, cover, back;
	private EditText name, wishes;
	private TextView bgm;
	private RelativeLayout setcover, setbgm;
	private Button ok;
	private List<Friend> list;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.group_new_edit);

		head = (ImageView) findViewById(R.id.new_group_head);
		cover = (ImageView) findViewById(R.id.new_group_showCover);
		back = (ImageView) findViewById(R.id.new_group_back);
		ok = (Button) findViewById(R.id.new_group_ok);

		name = (EditText) findViewById(R.id.new_group_showName);
		wishes = (EditText) findViewById(R.id.new_group_showWishes);
		bgm = (TextView) findViewById(R.id.new_group_showBgm);

		setcover = (RelativeLayout) findViewById(R.id.new_group_cover);
		setbgm = (RelativeLayout) findViewById(R.id.new_group_bgm);

		setcover.setOnClickListener(this);
		setbgm.setOnClickListener(this);
		back.setOnClickListener(this);
		head.setOnClickListener(this);
		ok.setOnClickListener(this);
		list = (List<Friend>) getIntent().getSerializableExtra("selected");
		if(null!=list){
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getName());
			}
		}else{
			System.out.println("--为空--");
		}
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = null;

		switch (arg0.getId()) {
		case R.id.contact_group_new_back:
			finish();
			break;
		case R.id.new_group_ok:
			intent = new Intent(this, Contact_Group_Edit.class);

			startActivity(intent);
			break;
		case R.id.new_group_cover:
			intent = new Intent(this, Group_set_cover.class);
			intent.putExtra("from", "Contact_Group_New");
			startActivityForResult(intent, 10);
			break;
		case R.id.new_group_bgm:
			intent = new Intent(this, Group_set_bgm.class);
			intent.putExtra("from", "Contact_Group_New");
			startActivityForResult(intent, 11);
			break;
		case R.id.new_group_head:
			// intent = new Intent(this, Group_set_cover.class);
			// intent.putExtra("from", "Contact_Group_New");
			// startActivityForResult(intent, 10);
			break;
		default:
			break;
		}

	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 从设置封面页返回过来
		if (requestCode == 10 && resultCode == 500) {
			// getIntent().get
		}
		// 从设置BGM页返回过来
		if (requestCode == 11 && resultCode == 200) {
			String mp3Name = data.getStringExtra("mp3Name");
			if (mp3Name == null) {
				bgm.setText("未选择");
			} else {
				bgm.setText(mp3Name);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
