package com.example.notes.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.example.notes.R;
import com.example.notes.entity.Friend;
import com.example.notes.entity.FutureLetter;
import com.example.notes.entity.Group;
import com.example.notes.entity.MyImages;

public class InitData {

	private static List<FutureLetter> letters = null;
	private static int isRead = R.drawable.icon_weidu;
	private static final DateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");
	private static final MyImages img1 = new MyImages(SortName.img1);
	private static final MyImages img2 = new MyImages(SortName.img1);
	private static List<MyImages> Images = new ArrayList<MyImages>();

	public static final int head1 = R.drawable.icon_user_head;
	public static final int head2 = R.drawable.icon_user_img2;

	private static ArrayList<Friend> friends;// �����б�
	private static ArrayList<Friend> newFriends = new ArrayList<Friend>();// �������º����б�
	private static ArrayList<Group> groups = new ArrayList<Group>();// ���˱ʼǱ����б�

	public static List<FutureLetter> futureLetterInit() {
		letters = new ArrayList<FutureLetter>();
		Images.add(img1);
		Images.add(img2);

		FutureLetter letter1 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "��Ǯ", df.format(new Date()),
				df.format(new Date()), "δ����1", "����δ����1", Images, "ͼƬ");
		letters.add(letter1);

		FutureLetter letter2 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "��Ǯ2", df.format(new Date()),
				df.format(new Date()), "δ����2", "����δ����2", Images, "ͼƬ");
		letters.add(letter2);

		FutureLetter letter3 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "��Ǯ3", df.format(new Date()),
				df.format(new Date()), "δ����3", "����δ����3", Images, "ͼƬ");
		letters.add(letter3);

		FutureLetter letter4 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "��Ǯ4", df.format(new Date()),
				df.format(new Date()), "δ����4", "����δ����4", Images, "ͼƬ");
		letters.add(letter4);

		FutureLetter letter5 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "��Ǯ5", df.format(new Date()),
				df.format(new Date()), "δ����5", "����δ����5", Images, "ͼƬ");
		letters.add(letter5);

		FutureLetter letter6 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "��Ǯ6", df.format(new Date()),
				df.format(new Date()), "δ����6", "����δ����6", Images, "ͼƬ");
		letters.add(letter6);

		FutureLetter letter7 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "��Ǯ7", df.format(new Date()),
				df.format(new Date()), "δ����7", "����δ����7", Images, "ͼƬ");
		letters.add(letter7);

		return letters;
	}

	public static ArrayList<Friend> friendsInit() {
		// ģ���������
		newFriends.clear();
		friends = new ArrayList<Friend>();
		Friend p1 = new Friend(head1, "����", "��", "Hello", "���� ����", "2015-11-18");
		friends.add(p1);
		Friend p2 = new Friend(head2, "����ǿ", "��", "��Ҫ����", "�㽭 ����", "2015-11-17");
		friends.add(p2);
		Friend p3 = new Friend(head1, "����", "Ů", "������", "�Ĵ�����", "2015-11-16");
		friends.add(p3);
		Friend p4 = new Friend(head2, "����", "��", "�Բ�", "ī����", "2015-11-15");
		friends.add(p4);
		Friend p5 = new Friend(head1, "�����P", "Ů", "�úó�", "���� ����", "2015-11-14");
		friends.add(p5);
		Friend p6 = new Friend(head2, "�", "��", "��������", "���� ����", "2015-10-18");
		friends.add(p6);
		Friend p7 = new Friend(head1, "��ܰ��", "Ů", "�ֹ�ȥ��", "�Ĵ� �ɶ�", "2015-11-18");
		friends.add(p7);
		Friend p8 = new Friend(head2, "����", "Ů", "����ɤ����", "����", "2015-11-18");
		friends.add(p8);
		Friend p9 = new Friend(head1, "����", "��", "дһ������", "���� ����", "2015-11-18");
		friends.add(p9);
		Friend p10 = new Friend(head2, "����", "��", "������", "���� ����", "2015-11-18");
		friends.add(p10);
		Friend p11 = new Friend(head1, "������", "��", "������", "���� ����", "2015-11-18");
		friends.add(p11);
		Friend p12 = new Friend(head2, "����", "��", "������", "���� ����", "2015-11-18");
		friends.add(p12);
		Friend p13 = new Friend(head1, "����", "��", "������", "���� ����", "2015-11-18");
		friends.add(p13);
		Friend p14 = new Friend(head1, "����", "��", "������", "���� ����", "2015-11-18");
		friends.add(p14);
		Friend p15 = new Friend(head1, "�̻�", "��", "������", "���� ����", "2015-11-18");
		friends.add(p15);
		Friend p16 = new Friend(head1, "�ۺ�", "��", "������", "���� ����", "2015-11-18");
		friends.add(p16);
		Friend p17 = new Friend(head1, "������", "��", "������", "���� ����", "2015-11-18");
		friends.add(p17);
		Friend p18 = new Friend(head1, "��ҧ��", "��", "������", "���� ����", "2015-11-18");
		friends.add(p18);
		Friend p19 = new Friend(head1, "�̹���", "��", "������", "���� ����", "2015-11-18");
		friends.add(p19);
		Friend p20 = new Friend(head1, "��ѽ", "��", "����", "���� ����", "2015-11-18");
		friends.add(p20);
		Friend p21 = new Friend(head1, "����", "��", "������", "���� ����", "2015-11-18");
		friends.add(p21);
		Friend p22 = new Friend(head1, "�ݰ�", "��", "what", "���� ����", "2015-11-18");
		friends.add(p22);
		Friend p23 = new Friend(head1, "ţ��", "��", "����", "���� ����", "2015-11-18");
		friends.add(p23);
		Friend p24 = new Friend(head1, "������", "��", "����", "���� ����", "2015-11-18");
		friends.add(p24);
		Friend p25 = new Friend(head1, "����", "��", "����", "���� ����", "2015-11-18");
		friends.add(p25);
		Friend p26 = new Friend(head1, "����", "��", "����", "���� ����", "2015-11-18");
		friends.add(p26);
		Friend p27 = new Friend(head1, "����", "��", "����ȥ", "���� ����", "2015-11-18");
		friends.add(p27);
		// ��ȡ���������ݼ�ƴ����ĸ����
		String[] allNames = SortName.sortIndex(friends);

		newFriends = SortName.sortList(allNames);
		System.out.println("newFriends�ĳ��ȣ�" + newFriends.size());
		return newFriends;
	}

	/**
	 * ���˱ʼǱ�
	 */
	public static ArrayList<Group> groupsInit() {
		 groups.clear();
		Group group = new Group(SortName.img1, "�ʼ�һ", friends, "");
		groups.add(group);
		group = new Group(SortName.img1, "�ʼǶ�", friends, "");
		groups.add(group);
		group = new Group(SortName.img1, "�ʼ���", friends, "");
		groups.add(group);
		group = new Group(SortName.img1, "�ʼ���", friends, "");
		groups.add(group);
		return groups;
	}
}
