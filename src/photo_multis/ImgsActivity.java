package photo_multis;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import photo_multis.ImgsAdapter.OnItemClickClass;

import com.example.notes.R;
import com.example.notes.photo.Photo_pulish;



import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.RelativeLayout;

public class ImgsActivity extends Activity {

	Bundle bundle;
	FileTraversal fileTraversal;
	GridView imgGridView;
	ImgsAdapter imgsAdapter;

	Util util;

	HashMap<Integer, ImageView> hashImage;

	ArrayList<String> filelist;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.photogrally);
		
		imgGridView=(GridView) findViewById(R.id.gridView1);
		bundle= getIntent().getExtras();
		fileTraversal=bundle.getParcelable("data");
		imgsAdapter=new ImgsAdapter(this, fileTraversal.filecontent,onItemClickClass);
		imgGridView.setAdapter(imgsAdapter);

		hashImage=new HashMap<Integer, ImageView>();
		filelist=new ArrayList<String>();

		util=new Util(this);
	}
	
	class BottomImgIcon implements OnItemClickListener{
		
		int index;
		public BottomImgIcon(int index) {
			this.index=index;
		}
		
		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			
		}
	}
	

	
	ImgCallBack imgCallBack=new ImgCallBack() {
		@Override
		public void resultImgCall(ImageView imageView, Bitmap bitmap) {
			imageView.setImageBitmap(bitmap);
		}
	};
	
	class ImgOnclick implements OnClickListener{
		String filepath;
		CheckBox checkBox;
		public ImgOnclick(String filepath,CheckBox checkBox) {
			this.filepath=filepath;
			this.checkBox=checkBox;
		}
		@Override
		public void onClick(View arg0) {
			checkBox.setChecked(false);

			filelist.remove(filepath);
		}
	}
	
	ImgsAdapter.OnItemClickClass onItemClickClass=new OnItemClickClass() {
		@Override
		public void OnItemClick(View v, int Position, CheckBox checkBox) {
			String filapath=fileTraversal.filecontent.get(Position);
			if (checkBox.isChecked()) {
				checkBox.setChecked(false);

				filelist.remove(filapath);

			}else {
				checkBox.setChecked(true);
				Log.i("img", "img choise position->"+Position);

					filelist.add(filapath);



			}
		}
	};
	
	public void tobreak(View view){
		finish();
	}
	
	/**
	 * FIXME
	 * 亲只需要在这个方法把选中的文档目录已list的形式传过去即可
	 * @param view
	 */
	public void sendfiles(View view){
		Intent intent =new Intent(this, Photo_pulish.class);
		Bundle bundle=new Bundle();
		bundle.putStringArrayList("files", filelist);
		intent.putExtras(bundle);
		startActivity(intent);
		
	}
}
