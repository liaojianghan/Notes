package com.example.notes.entity;

public class Mp3 {
	private int image;
	private String name;
	private String singer;
//	2015.12.3
	private String Url;
    public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}


	public int getImage() {
		return image;
	}

	public void setImage(int image) {
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSinger() {
		return singer;
	}

	public void setSinger(String singer) {
		this.singer = singer;
	}

	public Mp3(String name, String singer,String Url) {
		super();
		this.name = name;
		this.singer = singer;
		this.Url=Url;
	}


}
