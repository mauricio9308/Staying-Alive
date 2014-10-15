package com.fmat.stayingalive.stayingalivegame.world;

/**
 * Created by Kevin on 14/10/2014.
 */
public class Player {

    public static final int PLAYER_START_X = 0; //dummy
    public static final int PLAYER_START_Y = 0; //dummy

    public static final int WIDTH = 0; //dummy
    public static final int HEIGHT = 0; //dummy

    public static final int JUMP_STATE_MAX = 10; //dummy

    static enum Orientation {
        LEFT, RIGHT
    }

    int x, y;
    Orientation orientation;
    int jumpState;
    boolean crouching;

    public Player() {
        this.x = PLAYER_START_X;
        this.y = PLAYER_START_Y;
        jumpState = 0;
        crouching = false;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
