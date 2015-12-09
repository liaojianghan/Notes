package com.example.notes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.notes.entity.Friend;
import com.example.notes.utils.InitData;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class Contact_Group_Edit extends Activity implements OnClickListener {
	private ImageView back;
	private TextView edit;
	private RelativeLayout set_note_msg, myname_in_note, report;
	private Button quit_group;
	private GridView add;
	private List<Map<String, Object>> mImgs;
	private List<Friend> mFriends = new ArrayList<Friend>();

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.contact_group_edit);

		back = (ImageView) findViewById(R.id.group_edit_back);
		edit = (TextView) findViewById(R.id.group_edit_update);
		set_note_msg = (RelativeLayout) findViewById(R.id.set_note_msg);
		myname_in_note = (RelativeLayout) findViewById(R.id.myname_in_note);
		add = (GridView) findViewById(R.id.contact_add_to_group);
		report = (RelativeLayout) findViewById(R.id.report);
		quit_group = (Button) findViewById(R.id.quit_group);

		back.setOnClickListener(this);
		edit.setOnClickListener(this);
		set_note_msg.setOnClickListener(this);
		myname_in_note.setOnClickListener(this);
		report.setOnClickListener(this);
		quit_group.setOnClickListener(this);

		// 11-16 ģ����gridview���������
//		Contact_Group_Add c = new Contact_Group_Add();
//		mFriends = c.selected;
//
//		System.out.println(mFriends == null ? "yes" : "no");
//		System.out.println("******");
//		for (int i = 0; i < mFriends.size(); i++) {
//			System.out.println(mFriends.get(i).getName());
//		}
		mFriends = InitData.friendsInit();
		mImgs = new ArrayList<Map<String, Object>>();
		if (mFriends != null) {
			for (int i = 0; i < mFriends.size(); i++) {
				Map<String, Object> item = new HashMap<String, Object>();
				if (i == mFriends.size() - 1) {
					item.put("imageItem", R.drawable.icon_add_group);
					item.put("tvItem", "���");
					mImgs.add(item);
					break;
				}
				item.put("imageItem", mFriends.get(i).getHead());
				item.put("tvItem", mFriends.get(i).getName());
				mImgs.add(item);
			}
			add.setAdapter(new SimpleAdapter(this, mImgs, R.layout.grid_item,
					new String[] { "imageItem", "tvItem" }, new int[] {
							R.id.grid_img, R.id.grid_tv }));
			add.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,
						int arg2, long arg3) {
					if (arg2 == mImgs.size() - 1) {
						Toast.makeText(Contact_Group_Edit.this, "���һ������ת", 2500)
								.show();
					} else {
						Toast.makeText(Contact_Group_Edit.this,
								"����˵�" + (arg2 + 1) + "��", 2500).show();
					}
				}
			});
		}
	}

	@Override
	public void onClick(View arg0) {
		Intent intent;
		switch (arg0.getId()) {
		case R.id.group_edit_back:
			Toast.makeText(this, "--����--", 2500).show();
			finish();
			break;
		case R.id.group_edit_update:
			Toast.makeText(this, "�༭���˱ʼǱ�", 2500).show();
			// TODO
			break;
		case R.id.set_note_msg:
			Toast.makeText(this, "���ñʼǵ���Ϣ", 2500).show();
			intent = new Intent(this, Contact_GroupInfo_Edit.class);
			startActivity(intent);
			break;
		case R.id.myname_in_note:
			Toast.makeText(this, "���ڱʼǱ��ǳ�", 2500).show();
			intent = new Intent(this, Contact_Group_Nickname.class);
			startActivity(intent);
			finish();
			break;
		case R.id.report:
			doReport();
			break;
		case R.id.quit_group:
			Toast.makeText(this, "�˳��ʼǱ�", 2500).show();
			break;
		default:
			break;
		}

	}

	void doReport() {
		LayoutInflater inflater = (LayoutInflater) this
				.getSystemService(LAYOUT_INFLATER_SERVICE);
		final View view = inflater.inflate(R.layout.alert_textview, null);
		AlertDialog.Builder ab = new AlertDialog.Builder(this);
		ab.setTitle("�ٱ�").setMessage("�ٱ��ñʼ���Υ����Ϣ").setView(view)
				.setPositiveButton("�ٱ�", new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						// ��תҳ�� TODO
						Toast.makeText(Contact_Group_Edit.this, "�ٱ��ɹ���", 2500)
								.show();
					}
				}).setNegativeButton("ȡ��", null).show();
	}
}
