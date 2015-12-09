package com.example.notes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import com.example.notes.adapter.addToGroupAdapter;
import com.example.notes.entity.Friend;
import com.example.notes.utils.InitData;
import com.example.notes.view.MyLetterListView;
import com.example.notes.view.MyLetterListView.OnTouchingLetterChangedListener;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Contact_Group_Add extends Activity implements OnClickListener {
	public static HashMap<String, Integer> alphaIndex;// 存放存在的汉语拼音首字母和与之对应的列表位置
	public static String[] sections;// 存放存在的汉语拼音首字母
	private HashMap<String, Integer> selector;// 存放含有索引字母的位置
	private String[] indexStr = { "#", "A", "B", "C", "D", "E", "F", "G", "H",
			"I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U",
			"V", "W", "X", "Y", "Z" };
	private List<Friend> friends = new ArrayList<Friend>();
	public static List<Friend> selected = new ArrayList<Friend>();
	private addToGroupAdapter adapter;
	private LayoutInflater inflater;

	private ListView newGroup;
	private ImageView back;
	private Button ok;
	private int index;

	private TextView overlay; // 对话框首字母textview
	private MyLetterListView rightIndex; // A-Z listview
	private Handler handler;
	private OverlayThread overlayThread; // 显示首字母对话框
	private boolean isNeedFresh;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_group_add);

		selector = new HashMap<String, Integer>();
		overlayThread = new OverlayThread();
		handler = new Handler();
		isNeedFresh = true;

		back = (ImageView) findViewById(R.id.contact_group_new_back);
		ok = (Button) findViewById(R.id.group_add_ok);
		back.setOnClickListener(this);
		ok.setOnClickListener(this);

		newGroup = (ListView) findViewById(R.id.newGroup);
		rightIndex = (MyLetterListView) findViewById(R.id.rightIndex);
		rightIndex.setBackgroundColor(Color.parseColor("#00ffffff"));
		rightIndex
				.setOnTouchingLetterChangedListener(new LetterListViewListener());

		System.out.println("***&^^***");
		// 模拟初始化数据
		initOverlay();
		friends = InitData.friendsInit();
		System.out.println("排序后好友长度：" + friends.size());
		// 循环字母表，找出新集合-newFriends中对应字母的位置
		selector = new HashMap<String, Integer>();
		for (int j = 0; j < indexStr.length; j++) {
			for (int i = 0; i < friends.size(); i++) {
				if (friends.get(i).getName().equals(indexStr[j])) {
					selector.put(indexStr[j], i);
				}
			}
		}
		// 适配器添加数据
		adapter = new addToGroupAdapter(this);
		newGroup.setAdapter(adapter);

	}

	private boolean mReady;

	// 初始化汉语拼音首字母弹出提示框
	private void initOverlay() {
		mReady = true;
		LayoutInflater inflater = LayoutInflater.from(this);
		overlay = (TextView) inflater.inflate(R.layout.overlay, null);
		overlay.setVisibility(View.INVISIBLE);
		WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_APPLICATION,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
						| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
				PixelFormat.TRANSLUCENT);
		WindowManager windowManager = (WindowManager) this
				.getSystemService(Context.WINDOW_SERVICE);
		windowManager.addView(overlay, lp);
	}

	private class LetterListViewListener implements
			OnTouchingLetterChangedListener {

		private boolean isScroll = false;

		public void onTouchingLetterChanged(final String s) {
			isScroll = false;
			if (alphaIndex.get(s) != null) {
				int position = alphaIndex.get(s);
				newGroup.setSelection(position);
				overlay.setText(s);
				overlay.setVisibility(View.VISIBLE);
				handler.removeCallbacks(overlayThread);
				// 延迟一秒后执行，让overlay为不可见
				handler.postDelayed(overlayThread, 1000);
			}
		}
	}

	// 设置overlay不可见
	private class OverlayThread implements Runnable {
		@Override
		public void run() {
			overlay.setVisibility(View.GONE);
		}
	}

	@Override
	public void onClick(View arg0) {
		switch (arg0.getId()) {
		case R.id.contact_group_new_back:

			finish();
			break;
		case R.id.group_add_ok:
			Intent intent = new Intent(this, Contact_Group_New.class);
			// Bundle bundle = new Bundle();
			// bundle.putSerializable("selected", (Serializable) selected);
			intent.putExtra("selected", (Serializable) selected);
			startActivity(intent);
			break;
		default:
			break;
		}

	}

}
