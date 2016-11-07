# Andorid获取是否处于锁屏状态
通过方法：

        KeyguardManager mKeyguardManager = (KeyguardManager)mContext.getSystemService(Context.KEYGUARD_SERVICE);   
        if (mKeyguardManager.inKeyguardRestrictedInputMode()) {
          //keyguard is on
          ...
        }
