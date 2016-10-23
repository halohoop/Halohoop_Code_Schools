# Android画笔Paint的Xfermode效果总结

### from [http://www.cnblogs.com/SusieBlog/p/5765832.html](http://www.cnblogs.com/SusieBlog/p/5765832.html)

PorterDuff.Mode有很多模式：

android.graphics.PorterDuff.Mode.SRC:只绘制源图像

android.graphics.PorterDuff.Mode.DST:只绘制目标图像

android.graphics.PorterDuff.Mode.DST_OVER:在源图像的顶部绘制目标图像

android.graphics.PorterDuff.Mode.DST_IN:只在源图像和目标图像相交的地方绘制目标图像

android.graphics.PorterDuff.Mode.DST_OUT:只在源图像和目标图像不相交的地方绘制目标图像

android.graphics.PorterDuff.Mode.DST_ATOP:在源图像和目标图像相交的地方绘制目标图像，在不相交的地方绘制源图像

android.graphics.PorterDuff.Mode.SRC_OVER:在目标图像的顶部绘制源图像

android.graphics.PorterDuff.Mode.SRC_IN:只在源图像和目标图像相交的地方绘制源图像

android.graphics.PorterDuff.Mode.SRC_OUT:只在源图像和目标图像不相交的地方绘制源图像

android.graphics.PorterDuff.Mode.SRC_ATOP:在源图像和目标图像相交的地方绘制源图像，在不相交的地方绘制目标图像

android.graphics.PorterDuff.Mode.XOR:在源图像和目标图像重叠之外的任何地方绘制他们，而在不重叠的地方不绘制任何内容

android.graphics.PorterDuff.Mode.LIGHTEN:获得每个位置上两幅图像中最亮的像素并显示

android.graphics.PorterDuff.Mode.DARKEN:获得每个位置上两幅图像中最暗的像素并显示

android.graphics.PorterDuff.Mode.MULTIPLY:将每个位置的两个像素相乘，除以255，然后使用该值创建一个新的像素进行显示。结果颜色=顶部颜色*底部颜色/255

android.graphics.PorterDuff.Mode.SCREEN:反转每个颜色，执行相同的操作（将他们相乘并除以255），然后再次反转。结果颜色=255-(((255-顶部颜色)*(255-底部颜色))/255)

可以每个模式都设置玩玩嘛，看看每个属性是什么效果~~~反正我是不会去玩这么无聊的东西的。我懒啊~~~O(∩_∩)O~~