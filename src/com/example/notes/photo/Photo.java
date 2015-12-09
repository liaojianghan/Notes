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
			if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // ���sd�Ƿ����
				
				
			}
			new DateFormat();
			String name = DateFormat.format("yyyyMMdd_hhmmss",Calendar.getInstance(Locale.CHINA)) + ".jpg";	
//		
			Bundle bundle = data.getExtras();
			Bitmap bitmap = (Bitmap) bundle.get("data");// ��ȡ������ص����ݣ���ת��ΪBitmapͼƬ��ʽ
		    
			FileOutputStream b = null;	
			
			File file = new File("/sdcard/Image/");
			file.mkdirs();// �����ļ���
			
			fileName = "/sdcard/Image/"+name;
		
		    pString=fileName;

			try {
				
				b = new FileOutputStream(fileName);
				
				
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// ������д���ļ�
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

