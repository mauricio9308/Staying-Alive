package com.fmat.stayingalive.model;

/**
 * Created by mauriciolara on 10/6/14.
 */
public class World {
    public static final int WORLD_WIDTH = 10;
    public static final int WORLD_HEIGHT = 19;

    public static final int SCORE_INCREMENT = 10;

    public static final float TICK_INITIAL = 0.5f;
    public static final float TICK_DECREMENT = 0.005f;

    private int mScore;
    private boolean mIsGameOver = false;


}
