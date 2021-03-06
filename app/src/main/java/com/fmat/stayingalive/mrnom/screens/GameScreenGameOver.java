package com.fmat.stayingalive.mrnom.screens;

import android.graphics.Color;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.TouchEvent;
import com.fmat.stayingalive.mrnom.Assets;

import java.util.List;

/**
 * Created by Kevin on 06/10/2014.
 */
public class GameScreenGameOver extends ScreenBase {

    public GameScreenGameOver(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {

        List<TouchEvent> events = mGame.getInput().getTouchEvents();

        for (TouchEvent event : events) {
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 128, 200, 64, 64)) {
                    mGame.setScreen(new MenuScreen(mGame));
                }
            }
        }

    }

    @Override
    public void present(float deltaTime) {
        Graphics graphics = mGame.getGraphics();
        graphics.drawPixmap(Assets.background, 0, 0);
        drawWorld();
        graphics.drawLine(0, 416, 480, 416, Color.BLACK);

        graphics.drawPixmap(Assets.gameOver, 68, 100);
        graphics.drawPixmap(Assets.buttons, 128, 200, 0, 128, 64, 64);

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
