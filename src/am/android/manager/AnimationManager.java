package am.android.manager;

import am.android.gpssatelliteviewer.R;
import android.app.Activity;
import android.content.Context;

public class AnimationManager
{	
	
	/******��������******/
	public static void fromLeftToRight(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.left_to_right_enter, R.anim.left_to_right_exit);
	}
	/******��������******/
	
	
	/******��������******/
	public static void fromRightToLeft(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.right_to_left_enter, R.anim.right_to_left_exit);
	}
	/******��������******/
	
	
	/******��������******/
	public static void fromUpToDown(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.top_to_bottom_enter, R.anim.top_to_bottom_exit);
	}
	/******��������******/
	
	/******��������******/
	public static void fromDownToUp(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.bottom_to_top_enter, R.anim.bottom_to_top_exit);
	}
	/******��������******/
	
	
	/******���ҽ���Ч��******/
	public static void fromSlideCross(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_right_exit);
	}
	/******���ҽ���Ч��******/
	
	
	/******��СЧ��******/
	public static void fromZoom(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
	}
	/******��СЧ��******/
	
	
			
}
