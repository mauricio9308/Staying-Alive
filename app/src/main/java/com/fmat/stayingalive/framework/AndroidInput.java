package com.fmat.stayingalive.framework;

import android.content.Context;
import android.view.View;

import com.fmat.stayingalive.interfaces.Input;

import java.util.List;

/**
 * Created by Kevin on 29/09/2014.
 */
public class AndroidInput implements Input {

    AccelerometerHandler accelerometerHandler;
    KeyboardHandler keyboardHandler;


    public AndroidInput(Context context, View view) {
        accelerometerHandler = new AccelerometerHandler(context);
        keyboardHandler = new KeyboardHandler(view);
    }

    @Override
    public boolean isKeyPressed(int keyCode) {
        return keyboardHandler.isKeyPressed(keyCode);
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
