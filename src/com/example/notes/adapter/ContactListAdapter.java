package com.example.notes.adapter;

import java.util.HashMap;
import java.util.List;
import java.util.regex.Pattern;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

import com.example.notes.Contact_Friend_Search;
import com.example.notes.Contact_Group_Add;
import com.example.notes.Contact_Group_Edit;
import com.example.notes.R;
import com.example.notes.entity.Friend;
import com.example.notes.entity.Group;

public class ContactListAdapter extends BaseAdapter {
	public static HashMap<String, Integer> alphaIndexer;// 存放存在的汉语拼音首字母和与之对应的列表位置
	public static String[] sections;// 存放存在的汉语拼音首字母

	private Context context;
	private LayoutInflater inflater;
	private List<Friend> list;
	private List<Group> groups;
	final int VIEW_TYPE = 3;

	public ContactListAdapter(Context context, List<Friend> list) {
		this.list = list;
		this.context = context;
	}

	public ContactListAdapter(Context context, List<Friend> list,
			List<Group> groups) {
		this.inflater = LayoutInflater.from(context);
		this.list = list;
		this.context = context;
		this.groups = groups;

		alphaIndexer = new HashMap<String, Integer>();
		sections = new String[list.size()];

		for (int i = 0; i < list.size(); i++) {
			// 当前汉语拼音首字母
			String currentStr = getAlpha(list.get(i).getPinYinName());
			// 上一个汉语拼音首字母，如果不存在为" "
			String previewStr = (i - 1) >= 0 ? getAlpha(list.get(i - 1)
					.getPinYinName()) : " ";
			if (!previewStr.equals(currentStr)) {
				String name = getAlpha(list.get(i).getPinYinName());
				alphaIndexer.put(name, i);
				sections[i] = name;
			}
		}
	}

	@Override
	public int getViewTypeCount() {
		return VIEW_TYPE;
	}

	@Override
	public int getItemViewType(int position) {
		return position < 2 ? position : 2;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	ViewHolder holder;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final TextView city;
		int viewType = getItemViewType(position);
		// if (viewType == 0) { // 定位
		// convertView = inflater.inflate(R.layout.frist_list_item, null);
		// TextView locateHint = (TextView) convertView
		// .findViewById(R.id.locateHint);
		// city = (TextView) convertView.findViewById(R.id.lng_city);
		// city.setOnClickListener(new OnClickListener() {
		// @Override
		// public void onClick(View v) {
		// if (locateProcess == 2) {
		//
		// Toast.makeText(getApplicationContext(),
		// city.getText().toString(),
		// Toast.LENGTH_SHORT).show();
		// } else if (locateProcess == 3) {
		// locateProcess = 1;
		// personList.setAdapter(adapter);
		// adapter.notifyDataSetChanged();
		// mLocationClient.stop();
		// isNeedFresh = true;
		// InitLocation();
		// currentCity = "";
		// mLocationClient.start();
		// }
		// }
		// });
		// ProgressBar pbLocate = (ProgressBar) convertView
		// .findViewById(R.id.pbLocate);
		// if (locateProcess == 1) { // 正在定位
		// locateHint.setText("正在定位");
		// city.setVisibility(View.GONE);
		// pbLocate.setVisibility(View.VISIBLE);
		// } else if (locateProcess == 2) { // 定位成功
		// locateHint.setText("当前定位城市");
		// city.setVisibility(View.VISIBLE);
		// city.setText(currentCity);
		// mLocationClient.stop();
		// pbLocate.setVisibility(View.GONE);
		// } else if (locateProcess == 3) {
		// locateHint.setText("未定位到城市,请选择");
		// city.setVisibility(View.VISIBLE);
		// city.setText("重新选择");
		// pbLocate.setVisibility(View.GONE);
		// }
		// }
		if (viewType == 0) {// 第一个条目--多人笔记本
			convertView = inflater.inflate(R.layout.group_list, null);
			final View v = convertView;
			holder = new ViewHolder();
			holder.right = (ImageView) convertView.findViewById(R.id.add_group);
			holder.right.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View arg0) {
					// 跳转到添加多人笔记本页面
					Intent intent = new Intent();
					intent.setClass(context, Contact_Group_Add.class);
					v.getContext().startActivity(intent);
				}
			});
			GridView group = (GridView) convertView.findViewById(R.id.groups);
			group.setAdapter(new GroupListAdapter(context, this.groups));
			group.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					// 跳转到多人笔记本编辑页面
					Intent intent = new Intent();
					intent.setClass(context, Contact_Group_Edit.class);
					v.getContext().startActivity(intent);
				}
			});

		} else if (viewType == 1) {// 我的好友--标题
			convertView = inflater.inflate(R.layout.friend_list_title, null);
			final View v = convertView;
			holder = new ViewHolder();
			holder.right = (ImageView) convertView
					.findViewById(R.id.add_friend);
			holder.right.setOnClickListener(new OnClickListener() {

				public void onClick(View arg0) {
					// 跳转到好友搜索页面
					Intent intent = new Intent();
					intent.setClass(context, Contact_Friend_Search.class);
					v.getContext().startActivity(intent);
				}
			});

		} else {// viewType==2 好友列表
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.friend_list_item, null);
				holder = new ViewHolder();
				holder.alpha = (TextView) convertView.findViewById(R.id.alpha);
				holder.name = (TextView) convertView
						.findViewById(R.id.friend_list_name);
				holder.head = (ImageView) convertView
						.findViewById(R.id.friend_list_img);
				holder.right = (ImageView) convertView
						.findViewById(R.id.friend_list_right);
				convertView.setTag(holder);
			} else {
				holder = (ViewHolder) convertView.getTag();
			}
			if (position >= 1) {
//				System.out.println("position:" + position);
				holder.name.setText(list.get(position).getName());
				holder.head.setImageResource(R.drawable.icon_user_img2);
				holder.right.setImageResource(R.drawable.normal);

				String currentStr = getAlpha(list.get(position).getPinYinName());
				String previewStr = (position - 1) >= 0 ? getAlpha(list.get(
						position - 1).getPinYinName()) : " ";
				if (!previewStr.equals(currentStr)) {
					holder.alpha.setVisibility(View.VISIBLE);
					holder.alpha.setText(currentStr);
				} else {
					holder.alpha.setVisibility(View.GONE);
				}
			}
		}
		return convertView;
	}

	private class ViewHolder {
		TextView alpha; // 首字母标题
		TextView name; // 城市名字
		ImageView head;
		ImageView right;
	}

	// 获得汉语拼音首字母
	private String getAlpha(String str) {
		if (str == null) {
			return "#";
		}
		if (str.trim().length() == 0) {
			return "#";
		}
		char c = str.trim().substring(0, 1).charAt(0);
		// 正则表达式，判断首字母是否是英文字母
		Pattern pattern = Pattern.compile("^[A-Za-z]+$");
		if (pattern.matcher(c + "").matches()) {
			return (c + "").toUpperCase();
		} else {
			return "#";
		}
	}

}
