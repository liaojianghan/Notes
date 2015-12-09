package com.example.notes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;




import com.example.notes.photo.DisplayUtils;
import com.example.notes.photo.EmotionGvAdapter;
import com.example.notes.photo.EmotionPagerAdapter;
import com.example.notes.photo.EmotionUtils;
import com.example.notes.photo.StringUtils;




import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Note_write_txt extends Activity implements OnItemClickListener {
	private TextView cancle, address;
	private ImageView send, emj,sound;
	private EditText title, msg;
	private MediaPlayer player ;
	private Handler handler;
	private int a;
//	2015.13.8
	private LinearLayout ll_emotion_dashboard;
	private ViewPager vp_emotion_dashboard;
	private EmotionPagerAdapter emotionPagerGvAdapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.note_write_txt);
		a=0;
		sound=(ImageView) findViewById(R.id.icon_audio);
		send = (ImageView) findViewById(R.id.note_write_send);
		emj = (ImageView) findViewById(R.id.emj);
		title = (EditText) findViewById(R.id.note_write_title);
		msg = (EditText) findViewById(R.id.note_write_msg);
		address = (TextView) findViewById(R.id.note_location_address);
//	 2015.12.2
		player =new MediaPlayer();
		handler=new Handler();
		
		cancle = (TextView) findViewById(R.id.note_write_cancel);
//		2015.12.8
		ll_emotion_dashboard = (LinearLayout) findViewById(R.id.ll_emotion_dashboard);
		vp_emotion_dashboard = (ViewPager) findViewById(R.id.vp_emotion_dashboard);
		
		Intent data = getIntent(); 
	    Bundle bundle=getIntent().getExtras();
	    final String sad=bundle.getString("picturePath1");
  
//	    2015.12.2
	    if (sad!=null) {
	    	sound.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
				

		              String  path   =  sad;

		               try {
						 player.setDataSource(path);
						 player.prepare();
                      
						 a= player.getDuration();
			            
						 player.start();
			              
					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalStateException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
						
					}

		              
				handler.postDelayed(new Runnable() {
					
					@Override
					public void run() {
						// TODO Auto-generated method stub
						 
					player.reset();
					}
				}, a);
				
				}
			});
		}
	   
		cancle.setOnClickListener(new OnClickListener() {

			public void onClick(View arg0) {
				finish();
			}
		});
		emj.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				ll_emotion_dashboard.setVisibility(
						ll_emotion_dashboard.getVisibility() == View.VISIBLE ? 
								View.VISIBLE : View.GONE);
			
			 
			}
		});
		 initEmotion();
		 
		msg.setText(StringUtils.getEmotionContent(this, msg, ""));
		
	}
	private void initEmotion() {
		// 获取屏幕宽度
		int gvWidth = DisplayUtils.getScreenWidthPixels(this);
		// 表情边距
		int spacing = DisplayUtils.dp2px(this, 8);
		// GridView中item的宽度
		int itemWidth = (gvWidth - spacing * 8) / 7;
		int gvHeight = itemWidth * 3 + spacing * 4;

		List<GridView> gvs = new ArrayList<GridView>();
		List<String> emotionNames = new ArrayList<String>();
		// 遍历所有的表情名字
		for (String emojiName : EmotionUtils.emojiMap.keySet()) {
			emotionNames.add(emojiName);
			// 每20个表情作为一组,同时添加到ViewPager对应的view集合中
			if (emotionNames.size() == 20) {
				GridView gv = createEmotionGridView(emotionNames, gvWidth, spacing, itemWidth, gvHeight);
				gvs.add(gv);
				// 添加完一组表情,重新创建一个表情名字集合
				emotionNames = new ArrayList<String>();
			}
		}

		// 检查最后是否有不足20个表情的剩余情况
		if (emotionNames.size() > 0) {
			GridView gv = createEmotionGridView(emotionNames, gvWidth, spacing, itemWidth, gvHeight);
			gvs.add(gv);
		}

		// 将多个GridView添加显示到ViewPager中
		emotionPagerGvAdapter = new EmotionPagerAdapter(gvs);
		vp_emotion_dashboard.setAdapter(emotionPagerGvAdapter);
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(gvWidth, gvHeight);
		vp_emotion_dashboard.setLayoutParams(params);
        
	    
	}
	public void getLocation(View v) {
		// 定位功能 TODO
		Toast.makeText(this, "点击了定位", 3000).show();

		AlertDialog.Builder ab = new AlertDialog.Builder(this);
		ab.setMessage("是否允许获取您的位置？")
				.setPositiveButton("是", new AlertDialog.OnClickListener() {
					public void onClick(DialogInterface arg0, int arg1) {
						// 跳转到相册选择页面 TODO
						Toast.makeText(Note_write_txt.this, "定位成功！", 2500)
								.show();
					}
				}).setNegativeButton("否", null).show();

	}

	
//	2015.12.8
	private GridView createEmotionGridView(List<String> emotionNames, int gvWidth, int padding, int itemWidth, int gvHeight) {
		// 创建GridView
		GridView gv = new GridView(this);
		gv.setBackgroundColor(Color.rgb(233, 233, 233));
		gv.setSelector(android.R.color.transparent);
		gv.setNumColumns(7);
		gv.setPadding(padding, padding, padding, padding);
		gv.setHorizontalSpacing(padding);
		gv.setVerticalSpacing(padding);
		LayoutParams params = new LayoutParams(gvWidth, gvHeight);
		Toast.makeText(Note_write_txt.this,gvWidth+"" , 1000).show();
		gv.setLayoutParams(params);
		// 给GridView设置表情图片
		EmotionGvAdapter adapter = new EmotionGvAdapter(this, emotionNames, itemWidth);
		gv.setAdapter(adapter);
		gv.setOnItemClickListener(this);
//		Toast.makeText(Note_write_txt.this, "daiwofei00", 1000).show();
		return gv;
	}
//	2015.12.8
	

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		// TODO Auto-generated method stub

		Object itemAdapter = parent.getAdapter();
		
		if (itemAdapter instanceof EmotionGvAdapter) {
			// 点击的是表情
			EmotionGvAdapter emotionGvAdapter = (EmotionGvAdapter) itemAdapter;

			if (position == emotionGvAdapter.getCount() - 1) {
				// 如果点击了最后一个回退按钮,则调用删除键事件
				msg.dispatchKeyEvent(new KeyEvent(
						KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_DEL));
		        
			} else {
			
				// 如果点击了表情,则添加到输入框中
				String emotionName =(String) emotionGvAdapter.getItem(position);

				// 获取当前光标位置,在指定位置上添加表情图片文本
				int curPosition = msg.getSelectionStart();
				StringBuilder sb = new StringBuilder(msg.getText().toString());
				sb.insert(curPosition, emotionName);

				// 特殊文字处理,将表情等转换一下
				
				msg.setText(StringUtils.getEmotionContent(
						this, msg, sb.toString()));
				
				// 将光标设置到新增完表情的右侧
				msg.setSelection(curPosition + emotionName.length());
			}

		}
	
	}
}
