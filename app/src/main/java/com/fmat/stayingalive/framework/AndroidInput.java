package com.fmat.stayingalive.framework;

import android.content.Context;

import com.fmat.stayingalive.interfaces.Input;

import java.util.List;

/**
 * Created by Kevin on 29/09/2014.
 */
public class AndroidInput implements Input {

    AccelerometerHandler accelerometerHandler;

    public AndroidInput(Context context) {
        accelerometerHandler = new AccelerometerHandler(context);
    }

    @Override
    public boolean isKeyPressed(int keyCode) {
        return false;
    }

    @Override
    public boolean isTouchDown(int pointer) {
        return false;
    }

    @Override
    public int getTouchX(int pointer) {
        return 0;
    }

    @Override
    public int getTouchY(int pointer) {
        return 0;
    }

    @Override
    public float getAccelX() {
        return accelerometerHandler.accelX;
    }

    @Override
    public float getAccelY() {
        return accelerometerHandler.accelY;
    }

    @Override
    public float getAccelZ() {
        return accelerometerHandler.accelZ;
    }

    @Override
    public List<KeyEvent> getKeyEvents() {
        return null;
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        return null;
    }
}
