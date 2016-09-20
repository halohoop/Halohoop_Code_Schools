package com.tplink.tpcompass.widgets;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Pooholah on 2016/9/15.
 */

public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    public MySurfaceView(Context context) {
        this(context, null);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MySurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
        initAttrs(attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        getHolder().addCallback(this);
    }

    private void initAttrs(AttributeSet attrs) {
        //TODO deal with attrs
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        //surfaceview templete
        Canvas canvas = surfaceHolder.lockCanvas();
        draw(canvas);//final to onDraw
        surfaceHolder.unlockCanvasAndPost(canvas);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }
}
