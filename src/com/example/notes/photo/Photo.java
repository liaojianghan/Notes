package com.example.notes.photo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;



import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

public class Photo {
	public String pString=null;
    public String  photoing(int requestCode, int resultCode, Intent data ,String fileName) {

	 if (resultCode == Activity.RESULT_OK) {
			String sdStatus = Environment.getExternalStorageState();
			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
				
				
			}
			new DateFormat();
			String name = DateFormat.format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";	
//		
			Bundle bundle = data.getExtras();
			Bitmap bitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式
		    
			FileOutputStream b = null;	
			
			File file = new File("/sdcard/Image/");
			file.mkdirs();// 创建文件夹
			
			fileName = "/sdcard/Image/"+name;
		
		    pString=fileName;

			try {
				
				b = new FileOutputStream(fileName);
				
				
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} finally {
				try {
					b.flush();
					b.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			
		}
		
		return fileName;
		
	}
	
}

