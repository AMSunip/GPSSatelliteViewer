package am.android.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import am.android.consts.Const;
import android.content.Context;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.provider.Settings;

public class GpsManager
{
	
	public static boolean isGPSEnable(Context context) 
	{
	       String str = Settings.Secure.getString(context.getContentResolver(),Settings.Secure.LOCATION_PROVIDERS_ALLOWED);   
	                   
	       if (str != null) 
	       {           
	           return str.contains("gps");     
	       }      
	       else
	       {         
	           return false;     
	       }    
	} 
	
	
	public static ArrayList<HashMap<String,Integer>> getMySatelliteListdata(ArrayList<GpsSatellite> numSatelliteList)
	{
		ArrayList<HashMap<String,Integer>> listdata_result=new ArrayList<HashMap<String,Integer>>();
		listdata_result.clear();
		for(int i=0;i<numSatelliteList.size();i++)
		{
			HashMap<String,Integer> map=new HashMap<String,Integer>();
			map.put("PointX",(int)numSatelliteList.get(i).getAzimuth());       //水平方位角    
			map.put("PointY", (int)numSatelliteList.get(i).getElevation());    //仰角       
			map.put("signal",(int)numSatelliteList.get(i).getSnr());
			map.put("prn", numSatelliteList.get(i).getPrn());
			map.put("type", 0);
			listdata_result.add(map);
		}	
		return listdata_result;
	}	
	
		
	public static void getGpsStatus(int event, GpsStatus status)
	{	 
		    if(event==GpsStatus.GPS_EVENT_SATELLITE_STATUS)
		    {
		    	int maxSatellites = status.getMaxSatellites();
		    	int count=0;
		    	Iterator<GpsSatellite> it = status.getSatellites().iterator();
		    	Const.numSatelliteList.clear();   	
		    	while((it.hasNext()&&(count<maxSatellites)))
		    	{
		    		GpsSatellite s=it.next();
		    		Const.numSatelliteList.add(s);
		    		count++;
		    	}	
		    }    
	 }
	
	
	public static String getSatDataType(Context context)
	{
		String satDataType="";
		
	    if(isGPSEnable(context))
	    {
			satDataType="移动设备";
		}
		else
		{
			satDataType="无数据源";
		}
	    
		return satDataType;
	}
		
}
