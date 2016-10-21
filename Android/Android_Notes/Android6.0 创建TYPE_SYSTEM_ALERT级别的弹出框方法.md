# Android6.0 创建TYPE_SYSTEM_ALERT级别的弹出框方法，以及需要的运行时权限

	if (Build.VERSION.SDK_INT >= 23) {
	   if(!Settings.canDrawOverlays(this)) {
	       Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
	       startActivity(intent);
	       return;
	   } else {
	   		//already hava permission
	   }
	} else {
	   //api version is lower than 23;
	   //just need manifest permission
	}
	
### see [https://developer.android.com/reference/android/provider/Settings.html#canDrawOverlays(android.content.Context)](https://developer.android.com/reference/android/provider/Settings.html#canDrawOverlays(android.content.Context))
