# Android 性能分析工具dumpsys的使用
##
    Android提供的dumpsys工具可以用于查看感兴趣的系统服务信息与状态，手机连接电脑后可以直接命令行执行adb shell dumpsys 查看所有支持的Service但是这样输出的太多，可以通过dumpsys | grep "DUMP OF SERVICE" 仅显示主要的Service的信息
## 一、列出dumpsys所有支持命令

    > adb shell  
    $ dumpsys | grep "DUMP OF SERVICE"  


以下是在三星S3手机上的输出信息

    C:\Windows\System32>adb shell  
    shell@m:/ $ dumpsys | grep "DUMP OF SERVICE"  
    dumpsys | grep "DUMP OF SERVICE"  
    DUMP OF SERVICE AtCmdFwd:  
    DUMP OF SERVICE CustomFrequencyManagerService:  
    DUMP OF SERVICE DirEncryptService:  
    DUMP OF SERVICE Exynos.HWCService:  
    DUMP OF SERVICE Exynos.IPService:  
    DUMP OF SERVICE FMPlayer:  
    DUMP OF SERVICE LEDService:  
    DUMP OF SERVICE SecTVOutService:  
    DUMP OF SERVICE SurfaceFlinger:  
    DUMP OF SERVICE TvoutService_C:  
    DUMP OF SERVICE accessibility:  
    DUMP OF SERVICE account:  
    DUMP OF SERVICE activity:  
    DUMP OF SERVICE alarm:  
    DUMP OF SERVICE android.security.keystore:  
    DUMP OF SERVICE application_policy:  
    DUMP OF SERVICE appops:  
    DUMP OF SERVICE appwidget:  
    DUMP OF SERVICE audio:  
    DUMP OF SERVICE backup:  
    DUMP OF SERVICE battery:  
    DUMP OF SERVICE batteryinfo:  
    DUMP OF SERVICE bluetooth_manager:  
    DUMP OF SERVICE bluetooth_secure_mode_manager:  
    DUMP OF SERVICE clipboard:  
    DUMP OF SERVICE clipboardEx:  
    DUMP OF SERVICE commontime_management:  
    DUMP OF SERVICE connectivity:  
    DUMP OF SERVICE container_service:  
    DUMP OF SERVICE content:  
    DUMP OF SERVICE country_detector:  
    DUMP OF SERVICE cpuinfo:  
    DUMP OF SERVICE dbinfo:  
    DUMP OF SERVICE device_policy:  
    DUMP OF SERVICE devicestoragemonitor:  
    DUMP OF SERVICE diskstats:  
    DUMP OF SERVICE display:  
    DUMP OF SERVICE dreams:  
    DUMP OF SERVICE drm.drmManager:  
    DUMP OF SERVICE dropbox:  
    DUMP OF SERVICE edmnativehelper:  
    DUMP OF SERVICE enterprise_license_policy:  
    DUMP OF SERVICE enterprise_policy:  
    DUMP OF SERVICE entropy:  
    DUMP OF SERVICE gfxinfo:  
    DUMP OF SERVICE hardware:  
    DUMP OF SERVICE harmony_eas_service:  
    DUMP OF SERVICE input:  
    DUMP OF SERVICE input_method:  
    DUMP OF SERVICE iphonesubinfo:  
    DUMP OF SERVICE isms:  
    DUMP OF SERVICE license_log_service:  
    DUMP OF SERVICE location:  
    DUMP OF SERVICE lock_settings:  
    DUMP OF SERVICE log_manager_service:  
    DUMP OF SERVICE mdm.remotedesktop:  
    DUMP OF SERVICE media.audio_flinger:  
    DUMP OF SERVICE media.audio_policy:  
    DUMP OF SERVICE media.camera:  
    DUMP OF SERVICE media.player:  
    DUMP OF SERVICE meminfo:  
    DUMP OF SERVICE motion_recognition:  
    DUMP OF SERVICE mount:  
    DUMP OF SERVICE multiwindow:  
    DUMP OF SERVICE netpolicy:  
    DUMP OF SERVICE netstats:  
    DUMP OF SERVICE network_management:  
    DUMP OF SERVICE nfc:  
    DUMP OF SERVICE nfccontroller:  
    DUMP OF SERVICE notification:  
    DUMP OF SERVICE package:  
    DUMP OF SERVICE permission:  
    DUMP OF SERVICE phone:  
    DUMP OF SERVICE phone_restriction_policy:  
    DUMP OF SERVICE phoneext:  
    DUMP OF SERVICE power:  
    DUMP OF SERVICE remoteinjection:  
    DUMP OF SERVICE samplingprofiler:  
    DUMP OF SERVICE samsung.smartfaceservice:  
    DUMP OF SERVICE scheduling_policy:  
    DUMP OF SERVICE search:  
    DUMP OF SERVICE sec_analytics:  
    DUMP OF SERVICE secontroller:  
    DUMP OF SERVICE sensorservice:  
    DUMP OF SERVICE serial:  
    DUMP OF SERVICE servicediscovery:  
    DUMP OF SERVICE simphonebook:  
    DUMP OF SERVICE sip:  
    DUMP OF SERVICE statusbar:  
    DUMP OF SERVICE telephony.registry:  
    DUMP OF SERVICE textservices:  
    DUMP OF SERVICE tvoutservice:  
    DUMP OF SERVICE uimode:  
    DUMP OF SERVICE updatelock:  
    DUMP OF SERVICE usagestats:  
    DUMP OF SERVICE usb:  
    DUMP OF SERVICE user:  
    DUMP OF SERVICE vibrator:  
    DUMP OF SERVICE voip:  
    DUMP OF SERVICE wallpaper:  
    DUMP OF SERVICE wfd:  
    DUMP OF SERVICE wifi:  
    DUMP OF SERVICE wifi_policy:  
    DUMP OF SERVICE wifip2p:  
    DUMP OF SERVICE window:  


