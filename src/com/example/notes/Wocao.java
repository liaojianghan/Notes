package com.example.notes;

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class Wocao extends Fragment{
	TextView textView;
@Override
public View onCreateView(LayoutInflater inflater, ViewGroup container,
		Bundle savedInstanceState) {
	// TODO Auto-generated method stub
	View view=inflater.inflate(R.layout.about, container, false);
	textView=(TextView) view.findViewById(R.id.about_company);

	
	textView.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			Toast.makeText(getActivity(), "nihao", 1000).show();
		    ThumbnailUtils.extractThumbnail(null, 50, 50);
		 
	        
		}
	});
	
	return super.onCreateView(inflater, container, savedInstanceState);
}
}
