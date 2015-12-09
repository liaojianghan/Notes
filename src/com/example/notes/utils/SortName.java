package com.example.notes.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

import com.example.notes.R;
import com.example.notes.entity.Friend;

public class SortName {
	public static final int img1 = R.drawable.icon_user_head;
	public static final int img2 = R.drawable.icon_user_img2;

	private static List<Friend> oldFriends = new ArrayList<Friend>();
	private static ArrayList<Friend> newFriends = new ArrayList<Friend>();

	public static String[] indexStr = { "#", "A", "B", "C", "D", "E", "F", "G",
			"H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };

	/**
	 * 获取排序后的新数据
	 * 
	 * @param friends
	 * @return
	 */
	public static String[] sortIndex(List<Friend> friends) {
		oldFriends = friends;
		TreeSet<String> set = new TreeSet<String>();
		System.out.println("好友长度："+friends.size());
		// 获取初始化数据源中的首字母，添加到set中
		for (Friend friend : friends) {
			set.add(StringHelper.getPinYinHeadChar(friend.getName()).substring(
					0, 1));
		}
		// 新数组的长度为原数据加上set的大小
		String[] names = new String[friends.size() + set.size()];

		int i = 0;
		for (String string : set) {
			names[i] = string;//
			i++;
		}
		String[] pinYinNames = new String[friends.size()];
		for (int j = 0; j < friends.size(); j++) {
			friends.get(j).setPinYinName(
					StringHelper
							.getPingYin(friends.get(j).getName().toString()));
			pinYinNames[j] = StringHelper.getPingYin(friends.get(j).getName()
					.toString());
		}
		// 将原数据拷贝到新数据中
		System.arraycopy(pinYinNames, 0, names, set.size(), pinYinNames.length);
		// 自动按照首字母排序
		Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
		return names;
	}

	/**
	 * 重新排序获得一个新的List集合
	 * @param allNames
	 */
	public static ArrayList<Friend> sortList(String[] allNames) {
		for (int i = 0; i < allNames.length; i++) {
			if (allNames[i].length() != 1) {
				for (int j = 0; j < oldFriends.size(); j++) {
					if (allNames[i].equals(oldFriends.get(j).getPinYinName())) {

						Friend f = new Friend();
						f.setHead(oldFriends.get(j).getHead());
						f.setName(oldFriends.get(j).getName());
						f.setSex(oldFriends.get(j).getSex());
						f.setSign(oldFriends.get(j).getSign());
						f.setNickName(oldFriends.get(j).getNickName());
						f.setAddress(oldFriends.get(j).getAddress());
						f.setBirthday(oldFriends.get(j).getBirthday());
						f.setPinYinName(oldFriends.get(j).getPinYinName());
						newFriends.add(f);
					}
				}
			} else {// allNames[i].length()==1   添加字母
				//newFriends.add(new Friend(allNames[i]));
			}
		}
		return newFriends;
	}

}
