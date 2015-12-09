package com.example.notes.photo;

import java.util.List;

import com.example.notes.R;



import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.AbsListView.LayoutParams;

public class EmotionGvAdapter extends BaseAdapter{
	private Context context;
	private List<String> emotionNames;
	private int itemWidth;
	public EmotionGvAdapter(Context context, List<String> emotionNames, int itemWidth) {
		// TODO Auto-generated constructor stub
	this.context=context;
	this.emotionNames=emotionNames;
	this.itemWidth=itemWidth;
	
	
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return emotionNames.size()+1;
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return emotionNames.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {

		ImageView iv = new ImageView(context);
		// ����padding����,��ͼƬ��ʾ�Ŀ�������Сһ��,���ǿؼ���С�������Χ��Ȼ���ֲ���
		iv.setPadding(itemWidth/8, itemWidth/8, itemWidth/8, itemWidth/8);
		LayoutParams params = new LayoutParams(itemWidth, itemWidth);
		iv.setLayoutParams(params);
		
		// ���һ��λ����ʾɾ����ť
		if(position == getCount() - 1) {
			iv.setImageResource(R.drawable.compose_emotion_delete);
		} else {
			String emotionName = emotionNames.get(position);
			iv.setImageResource(EmotionUtils.getImgByName(emotionName));
		}
		
		return iv;
	
	}

}
