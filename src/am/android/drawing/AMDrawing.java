package am.android.drawing;

import java.util.ArrayList;
import java.util.HashMap;

import am.android.consts.Const;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Paint.Align;

public class AMDrawing
{
	
	public static void drawMyCircle(Canvas canvas,int x,int y,int r)
	{
		Paint paint=new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
	    paint.setStyle(Paint.Style.STROKE);
		PathEffect effect=new DashPathEffect(new float[]{5,5,5,5},1);
		paint.setPathEffect(effect);
	    canvas.drawCircle(x, y, r, paint);
	}
	
	
    public static void drawMyCircleOutline(Canvas canvas,int left,int top,int right,int bottom)
    {
		Paint paint=new Paint();
	    paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStrokeWidth(3);
		paint.setStyle(Paint.Style.STROKE);
		RectF rectf=new RectF();
	    rectf.set(left, top, right, bottom);
		canvas.drawRoundRect(rectf, 20, 20, paint);
	}
    
    
    public static void drawMyColor(Canvas canvas,int color,int x,int y,int width,int height)
    {
	    Paint paint=new Paint();
	    paint.setAntiAlias(true);
	    paint.setColor(color);
	    Rect rect=new Rect();
	    rect.set(x, y,x+width,y+height);
	    canvas.drawRect(rect, paint);
    }
    
    
    public static void drawMyLine(Canvas canvas,int color,Paint.Style style,int x1,int y1,int x2,int y2)
   	{
   	    Paint paint=new Paint();
   	    paint.setAntiAlias(true);
   		paint.setColor(color);
   		paint.setStrokeWidth(1);
   		paint.setStyle(style);
   		Path path=new Path();
   		path.moveTo(x1, y1);
   		path.lineTo(x2, y2);
   		PathEffect effect=new DashPathEffect(new float[]{5,5,5,5},1);
   		paint.setPathEffect(effect);
   		canvas.drawPath(path, paint);	
    }
    
    
    public static void drawMyPrn(Canvas canvas,int color,String text,int width,int textsize,int x,int y)
    {
	    Paint paint=new Paint();
	    paint.setAntiAlias(true);
	    paint.setColor(color);
	    paint.setTextAlign(Align.CENTER);
	    paint.setTextSize(textsize);
	    canvas.drawText(text, x, y-width/60, paint);
    }
      
    
    public static void drawMyRectangle(Canvas canvas,int left,int top,int right,int bottom)
    {
	   Paint paint=new Paint();
	   paint.setAntiAlias(true);
	   paint.setColor(Color.WHITE);
	   paint.setStrokeWidth(3);
	   paint.setStyle(Paint.Style.STROKE);
	   canvas.drawRect(left-3, top-3, right+3, bottom+3, paint);
    }
    
    
    public static void drawMyRectangleLevelA(Canvas canvas,int width,ArrayList<HashMap<String,Integer>> listdata_satellite)
    {
	   int widthA=width/12;
	   for(int i=0;i<listdata_satellite.size();i++)
	   {
		    drawMyRectangleOne(canvas,width,listdata_satellite.get(i).get("signal").intValue(),width/12+i*widthA+width/96,
		    		           (140-listdata_satellite.get(i).get("signal").intValue())*width/120,width/12+(i+1)*widthA-width/96,7*width/6);
		    
		    drawMySatellite(canvas,width,width/8+i*widthA,(138-listdata_satellite.get(i).get("signal").intValue())*width/120,
		                              listdata_satellite.get(i).get("signal").intValue(),
		                              listdata_satellite.get(i).get("type").intValue(),
		                              listdata_satellite.get(i).get("prn").intValue());    
	   }   
    }
    
    
    public static void drawMyRectangleLevelB(Canvas canvas,int width,ArrayList<HashMap<String,Integer>> listdata_satellite)
    {
	   int widthB=5*width/96;
	   
	   if((width/240)%2==0)
	   {
	   
	       for(int i=0;i<listdata_satellite.size();i++)
	       {
		    drawMyRectangleOne(canvas, width,listdata_satellite.get(i).get("signal").intValue(),i*widthB+9*width/96,
		    		           (140-listdata_satellite.get(i).get("signal").intValue())*width/120,
		    		           width/12+(i+1)*widthB-width/96,7*width/6);
		    
		    drawMySatellite(canvas,width,7*width/64+i*widthB,(138-listdata_satellite.get(i).get("signal").intValue())*width/120,
		                    listdata_satellite.get(i).get("signal").intValue(),
		                    listdata_satellite.get(i).get("type").intValue(),
		                    listdata_satellite.get(i).get("prn").intValue()); 
	       }	
	   }  
	   else
	   {
		   for(int i=0;i<listdata_satellite.size();i++)
	       {
		    drawMyRectangleOne(canvas, width,listdata_satellite.get(i).get("signal").intValue(),i*widthB+9*width/96+(i+1)/2,
		    		           (140-listdata_satellite.get(i).get("signal").intValue())*width/120,
		    		           width/12+(i+1)*widthB-width/96+(i+1)/2,7*width/6);
		    
		    drawMySatellite(canvas,width,7*width/64+i*widthB+(i+1)/2,(138-listdata_satellite.get(i).get("signal").intValue())*width/120,
		                    listdata_satellite.get(i).get("signal").intValue(),
		                    listdata_satellite.get(i).get("type").intValue(),
		                    listdata_satellite.get(i).get("prn").intValue()); 
	       }	
	   }
    }
    
    
    public static void drawMyRectangleLevelC(Canvas canvas,int width,ArrayList<HashMap<String,Integer>> listdata_satellite)
    {
	   int widthC=width/24;
	   for(int i=0;i<listdata_satellite.size();i++)
	   {
		    drawMyRectangleOne(canvas,width,listdata_satellite.get(i).get("signal").intValue(),width/12+i*widthC+width/96,
		    		           (140-listdata_satellite.get(i).get("signal").intValue())*width/120,width/12+(i+1)*widthC-width/96,7*width/6);
		    
		    drawMySatellite(canvas,width,5*width/48+i*widthC,(138-listdata_satellite.get(i).get("signal").intValue())*width/120,
		                              listdata_satellite.get(i).get("signal").intValue(),
		                              listdata_satellite.get(i).get("type").intValue(),
		                              listdata_satellite.get(i).get("prn").intValue());    
	   }   
    }
    
    
    public static void drawMyRectangleLevelD(Canvas canvas,int width,ArrayList<HashMap<String,Integer>> listdata_satellite)
    {
	   int widthD=width/30;
	   for(int i=0;i<listdata_satellite.size();i++)
	   {
		    drawMyRectangleOne(canvas,
		    		           width,
		    		           listdata_satellite.get(i).get("signal").intValue(),
		    		           width/12+i*widthD+width/96,
		    		           (140-listdata_satellite.get(i).get("signal").intValue())*width/120,
		    		           width/12+(i+1)*widthD-width/96,7*width/6);
		    
		    drawMySatellite(canvas,width,5*width/48+i*widthD,(138-listdata_satellite.get(i).get("signal").intValue())*width/120,
		                              listdata_satellite.get(i).get("signal").intValue(),
		                              listdata_satellite.get(i).get("type").intValue(),
		                              listdata_satellite.get(i).get("prn").intValue());    
	   }   
    }
    
    
    public static void drawMyRectangleLevelE(Canvas canvas,int width,ArrayList<HashMap<String,Integer>> listdata_satellite)
    {
	   int widthE=width/40;
	   for(int i=0;i<listdata_satellite.size();i++)
	   {
		    drawMyRectangleOne(canvas,
		    		           width,
		    		           listdata_satellite.get(i).get("signal").intValue(),
		    		           width/12+i*widthE+width/96,
		    		           (140-listdata_satellite.get(i).get("signal").intValue())*width/120,
		    		           width/12+(i+1)*widthE-width/96,7*width/6);
		    
		    drawMySatellite(canvas,width,5*width/48+i*widthE,(138-listdata_satellite.get(i).get("signal").intValue())*width/120,
		                              listdata_satellite.get(i).get("signal").intValue(),
		                              listdata_satellite.get(i).get("type").intValue(),
		                              listdata_satellite.get(i).get("prn").intValue());    
	   }   
    }
    
    
    public static void drawMyRectangleOne(Canvas canvas,int width,int signal,int left,int top,int right,int bottom)
    {
       Paint paint=new Paint();
   	   paint.setAntiAlias(true);
   	   paint.setColor(Const.color[signal/10]); 
   	   canvas.drawRect(left, top, right, bottom, paint);
    }
    
    
    public static void drawMySatellite(Canvas canvas,int width,int x,int y,int signal,int type,int prn)
    {
    	if(type==1)
    	{
    		drawASatellite(canvas,width,x,y,signal,"1",prn);	
    	}
    	
    	//»­±±¶·1ÎÀÐÇ
    	else if(type==2)
    	{
    		drawASatellite(canvas,width,x,y,signal,"2",prn);	
    	}
    	
    	//»­GPSÎÀÐÇ
    	else
    	{
    		drawASatellite(canvas,width,x,y,signal,"",prn);	
    	}	
    }
    
    
    public static void drawASatellite(Canvas canvas,int width,int x,int y,int signal,String flag,int prn)
    {
    	Paint paint=new Paint();
		paint.setAntiAlias(true);
		
		if(signal/10>4)
		{
			signal=40;
		}
		
		paint.setColor(Const.color[signal/10]);
		paint.setStyle(Paint.Style.FILL);
		
		if(flag=="1")
		{	 
	       drawMyTuliBeidou1(canvas,x,y-width/84,x-width/96,y+width/160,x+width/96,y+width/160,Paint.Style.FILL,Const.color[signal/10]);	
		}
		
		else if(flag=="2")
		{
			canvas.drawRect(x-width/96,y-width/96 , x+width/96, y+width/96, paint);
		}
		
		else
		{
			canvas.drawCircle(x, y,width/80, paint);
		}
		
		 drawMyPrn(canvas,Const.color[signal/10],String.valueOf(prn),width,width/32,x,y);
    }
    
    
    public static void drawMyText(Canvas canvas,String text,int textsize,int x,int y)
    {
	    Paint paint=new Paint();
	    paint.setAntiAlias(true);
	    paint.setColor(Color.WHITE);
	    paint.setTextAlign(Align.CENTER);
	    paint.setTextSize(textsize);
	    canvas.drawText(text, x, y, paint);
    }
    
    
    public static void drawMyText(Canvas canvas,int color,String text,int textsize,int x,int y)
    {
	    Paint paint=new Paint();
	    paint.setAntiAlias(true);
	    paint.setColor(color);
	    paint.setTextAlign(Align.CENTER);
	    paint.setTextSize(textsize);
	    canvas.drawText(text, x, y, paint);
    }
    
    
    public static void drawMyTuliBeidou1(Canvas canvas,int x1,int y1,int x2,int y2,int x3,int y3,Paint.Style style,int color)
    {
    	Paint paint=new Paint();
 	    paint.setAntiAlias(true);
 	    paint.setColor(color);
 	    paint.setStrokeWidth(3);
 	    paint.setStyle(style);
 	    Path path = new Path();
        path.moveTo(x1, y1);
        path.lineTo(x2, y2);
        path.lineTo(x3, y3);
        path.lineTo(x1, y1);
        path.close();
        canvas.drawPath(path, paint);	    
    }
    
    
    public static void drawMyTuliBeidou2(Canvas canvas,int x,int y,int width)
    {
    	Paint paint=new Paint();
 	    paint.setAntiAlias(true);
 	    paint.setColor(Color.WHITE);
 	    paint.setStrokeWidth(3);
 	    paint.setStyle(Paint.Style.STROKE);      //»­¿ÕÐÄÍ¼
 	    canvas.drawRect(x-width/2, y-width/2, x+width/2, y+width/2, paint);
    }
    
    public static void	drawMyTuliFrame(Canvas canvas,int width)
    {
    	drawMyTuliOutline(canvas,width/48,13*width/10,47*width/48,173*width/120);        
     	drawMyLine(canvas,Color.WHITE,Paint.Style.STROKE,width/2,13*width/10,width/2,173*width/120);
    }
   
    
    public static void drawMyTuliGPS(Canvas canvas,int x,int y,int r)
    {
    	Paint paint=new Paint();
 	    paint.setAntiAlias(true);
 	    paint.setColor(Color.WHITE);
 	    paint.setStrokeWidth(3);
 	    paint.setStyle(Paint.Style.STROKE);      //»­¿ÕÐÄÍ¼
 	    canvas.drawCircle(x, y, r, paint);
    }
    
    
    public static void drawMyTuliOutline(Canvas canvas,int left,int top,int right,int bottom)
    {
    	Paint paint=new Paint();
 	    paint.setAntiAlias(true);
 	    paint.setColor(Color.WHITE);
 	    paint.setStrokeWidth(3);
 	    paint.setStyle(Paint.Style.STROKE);
 	    Rect rect=new Rect(left,top,right,bottom);
 	    canvas.drawRect(rect, paint);
    }
      
}
