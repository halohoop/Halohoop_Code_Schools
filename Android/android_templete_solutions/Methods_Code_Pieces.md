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

## 004.将十六进制字符串颜色代码（如：#ff0000）转换为int颜色值

	public int parseColor(String colorHex){
		//dont forget to try/catch
		return android.graphics.Color.parseColor(colorHex);
	}	

	int red = parseColor("#ff0000");

## 005.获取屏幕的宽高，兼容新旧Api

    @SuppressLint("NewApi")//getSize(方法)需要 api13才能使用
    /**
     * 通过返回值point拿宽高
     * point.x 屏幕的宽
     * point.y 屏幕的高
     */
    public static Point getScreenSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point out = new Point();
        //Build.VERSION_CODES.HONEYCOMB_MR2 → 13
        if (Build.VERSION.SDK_INT >= 13) {
            display.getSize(out);
        } else {
            int width = display.getWidth();
            int height = display.getHeight();
            out.set(width, height);
        }
        return out;
    }

## 006.bitmap转为byte数组

    /**
     * bitmap转为byte数组
     * @param bitmap
     * @return
     */
    public byte[] bitmap2ByteArray(Bitmap bitmap) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();//初始化一个流对象
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, output);//把bitmap100%高质量压缩 到 output对象里
        bitmap.recycle();//自由选择是否进行回收
        byte[] result = output.toByteArray();//转换成功了
        try {
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }