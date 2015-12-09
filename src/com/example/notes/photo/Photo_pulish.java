package com.example.notes.photo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.net.URI;
import java.util.ArrayList;

import com.example.notes.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Photo_pulish extends Activity{
	FileOutputStream photo_bitmap;
	ImageView imageView;
	Bitmap bitmap;
    TextView textView;

	Bundle bundle;
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.photo_publish);
		imageView=(ImageView) findViewById(R.id.photo_publish_one);
		textView=(TextView) findViewById(R.id.textView1);
        //2015.12.3多张相片地址传递到这里
		Intent data = getIntent(); 
	    Bundle bundle=getIntent().getExtras();
	    ArrayList<String> listfile=new ArrayList<String>();
	    listfile= bundle.getStringArrayList("files");
	  

		
		if (listfile!=null) {
//			相册多张在这个listview里面
			if (bundle.getStringArrayList("files")!=null) {
				
				Toast.makeText(Photo_pulish.this, listfile.toString(), 1000).show();
				textView.setText(listfile.toString());
				ArrayAdapter<String> arryAdapter=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listfile);
			
				
		}else {
//			相机一张传到这里
			imageView.setImageBitmap((Bitmap) data.getExtras().getParcelable("data"));
		}

	   


        
  
	}
		
	}
}
