package com.fmat.stayingalive.mrnom.screens;

import android.graphics.Color;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.TouchEvent;
import com.fmat.stayingalive.mrnom.Assets;

import java.util.List;

public class GameScreenPause extends ScreenBase {

    public GameScreenPause(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {

        List<TouchEvent> events = mGame.getInput().getTouchEvents();

        for (TouchEvent event : events) {
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 80, 100, 200, 48)) {
                    mGame.setScreen(new GameScreen(mGame));
                } else if (inBounds(event, 80, 148, 200, 48)) {
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
        graphics.drawPixmap(Assets.pause, 80, 100);
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