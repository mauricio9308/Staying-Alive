package com.fmat.stayingalive.stayingalivegame.world;

/**
 * Created by Kevin on 14/10/2014.
 */
public class PowerUp {

    public static final int DEFAULT_POWERUP_SPEED = 0; //dummy
    public static final int POWERUP_DURATION_TIME = 0; //dummy. This is duration of powerup moving around in world, not duration of its effect

    static enum Effect {
        INVINCIBLE, BURST, SPEEDUP
    }

    int x, y;
    int speed;
    int orientation; //degrees 0-360
    int duration;

    public PowerUp(int x, int y, int orientation, int speed) {
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.duration = POWERUP_DURATION_TIME;
        this.orientation = orientation;
    }
    public PowerUp(int x, int y, int orientation) {
        this(x, y, orientation, DEFAULT_POWERUP_SPEED);
    }


    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }


}
