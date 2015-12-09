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

//2015-11-10 弹出的菜单
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
				// 销毁弹出框
				dismiss();
			}
		});
		// 设置按钮监听
		for (int i = 0; i < ids.length; i++) {
			RelativeLayout rel = (RelativeLayout) mMenuView
					.findViewById(ids[i]);
			rel.setOnClickListener(itemsOnClick);
		}

		// 设置SelectPicPopupWindow的View
		this.setContentView(mMenuView);
		// 设置SelectPicPopupWindow弹出窗体的宽
		this.setWidth(LayoutParams.FILL_PARENT);
		// 设置SelectPicPopupWindow弹出窗体的高
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// 设置SelectPicPopupWindow弹出窗体可点击
		this.setFocusable(true);
		// 设置SelectPicPopupWindow弹出窗体动画效果
		// this.setAnimationStyle(R.style.AnimBottom);
		// 实例化一个ColorDrawable颜色为半透明
		ColorDrawable dw = new ColorDrawable(Color.argb(0, 0, 0, 0));
		// 设置SelectPicPopupWindow弹出窗体的背景
		this.setBackgroundDrawable(dw);
		// mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
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
