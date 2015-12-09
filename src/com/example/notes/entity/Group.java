package com.example.notes.entity;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class Group implements Serializable{
	private int cover;
	private String name;
	private String wishes;
	private String barcode;
	private String bgm;
	private int head;
	private List<Friend> friends;
	private String pinyin;

	public Group() {
		super();
	}
	public Group(int cover, String name,List<Friend> friends,String pinyin) {
		super();
		this.cover = cover;
		this.name = name;
		this.friends = friends;
		this.pinyin=pinyin;
	}
	public Group(int cover, String name, String wishes, String barcode,
			String bgm, int head, List<Friend> friends) {
		super();
		this.cover = cover;
		this.name = name;
		this.wishes = wishes;
		this.barcode = barcode;
		this.bgm = bgm;
		this.head = head;
		this.friends = friends;
	}

	public int getCover() {
		return cover;
	}

	public void setCover(int cover) {
		this.cover = cover;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWishes() {
		return wishes;
	}

	public void setWishes(String wishes) {
		this.wishes = wishes;
	}

	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public String getBgm() {
		return bgm;
	}

	public void setBgm(String bgm) {
		this.bgm = bgm;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public List<Friend> getFriends() {
		return friends;
	}

	public void setFriends(List<Friend> friends) {
		this.friends = friends;
	}

	public String getPinyin() {
		return pinyin;
	}

	public void setPinyin(String pinyin) {
		this.pinyin = pinyin;
	}

}
