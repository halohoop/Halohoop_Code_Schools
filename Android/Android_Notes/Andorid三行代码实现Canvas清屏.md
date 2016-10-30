# Andorid三行代码实现Canvas清屏

### from [http://blog.csdn.net/yanzi1225627/article/details/8236309](http://blog.csdn.net/yanzi1225627/article/details/8236309)

	Paint paint = new Paint();  
    paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));  
    canvas.drawPaint(paint);
	//canvas对应的图像已经清空了