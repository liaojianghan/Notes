package com.example.notes;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class ScanCode extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.note_write_txt);
	}
}
