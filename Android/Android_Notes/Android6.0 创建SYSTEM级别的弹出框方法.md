# Android6.0 创建SYSTEM级别的弹出框方法，以及需要的运行时权限

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

	//And int Manifest declare：
	<uses-permission android:name="android.permission.SYSTEM_ALERT_WINDOW" />

当然光有这个权限是不够，我们要知道WindowManager的LayoutParam的typs属性的引用：
下面是我自己的总结：

* 经过测试得知，非安卓内置内置的应用，也就是没有最高权限的应用（其实也就是没有运行在安卓System进程中的应用），使用一些type会直接报错，即使声明了权限以及运行时权限。
* 但是还是有一些我们是可以直接使用的总结如下，有error就表示的权限不允许使用，并没有全部挨个列出，因为测试到后面发现有其中的几个能够使用的就能够满足需求了：

		//权限拒绝的
		WindowManager.LayoutParams.TYPE_SEARCH_BAR;//2001 error
		WindowManager.LayoutParams.TYPE_KEYGUARD_DIALOG;//2009 error
		WindowManager.LayoutParams.TYPE_SYSTEM_DIALOG;//2008 error
		WindowManager.LayoutParams.TYPE_INPUT_METHOD;//2011 error
		//以下是可以使用的
		//在状态栏后面，可以接收点击
		WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;//2003 behind status can click
		//在状态栏后面，可以接收点击
		WindowManager.LayoutParams.TYPE_PHONE;//2002 behind status can click
		WindowManager.LayoutParams.TYPE_SYSTEM_OVERLAY;//2006 behind status can't click
		WindowManager.LayoutParams.TYPE_SYSTEM_ERROR;//2010 above status can click