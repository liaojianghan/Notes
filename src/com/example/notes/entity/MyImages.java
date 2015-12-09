package com.example.notes.entity;

import java.io.Serializable;

import com.example.notes.R;

@SuppressWarnings("serial")
public class MyImages implements Serializable{
	public static final int[] imgs_pressed = { R.drawable.icon_note_pressed,
			R.drawable.icon_faxian_pressed, R.drawable.icon_contact_pressed,
			R.drawable.icon_me_pressed };
	public static final int[] imgs_normal = { R.drawable.icon_note_normal,
			R.drawable.icon_faxian_normal, R.drawable.icon_contact_normal,
			R.drawable.icon_me_normal };

	private int id;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MyImages(int id) {
		super();
		this.id = id;
	}

}
