package com.example.notes.photo;

import java.io.File;

import android.content.Intent;

public class Photos {
	
public void name(Intent intent) {
	
	
	intent.addCategory(Intent.CATEGORY_OPENABLE);
	intent.setType("image/*");
	intent.putExtra("crop", "true");

	intent.putExtra("return-data", true);
    File file=new File("image/*");
    
	

	
}


	
}
