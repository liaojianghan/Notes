package com.example.notes.adapter;

import java.util.Date;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.notes.Main;
import com.example.notes.R;
import com.example.notes.view.RoundImage;

public class NotesListAdapter extends BaseAdapter {
	private Context context;
	private ViewHolder vh;

	public NotesListAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO 获取listview中要放入的集合长度
		return 15;
	}

	@Override
	public Object getItem(int arg0) {
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		return 0;
	}

	@Override
	public View getView(int arg0, View arg1, ViewGroup arg2) {
		View v;// TODO 修改listview的高度
		vh = new ViewHolder();
		if (arg1 == null) {
			v = LayoutInflater.from(context).inflate(R.layout.note_list_item,
					null);
		} else {
			v = arg1;
		}
		vh.user_img = (RoundImage) v.findViewById(R.id.user_img);
		vh.note_type=(ImageView) v.findViewById(R.id.note_type_icon);
		vh.user_name = (TextView) v.findViewById(R.id.user_name);
		vh.user_title = (TextView) v.findViewById(R.id.user_title);
		vh.time = (TextView) v.findViewById(R.id.time);
		vh.user_img.setImageResource(R.drawable.icon_user_img2);
		vh.note_type.setImageResource(R.drawable.icon_duoren);
		vh.user_name.setText("用户");
		vh.user_title.setText("标题");
		vh.time.setText(Main.df.format(new Date()) + "");
		return v;
	}

	private class ViewHolder {
		private RoundImage user_img;
		private ImageView note_type;
		private TextView user_name;
		private TextView user_title;
		private TextView time;
	}
}
