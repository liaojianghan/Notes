package com.example.notes.photo;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

public class EmotionPagerAdapter  extends PagerAdapter{
  private List<GridView> gvs;
  public EmotionPagerAdapter(List<GridView>gvs) {
	  this.gvs=gvs;
	// TODO Auto-generated constructor stub
}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return gvs.size();
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0==arg1;
	}
	@Override
    public void destroyItem(ViewGroup container, int position, Object object) {
			// TODO Auto-generated method stub
		((ViewPager) container).removeView(gvs.get(position));
		}
@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
	((ViewPager) container).addView(gvs.get(position));;
	return gvs.get(position);
	}
}
