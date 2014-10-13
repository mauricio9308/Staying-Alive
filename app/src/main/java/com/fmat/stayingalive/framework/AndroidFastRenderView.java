package com.fmat.stayingalive.framework;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.fmat.stayingalive.activity.BuildConfig;

/**
 * Created by Kevin on 9/24/2014.
 */
public class AndroidFastRenderView extends SurfaceView implements Runnable {

    private AndroidGame mGame;
    private Bitmap mFrameBuffer;
    private Thread mRenderThread;
    private SurfaceHolder mSurfaceHolder;
    private volatile boolean mIsRunning = false;



    public AndroidFastRenderView(AndroidGame game, Bitmap frameBuffer) {
        super(game);
        mGame = game;
        mFrameBuffer = frameBuffer;
        mSurfaceHolder = getHolder();
    }


    @Override
    public void run() {
        Rect rect = new Rect();
        long startTime = System.nanoTime();
        while(mIsRunning){
            if(!mSurfaceHolder.getSurface().isValid()){
                continue;
            }
            float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
            startTime = System.nanoTime();

            mGame.getCurrentScreen().update(deltaTime);
            mGame.getCurrentScreen().present(deltaTime);

            Canvas canvas = mSurfaceHolder.lockCanvas();
            canvas.getClipBounds(rect);
            canvas.drawBitmap(mFrameBuffer, null, rect, null);
            mSurfaceHolder.unlockCanvasAndPost(canvas);
        }
    }

    public void resume(){
        mIsRunning = true;
        mRenderThread = new Thread( AndroidFastRenderView.this /* Runnable*/ );
        mRenderThread.start();
    }

    public void pause(){
        mIsRunning = false;
        try{
            mRenderThread.join();
        } catch (InterruptedException e){
            if(BuildConfig.DEBUG){
                e.printStackTrace();
            }
        }
    }
}
