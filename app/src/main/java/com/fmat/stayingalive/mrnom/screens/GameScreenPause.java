package com.fmat.stayingalive.mrnom.screens;

import android.graphics.Color;
import android.text.method.Touch;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.Input;
import com.fmat.stayingalive.interfaces.TouchEvent;
import com.fmat.stayingalive.mrnom.Assets;
import com.fmat.stayingalive.mrnom.world.World;

import java.util.List;

/**
 * Created by Kevin on 08/10/2014.
 */
public class GameScreenPause extends ScreenBase {

    public GameScreenPause(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {

        List<Input.TouchEvent> events = mGame.getInput().getTouchEvents();
        World world = getWorld();

        for (Input.TouchEvent event : events) {
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
