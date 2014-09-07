package com.fmat.stayingalive.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

public class SurfaceViewTestActivity extends ActionBarActivity {

    private FastRenderView mRenderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature( Window.FEATURE_NO_TITLE);
        getWindow().setFlags( WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mRenderView = new FastRenderView( SurfaceViewTestActivity.this /* context */);
        setContentView( mRenderView );
    }

    @Override
    public void onResume(){
        super.onResume();
        mRenderView.resume();
    }

    @Override
    public void onPause(){
        super.onPause();
        mRenderView.pause();
    }

    private class FastRenderView extends SurfaceView implements Runnable {

        private Thread mRenderThread;
        private SurfaceHolder mSurfaceHolder;
        private volatile boolean mIsRunning = false;

        public FastRenderView(Context context) {
            super(context);
            mSurfaceHolder = getHolder();
        }

        public FastRenderView(Context context, AttributeSet attrs) {
            super(context, attrs);
            mSurfaceHolder = getHolder();
        }

        public FastRenderView(Context context, AttributeSet attrs, int defStyle) {
            super(context, attrs, defStyle);
            mSurfaceHolder = getHolder();
        }

        @Override
        public void run() {
            while( mIsRunning ){
                if( !mSurfaceHolder.getSurface().isValid() ){
                    continue;
                }else{
                    Canvas canvas = mSurfaceHolder.lockCanvas();
                    canvas.drawRGB( 255, 0, 0 );
                    mSurfaceHolder.unlockCanvasAndPost( canvas );
                }
            }
        }

        public void resume(){
            mIsRunning = true;
            mRenderThread = new Thread( FastRenderView.this /* Runnable*/ );
            mRenderThread.start();
        }

        public void pause(){
            mIsRunning = false;

            try{
                mRenderThread.join();
            }catch ( InterruptedException e ){
                if( BuildConfig.DEBUG ){
                    e.printStackTrace();
                }
            }
        }
    }
}
