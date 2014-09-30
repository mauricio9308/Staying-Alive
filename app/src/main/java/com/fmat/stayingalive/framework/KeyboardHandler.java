package com.fmat.stayingalive.framework;

import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnKeyListener;

import com.fmat.stayingalive.interfaces.Input.KeyEvent;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 9/24/2014.
 */
public class KeyboardHandler implements OnKeyListener {

    boolean[] pressedKeys = new boolean[128];
    Pool<KeyEvent> keyEventPool;
    List<KeyEvent> keyEventsBuffer = new ArrayList<KeyEvent>();
    List<KeyEvent> keyEvents = new ArrayList<KeyEvent>();

    public KeyboardHandler(View view) {
        Pool.PoolObjectFactory<KeyEvent> factory = new Pool.PoolObjectFactory<KeyEvent>() {
            @Override
            public KeyEvent createObject() {
                return new KeyEvent();
            }
        };
        keyEventPool = new Pool<KeyEvent>(factory, 100);
        view.setOnKeyListener(this);
        view.setFocusableInTouchMode(true);
        view.requestFocus();

    }

    @Override
    public boolean onKey(View view, int keyCode, android.view.KeyEvent event) {
        if (event.getAction() == android.view.KeyEvent.ACTION_MULTIPLE) {
            return false;
        }
        synchronized (this) {
            KeyEvent keyEvent = keyEventPool.newObject();
            keyEvent.keyCode = keyCode;
            keyEvent.keyChar = (char) event.getUnicodeChar();
            if (event.getAction() == android.view.KeyEvent.ACTION_DOWN) {
                keyEvent.type = keyEvent.KEY_DOWN;
                if (keyCode > 0 && keyCode < 127) {
                    pressedKeys[keyCode] = true;
                }
            }
            if (event.getAction() == android.view.KeyEvent.ACTION_UP) {
                keyEvent.type = keyEvent.KEY_UP;
                if (keyCode > 0 && keyCode < 127) {
                    pressedKeys[keyCode] = false;
                }
            }
            keyEventsBuffer.add(keyEvent);
        }
        return false;
    }

    public boolean isKeyPressed(int keyCode) {
        if (keyCode < 0 || keyCode > 127) {
            return false;
        }
        return pressedKeys[keyCode];
    }

    public List<KeyEvent> getKeyEvents() {
        synchronized (this) {
            int length = keyEvents.size();
            for (int i = 0; i < length; i++) {
                keyEventPool.free(keyEvents.get(i));
            }
            keyEvents.clear();
            keyEvents.addAll(keyEventsBuffer);
            keyEventsBuffer.clear();
            return keyEvents;
        }
    }

}
