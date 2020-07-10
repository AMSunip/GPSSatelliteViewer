package am.android.consts;

import java.io.File;
import java.util.ArrayList;

import am.android.view.DrawSatelliteView;
import am.android.view.DrawSignalView;
import android.graphics.Color;
import android.location.GpsSatellite;
import android.os.Environment;

public class Const
{
    
	public static ArrayList<GpsSatellite> numSatelliteList   = new ArrayList<GpsSatellite>();
    
    public static DrawSatelliteView       drawsatelliteview  = null;
    public static DrawSignalView          drawsignalview     = null;
    
    public static File                    file_settings      = new File(Const.PATH_DATABASE+"//settings2.db");
    
    public static String                  PATH_DATABASE      = Environment.getExternalStorageDirectory().getAbsolutePath()+"//GPSSatelliteViewer//Databases";
    public static String                  SETTINGS_FREQUENCY = "3";
    public static String                  THEME              = "0";
    
    public static int                     colorG             = Color.rgb(0, 255, 0);
    public static int                     count_satellite    = 0;
	public static int[]                   color              = new int[]{Color.RED,Color.rgb(255, 128, 0),Color.rgb(255, 255,0),Color.rgb(217, 255,0),colorG,colorG,colorG,colorG,colorG,colorG,colorG};
	   
}
