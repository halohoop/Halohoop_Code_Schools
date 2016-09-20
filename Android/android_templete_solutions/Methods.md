# Halohoop_Code_Schools

### Version: 1.0.2

  * 打开应用市场指定搜索某个应用;
 
## 打开应用市场指定搜索某个应用

    
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
<<<<<<< 222f7b65d11dffce7c0cdaaf5944b04b27e1fdbe
            intent.setPackage(packageName);// 填入应用市场的包名
=======
            //intent.setPackage(packageName);// 可以指定应用市场的包名
>>>>>>> 打开应用市场指定搜索某个应用
            context.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }