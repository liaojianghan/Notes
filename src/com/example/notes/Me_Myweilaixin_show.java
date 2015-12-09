package com.example.notes;

import com.example.notes.entity.FutureLetter;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class Me_Myweilaixin_show extends Activity implements OnClickListener {
	private ImageView back, head;
	private TextView showName, showMsg, showTime;
	private ImageView Img1, Img2, Img3;
	private FutureLetter letter;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.me_weilaixin_show);

		letter = (FutureLetter) getIntent().getSerializableExtra("letter");
		System.out.println("**---**"+letter.getFromName());
		back = (ImageView) findViewById(R.id.show_back);
		head = (ImageView) findViewById(R.id.show_img);
		showName = (TextView) findViewById(R.id.show_name);
		showMsg = (TextView) findViewById(R.id.show_msg);
		showTime = (TextView) findViewById(R.id.show_time);
		Img1 = (ImageView) findViewById(R.id.show_img1);
		Img2 = (ImageView) findViewById(R.id.show_img2);
		Img3 = (ImageView) findViewById(R.id.show_img3);

		back.setOnClickListener(this);
		Img1.setOnClickListener(this);
		Img2.setOnClickListener(this);
		Img3.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.show_back:
			finish();
			break;
		case R.id.show_img1:

			break;
		case R.id.show_img2:

			break;
		case R.id.show_img3:

			break;
		default:
			break;
		}

	}
}