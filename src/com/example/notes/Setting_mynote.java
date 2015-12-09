package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Setting_mynote extends Activity implements OnClickListener {
	private ImageView back, cover;
	private TextView bgm;
	private RelativeLayout set_cover, set_bgm, limit;
	private String mp3Name;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.setting_mynote);

		back = (ImageView) findViewById(R.id.setting_mynote_back);
		cover = (ImageView) findViewById(R.id.mynote_cover);
		bgm = (TextView) findViewById(R.id.mynote_bgm);
		set_cover = (RelativeLayout) findViewById(R.id.setting_note_cover);
		set_bgm = (RelativeLayout) findViewById(R.id.setting_note_bgm);
		limit = (RelativeLayout) findViewById(R.id.mynote_set_limit);

		back.setOnClickListener(this);
		// cover.setOnClickListener(this);
		bgm.setOnClickListener(this);
		set_cover.setOnClickListener(this);
		set_bgm.setOnClickListener(this);
		limit.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		Intent intent = null;
		switch (arg0.getId()) {
		case R.id.setting_mynote_back:
			finish();
			break;
		case R.id.mynote_set_limit:
			intent = new Intent(this, Group_set_limit.class);
			startActivity(intent);
			break;
		case R.id.setting_note_cover:
			Toast.makeText(this, "点击了添加封面", 2500).show();
			intent = new Intent(this, Group_set_cover.class);
			intent.putExtra("from", "Setting_mynote");
			startActivityForResult(intent, 30);
			break;
		case R.id.setting_note_bgm:
			intent = new Intent(this, Group_set_bgm.class);
			intent.putExtra("from", "Setting_mynote");
			startActivityForResult(intent, 31);
			break;

		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// 从设置封面页返回过来
		if (requestCode == 30 && resultCode == 500) {
			// getIntent().get TODO
		}
		// 从设置BGM页返回过来
		if (requestCode == 31 && resultCode == 200) {
			mp3Name = data.getStringExtra("mp3Name");
			if (mp3Name == null) {
				bgm.setText("未选择");
			} else {
				bgm.setText(mp3Name);
			}

		}
		super.onActivityResult(requestCode, resultCode, data);
	}

}
