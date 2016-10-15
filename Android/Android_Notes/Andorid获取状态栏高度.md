# Andorid获取状态栏高度 

### from [http://www.2cto.com/kf/201501/374049.html](http://www.2cto.com/kf/201501/374049.html)


在应用开发中，有时我们需要用代码计算布局的高度，可能需要减去状态栏(status bar)的高度。状态栏高度定义在Android系统尺寸资源中status_bar_height,但这并不是公开可直接使用的，例如像通常使用系统资源那样android.R.dimen.status_bar_height。但是系统给我们提供了一个Resource类，通过这个类我们可以获取资源文件。下边是在Activity中获取的方法

 public int getStatusBarHeight() {
  int result = 0;
  int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
  if (resourceId > 0) {
      result = getResources().getDimensionPixelSize(resourceId);
  }
  return result;
}



这里还有另外一种方法，大家都知道Android的所有资源都会有惟一标识在R类中作为引用。我们也可以通过反射获取R类的实例域，代码如下

    /**
     * 获得状态栏的高度
     * 
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

\

我们可以看到得到的结果是一样的。当然，获取状态栏的高度方法是不是就只有以上两种呢，当然不是，下边再介绍一种获取状态栏高度的方法，不过不推荐使用，因为这个方法依赖于WMS(窗口管理服务的回调)。

    Rect rectangle= new Rect();
    Window window= getWindow();
    window.getDecorView().getWindowVisibleDisplayFrame(rectangle);
    int statusBarHeight= rectangle.top;