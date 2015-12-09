package com.example.notes;

import java.util.ArrayList;
import java.util.List;

import com.example.notes.entity.Mp3;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class Group_set_bgm extends Activity {
	private EditText search;
	private ListView listview;
	private static List<Mp3> mp3List = new ArrayList<Mp3>();
	private String from;
	private ContentResolver cr;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.note_set_bgm);
		 cr = getApplication().getContentResolver();
		// 启动activity时不自动弹出软键盘
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

		from = getIntent().getStringExtra("from");
		search = (EditText) findViewById(R.id.search_music);
		listview = (ListView) findViewById(R.id.music_list);
	    mp3List.clear();
		initData();
        MyAdapter myAdapter=new MyAdapter();
		listview.setAdapter(myAdapter);
	
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				String name = mp3List.get(arg2).getName();
//				2015.12.3缓存音乐地址
				mp3List.get(arg2).getUrl();
				Intent intent = null;

				if (from.equals("Contact_GroupInfo_Edit")) {
					intent = new Intent(Group_set_bgm.this,
							Contact_GroupInfo_Edit.class);
				}
				if (from.equals("Contact_Group_New")) {
					intent = new Intent(Group_set_bgm.this,
							Contact_Group_New.class);
				}
				if (from.equals("Setting_mynote")) {
					intent = new Intent(Group_set_bgm.this,
							Setting_mynote.class);
				}
				intent.putExtra("mp3Name", name);
				System.out.println("mp3Name:" + name);
				setResult(200, intent);
				finish();// TODO

			}
		});
	}

	public Bitmap createAlbumArt(final String filePath) {
	    Bitmap bitmap = null;
	    //能够获取多媒体文件元数据的类
	    MediaMetadataRetriever retriever = new MediaMetadataRetriever();
	    try {
	        retriever.setDataSource(filePath); //设置数据源
	        byte[] embedPic = retriever.getEmbeddedPicture(); //得到字节型数据
	        bitmap = BitmapFactory.decodeByteArray(embedPic, 0, embedPic.length); //转换为图片
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            retriever.release();
	        } catch (Exception e2) {
	            e2.printStackTrace();
	        }
	    }
	    return bitmap;
	}
	// 模拟音乐数据
	public void initData() {
		if (cr == null) {
		    return;
		}
		// 获取所有歌曲
		Cursor cursor = cr.query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, 
		    null, null, null, MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
		if (null == cursor) {
		    return;
		}

		if (cursor.moveToFirst()) {
		    do {

		        String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
		        String singer = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
		        String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
		        long size = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.SIZE));
		        String url = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA));
		       int duration = cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
		      
		       int i=cursor.getInt(cursor.getColumnIndex(MediaStore.Audio.Media.IS_MUSIC)) ;
		       if (i!=0) {
		    	   Mp3 mp1=new Mp3(title,singer,url);
			        mp3List.add(mp1);
			}
		       
		       
		    } while (cursor.moveToNext());
		}

	}

	class MyAdapter extends BaseAdapter {

		LayoutInflater li;

		public MyAdapter() {
			li = getLayoutInflater();
		}

		public int getCount() {
			// TODO Auto-generated method stub
			return mp3List.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		// 获取SD卡的音乐文件并显示
		public View getView(int arg0, View arg1, ViewGroup arg2) {
			View v;
			if (arg1 == null) {
				v = li.inflate(R.layout.bgm_item, null);
			} else {
				v = arg1;
			}
			ImageView mp3_img = (ImageView) v.findViewById(R.id.mp3_pic);
			TextView mp3_name = (TextView) v.findViewById(R.id.mp3_name);
			TextView mp3_singer = (TextView) v.findViewById(R.id.mp3_singer);
			// TextView time = (TextView) v.findViewById(R.id.time);
			mp3_img.setImageResource(R.drawable.icon_mp3);
			mp3_name.setText(mp3List.get(arg0).getName());
			mp3_singer.setText(mp3List.get(arg0).getSinger());
			
			return v;
		}

	}
}
