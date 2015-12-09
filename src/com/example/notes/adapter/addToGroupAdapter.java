package com.example.notes.adapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.example.notes.R;
import com.example.notes.entity.Friend;

public class addToGroupAdapter extends BaseAdapter{
	public static HashMap<String, Integer> alphaIndex;// ��Ŵ��ڵĺ���ƴ������ĸ����֮��Ӧ���б�λ��
	public static String[] sections;// ��Ŵ��ڵĺ���ƴ������ĸ

	private List<Friend> friends = new ArrayList<Friend>();
	public static List<Friend> selected = new ArrayList<Friend>();
	private LayoutInflater inflater;
	private ViewHolder holder;
	private int index;		
	
	public addToGroupAdapter(Context context) {
		super();
		inflater = LayoutInflater.from(context);

		alphaIndex = new HashMap<String, Integer>();
		sections = new String[friends.size()];

		for (int i = 0; i < friends.size(); i++) {
			// ��ǰ����ƴ������ĸ
			String currentStr = getAlpha(friends.get(i).getPinYinName());
			// ��һ������ƴ������ĸ�����������Ϊ" "
			String previewStr = (i - 1) >= 0 ? getAlpha(friends.get(i - 1)
					.getPinYinName()) : " ";
			if (!previewStr.equals(currentStr)) {
				String name = getAlpha(friends.get(i).getPinYinName());
				alphaIndex.put(name, i);
				sections[i] = name;
			}
		}
	}

	@Override
	public int getCount() {
		return friends.size();
	}

	@Override
	public Object getItem(int position) {
		return friends.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public boolean isEnabled(int position) {
		if (friends.get(position).getName().length() == 1)// �������ĸ����
			return false;// ��ʾ���ܵ��
		return super.isEnabled(position);
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		index = position;
		holder = new ViewHolder();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.friend_list_item, null);
			holder = new ViewHolder();
			holder.alpha = (TextView) convertView.findViewById(R.id.alpha);
			holder.name = (TextView) convertView
					.findViewById(R.id.friend_list_name);
			holder.head = (ImageView) convertView
					.findViewById(R.id.friend_list_img);
			holder.choose = (CheckBox) convertView
					.findViewById(R.id.checkBox1);
			holder.right = (ImageView) convertView
					.findViewById(R.id.friend_list_right);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.name.setText(friends.get(position).getName());
		holder.head.setImageResource(R.drawable.icon_user_img2);
		holder.right.setImageResource(R.drawable.normal);
		holder.choose.setVisibility(0);
		holder.choose
				.setOnCheckedChangeListener(new OnCheckedChangeListener() {

					public void onCheckedChanged(CompoundButton arg0,
							boolean isSelected) {
						if (isSelected) {
							selected.add(friends.get(index));
						} else {
							selected.remove(friends.get(index));
						}
					}
				});

		String currentStr = getAlpha(friends.get(position).getPinYinName());
		String previewStr = (position - 1) >= 0 ? getAlpha(friends.get(
				position - 1).getPinYinName()) : " ";
		if (!previewStr.equals(currentStr)) {
			holder.alpha.setVisibility(View.VISIBLE);
			holder.alpha.setText(currentStr);
		} else {
			holder.alpha.setVisibility(View.GONE);
		}
		return convertView;
	}

	private class ViewHolder {
		private TextView alpha; // ����ĸ����
		private ImageView head;
		private TextView name;
		private CheckBox choose;
		private ImageView right;
	}

	// ��ú���ƴ������ĸ
	private String getAlpha(String str) {
		if (str == null) {
			return "#";
		}
		if (str.trim().length() == 0) {
			return "#";
		}
		char c = str.trim().substring(0, 1).charAt(0);
		// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase();
		} else {
			return "#";
		}
	}

}
