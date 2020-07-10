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

public class DrawSignalView extends View 
{
	private int width;
	
	private Point center=new Point();

	private ArrayList<HashMap<String,Integer>> listdata_satellite=new ArrayList<HashMap<String,Integer>>();
	
	public DrawSignalView(Context context, AttributeSet attrs)
	{
		super(context, attrs);
	}

    @Override
    protected void onDraw(Canvas canvas)
    {
	   super.onDraw(canvas);
	  
	   width=canvas.getWidth();
	   int r=11*width/24;
	   center.x=width/2;
	   center.y=3*width/4;
	  
	   /******����߿�******/
	   AMDrawing.drawMyCircleOutline(canvas,width/48,width/48,47*width/48,width/5);
	   AMDrawing.drawMyCircleOutline(canvas,width/48,width/4,47*width/48,5*width/4);
	   AMDrawing.drawMyText(canvas, "�����ź�ͼ", 5*width/96, 71*width/96+5*width/480,11*width/8+7*width/480);
	   
	   if(GpsManager.isGPSEnable(getContext()))
	   {
		    AMDrawing.drawMyText(canvas, Color.GREEN,"GPS�ѿ���", 5*width/96, width/4,11*width/8+7*width/480);
	   }  
	   else
	   {
			AMDrawing.drawMyText(canvas, Color.RED,"GPSδ����", 5*width/96, width/4,11*width/8+7*width/480);
	   }
	   /******����߿�******/
	  
	   /******�ź�ǿ��ģ��******/
	   AMDrawing.drawMyText(canvas,"�ź�",5*width/96,width/12,width/10);
       int w=r/6;
	   int[] color=new int[]{Color.RED,Color.rgb(255, 128, 0),Color.rgb(217, 255, 0),Color.rgb(192, 255, 0),Color.rgb(0, 255, 0)};
	   for(int i=0;i<5;i++)
	   {
		 AMDrawing.drawMyColor(canvas,color[i],width/6+i*w,width/24,r,7*width/96);
		 AMDrawing.drawMyText(canvas,String.valueOf(i*10),5*width/96,width/6+i*w,width/6);
	   }
	     AMDrawing.drawMyColor(canvas,Color.rgb(0, 255, 0),width/6+4*w,width/24,5*w,7*width/96);
		 AMDrawing.drawMyText(canvas,String.valueOf(99),5*width/96,1*width/6+10*w,width/6);
		/******�ź�ǿ��ģ��******/
	     
	 	/******����ͼ��******/
		AMDrawing.drawMyTuliFrame(canvas,width);
	    /******����ͼ��******/
	      
	    /******�ڿ����******/
	    AMDrawing.drawMyRectangle(canvas,width/12,width/3,11*width/12,7*width/6);
	     //������
	     for(int i=0;i<5;i++)
	     {
	    	 AMDrawing.drawMyText(canvas, String.valueOf(20*i), width/24, width/20, (7-i)*width/6+width/60);
	     }
	         AMDrawing.drawMyText(canvas, String.valueOf(99), width/24, width/20,width/3+width/120);
	     
	     //��ˮƽ��
	     for(int i=0;i<4;i++)
	     {
	    	 AMDrawing.drawMyLine(canvas,Color.WHITE,Paint.Style.STROKE, width/12,(3+i)*width/6, 11*width/12,(3+i)*width/6);
	     }
	   //  AMDrawing.drawMyText(canvas,String.valueOf("PRN"),width/32,37*width/40,6*width/5); 
	 
	     //��ȡ��������
	    // Const.count_satellite=Const.numSatelliteList.size();	    
	     Const.count_satellite= this.listdata_satellite.size();
	     //������     
	     //10���ڵ�����
	     if(Const.count_satellite<=10)
	     {
	    	for(int i=0;i<9;i++)
		    {
	    	   AMDrawing.drawMyLine(canvas,Color.WHITE,Paint.Style.STROKE, (i+2)*width/12, width/3, (i+2)*width/12, 7*width/6); 
	    	   AMDrawing.drawMyText(canvas, String.valueOf(i+1), width/24,(2*i+3)*width/24,73*width/60);
		    }
	    	   AMDrawing.drawMyText(canvas, String.valueOf(10), width/24, 7*width/8,73*width/60);
	    	   AMDrawing.drawMyRectangleLevelA(canvas,width,listdata_satellite);
	     }
	     
	     //��������11-16��
	     else  if((Const.count_satellite>=11)&&(Const.count_satellite<=16))
	     {
	    	 for(int i=0;i<15;i++)
			 {
	    	    AMDrawing.drawMyLine(canvas,Color.WHITE,Paint.Style.STROKE, (13+5*i)*width/96, width/3, (13+5*i)*width/96, 7*width/6); 
	    	    AMDrawing.drawMyText(canvas, String.valueOf(i+1),width/24,(21+10*i)*width/192,73*width/60);
			 }
	    	    AMDrawing.drawMyText(canvas, String.valueOf(16), width/24, 57*width/64,73*width/60);
	    	    AMDrawing.drawMyRectangleLevelB(canvas,width,listdata_satellite);
	     }
	     
	     //��������16-20��
	     else  if((Const.count_satellite>=16)&&(Const.count_satellite<=20))
	     {
	    	 for(int i=0;i<19;i++)
			 {
	    	    AMDrawing.drawMyLine(canvas,Color.WHITE,Paint.Style.STROKE,(3+i)*width/24, width/3, (3+i)*width/24, 7*width/6);  
	    	    AMDrawing.drawMyText(canvas, String.valueOf(i+1),3*width/96,(5+2*i)*width/48,29*width/24);
			 } 
	    	 AMDrawing.drawMyText(canvas, String.valueOf(20), 3*width/96, 43*width/48,29*width/24);
	    	 AMDrawing.drawMyRectangleLevelC(canvas,width,listdata_satellite);
	     }
	     
	     //��������21-25��
	     else  if((Const.count_satellite>=21)&&(Const.count_satellite<=25))
	     {
	    	 for(int i=0;i<24;i++)
			 {
	    	    AMDrawing.drawMyLine(canvas,Color.WHITE,Paint.Style.STROKE,(7+2*i)*width/60, width/3, (7+2*i)*width/60, 7*width/6);  
	    	    AMDrawing.drawMyText(canvas, String.valueOf(i+1),width/40,(6+2*i)*width/60,289*width/240);
			 } 
	    	 AMDrawing.drawMyText(canvas, String.valueOf(25), width/40, 9*width/10,289*width/240);
	    	 AMDrawing.drawMyRectangleLevelD(canvas,width,listdata_satellite);
	     }
	     
	     //��������26-32��
	     else
	     {
	    	 for(int i=0;i<33;i++)
			 {
	    	    AMDrawing.drawMyLine(canvas,Color.WHITE,Paint.Style.STROKE,(13+3*i)*width/120, width/3, (13+3*i)*width/120, 7*width/6);  
	    	    AMDrawing.drawMyText(canvas, String.valueOf(i+1),width/48,(23+6*i)*width/240,6*width/5);
			 } 
	    	  AMDrawing.drawMyRectangleLevelE(canvas,width,listdata_satellite); 
	     }
	   
	     //ϵͳ���������������ֵ:33
    }
    
    private void sortListdata()
    {
    	HashMap<String,Integer> map=new HashMap<String,Integer>();	
    	for(int i=0;i<listdata_satellite.size();i++)
    	{
    		for(int j=i;j<listdata_satellite.size();j++)
    		{
    			if(listdata_satellite.get(i).get("prn").intValue()>listdata_satellite.get(j).get("prn").intValue())
    			{
    				map=listdata_satellite.get(i);
    				listdata_satellite.set(i, listdata_satellite.get(j));
    				listdata_satellite.set(j, map);
    			}
    		}
    	}
    }
    
    
    public void setValue(ArrayList<HashMap<String,Integer>> listdata_satellite)
    {    
       this.listdata_satellite.clear();      
       for(int i=0;i<listdata_satellite.size();i++)
       {
    	   //����ź�ǿ��Ϊ0���򲻻��������Դ����
    	   if(listdata_satellite.get(i).get("signal").intValue()>0)
    	   {
    	     this.listdata_satellite.add(listdata_satellite.get(i));
    	   }
       }
       sortListdata();
       invalidate();      
    }
	
}
