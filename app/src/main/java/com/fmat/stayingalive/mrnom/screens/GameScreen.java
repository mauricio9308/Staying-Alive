package com.fmat.stayingalive.mrnom.screens;

import android.graphics.Color;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.Input.TouchEvent;
import com.fmat.stayingalive.mrnom.Assets;
import com.fmat.stayingalive.mrnom.Settings;
import com.fmat.stayingalive.mrnom.world.World;

import java.util.List;

/**
 * Created by Kevin on 06/10/2014.
 */
public class GameScreen extends ScreenBase {

    public GameScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<TouchEvent> events = mGame.getInput().getTouchEvents();
        World world = getWorld();

        for (TouchEvent event : events) {
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 0, 64, 64)) {
                    playSound();
                    mGame.setScreen(new GameScreenPause(mGame));
                } else if (inBounds(event, 0, 416, 64, 64)) {
                    world.snake.turnLeft();
                } else if (inBounds(event, 256, 416, 64, 64)) {
                    world.snake.turnRight();
                }
            }
        }

        world.update(deltaTime);

        if (world.gameOver) {
            if (Settings.soundEnabled) Assets.bitten.play(1);
            mGame.setScreen(new GameScreenGameOver(mGame));
            Settings.addScore(world.score);
            Settings.save(mGame.getFileIO());
        }

    }

    @Override
    public void present(float deltaTime) {
        Graphics graphics = mGame.getGraphics();
        graphics.drawPixmap(Assets.background, 0, 0);
        drawWorld();
        graphics.drawLine(0, 416, 480, 416, Color.BLACK);
        graphics.drawPixmap(Assets.buttons, 0, 0, 64, 128, 64, 64);
        graphics.drawPixmap(Assets.buttons, 0, 416, 64, 64, 64, 64);
        graphics.drawPixmap(Assets.buttons, 256, 416, 0, 64, 64, 64);
    }

    @Override
    public void pause() {
        mGame.setScreen(new GameScreenPause(mGame));
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
