package com.fmat.stayingalive.stayingalivegame.world;

/**
 * Created by Kevin on 14/10/2014.
 */
public class Bullet {

    public static final int DEFAULT_BULLET_SPEED = 0; //dummy

    int x, y;
    int speed;
    int orientation; //degrees 0-360

    public Bullet(int x, int y, int orientation, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.orientation = orientation;
    }
    public Bullet(int x, int y, int orientation) {
        this(x, y, orientation, DEFAULT_BULLET_SPEED);
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

}
