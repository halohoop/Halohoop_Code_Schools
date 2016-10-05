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

## 007.Bitmap图片拼接

	/**
	* 横向拼接
	* <功能详细描述>
	* @param first
	* @param second
	* @return
	*/
	private Bitmap add2Bitmap(Bitmap first, Bitmap second) {
		int width = first.getWidth() + second.getWidth();
		int height = Math.max(first.getHeight(), second.getHeight());
		Bitmap result = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(result);
		canvas.drawBitmap(first, 0, 0, null);
		canvas.drawBitmap(second, first.getWidth(), 0, null);
		return result;

	}


	/**
	* 纵向拼接
	* <功能详细描述>
	* @param first
	* @param second
	* @return
	*/
	private Bitmap addBitmap(Bitmap first, Bitmap second) {
		int width = Math.max(first.getWidth(),second.getWidth());
		int height = first.getHeight() + second.getHeight();
		Bitmap result = Bitmap.createBitmap(width, height, Config.ARGB_8888);
		Canvas canvas = new Canvas(result);
		canvas.drawBitmap(first, 0, 0, null);
		canvas.drawBitmap(second, 0, first.getHeight(), null);
		return result;
	}

### from [http://blog.csdn.net/ajun495175289/article/details/18091683](http://blog.csdn.net/ajun495175289/article/details/18091683)

## 008.Open Activity in a Service

	Intent dialogIntent = new Intent(getBaseContext(), YourActivity.class);   
    dialogIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);   
    getApplication().startActivity(dialogIntent);   

### from [http://blog.csdn.net/aminfo/article/details/7895426](http://blog.csdn.net/aminfo/article/details/7895426)

## 009.在view中监听home键、菜单键和返回键，构建回调

	/**
	 * 监听home键和menu键
	 * Receiver for listening home & recent key
	 * 使用广播接受者的形式
	 */
	class HomeKeyEventReceiver extends BroadcastReceiver {

	      String SYSTEM_REASON = "reason";

	      String SYSTEM_HOME_KEY = "homekey";

	      String SYSTEM_DIALOG_REASON_RECENT_APPS = "recentapps";

	      @Override
	      public void onReceive(Context context, Intent intent) {
		  String action = intent.getAction();
		  if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
		      String reason = intent.getStringExtra(SYSTEM_REASON);
		      if (TextUtils.equals(reason, SYSTEM_HOME_KEY)) {
			  yourCallback.onHome();
		      } else if(TextUtils.equals(reason, SYSTEM_DIALOG_REASON_RECENT_APPS)) {
			  yourCallback.onMenu();
		      }
		  }
	      }
 	 }

	  /**
	   * 监听返回键
	   * Listening to the back key press
	   * the method 'onKeyUp' is Override from View
	   */
	  @Override
	  public boolean onKeyUp(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
		    yourCallback.back();
		    return true;
		}
		//others let it go
		return super.onKeyUp(keyCode, event);
	  }

## 010.Android 获取View在屏幕中的位置
from：(http://blog.csdn.net/lonely_fireworks/article/details/7849643)[http://blog.csdn.net/lonely_fireworks/article/details/7849643]
Android里面提供了一些方法可以获取View在屏幕中的位置。

getLocationOnScreen ，计算该视图在全局坐标系中的x，y值，获取在当前屏幕内的绝对坐标（该值从屏幕顶端算起，包括了通知栏高度）。

getLocationInWindow ，计算该视图在它所在的widnow的坐标x，y值。

getLeft , getTop, getBottom, getRight,  这一组是获取相对在它父亲布局里的坐标。

注意：如果在Activity的OnCreate()事件输出那些参数，是全为0，要等UI控件都加载完了才能获取到这些数据。

		button2.setOnClickListener(new OnClickListener() {  

				@Override  
				public void onClick(View v) {  
						int[] location = new int[2];  
						v.getLocationOnScreen(location);  
						x = location[0];  
						y = location[1];  
						Log.d("test", "Screenx--->" + x + "  " + "Screeny--->" + y);  
						v.getLocationInWindow(location);  
						x = location[0];  
						y = location[1];  
						Log.d("test", "Window--->" + x + "  " + "Window--->" + y);  
									Log.d("test", "left:" + v.getLeft());  
									Log.d("test", "right:" + v.getRight());  
									Log.d("test", "Top:" + v.getTop());  
									Log.d("test", "Bottom:" + v.getBottom());  
									Log.d("test", "Width:"+v.getWidth());  
									Log.d("test", "Height:"+v.getHeight());  
				}  
});

输出结果

08-09 23:57:10.883: D/test(4723): Screenx--->0  Screeny--->148
08-09 23:57:10.922: D/test(4723): Window--->0  Window--->148
08-09 23:57:10.922: D/test(4723): left:0
08-09 23:57:10.922: D/test(4723): right:480
08-09 23:57:10.922: D/test(4723): Top:72
08-09 23:57:10.922: D/test(4723): Bottom:144
08-09 23:57:10.922: D/test(4723): Width:480
08-09 23:57:10.922: D/test(4723): Height:72
