package am.android.manager;

import android.R;
import android.app.Activity;
import android.content.Context;

public class AnimationManagerSystem
{
	/******fade******/
	public static void fromFade(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
	}
	/******fade******/
	
	
	/******slide******/
	public static void fromSlide(Context context)
	{
		((Activity) context).overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
	}
	/******slide******/	
		
}
