package am.android.manager;

import java.io.File;

import am.android.consts.Const;
import android.os.Environment;

public class FileManager
{
	
	public static boolean isSDCardExist()
	{
		boolean result = true;
		if(!Environment.getExternalStorageState().equals(android.os.Environment.MEDIA_MOUNTED))
		{
		     result=false;
		}
		return result;
	}
	
	public static boolean makeDirectory()
	{
		boolean isSDCardExist = isSDCardExist();
		
		if(isSDCardExist)
		{
		    new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"//GPSSatelliteViewer").mkdir();
	 	    new File(Environment.getExternalStorageDirectory().getAbsolutePath()+"//GPSSatelliteViewer//Databases").mkdir();
		}
		
		return isSDCardExist;
		
	}
	
	public static void makeFile()
	{
		Const.file_settings=new File(Const.PATH_DATABASE+"//settings2.db");
	}	
}
