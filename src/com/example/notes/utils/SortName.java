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
	 * ��ȡ������������
	 * 
	 * @param friends
	 * @return
	 */
	public static String[] sortIndex(List<Friend> friends) {
		oldFriends = friends;
		TreeSet<String> set = new TreeSet<String>();
		System.out.println("���ѳ��ȣ�"+friends.size());
		// ��ȡ��ʼ������Դ�е�����ĸ����ӵ�set��
		for (Friend friend : friends) {
			set.add(StringHelper.getPinYinHeadChar(friend.getName()).substring(
					0, 1));
		}
		// ������ĳ���Ϊԭ���ݼ���set�Ĵ�С
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
		// ��ԭ���ݿ�������������
		System.arraycopy(pinYinNames, 0, names, set.size(), pinYinNames.length);
		// �Զ���������ĸ����
		Arrays.sort(names, String.CASE_INSENSITIVE_ORDER);
		return names;
	}

	/**
	 * ����������һ���µ�List����
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
			} else {// allNames[i].length()==1   �����ĸ
				//newFriends.add(new Friend(allNames[i]));
			}
		}
		return newFriends;
	}

}
