package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Me_UserInfo extends Activity implements OnClickListener {
	private ImageView back;
	private int[] ids = { R.id.note_set_head, R.id.note_set_name,
			R.id.note_set_code, R.id.note_set_sexs, R.id.note_set_areas,
			R.id.note_set_sign };
	// 11-16
	private ImageView userImg;
	private SetHead_popmenu popmenu;
	private TextView userName, userSex, userArea, userSign;
	private String name, area, sign;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.me_userinfo);

		back = (ImageView) findViewById(R.id.note_settings_back);
		back.setOnClickListener(this);
		userImg = (ImageView) findViewById(R.id.note_set_user_head);
		// 11-16
		userName = (TextView) findViewById(R.id.note_set_userName);
		userSex = (TextView) findViewById(R.id.note_set_userSex);
		userArea = (TextView) findViewById(R.id.note_set_userArea);
		userSign = (TextView) findViewById(R.id.note_set_userSign);

		name = getIntent().getStringExtra("name");
		sign = getIntent().getStringExtra("sign");
		area = getIntent().getStringExtra("area");
		if (null != name) {
			userName.setText(name);
		}
		if (null != sign) {
			userSign.setText(sign);
		}
		if (null != area) {
			userArea.setText(area);
		}

		for (int i = 0; i < ids.length; i++) {
			RelativeLayout rel = (RelativeLayout) findViewById(ids[i]);
			rel.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = null;
		switch (arg0.getId()) {
		case R.id.note_set_head:
			Toast.makeText(this, "���������ͷ��", 3000).show();
			do_setHead(null);
			break;
		case R.id.note_set_name:
			Toast.makeText(this, "�������������", 3000).show();
			intent = new Intent(this, UserInfo_name.class);
			startActivity(intent);
			finish();
			break;
		case R.id.note_set_code:
			Toast.makeText(this, "��������ö�ά��", 3000).show();
			intent = new Intent(this, UserInfo_barcode.class);
			startActivity(intent);
			break;
		case R.id.note_set_sexs:
			Toast.makeText(this, "����������Ա�", 3000).show();
			do_setHead("sex");
			break;
		case R.id.note_set_areas:
			Toast.makeText(this, "��������õ���", 3000).show();
			intent = new Intent(this, UserInfo_address.class);
			startActivity(intent);
			finish();
			break;
		case R.id.note_set_sign:
			Toast.makeText(this, "��������ø���ǩ��", 3000).show();
			intent = new Intent(this, UserInfo_sign.class);
			startActivity(intent);
			finish();
			break;
		case R.id.note_settings_back:
			finish();
			break;
		default:
			break;
		}
	}

	void do_setHead(String str) {
		final String type = str;
		// Ϊ������popmenuʵ�ּ�����
		OnClickListener itemsOnClick = new OnClickListener() {
			public void onClick(View v) {
				popmenu.dismiss();
				if (null != type) {
					switch (v.getId()) {
					case R.id.menu1:
						Toast.makeText(Me_UserInfo.this, "��", 2500).show();
						userSex.setText("��");
						break;
					case R.id.menu2:
						Toast.makeText(Me_UserInfo.this, "Ů", 2500).show();
						userSex.setText("Ů");
						break;
					default:
						break;
					}
				} else {
					switch (v.getId()) {
					case R.id.menu1:
						Toast.makeText(Me_UserInfo.this, "����", 2500).show();
						break;
					case R.id.menu2:
						Toast.makeText(Me_UserInfo.this, "���ֻ����ѡ��", 2500)
								.show();
						break;
					default:
						break;
					}
				}

			}
		};
		// ʵ����SelectPicPopupWindow
		popmenu = new SetHead_popmenu(Me_UserInfo.this, itemsOnClick, type);
		// ��ʾ����λ��
		popmenu.showAtLocation(Me_UserInfo.this.findViewById(R.id.me_userinfo),
				Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL, 0, 0); // ����layout��PopupWindow����ʾ��λ��
	}

}
