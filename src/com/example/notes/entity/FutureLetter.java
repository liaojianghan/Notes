package com.example.notes.entity;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class FutureLetter implements Serializable{
	private int FromHead;
	private int IsRead;
	private int ToHead;
	private String FromName;
	private String OpenTime;
	private String GetTime;
	private String letterTitle;
	private String letterBody;
	private List<MyImages> letterImgs;
	private String letterType;

	public FutureLetter() {
		super();
	}

	public FutureLetter(int fromHead, int isRead, int toHead, String fromName,
			String openTime, String getTime, String letterType) {
		super();
		this.FromHead = fromHead;
		this.IsRead = isRead;
		this.ToHead = toHead;
		this.FromName = fromName;
		this.OpenTime = openTime;
		this.GetTime = getTime;
		this.letterType = letterType;
	}

	public FutureLetter(int fromHead, String fromName, String openTime,
			String letterTitle, String letterBody, List<MyImages> letterImgs) {
		super();
		this.FromHead = fromHead;
		this.FromName = fromName;
		this.OpenTime = openTime;
		this.letterTitle = letterTitle;
		this.letterBody = letterBody;
		this.letterImgs = letterImgs;

	}

	public FutureLetter(int fromHead, int isRead, int toHead, String fromName,
			String openTime, String getTime, String letterTitle,
			String letterBody, List<MyImages> letterImgs, String letterType) {
		super();
		this.FromHead = fromHead;
		this.IsRead = isRead;
		this.ToHead = toHead;
		this.FromName = fromName;
		this.OpenTime = openTime;
		this.GetTime = getTime;
		this.letterTitle = letterTitle;
		this.letterBody = letterBody;
		this.letterImgs = letterImgs;
		this.letterType = letterType;
	}

	public int getFromHead() {
		return FromHead;
	}

	public void setFromHead(int fromHead) {
		FromHead = fromHead;
	}

	public int getIsRead() {
		return IsRead;
	}

	public void setIsRead(int isRead) {
		IsRead = isRead;
	}

	public int getToHead() {
		return ToHead;
	}

	public void setToHead(int toHead) {
		ToHead = toHead;
	}

	public String getFromName() {
		return FromName;
	}

	public void setFromName(String fromName) {
		FromName = fromName;
	}

	public String getOpenTime() {
		return OpenTime;
	}

	public void setOpenTime(String openTime) {
		OpenTime = openTime;
	}

	public String getGetTime() {
		return GetTime;
	}

	public void setGetTime(String getTime) {
		GetTime = getTime;
	}

	public String getLetterTitle() {
		return letterTitle;
	}

	public void setLetterTitle(String letterTitle) {
		this.letterTitle = letterTitle;
	}

	public String getLetterBody() {
		return letterBody;
	}

	public void setLetterBody(String letterBody) {
		this.letterBody = letterBody;
	}

	public List<MyImages> getLetterImgs() {
		return letterImgs;
	}

	public void setLetterImgs(List<MyImages> letterImgs) {
		this.letterImgs = letterImgs;
	}

	public String getLetterType() {
		return letterType;
	}

	public void setLetterType(String letterType) {
		this.letterType = letterType;
	}

}
