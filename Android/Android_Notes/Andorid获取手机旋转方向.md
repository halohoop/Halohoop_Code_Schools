# Andorid获取手机旋转方向
通过方法：

        switch (mDisplay.getRotation()) {
                case Surface.ROTATION_0://手机处于正常状态//物理键在下边，手机听筒在上边
                        break;
                case Surface.ROTATION_90://物理键在右边，手机听筒在左边
                        break;
                case Surface.ROTATION_180://物理键在上边，手机听筒在下边
                        break;
                case Surface.ROTATION_270://物理键在左边，手机听筒在右边
                        break;
        }
