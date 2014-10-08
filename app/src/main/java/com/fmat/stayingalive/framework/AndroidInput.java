package com.fmat.stayingalive.framework;

import android.content.Context;
import android.view.View;

import com.fmat.stayingalive.interfaces.Input;
import com.fmat.stayingalive.interfaces.TouchEvent;

import java.util.List;

/**
 * Created by Kevin on 29/09/2014.
 */
public class AndroidInput implements Input {

    private AccelerometerHandler mAccelerometerHandler;
    private KeyboardHandler mKeyboardHandler;
    private MultiTouchHandler mTouchHandler;

    public AndroidInput(Context context, View view, float x, float y) {
        mAccelerometerHandler = new AccelerometerHandler(context);
        mKeyboardHandler = new KeyboardHandler(view);
        mTouchHandler = new MultiTouchHandler( view, x, y );
    }

    @Override
    public boolean isKeyPressed(int keyCode) {
        return mKeyboardHandler.isKeyPressed(keyCode);
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return mTouchHandler.isTouchDown( pointer );
    }

    @Override
    public int getTouchX(int pointer) {
        return mTouchHandler.getTouchX(pointer);
    }

    @Override
    public int getTouchY(int pointer) {
        return mTouchHandler.getTouchY( pointer );
    }

    @Override
    public float getAccelX() {
        return mAccelerometerHandler.accelX;
    }

    @Override
    public float getAccelY() {
        return mAccelerometerHandler.accelY;
    }

    @Override
    public float getAccelZ() {
        return mAccelerometerHandler.accelZ;
    }

    @Override
    public List<KeyEvent> getKeyEvents() {
        return null;
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        return mTouchHandler.getTouchEvents();
    }
}
