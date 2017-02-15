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



## 011.Android 官方demo，TextView设置富文本，简单一眼就懂.

        // BEGIN_INCLUDE(text_auto_linkify)
        /*
         *  text_auto_linkify shows the android:autoLink property, which
         *  automatically linkifies things like URLs and phone numbers
         *  found in the text. No java code is needed to make this
         *  work.
         *  This can also be enabled programmatically by calling
         *  .setAutoLinkMask(Linkify.ALL) before the text is set on the TextView.
         *
         *  See android.text.util.Linkify for other options, for example only
         *  auto-linking email addresses or phone numbers
         */
        // END_INCLUDE(text_auto_linkify)

        // BEGIN_INCLUDE(text_html_resource)
        /*
         * text_html_resource has links specified by putting anchor tags (<a>) in the string
         * resource. By default these links will appear but not
         * respond to user input. To make them active, you need to
         * call setMovementMethod() on the TextView object.
         */
        TextView textViewResource = (TextView) findViewById(R.id.text_html_resource);
        textViewResource.setText(
                Html.fromHtml(getResources().getString(R.string.link_text_manual)));
        textViewResource.setMovementMethod(LinkMovementMethod.getInstance());
        // END_INCLUDE(text_html_resource)

        // BEGIN_INCLUDE(text_html_program)
        /*
         * text_html_program shows creating text with links from HTML in the Java
         * code, rather than from a string resource. Note that for a
         * fixed string, using a (localizable) resource as shown above
         * is usually a better way to go; this example is intended to
         * illustrate how you might display text that came from a
         * dynamic source (eg, the network).
         */
        TextView textViewHtml = (TextView) findViewById(R.id.text_html_program);
        textViewHtml.setText(
                Html.fromHtml(
                        "<b>text_html_program: Constructed from HTML programmatically.</b>"
                                + "  Text with a <a href=\"http://www.google.com\">link</a> "
                                + "created in the Java source code using HTML."));
        textViewHtml.setMovementMethod(LinkMovementMethod.getInstance());
        // END_INCLUDE(text_html_program)

        // BEGIN_INCLUDE(text_spannable)
        /*
         * text_spannable illustrates constructing a styled string containing a
         * link without using HTML at all. Again, for a fixed string
         * you should probably be using a string resource, not a
         * hardcoded value.
         */
        SpannableString ss = new SpannableString(
                "text_spannable: Manually created spans. Click here to dial the phone.");

        /*
         * Make the first 38 characters bold by applying a StyleSpan with bold typeface.
         *
         * Characters 45 to 49 (the word "here") is made clickable by applying a URLSpan
         * pointing to a telephone number. Clicking it opens the "tel:" URL that starts the dialer.
         *
         * The SPAN_EXCLUSIVE_EXCLUSIVE flag defines this span as exclusive, which means
         * that it will not expand to include text inserted on either side of this span.
         */
        ss.setSpan(new StyleSpan(Typeface.BOLD), 0, 39,//include front not include back
                Spanned.SPAN_INCLUSIVE_INCLUSIVE);
        ss.setSpan(new URLSpan("tel:4155551212"), 40 + 6, 40 + 10,
                Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        TextView textViewSpan = (TextView) findViewById(R.id.text_spannable);
        textViewSpan.setText(ss);

        /*
         * Set the movement method to move between links in this TextView.
         * This means that the user traverses through links in this TextView, automatically
         * handling appropriate scrolling and key commands.
         */
        textViewSpan.setMovementMethod(LinkMovementMethod.getInstance());
        // END_INCLUDE(text_spannable)

## 012.android.support.v4.widget.DrawerLayout使用方法
1.定义一个xml布局文件DrawerLayout，以DrawerLayout作为根布局；
2.In activity,fvb it，然后按照以下步骤：
2.1.对想要变成拖出的控件设置：android:layout_gravity="start"
2.2.设置阴影，资源一般使用点九图

        // set a custom shadow that overlays the main content when the drawer opens
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);

2.3.设置ActionBar支持drawer

        // enable ActionBar app icon to behave as action to toggle nav drawer
        getActionBar().setDisplayHomeAsUpEnabled(true);
        getActionBar().setHomeButtonEnabled(true);

2.4.新建drawer开关的回调

        // ActionBarDrawerToggle ties together the the proper interactions
        // between the sliding drawer and the action bar app icon
        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                R.drawable.ic_drawer,  /* nav drawer image to replace 'Up' caret */
                R.string.drawer_open,  /* "open drawer" description for accessibility */
                R.string.drawer_close  /* "close drawer" description for accessibility */
        ) {
            public void onDrawerClosed(View view) {
                getActionBar().setTitle(mTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerOpened(View drawerView) {
                getActionBar().setTitle(mDrawerTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };

2.5.设置回调

    mDrawerLayout.setDrawerListener(mDrawerToggle);
    //mDrawerLayout.addDrawerListener(mDrawerToggle);

2.6.通过api---DrawerLayout.isDrawerOpen获取是否打开；





