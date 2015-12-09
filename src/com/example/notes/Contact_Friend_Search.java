package com.example.notes;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;

public class Contact_Friend_Search extends Activity {
	private ImageView back;
	private EditText search_friend;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_friend_search);

		search_friend = (EditText) findViewById(R.id.contact_search_friend);
		back = (ImageView) findViewById(R.id.contact_search_back);
		back.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				finish();// TODO
			}
		});
	}
}
