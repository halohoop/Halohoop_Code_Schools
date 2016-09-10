# Halohoop_Code_Schools

### Version: 1.0.2

  * [打开应用市场指定搜索某个应用](https://github.com/halohoop/Halohoop_Code_Schools/blob/android_templete_solutions/Android/android_templete_solutions/Methods.md#%E6%89%93%E5%BC%80%E5%BA%94%E7%94%A8%E5%B8%82%E5%9C%BA%E6%8C%87%E5%AE%9A%E6%90%9C%E7%B4%A2%E6%9F%90%E4%B8%AA%E5%BA%94%E7%94%A8)；

### Version: 1.0.3

  * 设置屏幕亮度到最亮;
 
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

## 002.设置屏幕亮度到最亮
	WindowManager.LayoutParams lp = getWindow().getAttributes();//getWindow是activity的方法
	lp.screenBrightness = 1.0f;
	getWindow().setAttributes(lp);