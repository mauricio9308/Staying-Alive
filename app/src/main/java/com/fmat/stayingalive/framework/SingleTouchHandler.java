package com.fmat.stayingalive.framework;

import android.view.MotionEvent;
import android.view.View;

import com.fmat.stayingalive.interfaces.TouchEvent;
import com.fmat.stayingalive.interfaces.TouchHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mauriciolara on 9/24/14.
 */
public class SingleTouchHandler implements TouchHandler, View.OnTouchListener {
    private boolean mIsTouched;
    private int mTouchX;
    private int mTouchY;
    private Pool<TouchEvent> mTouchEventPool;
    private List<TouchEvent> mTouchEvents = new ArrayList<TouchEvent>();
    private List<TouchEvent> mTouchEventsBuffer = new ArrayList<TouchEvent>();
    private float mScaleX;
    private float mScaleY;

    public SingleTouchHandler(View view, float scaleX, float scaleY) {
        Pool.PoolObjectFactory<TouchEvent> factory = new Pool.PoolObjectFactory<TouchEvent>() {
            @Override
            public TouchEvent createObject() {
                return new TouchEvent();
            }
        };

        mTouchEventPool = new Pool<TouchEvent>(factory, 100);
        view.setOnTouchListener(SingleTouchHandler.this /* OnTouchListener */);

        mScaleX = scaleX;
        mScaleY = scaleY;
    }

    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (SingleTouchHandler.this) {
            if (pointer == 0) {
                return mIsTouched;
            } else {
                return false;
            }
        }
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (SingleTouchHandler.this) {
            return mTouchX;
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (SingleTouchHandler.this) {
            return mTouchX;
        }
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        synchronized (SingleTouchHandler.this) {
            int len = mTouchEvents.size();


            for (int i = 0; i < len; i++)
                mTouchEventPool.free(mTouchEvents.get(i));
            mTouchEvents.clear();
            mTouchEvents.addAll(mTouchEventsBuffer);
            mTouchEventsBuffer.clear();
            return mTouchEvents;
        }
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        synchronized (this) {
            TouchEvent touchEvent = mTouchEventPool.newObject();
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchEvent.type = TouchEvent.TOUCH_DOWN;
                    mIsTouched = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    touchEvent.type = TouchEvent.TOUCH_DRAG;
                    mIsTouched = true;
                    break;
                case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    touchEvent.type = TouchEvent.TOUCH_UP;
                    mIsTouched = false;
                    break;
            }
            touchEvent.x = mTouchX = (int) (event.getX() * mScaleX);
            touchEvent.y = mTouchY = (int) (event.getY() * mScaleY);
            mTouchEventsBuffer.add(touchEvent);
            return true;
        }
    }
}
