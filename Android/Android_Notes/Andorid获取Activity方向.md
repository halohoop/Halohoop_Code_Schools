# Andorid获取Activity方向
通过方法：

    	Configuration newConfig = getResources().getConfiguration();  
            if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){  
                //横屏  
            }else if(newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){  
                //竖屏  
            }else if(newConfig.hardKeyboardHidden == Configuration.KEYBOARDHIDDEN_NO){  
                //键盘没关闭。屏幕方向为横屏  
            }else if(newConfig.hardKeyboardHidden == Configuration.KEYBOARDHIDDEN_YES){  
                //键盘关闭。屏幕方向为竖屏  
            }  

通过方法获取固定方向的方式：

	Activity.getRequestedOrientation();

能够获取到当前Activity指定的水平还是竖直的方式，应用实例就是，比如说，我们长截屏的时候需要获取到这个方向的方式，
判断当前正处在的方向，先固定下来，等待截完屏再设置回去，有以下值：
	
screenOrientation 用来指定Activity的在设备上显示的方向，每个值代表如下含义：

	"unspecified" 	默认值 由系统来判断显示方向.判定的策略是和设备相关的，所以不同的设备会有不同的显示方向.
	"landscape" 	横屏显示（宽比高要长）
	"portrait" 	竖屏显示(高比宽要长)
	"user" 	用户当前首选的方向
	"behind" 	和该Activity下面的那个Activity的方向一致(在Activity堆栈中的)
	"sensor" 	有物理的感应器来决定。如果用户旋转设备这屏幕会横竖屏切换。
	"nosensor" 	忽略物理感应器，这样就不会随着用户旋转设备而更改了 （ "unspecified"设置除外 ）。

我们可以通过方法：

	Activity.setRequestedOrientation(int);

来设置固定方向的方式：

    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);//
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT);
	//等，