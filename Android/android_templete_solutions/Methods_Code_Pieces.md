# Halohoop_Code_Schools
 
## 001.打开应用市场指定搜索某个应用

    
	/**
	 * 使用隐式意图打开手机中原有应用市场
	 * 并且搜索传进来的包名对应的软件
	 *
	 */
	public static boolean openMarketAndSearchYST(Context context, String packageName) {
        try {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            Uri uri = Uri.parse("market://details?id=" + packageName);// 填入需要搜索的包名
            intent.setData(uri);
            //intent.setPackage(packageName);// 可以指定应用市场的包名
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

## 002.设置屏幕亮度到最亮,扫码的时候使用
	WindowManager.LayoutParams lp = getWindow().getAttributes();//getWindow是activity的方法
	lp.screenBrightness = 1.0f;
	getWindow().setAttributes(lp);

## 003.判断版本代码块
	if (Build.VERSION.SDK_INT/*当前手机的版本*/ >= Build.VERSION_CODES.JELLY_BEAN/*常量版本*/) {
		//blah blah blah
	} else {
		//blah blah blah
	}