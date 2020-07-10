package am.android.manager;

import am.android.gpssatelliteviewer.R;
import android.app.Activity;
import android.content.Context;

public class AnimationManager
{	
	
	/******从左往右******/
	public static void fromLeftToRight(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.left_to_right_enter, R.anim.left_to_right_exit);
	}
	/******从左往右******/
	
	
	/******从右往左******/
	public static void fromRightToLeft(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.right_to_left_enter, R.anim.right_to_left_exit);
	}
	/******从右往左******/
	
	
	/******从上往下******/
	public static void fromUpToDown(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.top_to_bottom_enter, R.anim.top_to_bottom_exit);
	}
	/******从上往下******/
	
	/******从下往上******/
	public static void fromDownToUp(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.bottom_to_top_enter, R.anim.bottom_to_top_exit);
	}
	/******从下往上******/
	
	
	/******左右交错效果******/
	public static void fromSlideCross(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.slide_left_enter,R.anim.slide_right_exit);
	}
	/******左右交错效果******/
	
	
	/******缩小效果******/
	public static void fromZoom(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.zoom_enter,R.anim.zoom_exit);
	}
	/******缩小效果******/
	
	
			
}
