package com.example.notes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Regist extends Activity implements OnClickListener {
	private ImageView back, select;
	private EditText number, code, pwd, pwd2;
	private ImageView clear_phonenum, clear_pwd, clear_pwd2;
	private Button regist, get_code;
	private TextView xieyi;
	private int count = 0;
	private boolean isSelected = false;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.regist);

		back = (ImageView) findViewById(R.id.regist_back);
		select = (ImageView) findViewById(R.id.select);
		number = (EditText) findViewById(R.id.phone_num);
		code = (EditText) findViewById(R.id.yanzhengma);
		pwd = (EditText) findViewById(R.id.pwd);
		pwd2 = (EditText) findViewById(R.id.query_pwd);
		clear_phonenum = (ImageView) findViewById(R.id.clear_phonenum);
		clear_pwd = (ImageView) findViewById(R.id.clear_pwd);
		clear_pwd2 = (ImageView) findViewById(R.id.clear_pwd2);
		regist = (Button) findViewById(R.id.start_regist);
		get_code = (Button) findViewById(R.id.get_code);
		xieyi = (TextView) findViewById(R.id.xieyi_content);

		// number.clearFocus();
		doFocus();

		back.setOnClickListener(this);
		get_code.setOnClickListener(this);
		xieyi.setOnClickListener(this);
		regist.setOnClickListener(this);
		select.setOnClickListener(this);
	}

	void doFocus() {
		number.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View arg0, boolean arg1) {
				if (arg1) {
					System.out.println(arg1);
					clear_phonenum.setVisibility(0);
					clear_phonenum.setOnClickListener(new OnClickListener() {
						public void onClick(View arg0) {
							number.setText("");
						}
					});
				} else {
					clear_phonenum.setVisibility(8);
				}
			}
		});
		pwd.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View arg0, boolean arg1) {
				if (arg1) {
					clear_pwd.setVisibility(0);
					clear_pwd.setOnClickListener(new OnClickListener() {
						public void onClick(View arg0) {
							pwd.setText("");
						}
					});
				} else {
					clear_pwd.setVisibility(8);
				}
			}
		});
		pwd2.setOnFocusChangeListener(new OnFocusChangeListener() {
			public void onFocusChange(View arg0, boolean arg1) {
				if (arg1) {
					clear_pwd2.setVisibility(0);
					clear_pwd2.setOnClickListener(new OnClickListener() {
						public void onClick(View arg0) {
							pwd2.setText("");
						}
					});
				} else {
					clear_pwd2.setVisibility(8);
				}
			}
		});
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.regist_back:
			finish();
			break;
		case R.id.get_code:
			Toast.makeText(this, "点击了获取验证码", 2500).show();
			break;
		case R.id.xieyi_content:
			Toast.makeText(this, "点击了协议内容", 2500).show();
			break;
		case R.id.start_regist:
			if (isSelected == true) {
				Toast.makeText(this, "点击了注册", 2500).show();
			} else {
				Toast.makeText(this, "您必须先同意协议内容才可以注册", 2500).show();
			}
			break;
		case R.id.select:
			count++;
			if (count % 2 == 0) {
				isSelected = false;
				select.setImageResource(R.drawable.icon_weixuanzhong);
			} else {
				isSelected = true;
				select.setImageResource(R.drawable.icon_xuanzhong);
			}
			break;
		default:
			break;
		}

	}

}
