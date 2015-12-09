package com.example.notes;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Setting_about extends Activity implements OnClickListener {
	private ImageView back;
	private TextView xieyi;
	private RelativeLayout introduce, checkupdate;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting_about);

		back = (ImageView) findViewById(R.id.setting_about_back);
		xieyi = (TextView) findViewById(R.id.xieyi);
		introduce = (RelativeLayout) findViewById(R.id.setting_about_introduce);
		checkupdate = (RelativeLayout) findViewById(R.id.setting_about_update);

		back.setOnClickListener(this);
		introduce.setOnClickListener(this);
		checkupdate.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = null;
		switch (arg0.getId()) {
		case R.id.setting_about_back:
			finish();
			break;
		case R.id.xieyi:
    
			break;
		
		case R.id.setting_about_introduce:
			intent = new Intent(this, About.class);
			startActivity(intent);

			break;
		case R.id.setting_about_update:
			AlertDialog.Builder ab = new AlertDialog.Builder(this);
			ab.setTitle("警告").setMessage("发现新版本，是否更新？")
					.setPositiveButton("是", new AlertDialog.OnClickListener() {
						public void onClick(DialogInterface arg0, int arg1) {
							// 跳转到相册选择页面 TODO
							Toast.makeText(Setting_about.this, "更新成功！", 2500)
									.show();
						}
					}).setNegativeButton("否", null).show();
			break;
		default:
			break;
		}

	}
}
