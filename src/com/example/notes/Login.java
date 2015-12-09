package com.example.notes;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends Activity implements OnClickListener {
	private EditText name, pwd;
	private TextView regist, lost_pwd;
	private Button login;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.login);
		name = (EditText) findViewById(R.id.login_name);
		pwd = (EditText) findViewById(R.id.login_pwd);
		regist = (TextView) findViewById(R.id.regist);
		lost_pwd = (TextView) findViewById(R.id.lost_pwd);
		login = (Button) findViewById(R.id.login_button);

		regist.setOnClickListener(this);
		lost_pwd.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View arg0) {
		String username;
		String password;
		Intent intent;
		switch (arg0.getId()) {
		case R.id.regist:
			intent = new Intent(this, Regist.class);
			startActivity(intent);
			//finish();
			break;
		case R.id.lost_pwd:
			intent = new Intent(this, Setting_code.class);
			startActivity(intent);
			break;
		case R.id.login_button:
			username = name.getText().toString();
			password = pwd.getText().toString();
			// µÇÂ¼ÑéÖ¤ TODO
			if (username.equals("aa") && password.equals("123")) {
				intent = new Intent(this, Main.class);
				startActivity(intent);
				finish();
			}
			break;

		default:
			break;
		}

	}
}
