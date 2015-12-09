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

	private static ArrayList<Friend> friends;// 好友列表
	private static ArrayList<Friend> newFriends = new ArrayList<Friend>();// 排序后的新好友列表
	private static ArrayList<Group> groups = new ArrayList<Group>();// 多人笔记本的列表

	public static List<FutureLetter> futureLetterInit() {
		letters = new ArrayList<FutureLetter>();
		Images.add(img1);
		Images.add(img2);

		FutureLetter letter1 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "老钱", df.format(new Date()),
				df.format(new Date()), "未来信1", "这是未来信1", Images, "图片");
		letters.add(letter1);

		FutureLetter letter2 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "老钱2", df.format(new Date()),
				df.format(new Date()), "未来信2", "这是未来信2", Images, "图片");
		letters.add(letter2);

		FutureLetter letter3 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "老钱3", df.format(new Date()),
				df.format(new Date()), "未来信3", "这是未来信3", Images, "图片");
		letters.add(letter3);

		FutureLetter letter4 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "老钱4", df.format(new Date()),
				df.format(new Date()), "未来信4", "这是未来信4", Images, "图片");
		letters.add(letter4);

		FutureLetter letter5 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "老钱5", df.format(new Date()),
				df.format(new Date()), "未来信5", "这是未来信5", Images, "图片");
		letters.add(letter5);

		FutureLetter letter6 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "老钱6", df.format(new Date()),
				df.format(new Date()), "未来信6", "这是未来信6", Images, "图片");
		letters.add(letter6);

		FutureLetter letter7 = new FutureLetter(SortName.img1, isRead,
				SortName.img2, "老钱7", df.format(new Date()),
				df.format(new Date()), "未来信7", "这是未来信7", Images, "图片");
		letters.add(letter7);

		return letters;
	}

	public static ArrayList<Friend> friendsInit() {
		// 模拟好友数据
		newFriends.clear();
		friends = new ArrayList<Friend>();
		Friend p1 = new Friend(head1, "耿琦", "男", "Hello", "北京 东城", "2015-11-18");
		friends.add(p1);
		Friend p2 = new Friend(head2, "王宝强", "男", "不要放弃", "浙江 杭州", "2015-11-17");
		friends.add(p2);
		Friend p3 = new Friend(head1, "柳岩", "女", "美美哒", "澳大利亚", "2015-11-16");
		friends.add(p3);
		Friend p4 = new Friend(head2, "文章", "男", "卧槽", "墨尔本", "2015-11-15");
		friends.add(p4);
		Friend p5 = new Friend(head1, "马伊琍", "女", "好好吃", "北京 西城", "2015-11-14");
		friends.add(p5);
		Friend p6 = new Friend(head2, "李晨", "男", "不锻炼吗？", "湖北 荆州", "2015-10-18");
		friends.add(p6);
		Friend p7 = new Friend(head1, "张馨予", "女", "又过去了", "四川 成都", "2015-11-18");
		friends.add(p7);
		Friend p8 = new Friend(head2, "韩红", "女", "唱破嗓子了", "拉萨", "2015-11-18");
		friends.add(p8);
		Friend p9 = new Friend(head1, "韩寒", "男", "写一点生活", "江苏 苏州", "2015-11-18");
		friends.add(p9);
		Friend p10 = new Friend(head2, "丹丹", "男", "今天又", "北京 东城", "2015-11-18");
		friends.add(p10);
		Friend p11 = new Friend(head1, "丹凤眼", "男", "今天又", "北京 东城", "2015-11-18");
		friends.add(p11);
		Friend p12 = new Friend(head2, "哈哈", "男", "今天又", "北京 东城", "2015-11-18");
		friends.add(p12);
		Friend p13 = new Friend(head1, "萌萌", "男", "今天又", "北京 东城", "2015-11-18");
		friends.add(p13);
		Friend p14 = new Friend(head1, "秘密", "男", "今天又", "北京 东城", "2015-11-18");
		friends.add(p14);
		Friend p15 = new Friend(head1, "烟花", "男", "今天又", "北京 东城", "2015-11-18");
		friends.add(p15);
		Friend p16 = new Friend(head1, "眼黑", "男", "今天又", "北京 东城", "2015-11-18");
		friends.add(p16);
		Friend p17 = new Friend(head1, "许三多", "男", "今天又", "北京 东城", "2015-11-18");
		friends.add(p17);
		Friend p18 = new Friend(head1, "程咬金", "男", "今天又", "北京 东城", "2015-11-18");
		friends.add(p18);
		Friend p19 = new Friend(head1, "程哈哈", "男", "今天又", "北京 东城", "2015-11-18");
		friends.add(p19);
		Friend p20 = new Friend(head1, "哎呀", "男", "今天", "北京 东城", "2015-11-18");
		friends.add(p20);
		Friend p21 = new Friend(head1, "阿莱", "男", "今天了", "北京 东城", "2015-11-18");
		friends.add(p21);
		Friend p22 = new Friend(head1, "拜拜", "男", "what", "北京 东城", "2015-11-18");
		friends.add(p22);
		Friend p23 = new Friend(head1, "牛顿", "男", "今天", "北京 东城", "2015-11-18");
		friends.add(p23);
		Friend p24 = new Friend(head1, "拍拍熊", "男", "今天", "北京 东城", "2015-11-18");
		friends.add(p24);
		Friend p25 = new Friend(head1, "球球", "男", "今天", "北京 东城", "2015-11-18");
		friends.add(p25);
		Friend p26 = new Friend(head1, "人人", "男", "今天", "北京 东城", "2015-11-18");
		friends.add(p26);
		Friend p27 = new Friend(head1, "三三", "男", "今天去", "北京 东城", "2015-11-18");
		friends.add(p27);
		// 获取排序后的数据及拼音字母数组
		String[] allNames = SortName.sortIndex(friends);

		newFriends = SortName.sortList(allNames);
		System.out.println("newFriends的长度：" + newFriends.size());
		return newFriends;
	}

	/**
	 * 多人笔记本
	 */
	public static ArrayList<Group> groupsInit() {
		 groups.clear();
		Group group = new Group(SortName.img1, "笔记一", friends, "");
		groups.add(group);
		group = new Group(SortName.img1, "笔记二", friends, "");
		groups.add(group);
		group = new Group(SortName.img1, "笔记三", friends, "");
		groups.add(group);
		group = new Group(SortName.img1, "笔记四", friends, "");
		groups.add(group);
		return groups;
	}
}
