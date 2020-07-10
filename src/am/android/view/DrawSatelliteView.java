package am.android.view;

import java.util.ArrayList;
import java.util.HashMap;

import am.android.consts.Const;
import am.android.drawing.AMDrawing;
import am.android.manager.GpsManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;

public class DrawSatelliteView extends View 
{
	private ArrayList<HashMap<String,Integer>> listdata_satellite=new ArrayList<HashMap<String,Integer>>();
	private static int width;
	private Point center=new Point();
	private int tempX,tempY;
	
	public DrawSatelliteView(Context context, AttributeSet attrs) 
	{
		super(context, attrs);
	}

	@Override
	protected void onDraw(Canvas canvas)
	{
		super.onDraw(canvas);
		
		/******确定宽度,高度以及中心点******/
		width=canvas.getWidth();                //480                  720
	//	height=canvas.getHeight();              //618(690)(720)        1030(1126)(1280)
		center.x=width/2;
		center.y=3*width/4;
		int r=11*width/24;
		/******确定宽度,高度以及中心点******/
		
		/******画外轮廓******/
		Paint paint1=new Paint();
		paint1.setAntiAlias(true);
		paint1.setColor(Color.WHITE);
		paint1.setStrokeWidth(3);
		paint1.setStyle(Paint.Style.STROKE);
		canvas.drawCircle(center.x, center.y, r+2, paint1);
		/******画外轮廓******/
		
		/******画填充圆******/
		/*Paint paint2=new Paint();
		paint2.setAntiAlias(true);
		paint2.setColor(Color.rgb(0, 0, 61));
		canvas.drawCircle(center.x, center.y,r-1, paint2);*/
		/******画填充圆******/
		
		/******画中心点******/
		Paint paint3=new Paint();
		paint3.setAntiAlias(true);
		paint3.setColor(Color.WHITE);
		paint3.setStyle(Paint.Style.FILL);
		canvas.drawCircle(center.x, center.y,5, paint3);
		/******画中心点******/	
		
		/******画虚线圆******/
		AMDrawing.drawMyCircle(canvas,center.x,center.y,r/3);
		AMDrawing.drawMyCircle(canvas,center.x,center.y,2*r/3);
		/******画虚线圆******/
		
		/******画虚线******/
		for(int i=0;i<12;i++)
		{
			AMDrawing.drawMyLine(canvas,Color.WHITE,Paint.Style.STROKE,center.x,center.y,center.x+(int)(r*Math.cos(i*30*Math.PI/180)),center.y+(int)(r*Math.sin(i*30*Math.PI/180)));
			AMDrawing.drawMyText(canvas,String.valueOf((i+3)*30%360)+"°",5*width/96,center.x+(int)(5*r*Math.cos(i*30*Math.PI/180)/6),center.y+(int)(5*r*Math.sin(i*30*Math.PI/180)/6)+width/48);
		}
		/******画虚线******/
		
		/******画NESW******/
		String[] direction=new String[]{"E","S","W","N"};
		for(int i=0;i<4;i++)
		{
			AMDrawing.drawMyText(canvas,direction[i],width/16,center.x+(int)(2*r*Math.cos(i*Math.PI/2)/3),center.y+(int)(2*r*Math.sin(i*Math.PI/2)/3)+width/48);
		}
		/******画NESW******/
		
		/******画外边框******/
		AMDrawing.drawMyCircleOutline(canvas,width/48,width/48,47*width/48,width/5);
		AMDrawing.drawMyCircleOutline(canvas,width/48,width/4,47*width/48,5*width/4);	
		/******画外边框******/
		
		/******卫星图例******/
		AMDrawing.drawMyTuliFrame(canvas,width);
		AMDrawing.drawMyText(canvas, "卫星分布图", 5*width/96, 71*width/96+5*width/480,11*width/8+7*width/480);

		if(GpsManager.isGPSEnable(getContext()))
		{
			AMDrawing.drawMyText(canvas, Color.GREEN,"GPS已开启", 5*width/96, width/4,11*width/8+7*width/480);
		}
		else
		{
			AMDrawing.drawMyText(canvas, Color.RED,"GPS未开启", 5*width/96, width/4,11*width/8+7*width/480);
		}
		
		/******卫星图例******/
		
		/******信号强度模块******/
		AMDrawing.drawMyText(canvas,"信号",5*width/96,width/12,width/10);
        int w=r/6;
		for(int i=0;i<5;i++)
		{
		AMDrawing.drawMyColor(canvas,Const.color[i],width/6+i*w,width/24,r,7*width/96);
		AMDrawing.drawMyText(canvas,String.valueOf(i*10),5*width/96,width/6+i*w,width/6);
		}
		AMDrawing.drawMyColor(canvas,Color.rgb(0, 255, 0),width/6+4*w,width/24,5*w,7*width/96);
		AMDrawing.drawMyText(canvas,String.valueOf(99),5*width/96,width/6+10*w,width/6);
		/******信号强度模块******/	
			
		/******卫星数据图形展现******/
		for(int i=0;i<listdata_satellite.size();i++)
		{
			tempX=listdata_satellite.get(i).get("PointX").intValue();
			tempY=90-listdata_satellite.get(i).get("PointY").intValue();  //注意区分【仰角】与【天顶距】
	    	AMDrawing.drawMySatellite(canvas,width,center.x+convertPointX(tempX,tempY,r),center.y+convertPointY(tempX,tempY,r),listdata_satellite.get(i).get("signal").intValue(),listdata_satellite.get(i).get("type").intValue(),listdata_satellite.get(i).get("prn").intValue()); 
		}
		/******卫星数据图形展现******/
		
		/******标记卫星数量******/
	  //Const.count_satellite=Const.numSatelliteList.size();
		Const.count_satellite= this.listdata_satellite.size();
		AMDrawing.drawMyText(canvas,"卫星总数",5*width/96,7*width/48,5*width/16);
		AMDrawing.drawMyText(canvas,String.valueOf(Const.count_satellite),5*width/96,7*width/48,3*width/8);
		/******标记卫星数量******/
	}
 
    
    private int convertPointX(int PointX,int PointY,int R)
    {
    	return  (int)(PointY*R*Math.cos((PointX-90)*Math.PI/180)/90); 
    }
    
    private int convertPointY(int PointX,int PointY,int R)
    {
    	return (int)(PointY*R*Math.sin((PointX-90)*Math.PI/180)/90);
    }
    
    
    public void setValue(ArrayList<HashMap<String,Integer>> listdata_satellite)
    {    
       this.listdata_satellite.clear();      
       for(int i=0;i<listdata_satellite.size();i++)
       {
    	   //如果信号强度为0，则不会加入数据源里面
    	   if(listdata_satellite.get(i).get("signal").intValue()>0)
    	   {
    		   this.listdata_satellite.add(listdata_satellite.get(i));  
    	   }
       }
       invalidate();      
    }
    
}


