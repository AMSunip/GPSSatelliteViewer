package am.android.activity;

import am.android.consts.Const;
import am.android.gpssatelliteviewer.R;
import am.android.manager.AnimationManagerSystem;
import am.android.manager.DatabaseManager;
import am.android.manager.FileManager;
import am.android.manager.GpsManager;
import am.android.manager.InforManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;

public class SettingActivity extends Activity implements OnClickListener
{
	
	private int            count              = 0;
	
    private Button         btn_exit           = null;
    private Button         btn_tomainpage     = null;
    
    private ImageButton    imgbtn_comeback    = null;
    private ImageView      iv_gps             = null;
    
    private LinearLayout   layout_setting     = null;
    
    private RelativeLayout layout_gps         = null;
    private RadioButton    rb_every1s         = null;
	private RadioButton    rb_every2s         = null;
	private RadioButton    rb_every3s         = null;
	private RadioButton    rb_every4s         = null;
	private RadioButton    rb_every5s         = null;
	private RadioButton    rb_theme_classical = null;
	private RadioButton    rb_theme_original  = null;
	private RadioGroup     rg_frequency       = null;
	private RadioGroup     rg_themesetting = null;
    
    private Handler handler=new Handler();
    
    private Runnable m_run =new Runnable() 
    {
		
		@Override
		public void run()
		{
		   handler.postDelayed(m_run, 500);
		   
		   if(GpsManager.isGPSEnable(SettingActivity.this))
		   {
			   count++;
			   if(count%2==0)
			   {
				   iv_gps.setBackgroundResource(R.drawable.gps_a);
			   }
			   else
			   {
				   iv_gps.setBackgroundResource(R.drawable.gps_b);
			   }
			   
			   if(count>999)
			   {
				   count=0;
			   }	   
		   }
		   else
		   {
			   iv_gps.setBackgroundResource(R.drawable.arrextend);
		   }
		   
		}
	};
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
	   super.onCreate(savedInstanceState);
	   setContentView(R.layout.activity_setting);
	   
