package com.fmat.stayingalive.mrnom;

import android.text.method.Touch;

import com.fmat.stayingalive.interfaces.Game;
import com.fmat.stayingalive.interfaces.Graphics;
import com.fmat.stayingalive.interfaces.Input;
import com.fmat.stayingalive.interfaces.Screen;
import com.fmat.stayingalive.interfaces.TouchEvent;

import java.util.List;

/**
 * Created by Kevin on 01/10/2014.
 */
public class MenuScreen extends ScreenBase {

    public MenuScreen(Game game) {
        super(game);
    }

    @Override
    public void update(float deltaTime) {
        List<Input.TouchEvent> touchEventList = mGame.getInput().getTouchEvents();

        for(Input.TouchEvent event : touchEventList) {
            if (event.type == TouchEvent.TOUCH_UP) {
                if (inBounds(event, 0, 416, 64, 64)) {
                    Settings.soundEnabled = !Settings.soundEnabled;
                    if (Settings.soundEnabled) {
                        playSound();
                    }
                }

                if (inBounds(event, 64, 220, 192, 42)) {
                    playSound();
                }
                if (inBounds(event, 64, 262, 192, 42)) {
                    playSound();
                }
                if (inBounds(event, 64, 304, 192, 42)) {
                    playSound();
                }
            }
        }
    }

    @Override
    public void present(float deltaTime) {
        Graphics graphics = mGame.getGraphics();
        graphics.drawPixmap(Assets.background, 0, 0);
        graphics.drawPixmap(Assets.logo, 32, 20);
        graphics.drawPixmap(Assets.mainMenu, 64, 220);
        if (Settings.soundEnabled) {
            graphics.drawPixmap(Assets.buttons, 0, 416, 0, 0, 64, 64);
        } else {
            graphics.drawPixmap(Assets.buttons, 0, 416, 64, 0, 64, 64);
        }
    }

    @Override
    public void pause() {
        Settings.save(mGame.getFileIO());
    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }
}
