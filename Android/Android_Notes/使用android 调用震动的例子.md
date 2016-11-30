# 使用android 调用震动的例子

调用Android系统的震动，只需要一个类 那就是Vibrator ,这个类在hard包中，一看系统级的服务，又要通过manifest.xml文件设置权限了


	<?xml version="1.0" encoding="utf-8"?>  
	<manifest xmlns:android="http://schemas.android.com/apk/res/android"  
	      package="uni.vibrator"  
	      android:versionCode="1"  
	      android:versionName="1.0">  
	    <uses-sdk android:minSdkVersion="8" />  
	  
	    <application android:icon="@drawable/icon" android:label="@string/app_name">  
	        <activity android:name=".VibratorDemoActivity"  
	                  android:label="@string/app_name">  
	            <intent-filter>  
	                <action android:name="android.intent.action.MAIN" />  
	                <category android:name="android.intent.category.LAUNCHER" />  
	            </intent-filter>  
	        </activity>  
	  
	    </application>  
	     <uses-permission android:name="android.permission.VIBRATE" />  
	</manifest>  

##
	<?xml version="1.0" encoding="utf-8"?>  
	<manifest xmlns:android="http://schemas.android.com/apk/res/android"  
	      package="uni.vibrator"  
	      android:versionCode="1"  
	      android:versionName="1.0">  
	    <uses-sdk android:minSdkVersion="8" />  
	  
	    <application android:icon="@drawable/icon" android:label="@string/app_name">  
	        <activity android:name=".VibratorDemoActivity"  
	                  android:label="@string/app_name">  
	            <intent-filter>  
	                <action android:name="android.intent.action.MAIN" />  
	                <category android:name="android.intent.category.LAUNCHER" />  
	            </intent-filter>  
	        </activity>  
	  
	    </application>  
	     <uses-permission android:name="android.permission.VIBRATE" />  
	</manifest>  

下面还是一起学习一下SDK吧
 

Class that operates the vibrator on the device.
If your process exits, any vibration you started with will stop. 

//Vibrator类用来操作设备上的震动，如果你的线程退出了，那么启动的震动也会停止

---------------------------------------------------------------------------------------------------------------------------------------------------
public void vibrate (long[] pattern, int repeat)
Since: API Level 1

Vibrate with a given pattern.  //根据给定的节奏震动

Pass in an array of ints that are the durations for which to turn on or off the vibrator in milliseconds. The first value indicates the number of milliseconds to wait before turning the vibrator on. The next value indicates the number of milliseconds for which to keep the vibrator on before turning it off. Subsequent values alternate between durations in milliseconds to turn the vibrator off or to turn the vibrator on.
//传递一个整型数组作为关闭和开启震动的持续时间，以毫秒为单位。第一个值表示等待震动开启的毫秒数，下一个值表示保持震动的毫秒数，这个序列值交替表示震动关闭和开启的毫秒数

To cause the pattern to repeat, pass the index into the pattern array at which to start the repeat, or -1 to disable repeating.
//为了重复的按设定的节奏震动，传递index参数表示重复次数，用-1表示不重复。

Parameters
pattern     an array of longs of times for which to turn the vibrator on or off.
repeat     the index into pattern at which to repeat, or -1 if you don't want to repeat.
---------------------------------------------------------------------------------------------------------------------------------------------------

还包含一个方法叫做cancel，用来取消震动

一段演示的代码

	/* 
	 * @author octobershiner 
	 * 2011 7 25 
	 * SE.HIT 
	 * 一个使用android手机震动的demo 
	 * */  
	package uni.vibrator;  
	  
	import android.app.Activity;  
	import android.content.Context;  
	import android.os.Bundle;  
	import android.os.Vibrator;  
	  
	public class VibratorDemoActivity extends Activity {  
	    private Vibrator vibrator;  
	    /** Called when the activity is first created. */  
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.main);  
	          
	        /* 
	         * 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到 
	         * */  
	        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);  
	        long [] pattern = {100,400,100,400};   // 停止 开启 停止 开启   
	        vibrator.vibrate(pattern,2);           //重复两次上面的pattern 如果只想震动一次，index设为-1   
	    }  
	      
	    public void onStop(){  
	        super.onStop();  
	        vibrator.cancel();  
	    }  
	}

##

	/* 
	 * @author octobershiner 
	 * 2011 7 25 
	 * SE.HIT 
	 * 一个使用android手机震动的demo 
	 * */  
	package uni.vibrator;  
	  
	import android.app.Activity;  
	import android.content.Context;  
	import android.os.Bundle;  
	import android.os.Vibrator;  
	  
	public class VibratorDemoActivity extends Activity {  
	    private Vibrator vibrator;  
	    /** Called when the activity is first created. */  
	    @Override  
	    public void onCreate(Bundle savedInstanceState) {  
	        super.onCreate(savedInstanceState);  
	        setContentView(R.layout.main);  
	          
	        /* 
	         * 想设置震动大小可以通过改变pattern来设定，如果开启时间太短，震动效果可能感觉不到 
	         * */  
	        vibrator = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);  
	        long [] pattern = {100,400,100,400};   // 停止 开启 停止 开启   
	        vibrator.vibrate(pattern,2);           //重复两次上面的pattern 如果只想震动一次，index设为-1   
	    }  
	      
	    public void onStop(){  
	        super.onStop();  
	        vibrator.cancel();  
	    }  
	}