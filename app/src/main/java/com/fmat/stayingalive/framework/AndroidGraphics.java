package com.fmat.stayingalive.framework;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.fmat.stayingalive.activity.BuildConfig;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.Pixmap;

/**
 * Created by mauriciolara on 9/22/14.
 */
public class AndroidGraphics extends SurfaceView implements Graphics, Runnable{

    private Thread mRenderThread;
    private SurfaceHolder mSurfaceHolder;
    private volatile boolean mIsRunning = false;

    public AndroidGraphics(Context context) {
        super(context);
        mSurfaceHolder = getHolder();
    }

    public AndroidGraphics(Context context, AttributeSet attrs) {
        super(context, attrs);
        mSurfaceHolder = getHolder();
    }

    public AndroidGraphics(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mSurfaceHolder = getHolder();
    }


    @Override
    public void run() {
        while (mIsRunning) {
            if (!mSurfaceHolder.getSurface().isValid()) {
                continue;
            } else {
                Canvas canvas = mSurfaceHolder.lockCanvas();
                canvas.drawRGB(255, 0, 0);
                mSurfaceHolder.unlockCanvasAndPost(canvas);
            }
        }
    }

    /* Control methods */

    public void resume() {
        mIsRunning = true;
        mRenderThread = new Thread( AndroidGraphics.this /* Runnable*/);
        mRenderThread.start();
    }

    public void pause() {
        mIsRunning = false;

        try {
            mRenderThread.join();
        } catch (InterruptedException e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }
        }
    }


    @Override
    public Pixmap newPixmap(String fileName, PixmapFormat format) {
        return null;
    }

    @Override
    public void clear(int color) {

    }

    @Override
    public void drawPixel(int x, int y, int color) {

    }

    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {

    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) {

    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y, int srcX, int srcY, int srcWidth, int srcHeight) {

    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y) {

    }

}
