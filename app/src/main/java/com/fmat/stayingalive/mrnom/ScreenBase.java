package com.fmat.stayingalive.mrnom;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.Screen;
import com.fmat.stayingalive.interfaces.TouchEvent;

/**
 * Created by Kevin on 01/10/2014.
 */
public abstract class ScreenBase extends Screen {

    public ScreenBase(Game game) {
        super(game);
    }

    protected void playSound() {
        if (Settings.soundEnabled) {
            Assets.click.play(1);
        }
    }

    protected boolean inBounds(TouchEvent event, int x, int y, int width, int height) {
        if (event.x > x && event.x < x + width && event.y > y && event.y > y + height) {
            return true;
        }
        return false;
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

}
