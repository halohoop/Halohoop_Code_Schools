# Andorid跳转到开启GPS的设置界面
通过方法：

        // 转到手机设置界面，用户设置GPS
        Intent intent = new Intent(
                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivityForResult(intent, 0); // 设置完成后返回到原来的界面
