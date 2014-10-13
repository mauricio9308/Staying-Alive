package com.fmat.stayingalive.mrnom.world;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kevin on 06/10/2014.
 */
public class Snake {

    public static final int UP = 0;
    public static final int LEFT = 1;
    public static final int DOWN = 2;
    public static final int RIGHT = 3;

    public List<SnakePart> parts = new ArrayList<SnakePart>();
    public int direction;

    public Snake() {
        direction = UP;
        parts.add(new SnakePart(5,6));
        parts.add(new SnakePart(5,7));
        parts.add(new SnakePart(5,8));
    }

    public void turnLeft(){
        direction += 1;
        if (direction > RIGHT) {
            direction = UP;
        }
    }
    public void turnRight(){
        direction -= 1;
        if (direction < UP) {
            direction = RIGHT;
        }
    }
    public void eat() {
        SnakePart end = parts.get(parts.size() - 1);
        parts.add(new SnakePart(end.x, end.y));
    }
    public void advance() {
        SnakePart head = parts.get(0);

        for (int i = parts.size() - 1; i > 0; i--) {
            SnakePart before = parts.get(i - 1);
            SnakePart after = parts.get(i);
            after.x = before.x;
            after.y = before.y;
        }

        if (direction == UP) {
            head.y--;
        } else if (direction == DOWN) {
            head.y++;
        } else if (direction == LEFT) {
            head.x--;
        } else {
            head.x++;
        }

        if (head.x < 0) {
            head.x = World.WORLD_WIDTH - 1;
        } else if (head.y < 0) {
            head.y = World.WORLD_HEIGHT - 1;
        } else if (head.x > World.WORLD_WIDTH - 1) {
            head.x = 0;
        } else if (head.y > World.WORLD_HEIGHT - 1) {
            head.y = 0;
        }


    }
    public boolean checkBitten() {
        SnakePart head = parts.get(0);
        for (int i = 1; i < parts.size(); i++) {
            SnakePart part = parts.get(i);
            if (part.x == head.x && part.y == head.y) {
                return true;
            }
        }
        return false;
    }

}
