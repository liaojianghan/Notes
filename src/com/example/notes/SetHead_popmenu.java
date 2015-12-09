package com.example.notes;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.webkit.WebView.FindListener;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class SetHead_popmenu extends PopupWindow {
	// 2015-11-12
	private RelativeLayout menuItem1, menuItem2, cancle;
	private View popMenu;

	private TextView item1, item2, item3;

	public SetHead_popmenu(Activity context, OnClickListener itemsOnClick,
			String type) {
		super(context);
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		// ���˱ʼǱ���ͷ��༭������popmenu
		popMenu = inflater.inflate(R.layout.popmenu_head, null);

		item1 = (TextView) popMenu.findViewById(R.id.menuItem1);
		item2 = (TextView) popMenu.findViewById(R.id.menuItem2);
		if (type != null && type.equals("sex")) {
			item1.setText("��");
			item2.setText("Ů");
		}

		menuItem1 = (RelativeLayout) popMenu.findViewById(R.id.menu1);
		menuItem2 = (RelativeLayout) popMenu.findViewById(R.id.menu2);
		cancle = (RelativeLayout) popMenu.findViewById(R.id.menu3);
		cancle.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// ���ٵ�����
				dismiss();
			}
		});

		menuItem1.setOnClickListener(itemsOnClick);
		menuItem2.setOnClickListener(itemsOnClick);
		// ����SelectPicPopupWindow��View
		this.setContentView(popMenu);
		// ����SelectPicPopupWindow��������Ŀ�
		this.setWidth(LayoutParams.FILL_PARENT);
		// ����SelectPicPopupWindow��������ĸ�
		this.setHeight(LayoutParams.WRAP_CONTENT);
		// ����SelectPicPopupWindow��������ɵ��
		this.setFocusable(true);
		// ����SelectPicPopupWindow�������嶯��Ч��
		// this.setAnimationStyle(R.style.AnimBottom);
		// ʵ����һ��ColorDrawable��ɫΪ��͸��
		ColorDrawable cd = new ColorDrawable(0xb0000000);
		// ����SelectPicPopupWindow��������ı���
		this.getBackground().setAlpha(50);
	//	this.setBackgroundDrawable(cd);
		// mMenuView���OnTouchListener�����жϻ�ȡ����λ�������ѡ������������ٵ�����
		popMenu.setOnTouchListener(new OnTouchListener() {

			public boolean onTouch(View v, MotionEvent event) {
				int height = popMenu.findViewById(R.id.head_popmenu).getTop();
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
