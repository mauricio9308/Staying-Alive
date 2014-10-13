package com.fmat.stayingalive.mrnom.screens;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.Pixmap;
import com.fmat.stayingalive.interfaces.Screen;
import com.fmat.stayingalive.interfaces.TouchEvent;
import com.fmat.stayingalive.mrnom.Assets;
import com.fmat.stayingalive.mrnom.MrNomGame;
import com.fmat.stayingalive.mrnom.Settings;
import com.fmat.stayingalive.mrnom.world.Snake;
import com.fmat.stayingalive.mrnom.world.SnakePart;
import com.fmat.stayingalive.mrnom.world.Stain;
import com.fmat.stayingalive.mrnom.world.World;

/**
 * Created by Kevin on 01/10/2014.
 */
public abstract class ScreenBase extends Screen {

    public ScreenBase(Game game) {
        super(game);
    }

    protected World getWorld() {
        MrNomGame mrNomGame = (MrNomGame) mGame;
        return mrNomGame.world;
    }

    protected void playSound() {
        if (Settings.soundEnabled) {
            Assets.click.play(1);
        }
    }

    protected boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if(event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1){
            return true;
        }else {
            return false;
        }
    }

    protected void drawText(Graphics graphics, String line, int x, int y) {
        int srcX = 0;
        int srcWidth = 0;
        for (int i = 0; i < line.length(); i++) {
            char character = line.charAt(i);
            if (character == ' ') {
                x += 20;
            } else if (character == '.') {
                srcX = 200;
                srcWidth = 10;
            } else {
                srcX = (character - '0') * 20;
                srcWidth = 20;
            }
            graphics.drawPixmap(Assets.numbers, x, y, srcX, 0, srcWidth, 32);
            x += srcWidth;
        }
    }

    protected void drawWorld() {
        World world = getWorld();
        Graphics graphics = mGame.getGraphics();
        Snake snake = world.snake;
        SnakePart head = snake.parts.get(0);
        Stain stain = world.stain;

        Pixmap stainPixmap = null;
        if (stain.type == Stain.TYPE_1) {
            stainPixmap = Assets.stain1;
        } else if (stain.type == Stain.TYPE_2) {
            stainPixmap = Assets.stain2;
        } else {
            stainPixmap = Assets.stain3;
        }

        graphics.drawPixmap(stainPixmap, stain.x * 32, stain.y * 32);

        for (int i = 1; i < snake.parts.size(); i++) {
            SnakePart part = snake.parts.get(i);
            graphics.drawPixmap(Assets.tail, part.x * 32, part.y * 32);
        }

        Pixmap headPixmap = null;
        if (snake.direction == Snake.UP) {
            headPixmap = Assets.headUp;
        } else if (snake.direction == Snake.RIGHT) {
            headPixmap = Assets.headRight;
        } else if (snake.direction == Snake.DOWN) {
            headPixmap = Assets.headDown;
        } else {
            headPixmap = Assets.headLeft;
        }

        graphics.drawPixmap(headPixmap, head.x * 32, head.y * 32);

    }


}
