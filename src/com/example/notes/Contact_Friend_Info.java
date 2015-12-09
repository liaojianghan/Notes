package com.example.notes;

import com.example.notes.entity.Friend;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Contact_Friend_Info extends Activity implements OnClickListener {
	private RelativeLayout friend_name;
	private TextView nickName;
	private Button send_msg;
	private ImageView back;
	private ImageView head, sexIcon;
	private TextView name, sign, sex, area, birthday;
	private Friend friend;
	private String str;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_friend_info);

		friend = (Friend) getIntent().getSerializableExtra("friend");
		System.out.println(friend == null);
		System.out.println(friend.getName());
		System.out.println(friend.getSex());
		System.out.println(friend.getAddress());
		System.out.println(friend.getBirthday());
		str = getIntent().getStringExtra("name");

		findView();
		doFindView();
	}

	public void findView() {
		back = (ImageView) findViewById(R.id.contact_friend_msg_previous);
		friend_name = (RelativeLayout) findViewById(R.id.friend_name);
		nickName = (TextView) findViewById(R.id.friend_info_nickname);
		sexIcon = (ImageView) findViewById(R.id.friend_info_sex_icon);
		head = (ImageView) findViewById(R.id.friend_info_head);
		name = (TextView) findViewById(R.id.friend_info_name);
		sign = (TextView) findViewById(R.id.friend_info_sign);
		sex = (TextView) findViewById(R.id.friend_info_sex);
		area = (TextView) findViewById(R.id.friend_info_area);
		birthday = (TextView) findViewById(R.id.friend_info_birthday);
		send_msg = (Button) findViewById(R.id.send_message);
	}

	public void doFindView() {
		nickName.setText(str);
		head.setImageResource(friend.getHead());
		name.setText(friend.getName());
		sex.setText(friend.getSex());
		 if ("男".equals(friend.getSex())) {
		 sexIcon.setImageResource(R.drawable.icon_male);
		 } else {
		 sexIcon.setImageResource(R.drawable.icon_female);
		 }
		sign.setText(friend.getSign());
		area.setText(friend.getAddress());
		birthday.setText(friend.getBirthday());

		back.setOnClickListener(this);
		friend_name.setOnClickListener(this);
		send_msg.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.contact_friend_msg_previous:
			Toast.makeText(this, "--返回--", 2500).show();
			finish();
			break;
		case R.id.friend_name:
			Toast.makeText(this, "修改备注名", 2500).show();
			Intent intent = new Intent(this, Contact_Friend_UpdateName.class);
			startActivity(intent);
			finish();
			break;
		case R.id.send_message:
			Toast.makeText(this, "发送消息", 2500).show();
			break;
		default:
			break;
		}

	}
}
