package am.android.adapter;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyViewPagerAdapter extends PagerAdapter
{

	private ArrayList<View>   listdata_listViews = null;
	private ArrayList<String> listdata_titleList = null;
	
	public MyViewPagerAdapter(ArrayList<View> listdata_listViews)
	{
		this.listdata_listViews=listdata_listViews;
	}
	
	public MyViewPagerAdapter(ArrayList<View> listdata_listViews,ArrayList<String> listdata_titleList)
	{
		this.listdata_listViews=listdata_listViews;
		this.listdata_titleList=listdata_titleList;
	}
	
	@Override
	public void destroyItem(ViewGroup container, int position, Object object)
	{
		container.removeView(listdata_listViews.get(position));
	}
	
	 @Override  
     public Object instantiateItem(ViewGroup container, int position) 
	 {
		container.addView(listdata_listViews.get(position), 0);
		return listdata_listViews.get(position); 
	 }
	
	@Override
	public int getCount()
	{	
		return listdata_listViews.size();
	}
	
	 @Override  
     public int getItemPosition(Object object)
	 {  
        return super.getItemPosition(object);  
     } 
	 
	 @Override  
     public CharSequence getPageTitle(int position)
	 {  
         return listdata_titleList.get(position);
     }  

	@Override
	public boolean isViewFromObject(View v, Object object)
	{
		return v==object;
	}

}