## 二、具体命令如何查看帮助
从上面可以看出Service非常多，“DUMP OF SERVICE”关键字后面的单词都可以直接通过 dumpsys + 单词 查看相关信息，具体每一个如何使用有一种通用的查看帮助的办法。

查看每一个命令的使用帮助，以下以meminfo 为例演示：

    shell@m:/ $ dumpsys meminfo -h  
    dumpsys meminfo -h  
    meminfo dump options: [-a] [--oom] [process]  
      -a: include all available information for each process.  
      --oom: only show processes organized by oom adj.  
    If [process] is specified it can be the name or  
    pid of a specific process to dump.  


## 三、一些关键命令解释

    名字  功能
    account     显示accounts信息
    activity    显示所有的activities的信息
    cpuinfo     显示CPU信息
    window  显示键盘，窗口和它们的关系
    wifi    显示wifi信息
    batteryinfo $package_name   电量信息及CPU 使用时长
    package packagename     获取安装包信息
    usagestats  每个界面启动的时间
    statusbar   显示状态栏相关的信息
    meminfo     
    内存信息（meminfo $package_name or $pid 
    使用程序的包名或者进程id显示内存信息）
    diskstats   磁盘相关信息
    battery     电池信息
    alarm   显示Alarm信息
         

### activity  - [使用adb shell dumpsys检测Android的Activity任务栈](http://blog.iderzheng.com/debug-activity-task-stack-with-adb-shell-dumpsys/)

### window - [通过adb shell dumpsys命令获取当前应用的component](http://blog.csdn.net/gb112211/article/details/33073191)

### statusbar - [找出广告通知属于哪个应用](http://lmbj.net/blog/android-shell-dumpsys/)

#### dumpsys statusbar | grep notification=Notification 




## 四、参考资料
[Dumpsys](https://source.android.com/devices/tech/input/dumpsys.html) （官方文档）

[How to discover memory usage of my application in Android](http://stackoverflow.com/questions/2298208/how-to-discover-memory-usage-of-my-application-in-android#2299813)

[android中dumpsys函数介绍与使用 （代码分析）](http://su1216.iteye.com/blog/1729648)

来自：[http://blog.csdn.net/androiddevelop/article/details/37689339](http://blog.csdn.net/androiddevelop/article/details/37689339)