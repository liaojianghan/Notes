package com.example.notes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.notes.entity.FutureLetter;
import com.example.notes.utils.InitData;
import com.example.notes.view.RoundImage;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Me_Myweilaixin extends Activity {
	private ListView list_weilaixin;
	private MyAdapter mAdapter;
	private List<FutureLetter> letters = new ArrayList<FutureLetter>();
	private RoundImage from_image;
	private ImageView read_type;
	private RoundImage xin_user_img;
	private TextView from_name;
	private TextView xin_receive_time, xin_send_time;

	private static final DateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.me_weilaixin);
		mAdapter = new MyAdapter();
		letters = InitData.futureLetterInit();

		list_weilaixin = (ListView) findViewById(R.id.list_weilaixin);
		list_weilaixin.setAdapter(mAdapter);
		list_weilaixin.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String currtime = df.format(new Date());
				String time = xin_receive_time.getText().toString()
						.substring(3);
				System.out.println(currtime + "\n时间：" + time);

				FutureLetter letter = letters.get(arg2);
				if (currtime.equals(time)) {
					Toast.makeText(Me_Myweilaixin.this, "时间正确，跳转详细页面", 2500)
					.show();
					Intent data = new Intent(Me_Myweilaixin.this,
							Me_Myweilaixin_show.class);
					Bundle b=new Bundle();
					b.putSerializable("letter", letter);
					data.putExtras(b);

					startActivity(data);
				} else {
					Toast.makeText(Me_Myweilaixin.this, "未到开启时间", 2500).show();
				}

			}
		});
	}

	// 自定义适配器
	class MyAdapter extends BaseAdapter {
		LayoutInflater li;

		public MyAdapter() {
			li = getLayoutInflater();
		}

		public int getCount() {
			return letters.size();
		}

		public Object getItem(int arg0) {
			return null;
		}

		public long getItemId(int arg0) {
			return 0;
		}

		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View v = null;
			if (arg1 == null) {
				v = li.inflate(R.layout.me_weilaixin_item, null);
			} else {
				v = arg1;
			}
			from_image = (RoundImage) v.findViewById(R.id.from_img);
			read_type = (ImageView) v.findViewById(R.id.read_type);
			xin_user_img = (RoundImage) v.findViewById(R.id.xin_user_img);
			from_name = (TextView) v.findViewById(R.id.from_name);
			xin_receive_time = (TextView) v.findViewById(R.id.xin_receive_time);
			xin_send_time = (TextView) v.findViewById(R.id.xin_send_time);

			from_image.setImageResource(letters.get(arg0).getFromHead());
			read_type.setImageResource(letters.get(arg0).getIsRead());
			xin_user_img.setImageResource(letters.get(arg0).getToHead());
			from_name.setText(letters.get(arg0).getFromName());
			xin_receive_time.setText("收信：" + letters.get(arg0).getOpenTime());
			xin_send_time.setText("发信：" + letters.get(arg0).getGetTime());

			return v;
		}
	}
}