	   init();   
	   
   }
    
   
    @Override
	public void onClick(View v)
	{
    	
		if(v.getId() == imgbtn_comeback.getId())
		{
		  finish();
		  AnimationManagerSystem.fromFade(SettingActivity.this);
		  if(!FileManager.isSDCardExist())
		  {
			  InforManager.showInfor(SettingActivity.this, "温馨提示：检测不到SD卡"); 
		  }
		}
				
		
		if(v.getId() == layout_gps.getId())
        {    	
        	Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);      	
        	startActivityForResult(intent, 1);
        	AnimationManagerSystem.fromFade(SettingActivity.this);
        }
		
		if(v.getId() == btn_exit.getId())
		{
			setResult(RESULT_OK);
			finish();
		}
		
		if(v.getId() == btn_tomainpage.getId())
		{
			finish();
			AnimationManagerSystem.fromFade(SettingActivity.this);
		}
		
	}


	private void init() 
	{
		initView();
		
		initRadioGroup();
		
		m_run.run();
	}
	
 
    private void initView()
    {
    	layout_setting=(LinearLayout)findViewById(R.id.layout_setting);
    	imgbtn_comeback=(ImageButton)findViewById(R.id.imgbtn_comeback);
		imgbtn_comeback.setOnClickListener(this);
	
		btn_exit=(Button)findViewById(R.id.btn_exit);
		btn_exit.setOnClickListener(this);
		btn_tomainpage=(Button)findViewById(R.id.btn_tomainpage);
		btn_tomainpage.setOnClickListener(this);
		iv_gps=(ImageView)findViewById(R.id.iv_gps);
		layout_gps=(RelativeLayout)findViewById(R.id.layout_gps);
		layout_gps.setOnClickListener(this);
		
		rg_themesetting=(RadioGroup)findViewById(R.id.rg_themesetting);
		rb_theme_original=(RadioButton)findViewById(R.id.rb_theme_original);
		rb_theme_classical=(RadioButton)findViewById(R.id.rb_theme_classical);
		
		rg_frequency=(RadioGroup)findViewById(R.id.rg_frequency);	
		rb_every1s=(RadioButton)findViewById(R.id.rb_every1s);
		rb_every2s=(RadioButton)findViewById(R.id.rb_every2s);
		rb_every3s=(RadioButton)findViewById(R.id.rb_every3s);
		rb_every4s=(RadioButton)findViewById(R.id.rb_every4s);
		rb_every5s=(RadioButton)findViewById(R.id.rb_every5s);
		
		setMyTheme();
		
		
		rg_themesetting.setOnCheckedChangeListener(new OnCheckedChangeListener()
		{
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{
				if(checkedId == rb_theme_classical.getId())
				{
					//古典怀旧
					Const.THEME = "1";
					if(FileManager.isSDCardExist())
					{
					    saveSettings();
						setMyTheme();
					}
					else
					{
						InforManager.showInfor(SettingActivity.this, "温馨提示：\n设置保存失败\n检测不到SD卡");
					}	
					
				}
				else
				{
					//极致简约
					Const.THEME = "0";
					if(FileManager.isSDCardExist())
					{
					    saveSettings();
						setMyTheme();
					}
					else
					{
						setMyTheme();
						InforManager.showInfor(SettingActivity.this, "温馨提示：\n设置保存失败\n检测不到SD卡");
					}	
				}
				
			}
		});
		
		
		rg_frequency.setOnCheckedChangeListener(new OnCheckedChangeListener() 
		{
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId)
			{

				if(checkedId == rb_every1s.getId())
				{	
					Const.SETTINGS_FREQUENCY = "1";	
				}
				else if(checkedId == rb_every2s.getId())
				{
					Const.SETTINGS_FREQUENCY = "2";					
				}
				else if(checkedId == rb_every4s.getId())
				{
					Const.SETTINGS_FREQUENCY = "4";					
				}
				else if(checkedId == rb_every5s.getId())
				{
					Const.SETTINGS_FREQUENCY = "5";					
				}
				else
				{
					//默认每3s刷新一次数据
					Const.SETTINGS_FREQUENCY = "3";	
				}
				
				if(FileManager.isSDCardExist())
				{
					saveSettings();
				}
				else
				{
				    InforManager.showInfor(SettingActivity.this, "温馨提示：\n设置保存失败\n检测不到SD卡");
				}	
			}
		});
		
		
		
		
		
		
    }
	
    
	private void initRadioGroup()
	{
		
		
		if(Const.THEME.equals("1"))
		{
			rg_themesetting.check(rb_theme_classical.getId());
		}
		else
		{
			rg_themesetting.check(rb_theme_original.getId());
		}
		
		
		if(Const.SETTINGS_FREQUENCY.equals("1"))
		{
			rg_frequency.check(rb_every1s.getId());
		}
		else if(Const.SETTINGS_FREQUENCY.equals("2"))
		{
			rg_frequency.check(rb_every2s.getId());
		}
		else if(Const.SETTINGS_FREQUENCY.equals("3"))
		{
			rg_frequency.check(rb_every3s.getId());
		}
		else if(Const.SETTINGS_FREQUENCY.equals("4"))
		{
			rg_frequency.check(rb_every4s.getId());
		}
		else
		{
			rg_frequency.check(rb_every5s.getId());
		}	
	}
    

	private void saveSettings()
	{
		DatabaseManager.writerDataToSettingFile(SettingActivity.this,Const.THEME,Const.SETTINGS_FREQUENCY);
	}
	
	
	private void setMyTheme()
	{
		if(Const.THEME.equals("1"))
		{
			layout_setting.setBackgroundResource(R.drawable.theme_classical);
		}
		else
		{
			layout_setting.setBackgroundResource(R.drawable.theme_original2);
		}
	}
	
}
