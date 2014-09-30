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
public class MultiTouchHandler implements TouchHandler, View.OnTouchListener {

    private static final int TOTAL_OF_POINTERS = 20;

    private boolean[] mIsTouched = new boolean[TOTAL_OF_POINTERS];
    private int[] mPointerX = new int[TOTAL_OF_POINTERS];
    private int[] mPointerY = new int[TOTAL_OF_POINTERS];
    private Pool<TouchEvent> mTouchEventPool;
    private List<TouchEvent> mTouchEvents = new ArrayList<TouchEvent>();
    private List<TouchEvent> mTouchEventsBuffer = new ArrayList<TouchEvent>();

    private float mScaleX;
    private float mScaleY;

    public MultiTouchHandler(View view, float scaleX, float scaleY) {
        Pool.PoolObjectFactory<TouchEvent> factory = new Pool.PoolObjectFactory<TouchEvent>() {
            @Override
            public TouchEvent createObject() {
                return new TouchEvent();
            }
        };
        mTouchEventPool = new Pool<TouchEvent>(factory, 100);
        view.setOnTouchListener(this);
        mScaleX = scaleX;
        mScaleY = scaleY;
    }


    @Override
    public boolean isTouchDown(int pointer) {
        synchronized (this) {
            if (pointer < 0 || pointer >= 20)
                return false;
            else
                return mIsTouched[pointer];
        }
    }

    @Override
    public int getTouchX(int pointer) {
        synchronized (this) {
            if (pointer < 0 || pointer >= 20) {
                return 0;
            } else {
                return mPointerX[pointer];
            }
        }
    }

    @Override
    public int getTouchY(int pointer) {
        synchronized (this) {
            if (pointer < 0 || pointer >= 20) {
                return 0;
            } else {
                return mPointerY[pointer];
            }
        }
    }

    @Override
    public List<TouchEvent> getTouchEvents() {
        synchronized (this) {
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
            int action = event.getAction() & MotionEvent.ACTION_MASK;
            int pointerIndex = (event.getAction() & MotionEvent.ACTION_POINTER_ID_MASK)
                    >> MotionEvent.ACTION_POINTER_ID_SHIFT;
            int pointerId = event.getPointerId(pointerIndex);
            TouchEvent touchEvent;
            switch (action) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_POINTER_DOWN:
                    touchEvent = mTouchEventPool.newObject();
                    touchEvent.type = TouchEvent.TOUCH_DOWN;
                    touchEvent.pointer = pointerId;
                    touchEvent.x = mPointerX[pointerId] = (int) (event.getX(pointerIndex) * mScaleX);
                    touchEvent.y = mPointerY[pointerId] = (int) (event.getY(pointerIndex) * mScaleY);
                    mIsTouched[pointerId] = true;
                    mTouchEventsBuffer.add(touchEvent);
                    break;
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                case MotionEvent.ACTION_CANCEL:
                    touchEvent = mTouchEventPool.newObject();
                    touchEvent.type = TouchEvent.TOUCH_UP;
                    touchEvent.pointer = pointerId;
                    touchEvent.x = mPointerX[pointerId] = (int) (event
                            .getX(pointerIndex) * mScaleX);
                    touchEvent.y = mPointerY[pointerId] = (int) (event
                            .getY(pointerIndex) * mScaleY);
                    mIsTouched[pointerId] = false;
                    mTouchEventsBuffer.add(touchEvent);
                    break;
                case MotionEvent.ACTION_MOVE:
                    int pointerCount = event.getPointerCount();
                    for (int i = 0; i < pointerCount; i++) {
                        pointerIndex = i;
                        pointerId = event.getPointerId(pointerIndex);
                        touchEvent = mTouchEventPool.newObject();
                        touchEvent.type = TouchEvent.TOUCH_DRAG;
                        touchEvent.pointer = pointerId;
                        touchEvent.x = mPointerX[pointerId] = (int) (event
                                .getX(pointerIndex) * mScaleX);
                        touchEvent.y = mPointerY[pointerId] = (int) (event
                                .getY(pointerIndex) * mScaleY);
                        mTouchEventsBuffer.add(touchEvent);
                    }
                    break;
            }
            return true;
        }
    }
}
