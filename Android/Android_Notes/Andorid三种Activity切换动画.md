# Andorid三种Activity切换动画
##activity切换动画：
##1使用overridePendingTransition；
##2使用startActivity两个参数的：
	startActivity(intent, ActivityAnimUtils.getOptions(WelcomeActivity.this).toBundle());
  
	import android.app.ActivityOptions;
	import android.content.Context;
 
	public class ActivityAnimUtils {
    /**
     * activity切换过场动画
     */
    public static ActivityOptions getOptions(Context context) {
        ActivityOptions options =
                ActivityOptions.makeCustomAnimation(context,
                        android.R.anim.slide_in_left,
                        android.R.anim.slide_out_right);
        return options;
    }
          
 
##3使用主题设置：
    <style name="Anim_fade" parent="android:Theme.NoTitleBar">  
       <item name="android:windowAnimationStyle">@style/fade</item>  
    </style>  
    <style name="fade" parent="@android:style/Animation.Activity">  
       <item name="android:activityOpenEnterAnimation">@anim/fade_in</item>  
       <item name="android:activityOpenExitAnimation">@anim/fade_out</item>  
       <item name="android:activityCloseEnterAnimation">@anim/fade_in</item>  
       <item name="android:activityCloseExitAnimation">@anim/fade_out</item>  
    </style>  