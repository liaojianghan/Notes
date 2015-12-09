package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Contact_GroupInfo_Edit extends Activity implements OnClickListener {
	private int[] ids = { R.id.group_set_head, R.id.group_set_name,
			R.id.group_set_code, R.id.group_set_bgm, R.id.group_set_sign,
			R.id.group_set_cover, R.id.group_set_limit, };

	private ImageView back;
	private SetHead_popmenu popmenu;
	private ImageView showHead, showCover;
	private TextView showName, showBgm, showSign;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.group_info_edit);

		for (int i = 0; i < ids.length; i++) {
			RelativeLayout rel = (RelativeLayout) findViewById(ids[i]);
			rel.setOnClickListener(this);
		}
		back = (ImageView) findViewById(R.id.note_msg_edit_back);
		back.setOnClickListener(this);
		// 11-16
		showHead = (ImageView) findViewById(R.id.group_set_showhead);
		showCover = (ImageView) findViewById(R.id.group_set_showcover);
		showName = (TextView) findViewById(R.id.group_set_showname);
		showBgm = (TextView) findViewById(R.id.group_set_showbgm);
		showSign = (TextView) findViewById(R.id.group_set_showsign);

	}

	@Override
	public void onClick(View arg0) {
		Intent intent = null;
		switch (arg0.getId()) {
		case R.id.group_set_head:
			doHead(null);
			break;
		case R.id.group_set_name:
			intent = new Intent(this, Group_set_name.class);
			startActivityForResult(intent, 20);
			break;
		case R.id.group_set_code:
			intent = new Intent(this, Group_set_code.class);
			startActivity(intent);
			break;
		case R.id.group_set_bgm:
			intent = new Intent(this, Group_set_bgm.class);
			intent.putExtra("from", "Contact_GroupInfo_Edit");
			startActivityForResult(intent, 21);
			break;
		case R.id.group_set_sign:
			intent = new Intent(this, Group_set_sign.class);
			startActivityForResult(intent, 22);
			break;
		case R.id.group_set_cover:
			intent = new Intent(this, Group_set_cover.class);
			intent.putExtra("from", "Contact_GroupInfo_Edit");
			startActivityForResult(intent, 23);
			break;
		case R.id.group_set_limit:
			intent = new Intent(this, Group_set_limit.class);
			startActivity(intent);
			break;
		default:
			// 按左上返回键
			finish();
			break;
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 从设置名称页返回过来
		if (requestCode == 20 && resultCode == 100) {
			String Name = data.getStringExtra("name");
			if (Name == null) {
				showName.setText("未选择");
			} else {
				showName.setText(Name);
			}
		}
		// 从设置BGM页返回过来
		if (requestCode == 21 && resultCode == 200) {
			String mp3Name = data.getStringExtra("mp3Name");
			if (mp3Name == null) {
				showBgm.setText("未选择");
			} else {
				showBgm.setText(mp3Name);
			}
		}
		// 从设置签名页返回过来
		if (requestCode == 22 && resultCode == 400) {
			String sign = data.getStringExtra("sign");
			if (null == sign) {
				showSign.setText("");
			} else {
				showSign.setText(sign);
			}
		}
		// 从设置封面页返回过来
		if (requestCode == 23 && resultCode == 500) {
			// TODO
		}

		super.onActivityResult(requestCode, resultCode, data);
	}

	public void doHead(String type) {
		// 为弹出的popmenu实现监听类
		OnClickListener itemsOnClick = new OnClickListener() {
			public void onClick(View v) {
				popmenu.dismiss();
				switch (v.getId()) {
				case R.id.menu1:
					Toast.makeText(Contact_GroupInfo_Edit.this, "拍照", 2500)
							.show();
					break;
				case R.id.menu2:
					Toast.makeText(Contact_GroupInfo_Edit.this, "从手机相册选择", 2500)
							.show();
					break;
				default:
					break;
				}
			}
		};
		// 实例化SelectPicPopupWindow
		popmenu = new SetHead_popmenu(Contact_GroupInfo_Edit.this,
				itemsOnClick, type);
		// 显示窗口位置
		popmenu.showAtLocation(
				Contact_GroupInfo_Edit.this.findViewById(R.id.group_msg_edit),
				Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // 设置layout在PopupWindow中显示的位置
	}
}
