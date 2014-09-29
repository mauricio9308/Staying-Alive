package com.fmat.stayingalive.framework;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;

import com.fmat.stayingalive.interfaces.Audio;
import com.fmat.stayingalive.interfaces.FileIO;
import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.Input;
import com.fmat.stayingalive.interfaces.Screen;

/**
 * Created by Kevin on 9/24/2014.
 */
public abstract class AndroidGame extends ActionBarActivity implements Game  {

    AndroidFastRenderView mRenderView;
    AndroidGraphics mGraphics;
    PowerManager.WakeLock mWakeLock;
    Screen mScreen;
    AndroidFileIO mFileIO;
    AndroidAudio mAudio;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FULLSCREEEEEEEEEEEEEN
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //framebuffer
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        int frameBufferWidth = isLandscape? 480 : 320;
        int frameBufferHeight = isLandscape? 320 : 480;
        Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth, frameBufferHeight, Bitmap.Config.RGB_565);

        //renderview
        mRenderView = new AndroidFastRenderView(this, frameBuffer);

        //mGraphics
        mGraphics = new AndroidGraphics(getAssets(), frameBuffer);

        //fileio
        mFileIO = new AndroidFileIO(getAssets());

        //audio
        mAudio = new AndroidAudio(getAssets());

        //input


        //screen
        mScreen = getStartScreen();

        setContentView(mRenderView);

        //no lock
        PowerManager powerManager = (PowerManager) getSystemService(Context.POWER_SERVICE);
        mWakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "Lock");

    }

    @Override
    public Input getInput() {
        return null;
    }

    @Override
    public FileIO getFileIO() {
        return null;
    }

    @Override
    public Graphics getGraphics() {
        return mGraphics;
    }

    @Override
    public Audio getAudio() {
        return null;
    }

    @Override
    public void setScreen(Screen screen) {
        if (screen == null) {
            return;
        }
        mScreen.pause();
        mScreen.resume();
        screen.resume();
        screen.update(0);
        mScreen = screen;
    }

    @Override
    public Screen getCurrentScreen() {
        return mScreen;
    }

    public void onPause() {
        super.onPause();
        mWakeLock.release();
        mScreen.pause();
        mRenderView.pause();
    }


    public void onResume() {
        super.onResume();
        mWakeLock.acquire();
        mScreen.resume();
        mRenderView.resume();
    }

}
