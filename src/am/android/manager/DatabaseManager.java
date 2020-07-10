package am.android.manager;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import am.android.consts.Const;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager
{
	
	public static void fileExist(Context context,String fileName)
	{
		File   file = new File(Const.PATH_DATABASE+"//"+fileName);
		if(!file.exists())
		{
		 try 
		 {
			file.createNewFile();
			InputStream is=context.getAssets().open(fileName);
			FileOutputStream fos=new FileOutputStream(file);
			byte[] buffer=new byte[1024];
			while(is.read(buffer)!=-1)
			{
				fos.write(buffer);
			}
			fos.close();
			is.close();	
			
		  } 
		 catch (IOException e)
		 {
			e.printStackTrace();
		 }		  
	   }	
	}
	
	
	public static void getMySettings()
	{
		SQLiteDatabase database=SQLiteDatabase.openDatabase(Const.PATH_DATABASE+"//settings2.db", null, SQLiteDatabase.OPEN_READWRITE);
		Cursor cursor=database.rawQuery("select * from settings2", null);
		
		while(cursor.moveToNext())
		{
			Const.THEME=cursor.getString(1);
			Const.SETTINGS_FREQUENCY=cursor.getString(2);	
					
		}
		
		database.close();
	}
	
	
	public static void writerDataToSettingFile(Context context,String THEME,String SETTINGS_FREQUENCY)
	{
	    fileExist(context,"settings2.db");    
	    ContentValues cv=new ContentValues();
	    cv.put("theme", THEME);
	    cv.put("frequency",SETTINGS_FREQUENCY);
	   
	    SQLiteDatabase database=SQLiteDatabase.openDatabase(Const.PATH_DATABASE+"//settings2.db", null,SQLiteDatabase.OPEN_READWRITE);
	    database.update("settings2", cv, "_id=?", new String[]{"1"});
	    database.close();  
	}	
	
}
