# Android WindowManager 监听返回键及home键

### from [http://www.cnblogs.com/zgz345/p/3785657.html](http://www.cnblogs.com/zgz345/p/3785657.html)

一、监听home键盘，Android Home键系统负责监听，捕获后系统自动处理。有时候，我们需要监听home键处理自己的逻辑，监听方法如下:
复制代码

    /**
     * 监听home键广播
     */
    private final static BroadcastReceiver homeListenerReceiver = new BroadcastReceiver() {
        final String SYSTEM_DIALOG_REASON_KEY = "reason";

        final String SYSTEM_DIALOG_REASON_HOME_KEY = "homekey";

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if (action.equals(Intent.ACTION_CLOSE_SYSTEM_DIALOGS)) {
                String reason = intent.getStringExtra(SYSTEM_DIALOG_REASON_KEY);
                if (reason != null && reason.equals(SYSTEM_DIALOG_REASON_HOME_KEY)) {
                    // 处理自己的逻辑
                }
            }
        }
    };

	在创建View时注册Receiver
	IntentFilter homeFilter = new IntentFilter(Intent.ACTION_CLOSE_SYSTEM_DIALOGS);
	context.registerReceiver(homeListenerReceiver, homeFilter);

	在View消失时反注册Receiver
	// 反注册home监听
	if (homeListenerReceiver != null) {
	   context.unregisterReceiver(homeListenerReceiver);
	}

复制代码

二、WindowManager view中监听返回及menu键
复制代码

	重写dispatchKeyEvent
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        switch (event.getKeyCode()) {
            case KeyEvent.KEYCODE_BACK:
            case KeyEvent.KEYCODE_MENU:
                // 处理自己的逻辑break;
            default:
                break;
        }
        return super.dispatchKeyEvent(event);
    }

