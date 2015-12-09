package com.example.notes.entity;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Friend implements Serializable {

	private int head;
	private String name;
	private String sex;
	private String sign;
	private String nickName;
	private String address;
	private String birthday;
	private String pinYinName;

	public Friend() {
		super();
	}

	public Friend(String name) {
		super();
		this.name = name;
	}

	public Friend(int head, String name) {
		super();
		this.head = head;
		this.name = name;
	}

	public Friend(String name, String pinYinName) {
		super();
		this.name = name;
		this.pinYinName = pinYinName;
	}

	public Friend(int head, String name, String sex, String sign,
			String address, String birthday) {
		super();
		this.head = head;
		this.name = name;
		this.sex = sex;
		this.sign = sign;
		this.address = address;
		this.birthday = birthday;
	}

	public Friend(int head, String name, String sex, String sign,
			String nickName, String address, String birthday, String pinYinName) {
		super();
		this.head = head;
		this.name = name;
		this.sex = sex;
		this.sign = sign;
		this.nickName = nickName;
		this.address = address;
		this.birthday = birthday;
		this.pinYinName = pinYinName;
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPinYinName() {
		return pinYinName;
	}

	public void setPinYinName(String pinYinName) {
		this.pinYinName = pinYinName;
	}

}
