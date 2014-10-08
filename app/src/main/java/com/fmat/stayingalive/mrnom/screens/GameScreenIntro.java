package com.fmat.stayingalive.mrnom.screens;

import android.graphics.Color;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.Input;
import com.fmat.stayingalive.interfaces.TouchEvent;
import com.fmat.stayingalive.mrnom.Assets;

import java.util.List;

/**
 * Created by Kevin on 06/10/2014.
 */
public class GameScreenIntro extends ScreenBase {

    public GameScreenIntro(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> events = mGame.getInput().getTouchEvents();
        if (events.size() > 0) {
            mGame.setScreen(new GameScreen(mGame));
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics g = mGame.getGraphics();
        g.drawPixmap(Assets.background, 0, 0);
        g.drawPixmap(Assets.ready, 47, 100);
        g.drawLine(0, 416, 480, 416, Color.BLACK);

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
