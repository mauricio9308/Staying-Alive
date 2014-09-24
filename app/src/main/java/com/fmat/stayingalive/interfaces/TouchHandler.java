package com.fmat.stayingalive.interfaces;

import java.util.List;

/**
 * Created by mauriciolara on 9/24/14.
 */
public interface TouchHandler {
    public boolean isTouchDown(int pointer);

    public int getTouchX(int pointer);

    public int getTouchY(int pointer);

    public List<TouchEvent> getTouchEvents();
}
