package com.example.notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.notes.entity.Friend;
import com.example.notes.utils.InitData;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class Group_set_cover extends Activity implements OnClickListener {
	private ImageView back;
	private EditText search;
	private String name;
	// 11-16
	private GridView bg;
	private List<Map<String, Object>> mImgs;
	private List<Friend> mFriends = new ArrayList<Friend>();
	private String from;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.note_set_cover);

		from = getIntent().getStringExtra("from");

		back = (ImageView) findViewById(R.id.note_set_bg_back);
		back.setOnClickListener(this);
		mFriends=InitData.friendsInit();
		bg = (GridView) findViewById(R.id.bg_grid);
		do_bg();

	}

	void do_bg() {
		mImgs = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < 14; i++) {
			Map<String, Object> item = new HashMap<String, Object>();
			item.put("imageItem", R.drawable.icon_user_img2);
			mImgs.add(item);
		}
		bg.setAdapter(new SimpleAdapter(this, mImgs, R.layout.grid_item,
				new String[] { "imageItem" }, new int[] { R.id.grid_img }));
		bg.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				mImgs.get(arg2);
			}
		});
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.note_set_bg_back:
			doBack(from);
			break;
		default:
			break;
		}

	}

	void doBack(String from) {
		Toast.makeText(this, "--·µ»Ø--", 2500).show();
		Intent intent = null;
		if (from.equals("Contact_GroupInfo_Edit")) {
			intent = new Intent(this, Contact_GroupInfo_Edit.class);
		}
		if (from.equals("Contact_Group_New")) {
			intent = new Intent(this, Contact_Group_New.class);
		}
		setResult(500, intent);
		finish();// TODO
	}

	@Override
	public void onBackPressed() {
		// super.onBackPressed();
		doBack(from);
	}

}
