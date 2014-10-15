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

    public void move() {
        //I know there is a mathematically superior way to do this but not enough time to find out
        if (orientation <= 90) {
            this.x += speed * ((90 - orientation)/90);
            this.y += speed * (orientation/90);
        } else if (orientation > 90 && orientation <= 180) {
            this.x -= speed * ((orientation - 90)/90);
            this.y += speed * ((180 - orientation)/90);
        } else if (orientation > 180 && orientation <= 270) {
            this.x -= speed * ((270 - orientation)/90);
            this.y -= speed * ((orientation - 180)/90);
        } else {
            this.x += speed * ((orientation - 270)/90);
            this.y -= speed * ((360 - orientation)/90);
        }
        //TODO if it hits a wall, self destructs
    }

}
