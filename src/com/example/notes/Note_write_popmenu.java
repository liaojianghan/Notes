package com.example.notes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebView.FindListener;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

//2015-11-10 �����Ĳ˵�
public class Note_write_popmenu extends PopupWindow {
	private int[] ids = { R.id.note_take_photo, R.id.note_txt,
			R.id.note_weilaixin, R.id.note_short_aduio, R.id.note_from_phone };
	private View mMenuView;
	private RelativeLayout write_cancle;

	public Note_write_popmenu(Activity context, OnClickListener itemsOnClick) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		mMenuView = inflater.inflate(R.layout.popmenu_write_note, null);
		write_cancle = (RelativeLayout) mMenuView
				.findViewById(R.id.note_write_cancle);
		write_cancle.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ���ٵ�����
				dismiss();
			}
		});
		// ���ð�ť����
		for (int i = 0; i < ids.length; i++) {
			RelativeLayout rel = (RelativeLayout) mMenuView
					.findViewById(ids[i]);
			rel.setOnClickListener(itemsOnClick);
		}

		// ����SelectPicPopupWindow��View
		this.setContentView(mMenuView);
		// ����SelectPicPopupWindow��������Ŀ�
		this.setWidth(LayoutParams.FILL_PARENT);
		// ����SelectPicPopupWindow��������ĸ�
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// ����SelectPicPopupWindow��������ɵ��
		this.setFocusable(true);
		// ����SelectPicPopupWindow�������嶯��Ч��
		// this.setAnimationStyle(R.style.AnimBottom);
		// ʵ����һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable dw = new ColorDrawable(Color.argb(0, 0, 0, 0));
		// ����SelectPicPopupWindow��������ı���
		this.setBackgroundDrawable(dw);
		// mMenuView���OnTouchListener�����жϻ�ȡ����λ�������ѡ������������ٵ�����
		mMenuView.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				int height = mMenuView.findViewById(R.id.pop_menu).getTop();
				int y = (int) event.getY();
				if (event.getAction() == MotionEvent.ACTION_UP) {
					if (y < height) {
						dismiss();
					}
				}
				return true;
			}
		});
	}
}
