package am.android.manager;

import am.android.gpssatelliteviewer.R;
import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class InforManager 
{
	public static void showInfor(Context context,String infor)
	{
		View view=((Activity) context).getLayoutInflater().inflate(R.layout.layout_toast, null);
		Toast toast=new Toast(context);
		toast.setView(view);
		TextView tv_info=(TextView)view.findViewById(R.id.tv_infor);
		tv_info.setText(infor);
		toast.show();
	}
	
	
	public static void showInfor(Context context,String infor,boolean isLong)
	{
		View view=((Activity) context).getLayoutInflater().inflate(R.layout.layout_toast, null);
		Toast toast=new Toast(context);
		toast.setView(view);
		TextView tv_info=(TextView)view.findViewById(R.id.tv_infor);
		tv_info.setText(infor);
		if(isLong)
		{
		  toast.setDuration(Toast.LENGTH_LONG);
		}
		else
		{
		  toast.setDuration(Toast.LENGTH_SHORT);
		}
		  toast.show();
	}	
}
