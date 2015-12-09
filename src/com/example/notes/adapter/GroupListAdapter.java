package com.example.notes.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.notes.R;
import com.example.notes.entity.Group;

public class GroupListAdapter extends BaseAdapter{
	private Context context;
	private LayoutInflater inflater;
	private List<Group> groups;

	public GroupListAdapter(Context context, List<Group> groups) {
		this.context = context;
		inflater = LayoutInflater.from(this.context);
		this.groups = groups;
	}

	public int getCount() {
		return groups.size();
	}

	public Object getItem(int position) {
		return position;
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = inflater.inflate(R.layout.group_list_item, null);
		ImageView img = (ImageView) convertView.findViewById(R.id.group_img);
		ImageView right = (ImageView) convertView
				.findViewById(R.id.group_right);
		TextView name = (TextView) convertView.findViewById(R.id.group_name);
		img.setImageResource(R.drawable.icon_user_img2);
		right.setImageResource(R.drawable.normal);
		name.setText(groups.get(position).getName());
		return convertView;
	}
}
