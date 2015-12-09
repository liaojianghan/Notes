package com.example.notes.entity;

public class Note {
	private int cover;
	private String type;
	private String title;
	private String noteBody;
	private String time;

	public Note(int cover, String type, String title, String noteBody,
			String time) {
		super();
		this.cover = cover;
		this.type = type;
		this.title = title;
		this.noteBody = noteBody;
		this.time = time;
	}

	public int getCover() {
		return cover;
	}

	public void setCover(int cover) {
		this.cover = cover;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getNoteBody() {
		return noteBody;
	}

	public void setNoteBody(String noteBody) {
		this.noteBody = noteBody;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
