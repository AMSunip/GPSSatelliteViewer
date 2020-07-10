package am.android.gpssatelliteviewer;

import java.util.ArrayList;
import java.util.Iterator;

import am.android.activity.AboutActivity;
import am.android.activity.SettingActivity;
import am.android.adapter.MyViewPagerAdapter;
import am.android.consts.Const;
import am.android.manager.AnimationManager;
import am.android.manager.AnimationManagerSystem;
import am.android.manager.DatabaseManager;
import am.android.manager.FileManager;
import am.android.manager.GpsManager;
import am.android.manager.InforManager;
import am.android.view.DrawSatelliteView;
import am.android.view.DrawSignalView;
import am.android.view.SlidingMenu;
import android.location.Criteria;
import android.location.GpsSatellite;
import android.location.GpsStatus;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class GPSSatelliteViewerActivity extends Activity implements OnClickListener
{
	
	private ArrayList<String> listdata_titleList = null;
	private ArrayList<View>   listdata_listViews = null;
	
	private Criteria          criteria           = null;
	
    private DrawSatelliteView drawsatelliteview  = null;
    private DrawSignalView    drawsignalview     = null;
	
    private Handler           handler_gps        = new Handler();
    
	private ImageButton       imgbtn_left        = null;
	private ImageButton       imgbtn_right       = null;
	
	private LinearLayout      layout_main        = null;
	private LinearLayout      layout_menu        = null;
	private LocationManager   locationManager    = null;
	private long              exitTime           = 0;
	
	private MyViewPagerAdapter adapter            = null;
	
	private PagerTabStrip     pagerTabStrip      = null;
	
	private SlidingMenu       view_slidingMenu   = null;
	
	private TextView          tv_about           = null;
	private TextView          tv_exit            = null;
	private TextView          tv_satellitefbt    = null;
	private TextView          tv_satellitexht    = null;
	private TextView          tv_setting         = null;
	private TextView          tv_title           = null;
	
	private View              view1              = null;
	private View              view2              = null;
	private ViewPager         viewPager          = null;
   
	
	private Runnable          run_gps            = new Runnable()
	{
		@Override
		
		public void run() 
		{
			handler_gps.postDelayed(run_gps,Integer.valueOf(Const.SETTINGS_FREQUENCY)*1000);
			
		    drawsatelliteview.setValue(GpsManager.getMySatelliteListdata(Const.numSatelliteList));   
		    drawsignalview.setValue(GpsManager.getMySatelliteListdata(Const.numSatelliteList));
		}	
	};
    
	
	private final GpsStatus.Listener statusListener=new GpsStatus.Listener() 
	{	
		@Override
		public void onGpsStatusChanged(int event)
		{
			GpsStatus status = locationManager.getGpsStatus(null);
			GetGPSStatus(event, status);	
		}
	};
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_gpssatellite_viewer);
		
		init();	
	}

	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{
		 if(requestCode == 0 && resultCode == RESULT_OK)
		 {
			 finish();
			 AnimationManagerSystem.fromFade(GPSSatelliteViewerActivity.this);
	     }
	}
	
	
	@Override
	public void onClick(View v)
	{
		
		if(v==tv_satellitefbt)
		{
			view_slidingMenu.closeMenu();
			toTabFBT();
		}
		
		if(v==tv_satellitexht)
		{
			view_slidingMenu.closeMenu();
			toTabXHT();	
		}
		
		if(v==tv_setting)
		{
			Intent intent = new Intent();			
			intent.setClass(GPSSatelliteViewerActivity.this,SettingActivity.class);			
			startActivityForResult(intent,0);
			AnimationManager.fromZoom(GPSSatelliteViewerActivity.this);
			
			if(!FileManager.isSDCardExist())
			{
				InforManager.showInfor(GPSSatelliteViewerActivity.this, "温馨提示：检测不到SD卡");
			}
			
		}
		
		if(v==tv_about)
		{
			Intent intent = new Intent();			
			intent.setClass(GPSSatelliteViewerActivity.this,AboutActivity.class);			
			startActivity(intent);
			AnimationManager.fromZoom(GPSSatelliteViewerActivity.this);
		}
		
		if(v==tv_exit)
		{
			view_slidingMenu.closeMenu();
			System.exit(0);		
		}
		
		
		if(v==imgbtn_left)
		{
			setSlide(view_slidingMenu);
		}
		
		if(v==imgbtn_right)
		{
			if(viewPager.getCurrentItem()==0)
			{
				toTabXHT();
			}
			else
			{
				toTabFBT();
			}
		}	
	}
	
	
	@Override
	protected void onDestroy()
	{
		super.onDestroy();
		System.exit(0);
	}
	

	@Override
    public boolean onKeyDown(int keyCode, KeyEvent event) 
	{
		if(keyCode==KeyEvent.KEYCODE_BACK&& event.getAction() == KeyEvent.ACTION_DOWN)
		{
		    if((System.currentTimeMillis()-exitTime) > 2000)
			{
		    	InforManager.showInfor(GPSSatelliteViewerActivity.this, "再按一次退出程序");   
		        exitTime = System.currentTimeMillis();		      
		    } 
		    else 
		    {    
               finish();
               AnimationManagerSystem.fromFade(GPSSatelliteViewerActivity.this);
		    }    
		    return true;    
		}
		
		if(keyCode == KeyEvent.KEYCODE_MENU)
		{
			setSlide(view_slidingMenu);
		}
		
		return super.onKeyDown(keyCode, event);
	}
	
	
	protected void onStart()
	{
		super.onStart();
		if(FileManager.isSDCardExist())
		{
		    setMyTheme();
		}
	}

	
	private void init()
	{
		//初始化时，先判断SD卡是否存在
		//说明：如果SD卡不存在，则不能保存配置信息
		
		//当SD卡存在时
		
		if(FileManager.isSDCardExist())
		{
			FileManager.makeDirectory();
			FileManager.makeFile();			
			initDatabaseFile();	
			initView();			
	        initGoogleGPS();		
		    run_gps.run();
		}
	    //当SD卡不存在时
		else
		{
			initView();			
	        initGoogleGPS();		
		    run_gps.run();
		    InforManager.showInfor(GPSSatelliteViewerActivity.this, "温馨提示：检测不到SD卡");
		}
	        
	}


	private void initDatabaseFile()
	{
		
		DatabaseManager.fileExist(GPSSatelliteViewerActivity.this,"settings2.db");
		
		if(Const.file_settings.exists())
		{
		   DatabaseManager.getMySettings();  
		}
		else
		{
		  setTheme("0");
		  setFrequency("3");
		}
	}

	
	private void initGoogleGPS()
	{
		locationManager=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
		locationManager.addGpsStatusListener(statusListener);
		criteria=new Criteria();
		criteria.setAccuracy(Criteria.ACCURACY_COARSE);
		criteria.setAltitudeRequired(true);	     	
		criteria.setBearingRequired(true);
		criteria.setCostAllowed(false);
		criteria.setPowerRequirement(Criteria.POWER_MEDIUM);	
		String str_location = locationManager.getBestProvider(criteria, false);  
        locationManager.requestLocationUpdates(str_location, 5000, 0, locationListener);  		
	}
	
	
	private final LocationListener locationListener=new LocationListener()
	{

		@Override
		public void onLocationChanged(Location location) 
		{
			  /* if(location!=null)
			   {
				   InforManager.showInfor(GPSSatelliteViewerActivity.this, String.valueOf(location.getLatitude())+":"+String.valueOf(location.getLongitude()));
			   }*/
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras)
		{
		
		}

		@Override
		public void onProviderEnabled(String provider) 
		{
		
			
		}

		@Override
		public void onProviderDisabled(String provider)
		{
			
		}		
	};
	
	
	private void GetGPSStatus(int event, GpsStatus status)
	{
	    if(status==null)
	    {
	    	
	    }
	    else if(event==GpsStatus.GPS_EVENT_SATELLITE_STATUS)
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
	
	
	private void initView()
	{
		layout_main=(LinearLayout)findViewById(R.id.layout_main);
		layout_menu=(LinearLayout)findViewById(R.id.layout_menu);
		imgbtn_left=(ImageButton)findViewById(R.id.imgbtn_left);
		imgbtn_left.setOnClickListener(this);
		imgbtn_right=(ImageButton)findViewById(R.id.imgbtn_right);
		imgbtn_right.setOnClickListener(this);
		view_slidingMenu = (SlidingMenu) findViewById(R.id.slide_menu);		
		tv_title=(TextView)findViewById(R.id.tv_title);
		
		tv_about=(TextView)findViewById(R.id.tv_about);  
		tv_about.setOnClickListener(this);
		tv_exit=(TextView)findViewById(R.id.tv_exit);
		tv_exit.setOnClickListener(this);
		tv_satellitefbt=(TextView)findViewById(R.id.tv_satellitefbt);
		tv_satellitefbt.setOnClickListener(this);
		tv_satellitexht =(TextView)findViewById(R.id.tv_satellitexht);
		tv_satellitexht.setOnClickListener(this);
		tv_setting=(TextView)findViewById(R.id.tv_setting);
		tv_setting.setOnClickListener(this);
		tv_title=(TextView)findViewById(R.id.tv_title);
		tv_title.setOnClickListener(this);
		
		viewPager = (ViewPager) findViewById(R.id.viewpager); 
		pagerTabStrip=(PagerTabStrip) findViewById(R.id.pagertab); 
		pagerTabStrip.setDrawFullUnderline(false); 
		pagerTabStrip.setTextSpacing(50); 
		
		LayoutInflater inflater=LayoutInflater.from(GPSSatelliteViewerActivity.this);
		view1=inflater.inflate(R.layout.item_circle, null);
		view2=inflater.inflate(R.layout.item_rectangle, null);    
		listdata_listViews = new ArrayList<View>();
		listdata_listViews.add(view1);  
		listdata_listViews.add(view2);   
	    listdata_titleList = new ArrayList<String>();
	    listdata_titleList.add("卫星分布图");  
	    listdata_titleList.add("卫星信号图"); 
	    
	    drawsatelliteview=(DrawSatelliteView)view1.findViewById(R.id.drawsatelliteview);
	    drawsignalview=(DrawSignalView)view2.findViewById(R.id.drawsignalview);
		
	    adapter=new MyViewPagerAdapter(listdata_listViews, listdata_titleList);
	    viewPager.setAdapter(adapter); 
	    
	    toTabFBT();
	    
	    
	    viewPager.setOnPageChangeListener(new OnPageChangeListener()
	    {

			@Override
			public void onPageScrollStateChanged(int position) 
			{				
				if(viewPager.getCurrentItem()==0)
				{
					toTabFBT();
				}
				else
				{
					toTabXHT();
				}				
			}

			@Override
			public void onPageScrolled(int position, float arg1, int arg2) 
			{
				
			}

			@Override
			public void onPageSelected(int position)
			{
				
			}	
	    });	
	}
	
	
	private void setFrequency(String Frequency)
	{
		Const.SETTINGS_FREQUENCY = Frequency;
	}
	
	
	private void setMyTheme()
	{
		//古典怀旧
		if(Const.THEME.equals("1"))
		{
			layout_main.setBackgroundResource(R.drawable.theme_classical);
			layout_menu.setBackgroundResource(R.drawable.background_classical);
		}
		//极致简约
		else
		{
			layout_main.setBackgroundResource(R.drawable.theme_original);
			layout_menu.setBackgroundResource(R.drawable.background_original);
		}
	}

	
	private void setTheme(String Theme)
	{
		Const.THEME = Theme;
	}
	
	
	private void setSlide(SlidingMenu slideMenu)
	{
		if (slideMenu.isMainScreenShowing()) 
		{
			slideMenu.openMenu();
		}
		else
		{
			slideMenu.closeMenu();
		}
	}	

	
	private void toTabFBT()
	{
		viewPager.setCurrentItem(0);
		tv_title.setText("卫星分布图");
		imgbtn_right.setBackgroundResource(R.drawable.totab_a);	
	}
	
	
	private void toTabXHT()
	{
		viewPager.setCurrentItem(1);
		tv_title.setText("卫星信号图");
		imgbtn_right.setBackgroundResource(R.drawable.totab_b);	
	}
	
}
