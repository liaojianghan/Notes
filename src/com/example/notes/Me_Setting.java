package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class Me_Setting extends Activity implements OnClickListener {
	private Button exit;
	private ImageView back;
	private int[] ids = { R.id.me_setting_pwd, R.id.me_setting_mynote,
			R.id.me_setting_about, R.id.me_clear_cache,
			R.id.me_setting_changeuser };

	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		// �����ޱ���
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.me_setting);

		exit = (Button) findViewById(R.id.me_setting_exit);
		exit.setOnClickListener(this);
		back = (ImageView) findViewById(R.id.me_advice_previous);
		back.setOnClickListener(this);
		for (int i = 0; i < ids.length; i++) {
			RelativeLayout rel = (RelativeLayout) findViewById(ids[i]);
			rel.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = null;
		switch (arg0.getId()) {
		case R.id.me_setting_pwd:
			Toast.makeText(this, "�������������", 3000).show();
			intent = new Intent(this, Setting_code.class);
			startActivity(intent);
			break;
		case R.id.me_setting_mynote:
			Toast.makeText(this, "��������ñʼ�", 3000).show();
			intent = new Intent(this, Setting_mynote.class);
			startActivity(intent);
			break;
		case R.id.me_setting_about:
			Toast.makeText(this, "����˹�������", 3000).show();
			intent = new Intent(this, Setting_about.class);
			startActivity(intent);
			break;
		case R.id.me_clear_cache:
			Toast.makeText(this, "������������", 3000).show();
			break;
		case R.id.me_setting_changeuser:
			Toast.makeText(this, "����˸����û�", 3000).show();
			intent = new Intent(this, Login.class);
			startActivity(intent);
			break;
		case R.id.me_setting_exit:
			Toast.makeText(this, "������˳���¼", 3000).show();
			break;
		case R.id.me_advice_previous:
			finish();
			break;
		default:
			break;
		}

	}
}
